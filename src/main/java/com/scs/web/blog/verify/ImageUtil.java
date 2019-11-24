package com.scs.web.blog.verify;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @ClassName ImageUtil
 * @Description 图像工具类
 * @Author ding
 * @Date 2019/11/20 11:08
 * @Version 1.0
 **/
public class ImageUtil {
    public static BufferedImage getImage(int width, int height, String content) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) img.getGraphics();
        graphics.setColor(new Color(68, 134, 49));
        graphics.fillRect(0, 0, width, height);
        graphics.setPaint(new Color(60, 63, 65));
        Font font = new Font("微软雅黑", Font.BOLD, 40);
        graphics.setFont(font);
        graphics.drawString(content, width / 3, height / 2);
        graphics.rotate(1.5);
        return img;
    }
//    /**
//     * 将字符串绘制成指定大小的矩形图片
//     * @param content
//     * @param width
//     * @param height
//     * @return
//     */
//    public static BufferedImage getImage(String content, int width, int height){
//        BufferedImage img = new BufferedImage(width,height,1);
//        Graphics2D g = (Graphics2D) img.getGraphics();
//        Color foreColor = new Color(89,168,105);
//        Color bgColor = new Color(66,3,55);
//        g.setColor(bgColor);
//        g.fillRect(0,0,width,height);
//        g.setPaint(foreColor);
//        Font font = new Font("微软雅黑",Font.BOLD,25);
//        g.setFont(font);
//        g.drawString(content,100,50);
//        return img;
//    }
//
//    public static void main(String[] args) throws IOException {
//        String code = Util.getRandomString(4);
//        BufferedImage img = ImageUtil.getImage(code,200,100);
////        将
//        File file = new File("D:/code.jpg");
//        ImageIO.write(img,"jpg",file);
//    }
}
