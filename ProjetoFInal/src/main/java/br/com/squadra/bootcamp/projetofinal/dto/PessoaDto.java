package br.com.squadra.bootcamp.projetofinal.dto;

import br.com.squadra.bootcamp.projetofinal.entities.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private List<EnderecoDto> enderecos;

    private int status;

}
