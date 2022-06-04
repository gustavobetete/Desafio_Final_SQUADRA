package br.com.squadra.bootcamp.projetofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BairroFormAtualizarDto {

    @NotNull(message = "O campo codigoBairro não pode ser nulo.")
    private Long codigoBairro;

    @NotNull(message = "O campo codigoMunicipio não pode ser nulo.")
    private Long codigoMunicipio;

    @NotNull(message = "O campo nome não pode ser nulo.")
    private String nome;

    @NotNull(message = "O campo status não pode ser nulo.")
    private int status;
}
