package br.com.squadra.bootcamp.projetofinal.entities;

import br.com.squadra.bootcamp.projetofinal.constants.Sigla;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "TB_MUNICIPIO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Municipio {

    @SequenceGenerator(name= "MUNICIPIO_SEQUENCIA", sequenceName = "SEQ_ID", initialValue=1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MUNICIPIO_SEQUENCIA")
    @Column(name = "CODIGO_MUNICIPIO")
    private Long codigoMunicipio;

    private String nome;

    private int status;

    @OneToMany(mappedBy = "municipios", cascade = CascadeType.PERSIST)
    private List<Bairro> bairros;

    @ManyToOne
    @JoinColumn(name = "CODIGO_UF")
    private UF uf;
}
