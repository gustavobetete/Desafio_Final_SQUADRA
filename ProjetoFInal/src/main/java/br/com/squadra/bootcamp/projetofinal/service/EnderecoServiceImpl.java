package br.com.squadra.bootcamp.projetofinal.service;

import br.com.squadra.bootcamp.projetofinal.config.objectNotFound.ObjectNotFoundExceptions;
import br.com.squadra.bootcamp.projetofinal.dto.EnderecoDto;
import br.com.squadra.bootcamp.projetofinal.dto.EnderecoFormAtualizarDto;
import br.com.squadra.bootcamp.projetofinal.dto.EnderecoFormDto;
import br.com.squadra.bootcamp.projetofinal.entities.Bairro;
import br.com.squadra.bootcamp.projetofinal.entities.Endereco;
import br.com.squadra.bootcamp.projetofinal.entities.Pessoa;
import br.com.squadra.bootcamp.projetofinal.repository.BairroRepository;
import br.com.squadra.bootcamp.projetofinal.repository.EnderecoRepository;
import br.com.squadra.bootcamp.projetofinal.repository.PessoaRepository;
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
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BairroRepository bairroRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EnderecoDto> inserir(EnderecoFormDto enderecoFormDto) {
        Endereco endereco = modelMapper.map(enderecoFormDto, Endereco.class);
        endereco.setNomeRua(endereco.getNomeRua().toUpperCase());
        Pessoa pessoa = pessoaRepository.getById(enderecoFormDto.getCodigoPessoa());
        Bairro bairro = bairroRepository.getById(enderecoFormDto.getCodigoBairro());
        endereco.setPessoas(pessoa);
        endereco.setBairro(bairro);
        enderecoRepository.save(endereco);
        List<Endereco> listaEndereco = enderecoRepository.findAll();
        return listaEndereco.stream().map(e -> modelMapper.map(e, EnderecoDto.class)).toList();
    }

    @Override
    public Page<EnderecoDto> listarEndereco(Pageable paginacao) {
        Page<Endereco> endereco = enderecoRepository.findAll(paginacao);
        Page<EnderecoDto> enderecoDto = new PageImpl<>(endereco.stream().map(e -> modelMapper.map(e, EnderecoDto.class)).collect(Collectors.toList()));
        return enderecoDto;
    }

    @Override
    public List<EnderecoDto> atualizar(EnderecoFormAtualizarDto enderecoFormAtualizarDto){
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(enderecoFormAtualizarDto.getCodigoEndereco());
        if (enderecoOptional.isPresent()) {
            Endereco endereco = modelMapper.map(enderecoFormAtualizarDto, Endereco.class);
            endereco.setNomeRua(endereco.getNomeRua().toUpperCase());
            endereco.setComplemento(endereco.getComplemento().toUpperCase());
            enderecoRepository.save(endereco);
            List<Endereco> listaEndereco = enderecoRepository.findAll();
            return listaEndereco.stream().map(e -> modelMapper.map(e, EnderecoDto.class)).toList();
        }
        return null;
    }

    @Override
    public List<EnderecoDto> delete(Long codigoEndereco) throws ObjectNotFoundException {
        Endereco endereco = this.enderecoRepository.findById(codigoEndereco)
                .orElseThrow(() -> new ObjectNotFoundExceptions( "Endere??o n??o encontrado"));
        this.enderecoRepository.delete(endereco);
        List<Endereco> listaEndereco = enderecoRepository.findAll();
        return listaEndereco.stream().map(e -> modelMapper.map(e, EnderecoDto.class)).toList();
    }
}
