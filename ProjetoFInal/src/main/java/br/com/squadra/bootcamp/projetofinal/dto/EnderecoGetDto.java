package br.com.squadra.bootcamp.projetofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoGetDto {

    private Long codigoEndereco;

    private Long codigoBairro;

    private Long codigoPessoa;

    private String nomeRua;

    private int numero;

    private String complemento;

    private String cep;

    private List<BairroGetDto> bairros;
}
