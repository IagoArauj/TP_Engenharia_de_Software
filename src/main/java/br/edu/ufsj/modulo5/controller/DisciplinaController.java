package br.edu.ufsj.modulo5.controller;

import br.edu.ufsj.modulo5.model.Disciplina;
import br.edu.ufsj.modulo5.repository.DisciplinaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaRepository repository;

    public DisciplinaController(DisciplinaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("disciplinas", repository.findAll());

        return "disciplinas/index";
    }

    @GetMapping("/novo")
    public String create(Model model) {
        model.addAttribute("disciplina", new Disciplina());

        return "disciplinas/create";
    }

    @PostMapping("/novo")
    public String save(Model model, @ModelAttribute Disciplina disciplina) {
        repository.save(disciplina);

        return "redirect:/disciplinas";
    }

    @PostMapping("/confirmar/{disciplinaId}")
    public String update(@PathVariable String disciplinaId) {
        Disciplina disciplina = repository.getOne(Integer.parseInt(disciplinaId));
        disciplina.setConfirmada(true);

        return "redirect:/disciplinas";
    }
}
