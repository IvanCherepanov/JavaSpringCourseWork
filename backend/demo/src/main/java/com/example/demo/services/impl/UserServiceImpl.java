package com.example.demo.services.impl;

import com.example.demo.model.dao.IUserRepository;
import com.example.demo.model.entity.User;
import com.example.demo.security.ApplicationUserRole;
import com.example.demo.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
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
    public String getUserRole(Authentication authentication) {
        if (authentication == null)
            return "GUEST";
        else
            return ((User) loadUserByUsername(authentication.getName())).getRole();
    }

    @Override
    public Long getUserId(Authentication authentication) {
        if (authentication == null)
            return -1l;
        else
            return ((User) loadUserByUsername(authentication.getName())).getId();
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

    @Override
    public void create(String email,String username,String password, String role) {
        User user = new User();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setEmail(email);
        user.setUsername(username);
        user.setRole(role);
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
