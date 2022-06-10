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

import java.util.*;
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
        List<Endereco> enderecos = new ArrayList<>();

        for(int i = 0 ; i < pessoaFormDto.getEnderecos().size(); i++){
            enderecos.add(modelMapper.map(pessoaFormDto.getEnderecos().get(i), Endereco.class));
        }

        for(int i = 0; i < enderecos.size(); i++){
            enderecos.get(i).setPessoas(pessoa);
            enderecos.get(i).setBairro(bairroRepository.getById(pessoaFormDto.getEnderecos().get(i).getCodigoBairro()));
        }

        enderecoRepository.saveAll(enderecos);

        //exceptions personalizada caso exista um usario com o mesmo nome.
//        Optional<Pessoa> optionalPessoa = pessoaRepository.findByLogin(pessoa.getLogin());
//        if(optionalPessoa.isPresent()){
//            throw new UnauthorizedExceptions("Já existe um usuario com o login " + pessoa.getLogin() + ". Você não pode cadastrar dois usuarios com o mesmo login.");
//        }

        pessoaRepository.save(pessoa);
        List<Pessoa> listaPessoa = pessoaRepository.findAll();
        List<PessoaDto> pessoasDto = listaPessoa.stream().map(e -> modelMapper.map(e, PessoaDto.class)).toList();
        pessoasDto.stream().forEach(e -> e.setEnderecos(Collections.emptyList()));
        return pessoasDto;
    }

    @Override
    public List<PessoaDto> listarPessoa(Pageable paginacao) {
        Page<Pessoa> pessoas = pessoaRepository.findAll(paginacao);
        Page<PessoaDto> pessoaDto = new PageImpl<>(pessoas.stream().map(e -> modelMapper.map(e, PessoaDto.class)).collect(Collectors.toList()));

        List<PessoaDto> listaPessoaDtoObjeto = pessoaDto.getContent();
        listaPessoaDtoObjeto.stream().forEach(e -> e.setEnderecos(Collections.emptyList()));
        return listaPessoaDtoObjeto;
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

        //pessoa.getEnderecos().add(enderecoRepository.(codigoPessoa));
        PessoaGetDto pessoaGetDto = modelMapper.map(pessoa, PessoaGetDto.class);


        for(int i = 0; i < pessoaGetDto.getEnderecos().size(); i++){
            pessoaGetDto.getEnderecos().get(i).setCodigoPessoa(pessoa.getCodigoPessoa());
            pessoaGetDto.getEnderecos().get(i).setBairros(List.of(modelMapper.map(pessoa.getEnderecos().get(i).getBairro(), BairroGetDto.class)));
            for(int j = 0; j < pessoaGetDto.getEnderecos().get(i).getBairros().size(); j++){
                for(int x = 0; x < pessoa.getEnderecos().size(); x ++) {
                    pessoaGetDto.getEnderecos().get(i).getBairros().get(j).setCodigoMunicipio(pessoa.getEnderecos().get(x).getBairro().getMunicipios().getCodigoMunicipio());
                    pessoaGetDto.getEnderecos().get(i).getBairros().get(j).setMunicipio(modelMapper.map(pessoa.getEnderecos().get(x).getBairro().getMunicipios(), MunicipioGetDto.class));
                }
            }
        }

        return pessoaGetDto;

    }
}
