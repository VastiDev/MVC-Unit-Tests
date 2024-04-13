package com.example.mvcunittest.dto;

import java.time.LocalDate;

public record LivroRequestDto (String titulo, String autor, LocalDate anoPublicacao){
}
