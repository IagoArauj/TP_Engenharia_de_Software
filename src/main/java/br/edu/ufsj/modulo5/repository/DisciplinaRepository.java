package br.edu.ufsj.modulo5.repository;

import br.edu.ufsj.modulo5.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
    List<Disciplina> findFirstBySalaAndHorario(String sala, Date horario);
}
