package br.com.squadra.bootcamp.projetofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoFormDto {


    @NotNull(message = "O campo codigoBairro não pode ser nulo.")
    private Long codigoBairro;

    @NotNull(message = "O campo codigoPessoa não pode ser nulo.")
    private Long codigoPessoa;

    @NotNull(message = "O campo nomeRua não pode ser nulo.")
    private String nomeRua;

    @NotNull(message = "O campo numero não pode ser nulo.")
    private int numero;

    @NotNull(message = "O campo complemento não pode ser nulo.")
    private String complemento;

    @NotNull(message = "O campo cep não pode ser nulo.")
    private String cep;
}
