package br.edu.ufsj.modulo5.controller;

import br.edu.ufsj.modulo5.model.Disciplina;
import br.edu.ufsj.modulo5.repository.DisciplinaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DisciplinaControllerTest {

    @Autowired
    private DisciplinaRepository testSubject;

    @Test
    void store() {
        Disciplina disciplina = new Disciplina(
                null,
                "Engenharia",
                "1",
                "",
                "101",
                32,
                new Date(),
                "Sofia",
                5,
                false
        );
        disciplina = testSubject.save(disciplina);

        Optional<Disciplina> result = testSubject.findById(disciplina.getId());

        assertThat(result.isPresent()).isTrue();

    }

    @Test
    void update() {
        Disciplina disciplina = new Disciplina(
                null,
                "Engenharia",
                "1",
                "",
                "101",
                32,
                new Date(),
                "Sofia",
                5,
                false
        );
        disciplina = testSubject.save(disciplina);
        disciplina.setPeriodo(8);
        testSubject.save(disciplina);

        Optional<Disciplina> result = testSubject.findById(disciplina.getId());

        result.ifPresent(value -> assertThat(value.getPeriodo()).isNotEqualTo(5));
    }

    @Test
    void destroy() {
        Disciplina disciplina = new Disciplina(
                null,
                "Engenharia",
                "1",
                "",
                "101",
                32,
                new Date(),
                "Sofia",
                5,
                false
        );
        disciplina = testSubject.save(disciplina);
        testSubject.delete(disciplina);

        Optional<Disciplina> result = testSubject.findById(disciplina.getId());
        assertThat(result.isEmpty()).isTrue();
    }
}