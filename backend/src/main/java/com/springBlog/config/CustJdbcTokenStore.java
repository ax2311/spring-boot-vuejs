package com.springBlog.config;

import javax.sql.DataSource;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

public class CustJdbcTokenStore extends JdbcTokenStore {
	
	Logger logger = LogManager.getLogger(CustJdbcTokenStore.class);

	public CustJdbcTokenStore(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public OAuth2AccessToken readAccessToken(String tokenValue) {
	    OAuth2AccessToken accessToken = null;

	    try {
	        accessToken = new DefaultOAuth2AccessToken(tokenValue);
	    }
	    catch (EmptyResultDataAccessException e) {
	        	logger.info("Failed to find access token for token "+tokenValue);
	    }
	    catch (IllegalArgumentException e) {
	    	logger.info("Failed to deserialize access token for " +tokenValue,e);
	        removeAccessToken(tokenValue);
	    }

	    return accessToken;
	}

}
