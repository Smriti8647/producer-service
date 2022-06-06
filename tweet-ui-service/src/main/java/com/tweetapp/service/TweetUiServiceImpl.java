package com.tweetapp.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.tweetapp.client.AuthenticationServiceClient;
import com.tweetapp.client.UpdateServiceClient;
import com.tweetapp.common.ApiResponse;
import com.tweetapp.model.Comment;
import com.tweetapp.model.Tag;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.ValidationResponse;

import feign.FeignException;

@Service
public class TweetUiServiceImpl implements TweetUiService {

	@Autowired
	UpdateServiceClient updateServiceClient;

	@Autowired
	AuthenticationServiceClient authenticationServiceClient;
	
	@Autowired
	private KafkaTemplate<String, Tag> kafkaTemplate;
	
	@Value("kafka-topic")
	private String TOPIC;

	public ResponseEntity<ApiResponse> getAllTweet(final String token) {
		try {
			ValidationResponse validationResponse = jwtTokenValidation(token);
			if (validationResponse.getIsSuccess()) {
				ResponseEntity<?> response = updateServiceClient.tweets();
				return new ResponseEntity<>(new ApiResponse(true, response.getBody()), response.getStatusCode());
			} else {
				return createUnauthorizedResponse(validationResponse);
			}
		} catch (FeignException e) {
			return createFeignExceptionResponse(e);
		} catch (RuntimeException e) {
			return createRuntimeExceptionResponse(e);
		}
	}

	public ResponseEntity<ApiResponse> getUsers(final String token) {
		try {
			ValidationResponse validationResponse = jwtTokenValidation(token);
			if (validationResponse.getIsSuccess()) {
				ResponseEntity<?> response = updateServiceClient.allUsers();
				return new ResponseEntity<>(new ApiResponse(true, response.getBody()), response.getStatusCode());
			} else {
				return createUnauthorizedResponse(validationResponse);
			}
		} catch (FeignException e) {
			return createFeignExceptionResponse(e);
		} catch (RuntimeException e) {
			return createRuntimeExceptionResponse(e);
		}
	}

	public ResponseEntity<ApiResponse> getUsers(final String token, String username) {
		try {
			ValidationResponse validationResponse = jwtTokenValidation(token);
			if (validationResponse.getIsSuccess()) {
				ResponseEntity<?> response = updateServiceClient.findUser(username);
				return new ResponseEntity<>(new ApiResponse(true, response.getBody()), response.getStatusCode());
			} else {
				return createUnauthorizedResponse(validationResponse);
			}
		} catch (FeignException e) {
			return createFeignExceptionResponse(e);
		} catch (RuntimeException e) {
			return createRuntimeExceptionResponse(e);
		}
	}

	public ResponseEntity<ApiResponse> getTweets(final String token, String username) {
		try {
			ValidationResponse validationResponse = jwtTokenValidation(token);
			if (validationResponse.getIsSuccess()) {
				ResponseEntity<?> response = updateServiceClient.tweets(username);
				return new ResponseEntity<>(new ApiResponse(true, response.getBody()), response.getStatusCode());
			} else {
				return createUnauthorizedResponse(validationResponse);
			}
		} catch (FeignException e) {
			return createFeignExceptionResponse(e);
		} catch (RuntimeException e) {
			return createRuntimeExceptionResponse(e);
		}
	}

	public ResponseEntity<ApiResponse> createNewTweet(final String token, Tweet tweet) {
		try {
			ValidationResponse validationResponse = jwtTokenValidation(token);
			if (validationResponse.getIsSuccess()) {
				tweet.setLoginId(validationResponse.getUserId());
				tweet.setTime(LocalDateTime.now());
				ResponseEntity<?> response = updateServiceClient.addTweet(tweet);
				return new ResponseEntity<>(new ApiResponse(true, response.getBody()), response.getStatusCode());
			} else {
				return createUnauthorizedResponse(validationResponse);
			}
		} catch (FeignException e) {
			return createFeignExceptionResponse(e);
		} catch (RuntimeException e) {
			return createRuntimeExceptionResponse(e);
		}
	}

	public ResponseEntity<ApiResponse> updateTweet(final String token, String username, String id, String updateTweet) {
		try {
			ValidationResponse validationResponse = jwtTokenValidation(token);
			if (validationResponse.getIsSuccess()) {
				ResponseEntity<?> response = updateServiceClient.updateTweet(updateTweet, id);
				return new ResponseEntity<>(new ApiResponse(true, response.getBody()), response.getStatusCode());
			} else {
				return createUnauthorizedResponse(validationResponse);
			}
		} catch (FeignException e) {
			return createFeignExceptionResponse(e);
		} catch (RuntimeException e) {
			return createRuntimeExceptionResponse(e);
		}
	}

