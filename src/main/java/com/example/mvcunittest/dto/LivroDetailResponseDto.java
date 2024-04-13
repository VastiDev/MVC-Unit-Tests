package com.example.mvcunittest.dto;

import com.example.mvcunittest.domain.Livro;

import java.time.LocalDate;

public record LivroDetailResponseDto(String titulo, String autor, LocalDate anoPublicacao) {
    public LivroDetailResponseDto(Livro livro) {
        this(livro.getTitulo(), livro.getAutor(), livro.getAnoPublicacao());
    }
}
