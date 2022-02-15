package com.bank.activePayment.service;

import com.bank.activePayment.model.Credit;

import reactor.core.publisher.Mono;

public interface ActivePaymentService {
	public Mono<Credit> PayCredit(String idCredit, Double amount);
}