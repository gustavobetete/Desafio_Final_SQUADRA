package br.com.squadra.bootcamp.projetofinal.dto;

import br.com.squadra.bootcamp.projetofinal.constants.Sigla;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UFDto {


    private Long codigoUF;
    private String nome;
    private Sigla sigla;
    private int status;
}
