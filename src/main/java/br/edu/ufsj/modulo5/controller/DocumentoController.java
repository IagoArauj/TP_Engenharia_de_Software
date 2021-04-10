package br.edu.ufsj.modulo5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// * CRUD
// * C - Create (criar novos dados)
// * R - Read (ler os dados da base)
// * U - Update (atualiza os dados da base)
// * D - Delete (deleta os dados da base)

@Controller
@RequestMapping("/documentos")
public class DocumentoController {

    @GetMapping
    public String index() {

        return "documentos/index";
    }
}
