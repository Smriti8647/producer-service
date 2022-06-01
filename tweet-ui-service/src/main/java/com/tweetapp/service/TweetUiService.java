package com.tweetapp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.tweetapp.common.ApiResponse;
import com.tweetapp.model.Comment;
import com.tweetapp.model.Tweet;

public interface TweetUiService {

	public ResponseEntity<ApiResponse> getAllTweet(final String token);

	public ResponseEntity<ApiResponse> getUsers(@RequestHeader("Authorization") final String token);
	
	public ResponseEntity<ApiResponse> getUsers(@RequestHeader("Authorization") final String token, @PathVariable("username") String username);
	
	public ResponseEntity<ApiResponse> getTweets(@RequestHeader("Authorization") final String token, @PathVariable("username") String username);
	
    public ResponseEntity<ApiResponse> createNewTweet(@RequestHeader("Authorization") final String token, @RequestBody Tweet tweet);
	
	public ResponseEntity<ApiResponse> updateTweet(@RequestHeader("Authorization") final String token, @PathVariable("username") String username,@PathVariable("id") String id, @RequestBody String updateTweet);
	
	public ResponseEntity<ApiResponse> deleteTweet(@RequestHeader("Authorization") final String token, @PathVariable("username") String username,@PathVariable("id") String id);
	
	public ResponseEntity<ApiResponse> likeTweet(@RequestHeader("Authorization") final String token, @PathVariable("username") String username,@PathVariable("id") String id);
	
	public ResponseEntity<ApiResponse> removeLikeTweet(@RequestHeader("Authorization") final String token, @PathVariable("username") String username,@PathVariable("id") String id);
	
	public ResponseEntity<ApiResponse> replyTweet(@RequestHeader("Authorization") final String token, @PathVariable("username") String username,@PathVariable("id") String id, @RequestBody Comment comment );
		
}
