package br.edu.ufsj.modulo5.controller;

import br.edu.ufsj.modulo5.model.Documento;
import br.edu.ufsj.modulo5.repository.DocumentoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class DocumentoControllerTest {

    @Autowired
    private DocumentoRepository testSubject;

    @Test
    void store() {
        Documento documento = new Documento(
                null,
                new Date(),
                "nome",
                "desc",
                new byte[10],
                "test",
                "nome"
        );
        documento = testSubject.save(documento);
        Optional<Documento> result = testSubject.findById(documento.getId());

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    void update() {
        Documento documento = new Documento(
                null,
                new Date(),
                "nome",
                "desc",
                new byte[10],
                "test",
                "nome"
        );
        documento = testSubject.save(documento);
        documento.setNome("nome2");
        testSubject.save(documento);

        Optional<Documento> result = testSubject.findById(documento.getId());

        result.ifPresent(value -> assertThat(value.getNome()).isNotEqualTo("nome"));
    }

    @Test
    void destroy() {
        Documento documento = new Documento(
                null,
                new Date(),
                "nome",
                "desc",
                new byte[10],
                "test",
                "nome"
        );
        documento = testSubject.save(documento);
        testSubject.delete(documento);

        Optional<Documento> result = testSubject.findById(documento.getId());
        assertThat(result.isEmpty()).isTrue();
    }
}