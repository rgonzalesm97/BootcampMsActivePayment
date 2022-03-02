package com.bank.activePayment.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.bank.activePayment.model.Credit;
import com.bank.activePayment.model.History;
import com.bank.activePayment.proxy.ActivePaymentProxy;
import com.bank.activePayment.service.impl.ActivePaymentServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ActivePaymentServiceImplTest {
	
	@Mock
	private static ActivePaymentProxy proxy;
	
	private static ActivePaymentServiceImpl service;
	
	@BeforeAll
	public static void setUp() {
		proxy = mock(ActivePaymentProxy.class);
		//service = new ActivePaymentServiceImpl(proxy);
	}
		
	@Test
	void payCredit() {
		Credit credit = new Credit();
		credit.setId("23jk4hid23hf");
		credit.setIdClient("23jk4hidClient23hf");
		credit.setCardNumber("23jk4hidCard23hf");
		credit.setTypeCredit("23jk4htypeAccount23hf");
		credit.setAccountNumber("23jk4hcreditNumber23hf");
		credit.setBalance(Double.valueOf(28500.0));
		credit.setCredit(Double.valueOf(35000.0));
		credit.setDebt(Double.valueOf(1500));
		
		History history = new History();
		history.setIdProduct("23jk4hid23hf");
		history.setType("credit pay");
		history.setAmount(Double.valueOf(1500));
		history.setIdThirdPartyProduct(null);
		history.setDate(any());
		
		Mockito.when(proxy.getCredit("23jk4hid23hf").thenReturn(Mono.just(credit)));
		Mockito.when(proxy.updateCredit(credit).thenReturn(Mono.just(credit)));
		
		Mono<History> result = service.payCredit(credit.getId(), Double.valueOf(1500));
		
		StepVerifier.create(result)
		.expectNext(history)
		.verifyComplete();
	}
}
