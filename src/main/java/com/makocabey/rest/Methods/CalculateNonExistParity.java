package com.makocabey.rest.Methods;


import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.makocabey.rest.Exceptions.ParityCodeNotFoundException;

import com.makocabey.rest.Parity;
import com.makocabey.rest.ParityRepository;



@Component
public class CalculateNonExistParity {

	private final ParityRepository repository;
	
	
	CalculateNonExistParity(ParityRepository repository){
		this.repository = repository;
	}
	
	
	
	public Parity calculateNonExistParity(LocalDate date, String parityCode) 
			throws ParityCodeNotFoundException {
		
		parityCode = parityCode.toUpperCase();
		
		String pr_1_kod = parityCode.substring(0, 3);
		String pr_2_kod = parityCode.substring(3);
		
		
		List<Parity> pr1 = repository.findByDateAndParityCodeAllIgnoreCase(date, pr_1_kod+"TRY");
		List<Parity> pr2 = repository.findByDateAndParityCodeAllIgnoreCase(date, pr_2_kod+"TRY");
		
			
		if((pr1.isEmpty()) || (pr2.isEmpty())) 
			throw new ParityCodeNotFoundException(parityCode);

	
		return new Parity(parityCode, 
				date, 
				(pr1.get(0).getPurchasePrice() / pr2.get(0).getPurchasePrice()),
				(pr1.get(0).getSalePrice()     / pr2.get(0).getSalePrice()),
				(pr1.get(0).getAveragePrice()  / pr2.get(0).getAveragePrice()));
	}
}
