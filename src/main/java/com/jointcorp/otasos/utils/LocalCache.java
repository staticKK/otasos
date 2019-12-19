package com.jointcorp.otasos.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LocalCache {

    private Map<String,CacheData> CACHE_DATA = new ConcurrentHashMap<>();

    private static ScheduledExecutorService swapPool = Executors.newScheduledThreadPool(5);

    private ReentrantLock lock = new ReentrantLock();

    public LocalCache(){
        //每5秒清除一次过期数据
        swapPool.scheduleWithFixedDelay(new SwapWork(), 5, 5, TimeUnit.SECONDS);
    }

    private <T> void cache(String key,T t,long expire) {
        CacheData<T> c = new CacheData(t,expire);
        CACHE_DATA.put(key,c);
    }

    public <T> T tryGet(String key,T t,long expire) {
        lock.lock();
        try {
            CacheData<T> data = CACHE_DATA.get(key);
            if (data != null && (data.getExpire() >= System.currentTimeMillis())) {
                return data.getData();
            } else {
                cache(key,t,expire);
            }
        } finally {
            lock.unlock();
        }
        return null;
    }

    private class CacheData<T>{
        CacheData(T t,long expire){
            expire = expire <= 0 ? 0 : expire;
            this.data = t;
            this.expire = System.currentTimeMillis() + expire;
        }
        private T data;
        private long expire;   // 过期时间,存活到的时间点 毫秒
        public T getData() {
            return data;
        }
        public long getExpire() {
            return expire;
        }
    }

    private class SwapWork implements Runnable{
        @Override
        public void run() {
            lock.lock();
            try {
                for(String key : CACHE_DATA.keySet()) {
                    CacheData c = CACHE_DATA.get(key);
                    if(c.getExpire() < System.currentTimeMillis()) {
                        CACHE_DATA.remove(key);
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

}
