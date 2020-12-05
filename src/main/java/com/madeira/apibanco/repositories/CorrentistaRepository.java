package com.madeira.apibanco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.madeira.apibanco.domain.Correntista;

@Repository
public interface CorrentistaRepository extends JpaRepository<Correntista, Integer> {
	
	@Transactional(readOnly=true)
	Correntista findByCpfOuCnpj(String cpfOuCnpj);
}
