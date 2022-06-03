package br.com.squadra.bootcamp.projetofinal.config.objectNotFound;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StandardError {

    private Integer status;
    private String mensagem;
}
