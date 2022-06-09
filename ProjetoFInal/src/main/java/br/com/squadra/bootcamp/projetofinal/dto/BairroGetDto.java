package br.com.squadra.bootcamp.projetofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BairroGetDto {

    private Long codigoBairro;

    private Long codigoMunicipio;

    private String nome;

    private int status;

    private MunicipioGetDto municipio;
}
