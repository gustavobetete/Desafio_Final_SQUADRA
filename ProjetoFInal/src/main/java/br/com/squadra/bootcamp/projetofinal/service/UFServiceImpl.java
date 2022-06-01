package br.com.squadra.bootcamp.projetofinal.service;

import br.com.squadra.bootcamp.projetofinal.constants.Sigla;
import br.com.squadra.bootcamp.projetofinal.dto.UFDto;
import br.com.squadra.bootcamp.projetofinal.dto.UFFormDto;
import br.com.squadra.bootcamp.projetofinal.entities.UF;
import br.com.squadra.bootcamp.projetofinal.repository.UFRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UFServiceImpl implements UFService{

    @Autowired
    private UFRepository ufRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UFDto> inserir(UFFormDto ufFormDto) {
        UF uf = modelMapper.map(ufFormDto, UF.class);
        uf.setNome(uf.getNome().toUpperCase());
        ufRepository.save(uf);
        List<UF> listaUf = ufRepository.findAll();
        return listaUf.stream().map(e -> modelMapper.map(e, UFDto.class)).toList();
    }

    @Override
    public Page<UFDto> listarUF(Pageable paginacao, Sigla sigla, Long codigoUF) {
        Page<UF> uf = ufRepository.findAll(paginacao);
        Page<UFDto> ufDto = new PageImpl<>(uf.stream().map(e -> modelMapper.map(e, UFDto.class)).collect(Collectors.toList()));
        return ufDto;
    }
}
