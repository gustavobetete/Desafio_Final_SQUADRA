package br.com.squadra.bootcamp.projetofinal.controller;

import br.com.squadra.bootcamp.projetofinal.dto.*;
import br.com.squadra.bootcamp.projetofinal.service.PessoaService;
import br.com.squadra.bootcamp.projetofinal.service.UFService;
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
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    @Transactional
    public ResponseEntity<List<PessoaDto>> inserir(@Valid @RequestBody PessoaFormDto pessoaFormDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.inserir(pessoaFormDto));
    }

    @GetMapping
    public ResponseEntity<Page<PessoaDto>> listarPessoa(@PageableDefault(sort = "codigoPessoa", direction = Sort.Direction.ASC) Pageable paginacao){
        return ResponseEntity.ok().body(pessoaService.listarPessoa(paginacao));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<List<PessoaDto>> atualizar(@RequestBody @Valid PessoaFormAtualizarDto pessoaFormAtualizarDto){
        return ResponseEntity.ok().body(pessoaService.atualizar(pessoaFormAtualizarDto));
    }

    @DeleteMapping(path = "/{codigoPessoa}")
    public ResponseEntity<List<PessoaDto>> delete(@PathVariable Long codigoPessoa) {
        return ResponseEntity.ok().body(pessoaService.delete(codigoPessoa));
    }
}
