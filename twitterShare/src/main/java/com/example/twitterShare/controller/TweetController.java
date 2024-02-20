package com.example.twitterShare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.twitterShare.dto.TweetText;
import com.example.twitterShare.service.TwitterService;

@RestController
public class TweetController 
{
	@Autowired
	TwitterService twitterService;
		
	@PostMapping("/tweet")
	public String postTweet(TweetText tweetText)
	{
		try 
		{
			return twitterService.postTweet(tweetText);
		}
		catch(Exception e)
		{
			return "Failed to post tweet. Error: " + e.getMessage();
		}
		
	}
}
