package tn.esprit.pidev.repositories;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.pidev.entities.Stock;

public interface IStockRepository  extends CrudRepository<Stock, Integer>{
	
	

}
