package com.example.mvcunittest.service;

import com.example.mvcunittest.domain.Livro;
import com.example.mvcunittest.dto.LivroDetailResponseDto;
import com.example.mvcunittest.dto.LivroRequestDto;

import java.util.List;
import java.util.UUID;

public interface LivroService {
    Livro cadastraLivro(LivroRequestDto livroRequestDto);

    List<Livro> getAll();


    LivroDetailResponseDto getLivroById(UUID id);

    Livro alteraLivro(UUID id, LivroRequestDto livroRequestDto);

    void deletaLivro(UUID id);
}
