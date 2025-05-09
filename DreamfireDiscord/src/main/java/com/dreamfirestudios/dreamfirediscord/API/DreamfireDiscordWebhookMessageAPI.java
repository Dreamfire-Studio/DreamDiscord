package com.dreamfirestudios.dreamfirediscord.API;

import org.bukkit.Bukkit;

public class DreamfireDiscordWebhookMessageAPI {
    public static int sendWebhookMessage(String webhookUrl, String payload) {
        Bukkit.getConsoleSender().sendMessage("PAYLOAD: " + webhookUrl + ":" + payload);
        try {
            java.net.URL url = new java.net.URL(webhookUrl);
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (var outputStream = connection.getOutputStream()) {
                byte[] input = payload.getBytes("utf-8");
                outputStream.write(input, 0, input.length);
            }

            return connection.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
