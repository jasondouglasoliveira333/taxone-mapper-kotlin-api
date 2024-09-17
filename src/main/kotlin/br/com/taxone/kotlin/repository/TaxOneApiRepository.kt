package br.com.taxone.kotlin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.taxone.kotlin.entity.TaxOneApi;

@Repository
public interface TaxOneApiRepository : JpaRepository<TaxOneApi, Int>{

}
