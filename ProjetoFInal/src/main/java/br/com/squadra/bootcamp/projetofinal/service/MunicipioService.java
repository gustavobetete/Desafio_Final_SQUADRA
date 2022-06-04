package br.com.squadra.bootcamp.projetofinal.service;

import br.com.squadra.bootcamp.projetofinal.dto.MunicipioDto;
import br.com.squadra.bootcamp.projetofinal.dto.MunicipioFormAtualizarDto;
import br.com.squadra.bootcamp.projetofinal.dto.MunicipioFormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MunicipioService {
    List<MunicipioDto> inserir(MunicipioFormDto municipioFormDto);

    Page<MunicipioDto> listarMunicipio(Pageable paginacao);

    List<MunicipioDto> atualizar(MunicipioFormAtualizarDto municipioFormAtualizarDto);

    List<MunicipioDto> delete(Long codigoMunicipio);
}
