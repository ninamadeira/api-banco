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

import com.madeira.apibanco.domain.ContaCorrente;
import com.madeira.apibanco.dto.ContaCorrenteDTO;
import com.madeira.apibanco.services.ContaCorrenteService;

@RestController
@RequestMapping(value = "/contascorrentes")
public class ContaCorrenteResource {
	@Autowired
	private ContaCorrenteService service;

	@GetMapping("/{id}")
	public ResponseEntity<ContaCorrente> find(@PathVariable Integer id) {
		ContaCorrente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<ContaCorrenteDTO>> findAll() {
		List<ContaCorrente> list = service.findAll();
		List<ContaCorrenteDTO> listDto = list.stream().map(obj -> new ContaCorrenteDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping("/page")
	public ResponseEntity<Page<ContaCorrenteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "numero") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction)  {
		Page<ContaCorrente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ContaCorrenteDTO> listDto = list.map(obj -> new ContaCorrenteDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}

}
