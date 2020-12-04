package com.makocabey.rest;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.makocabey.rest.Exceptions.*;
import com.makocabey.rest.Methods.*;



@RestController
class ParityController {
	
	@Autowired
	private final ParityRepository repository;
	
	@Autowired
	private final CalculateNonExistParity calculateParity;
	
	@Autowired
	private final XMLService xmlService;
	
	ParityController(ParityRepository repository, 
			CalculateNonExistParity calculateParity,
			XMLService xmlService) {
		
		this.repository = repository;
		this.calculateParity = calculateParity;
		this.xmlService = xmlService;
	}
	
	
	Parity calculateNonExistParity(LocalDate date, String parityCode) {
		return this.calculateParity.calculateNonExistParity(date, parityCode);
	}
	
	
	
	static Map<String, ChangeModelFactory> changeModelMap = new TreeMap<String, ChangeModelFactory>
																(String.CASE_INSENSITIVE_ORDER);
	static {
		changeModelMap.put("ABSOLUTE", ChangeModelFactory.ABSOLUTE);
		changeModelMap.put("RELATIVE", ChangeModelFactory.RELATIVE);
		changeModelMap.put("LOGARITMIC", ChangeModelFactory.LOGARITMIC);
	}
	
	
	
	/* Verileri Listele */
	@GetMapping("/")
	public List<Parity> listAllData() 
		throws DatabaseEmptyException {
		
		List<Parity> queryRes =  repository.findAll();
		
		if (queryRes.isEmpty())	
			throw new DatabaseEmptyException();
		
		return queryRes;
	}
	
	
	
	@PostMapping("")
	public ResponseEntity<String> insertDataFromAllDays(
			@RequestBody List<Parity> newParites) 
				throws CurrencyLessThanZeroException, 
				IncorrectParityCodeFormatException {
		
		Set<LocalDate> setOfDate = new HashSet<LocalDate>();
		
		Set<String> setOfParityCode = new HashSet<String>(); 
		
		
		for(Parity p : newParites) {
			
			if (setOfParityCode.add((p.getParityCode() + String.valueOf(p.getDate()))) == false) {
				throw new EntityElementDuplicatedException(p.getParityCode(), p.getDate());
			}
			
			if ((p.getSalePrice() <= 0.0) || 
				(p.getPurchasePrice() <= 0.0) || 
				(p.getAveragePrice() <= 0.0) 
				) 
				throw new CurrencyLessThanZeroException(p.getDate(), p.getParityCode());
			
			if ( p.getParityCode().length() != 6) 
				throw new IncorrectParityCodeFormatException(p.getParityCode(), p.getDate());
			
			
			setOfDate.add(p.getDate());
		}
		
		
		for(LocalDate date : setOfDate) {
			
			repository.deleteByDate(date);
		}
		
		repository.saveAll(newParites);
		
		return new ResponseEntity<String>("SUCCESS ! ", HttpStatus.OK);
	}
	
	 
	
	/* 1. Bir tarihte verilen parite data listesinin veritabanına kaydedilmesi. */
	@PostMapping("/{date}")
	public ResponseEntity<String>  insertNewDataWithDate(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, 
			@RequestBody List<Parity> newParites) 
				throws EntityElementDuplicatedException, 
				CurrencyLessThanZeroException,
				IncorrectParityCodeFormatException {
		
		Set<String> setOfParityCode = new HashSet<String>();
		
		for(Parity p : newParites) {
			
			if (setOfParityCode.add(p.getParityCode()) == false) 
				throw new EntityElementDuplicatedException(p.getParityCode(), p.getDate());
			
			if ((p.getSalePrice() <= 0.0) 	  || 
				(p.getPurchasePrice() <= 0.0) || 
				(p.getAveragePrice() <= 0.0)    ) 
				throw new CurrencyLessThanZeroException(p.getDate(), p.getParityCode());
			
			if (p.getParityCode().length() != 6) 
				throw new IncorrectParityCodeFormatException(p.getParityCode(), p.getDate());
			
		}
				
		
		if(!((repository.findByDate(date)).isEmpty())) {
			repository.deleteByDate(date);
		}
		
		
		repository.saveAll(newParites);
		
		
		return new ResponseEntity<String>("SUCCESS - " + String.valueOf(date) + " - dated data inserted.", 
				HttpStatus.OK);
	}
	
	
	
	/* 2. İstenen tarihteki kaydedilmiş parite data listesinin listelenmesi. */
	@GetMapping("/{date}")
	public List<Parity> listDataByDate(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) 
				throws DateNotFoundException {
		
		List<Parity> queryResults = repository.findByDate(date);
	
		
		if(queryResults.isEmpty()) 
			throw new DateNotFoundException(date);
		
		
		return queryResults;
	}
	
	
	
