package com.lizheng.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class QrCodeUtil {

    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;

    public void qrEncode(String content, String path) throws IOException, WriterException {
        BitMatrix bitMatrix = getBitMatrix(content);
        // 输出原图片
        MatrixToImageWriter.writeToPath(bitMatrix, "gif", new File(path).toPath());
        System.out.println("输出成功.");
    }

    /**
     * 使用二维码编码文本
     * @param content
     * @return
     * @throws WriterException
     */
    public BufferedImage qrEncode(String content) throws WriterException {
        BitMatrix bitMatrix = getBitMatrix(content);
        // 输出原图片
        MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);
//        BufferedImage bufferedImage = LogoMatrix(MatrixToImageWriter.toBufferedImage(bitMatrix,matrixToImageConfig), new File("/home/didi/logo.png"));
//        输出带logo图片
//        ImageIO.write(bufferedImage, "gif", new File("/home/didi/zxing1.gif"));//输出带logo图片
        return MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);
    }

    private BitMatrix getBitMatrix(String content) throws WriterException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        //内容编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置二维码边的空度，非负数
        hints.put(EncodeHintType.MARGIN, 1);
        return new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
    }

    public BufferedImage LogoMatrix(BufferedImage matrixImage, File logoFile) throws IOException{
        Graphics2D g2 = matrixImage.createGraphics();

        int matrixWidth = matrixImage.getWidth();
        int matrixHeigh = matrixImage.getHeight();

        BufferedImage logo = ImageIO.read(logoFile);

        //开始绘制图片
        g2.drawImage(logo,matrixWidth/5*2,matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5, null);
        BasicStroke stroke = new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        g2.setStroke(stroke);
        //指定弧度的圆角矩形
        RoundRectangle2D.Float round = new RoundRectangle2D.Float((float) matrixWidth/5*2,
                (float) matrixHeigh/5*2, (float) matrixWidth/5, (float)matrixHeigh/5,20,20);
        g2.setColor(Color.white);
        g2.draw(round);

        //设置logo 有一道灰色边框
        BasicStroke stroke2 = new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        g2.setStroke(stroke2);
        RoundRectangle2D.Float round2 = new RoundRectangle2D.Float((float) matrixWidth/5*2+2,
                (float) matrixHeigh/5*2+2, (float) matrixWidth/5-4, (float) matrixHeigh/5-4,20,20);
        g2.setColor(new Color(128,128,128));
        g2.draw(round2);

        g2.dispose();
        matrixImage.flush() ;
        return matrixImage ;
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length ; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] +nums[j] == target){
                    return new int []{i,j};
                }
            }
        }
        return null;
    }
    public static final  String STR ="%s在%s，发生%s的%s行为";

    public static void main(String[] args) {
        System.out.println(String.format(STR,"朱百年","滴滴","泄露数据","恶劣"));
    }
}
