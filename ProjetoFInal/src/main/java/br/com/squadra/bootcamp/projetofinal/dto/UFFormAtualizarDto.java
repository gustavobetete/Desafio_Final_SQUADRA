package br.com.squadra.bootcamp.projetofinal.dto;

import br.com.squadra.bootcamp.projetofinal.constants.Sigla;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UFFormAtualizarDto {

    @NotNull(message = "O campo codigoUF não pode ser nulo.")
    private Long codigoUF;

    @NotNull(message = "O campo nome não pode ser nulo.")
    private String nome;

    @NotNull(message = "O campo sigla não pode ser nulo.")
    private Sigla sigla;

    @NotNull(message = "O campo status não pode ser nulo.")
    private int status;
}
