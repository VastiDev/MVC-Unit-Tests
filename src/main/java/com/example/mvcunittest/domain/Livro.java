package com.example.mvcunittest.domain;

import com.example.mvcunittest.dto.LivroRequestDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "livros")
@NoArgsConstructor
@Getter
@ToString
@AllArgsConstructor
public class Livro {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "idLivro", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String autor;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate anoPublicacao;

    public Livro(LivroRequestDto livroRequestDto) {
        this.titulo = livroRequestDto.titulo();
        this.autor = livroRequestDto.autor();
        this.anoPublicacao = livroRequestDto.anoPublicacao();
    }

    public void atualizaCom(LivroRequestDto livroRequestDto) {
        this.titulo = livroRequestDto.titulo();
        this.autor = livroRequestDto.autor();
        this.anoPublicacao = livroRequestDto.anoPublicacao();
    }
}
