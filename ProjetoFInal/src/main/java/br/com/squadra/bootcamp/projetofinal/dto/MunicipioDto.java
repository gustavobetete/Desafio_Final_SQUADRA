package br.com.squadra.bootcamp.projetofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioDto {

    private Long codigoMunicipio;

    private Long codigoUF;

    private String nome;

    private int status;
}
