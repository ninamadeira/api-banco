package com.madeira.apibanco.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.madeira.apibanco.domain.Correntista;
import com.madeira.apibanco.domain.enums.TipoCorrentista;
import com.madeira.apibanco.dto.CorrentistaNewDTO;
import com.madeira.apibanco.repositories.CorrentistaRepository;
import com.madeira.apibanco.resources.exception.FieldMessage;
import com.madeira.apibanco.services.validation.utils.Util;

public class CorrentistaInsertValidator implements ConstraintValidator<CorrentistaInsert, CorrentistaNewDTO> {
	
	@Autowired
	private CorrentistaRepository repo;

	@Override
	public void initialize(CorrentistaInsert ann) {
	}

	@Override
	public boolean isValid(CorrentistaNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		Correntista aux = repo.findByCpfOuCnpj(objDto.getCpfOuCnpj());
		if (aux != null) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF/CNPJ já existente"));
		}

		if (objDto.getTipo().equals(TipoCorrentista.PESSOAFISICA.getCod()) && !Util.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}

		if (objDto.getTipo().equals(TipoCorrentista.PESSOAJURIDICA.getCod()) && !Util.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
