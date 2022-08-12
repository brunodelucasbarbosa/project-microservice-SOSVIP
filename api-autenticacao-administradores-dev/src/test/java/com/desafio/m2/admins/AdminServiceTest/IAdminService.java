package com.desafio.m2.admins.AdminServiceTest;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.desafio.m2.dao.AdminDAO;
import com.desafio.m2.model.Admin;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IAdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Test
    public void getAllAdminTest() {

        Admin admin1 = new Admin(
                "Admin1",
                "admin1@email.com",
                "123456");

        adminDAO.save(admin1);

        Admin admin2 = new Admin(
                "Admin2",
                "admin2@email.com",
                "123456");

        adminDAO.save(admin2);

        List<Admin> admins = new ArrayList<Admin>();
        for (Admin admin : adminDAO.findAll()) {
            admins.add(admin);
        }

        Assertions.assertThat(admins.size()).isEqualTo(2);
    }

    @Test
    public void getAdminByIdTest() {

        Admin admin = new Admin(
                "Admin",
                "admin@email.com",
                "123456");

        adminDAO.save(admin);
        var admin2 = adminDAO.findById(admin.getId());

        Assertions.assertThat(adminDAO.findById(admin.getId())).isNotNull();
        Assertions.assertThat(admin2.get().getId()).isEqualTo(admin.getId());
    }

    @Test
    public void getAdminByEmailTest() {

        Admin admin = new Admin(
                "Admin",
                "admin@email.com",
                "123456");

        adminDAO.save(admin);
        var admin2 = adminDAO.findByEmail("admin@email.com");

        Assertions.assertThat(admin2.getEmail()).isEqualTo("admin@email.com");
    }

    @Test
    public void createAdminTest() {

        Admin admin = new Admin(
                "Admin",
                "admin@email.com",
                "123456");

        adminDAO.save(admin);

        Assertions.assertThat(admin.getName()).isEqualTo("Admin");
        Assertions.assertThat(admin.getEmail()).isEqualTo("admin@email.com");
        Assertions.assertThat(admin.getPassword()).isEqualTo("123456");
    }
}
