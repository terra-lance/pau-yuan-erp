package com.terrase.frame.integration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OutpostClient {
	public static String printText(String clientUrl, String printerName, String printerUsername, String password,
			String message) throws Exception {
		return printText(clientUrl, printerName, printerUsername, password, message, "Arial", 10);
	}
	
	public static String printText(String clientUrl, String printerName, String printerUsername, String password,
			String message, String font, Integer fontSize) throws Exception {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(clientUrl + "/api/printer/printtext");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Printer-Name", printerName);
			connection.setRequestProperty("Printer-Username", printerUsername);
			connection.setRequestProperty("Printer-Password", password);
			connection.setRequestProperty("Font-Name", font);
			connection.setRequestProperty("Font-Size", fontSize.toString());
			connection.setDoOutput(true);

			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(message);
			setJsonObject(connection, json);

			String jsonResponse = getJsonResponse(connection);
			String response = objectMapper.readValue(jsonResponse, String.class);
			return response;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	private static void setJsonObject(URLConnection connection, String json) throws Exception {
		try (OutputStream outputStream = connection.getOutputStream()) {
			byte[] input = json.getBytes("utf-8");
			outputStream.write(input, 0, input.length);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private static String getJsonResponse(URLConnection connection) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}

			return response.toString();
		} catch (Exception ex) {
			throw ex;
		}
	}
}
