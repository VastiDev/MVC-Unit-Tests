package com.example.mvcunittest.dto;

import com.example.mvcunittest.domain.Livro;

import java.time.LocalDate;

public record LivroUpdatedDto(String titulo, String autor, LocalDate anoPublicacao) {
    public LivroUpdatedDto(Livro alteradoLivro) {
        this(alteradoLivro.getTitulo(), alteradoLivro.getAutor(), alteradoLivro.getAnoPublicacao());
    }
}
