package com.psm.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @BelongsProject: psm-backend
 * @BelongsPackage: com.psm.utils
 * @Author: ChenLan
 * @Date: 2023/10/24 20:03
 * @Description:用于给多媒体文件哈希去重，以免重复的文件浪费磁盘空间
 */
@Component
public class DuplicateFilterUitil {
    public static BufferedImage standardizeImage(BufferedImage image) {
        // 标准化图片尺寸
        int width = 200;
        int height = 200;
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // 创建新的标准化图片
        BufferedImage standardizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = standardizedImage.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        return standardizedImage;
    }

    //计算图片的哈希值
    public static String calculateImgHash(MultipartFile multipartFile) throws IOException, NoSuchAlgorithmException {
        // 读取原始图片
        BufferedImage originalImage = ImageIO.read(multipartFile.getInputStream());

        // 标准化图片
        BufferedImage standardizedImage = standardizeImage(originalImage);

        // 计算哈希值
        MessageDigest md = MessageDigest.getInstance("MD5");
        try (DigestInputStream dis = new DigestInputStream((InputStream)ImageIO.createImageInputStream(standardizedImage), md)) {
            while (dis.read() != -1) {
                // 读取文件内容以更新哈希值
            }
            md = dis.getMessageDigest();
        }

        byte[] hashBytes = md.digest();
        StringBuilder hashBuilder = new StringBuilder();
        for (byte b : hashBytes) {
            hashBuilder.append(String.format("%02x", b));
        }

        return hashBuilder.toString();
    }
}