package br.com.squadra.bootcamp.projetofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BairroFormDto {

    @NotNull(message = "O campo codigoMunicipio não pode ser nulo.")
    private Long codigoMunicipio;

    @NotNull(message = "O campo nome não pode ser nulo.")
    @NotEmpty(message = "O campo nome não pode ser vazio.")
    @NotBlank(message = "O campo nome não pode estar em branco.")
    private String nome;

    @NotNull(message = "O campo status não pode ser nulo.")
    private int status;
}
