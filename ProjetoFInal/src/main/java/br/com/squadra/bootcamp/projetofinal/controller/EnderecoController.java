package br.com.squadra.bootcamp.projetofinal.controller;

import br.com.squadra.bootcamp.projetofinal.dto.*;
import br.com.squadra.bootcamp.projetofinal.service.BairroService;
import br.com.squadra.bootcamp.projetofinal.service.EnderecoService;
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
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    @Transactional
    public ResponseEntity<List<EnderecoDto>> inserir(@Valid @RequestBody EnderecoFormDto enderecoFormDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.inserir(enderecoFormDto));
    }

    @GetMapping
    public ResponseEntity<Page<EnderecoDto>> listarEndereco(@PageableDefault(sort = "codigoEndereco", direction = Sort.Direction.ASC) Pageable paginacao){
        return ResponseEntity.ok().body(enderecoService.listarEndereco(paginacao));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<List<EnderecoDto>> atualizar(@RequestBody @Valid EnderecoFormAtualizarDto enderecoFormAtualizarDto){
        return ResponseEntity.ok().body(enderecoService.atualizar(enderecoFormAtualizarDto));
    }

    @DeleteMapping(path = "/{codigoEndereco}")
    public ResponseEntity<List<EnderecoDto>> delete(@PathVariable Long codigoEndereco) {
        return ResponseEntity.ok().body(enderecoService.delete(codigoEndereco));
    }
}
