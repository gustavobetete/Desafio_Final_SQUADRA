package br.com.squadra.bootcamp.projetofinal.service;

import br.com.squadra.bootcamp.projetofinal.dto.PessoaDto;
import br.com.squadra.bootcamp.projetofinal.dto.PessoaEnderecoDto;
import br.com.squadra.bootcamp.projetofinal.dto.PessoaFormAtualizarDto;
import br.com.squadra.bootcamp.projetofinal.dto.PessoaFormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PessoaService {

    List<PessoaDto> inserir(PessoaFormDto pessoaFormDto);

    Page<PessoaDto> listarPessoa(Pageable paginacao);

    List<PessoaDto> atualizar(PessoaFormAtualizarDto pessoaFormAtualizarDto);

    List<PessoaDto> delete(Long codigoPessoa);

    PessoaEnderecoDto listarId(Long codigoPessoa);
}
