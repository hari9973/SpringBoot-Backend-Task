package com.hari.NTAT_TASK;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.hari.NTAT_TASK.repository.UserRepository;
import com.hari.NTAT_TASK.models.Users;
import com.hari.NTAT_TASK.models.Profile;

@SpringBootApplication
public class NtatTaskApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(NtatTaskApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("https://simple-payment-app.netlify.app");
			}
		};
	}

	@Override
	public void run(String... args) throws Exception {
		Users u1 = new Users("guest","guest","123456");
		Profile p1 = new Profile("https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg","10000");
		u1.setProfile(p1);
		userRepository.save(u1);

		Users u2 = new Users("User1","User1","123456");
		Profile p2 = new Profile("https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg","10000");
		u1.setProfile(p2);
		userRepository.save(u2);

		Users u3 = new Users("User2","User2","123456");
		Profile p3 = new Profile("https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg","10000");
		u1.setProfile(p3);
		userRepository.save(u3);

		Users u4 = new Users("Richard Hansons","Richard Hansons","123456");
		Profile p4 = new Profile("https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg","10000");
		u1.setProfile(p4);
		userRepository.save(u4);
	}
}

/*@Component
class DemoCommandLineRunner implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		Users u1 = new Users("guest","guest","123456");
		Profile p1 = new Profile("https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg","10000");
		u1.setProfile(p1);
		userRepository.save(u1);

		Users u2 = new Users("User1","User1","123456");
		Profile p2 = new Profile("https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg","10000");
		u1.setProfile(p2);
		userRepository.save(u2);

		Users u3 = new Users("User2","User2","123456");
		Profile p3 = new Profile("https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg","10000");
		u1.setProfile(p3);
		userRepository.save(u3);

		Users u4 = new Users("Richard Hansons","Richard Hansons","123456");
		Profile p4 = new Profile("https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg","10000");
		u1.setProfile(p4);
		userRepository.save(u4);
	}
}*/