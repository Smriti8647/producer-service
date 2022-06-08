package com.tweetapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tweetapp.client.AuthenticationServiceClient;
import com.tweetapp.client.UpdateServiceClient;
import com.tweetapp.common.ApiResponse;
import com.tweetapp.model.Comment;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.UserResponse;
import com.tweetapp.model.ValidationResponse;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TweetUiServiceTest {

	@InjectMocks
	public TweetUiServiceImpl tweetService;

	@Mock
	UpdateServiceClient updateServiceClient;

	@Mock
	AuthenticationServiceClient authenticationServiceClient;

	@MockBean
	public Tweet tweet;

	@BeforeAll
	public static void init() {
		TweetUiServiceImpl service = new TweetUiServiceImpl();
	}

	@Test
	public void testGetAllTweet() throws Exception{
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setIsSuccess(true);
		List<Tweet> tweetList = new ArrayList<>();
		ResponseEntity<List<Tweet>> response = new ResponseEntity<>(tweetList, HttpStatus.OK);
		when(updateServiceClient.tweets()).thenReturn(response);
		when(authenticationServiceClient.validateAndReturnUser(anyString()))
				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
		ResponseEntity<ApiResponse> object = tweetService.getAllTweet(anyString());
		assertNotNull(object);
	}
	
	@Test
	public void testGetAllTweet_RuntimeException() {
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setIsSuccess(true);
		when(authenticationServiceClient.validateAndReturnUser(anyString()))
				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
		when(updateServiceClient.tweets()).thenThrow(new RuntimeException());
		ResponseEntity<ApiResponse> object = tweetService.getAllTweet(anyString());
		assertEquals(false,object.getBody().getSuccess());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,object.getStatusCode());
	}
	
	@Test
	public void testGetAllTweet_FeigeException() {
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setIsSuccess(true);
		when(authenticationServiceClient.validateAndReturnUser(anyString()))
				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
		when(updateServiceClient.tweets()).thenThrow(new SampleFeignClientException(-1,""));
		ResponseEntity<ApiResponse> object = tweetService.getAllTweet(anyString());
		assertEquals(false,object.getBody().getSuccess());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,object.getStatusCode());
	}
	
	@Test
	public void testGetAllTweet_FeigeException_WhileValidating() {;
		when(authenticationServiceClient.validateAndReturnUser(anyString()))
				.thenThrow(new SampleFeignClientException(-1,""));
		ResponseEntity<ApiResponse> object = tweetService.getAllTweet(anyString());
		assertEquals(false,object.getBody().getSuccess());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,object.getStatusCode());
	}

	@Test
	public void testGetAllUsers() {
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setIsSuccess(true);
		when(authenticationServiceClient.validateAndReturnUser(anyString()))
				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
		List<UserResponse> userList = new ArrayList<>();
		ResponseEntity<List<UserResponse>> response = new ResponseEntity<>(userList, HttpStatus.OK);
		when(updateServiceClient.allUsers()).thenReturn(response);
		ResponseEntity<ApiResponse> object = tweetService.getUsers(anyString());
		assertNotNull(object);
	}
	
	@Test
	public void testGetAllUsers_RuntimeException() {
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setIsSuccess(true);
		when(authenticationServiceClient.validateAndReturnUser(anyString()))
				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
		when(updateServiceClient.allUsers()).thenThrow(new RuntimeException());
		ResponseEntity<ApiResponse> object = tweetService.getUsers(anyString());
		assertEquals(false,object.getBody().getSuccess());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,object.getStatusCode());
	}
	
	@Test
	public void testGetAllUsers_FeigeException() {
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setIsSuccess(true);
		when(authenticationServiceClient.validateAndReturnUser(anyString()))
				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
		when(updateServiceClient.allUsers()).thenThrow(new SampleFeignClientException(-1,""));
		ResponseEntity<ApiResponse> object = tweetService.getUsers(anyString());
		assertEquals(false,object.getBody().getSuccess());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,object.getStatusCode());
	}

	@Test
	public void testGetUser() {
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setIsSuccess(true);
		when(authenticationServiceClient.validateAndReturnUser(anyString()))
				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
		List<UserResponse> userList = new ArrayList<>();
		ResponseEntity<List<UserResponse>> response = new ResponseEntity<>(userList, HttpStatus.OK);
		when(updateServiceClient.findUser("sam")).thenReturn(response);
		ResponseEntity<ApiResponse> object = tweetService.getUsers(anyString(), "sam");
		assertNotNull(object);
	}
	
//	@Test
//	public void testGetUser_RuntimeException() {
//		ValidationResponse validationResponse = new ValidationResponse();
//		validationResponse.setIsSuccess(true);
//		when(authenticationServiceClient.validateAndReturnUser(anyString()).getBody())
//				.thenReturn(validationResponse);
//		when(updateServiceClient.findUser(anyString())).thenThrow(new RuntimeException());
//		ResponseEntity<ApiResponse> object = tweetService.getUsers(anyString(), anyString());
//		assertEquals(false,object.getBody().getSuccess());
//		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,object.getStatusCode());
//	}
	
