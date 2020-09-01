package com.laptopssale.Services;

import com.laptopssale.Entities.Roles;
import com.laptopssale.Entities.User;
import com.laptopssale.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private MailSender mailSender;

    @Value("${domain}")
    private String domain;

    @Bean
    BCryptPasswordEncoder getPasswordEncode() {
        return new BCryptPasswordEncoder();
    }

    public void addUser(User user) throws NullPointerException {
        user.setActivationCode(UUID.randomUUID().toString());
        user.setRoles(Collections.singleton(Roles.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        sendMessage(user);
        userRepo.save(user);
    }

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format("Hello, %s! \n" +
                            "Welcome to LaptopShop. Please, visit next link: <a href=%s>Here</a>",
                    user.getUsername(),
                    domain + "/registration/" + user.getActivationCode());
            mailSender.send(user.getEmail(), "Activation code", message);
        }
    }

    @Override
    public User loadUserByUsername(String s) throws UsernameNotFoundException {
        if (userRepo.findByUsername(s) == null) {
            throw new UsernameNotFoundException("User not found!");
        } else
            return userRepo.findByUsername(s);
    }

    public void changeProfile(User user, User userEdit) throws NullPointerException {
        user.setName(userEdit.getName());
        user.setSurname(userEdit.getSurname());
        user.setOtchestvo(userEdit.getOtchestvo());
        user.setCountry(userEdit.getCountry());
        user.setTown(userEdit.getTown());


        if (!user.getEmail().equals(userEdit.getEmail())) {
            user.setActivationCode(UUID.randomUUID().toString());
            user.setEmail(userEdit.getEmail());
            user.setActivated(false);
            sendMessage(user);
        }
        if (!passwordEncoder.encode(user.getPassword()).equals(passwordEncoder.encode(userEdit.getPassword())) && !userEdit.getPassword().isEmpty())  {
            user.setPassword(passwordEncoder.encode(userEdit.getPassword()));
        }
        userRepo.save(user);

    }



}
