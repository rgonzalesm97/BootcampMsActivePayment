package com.bank.activePayment.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.bank.activePayment.service.ActivePaymentService;
import com.bank.activePayment.model.Credit;
import com.bank.activePayment.model.History;
import com.bank.activePayment.proxy.ActivePaymentProxy;

import reactor.core.publisher.Mono;

@Service
public class ActivePaymentServiceImpl implements ActivePaymentService{
	
private ActivePaymentProxy activePaymentProxy = new ActivePaymentProxy();
	
	@Override
	public Mono<Credit> PayCredit(String idCredit, Double amount) {
		
		Mono<Credit> actualCredit = activePaymentProxy.getCredit(idCredit);
		
		return actualCredit.flatMap(x -> {
			
			x.setDebt(x.getDebt()-amount);
			
			
			
			return activePaymentProxy.updateAccount(x)
										.doOnSuccess(y -> {
											if(y.getId()!=null) {
												saveHistory(y.getId(), "credit pay", amount);
											}
										});
		});
		
	}	
	
	
	public void saveHistory(String idProduct,
							String type,
							Double amount) {
		History history = new History();
		history.setIdProduct(idProduct);
		history.setType(type);
		history.setAmount(amount);
		history.setDate(new Date());
		
		activePaymentProxy.saveHistory(history);
		
	}
	
}
