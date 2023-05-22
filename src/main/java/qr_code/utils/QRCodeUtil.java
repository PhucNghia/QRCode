package qr_code.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class QRCodeUtil {

    public QRCodeUtil() {
    }
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads/images";

    public String generateQRCodeURL(String email, int width, int height) throws IOException, WriterException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix bitMatrix = new MultiFormatWriter().encode(email, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage qrImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Fill QR code image with white color
        Graphics2D graphics = qrImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        // Set QR code content color to black
        graphics.setColor(Color.BLACK);

        // Draw QR code content
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (bitMatrix.get(x, y)) {
                    graphics.fillRect(x, y, 1, 1);
                }
            }
        }

        // Save QR code image to the public image directory
        String fileName = removeEndPrefixEmail(email) + ".png";
        File imageFile = new File(UPLOAD_DIRECTORY + File.separator + fileName);
        ImageIO.write(qrImage, "png", imageFile);

        // Generate the public URL of the image

        return "localhost:70" + imageFile.getAbsolutePath();
    }
    public static  String removeEndPrefixEmail(String email){
        int atIndex = email.lastIndexOf("@");
        if (atIndex != -1) {
            return email.substring(0, atIndex);
        }
        return "";
    }
}