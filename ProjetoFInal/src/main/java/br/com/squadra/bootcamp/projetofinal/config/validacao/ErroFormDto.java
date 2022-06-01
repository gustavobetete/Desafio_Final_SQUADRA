package br.com.squadra.bootcamp.projetofinal.config.validacao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErroFormDto {
    private String nome;
    private String error;

}
