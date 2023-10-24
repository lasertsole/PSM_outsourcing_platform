package com.psm.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @BelongsProject: psm-backend
 * @BelongsPackage: com.psm.utils
 * @Author: ChenLan
 * @Date: 2023/10/25 0:29
 * @Description:
 */
public class MD5 {
    private static final Logger logger = LoggerFactory.getLogger(MD5.class);
    protected static char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    protected static MessageDigest messagedigest=null;

    static{

        try{
            messagedigest= MessageDigest.getInstance("MD5");

        }catch(NoSuchAlgorithmException e) {
            logger.error("MD5FileUtil messagedigest初始化失败",e);
        }

    }

    public static String getFileMD5(File file) {
        FileInputStream in=null;
        FileChannel ch=null;
        String md5="";
        try{
            in = new FileInputStream(file);
            ch = in.getChannel();

            // MappedByteBuffer存在内存占用和文件关闭等不确定问题。如果后续有操作文件需求，不建议使用
            MappedByteBuffer byteBuffer=ch.map(FileChannel.MapMode.READ_ONLY, 0,file.length());
            messagedigest.update(byteBuffer);
            byteBuffer.clear();
            md5 = bufferToHex(messagedigest.digest());
            System.out.println(md5);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(ch!=null) {
                try{
                    ch.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(in!=null){
                try{
                    in.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return md5;
    }

    public static String getStreamMD5(InputStream in) {
        String md5="";
        try{
            ByteBuffer byteBuffer= ByteBuffer.wrap(IOUtils.toByteArray(in));
            messagedigest.update(byteBuffer);
            byteBuffer.clear();
            md5=bufferToHex(messagedigest.digest());
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(in!=null) {
                try{
                    in.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return md5;
    }

    private static String bufferToHex(byte bytes[]){
        return bufferToHex(bytes, 0,bytes.length);
    }
    private static String bufferToHex(byte bytes[],int m,int n) {
        StringBuffer stringbuffer=new StringBuffer(2 *n);
        int k=m+n;
        for(int l=m; l<k; l++){
            appendHexPair(bytes[l],stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer){
        char c0=hexDigits[(bt& 0xf0) >> 4];
        char c1=hexDigits[bt& 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }
}
