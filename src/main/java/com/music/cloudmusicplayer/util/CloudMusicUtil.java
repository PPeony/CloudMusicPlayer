package com.music.cloudmusicplayer.util;

import com.music.cloudmusicplayer.common.Property;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @Author: Peony
 * @Date: 2020/11/10 14:27
 */
public class CloudMusicUtil {

    /**
     * @param file 多媒体文件
	 * @param request HttpServletRequest
     * @return java.lang.String
     */
    public static String uploadFile(MultipartFile file, HttpServletRequest request) {

        //1.后半段目录：  2020/03/15
        String directory = "";
        /**
         *  2.文件保存目录  D:/images/2020/03/15/
         *  如果目录不存在，则创建
         */
        File dir = new File(Property.FILE_PATH + directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        System.out.println("文件上传，保存位置：" + Property.FILE_PATH + directory);
        //3.给文件重新设置一个名字
        //后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String newFileName= UUID.randomUUID().toString().replaceAll("-", "")+suffix;
        //4.创建这个新文件
        File newFile = new File(Property.FILE_PATH + directory + newFileName);
        //5.复制操作
        try {
            file.transferTo(newFile);
            //协议 :// ip地址 ：端口号 / 文件目录(/images/2020/03/15/xxx.jpg)
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/musicfile/" + directory + newFileName;
            System.out.println("图片上传，访问URL：" + url);
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * @param param
     * @return boolean 出现问题返回true
     */
    public static boolean checkString(String param) {
        if (param == null) {
            return true;
        }
        if (param.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * @param s 需要加密的字符串
     * @return java.lang.String 加密结果
     */
    public static String MD5(String s) {
        // 需要加密的字符串=>s
        String res = null;
        try {
            // 加密对象，指定加密方式
            MessageDigest md5 = MessageDigest.getInstance("md5");
            // 准备要加密的数据
            byte[] b = s.getBytes();
            // 加密
            byte[] digest = md5.digest(b);
            // 十六进制的字符
            char[] chars = new char[] { '0', '1', '2', '3', '4', '5',
                    '6', '7' , '8', '9', 'A', 'B', 'C', 'D', 'E','F' };
            StringBuffer sb = new StringBuffer();
            // 处理成十六进制的字符串(通常)
            for (byte bb : digest) {
                sb.append(chars[(bb >> 4) & 15]);
                sb.append(chars[bb & 15]);
            }
            res = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return res;
    }



}
