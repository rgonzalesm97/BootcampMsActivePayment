package com.bank.activePayment.service;

import com.bank.activePayment.model.History;

import reactor.core.publisher.Mono;

public interface ActivePaymentService {
	public Mono<History> payCredit(String idCredit, Double amount);
}