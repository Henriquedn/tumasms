/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tumasms;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


/**
 * 
 * @author Marshall
 */
public class Tumasms {
	protected String api_key;
	protected String api_signature;
	protected String messages;
	private final String base_url = "http://tumasms.co.ke/ts/api/";

	public Tumasms(String api_key, String api_signature) {
		this.api_key = api_key;
		this.api_signature = api_signature;
		this.messages = "";
	}

	public void queue_sms(String recipient, String message, String sender,
			String scheduled_date) {
		this.messages += "<sms><recipient>" + recipient + "</recipient><message>" + message + "</message><sender>"
				+ sender + "</sender><scheduled_date>" + scheduled_date + "</scheduled_date></sms>";
	}

	public TSResponse send_sms() {
		String result = "";
		if (this.api_key.equals("") || this.api_signature.equals("")) {
			result = "Invalid Credentials. Please set them to be able to send sms";
		} else if (this.messages.equals("")) {
			result = "No messages have been queued. Nothing to send";
		} else {
			String sms = "<request>" + this.messages + "</request>";
			try {
				HttpPost post = new HttpPost(this.base_url + "send_sms");

				List<NameValuePair> nameValuePairs = new ArrayList<>(1);
				nameValuePairs.add(new BasicNameValuePair("api_key", this.api_key));
				nameValuePairs.add(new BasicNameValuePair("api_signature", this.api_signature));
				nameValuePairs.add(new BasicNameValuePair("messages", sms));
				post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpClient httpclient = new DefaultHttpClient();
				HttpResponse response = httpclient.execute(post);
				HttpEntity entity = response.getEntity();
				InputStream is = entity.getContent();

				java.util.Scanner s = null;
				try {
					s = new java.util.Scanner(is).useDelimiter("\\A");
				} finally {
					result = s.hasNext() ? s.next() : "";
					s.close();
					is.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(Tumasms.class.getName()).log(Level.SEVERE,
						null, ex);
			}

		}
		return TSResponse.getResponse(result);
	}
	
	
	

	public TSResponse get_balance() {
		String result = "";
		if (this.api_key.equals("") || this.api_signature.equals("")) {
			result = "Invalid Credentials. Please set them to be able to send sms";
		} else {
			try {
				String url = this.base_url + "get_balance";
				HttpPost post = new HttpPost(url);
				List<NameValuePair> nameValuePairs = new ArrayList<>(1);
				nameValuePairs.add(new BasicNameValuePair("api_key", this.api_key));
				nameValuePairs.add(new BasicNameValuePair("api_signature", this.api_signature));
				post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpClient httpclient = new DefaultHttpClient();
				HttpResponse response = httpclient.execute(post);
				HttpEntity entity = response.getEntity();
				InputStream is = entity.getContent();
				java.util.Scanner s = null;
				try {
					s = new java.util.Scanner(is).useDelimiter("\\A");
				} finally {
					result = s.hasNext() ? s.next() : "";
					s.close();
					is.close();
				}
			} catch (IOException | IllegalStateException ex) {
				Logger.getLogger(Tumasms.class.getName()).log(Level.SEVERE, null, ex);
			}

		}
		return TSResponse.getResponse(result);
	}

}
