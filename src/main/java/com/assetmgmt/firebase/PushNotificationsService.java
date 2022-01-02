package com.assetmgmt.firebase;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.assetmgmt.dto.ErrorLogDto;
import com.assetmgmt.enumeration.LogOperation;
import com.assetmgmt.filter.HeaderRequestInterceptor;
import com.assetmgmt.util.LogWrapper;

import net.minidev.json.JSONObject;

@Service
public class PushNotificationsService {

	public String sendNotification(String title,String messageText,String deviceToken) {
		JSONObject body = new JSONObject();
		body.put("to", deviceToken);
		body.put("priority", "high");

		JSONObject notification = new JSONObject();
		notification.put("title", title);
		notification.put("body", messageText);

		
		

		body.put("notification", notification);
	
		
		JSONObject data = new JSONObject();		
		data.put("volume", "3.21.15");
		data.put("contents", "http://www.test12345.com");
		
		JSONObject android = new JSONObject();
		android.put("priority", "high");
		
		JSONObject headerApns = new JSONObject();
		headerApns.put("apns-priority", "10");
		
		JSONObject headerUrgency = new JSONObject();
		headerUrgency.put("Urgency", "high");
		
		JSONObject apns = new JSONObject();
		apns.put("header",headerApns);
		
		JSONObject webpush = new JSONObject();	
		webpush.put("header",headerUrgency);
		
		JSONObject message = new JSONObject();		
		message.put("topic","subscriber-updates");
		message.put("notification",notification);
		message.put("data",data);
		message.put("android",android);
		message.put("apns",apns);
		message.put("webpush",webpush);		
		body.put("message",message);
		
	

		HttpEntity<String> request = new HttpEntity<>(body.toString());

		CompletableFuture<FirebaseResponse> pushNotification = this.send(request);
		CompletableFuture.allOf(pushNotification).join();

		try {
			FirebaseResponse firebaseResponse = pushNotification.get();
			

			return firebaseResponse.toString();
		} catch (InterruptedException | ExecutionException e) {
			LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.CREATE).errorMessage(e.getMessage())
						.exception(e).build());
		}
		return body.toString();
	}

	@Async

	private CompletableFuture<FirebaseResponse> send(HttpEntity<String> entity) {

		RestTemplate restTemplate = new RestTemplate();

		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FirebaseUtil.FIREBASE_SERVER_KEY));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);


		FirebaseResponse firebaseResponse = restTemplate.postForObject(FirebaseUtil.FIREBASE_API_URL, entity,
				FirebaseResponse.class);

		return CompletableFuture.completedFuture(firebaseResponse);
	}
}
