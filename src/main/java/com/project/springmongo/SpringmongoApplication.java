package com.project.springmongo;

import com.project.springmongo.model.ERole;
import com.project.springmongo.model.Role;
import com.project.springmongo.repository.RoleRepository;
import com.project.springmongo.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringmongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringmongoApplication.class, args);
	}

}
