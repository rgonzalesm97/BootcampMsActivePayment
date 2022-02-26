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
	public Mono<History> payCredit(String idCredit, Double amount) {
		
		return activePaymentProxy.getCredit(idCredit)
								 .flatMap(resp->payDebt(resp, amount))
								 .flatMap(activePaymentProxy::updateCredit)
								 .flatMap(resp->saveHistory(idCredit, "credit pay", amount, null));
		
	}	
	
	
	//AVTIVEPAYMENT UTIL METHODS
	public Mono<Credit> payDebt(Credit credit, Double amount) {
		
		Double debt = credit.getDebt();
		if(amount < debt) {
			credit.setDebt(debt-amount);
		}else {
			credit.setDebt(Double.valueOf(0));
		}
		return Mono.just(credit);
	}
	
	public Mono<History> saveHistory(String idProduct,
							String type,
							Double amount,
							String idThirdPartyProduct) {
		
		History history = new History();
		history.setIdProduct(idProduct);
		history.setType(type);
		history.setAmount(amount);
		history.setIdThirdPartyProduct(idThirdPartyProduct);
		history.setDate(new Date());
		
		return activePaymentProxy.saveHistory(history);
		
	}
	
}
