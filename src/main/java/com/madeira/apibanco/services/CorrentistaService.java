package com.madeira.apibanco.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.madeira.apibanco.domain.ContaCorrente;
import com.madeira.apibanco.domain.Correntista;
import com.madeira.apibanco.domain.enums.TipoCorrentista;
import com.madeira.apibanco.dto.CorrentistaDTO;
import com.madeira.apibanco.dto.CorrentistaNewDTO;
import com.madeira.apibanco.repositories.ContaCorrenteRepository;
import com.madeira.apibanco.repositories.CorrentistaRepository;
import com.madeira.apibanco.services.exception.ObjectNotFoundException;
import com.madeira.apibanco.services.validation.utils.Util;

@Service
public class CorrentistaService {

	@Autowired
	private CorrentistaRepository repo;

	@Autowired
	private ContaCorrenteRepository repo2;

	@Value("${agencia}")
	private String agencia;

	public Correntista find(Integer id) {
		Optional<Correntista> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Correntista.class.getName()));

	}

	public List<Correntista> findAll() {
		return repo.findAll();
	}

	public Page<Correntista> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	@Transactional
	public Correntista insert(Correntista obj) {
		obj.setId(null);
		Correntista correntista = repo.findByCpfOuCnpj(obj.getCpfOuCnpj());
		if (correntista == null) {
			Integer numeroConta = 0;
			boolean achouNumeroContaDistinto = false;
			while (!achouNumeroContaDistinto) {
				numeroConta = Util.getNumeroContaCorrente();
				ContaCorrente aux = repo2.findByNumero(numeroConta);
				if (aux == null) {
					achouNumeroContaDistinto = true;
				}
			}
			String tipo = "C";
			if (obj.getTipo().getCod() == 2) {
				tipo = "E";
			}

			String faixaScore = "";
			if (obj.getScore() >= 0 && obj.getScore() <= 1) {
				faixaScore = "o limite de cheque especial estará desabilitado e não irá gerar cartão de crédito.";
			} else if (obj.getScore() >= 2 && obj.getScore() <= 5) {
				faixaScore = "o limite de cheque especial será de R$ 1000,00 e o limite do cartão de crédito criado será de R$ 200,00.";
			} else if (obj.getScore() >= 6 && obj.getScore() <= 8) {
				faixaScore = "o limite de cheque especial será de R$ 2000,00 e o limite do cartão de crédito criado será de R$ 2000,00.";
			} else if (obj.getScore() == 9) {
				faixaScore = "o limite de cheque especial será de R$ 5000,00 e o limite do cartão de crédito criado será de R$ 15000,00.";
			}

			ContaCorrente obj2 = new ContaCorrente(null, numeroConta, agencia, tipo, faixaScore, obj);
			obj.setContaCorrente(obj2);
			obj = repo.save(obj);
			obj2 = repo2.save(obj2);
		}
		return obj;
	}

	public Correntista fromDTO(CorrentistaDTO objDto) {
		return new Correntista(objDto.getId(), objDto.getNome(), TipoCorrentista.toEnum(objDto.getTipo()),
				objDto.getCpfOuCnpj(), objDto.getScore());
	}

	public Correntista fromDTO(CorrentistaNewDTO objDto) {
		return new Correntista(null, objDto.getNome(), TipoCorrentista.toEnum(objDto.getTipo()), objDto.getCpfOuCnpj(),
				Util.getScore());
	}

}
