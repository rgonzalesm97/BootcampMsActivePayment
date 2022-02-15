package com.bank.activePayment.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.activePayment.service.ActivePaymentService;
import com.bank.activePayment.model.Credit;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/active-payment")
public class ActivePaymentController {
	
	private final ActivePaymentService activePaymentService;

	@PostMapping("/pay/{id}")
	public Mono<Credit> pay(@PathVariable("id") String idProduct,
								@RequestParam Double amount) {
		
		return activePaymentService.PayCredit(idProduct, amount).switchIfEmpty(Mono.just(new Credit()));
		
	}
	
}
