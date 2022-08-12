package com.desafio.m2.services;

import com.desafio.m2.dto.AdminDTORequest;
import com.desafio.m2.dto.AdminDTOResponse;
import com.desafio.m2.dto.AdminLoginDTO;
import com.desafio.m2.model.Admin;
import com.desafio.m2.security.Token;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAdminService {

	public Page<AdminDTOResponse> getAllAdmin(Pageable pageable);

	public AdminDTOResponse getAdminById(Long id);
	
	public Admin getAdminByEmail(String email);
	
	public AdminDTOResponse createAdmin(AdminDTORequest newAdmin);

	public Token createTokenAdmin(AdminLoginDTO loginData);
}
