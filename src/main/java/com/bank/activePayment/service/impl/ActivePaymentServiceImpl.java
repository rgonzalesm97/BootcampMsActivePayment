package com.bank.activePayment.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.bank.activePayment.service.ActivePaymentService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Service
public class ActivePaymentServiceImpl implements ActivePaymentService{
	
	private WebClient productClient = WebClient.builder().baseUrl("http://localhost:8081/product").build();
	
	
}
