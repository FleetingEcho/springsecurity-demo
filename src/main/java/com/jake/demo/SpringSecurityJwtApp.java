package com.jake.demo;

import com.jake.demo.system.entity.Role;
import com.jake.demo.system.entity.User;
import com.jake.demo.system.entity.UserRole;
import com.jake.demo.system.enums.RoleType;
import com.jake.demo.system.repository.RoleRepository;
import com.jake.demo.system.repository.UserRepository;
import com.jake.demo.system.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class SpringSecurityJwtApp implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    public static void main(java.lang.String[] args) {
        SpringApplication.run(SpringSecurityJwtApp.class, args);
    }

    @Override
    public void run(java.lang.String... args) {
        //Initialization role information
        for (RoleType roleType : RoleType.values()) {
            roleRepository.save(new Role(roleType.getName(), roleType.getDescription()));
        }
        //initialize admin user
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = User.builder().enabled(true).fullName("admin").userName("root").password(bCryptPasswordEncoder.encode("root")).build();
        userRepository.save(user);
        Role role = roleRepository.findByName(RoleType.ADMIN.getName()).get();
        userRoleRepository.save(new UserRole(user, role));
    }
}
