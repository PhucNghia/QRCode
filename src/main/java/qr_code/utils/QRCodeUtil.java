package qr_code.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class QRCodeUtil {

    public QRCodeUtil() {
    }

    private final Logger logger = LoggerFactory.getLogger(QRCodeUtil.class);
    private static final int QRCODE_SIZE = 250;
    private static final int BACKGROUND_SIZE = 500;
    private static final int BACKGROUND_COLOR = 0xFFFFFFFF; // White color
    private static final int QR_CODE_COLOR = 0xFFFFFF; // Black color

    private static final int cornerSize = 10;

    public static BufferedImage getBufferedImageFromResource(String resourcePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(resourcePath);
        InputStream inputStream = resource.getInputStream();
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        inputStream.close();
        return bufferedImage;
    }

    public byte[] generateQRCodeWithBackground(String qrCodeData) {
        try {
            // Load the background image
            BufferedImage backgroundImage = getBufferedImageFromResource("test.png");

            // Resize the background image
            Image scaledBackgroundImage = backgroundImage.getScaledInstance(BACKGROUND_SIZE, BACKGROUND_SIZE, Image.SCALE_SMOOTH);

            // Create a new buffered image with the same size as the background image
            BufferedImage combinedImage = new BufferedImage(BACKGROUND_SIZE, BACKGROUND_SIZE, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = combinedImage.createGraphics();

            // Draw the scaled background image onto the new buffered image
            graphics.drawImage(scaledBackgroundImage, 0, 0, null);

            // Generate the QR code
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.MARGIN, 2);
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);

            // Calculate the position to place the QR code in the center of the background image
            int xPos = (BACKGROUND_SIZE - QRCODE_SIZE) / 2;
            int yPos = (BACKGROUND_SIZE - QRCODE_SIZE) / 2;

            // Set the QR code color
            int qrCodeColor = Color.BLACK.getRGB();

            // Draw the QR code onto the new buffered image
            for (int x = 0; x < QRCODE_SIZE; x++) {
                for (int y = 0; y < QRCODE_SIZE; y++) {
                    combinedImage.setRGB(x + xPos, y + yPos, bitMatrix.get(x, y) ? qrCodeColor : BACKGROUND_COLOR);
                }
            }

            // Convert the combined image to a byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(combinedImage, "png", baos);

//            graphics.drawRect(xPos + 10,xPos + 10,xPos + 10,xPos + 10);
            baos.flush();
            byte[] imageBytes = baos.toByteArray();
            baos.close();

            System.out.println("QR code with background generated successfully!");

            return imageBytes;
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            System.err.println("Failed to generate QR code with background.");
            return null;
        }
    }


}