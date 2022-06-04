package br.com.squadra.bootcamp.projetofinal.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "TB_PESSOA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    @SequenceGenerator(name= "PESSOA_SEQUENCIA", sequenceName = "SEQ_ID", initialValue=1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PESSOA_SEQUENCIA")
    @Column(name = "CODIGO_PESSOA")
    private Long codigoPessoa;

    private String nome;

    private String sobrenome;

    private int idade;

    private String login;

    private String senha;

    private int status;
}
