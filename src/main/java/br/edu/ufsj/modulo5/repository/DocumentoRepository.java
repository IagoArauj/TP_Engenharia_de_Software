package br.edu.ufsj.modulo5.repository;

import br.edu.ufsj.modulo5.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {
    
}
