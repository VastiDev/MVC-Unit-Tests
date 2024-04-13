package com.example.mvcunittest.service;

import com.example.mvcunittest.domain.Livro;
import com.example.mvcunittest.dto.LivroDetailResponseDto;
import com.example.mvcunittest.dto.LivroRequestDto;
import com.example.mvcunittest.dto.LivroResponseDto;
import com.example.mvcunittest.dto.LivroUpdatedDto;
import com.example.mvcunittest.repository.LivroSpringDataJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LivroApplicationServiceTest {

    @Mock
    private LivroSpringDataJpaRepository livroRepository;

    @InjectMocks
    private LivroApplicationService livroService;

    private UUID generatedId;
    private LocalDate dateForBook1;
    private Livro livro;

    @BeforeEach
    void setUp() {
        // Inicializa variáveis antes de cada teste
        generatedId = UUID.randomUUID();
        dateForBook1 = LocalDate.of(1995, 5, 15);
    }

    @Test
    void cadastraLivro() {
        LivroRequestDto livroRequestDto = new LivroRequestDto("titulo1", "autor1", dateForBook1);
        Livro mockedLivro = new Livro(generatedId, "titulo1", "autor1", dateForBook1 );

        when(livroRepository.save(any(Livro.class))).thenReturn(mockedLivro);

        Livro livroCriado = livroService.cadastraLivro(livroRequestDto);

        // Verificações
        assertNotNull(livroCriado);
        assertEquals(generatedId, livroCriado.getId());
        verify(livroRepository).save(any(Livro.class));  // Verifica se o método save foi chamado


    }

    @Test
    void getAll() {
        LocalDate dateForBook2 = LocalDate.of(2003, 7, 20); // 20 de julho de 2003

        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        Livro livro1 = new Livro(id1, "titulo1", "autor1", dateForBook1);
        Livro livro2 = new Livro(id2, "titulo2", "author2", dateForBook2);

        when(livroRepository.findAll()).thenReturn(List.of(livro1, livro2));

        List<Livro> livros = livroService.getAll();

        assertNotNull(livros);
        assertEquals(2, livros.size());
        assertEquals(dateForBook1, livros.get(0).getAnoPublicacao());
        assertEquals(dateForBook2, livros.get(1).getAnoPublicacao());
        verify(livroRepository).findAll();
    }



    @Test
    void getLivroById() {
        Livro livroAchado = new Livro(generatedId, "titulo1", "autor1", dateForBook1);

        when(livroRepository.findById(generatedId)).thenReturn(Optional.of(livroAchado));

        LivroDetailResponseDto result = livroService.getLivroById(generatedId);

        assertNotNull(result);
        assertEquals("titulo1", result.titulo());
        assertEquals(dateForBook1, result.anoPublicacao());

        verify(livroRepository).findById(generatedId);
    }

    @Test
    void alteraLivro() {
        LivroRequestDto livroRequestDto = new LivroRequestDto("titulo1", "autor1", dateForBook1);
        Livro livroExistente = new Livro(generatedId, "titulo", "autor", dateForBook1);
        Livro livroAlterado = new Livro(generatedId, "titulo1", "autor1", dateForBook1);


        when(livroRepository.findById(generatedId)).thenReturn(Optional.of(livroExistente));
        when(livroRepository.save(any(Livro.class))).thenReturn(livroAlterado);

        Livro resultado = livroService.alteraLivro(generatedId, livroRequestDto);

        assertNotNull(resultado);
        assertEquals("titulo1", resultado.getTitulo());
        assertEquals(dateForBook1, resultado.getAnoPublicacao());

        verify(livroRepository).findById(generatedId);
        verify(livroRepository).save(any(Livro.class));
    }

    @Test
    void deletaLivro() {
        Livro livroAchado = new Livro(generatedId, "titulo1", "autor1", dateForBook1);

        when(livroRepository.findById(generatedId)).thenReturn(Optional.of(livroAchado));

        livroService.deletaLivro(generatedId);

        assertNotNull(livroAchado);
        assertEquals(generatedId, livroAchado.getId());

        verify(livroRepository).findById(generatedId);
        verify(livroRepository).delete(livroAchado);
    }
}