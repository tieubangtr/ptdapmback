package com.example.managementlibrary;


import com.example.managementlibrary.common.ERole;
import com.example.managementlibrary.common.Mail;
import com.example.managementlibrary.dto.request.RoleRequest;
import com.example.managementlibrary.dto.request.UserRequest;
import com.example.managementlibrary.entity.Role;
import com.example.managementlibrary.entity.User;
import com.example.managementlibrary.repository.RoleRepository;
import com.example.managementlibrary.repository.UserRepository;
import com.example.managementlibrary.service.MailService;
import com.example.managementlibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class ManagementLibraryApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ManagementLibraryApplication.class, args);
    }

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;



    @Override
    public void run(String... args) throws Exception {
//        if(roleRepository.findAll().isEmpty()){
//            try{
//                roleRepository.save(new Role(ERole.ROLE_USER));
//                roleRepository.save(new Role(ERole.ROLE_ADMIN));
//                UserRequest user=new UserRequest();
//                user.setName("Hieu");
//                user.setEmail("admin");
//                user.setPassword("1");
//                RoleRequest roleRequest=new RoleRequest();
//                roleRequest.setId(new Long(1));
//                Set<RoleRequest> roleRequests=new HashSet<>();
//                roleRequests.add(roleRequest);
//                user.setRoles(roleRequests);
//                userService.create(user);
//            }
//            catch (Exception e){
//                System.out.println(e);
//            }
//
//        }

    }
}
