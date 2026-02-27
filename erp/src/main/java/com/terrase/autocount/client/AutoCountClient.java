package com.terrase.autocount.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.terrase.autocount.base.AutoCountPostResponse;
import com.terrase.autocount.model.AutoCountCashSale;
import com.terrase.autocount.model.AutoCountCreditor;
import com.terrase.autocount.model.AutoCountDebtor;
import com.terrase.autocount.model.AutoCountPayableCreditNote;
import com.terrase.autocount.model.AutoCountPurchaseInvoice;
import com.terrase.autocount.model.AutoCountPurchaseOrder;
import com.terrase.autocount.model.AutoCountReceivableCreditNote;
import com.terrase.autocount.model.AutoCountReceivablePayment;

public class AutoCountClient {
	public static AutoCountPostResponse postDebtor(String clientUrl, String server, String database, String username,
			String password, List<AutoCountDebtor> debtors) throws Exception {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(clientUrl + "/api/autocount/debtor");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			setAutoCountHeader(connection, server, database, username, password);
			connection.setDoOutput(true);

			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(debtors);
			setJsonObject(connection, json);

			String jsonResponse = getJsonResponse(connection);
			AutoCountPostResponse response = objectMapper.readValue(jsonResponse, AutoCountPostResponse.class);
			return response;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static AutoCountPostResponse postCreditor(String clientUrl, String server, String database, String username,
			String password, List<AutoCountCreditor> creditors) throws Exception {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(clientUrl + "/api/autocount/creditor");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			setAutoCountHeader(connection, server, database, username, password);
			connection.setDoOutput(true);

			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(creditors);
			setJsonObject(connection, json);

			String jsonResponse = getJsonResponse(connection);
			AutoCountPostResponse response = objectMapper.readValue(jsonResponse, AutoCountPostResponse.class);
			return response;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static AutoCountPostResponse postCashSale(String clientUrl, String server, String database, String username,
			String password, List<AutoCountCashSale> cashSales) throws Exception {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(clientUrl + "/api/autocount/cashsale");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			setAutoCountHeader(connection, server, database, username, password);
			connection.setDoOutput(true);

			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(cashSales);
			setJsonObject(connection, json);

			String jsonResponse = getJsonResponse(connection);
			AutoCountPostResponse response = objectMapper.readValue(jsonResponse, AutoCountPostResponse.class);
			return response;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static AutoCountPostResponse deleteCashSale(String clientUrl, String server, String database,
			String username, String password, List<AutoCountCashSale> cashSales) throws Exception {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(clientUrl + "/api/autocount/cashsale/delete");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			setAutoCountHeader(connection, server, database, username, password);
			connection.setDoOutput(true);

			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(cashSales);
			setJsonObject(connection, json);

			String jsonResponse = getJsonResponse(connection);
			AutoCountPostResponse response = objectMapper.readValue(jsonResponse, AutoCountPostResponse.class);
			return response;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static AutoCountPostResponse postReceivablePayment(String clientUrl, String server, String database,
			String username, String password, List<AutoCountReceivablePayment> receivablePayments) throws Exception {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(clientUrl + "/api/autocount/receivablepayment");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			setAutoCountHeader(connection, server, database, username, password);
			connection.setDoOutput(true);

			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(receivablePayments);
			setJsonObject(connection, json);

			String jsonResponse = getJsonResponse(connection);
			AutoCountPostResponse response = objectMapper.readValue(jsonResponse, AutoCountPostResponse.class);
			return response;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static AutoCountPostResponse deleteReceivablePayment(String clientUrl, String server, String database,
			String username, String password, List<AutoCountReceivablePayment> receivablePayments) throws Exception {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(clientUrl + "/api/autocount/receivablepayment/delete");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			setAutoCountHeader(connection, server, database, username, password);
			connection.setDoOutput(true);

			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(receivablePayments);
			setJsonObject(connection, json);

			String jsonResponse = getJsonResponse(connection);
			AutoCountPostResponse response = objectMapper.readValue(jsonResponse, AutoCountPostResponse.class);
			return response;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static AutoCountPostResponse postReceivableCreditNote(String clientUrl, String server, String database,
			String username, String password, List<AutoCountReceivableCreditNote> receivableCreditNotes)
			throws Exception {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(clientUrl + "/api/autocount/receivablecreditnote");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			setAutoCountHeader(connection, server, database, username, password);
			connection.setDoOutput(true);

			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(receivableCreditNotes);
			setJsonObject(connection, json);

			String jsonResponse = getJsonResponse(connection);
			AutoCountPostResponse response = objectMapper.readValue(jsonResponse, AutoCountPostResponse.class);
			return response;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static AutoCountPostResponse deleteReceivableCreditNote(String clientUrl, String server, String database,
			String username, String password, List<AutoCountReceivableCreditNote> receivableCreditNotes)
			throws Exception {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(clientUrl + "/api/autocount/receivablecreditnote/delete");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			setAutoCountHeader(connection, server, database, username, password);
			connection.setDoOutput(true);

			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(receivableCreditNotes);
			setJsonObject(connection, json);

			String jsonResponse = getJsonResponse(connection);
			AutoCountPostResponse response = objectMapper.readValue(jsonResponse, AutoCountPostResponse.class);
			return response;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static AutoCountPostResponse postPurchaseOrder(String clientUrl, String server, String database,
			String username, String password, List<AutoCountPurchaseOrder> purchaseOrders) throws Exception {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(clientUrl + "/api/autocount/purchaseorder");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			setAutoCountHeader(connection, server, database, username, password);
			connection.setDoOutput(true);

			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(purchaseOrders);
			setJsonObject(connection, json);

			String jsonResponse = getJsonResponse(connection);
			AutoCountPostResponse response = objectMapper.readValue(jsonResponse, AutoCountPostResponse.class);
			return response;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static AutoCountPostResponse postPurchaseInvoice(String clientUrl, String server, String database,
			String username, String password, List<AutoCountPurchaseInvoice> purchaseInvoices) throws Exception {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(clientUrl + "/api/autocount/purchaseinvoice");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			setAutoCountHeader(connection, server, database, username, password);
			connection.setDoOutput(true);

			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(purchaseInvoices);
			setJsonObject(connection, json);

			String jsonResponse = getJsonResponse(connection);
			AutoCountPostResponse response = objectMapper.readValue(jsonResponse, AutoCountPostResponse.class);
			return response;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static AutoCountPostResponse deletePurchaseInvoice(String clientUrl, String server, String database,
			String username, String password, List<AutoCountPurchaseInvoice> purchaseInvoices) throws Exception {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(clientUrl + "/api/autocount/purchaseinvoice/delete");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			setAutoCountHeader(connection, server, database, username, password);
			connection.setDoOutput(true);

			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(purchaseInvoices);
			setJsonObject(connection, json);

			String jsonResponse = getJsonResponse(connection);
			AutoCountPostResponse response = objectMapper.readValue(jsonResponse, AutoCountPostResponse.class);
			return response;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static AutoCountPostResponse postPayableCreditNote(String clientUrl, String server, String database,
			String username, String password, List<AutoCountPayableCreditNote> payableCreditNotes) throws Exception {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(clientUrl + "/api/autocount/payablecreditnote");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			setAutoCountHeader(connection, server, database, username, password);
			connection.setDoOutput(true);

			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(payableCreditNotes);
			setJsonObject(connection, json);

			String jsonResponse = getJsonResponse(connection);
			AutoCountPostResponse response = objectMapper.readValue(jsonResponse, AutoCountPostResponse.class);
			return response;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	private static void setAutoCountHeader(URLConnection connection, String accountingServer, String accountingDatabase,
			String accountingUsername, String accountingPassword) {
		connection.setRequestProperty("Auto-Count-Server", accountingServer);
		connection.setRequestProperty("Auto-Count-Database", accountingDatabase);
		connection.setRequestProperty("Auto-Count-Username", accountingUsername);
		connection.setRequestProperty("Auto-Count-Password", accountingPassword);
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
