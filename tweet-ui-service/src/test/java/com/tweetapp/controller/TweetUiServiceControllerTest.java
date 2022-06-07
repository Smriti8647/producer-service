package com.tweetapp.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tweetapp.common.ApiResponse;
import com.tweetapp.model.Comment;
import com.tweetapp.model.Tweet;
import com.tweetapp.service.TweetUiService;

@SpringBootTest
public class TweetUiServiceControllerTest {

	@InjectMocks
	TweetUiServiceController controller;

	@Mock
	TweetUiService tweetUiService;

	@BeforeAll
	public static void init() {
		TweetUiServiceController controller = new TweetUiServiceController();
	}

	@Test
	public void testGetAllTweet() {
		ApiResponse response = new ApiResponse();
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		when(tweetUiService.getAllTweet(anyString())).thenReturn(responseEntity);
		ResponseEntity<ApiResponse> object = controller.getAllTweet(anyString());
		assertNotNull(object);
	}

	@Test
	public void testGetUsers() {
		ApiResponse response = new ApiResponse();
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		when(tweetUiService.getUsers(anyString())).thenReturn(responseEntity);
		ResponseEntity<ApiResponse> object = controller.getUsers(anyString());
		assertNotNull(object);
	}

	@Test
	public void testGetTweets() {
		ApiResponse response = new ApiResponse();
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		when(tweetUiService.getTweets(anyString(), anyString())).thenReturn(responseEntity);
		ResponseEntity<ApiResponse> object = controller.getTweets(anyString(), anyString());
		assertNotNull(object);
	}

	@Test
	public void testCreateNewTweet() {
		ApiResponse response = new ApiResponse();
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		Tweet tweet = new Tweet();
		when(tweetUiService.createNewTweet("jwtToken", tweet)).thenReturn(responseEntity);
		ResponseEntity<ApiResponse> object = controller.createNewTweet("jwtToken", tweet);
		assertNotNull(object);
	}

	@Test
	public void testUpdateTweet() {
		ApiResponse response = new ApiResponse();
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		when(tweetUiService.updateTweet("jwtToken", "sam", "tweetId", "new Tweet")).thenReturn(responseEntity);
		ResponseEntity<ApiResponse> object = controller.updateTweet("jwtToken", "sam", "tweetId", "new Tweet");
		assertNotNull(object);
	}

	@Test
	public void testLikeTweet() {
		ApiResponse response = new ApiResponse();
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		when(tweetUiService.likeTweet("jwtToken", "sam", "tweetId")).thenReturn(responseEntity);
		ResponseEntity<ApiResponse> object = controller.likeTweet("jwtToken", "sam", "tweetId");
		assertNotNull(object);
	}

	@Test
	public void testRemoveLikeTweet() {
		ApiResponse response = new ApiResponse();
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		when(tweetUiService.removeLikeTweet("jwtToken", "sam", "tweetId")).thenReturn(responseEntity);
		ResponseEntity<ApiResponse> object = controller.removeLikeTweet("jwtToken", "sam", "tweetId");
		assertNotNull(object);
	}

	@Test
	public void testDeleteTweet() {
		ApiResponse response = new ApiResponse();
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		when(tweetUiService.deleteTweet("jwtToken", "sam", "tweetId")).thenReturn(responseEntity);
		ResponseEntity<ApiResponse> object = controller.deleteTweet("jwtToken", "sam", "tweetId");
		assertNotNull(object);
	}

	@Test
	public void testReplyTweet() {
		ApiResponse response = new ApiResponse();
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		Comment comment = new Comment();
		when(tweetUiService.replyTweet("jwtToken", "sam", "tweetId", comment)).thenReturn(responseEntity);
		ResponseEntity<ApiResponse> object = controller.replyTweet("jwtToken", "sam", "tweetId", comment);
		assertNotNull(object);
	}
}
