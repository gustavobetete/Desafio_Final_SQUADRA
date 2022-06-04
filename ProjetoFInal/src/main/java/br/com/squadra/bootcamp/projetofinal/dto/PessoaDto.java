package br.com.squadra.bootcamp.projetofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDto {

    private Long codigoPessoa;

    private String nome;

    private String sobrenome;

    private int idade;

    private String login;

    private String senha;

    private int status;
}
