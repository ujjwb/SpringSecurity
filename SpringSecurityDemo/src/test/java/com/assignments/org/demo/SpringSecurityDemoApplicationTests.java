package com.assignments.org.demo;

import com.assignments.org.demo.entities.Role;
import com.assignments.org.demo.repositories.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityDemoApplicationTests {

	@Autowired
	RoleRepository roleRepository;
	@Test
	void contextLoads() {
//		Role r1=new Role();
//		r1.setName("CUSTOMER");
//		roleRepository.save(r1);
	}

}
