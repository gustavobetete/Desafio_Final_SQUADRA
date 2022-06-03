package br.com.squadra.bootcamp.projetofinal.service;

import br.com.squadra.bootcamp.projetofinal.config.objectNotFound.ObjectNotFoundExceptions;
import br.com.squadra.bootcamp.projetofinal.config.unauthorized.UnauthorizedExceptions;
import br.com.squadra.bootcamp.projetofinal.constants.Sigla;
import br.com.squadra.bootcamp.projetofinal.dto.UFDto;
import br.com.squadra.bootcamp.projetofinal.dto.UFFormAtualizarDto;
import br.com.squadra.bootcamp.projetofinal.dto.UFFormDto;
import br.com.squadra.bootcamp.projetofinal.entities.UF;
import br.com.squadra.bootcamp.projetofinal.repository.UFRepository;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Optional<UF> optionalUF = ufRepository.findByNome(uf.getNome());
        if(optionalUF.isPresent()){
            throw new UnauthorizedExceptions("Já existe um estado com o nome " + uf.getNome() + ". Você não pode cadastrar dois estados com o mesmo nome.");
        }
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

    @Override
    public List<UFDto> atualizar(UFFormAtualizarDto ufFormDto){
        Optional<UF> ufOptional = ufRepository.findById(ufFormDto.getCodigoUF());
        if (ufOptional.isPresent()) {
            UF uf = modelMapper.map(ufFormDto, UF.class);
            uf.setNome(uf.getNome().toUpperCase());
            ufRepository.save(uf);
            List<UF> listaUf = ufRepository.findAll();
            return listaUf.stream().map(e -> modelMapper.map(e, UFDto.class)).toList();
        }
        return null;
    }

    @Override
    public List<UFDto> delete(Long codigoUF) throws ObjectNotFoundException {
        UF uf = this.ufRepository.findById(codigoUF)
                .orElseThrow(() -> new ObjectNotFoundExceptions( "UF não encontrado"));
        this.ufRepository.delete(uf);
        List<UF> listarUF = ufRepository.findAll();
        return listarUF.stream().map(e -> modelMapper.map(e, UFDto.class)).toList();
    }
}
