package br.com.squadra.bootcamp.projetofinal.service;

import br.com.squadra.bootcamp.projetofinal.dto.BairroDto;
import br.com.squadra.bootcamp.projetofinal.dto.BairroFormAtualizarDto;
import br.com.squadra.bootcamp.projetofinal.dto.BairroFormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BairroService {

    List<BairroDto> inserir(BairroFormDto bairroFormDto);

    Page<BairroDto> listarBairro(Pageable paginacao);

    List<BairroDto> atualizar(BairroFormAtualizarDto bairroFormAtualizarDto);

    List<BairroDto> delete(Long codigoBairro);
}
