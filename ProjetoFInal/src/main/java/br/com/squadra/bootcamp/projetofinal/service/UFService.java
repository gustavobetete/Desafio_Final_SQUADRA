package br.com.squadra.bootcamp.projetofinal.service;

import br.com.squadra.bootcamp.projetofinal.constants.Sigla;
import br.com.squadra.bootcamp.projetofinal.dto.UFDto;
import br.com.squadra.bootcamp.projetofinal.dto.UFFormAtualizarDto;
import br.com.squadra.bootcamp.projetofinal.dto.UFFormDto;
import br.com.squadra.bootcamp.projetofinal.repository.UFRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UFService {

    List<UFDto> inserir(UFFormDto ufFormDto);
    Page<UFDto> listarUF(Pageable paginacao);

    List<UFDto> atualizar(UFFormAtualizarDto ufFormDto);

    List<UFDto> delete(Long codigoUF);
}
