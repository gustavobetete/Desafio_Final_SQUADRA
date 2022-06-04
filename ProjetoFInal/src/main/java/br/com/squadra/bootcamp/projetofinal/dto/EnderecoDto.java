package br.com.squadra.bootcamp.projetofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDto {

    private Long codigoEndereco;

    private Long codigoBairro;

    private Long codigoPessoa;

    private String nomeRua;

    private int numero;

    private String complemento;

    private String cep;
}
