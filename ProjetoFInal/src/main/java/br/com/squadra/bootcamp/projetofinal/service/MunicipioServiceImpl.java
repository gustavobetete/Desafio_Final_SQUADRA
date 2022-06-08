package br.com.squadra.bootcamp.projetofinal.service;

import br.com.squadra.bootcamp.projetofinal.config.objectNotFound.ObjectNotFoundExceptions;
import br.com.squadra.bootcamp.projetofinal.config.unauthorized.UnauthorizedExceptions;
import br.com.squadra.bootcamp.projetofinal.dto.MunicipioDto;
import br.com.squadra.bootcamp.projetofinal.dto.MunicipioFormAtualizarDto;
import br.com.squadra.bootcamp.projetofinal.dto.MunicipioFormDto;
import br.com.squadra.bootcamp.projetofinal.entities.Municipio;
import br.com.squadra.bootcamp.projetofinal.entities.UF;
import br.com.squadra.bootcamp.projetofinal.repository.MunicipioRepository;
import br.com.squadra.bootcamp.projetofinal.repository.UFRepository;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MunicipioServiceImpl implements MunicipioService{

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private UFRepository ufRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MunicipioDto> inserir(MunicipioFormDto municipioFormDto) {
        Municipio municipio = modelMapper.map(municipioFormDto, Municipio.class);
        municipio.setNome(municipio.getNome().toUpperCase());
        Optional<UF> uf = ufRepository.findByCodigoUF(municipio.getUf().getCodigoUF());

        Optional<Municipio> optionalMunicipio = municipioRepository.findByNome(municipio.getNome());
        if(optionalMunicipio.isPresent()){
            throw new UnauthorizedExceptions("Já existe um municipio com o nome " + municipio.getNome() + ". Você não pode cadastrar dois municipio com o mesmo nome.");
        }
        municipioRepository.save(municipio);
        List<Municipio> listaMunicipio = municipioRepository.findAll();
        return listaMunicipio.stream().map(e -> modelMapper.map(e, MunicipioDto.class)).toList();
    }

    @Override
    public Page<MunicipioDto> listarMunicipio(Pageable paginacao) {
        Page<Municipio> municipio = municipioRepository.findAll(paginacao);
        Page<MunicipioDto> municipioDto = new PageImpl<>(municipio.stream().map(e -> modelMapper.map(e, MunicipioDto.class)).collect(Collectors.toList()));
        return municipioDto;
    }

    @Override
    public List<MunicipioDto> atualizar(MunicipioFormAtualizarDto municipioFormAtualizarDto){
        Optional<Municipio> MunicipioOptional = municipioRepository.findById(municipioFormAtualizarDto.getCodigoMunicipio());
        if (MunicipioOptional.isPresent()) {
            Municipio municipio = modelMapper.map(municipioFormAtualizarDto, Municipio.class);
            municipio.setNome(municipio.getNome().toUpperCase());
            municipioRepository.save(municipio);
            List<Municipio> listaMunicipio = municipioRepository.findAll();
            return listaMunicipio.stream().map(e -> modelMapper.map(e, MunicipioDto.class)).toList();
        }
        return null;
    }

    @Override
    public List<MunicipioDto> delete(Long codigoMunicipio) throws ObjectNotFoundException {
        Municipio municipio = this.municipioRepository.findById(codigoMunicipio)
                .orElseThrow(() -> new ObjectNotFoundExceptions( "Municipio não encontrado"));
        this.municipioRepository.delete(municipio);
        List<Municipio> listaMunicipio = municipioRepository.findAll();
        return listaMunicipio.stream().map(e -> modelMapper.map(e, MunicipioDto.class)).toList();
    }
}
