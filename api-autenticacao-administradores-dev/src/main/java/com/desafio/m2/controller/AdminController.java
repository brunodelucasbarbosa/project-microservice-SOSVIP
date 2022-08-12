package com.desafio.m2.controller;

import com.desafio.m2.dto.AdminDTORequest;
import com.desafio.m2.dto.AdminDTOResponse;
import com.desafio.m2.exception.BadRequestException;
import com.desafio.m2.exception.InternalServerErrorException;
import com.desafio.m2.exception.ResourceNotFoundException;
import com.desafio.m2.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.desafio.m2.dto.AdminLoginDTO;
import com.desafio.m2.model.Admin;
import com.desafio.m2.services.IAdminService;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private IAdminService service;

	@GetMapping
	public ResponseEntity<Page<AdminDTOResponse>> getAllAdmin(Pageable pageable) {
		try {
			return ResponseEntity.ok(service.getAllAdmin(pageable));
		} catch (Exception e) {
			throw new InternalServerErrorException(e.getMessage());
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<AdminDTOResponse> getAdminById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(service.getAdminById(id));
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new InternalServerErrorException(e.getMessage());
		}
	}
	@PostMapping
	public ResponseEntity<?> createAdmin(@Valid @RequestBody AdminDTORequest newAdmin) {
		try {
			return ResponseEntity.ok(service.createAdmin(newAdmin));
		} catch (BadRequestException e) {
			throw new BadRequestException(e.getMessage());
		} catch (Exception e) {
			throw new InternalServerErrorException(e.getMessage());
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody AdminLoginDTO loginData) {
		try {
			return ResponseEntity.ok(service.createTokenAdmin(loginData));
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new InternalServerErrorException(e.getMessage());
		}
	}

	@GetMapping("/verificaToken")
	public ResponseEntity<String> verificaTokenaApis(@RequestHeader("Authorization") String tokenRecebido) {
		try {
			boolean tokenValido = TokenUtils.validateTokenAPis(tokenRecebido);

			if(tokenValido) {
				return ResponseEntity.ok("Token validado com sucesso!");
			}

			return ResponseEntity.status(403).body("Token inválido!");
		} catch (Exception e) {
			throw new InternalServerErrorException(e.getMessage());
		}
	}

}
