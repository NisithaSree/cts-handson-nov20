package com.example.OAuth20;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@RestController
public class OauthService {

	public static void main(String[] args) {
		SpringApplication.run(OauthService.class, args);
	}
	
	@RequestMapping(value="/user", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> userInfo(OAuth2Authentication oauth2){
		Map<String,Object> userInformation = new HashMap<>();
		userInformation.put("user", oauth2.getUserAuthentication().getPrincipal());
		userInformation.put("authorities", AuthorityUtils.authorityListToSet(oauth2.getUserAuthentication().getAuthorities()));
		return userInformation;
	}

}