package br.com.squadra.bootcamp.projetofinal.service;

import br.com.squadra.bootcamp.projetofinal.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PessoaService {

    List<PessoaDto> inserir(PessoaFormDto pessoaFormDto);

    List<PessoaDto> listarPessoa(Pageable paginacao);

    List<PessoaDto> atualizar(PessoaFormAtualizarDto pessoaFormAtualizarDto);

    List<PessoaDto> delete(Long codigoPessoa);

    PessoaGetDto listarCodigoPessoa(Long codigoPessoa);
}