//	@Test
//	public void testGetUser_FeigeException() {
//		ValidationResponse validationResponse = new ValidationResponse();
//		validationResponse.setIsSuccess(true);
//		when(authenticationServiceClient.validateAndReturnUser(anyString()))
//				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
//		when(updateServiceClient.findUser(anyString())).thenThrow(new SampleFeignClientException(-1,""));
//		ResponseEntity<ApiResponse> object = tweetService.getUsers(anyString(),anyString());
//		assertEquals(false,object.getBody().getSuccess());
//		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,object.getStatusCode());
//	}

	@Test
	public void testGetTweets() {
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setIsSuccess(true);
		when(authenticationServiceClient.validateAndReturnUser(anyString()))
				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
		List<Tweet> tweetList = new ArrayList<>();
		ResponseEntity<List<Tweet>> response = new ResponseEntity<>(tweetList, HttpStatus.OK);
		when(updateServiceClient.tweets("sam")).thenReturn(response);
		ResponseEntity<ApiResponse> object = tweetService.getTweets(anyString(), "sam");
		assertNotNull(object);
	}
	
//	@Test
//	public void testGetTweets_RuntimeException() {
//		ValidationResponse validationResponse = new ValidationResponse();
//		validationResponse.setIsSuccess(true);
//		when(authenticationServiceClient.validateAndReturnUser(anyString()))
//				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
//		when(updateServiceClient.tweets(anyString())).thenThrow(new RuntimeException());
//		ResponseEntity<ApiResponse> object = tweetService.getTweets(anyString(), anyString());
//		assertEquals(false,object.getBody().getSuccess());
//		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,object.getStatusCode());
//	}

	@Test
	public void testCreateNewTweet() {
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setIsSuccess(true);
		when(authenticationServiceClient.validateAndReturnUser(anyString()))
				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
		Tweet tweet = new Tweet();
		ResponseEntity<String> response = new ResponseEntity<>("hey all", HttpStatus.OK);
		when(updateServiceClient.addTweet(tweet)).thenReturn(response);
		ResponseEntity<ApiResponse> object = tweetService.createNewTweet(anyString(), tweet);
		assertNotNull(object);
	}
	
	@Test
	public void testCreateNewTweet_RuntimeException() {
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setIsSuccess(true);
		when(authenticationServiceClient.validateAndReturnUser(anyString()))
				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
		Tweet tweet = new Tweet();
		when(updateServiceClient.addTweet(tweet)).thenThrow(new RuntimeException());		
		ResponseEntity<ApiResponse> object = tweetService.createNewTweet("jwtToken", tweet);
		assertEquals(false,object.getBody().getSuccess());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,object.getStatusCode());
	}
	
	@Test
	public void testCreateNewTweet_FeigeException() {
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setIsSuccess(true);
		when(authenticationServiceClient.validateAndReturnUser(anyString()))
				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
		Tweet tweet=new Tweet();
		when(updateServiceClient.addTweet(tweet)).thenThrow(new SampleFeignClientException(-1,""));
		ResponseEntity<ApiResponse> object = tweetService.createNewTweet("jwtToken", tweet);
		assertEquals(false,object.getBody().getSuccess());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,object.getStatusCode());
	}

	@Test
	public void testUpdatetweet() {
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setIsSuccess(true);
		when(authenticationServiceClient.validateAndReturnUser(anyString()))
				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
		ResponseEntity<String> response = new ResponseEntity<>("hey all", HttpStatus.OK);
		when(updateServiceClient.updateTweet("new Tweet", "tweetId")).thenReturn(response);
		ResponseEntity<ApiResponse> object = tweetService.updateTweet(anyString(), "sam", "new Tweet", "tweetId");
		assertNotNull(object);
	}
	
	@Test
	public void testUpdateTweet_RuntimeException() {
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setIsSuccess(true);
		when(authenticationServiceClient.validateAndReturnUser(anyString()))
				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
		when(updateServiceClient.updateTweet(anyString(), anyString())).thenThrow(new RuntimeException());
		ResponseEntity<ApiResponse> object = tweetService.updateTweet(anyString(), "sam", "new Tweet", "tweetId");
		assertEquals(false,object.getBody().getSuccess());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,object.getStatusCode());
	}
	
//	@Test
//	public void testUpdateTweet_FeigeException() {
//		ValidationResponse validationResponse = new ValidationResponse();
//		validationResponse.setIsSuccess(true);
//		when(authenticationServiceClient.validateAndReturnUser(anyString()))
//				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
//		when(updateServiceClient.updateTweet(anyString(), anyString())).thenThrow(new SampleFeignClientException(-1,""));
//		ResponseEntity<ApiResponse> object = tweetService.updateTweet(anyString(),anyString(),anyString(),anyString());
//		assertEquals(false,object.getBody().getSuccess());
//		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,object.getStatusCode());
//	}


	@Test
	public void testDeleteTweet() {
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setIsSuccess(true);
		when(authenticationServiceClient.validateAndReturnUser(anyString()))
				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
		ResponseEntity<String> response = new ResponseEntity<>("hey all", HttpStatus.OK);
		when(updateServiceClient.deleteTweet("new Tweet", "tweetId")).thenReturn(response);
		ResponseEntity<ApiResponse> object = tweetService.deleteTweet(anyString(), "new Tweet", "tweetId");
		assertNotNull(object);
	}
	
