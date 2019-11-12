package com.geek.onlyone;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

/**
 * @Athor wwr
 */

@Slf4j
public class ImageUtils {

    /**
     * 根据指定资源定位符读取图片
     * @param uri 可以是url也可以是本地图片地址
     * @return BufferedImage，若为null则读取失败
     */
    public static BufferedImage getImage(String uri){
        try{
            if(uri.startsWith("http://") || uri.startsWith("https://")){
                return ImageIO.read(new URL(uri));
            }
            return ImageIO.read(new File(uri));
        }
        catch (Exception e){
            log.warn("读取图片资源失败，", e);
        }
        return null;
    }

    /**
     * 将pastedImage贴到dstImage指定区域
     * @param dstImage 目标图片
     * @param x 目标区域起点x坐标
     * @param y 目标区域起点y坐标
     * @param width 目标区域宽度
     * @param height 目标区域高度
     * @param pastedImage 要粘贴的图片
     */
    public static void paste(BufferedImage dstImage, int x, int y, int width, int height, BufferedImage pastedImage){
        Graphics graphics = dstImage.getGraphics();
        graphics.drawImage(pastedImage, x, y, width, height,null);
    }

    /**
     * 将pastedImage粘贴到dstImage指定的圆形区域
     * @param dstImage 目标图片
     * @param x 目标区域起点x坐标
     * @param y 目标区域起点y坐标
     * @param width 目标区域宽度（圆形的直径）
     * @param pastedImage 粘贴的图片
     */
    public static void pasteCircle(BufferedImage dstImage, int x, int y, int width, BufferedImage pastedImage){
        Graphics graphics = dstImage.getGraphics();
        Shape clip = graphics.getClip();
        Ellipse2D.Double shape = new Ellipse2D.Double(x, y, x+width, y+width);
        graphics.setClip(shape);
        graphics.drawImage(pastedImage, x, y, x + width, y + width, null);
        graphics.setClip(clip);
    }
}