	public ResponseEntity<ApiResponse> deleteTweet(final String token, String username, String id) {
		try {
			ValidationResponse validationResponse = jwtTokenValidation(token);
			if (validationResponse.getIsSuccess()) {
				ResponseEntity<?> response = updateServiceClient.deleteTweet(username, id);
				return new ResponseEntity<>(new ApiResponse(true, response.getBody()), response.getStatusCode());
			} else {
				return createUnauthorizedResponse(validationResponse);
			}
		} catch (FeignException e) {
			return createFeignExceptionResponse(e);
		} catch (RuntimeException e) {
			return createRuntimeExceptionResponse(e);
		}
	}

	public ResponseEntity<ApiResponse> likeTweet(final String token, String username, String id) {
		try {
			ValidationResponse validationResponse = jwtTokenValidation(token);
			if (validationResponse.getIsSuccess()) {
				ResponseEntity<?> response = updateServiceClient.likeTweet(username, id);
				return new ResponseEntity<>(new ApiResponse(true, response.getBody()), response.getStatusCode());
			} else {
				return createUnauthorizedResponse(validationResponse);
			}
		} catch (FeignException e) {
			return createFeignExceptionResponse(e);
		} catch (RuntimeException e) {
			return createRuntimeExceptionResponse(e);
		}
	}

	public ResponseEntity<ApiResponse> removeLikeTweet(final String token, String username, String id) {
		try {
			ValidationResponse validationResponse = jwtTokenValidation(token);
			if (validationResponse.getIsSuccess()) {
				ResponseEntity<?> response = updateServiceClient.dislikeTweet(username, id);
				return new ResponseEntity<>(new ApiResponse(true, response.getBody()), response.getStatusCode());
			} else {
				return createUnauthorizedResponse(validationResponse);
			}
		} catch (FeignException e) {
			return createFeignExceptionResponse(e);
		} catch (RuntimeException e) {
			return createRuntimeExceptionResponse(e);
		}
	}

	public ResponseEntity<ApiResponse> replyTweet(final String token, String username, String id, Comment comment) {
		try {
			ValidationResponse validationResponse = jwtTokenValidation(token);
			if (validationResponse.getIsSuccess()) {
				comment.setTime(LocalDateTime.now());
				ResponseEntity<?> response = updateServiceClient.replyTweet(comment, id);
				return new ResponseEntity<>(new ApiResponse(true, response.getBody()), response.getStatusCode());
			} else {
				return createUnauthorizedResponse(validationResponse);
			}
		} catch (FeignException e) {
			return createFeignExceptionResponse(e);
		} catch (RuntimeException e) {
			return createRuntimeExceptionResponse(e);
		}
	}

	private ResponseEntity<ApiResponse> createUnauthorizedResponse(ValidationResponse validationResponse) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setError(validationResponse.getMessage().toString());
		apiResponse.setSuccess(false);
		return new ResponseEntity<>(apiResponse, validationResponse.getCode());
	}

	private ResponseEntity<ApiResponse> createFeignExceptionResponse(FeignException e) {
		ApiResponse apiResponse = new ApiResponse();
		System.out.println(e.contentUTF8());
		apiResponse.setError(e.contentUTF8() == "" ? e.getMessage() : e.contentUTF8());
		apiResponse.setSuccess(false);
		HttpStatus status = e.status() == -1 ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(e.status());
		return new ResponseEntity<>(apiResponse, status);
	}

	private ResponseEntity<ApiResponse> createRuntimeExceptionResponse(RuntimeException e) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setError(e.getMessage());
		apiResponse.setSuccess(false);
		return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ValidationResponse jwtTokenValidation(String token) {
		try {
			ValidationResponse reponse = authenticationServiceClient.validateAndReturnUser(token).getBody();
			return reponse;
		} catch (FeignException.Unauthorized e) {
			ValidationResponse validationResponse = new ValidationResponse();
			validationResponse.setIsSuccess(false);
			validationResponse.setMessage("JWT Token is Not Valid");
			validationResponse.setCode(HttpStatus.valueOf(e.status()));
			return validationResponse;
		} catch (FeignException e) {
			ValidationResponse validationResponse = new ValidationResponse();
			validationResponse.setIsSuccess(false);
			validationResponse.setMessage("Exception occurred while invoking authentication Api");
			validationResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
			return validationResponse;
		}
	}

	@Override
	public ResponseEntity<ApiResponse> setTag(String token, Tag tag) {
		try {
			ValidationResponse validationResponse = jwtTokenValidation(token);
			if (validationResponse.getIsSuccess()) {
				kafkaTemplate.send("tweetTag", tag);
				return new ResponseEntity<>(new ApiResponse(true, "tags sent"), HttpStatus.OK);
			} else {
				return createUnauthorizedResponse(validationResponse);
			}
		} catch (RuntimeException e) {
			return createRuntimeExceptionResponse(e);
		}
	}

}
