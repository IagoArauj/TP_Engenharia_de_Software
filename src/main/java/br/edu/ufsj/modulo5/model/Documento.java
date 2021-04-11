package br.edu.ufsj.modulo5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private @Temporal(TemporalType.DATE) Date data;

    private String nome;

    private String descricao;

    @Lob
    private byte[] arquivo;

    private String mimetype;

    private String nomeOriginal;
}
