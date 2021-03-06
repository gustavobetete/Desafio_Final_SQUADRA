package br.com.squadra.bootcamp.projetofinal.entities;

import br.com.squadra.bootcamp.projetofinal.constants.Sigla;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "TB_UF")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UF {

    @SequenceGenerator(name= "UF_SEQUENCIA", sequenceName = "SEQ_ID", initialValue=1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="UF_SEQUENCIA")
    @Column(name = "CODIGO_UF")
    private Long codigoUF;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Sigla sigla;
    private int status;

    @OneToMany(mappedBy = "uf", cascade = CascadeType.PERSIST)
    private List<Municipio> municipio;
}
