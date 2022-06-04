package br.com.squadra.bootcamp.projetofinal.service;

import br.com.squadra.bootcamp.projetofinal.dto.EnderecoDto;
import br.com.squadra.bootcamp.projetofinal.dto.EnderecoFormAtualizarDto;
import br.com.squadra.bootcamp.projetofinal.dto.EnderecoFormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EnderecoService {

    List<EnderecoDto> inserir(EnderecoFormDto enderecoFormDto);

    Page<EnderecoDto> listarEndereco(Pageable paginacao);

    List<EnderecoDto> atualizar(EnderecoFormAtualizarDto enderecoFormAtualizarDto);

    List<EnderecoDto> delete(Long codigoEndereco);
}
