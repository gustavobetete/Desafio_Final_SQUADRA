package br.com.squadra.bootcamp.projetofinal.controller;

import br.com.squadra.bootcamp.projetofinal.dto.*;
import br.com.squadra.bootcamp.projetofinal.service.BairroService;
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
@RequestMapping("/bairro")
public class BairroController {

    @Autowired
    private BairroService bairroService;

    @PostMapping
    @Transactional
    public ResponseEntity<List<BairroDto>> inserir(@Valid @RequestBody BairroFormDto bairroFormDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bairroService.inserir(bairroFormDto));
    }

    @GetMapping
    public ResponseEntity<Page<BairroDto>> listarBairro(@PageableDefault(sort = "codigoBairro", direction = Sort.Direction.ASC) Pageable paginacao){
        return ResponseEntity.ok().body(bairroService.listarBairro(paginacao));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<List<BairroDto>> atualizar(@RequestBody @Valid BairroFormAtualizarDto bairroFormAtualizarDto){
        return ResponseEntity.ok().body(bairroService.atualizar(bairroFormAtualizarDto));
    }

    @DeleteMapping(path = "/{codigoBairro}")
    public ResponseEntity<List<BairroDto>> delete(@PathVariable Long codigoBairro) {
        return ResponseEntity.ok().body(bairroService.delete(codigoBairro));
    }
}
