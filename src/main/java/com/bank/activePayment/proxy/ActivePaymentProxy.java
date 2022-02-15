package com.bank.activePayment.proxy;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.bank.activePayment.model.Credit;
import com.bank.activePayment.model.History;

import reactor.core.publisher.Mono;

public class ActivePaymentProxy {
	
	private final WebClient.Builder webClientBuilder = WebClient.builder();
	
	public Mono<Credit> getCredit(String idProduct){
		return webClientBuilder.build()
								.get()
								.uri("http://localhost:8090/credit/" + idProduct)
								.retrieve()
								.bodyToMono(Credit.class);
	}
	
	public Mono<Credit> updateAccount(Credit credit){
		return webClientBuilder.build()
				.put()
				.uri("http://localhost:8090/credit")
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(credit))
				.retrieve()
				.bodyToMono(Credit.class);
	}
	
	public void saveHistory(History history) {
		webClientBuilder.build()
						.post()
						.uri("http://localhost:8090/history")
						.contentType(MediaType.APPLICATION_JSON)
						.body(BodyInserters.fromValue(history))
						.retrieve()
						.bodyToMono(History.class)
						.subscribe();
	}
}