//	@Test
//	public void testDeleteTweet_RuntimeException() {
//		ValidationResponse validationResponse = new ValidationResponse();
//		validationResponse.setIsSuccess(true);
//		when(authenticationServiceClient.validateAndReturnUser(anyString()))
//				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
//		when(updateServiceClient.deleteTweet(anyString(), anyString())).thenThrow(new RuntimeException());
//		ResponseEntity<ApiResponse> object = tweetService.deleteTweet(anyString(), anyString(), anyString());
//		assertEquals(false,object.getBody().getSuccess());
//		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,object.getStatusCode());
//	}

	@Test
	public void testLikeTweet() {
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setIsSuccess(true);
		when(authenticationServiceClient.validateAndReturnUser(anyString()))
				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
		ResponseEntity<String> response = new ResponseEntity<>("hey all", HttpStatus.OK);
		when(updateServiceClient.likeTweet("sam", "tweetId")).thenReturn(response);
		ResponseEntity<ApiResponse> object = tweetService.likeTweet(anyString(), "sam", "tweetId");
		assertNotNull(object);
	}
	
//	@Test
//	public void testLikeTweet_RuntimeException() {
//		ValidationResponse validationResponse = new ValidationResponse();
//		validationResponse.setIsSuccess(true);
//		when(authenticationServiceClient.validateAndReturnUser(anyString()))
//				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
//		when(updateServiceClient.likeTweet(anyString(), anyString())).thenThrow(new RuntimeException());
//		ResponseEntity<ApiResponse> object = tweetService.likeTweet(anyString(), anyString(), anyString());
//		assertEquals(false,object.getBody().getSuccess());
//		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,object.getStatusCode());
//	}

	@Test
	public void testRemoveLikeTweet() {
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setIsSuccess(true);
		when(authenticationServiceClient.validateAndReturnUser(anyString()))
				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
		ResponseEntity<String> response = new ResponseEntity<>("hey all", HttpStatus.OK);
		when(updateServiceClient.dislikeTweet("sam", "tweetId")).thenReturn(response);
		ResponseEntity<ApiResponse> object = tweetService.removeLikeTweet(anyString(), "sam", "tweetId");
		assertNotNull(object);
	}
	
	@Test
	public void testRemoveLikeTweet_RuntimeException() {
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setIsSuccess(true);
		when(authenticationServiceClient.validateAndReturnUser(anyString()))
				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
		when(updateServiceClient.dislikeTweet("sam", "tweetId")).thenThrow(new RuntimeException());
		ResponseEntity<ApiResponse> object = tweetService.removeLikeTweet(anyString(), "sam", "tweetId");
		assertEquals(false,object.getBody().getSuccess());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,object.getStatusCode());
	}
	
//	@Test
//	public void testRemoveLike_FeigeException() {
//		ValidationResponse validationResponse = new ValidationResponse();
//		validationResponse.setIsSuccess(true);
//		when(authenticationServiceClient.validateAndReturnUser(anyString()))
//				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
//		when(updateServiceClient.dislikeTweet(anyString(), anyString())).thenThrow(new SampleFeignClientException(-1,""));
//		ResponseEntity<ApiResponse> object = tweetService.removeLikeTweet(anyString(),anyString(),anyString());
//		assertEquals(false,object.getBody().getSuccess());
//		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,object.getStatusCode());
//	}


	@Test
	public void testReplyTweet() {
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setIsSuccess(true);
		when(authenticationServiceClient.validateAndReturnUser(anyString()))
				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
		ResponseEntity<String> response = new ResponseEntity<>("hey all", HttpStatus.OK);
		Comment comment = new Comment();
		when(updateServiceClient.replyTweet(comment, "tweetId")).thenReturn(response);
		ResponseEntity<ApiResponse> object = tweetService.replyTweet(anyString(), "sam", "tweetId", comment);
		assertNotNull(object);
	}
	
	@Test
	public void testReplyTweet_RuntimeException() {
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setIsSuccess(true);
		when(authenticationServiceClient.validateAndReturnUser(anyString()))
				.thenReturn(new ResponseEntity<>(validationResponse, HttpStatus.OK));
		Comment comment = new Comment();
		when(updateServiceClient.replyTweet(comment, "sam")).thenThrow(new RuntimeException());
		
		ResponseEntity<ApiResponse> object = tweetService.replyTweet("jwtToken", "sam", "tweetId", comment);
		assertEquals(false,object.getBody().getSuccess());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,object.getStatusCode());
	}

}
