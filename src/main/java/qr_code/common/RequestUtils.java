package qr_code.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class RequestUtils {
    private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);

    /*
        execute a request with http/https
        ex:
        RequestUtils.executeRequest("http://abc/api/def", "POST", "token", ObjectMapperUtil.toJsonString(any_object));
     */
    public static String executeRequest(String targetURL, String method, String token, String body) {
        HttpURLConnection conn = null;
        logger.info("targetURL: {}, method: {}, token: {}, body: {}", targetURL, method, token, body);
        try {
            //Create connection
            URL url = new URL(targetURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setRequestProperty("Content-Type", "application/json;ty=3");
            conn.setRequestProperty("Accept", "application/json");
            if(StringUtils.isEmpty(token)) {
                conn.setRequestProperty("Authorization", "Bearer " + token);
            }
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            //Send request
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8))) {
                bw.write(body);
                bw.flush();
            }

            //Get Response
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            try (InputStream is = conn.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
            }

            return response.toString();
        } catch (Exception e) {
            logger.info("Error when execute POST method, {}", e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
