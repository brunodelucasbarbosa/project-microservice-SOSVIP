package br.com.sosvip.apigerenciamentoclientes.dao;

import br.com.sosvip.apigerenciamentoclientes.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDAO extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
    Client findByCpf(String cpf);
}
