package br.edu.ufsj.modulo5.controller;

import br.edu.ufsj.modulo5.model.Documento;
import br.edu.ufsj.modulo5.repository.DocumentoRepository;
import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/documentos")
public class DocumentoController {

    private final DocumentoRepository repository;

    public DocumentoController(DocumentoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("documentos", repository.findAll());

        return "documentos/index";
    }

    @GetMapping("/novo")
    public String create(Model model) {
        model.addAttribute("documento", new Documento());

        return "documentos/create";
    }

    @PostMapping("/novo")
    public String store(@ModelAttribute("documento") Documento documento, Model model) {

        repository.save(documento);

        return "redirect:/documentos";
    }


}
