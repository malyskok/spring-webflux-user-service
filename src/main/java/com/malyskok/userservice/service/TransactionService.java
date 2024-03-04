/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.malyskok.userservice.service;

import com.malyskok.userservice.dto.TransactionRequestDto;
import com.malyskok.userservice.dto.TransactionResponseDto;
import com.malyskok.userservice.dto.TransactionStatus;
import com.malyskok.userservice.entity.UserTransaction;
import com.malyskok.userservice.repository.UserRepository;
import com.malyskok.userservice.repository.UserTransactionRepository;
import com.malyskok.userservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserTransactionRepository transactionRepository;

    public Mono<TransactionResponseDto> createTransaction(TransactionRequestDto requestDto){
//        return userRepository.updateUserBalance(requestDto.getUserId(), requestDto.getAmount())
//                .filter(Boolean::booleanValue)
//                .map(aBoolean -> transactionRepository.save(EntityDtoUtil.toEntity(requestDto)))
//                .map(userTransactionMono -> EntityDtoUtil.toDto(requestDto, TransactionStatus.APPROVED))
//                .defaultIfEmpty(EntityDtoUtil.toDto(requestDto, TransactionStatus.DECLINED));

        return userRepository.updateUserBalance(requestDto.getUserId(), requestDto.getAmount())
                .filter(Boolean::booleanValue)
                .map(aBoolean -> EntityDtoUtil.toEntity(requestDto))
                .flatMap(userTransaction -> transactionRepository.save(userTransaction))
                .map(userTransactionMono -> EntityDtoUtil.toDto(requestDto, TransactionStatus.APPROVED))
                .defaultIfEmpty(EntityDtoUtil.toDto(requestDto, TransactionStatus.DECLINED));
    }

    public Flux<UserTransaction> getUserTransactions(Integer userId){
        return transactionRepository.findByUserId(userId);
    }
}