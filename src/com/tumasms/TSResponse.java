package com.tumasms;

import org.json.JSONObject;
import org.json.XML;

public class TSResponse {
	private String statusCode = "", statusType = "", statusMeta = "", statusDescription = "", contentDescription = "";
	private int messagesAvailable = 0;
	
	
	public TSResponse() {
		
	}
	
	public static TSResponse getResponse(String reponse_xml){
		JSONObject respose = XML.toJSONObject(reponse_xml);
		/*
		 * Result : 
		 * {
			  "response": {
			    "content": {
			      "description": "2 messages queued. 2 SMS deducted, 4 SMS available.",
			      "messages": {
			        "message": 4
			      }
			    },
			    "status": {
			      "description": "REQUEST_SUCCESSFUL",
			      "code": "0000",
			      "type": "SUCCESS",
			      "meta": "SMS_QUEUE"
			    }
			  }
			}
		 * 
		 * */
		
		JSONObject status = respose.getJSONObject("response").getJSONObject("status");
		JSONObject content = respose.getJSONObject("response").getJSONObject("content");
		
		TSResponse tsResponse = new TSResponse();
		tsResponse.setStatusCode(status.getString("code"));
		tsResponse.setStatusType(status.getString("type"));
		tsResponse.setStatusMeta(status.getString("meta"));
		tsResponse.setStatusDescription(status.getString("description"));
		
		tsResponse.setContentDescription(content.getString("description"));
		tsResponse.setMessagesAvailable(content.getJSONObject("messages").getInt("message"));
		
		return tsResponse;
	}
	
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getStatusMeta() {
		return statusMeta;
	}

	public void setStatusMeta(String statusMeta) {
		this.statusMeta = statusMeta;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public String getContentDescription() {
		return contentDescription;
	}

	public void setContentDescription(String contentDescription) {
		this.contentDescription = contentDescription;
	}

	public int getMessagesAvailable() {
		return messagesAvailable;
	}

	public void setMessagesAvailable(int messagesAvailable) {
		this.messagesAvailable = messagesAvailable;
	}

	

}
