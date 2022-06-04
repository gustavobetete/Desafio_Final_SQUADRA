package br.com.squadra.bootcamp.projetofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BairroDto {

    private Long codigoBairro;

    private Long codigoMunicipio;

    private String nome;

    private int status;
}
