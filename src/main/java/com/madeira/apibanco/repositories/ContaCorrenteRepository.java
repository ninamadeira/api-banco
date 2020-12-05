package com.madeira.apibanco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.madeira.apibanco.domain.ContaCorrente;


@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Integer> {
	
	@Transactional(readOnly=true)
	ContaCorrente findByNumero(Integer numero);
	
	
}
