package com.assetmgmt.controller;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/all/")
public class TestController {

	@PersistenceContext
	private EntityManager em;

	@GetMapping(value = "encrypt-password")
	public String sendMail(@RequestParam String password) {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return "Password is "+password;
	}
	
	@GetMapping(value = "test-password")
	public Map test(@RequestParam(defaultValue = "Sam",required = false) String name ) {
		Map<String,Object> map=new HashMap<>();
		map.put("name", name);
		return map;
	}
}
