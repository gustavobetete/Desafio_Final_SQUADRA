package br.com.squadra.bootcamp.projetofinal.repository;

import br.com.squadra.bootcamp.projetofinal.entities.Bairro;
import br.com.squadra.bootcamp.projetofinal.entities.Municipio;
import br.com.squadra.bootcamp.projetofinal.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByLogin(String login);
}
