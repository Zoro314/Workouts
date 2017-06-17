package com.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class WebServerUtils {
	public static WebServerRequestBody parseRequest(InputStream input) throws IOException {
		LineNumberReader in = new LineNumberReader(new InputStreamReader(input));
		String inputLine = in.readLine();
		System.out.println("Input Line is : " + inputLine);
		String[] requestCols = inputLine.split("\\s");
		for (String req : requestCols)
			System.out.println(req);
		System.out.println("Size of array is : " + requestCols.length);
		String method = requestCols[0];
		String uri = requestCols[1];
		WebServerRequestBody webServerRequestBody = new WebServerRequestBody();
		webServerRequestBody.setMethod(method);
		if (method.equalsIgnoreCase("GET")) {
			String[] request = uri.split("\\?", 2);
			if (request.length == 2) {
				System.out.println("URI is broken into two parts!!!");
				webServerRequestBody.setRequestUri(request[0]);
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.putAll(parseParams(uri.split("\\?", 2)[1], false));
				System.out.println("#####" + paramMap.keySet() + "#####");
				webServerRequestBody.setRequestParams(paramMap);
			} else {
				webServerRequestBody.setRequestUri(uri);
			}
		} else if (method.equalsIgnoreCase("POST")) {
			webServerRequestBody.setRequestUri(uri);
			StringBuilder request = new StringBuilder();
			char[] bodyChars = new char[1024];
			while (true) {
				int len;
				if (!in.ready() || (len = in.read(bodyChars)) <= 0) {
					String[] lines = request.toString().split("\r\n");
					webServerRequestBody.setRequestBody(lines[lines.length - 1]);
					break;
				}
				request.append(bodyChars, 0, len);
			}
		}
		System.out.println("Returning webServer Request Body");
		return webServerRequestBody;
	}

	public static Map<String, String> parseParams(String params, boolean isPostRequest) {
		String[] paramPairs = params.trim().split("&");
		Map<String, String> paramMap = new HashMap<String, String>();
		for (String paramPair : paramPairs) {
			if (paramPair.contains("=")) {
				String[] keyValue = paramPair.split("=");
				String value = keyValue[1];
				if (isPostRequest) {
					value = value.replace("+", " ");
				}
				paramMap.put(keyValue[0], value);
			}
		}
		return paramMap;
	}

	public static Boolean isPostRequest(WebServerRequestBody webServerRequestBody) {
		if (webServerRequestBody.getMethod() != null && webServerRequestBody.getMethod().equalsIgnoreCase("POST")) {
			return true;
		}
		return false;
	}

	public static Boolean isGetRequest(WebServerRequestBody webServerRequestBody) {
		if (webServerRequestBody.getMethod() != null && webServerRequestBody.getMethod().equalsIgnoreCase("GET")) {
			return true;
		}
		return false;
	}

}
