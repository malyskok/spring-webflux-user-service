/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.malyskok.userservice.controller;

import com.malyskok.userservice.dto.TransactionRequestDto;
import com.malyskok.userservice.dto.TransactionResponseDto;
import com.malyskok.userservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user-transaction")
public class UserTransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    public Mono<TransactionResponseDto> createTransaction(
            @RequestBody Mono<TransactionRequestDto> requestDtoMono) {

        return requestDtoMono.flatMap(requestDto ->
                transactionService.createTransaction(requestDto));
    }
}