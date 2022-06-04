package br.com.squadra.bootcamp.projetofinal.controller;

import br.com.squadra.bootcamp.projetofinal.dto.MunicipioDto;
import br.com.squadra.bootcamp.projetofinal.dto.MunicipioFormAtualizarDto;
import br.com.squadra.bootcamp.projetofinal.dto.MunicipioFormDto;
import br.com.squadra.bootcamp.projetofinal.service.MunicipioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/municipio")
public class MunicipioController {

    @Autowired
    private MunicipioService municipioService;

    @PostMapping
    @Transactional
    public ResponseEntity<List<MunicipioDto>> inserir(@Valid @RequestBody MunicipioFormDto municipioFormDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(municipioService.inserir(municipioFormDto));
    }

    @GetMapping
    public ResponseEntity<Page<MunicipioDto>> listarMunicipio(@PageableDefault(sort = "codigoMunicipio", direction = Sort.Direction.ASC) Pageable paginacao){
        return ResponseEntity.ok().body(municipioService.listarMunicipio(paginacao));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<List<MunicipioDto>> atualizar(@RequestBody @Valid MunicipioFormAtualizarDto municipioFormAtualizarDto){
        return ResponseEntity.ok().body(municipioService.atualizar(municipioFormAtualizarDto));
    }

    @DeleteMapping(path = "/{codigoMunicipio}")
    public ResponseEntity<List<MunicipioDto>> delete(@PathVariable Long codigoMunicipio) {
        return ResponseEntity.ok().body(municipioService.delete(codigoMunicipio));
    }
}
