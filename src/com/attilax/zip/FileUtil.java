/*
 * FileUtil.java
 * Copyright (C) 2007-3-19  <JustinLei@gmail.com>
 *
 *        This program is free software; you can redistribute it and/or modify
 *        it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation; either version 2 of the License, or
 *     (at your option) any later version.
 *
 *       This program is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *        GNU General Public License for more details.
 *
 */
package com.attilax.zip;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.attilax.io.filex;
/**
 * 文件工具类
 * 
 * @author TangLei <justinlei@gmail.com>
 * @date 2009-2-24
 */
public class FileUtil {
    private static Log log = LogFactory.getLog(FileUtil.class);
   // private FileUtil() {}
    
    /**
     * 获取随机的文件名称
     * @param seed    随机种子
     * @return
     */
    public static String getRandomFileName(String seed) {
        byte[] ra = new byte[100];
        new Random().nextBytes(ra);
        StringBuilder build = new StringBuilder("");
        for (int i = 0; i < ra.length; i++) {
            build.append(Byte.valueOf(ra[i]).toString());
        }
        String currentDate = Long.valueOf(new Date().getTime()).toString();
        seed = seed + currentDate + build.toString();
   //     return EncryptUtils.getMD5ofStr(seed).toLowerCase();
        return "";
    }
    
    /**
     * 列出所有当前层的文件和目录
     * 
     * @param dir            目录名称
     * @return fileList    列出的文件和目录
     */
    public static File[] ls(String dir) {
        return new File(dir).listFiles();
    }
    
    /**
     * 根据需要创建文件夹
     * 
     * @param dirPath 文件夹路径
     * @param del    存在文件夹是否删除
     */
    public static void mkdir(String dirPath,boolean del) {
        File dir = new File(dirPath);
        if(dir.exists()) {
            if(del)
                dir.delete();
            else return;
        }
        dir.mkdirs();
    }
    
    /**
     * 删除文件和目录
     * 
     * @param path
     * @throws Exception
     */
    public static void rm(String path) throws Exception{
        if(log.isDebugEnabled())
            log.debug("需要删除的文件: " + path);
        File file = new File(path);
        if(!file.exists()) {
            if(log.isWarnEnabled())
                log.warn("文件<" + path + ">不存在");
            return;
        }
        if(file.isDirectory()) {
            File[] fileList = file.listFiles();
            if(fileList == null || fileList.length == 0) {
                file.delete();
            } else {
                for (File _file : fileList) {
                    rm(_file.getAbsolutePath());
                }
            }
        file.delete();
        } else {
            file.delete();
        }
    }
    
    /**
     * 移动文件
     * 
     * @param source     源文件
     * @param target         目标文件
     * @param cache        文件缓存大小
     * @throws Exception
     */
    public static void mv(String source,String target,int cache) throws Exception {
        if(source.trim().equals(target.trim()))
            return;
        byte[] cached = new byte[cache];
        FileInputStream fromFile = new FileInputStream(source);
        FileOutputStream toFile = new FileOutputStream(target);
        while(fromFile.read(cached) != -1) {
            toFile.write(cached);
        }
        toFile.flush();
        toFile.close();
        fromFile.close();
        new File(source).deleteOnExit();
    }
    
    /**
     * 把属性文件转换成Map
     * 
     * @param propertiesFile
     * @return
     * @throws Exception
     */
    public static final Map<String, String> getPropertiesMap(String propertiesFile) throws Exception{
        Properties properties = new Properties();
        FileInputStream inputStream = new FileInputStream(propertiesFile);
        properties.load(inputStream);
        Map<String, String> map = new HashMap<String, String>();
        Set<Object> keySet = properties.keySet();
        for (Object key : keySet) {
            map.put((String)key, properties.getProperty((String)key));
        }
        return map;
    }
    
    @SuppressWarnings("unchecked")
    public static final Map<String, String> getPropertiesMap(Class clazz,String fileName) throws Exception{
        Properties properties = new Properties();
        InputStream inputStream = clazz.getResourceAsStream(fileName);
        if(inputStream == null)
            inputStream = clazz.getClassLoader().getResourceAsStream(fileName);
        properties.load(inputStream);
        Map<String, String> map = new HashMap<String, String>();
        Set<Object> keySet = properties.keySet();
        for (Object key : keySet) {
            map.put((String)key, properties.getProperty((String)key));
        }
        return map;
    }
    
    /**
     * 把属性文件转换成Map
     * 
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static final Map<String, String> getPropertiesMap(InputStream inputStream) throws Exception{
        Properties properties = new Properties();
        properties.load(inputStream);
        Map<String, String> map = new HashMap<String, String>();
        Set<Object> keySet = properties.keySet();
        for (Object key : keySet) {
            map.put((String)key, properties.getProperty((String)key));
        }
        return map;
    }
    
    /**
     * 把文本文件转换成String
     * 
     * @param fullPath
     * @return
     * @throws Exception
     */
    public static String readFile(String fullPath) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(fullPath));
        if(reader == null)
            return null;
        StringBuilder builder = new StringBuilder("");
        String line = null;
        while((line = reader.readLine()) != null) {
            builder.append(line + "/n");
        }
        return builder.toString();
    }
    
    /**
     * 获取资源文件流
     * 
     * @param clazz
     * @param name
     * @return
     */
    @SuppressWarnings("unchecked")
    public static InputStream getResourceAsStream(Class clazz,String name) {
        try {
            InputStream inputStream = clazz.getResourceAsStream(name);
            if(inputStream == null) 
                inputStream = clazz.getClassLoader().getResourceAsStream(name);
            return inputStream;
        } catch (Exception e) {
            if(log.isWarnEnabled())
                log.warn("获取资源文件失败", e);
            return null;
        }
    }

 // 补充一下里面用到的一个自己写的FileUtil的一个方法
    /**

         * 生产文件 如果文件所在路径不存在则生成路径

         * 

         * @param fileName

         *            文件名 带路径

         * @param isDirectory 是否为路径

         * @return

         * @author yayagepei

         * @date 2008-8-27

         */

        public static File buildFile(String fileName, boolean isDirectory) {

            File target = new File(fileName);

            if (isDirectory) {

                target.mkdirs();

            } else {

                if (!target.getParentFile().exists()) {

                    target.getParentFile().mkdirs();

                    target = new File(target.getAbsolutePath());

                }

            }

            return target;

        }
}