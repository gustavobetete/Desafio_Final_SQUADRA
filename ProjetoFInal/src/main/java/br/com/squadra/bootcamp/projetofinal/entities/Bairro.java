package br.com.squadra.bootcamp.projetofinal.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "TB_BAIRRO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bairro {

    @SequenceGenerator(name= "BAIRRO_SEQUENCIA", sequenceName = "SEQ_ID", initialValue=1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BAIRRO_SEQUENCIA")
    @Column(name = "CODIGO_BAIRRO")
    private Long codigoBairro;

    private String nome;

    private int status;

    @OneToMany(mappedBy = "bairros")
    private List<Endereco> endereco;

    @ManyToOne
    @JoinColumn(name = "CODIGO_MUNICIPIO")
    private Municipio municipios;
}
