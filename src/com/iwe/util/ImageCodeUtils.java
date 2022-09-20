package com.iwe.util;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 生成图片验证码
 */
public class ImageCodeUtils {

//    //根据传入的位数，生成随机字符
//    public static String getRandomStr(int length)
//    {
//        String s = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
//
//        Random random = new Random(System.currentTimeMillis());
//
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < length; i++) {
//            int i1 = random.nextInt(s.length());
//            sb.append(s.charAt(i1));
//        }
//        return sb.toString();
//    }
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//
//        for (int i = 0; i < 100; i++) {
//            Thread.sleep(10);
//            String randomStr = getRandomStr(4);
//            System.out.println(randomStr);
//        }
////        //BuffereImage 图片缓冲流
////        BufferedImage bufferedImage = createImageCode(100, 34, "ABCD");//生成图片的缓冲流
////        //把缓冲流，写到某个输出流
////        ImageIO.write(bufferedImage,"jpg",new FileOutputStream("/Users/edz/Desktop/11.jpg"));
//    }

    /**
     *
     * @param width  生成的图片的宽度
     * @param height 生成的图片的高度
     * @param code  把什么字符生成到图片上
     * @return 缓冲图片流
     */
    /**
     *一般公共的方法，建议用静态的
     */
    public static BufferedImage createImageCode(int width, int height, String code) {
        int codeSize = code.length();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Random rand = new Random();
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color[] colors = new Color[5];
        Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA,
                Color.ORANGE, Color.PINK, Color.YELLOW };
        float[] fractions = new float[colors.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
            fractions[i] = rand.nextFloat();
        }
        Arrays.sort(fractions);

        g2.setColor(Color.GRAY);// 设置边框色
        g2.fillRect(0, 0, width, height);

        Color c = getRandColor(200, 250);
        g2.setColor(c);// 设置背景色
        g2.fillRect(0, 2, width, height - 4);

        // 绘制干扰线
        Random random = new Random();
        g2.setColor(getRandColor(160, 200));// 设置线条的颜色
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            g2.drawLine(x, y, x + xl + 40, y + yl + 20);
        }

        // 添加噪点
        float yawpRate = 0.05f;// 噪声率
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int rgb = getRandomIntColor();
            image.setRGB(x, y, rgb);
        }

        shear(g2, width, height, c);// 使图片扭曲

        g2.setColor(getRandColor(100, 160));
        int fontSize = height - 4;
        Font font = new Font("Algerian", Font.ITALIC, fontSize);
        g2.setFont(font);
        char[] chars = code.toCharArray();
        for (int i = 0; i < codeSize; i++) {
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1),
                    (width / codeSize) * i + fontSize / 2, height / 2);
            g2.setTransform(affine);
            g2.drawChars(chars, i, 1, ((width - 10) / codeSize) * i + 5, height / 2 + fontSize / 2 - 10);
        }

        g2.dispose();
        return image;
    }

    private static Color getRandColor(int fc, int bc) {
        Random random = ThreadLocalRandom.current();

        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    private static int[] getRandomRgb() {
        Random random = ThreadLocalRandom.current();
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    private static void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private static void shearX(Graphics g, int w1, int h1, Color color) {
        Random random = ThreadLocalRandom.current();
        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * phase) / frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    private static void shearY(Graphics g, int w1, int h1, Color color) {
        Random random = ThreadLocalRandom.current();
        int period = random.nextInt(40) + 10; // 50;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * phase) / frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }
    }

}
