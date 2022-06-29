package tn.esprit.pidev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.pidev.entities.ExchangeRequest;

public interface IExchangeRepository extends JpaRepository<ExchangeRequest, Integer> {

}
