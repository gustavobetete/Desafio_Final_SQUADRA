package br.com.squadra.bootcamp.projetofinal.dto;

import br.com.squadra.bootcamp.projetofinal.constants.Sigla;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UFFormDto {

    @NotNull(message = "O campo nome não pode ser nulo.")
    @NotEmpty(message = "O campo nome não pode ser vazio.")
    @NotBlank(message = "O campo nome não pode estar em branco.")
    private String nome;

    @NotNull(message = "A sigla não não existe, insira uma sigla existente.")
    private Sigla sigla;

    @NotNull(message = "O campo status não pode ser nulo.")
    private int status;
}
