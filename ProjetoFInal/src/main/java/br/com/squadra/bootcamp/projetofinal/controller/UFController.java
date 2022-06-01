package br.com.squadra.bootcamp.projetofinal.controller;

import br.com.squadra.bootcamp.projetofinal.constants.Sigla;
import br.com.squadra.bootcamp.projetofinal.dto.UFDto;
import br.com.squadra.bootcamp.projetofinal.dto.UFFormDto;
import br.com.squadra.bootcamp.projetofinal.entities.UF;
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
@RequestMapping("/uf")
public class UFController {

    @Autowired
    private UFService ufService;

    @PostMapping
    @Transactional
    public ResponseEntity<List<UFDto>> inserir(@Valid @RequestBody UFFormDto ufFormDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(ufService.inserir(ufFormDto));
    }

    @GetMapping
    public ResponseEntity<Page<UFDto>> listarUF(@PageableDefault(sort = "codigoUF", direction = Sort.Direction.ASC) Pageable paginacao
            , @RequestParam(required = false) Sigla sigla, @RequestParam(required = false) Long codigoUF){
        return ResponseEntity.ok().body(ufService.listarUF(paginacao, sigla, codigoUF));
    }
}
