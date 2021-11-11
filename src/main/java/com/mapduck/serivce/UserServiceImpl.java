package com.mapduck.serivce;


import com.mapduck.advice.DuplicatedUsernameException;
import com.mapduck.domain.Role;
import com.mapduck.domain.User;
import com.mapduck.dto.UserVO;
import com.mapduck.repository.RoleRepository;
import com.mapduck.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public void save(UserVO userDto) {
        log.info("server: UserService save");

        User fetchedUser = this.userRepository.findByUsername(userDto.getUsername());

        if(fetchedUser != null){
            log.info("Duplicated :: {}", fetchedUser.toString());
            throw new DuplicatedUsernameException("username already taken");
        }

        User user = new User();

        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));


        Role role = this.roleRepository.findByRoleName("ROLE_MEMBER");
        user.setRoles(Arrays.asList(role));
        userDto.setRoles(Arrays.asList(role.getRoleName()));

        LOGGER.debug(user.toString());
        this.userRepository.save(user);

        log.info(user.toString());
        userDto.setId(user.getId());
    }

    @Override
    @Transactional
    public UserVO findUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username);

        if(user == null){
            throw new RuntimeException("user not found");
        }
        UserVO userDto = new UserVO();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUsername());

        userDto.setRoles(
                user.getRoles().
                stream().
                map(Role::getRoleName).collect(Collectors.toList()));
        return userDto;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("user not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                        .collect(Collectors.toList()));
    }
}
