package br.com.squadra.bootcamp.projetofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioFormAtualizarDto {

    @NotNull(message = "O campo codigoMunicipio não pode ser nulo.")
    private Long codigoMunicipio;

    @NotNull(message = "O campo codigoUF não pode ser nulo.")
    private Long codigoUF;

    @NotNull(message = "O campo nome não pode ser nulo.")
    private String nome;

    @NotNull(message = "O campo status não pode ser nulo.")
    private int status;
}
