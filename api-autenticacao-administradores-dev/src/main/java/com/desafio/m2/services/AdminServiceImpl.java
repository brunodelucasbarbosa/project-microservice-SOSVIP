package com.desafio.m2.services;

import com.desafio.m2.dao.AdminDAO;
import com.desafio.m2.dto.AdminDTORequest;
import com.desafio.m2.dto.AdminDTOResponse;
import com.desafio.m2.dto.AdminLoginDTO;
import com.desafio.m2.exception.BadRequestException;
import com.desafio.m2.model.Admin;
import com.desafio.m2.security.Encrypt;
import com.desafio.m2.security.Token;
import com.desafio.m2.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminServiceImpl implements IAdminService {
      
	@Autowired
	private AdminDAO adminDao;
	
	@Override
	public Page<AdminDTOResponse> getAllAdmin(Pageable pageable) {
		return adminDao.findAllAdmin(pageable);
	}

	@Override
	public AdminDTOResponse getAdminById(Long id) {

		AdminDTOResponse admin = adminDao.findAdminById(id);

		if (admin == null){
			throw new BadRequestException("Administrador(a) não encontrado(a).");
		}

		return admin;
	}
	
	@Override
	public Admin getAdminByEmail(String email) {

		return adminDao.findByEmail(email);
	}
	@Override
	public AdminDTOResponse createAdmin(AdminDTORequest newAdmin) {
		if (adminDao.findByEmail(newAdmin.getEmail()) != null){
			throw new BadRequestException("E-mail já cadastrado!");
		}

		Admin admin = new Admin();
		admin.setName(newAdmin.getName());
		admin.setEmail(newAdmin.getEmail());
		admin.setPassword(newAdmin.getPassword());


		try {
			admin.setPassword(Encrypt.encrypt(admin.getPassword()));

		} catch (Exception e){
			e.printStackTrace();
		}
	 
		return new AdminDTOResponse(adminDao.save(admin)) ;
	}

	@Override
	public Token createTokenAdmin(AdminLoginDTO loginData) {

		Admin user = adminDao.findByEmail(loginData.getEmail());

		if (user == null){
			throw new BadRequestException("Administrador(a) não encontrado(a).");
		}

		try {
			String encryptedPassword = Encrypt.encrypt(loginData.getPassword());

			if (!user.getPassword().trim().equals(encryptedPassword.trim())){
				throw new BadRequestException("Senha incorreta!");
			}
		} catch (Exception e){
			e.printStackTrace();
		}

		return new Token(TokenUtils.createToken(user));

	}



}

