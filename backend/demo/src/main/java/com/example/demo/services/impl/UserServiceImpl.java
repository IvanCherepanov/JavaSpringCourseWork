package com.example.demo.services.impl;

import com.example.demo.model.dao.IUserRepository;
import com.example.demo.model.entity.User;
import com.example.demo.security.ApplicationUserRole;
import com.example.demo.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends AbstractServiceImpl<User, IUserRepository> implements IUserService, UserDetailsService {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private IUserRepository iUserRepository;

    @Autowired
    protected UserServiceImpl(IUserRepository defaultDao) {
        super(defaultDao);
        this.iUserRepository = defaultDao;
    }


    @Override
    public User update(Long id, User entity) {
        findById(id);
        entity.setId(id);
        return create(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user =  iUserRepository.findUserByUsername(username);
        if(user != null){
            return user;
        }
        throw new
                UsernameNotFoundException("User not exist with name :" +username);
    }

    public void create(String email,String username,String password) {
        User user = new User();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setEmail(email);
        user.setUsername(username);
        user.setRole(ApplicationUserRole.USER.name());
        iUserRepository.save(user);
        System.out.println(iUserRepository.findUserByUsername(username));
    }

    @Bean
    public void createDef(){
        User user = new User();
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setEmail("email");
        user.setUsername("username");
        user.setRole("ADMIN");
        if (iUserRepository.findUserByUsername(user.getUsername())==null) {
            iUserRepository.save(user);
        }
    }

    public void addUser(User user) {
        iUserRepository.save(user);
    }
}
