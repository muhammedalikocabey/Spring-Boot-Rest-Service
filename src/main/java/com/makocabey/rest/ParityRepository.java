package com.makocabey.rest;


import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ParityRepository extends JpaRepository<Parity, Long>{
	
	Parity findById(long id);
	
	List<Parity> findAll();
	
	List <Parity> findByParityCodeAllIgnoreCase(String parityCode);
	
	List <Parity> findByDate(LocalDate date);
	
	List <Parity> findByDateAndParityCodeAllIgnoreCase(LocalDate date, String parityCode); 
	
	
	@Query(value = "FROM Parity WHERE date BETWEEN ?1 AND ?2")
	List<Parity> getAllBetweenDates(LocalDate startDate, LocalDate endDate);
	
	@Query(value = "FROM Parity WHERE date BETWEEN ?1 AND ?2 AND parityCode = UPPER(?3)") 
	List<Parity> getAllBetweenDatesAndParity(LocalDate startDate, LocalDate endDate, String parityCode);
	
	
	@Transactional
	List <Parity> deleteByDate(LocalDate date);
}
