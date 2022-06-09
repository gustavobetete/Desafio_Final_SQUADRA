package br.com.squadra.bootcamp.projetofinal.service;

import br.com.squadra.bootcamp.projetofinal.config.objectNotFound.ObjectNotFoundExceptions;
import br.com.squadra.bootcamp.projetofinal.config.unauthorized.UnauthorizedExceptions;
import br.com.squadra.bootcamp.projetofinal.dto.*;
import br.com.squadra.bootcamp.projetofinal.entities.Bairro;
import br.com.squadra.bootcamp.projetofinal.entities.Municipio;
import br.com.squadra.bootcamp.projetofinal.entities.UF;
import br.com.squadra.bootcamp.projetofinal.repository.BairroRepository;
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
public class BairroServiceImpl implements BairroService{

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BairroDto> inserir(BairroFormDto bairroFormDto) {
        Bairro bairro = modelMapper.map(bairroFormDto, Bairro.class);
        bairro.setNome(bairro.getNome().toUpperCase());
        Municipio municipio = modelMapper.map(municipioRepository.findByCodigoMunicipio(bairro.getMunicipios().getCodigoMunicipio()), Municipio.class);

        Optional<Bairro> optionalBairro = bairroRepository.findByNome(bairro.getNome());
        if(optionalBairro.isPresent()){
            throw new UnauthorizedExceptions("Já existe um bairro com o nome " + bairro.getNome() + ". Você não pode cadastrar dois bairros com o mesmo nome.");
        }
        bairroRepository.save(bairro);
        municipio.setBairros(bairro.getMunicipios().getBairros());

        List<Bairro> listaBairro = bairroRepository.findAll();
        List<BairroDto> listaBairroDto = listaBairro.stream().map(e -> modelMapper.map(e, BairroDto.class)).toList();
        for(int i = 0; i < listaBairroDto.size(); i++){
            listaBairroDto.get(i).setCodigoMunicipio(listaBairro.get(i).getMunicipios().getCodigoMunicipio());
        }

        return listaBairroDto;
    }

    @Override
    public Page<BairroDto> listarBairro(Pageable paginacao) {
        Page<Bairro> bairro = bairroRepository.findAll(paginacao);
//        Page<BairroDto> bairroDto = new PageImpl<>(bairro.stream().map(e -> modelMapper.map(e, BairroDto.class)).collect(Collectors.toList()));
//        return bairroDto;
        List<Bairro> listaBairro = bairro.getContent();
        List<BairroDto> listaBairroDto = listaBairro.stream().map(e -> modelMapper.map(e, BairroDto.class)).toList();
        for(int i = 0; i < listaBairroDto.size(); i++){
            listaBairroDto.get(i).setCodigoMunicipio(listaBairro.get(i).getMunicipios().getCodigoMunicipio());
        }

        return new PageImpl<>(listaBairroDto);
    }

    @Override
    public List<BairroDto> atualizar(BairroFormAtualizarDto bairroFormAtualizarDto){
        Optional<Bairro> bairroOptional = bairroRepository.findById(bairroFormAtualizarDto.getCodigoBairro());
        if (bairroOptional.isPresent()) {
            Bairro bairro = modelMapper.map(bairroFormAtualizarDto, Bairro.class);
            bairro.setNome(bairro.getNome().toUpperCase());
            bairroRepository.save(bairro);
            List<Bairro> listaBairro = bairroRepository.findAll();
            return listaBairro.stream().map(e -> modelMapper.map(e, BairroDto.class)).toList();
        }
        return null;
    }

    @Override
    public List<BairroDto> delete(Long codigoBairro) throws ObjectNotFoundException {
        Bairro bairro = this.bairroRepository.findById(codigoBairro)
                .orElseThrow(() -> new ObjectNotFoundExceptions( "Bairro não encontrado"));
        this.bairroRepository.delete(bairro);
        List<Bairro> listaBairro = bairroRepository.findAll();
        return listaBairro.stream().map(e -> modelMapper.map(e, BairroDto.class)).toList();
    }
}
