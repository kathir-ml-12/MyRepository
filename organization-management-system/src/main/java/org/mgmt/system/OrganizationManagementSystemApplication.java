package org.mgmt.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class OrganizationManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizationManagementSystemApplication.class, args);
	}

}
