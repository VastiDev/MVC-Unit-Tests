package com.example.mvcunittest.exceptions;

import java.util.UUID;

public class LivroNotFoundException extends RuntimeException{
    public LivroNotFoundException(UUID id){
        super("Livro não encontrado para Id:" + id);
    }

}
