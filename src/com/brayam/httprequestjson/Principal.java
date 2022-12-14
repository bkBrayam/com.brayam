package com.brayam.httprequestjson;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.*;

public class Principal {

	public static void main(String[] args) throws Exception {
		
		URL url = new URL("https://jsonplaceholder.typicode.com/users");
		HttpURLConnection cx = (HttpURLConnection) url.openConnection();
		cx.setRequestMethod("GET");
		
		InputStream strm= cx.getInputStream();
		byte[] arrStream= readAllBytes(strm);
		
		String cntJson="";
		
		for(byte tmp: arrStream)
			cntJson+=(char)tmp;	
		
		JSONArray json= new JSONArray(cntJson);
		
		for(Object obj: json)
			System.out.println(new JSONObject(((JSONObject)obj).get("company").toString()).get("name"));
		
	}
	
	 public static byte[] readAllBytes(InputStream inputStream) throws IOException {
	     final int bufLen = 4 * 0x400; // 4KB
	     byte[] buf = new byte[bufLen];
	     int readLen;
	     IOException exception = null;

	     try {
	         try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
	             while ((readLen = inputStream.read(buf, 0, bufLen)) != -1)
	                 outputStream.write(buf, 0, readLen);

	             return outputStream.toByteArray();
	         }
	     } catch (IOException e) {
	         exception = e;
	         throw e;
	     } finally {
	         if (exception == null) inputStream.close();
	         else try {
	             inputStream.close();
	         } catch (IOException e) {
	             exception.addSuppressed(e);
	         }
	     }
	 }

}
