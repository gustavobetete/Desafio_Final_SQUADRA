package br.com.squadra.bootcamp.projetofinal.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "TB_ENDERECO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    @SequenceGenerator(name= "ENDERECO_SEQUENCIA", sequenceName = "SEQ_ID", initialValue=1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ENDERECO_SEQUENCIA")
    @Column(name = "CODIGO_ENDERECO")
    private Long codigoEndereco;

    @Column(name = "CODIGO_BAIRRO")
    private Long codigoBairro;

    @Column(name = "CODIGO_PESSOA")
    private Long codigoPessoa;

    @Column(name = "NOME_RUA")
    private String nomeRua;

    private int numero;

    private String complemento;

    private String cep;



}
