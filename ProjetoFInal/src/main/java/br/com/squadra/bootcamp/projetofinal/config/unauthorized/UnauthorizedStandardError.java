package br.com.squadra.bootcamp.projetofinal.config.unauthorized;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnauthorizedStandardError {

    private Integer status;
    private String mensagem;
}
