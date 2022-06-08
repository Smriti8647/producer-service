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
import com.tweetapp.model.Tag;
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
	 void testGetAllTweet() {
		ApiResponse response = new ApiResponse();
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		when(tweetUiService.getAllTweet(anyString())).thenReturn(responseEntity);
		ResponseEntity<ApiResponse> object = controller.getAllTweet(anyString());
		assertNotNull(object);
	}

	@Test
	 void testGetUsers() {
		ApiResponse response = new ApiResponse();
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		when(tweetUiService.getUsers(anyString())).thenReturn(responseEntity);
		ResponseEntity<ApiResponse> object = controller.getUsers(anyString());
		assertNotNull(object);
	}
	
	@Test
	 void testGetUsers_bySearch() {
		ApiResponse response = new ApiResponse();
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		when(tweetUiService.getUsers(anyString(),anyString())).thenReturn(responseEntity);
		ResponseEntity<ApiResponse> object = controller.getUsers(anyString(),anyString());
		assertNotNull(object);
	}

	@Test
	 void testGetTweets() {
		ApiResponse response = new ApiResponse();
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		when(tweetUiService.getTweets(anyString(), anyString())).thenReturn(responseEntity);
		ResponseEntity<ApiResponse> object = controller.getTweets(anyString(), anyString());
		assertNotNull(object);
	}

	@Test
	 void testCreateNewTweet() {
		ApiResponse response = new ApiResponse();
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		Tweet tweet = new Tweet();
		when(tweetUiService.createNewTweet("jwtToken", tweet)).thenReturn(responseEntity);
		ResponseEntity<ApiResponse> object = controller.createNewTweet("jwtToken", tweet);
		assertNotNull(object);
	}

	@Test
	 void testUpdateTweet() {
		ApiResponse response = new ApiResponse();
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		when(tweetUiService.updateTweet("jwtToken", "sam", "tweetId", "new Tweet")).thenReturn(responseEntity);
		ResponseEntity<ApiResponse> object = controller.updateTweet("jwtToken", "sam", "tweetId", "new Tweet");
		assertNotNull(object);
	}

	@Test
	 void testLikeTweet() {
		ApiResponse response = new ApiResponse();
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		when(tweetUiService.likeTweet("jwtToken", "sam", "tweetId")).thenReturn(responseEntity);
		ResponseEntity<ApiResponse> object = controller.likeTweet("jwtToken", "sam", "tweetId");
		assertNotNull(object);
	}

	@Test
	 void testRemoveLikeTweet() {
		ApiResponse response = new ApiResponse();
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		when(tweetUiService.removeLikeTweet("jwtToken", "sam", "tweetId")).thenReturn(responseEntity);
		ResponseEntity<ApiResponse> object = controller.removeLikeTweet("jwtToken", "sam", "tweetId");
		assertNotNull(object);
	}

	@Test
	 void testDeleteTweet() {
		ApiResponse response = new ApiResponse();
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		when(tweetUiService.deleteTweet("jwtToken", "sam", "tweetId")).thenReturn(responseEntity);
		ResponseEntity<ApiResponse> object = controller.deleteTweet("jwtToken", "sam", "tweetId");
		assertNotNull(object);
	}

	@Test
	 void testReplyTweet() {
		ApiResponse response = new ApiResponse();
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		Comment comment = new Comment();
		when(tweetUiService.replyTweet("jwtToken", "sam", "tweetId", comment)).thenReturn(responseEntity);
		ResponseEntity<ApiResponse> object = controller.replyTweet("jwtToken", "sam", "tweetId", comment);
		assertNotNull(object);
	}
	
	@Test
	 void testsetTag() {
		ApiResponse response = new ApiResponse();
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		Tag tag=new Tag();
		when(tweetUiService.setTag("jwtToken",tag)).thenReturn(responseEntity);
		ResponseEntity<ApiResponse> object = controller.setTag("jwtToken",tag);
		assertNotNull(object);
	}
}
