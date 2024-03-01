/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.malyskok.userservice.service;

import com.malyskok.userservice.dto.UserDto;
import com.malyskok.userservice.repository.UserRepository;
import com.malyskok.userservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Mono<UserDto> getUserById(Integer userId) {
        return userRepository.findById(userId)
                .map(EntityDtoUtil::toDto);
    }

    public Flux<UserDto> getAllUsers() {
        return userRepository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> insertUser(Mono<UserDto> userDto) {
        return userDto.map(EntityDtoUtil::toEntity)
                .flatMap(userRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> updateUser(Integer id, Mono<UserDto> userDtoMono) {
        return userRepository.findById(id)
                .flatMap(existingUser ->
                        userDtoMono.map(EntityDtoUtil::toEntity)
                                .doOnNext(userToSave -> userToSave.setId(existingUser.getId())))
                .flatMap(userToSave -> userRepository.save(userToSave))
                .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> deleteUser(Integer id){
        return userRepository.deleteById(id);
    }
}