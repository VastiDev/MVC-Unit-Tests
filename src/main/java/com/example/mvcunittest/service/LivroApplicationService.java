package com.example.mvcunittest.service;

import com.example.mvcunittest.domain.Livro;
import com.example.mvcunittest.dto.LivroDetailResponseDto;
import com.example.mvcunittest.dto.LivroRequestDto;
import com.example.mvcunittest.exceptions.LivroNotFoundException;
import com.example.mvcunittest.repository.LivroSpringDataJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LivroApplicationService implements LivroService {
    private final LivroSpringDataJpaRepository livroRepository;

    @Override
    public Livro cadastraLivro(LivroRequestDto livroRequestDto) {
       Livro livro = new Livro(livroRequestDto);
       return livroRepository.save(livro);
    }

    @Override
    public List<Livro> getAll() {
        return livroRepository.findAll();
    }

    @Override
    public LivroDetailResponseDto getLivroById(UUID id) {
        Livro livro = encontrarLivroPorId(id);
        return new LivroDetailResponseDto(livro);
    }

    @Override
    public Livro alteraLivro(UUID id, LivroRequestDto livroRequestDto) {
        Livro livroExixtente = encontrarLivroPorId(id);
        livroExixtente.atualizaCom(livroRequestDto);
        return livroRepository.save(livroExixtente);
    }

    @Override
    public void deletaLivro(UUID id) {
        Livro livro = encontrarLivroPorId(id);
        livroRepository.delete(livro);
    }

    private Livro encontrarLivroPorId(UUID id){
        return livroRepository.findById(id).orElseThrow(()-> new LivroNotFoundException(id));
    }
}
