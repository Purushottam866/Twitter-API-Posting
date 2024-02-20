package com.example.twitterShare.service;

import org.springframework.http.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.twitterShare.dto.TweetText;

@Service
public class TwitterService 
{
	 	@Value("${twitter.api.baseUrl}")
	    private String twitterBaseUrl;

	    @Value("${twitter.api.consumerKey}")
	    private String consumerKey;

	    @Value("${twitter.api.consumerSecret}")
	    private String consumerSecret;

	    @Value("${twitter.api.accessToken}")
	    private String accessToken;

	    @Value("${twitter.api.accessTokenSecret}")
	    private String accessTokenSecret;
	
	public String postTweet(TweetText tweetText) 
	{
		String apiUrl = twitterBaseUrl + "/statuses/update.json";
		String oauthHeader = generateOAuthHeader(apiUrl , "POST");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set(HttpHeaders.AUTHORIZATION, oauthHeader);
		
		String requestBody = "status =" + tweetText;
		
		HttpEntity<String> entity = new HttpEntity<>(requestBody,headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);
		
		 if (response.getStatusCode().is2xxSuccessful()) {
	            System.out.println( "Tweet posted successfully!");
	        } else {
	        	 System.out.println( "Failed to post tweet. Error: " + response.getBody());
	        }
		return "success";
	}
	
	 private String generateOAuthHeader(String apiUrl, String httpMethod)
	 {
		 String oauthHeader = "OAuth oauth_consumer_key=\"" + consumerKey + "\", "
	                + "oauth_nonce=\"generated_nonce\", "
	                + "oauth_signature=\"generated_signature\", "
	                + "oauth_signature_method=\"HMAC-SHA1\", "
	                + "oauth_timestamp=\"generated_timestamp\", "
	                + "oauth_token=\"" + accessToken + "\", "
	                + "oauth_version=\"1.0\"";

	        return oauthHeader;
	 }      
	
}
