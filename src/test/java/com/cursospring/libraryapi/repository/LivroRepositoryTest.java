package com.cursospring.libraryapi.repository;

import com.cursospring.libraryapi.model.Autor;
import com.cursospring.libraryapi.model.GeneroLivro;
import com.cursospring.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;


    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("1525-1478");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = autorRepository
                .findById(UUID.fromString("bbe3aaab-637e-4e15-be68-8317a55b4110"))
                .orElse(null);

        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("1525-1478");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Outro Livro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        livro.setAutor(autor);
        livroRepository.save(livro);
}

    @Test
    void salvarAutoreLivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("1525-1478");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("terceiro livro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("José");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        autorRepository.save(autor);

        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    void atualizarAutorLivro(){
        UUID id = UUID.fromString("6c34294e-9946-45c0-9c6e-1dcdde39633b");
        var livroParaAtualizar = livroRepository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("bbe3aaab-637e-4e15-be68-8317a55b4110");
        Autor maria = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(maria);
        livroRepository.save(livroParaAtualizar);

    }

    @Test
    void deletar(){
        UUID id = UUID.fromString("0fa5accc-52e7-49f1-840a-19a93a5a0d46");
        livroRepository.deleteById(id);
    }

    @Test
    void deleteCascade(){
        UUID id = UUID.fromString("213edc92-eb6f-4392-92d3-92ea1ab56b95");
        livroRepository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest(){
        UUID id = UUID.fromString("fdb9940e-6cc3-4111-a7e2-a6c2930da5f5");
       Livro livro = livroRepository.findById(id).orElse(null);
        System.out.println(livro.getTitulo());
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisaTest(){
        List<Livro> pesquisaTitulo = livroRepository.findByTitulo("O roubo da csaa assombrada");

        pesquisaTitulo.forEach(System.out::println);
    }

    @Test
    void pesquisaIsbnTest(){
        List<Livro> pesquisaTitulo = livroRepository.findByIsbn("9996-2541");

        pesquisaTitulo.forEach(System.out::println);
    }

    @Test
    void pesquisaTituloPrecoTest(){
        var preco = BigDecimal.valueOf(201.00);
        var titulo = "O roubo da csaa assombrada";

        List<Livro> lista = livroRepository.findByTituloAndPreco(titulo, preco);
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivroComQuery(){
    var resultado = livroRepository.listarTodosPorTituloePreco();

    resultado.forEach(System.out::println);
    }
}