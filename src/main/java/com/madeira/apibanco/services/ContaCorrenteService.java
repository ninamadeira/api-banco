package com.madeira.apibanco.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.madeira.apibanco.domain.ContaCorrente;
import com.madeira.apibanco.repositories.ContaCorrenteRepository;
import com.madeira.apibanco.services.exception.ObjectNotFoundException;

@Service
public class ContaCorrenteService {

	@Autowired
	private ContaCorrenteRepository repo;

	public ContaCorrente find(Integer id) {
		Optional<ContaCorrente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + ContaCorrente.class.getName()));

	}
	
	public ContaCorrente findByNumero(Integer numero) {
		ContaCorrente obj = repo.findByNumero(numero);
		return obj;

	}

	public List<ContaCorrente> findAll() {
		return repo.findAll();
	}

	public Page<ContaCorrente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	
}
