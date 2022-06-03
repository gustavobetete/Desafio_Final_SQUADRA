package br.com.squadra.bootcamp.projetofinal.config.unauthorized;

public class UnauthorizedExceptions extends RuntimeException{

    public UnauthorizedExceptions(String msg){
        super(msg);
    }
}
