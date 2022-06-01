package br.com.squadra.bootcamp.projetofinal.config;

import br.com.squadra.bootcamp.projetofinal.constants.Sigla;
import org.springframework.core.convert.converter.Converter;

public class ToUpperCaseEnumSigla implements Converter<String, Sigla>{

    @Override
    public Sigla convert(String value){
        return Sigla.valueOf(value.toUpperCase());
    }
}
