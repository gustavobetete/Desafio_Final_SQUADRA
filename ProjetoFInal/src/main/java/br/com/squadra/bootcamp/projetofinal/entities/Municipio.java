package br.com.squadra.bootcamp.projetofinal.entities;

import br.com.squadra.bootcamp.projetofinal.constants.Sigla;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "TB_MUNICIPIO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Municipio {

    @SequenceGenerator(name= "MUNICIPIO_SEQUENCIA", sequenceName = "SEQ_ID", initialValue=1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MUNICIPIO_SEQUENCIA")
    @Column(name = "CODIGO_MUNICIPIO")
    private Long codigoMunicipio;

    @Column(name = "CODIGO_UF")
    private Long codigoUF;

    private String nome;

    private int status;

//    @ManyToOne
//    private UF uf;
}
