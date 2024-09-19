package com.example.springjwt.service;

import com.example.springjwt.dto.JoinDto;
import com.example.springjwt.entity.UserEntity;
import com.example.springjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDto joinDto) {
        String username = joinDto.getUsername();
        String password = joinDto.getPassword();

        if (userRepository.existsByUsername(username)) {
            throw new IllegalStateException("해당하는 회원이 있습니다.");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        userEntity.setPassword(encodedPassword);
        userEntity.setRole("ROLE_ADMIN");
        userRepository.save(userEntity);

    }


}
