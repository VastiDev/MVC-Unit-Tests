package com.example.mvcunittest.repository;

import com.example.mvcunittest.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroSpringDataJpaRepository extends JpaRepository<Livro, UUID> {
}
