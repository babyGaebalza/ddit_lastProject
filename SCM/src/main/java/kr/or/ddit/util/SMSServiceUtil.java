package kr.or.ddit.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class SMSServiceUtil {
	
	private final char[] characterTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
            'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

	public String makeCode(int certCharLength) {
		Random random = new Random(System.currentTimeMillis());
		int tablelength = characterTable.length;
		StringBuffer buf = new StringBuffer();
		
		for(int i = 0; i < certCharLength; i++) {
		buf.append(characterTable[random.nextInt(tablelength)]);
		}
		
		return buf.toString();
	}
	
	public void sendMessage(String to, String content) {
		String hostnameURL = "https://sens.apigw.ntruss.com" ;
		String requestURL =  "/sms/v2/services/";
		String requestURLType = "/messages" ;
		String method = "POST"; 
		String accessKey = "3dmhzPTPHMpdH8lIY1Qa"; 
		String secretKey= "Ak4p0JFE2rR0eTl0yovZfiXN2X9gVYyXgK7xyTlk"; 
		String serviceId="ncp:sms:kr:274933343187:smartcampussystem"; 
	
		requestURL += serviceId +  requestURLType ; 
		String apiURL =  hostnameURL + requestURL ; 
		
		String timeStamp = Long.toString(System.currentTimeMillis()); 
		
	    JSONObject bodyJSON = new JSONObject(); 
	    JSONObject toJSON = new JSONObject(); 
	    JSONArray toArr = new JSONArray(); 
	    
	    //toJSON.put("subject", "");  //LMS, MMS 
	    toJSON.put("content", content);   //기본메시지내용
	    toJSON.put("to", to.replaceAll("-", ""));
	    toArr.add(toJSON);
	    
	    bodyJSON.put("type" , "SMS"); 
	    bodyJSON.put( "contentType", "COMM"); 
	    bodyJSON.put("countryCode" , "82"); 
	    bodyJSON.put("from" , "01088792138"); //사전에 등록해야 함. 이번호로만 가능함. 
	    //bodyJSON.put("subject" , "");  //LMS, MMS 
	    bodyJSON.put("content" , "전송테스트"); //내용 80byte까지 개별메시지내용
	    
	    bodyJSON.put("messages" , toArr); 

	    String body = bodyJSON.toJSONString(); 
	    
	    System.out.println(body);


	    try {
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("x-ncp-apigw-timestamp", timeStamp);
			conn.setRequestProperty("x-ncp-iam-access-key", accessKey);
			conn.setRequestProperty("x-ncp-apigw-signature-v2", makeSignature(requestURL, timeStamp, method, accessKey, secretKey));
			conn.setRequestMethod(method);
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream()); 
			//이부분이 쓰는 부분인듯...
			wr.write(body.getBytes());
			wr.flush(); 
			wr.close(); 
			
			int responseCode = conn.getResponseCode(); 
			BufferedReader br; 
			System.out.println("responseCode"  + " "+  responseCode);
			if(responseCode == 202) {//정상호출
				br = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			}else {			
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream())); 
				System.out.println("에러확인");
			}
			
			String inputLine; 
			StringBuffer response = new StringBuffer(); 
			while((inputLine = br.readLine())!=null) {
				response.append(inputLine);
			}
			br.close(); 
			System.out.println(response.toString());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
	}
//규칙만드는 부분인듯 
	public String makeSignature(String url, String timeStamp, String method, String accessKey, String secretKey) {

		String space = " "; 
		String newline = "\n";
		
		String message = new StringBuilder()
						.append(method)
						.append(space)
						.append(url)
						.append(newline)
						.append(timeStamp)
						.append(newline)
						.append(accessKey)
						.toString(); 
		
		
		SecretKeySpec signingKey; 
		String encodeBase64String; 
		
		try {
			signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
			encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException e) {
			encodeBase64String =e.toString();	
		} 
		
		
		return encodeBase64String;
	}
		
	
}
