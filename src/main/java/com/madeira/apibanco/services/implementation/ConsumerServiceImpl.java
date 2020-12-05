package com.madeira.apibanco.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madeira.apibanco.domain.Correntista;
import com.madeira.apibanco.dto.CorrentistaNewDTO;
import com.madeira.apibanco.services.ConsumerService;
import com.madeira.apibanco.services.CorrentistaService;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	@Autowired
	private CorrentistaService service;

	@Override
	public void action(CorrentistaNewDTO correntistaNewDTO) {
		Correntista obj = service.fromDTO(correntistaNewDTO);
		obj = service.insert(obj);
	}
}
