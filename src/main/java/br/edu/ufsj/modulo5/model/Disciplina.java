package br.edu.ufsj.modulo5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String codigo;

    private String preRequisitos;

    private String sala;

    private Integer cargaHoraria;

    @DateTimeFormat(pattern = "HH:mm")
    private @Temporal(TemporalType.TIME) Date horario;

    private String professor;

    private int periodo;

    private boolean confirmada;

}
