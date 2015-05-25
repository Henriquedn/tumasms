// Load API Class
package com.tumasms;

import com.tumasms.Tumasms;

public class Example {
    public static void main(String[] args) {
    	
    	
    	

        // Setup API credentials
    	String apiKey = ""; //Check under Manage Settings->Manage API in Tumasms 
        String apiSignature = ""; 

        // API Call to Send Message(s)
        // Request        
        Tumasms tumasms = new Tumasms(apiKey, apiSignature);  // Instantiate API library
        tumasms.queue_sms("+254725987342", "Testing Tuma Sms Maven two", "Sender_ID", ""); // Replace example with valid recipient, message, sender id and scheduled datetime if required in format ("YYYY-MM-DD HH:mm:ss")
        tumasms.queue_sms("+254725987342", "Message heheheh", "Sender_ID", ""); // Replace example with valid recipient, message, sender id and scheduled datetime if required in format ("YYYY-MM-DD HH:mm:ss")
       
        TSResponse response = tumasms.send_sms(); // Initiate API call to send messages
    	
    	System.out.println(response.getStatusCode());
    	System.out.println(response.getStatusType());
    	System.out.println(response.getStatusMeta());
    	System.out.println(response.getStatusDescription());
    	
    	System.out.println(response.getContentDescription());
    	System.out.println(response.getMessagesAvailable());
    	
    	
   
    	
        // API Call to Check for Available SMS
        // Request        
        Tumasms tumasmsbal = new Tumasms(apiKey, apiSignature);  // Instantiate API library
        TSResponse responseBal = tumasmsbal.get_balance(); // Initiate API call to send messages
        System.out.println(responseBal.getStatusCode());
    	System.out.println(responseBal.getStatusType());
    	System.out.println(responseBal.getStatusMeta());
    	System.out.println(responseBal.getStatusDescription());
    	System.out.println(responseBal.getContentDescription());
    	System.out.println(responseBal.getMessagesAvailable());
   

        
    }
}
