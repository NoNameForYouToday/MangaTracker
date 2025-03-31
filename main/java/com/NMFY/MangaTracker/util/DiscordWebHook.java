package com.NMFY.MangaTracker.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class DiscordWebHook {
    public static String webhookUrl;


    public static void sendRequest(String content) {
        try {

            URL url = new URL(webhookUrl);
            HttpURLConnection hurl = (HttpURLConnection)url.openConnection();
            hurl.setRequestMethod("POST");
            hurl.addRequestProperty("Content-Type", "application/json");
            hurl.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_");
            hurl.setDoOutput(true);
            String title = "IP";
            String message = "{\"content\":\"" +content + "\"}";
            OutputStream os = hurl.getOutputStream();
            Throwable var6 = null;

            try {
                byte[] input = message.getBytes("utf-8");
                os.write(message.getBytes());
                os.flush();
                os.close();
                hurl.getInputStream().close();
            } catch (Throwable var19) {
                var6 = var19;
                throw var19;
            } finally {
                if (os != null) {
                    if (var6 != null) {
                        try {
                            os.close();
                        } catch (Throwable var18) {
                            var6.addSuppressed(var18);
                        }
                    } else {
                        os.close();
                    }
                }

            }
        } catch (MalformedURLException var21) {
        } catch (ProtocolException var22) {
        } catch (UnsupportedEncodingException var23) {
        } catch (IOException var24) {
        }

    }
}
