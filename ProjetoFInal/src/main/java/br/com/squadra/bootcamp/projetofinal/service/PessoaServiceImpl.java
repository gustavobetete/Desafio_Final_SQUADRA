package br.com.squadra.bootcamp.projetofinal.service;

import br.com.squadra.bootcamp.projetofinal.config.objectNotFound.ObjectNotFoundExceptions;
import br.com.squadra.bootcamp.projetofinal.config.unauthorized.UnauthorizedExceptions;
import br.com.squadra.bootcamp.projetofinal.dto.*;
import br.com.squadra.bootcamp.projetofinal.entities.*;
import br.com.squadra.bootcamp.projetofinal.repository.*;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaServiceImpl implements PessoaService{

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BairroRepository bairroRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PessoaDto> inserir(PessoaFormDto pessoaFormDto) {
        Pessoa pessoa = modelMapper.map(pessoaFormDto, Pessoa.class);
        pessoa.setNome(pessoa.getNome().toUpperCase());
        pessoa.setSobrenome(pessoa.getSobrenome().toUpperCase());
        List<Endereco> enderecos = pessoaFormDto.getEnderecos();

        enderecoRepository.saveAll(enderecos);

        //exceptions personalizada caso exista um usario com o mesmo nome.
        Optional<Pessoa> optionalPessoa = pessoaRepository.findByLogin(pessoa.getLogin());
        if(optionalPessoa.isPresent()){
            throw new UnauthorizedExceptions("Já existe um usuario com o login " + pessoa.getLogin() + ". Você não pode cadastrar dois usuarios com o mesmo login.");
        }

        pessoaRepository.save(pessoa);
        List<Pessoa> listaPessoa = pessoaRepository.findAll();
        return listaPessoa.stream().map(e -> modelMapper.map(e, PessoaDto.class)).toList();
    }

    @Override
    public List<PessoaDto> listarPessoa(Pageable paginacao) {
        Page<Pessoa> pessoas = pessoaRepository.findAll(paginacao);
        Page<PessoaDto> pessoaDto = new PageImpl<>(pessoas.stream().map(e -> modelMapper.map(e, PessoaDto.class)).collect(Collectors.toList()));
        return pessoaDto.getContent();
    }

    @Override
    public List<PessoaDto> atualizar(PessoaFormAtualizarDto pessoaFormAtualizarDto){
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(pessoaFormAtualizarDto.getCodigoPessoa());
        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = modelMapper.map(pessoaFormAtualizarDto, Pessoa.class);
            pessoa.setNome(pessoa.getNome().toUpperCase());
            pessoaRepository.save(pessoa);
            List<Pessoa> listaPessoa = pessoaRepository.findAll();
            return listaPessoa.stream().map(e -> modelMapper.map(e, PessoaDto.class)).toList();
        }
        return null;
    }

    @Override
    public List<PessoaDto> delete(Long codigoPessoa) throws ObjectNotFoundException {
        Pessoa pessoa = this.pessoaRepository.findById(codigoPessoa)
                .orElseThrow(() -> new ObjectNotFoundExceptions( "Pessoa não encontrado"));
        this.pessoaRepository.delete(pessoa);
        List<Pessoa> listaPessoa = pessoaRepository.findAll();
        return listaPessoa.stream().map(e -> modelMapper.map(e, PessoaDto.class)).toList();
    }

    @Override
    public PessoaGetDto listarCodigoPessoa(Long codigoPessoa){

        Pessoa pessoa = this.pessoaRepository.findById(codigoPessoa)
                .orElseThrow(() -> new ObjectNotFoundExceptions( "Pessoa não encontrado"));
        return modelMapper.map(pessoa, PessoaGetDto.class);

    }
}
