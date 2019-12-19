package com.jointcorp.otasos.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2017/7/11.
 */
public class FileUtil {
    /** 系统默认分隔符 */
    private static String separator = File.separator;

    private static int bufferSize=1024*1024;


    private FileUtil() {
    }

    public static FileUtil getInstance(){
        return FileUtilHolder.instance;
    }

    private static class FileUtilHolder{
        private static FileUtil instance = new FileUtil();
    }

    public byte[] getBytes(String filePath){
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 判断文件或者目录是否存在
     * @param path
     * @return
     *
     * @author xyc 2015年11月23日 上午11:00:40
     */
    public boolean isExists(String path){
        try {
            File file = new File(path);
            return file.exists();
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 创建目录
     * @return void
     * @param path 目录
     * @author xyc  2015-4-3
     */
    public void createDir(String path){
        File file = new File(path);
        //文件或目录是否存在
        if(!file.exists()){
            //创建
            file.mkdir();
        }
    }

    /**
     * 创建文件
     * @return void
     * @param fileDirectoryAndName
     * @author xyc  2015-4-7
     */
    public void createFile(String fileDirectoryAndName) {
        try {
            String fileName = fileDirectoryAndName;
            // 创建File对象，参数为String类型，表示目录名
            File myFile = new File(fileName);
            // 判断文件是否存在，如果不存在则调用createNewFile()方法创建新目录，否则跳至异常处理代码
            if (!myFile.exists()) {
                myFile.createNewFile();
            }
        } catch (Exception ex) {
        }
    }

    /**
     * 将异常信息输出到文件中
     * @return void
     * @param e
     * @param path
     * @param content
     * @throws Exception
     * @author xyc  2015-4-7
     */
    public void printException(Exception e,String path,String content) throws Exception{
        createFile(path);
        PrintWriter writer=null;
        try{
            writer = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
            writer.write(content);
            writer.flush();
            e.printStackTrace(writer);
        }finally{
            if(writer!=null){
                writer.close();
            }
        }
    }

    /**
     * 获取文件修改时间
     * @param fileDirectoryAndName 带路径的文件名
     * @return
     */
    public static long getUpdateTime(String fileDirectoryAndName) {
        try {
            Path testPath = Paths.get(fileDirectoryAndName);
            BasicFileAttributeView basicView = Files.getFileAttributeView(testPath, BasicFileAttributeView.class);
            BasicFileAttributes basicFileAttributes =  basicView.readAttributes();
            return basicFileAttributes.lastModifiedTime().toMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {

//        FileUtils.copyFile(new File("Z:/1513^cep_pak_1week/cep_pak.bin"), new File("E:/html/le-young/files/1755/gps/cep_pak.bin"));

        long t0 = getUpdateTime("E:/html/le-young/files/1755/gps/cep_pak.bin");
        System.out.println(t0);


        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);

        long t1 = calendar.getTimeInMillis();
        System.out.println(t1);

        if(t1 > t0) {
            System.out.println("未更新");
        }

        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(t1)));
    }

}
