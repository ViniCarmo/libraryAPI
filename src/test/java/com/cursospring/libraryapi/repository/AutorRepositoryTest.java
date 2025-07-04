package com.cursospring.libraryapi.repository;

import com.cursospring.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest

public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(195, 1, 31));

        var autorSalvo = autorRepository.save(autor);
        System.out.println("Autor Salvo, " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("2c527e4e-f768-4134-9f24-b35ba410f13e");
        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if (possivelAutor.isPresent()){

            Autor autorEncontrado = possivelAutor.get();
        System.out.println("Dados do autor: ");
        System.out.println(autorEncontrado);

        autorEncontrado.setDataNascimento(LocalDate.of(1980, 1, 31));

        autorRepository.save(autorEncontrado);

    }
}
}
