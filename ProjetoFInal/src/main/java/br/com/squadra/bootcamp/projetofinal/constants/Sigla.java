package br.com.squadra.bootcamp.projetofinal.constants;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Sigla {

    AC("ac"),
    AL("al"),
    AM("am"),
    AP("ap"),
    BA("ba"),
    CE("ce"),
    DF("df"),
    ES("es"),
    GO("go"),
    MA("ma"),
    MT("mt"),
    MS("ms"),
    MG("mg"),
    PA("pa"),
    PB("pb"),
    PR("pr"),
    PE("pe"),
    PI("pi"),
    RJ("rj"),
    RN("rn"),
    RS("rs"),
    RO("ro"),
    RR("rr"),
    SC("sc"),
    SP("sp"),
    SE("se"),
    TO("to");

    Sigla(String value) {
        this.value = value;
    }

    private String value;

    @JsonCreator
    public static Sigla fromValue(String value) {
        for (Sigla sigla : Sigla.values()) {
            if (sigla.value.equalsIgnoreCase(value)) {
                return sigla;
            }
        }
        return null;
    }
}
