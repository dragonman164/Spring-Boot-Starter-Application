package com.learn.spring.security.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.learn.spring.security.practice.models.User;
import com.learn.spring.security.practice.repo.UserRepository;

@SpringBootApplication
public class SpringSecurityPracticeApplication implements CommandLineRunner{


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityPracticeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(
            "abc",
            this.bCryptPasswordEncoder.encode("abc"),
            "abc@gmail.com",
			"ROLE_NORMAL"

        );
		this.userRepository.save(u1);
		User u2 = new User(
            "xyz",
            this.bCryptPasswordEncoder.encode("xyz"),
            "xyz@gmail.com",
			"ROLE_ADMIN"

        );
		this.userRepository.save(u2);
	}

}
