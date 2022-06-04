package br.com.squadra.bootcamp.projetofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaFormAtualizarDto {

    @NotNull(message = "O campo codigoPessoa não pode ser nulo.")
    private Long codigoPessoa;

    @NotNull(message = "O campo nome não pode ser nulo.")
    @NotEmpty(message = "O campo nome não pode ser vazio.")
    @NotBlank(message = "O campo nome não pode estar em branco.")
    private String nome;

    @NotNull(message = "O campo sobrenome não pode ser nulo.")
    private String sobrenome;

    @NotNull(message = "O campo idade não pode ser nulo.")
    private int idade;

    @NotNull(message = "O campo login não pode ser nulo.")
    private String login;

    @NotNull(message = "O campo senha não pode ser nulo.")
    private String senha;

    @NotNull(message = "O campo status não pode ser nulo.")
    private int status;
}
