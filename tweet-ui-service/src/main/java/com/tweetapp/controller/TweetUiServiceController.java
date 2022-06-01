package com.tweetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.common.ApiResponse;
import com.tweetapp.model.Comment;
import com.tweetapp.model.Tweet;
import com.tweetapp.service.TweetUiService;

@RestController
@RequestMapping("api/v1.0/tweets")
public class TweetUiServiceController {

	@Autowired
	TweetUiService tweetUiService;
	
	@GetMapping("/all")
	public  ResponseEntity<ApiResponse>  getAllTweet(@RequestHeader("Authorization") final String token){
		return tweetUiService.getAllTweet(token);
	}
	
	@GetMapping("/user/all")
	public ResponseEntity<?> getUsers(@RequestHeader("Authorization") final String token){
		return tweetUiService.getUsers(token);
	}
	
	@GetMapping("/user/search/{username}")
	public ResponseEntity<?> getUsers(@RequestHeader("Authorization") final String token, @PathVariable("username") String username){
		return tweetUiService.getUsers(token, username);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<?> getTweets(@RequestHeader("Authorization") final String token, @PathVariable("username") String username){
System.out.println("controller");
		return tweetUiService.getTweets(token, username);
	}
	
	@PostMapping("/{username}/add")
    public ResponseEntity<?> createNewTweet(@RequestHeader("Authorization") final String token, @RequestBody Tweet tweet) {
		return tweetUiService.createNewTweet(token, tweet);
	}
	
	@PutMapping("/{username}/update/{id}")
	public ResponseEntity<?> updateTweet(@RequestHeader("Authorization") final String token, @PathVariable("username") String username,@PathVariable("id") String id, @RequestBody String updateTweet){
		return tweetUiService.updateTweet(token, username, id, updateTweet);
	}
	
	@DeleteMapping("/{username}/delete/{id}")
	public ResponseEntity<?> deleteTweet(@RequestHeader("Authorization") final String token, @PathVariable("username") String username,@PathVariable("id") String id){
		return tweetUiService.deleteTweet(token, username, id);
	}
	
	@PutMapping("/{username}/like/{id}")
	public ResponseEntity<?> likeTweet(@RequestHeader("Authorization") final String token, @PathVariable("username") String username,@PathVariable("id") String id){
		return tweetUiService.likeTweet(token, username, id);
	}
	
	@PutMapping("/{username}/remove-like/{id}")
	public ResponseEntity<?> removeLikeTweet(@RequestHeader("Authorization") final String token, @PathVariable("username") String username,@PathVariable("id") String id){
		return tweetUiService.removeLikeTweet(token, username, id);
	}
	
	@PutMapping("/{username}/reply/{id}")
	public ResponseEntity<?> replyTweet(@RequestHeader("Authorization") final String token, @PathVariable("username") String username,@PathVariable("id") String id, @RequestBody Comment comment ){
		return tweetUiService.replyTweet(token, username, id, comment);
	}
}
