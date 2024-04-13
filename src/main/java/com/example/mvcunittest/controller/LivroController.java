package com.example.mvcunittest.controller;

import com.example.mvcunittest.dto.LivroDetailResponseDto;
import com.example.mvcunittest.dto.LivroRequestDto;;
import com.example.mvcunittest.dto.LivroResponseDto;
import com.example.mvcunittest.dto.LivroUpdatedDto;
import com.example.mvcunittest.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.mvcunittest.domain.Livro;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/livro")
public class LivroController {

    private final LivroService livroService;

    @PostMapping
    public ResponseEntity<LivroResponseDto> cadastraLivro(@RequestBody LivroRequestDto livroRequestDto){
        Livro newLivro = livroService.cadastraLivro(livroRequestDto);
        LivroResponseDto livroResponseDto = new LivroResponseDto(newLivro.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(livroResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> getAll(){
        List<Livro> livros = livroService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDetailResponseDto> getLivroById(@PathVariable UUID id) {
        LivroDetailResponseDto livroDetailResponseDto = livroService.getLivroById(id);
        return ResponseEntity.status(HttpStatus.OK).body(livroDetailResponseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LivroUpdatedDto> alteraLivro (@PathVariable UUID id, @RequestBody LivroRequestDto livroRequestDto){
        Livro alteradoLivro = livroService.alteraLivro(id, livroRequestDto);
        LivroUpdatedDto livroUpdatedDto = new LivroUpdatedDto(alteradoLivro);
        return ResponseEntity.status(HttpStatus.OK).body(livroUpdatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaLivro(@PathVariable UUID id){
        livroService.deletaLivro(id);
        return ResponseEntity.ok().build();
    }
}
