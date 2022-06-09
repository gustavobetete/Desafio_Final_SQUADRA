package br.com.squadra.bootcamp.projetofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaGetDto {

    private Long codigoPessoa;

    private String nome;

    private String sobrenome;

    private int idade;

    private String login;

    private String senha;

    private List<EnderecoGetDto> enderecos;

    private int status;
}