	/* 3. İstenen tarihteki bir parite datasının listelenmesi. 
	 *    Eğer istenen parite kaydedilenler arasında değilse 
	 *    mevcut parite datalarından hesaplanması.
	*/
	@GetMapping("/{date}/{parityCode}")
	public Parity listDataByDateAndParityCode(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
			@PathVariable String parityCode) 
				throws DateNotFoundException, 
				IncorrectParityCodeFormatException{
		
		
		if (repository.findByDate(date).isEmpty())  
			throw new DateNotFoundException(date);
		
		if (parityCode.length() != 6)  
			throw new IncorrectParityCodeFormatException(parityCode);

		
		List<Parity> queryRes = repository.findByDateAndParityCodeAllIgnoreCase(date, parityCode);
		
		
		if((queryRes).isEmpty()) {
			return calculateNonExistParity(date, parityCode);
		}
			
		else {
			return queryRes.get(0);
		}
	}
	
	
	
	/* 4. İstenen başlangıç ve bitiş tarihi arasında istenen paritenin tarihsel 
	 *    ortalama fiyat datasının listelenmesi.
	*/
	@GetMapping("/{startDate}/{endDate}/{parityCode}")
	public List<Parity> ListDataByParityCodeBetweenDays(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, 
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
			@PathVariable String parityCode) 
				throws IncorrectParityCodeFormatException, 
				EndDateAfterStartDateException, DateNotFoundException { 
		
		if (parityCode.length() != 6)  
			throw new IncorrectParityCodeFormatException(parityCode);
		
		if(startDate.isAfter(endDate)) 
			throw new EndDateAfterStartDateException(startDate, endDate);
		
		
		if(repository.getAllBetweenDates(startDate, endDate).isEmpty()) 
			throw new DateNotFoundException(startDate, endDate);
		
		
		List<Parity> queryRes = repository.getAllBetweenDatesAndParity(startDate, endDate, parityCode);
		
		if((queryRes).isEmpty()) {
			
			for(LocalDate dt = startDate; dt.isBefore(endDate.plusDays(1)); dt=dt.plusDays(1)) {
				queryRes.add(calculateNonExistParity(dt, parityCode));
			}
			
			return queryRes;
		}
		
		
		else {
			return queryRes;
		}
		
	}
	
	
	
	/* 5. Tarihsel datanın istenen getiri değişim metoduna göre 
	 * getiri değişiminin hesaplanıp listelenmesi.
	*/ 
	@GetMapping("/{startDate}/{endDate}/{parityCode}/{changeModel}")
	public List<CustomParityResponse> calculateMethodByParityCodeBetweenDates(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, 
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
			@PathVariable String parityCode,
			@PathVariable String changeModel) 
				throws IncorrectParityCodeFormatException,
				EndDateAfterStartDateException, 
				DateNotFoundException,
				ChangeModelNotFoundException {
		
		if (parityCode.length() != 6)  
			throw new IncorrectParityCodeFormatException(parityCode);
		
		if(startDate.isAfter(endDate)) 
			throw new EndDateAfterStartDateException(startDate, endDate);
		
		if(repository.getAllBetweenDates(startDate, endDate).isEmpty()) 
			throw new DateNotFoundException(startDate, endDate);
		
		
		List<Parity> queryResults = repository.getAllBetweenDatesAndParity
				(startDate, endDate, parityCode);
		
		
		if(queryResults.isEmpty()) { 
			
			for(LocalDate today = startDate; today.isBefore(endDate.plusDays(1)); today=today.plusDays(1)) {
				
				queryResults.add(calculateNonExistParity(today, parityCode));
				
			}
		}
		
		
		List<CustomParityResponse> listOfResponse = new ArrayList<CustomParityResponse>();
		
		
		Double ChangeModelResult = null;
		Double todaysPrice = null;
		Double yesterdaysPrice = null;
		
		
		for(Parity p : queryResults) {
			
			todaysPrice = p.getPurchasePrice();
			
			
			try {
				yesterdaysPrice = repository.findByDateAndParityCodeAllIgnoreCase
									(p.getDate().minusDays(1), p.getParityCode())
										.get(0).getPurchasePrice();
			}
			catch (Exception e) {
				try {
					Parity yest = calculateNonExistParity
									(p.getDate().minusDays(1), p.getParityCode());
					
					 yesterdaysPrice = yest.getSalePrice();
					
				} 
				catch (Exception e2) {
					throw new DateNotFoundException(p.getDate(), p.getParityCode());
				}
			}
			
			ChangeModelResult = changeModelMap.get(changeModel).
					CalculateChangeModel(todaysPrice, yesterdaysPrice);
			
			CustomParityResponse response = new CustomParityResponse(
					p.getDate(), p.getParityCode(), ChangeModelResult, changeModel);
			
			listOfResponse.add(response);
		}
		
		return listOfResponse;
	}
	
	
	
	
	
	@GetMapping("/dailyparity") 
	public ResponseEntity<String>  getDailyParityData() {
		List<Parity> listOfParity = xmlService.parseAndSaveParityData();
		
		LocalDate todaysDate = listOfParity.get(0).getDate();
		
		if(!((repository.findByDate(todaysDate)).isEmpty())) {
			repository.deleteByDate(todaysDate);
		}
		
		repository.saveAll(listOfParity);
		
		return new ResponseEntity<String>("The parity data of day{" + todaysDate + "} has been successfully saved to the database.", HttpStatus.OK);
	}
}
