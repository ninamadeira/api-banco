package com.madeira.apibanco.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.madeira.apibanco.domain.Correntista;
import com.madeira.apibanco.dto.CorrentistaDTO;
import com.madeira.apibanco.services.CorrentistaService;

@RestController
@RequestMapping(value = "/correntistas")
public class CorrentistaResource {
	@Autowired
	private CorrentistaService service;

	@GetMapping("/{id}")
	public ResponseEntity<Correntista> find(@PathVariable Integer id) {
		Correntista obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<CorrentistaDTO>> findAll() {
		List<Correntista> list = service.findAll();
		List<CorrentistaDTO> listDto = list.stream().map(obj -> new CorrentistaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping("/page")
	public ResponseEntity<Page<CorrentistaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Correntista> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CorrentistaDTO> listDto = list.map(obj -> new CorrentistaDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
