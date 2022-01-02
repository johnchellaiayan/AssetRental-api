package com.assetmgmt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.assetmgmt.entity.User;
import com.assetmgmt.entity.model.UserModel;
import com.assetmgmt.repository.UserRepository;

@Repository
@Transactional
public class UserDao {

	@Autowired
	private EntityManager em;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	public User saveUser(UserModel userDto) {
		User user = modelMapper.map(userDto, User.class);
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String password = user.getPassword();
		if (password != null)
			user.setPassword(encoder.encode(password));
		userRepository.save(user);
		return user;
	}

	public List<User> getUsers() {
		return userRepository.findAll();	
	}

}
