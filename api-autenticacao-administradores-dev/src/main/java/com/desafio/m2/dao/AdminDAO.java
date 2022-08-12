package com.desafio.m2.dao;

import com.desafio.m2.dto.AdminDTOResponse;
import com.desafio.m2.model.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdminDAO extends PagingAndSortingRepository<Admin, Long> {
    @Query("SELECT new com.desafio.m2.dto.AdminDTOResponse (admin.id, admin.name, admin.email) FROM Admin as admin")
    Page<AdminDTOResponse> findAllAdmin(Pageable pageable);

    @Query("SELECT new com.desafio.m2.dto.AdminDTOResponse (admin.id, admin.name, admin.email) FROM Admin as admin WHERE admin.id = :id")
    AdminDTOResponse findAdminById(Long id);

    Admin findByEmail(String email);
}
