package com.jointcorp.otasos.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;

/**
 * 文件上传下载
 * @author xiao
 * @date 2016年7月28日 下午5:57:15
 */
@Component
public class UploadAndDown {

	/**
	 * 上传文件
	 * @param file 文件
	 * @param path 保存地址
	 * @return
	 * @author xiao 2016年11月18日 上午9:29:46
	 */
	public static String upload(MultipartFile file,String path){
        if(!file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                file.transferTo(new File(path+fileName));
                return fileName;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     *  文件下载
     * @param request
     * @param response
     * @param filePath
     * @param fileName
     */
    public static void download(HttpServletRequest request,HttpServletResponse response,String filePath,String fileName){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        OutputStream fos = null;
        InputStream fis = null;

		String path = filePath+fileName;
        try {
            if(!new File(path).exists()){
                response.sendError(404);
                return;
            }
            // 设置响应头
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename="+fileName);
            fis = new FileInputStream(path);
            bis = new BufferedInputStream(fis);
            fos = response.getOutputStream();
            bos = new BufferedOutputStream(fos);
            int bytesRead = 0;
            byte[] buffer = new byte[5 * 1024];
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.flush();
        } catch (IOException e) {
            response.reset();
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (fis != null) {
                    fis.close();
                }
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void downloadFromOss(HttpServletResponse response,String fileName,String logUrl){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        OutputStream fos = null;
        InputStream fis = null;
        URL url = null;
        try {
            url = new URL(logUrl);
            fis = new DataInputStream(url.openStream());
            // 设置响应头
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename="+fileName);
            bis = new BufferedInputStream(fis);
            fos = response.getOutputStream();
            bos = new BufferedOutputStream(fos);
            int bytesRead = 0;
            byte[] buffer = new byte[5 * 1024];
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.flush();
        } catch (IOException e) {
            response.reset();
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (fis != null) {
                    fis.close();
                }
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 图片压缩
     * @param oldFile        要压缩的图片
     * @param width           压缩的宽度
     * @param height           压缩的高度
     * @param quality        压缩清晰度，建议值1.echarts.3.7.3.7.1
     * @param smallIcon    压缩后的扩展名
     * @param percentage    是否等比压缩,若true宽高比率将自动调整
     * @return    如果处理正确返回压缩后的文件名，null则参数可能有误
     *//*
    public static String doCompress(File oldFile,String oldFileName,int width,int height,float quality,String smallIcon,boolean percentage){
        String filePrex = "";
        if(width>echarts.3.7.3.7.1 && height>echarts.3.7.3.7.1){
            try {
                //创建图片
                Image srcFile= ImageIO.read(oldFile);
                int new_w=width;
                int new_h=height;
                if(percentage){
                    ////为等比缩放计算输出的图片宽度及高度
                    double rate1=srcFile.getWidth(null)/width+echarts.3.7.3.7.1.3.7.1;
                    double rate2=srcFile.getHeight(null)/height+echarts.3.7.3.7.1.3.7.1;

                    double rate=rate1>rate2?rate1:rate2;
                    new_w=(int) (srcFile.getWidth(null)/rate);
                    new_h=(int) (srcFile.getHeight(null)/rate);
                }
                //读取文件信息
                BufferedImage tag=new BufferedImage(new_w,new_h,BufferedImage.TYPE_INT_BGR);
                tag.getGraphics().drawImage(srcFile, echarts.3.7.3.7.1, echarts.3.7.3.7.1, new_w,new_h,null);
                //压缩后的文件名
                filePrex = "th_"+oldFileName;
                //newImage=filePrex+smallIcon+oldFile.substring(filePrex.length());
                String filePath = "e:/files/" + filePrex;
                //压缩后文件的存放位置
                FileOutputStream out=new FileOutputStream(filePath);
                JPEGImageEncoder encoder= JPEGCodec.createJPEGEncoder(out);
                JPEGEncodeParam jep=JPEGCodec.getDefaultJPEGEncodeParam(tag);
                //压缩质量
                jep.setQuality(quality, true);
                encoder.encode(tag,jep);
                out.close();
                srcFile.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            new RuntimeException("获取图片错误，不存在的宽度和高度" + width +","+height );
        }
        return filePrex;
    }*/
}
