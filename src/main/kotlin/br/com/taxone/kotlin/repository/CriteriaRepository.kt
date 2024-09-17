package br.com.taxone.kotlin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.taxone.kotlin.entity.Criteria;

@Repository
public interface CriteriaRepository : JpaRepository<Criteria, Int>{

}
