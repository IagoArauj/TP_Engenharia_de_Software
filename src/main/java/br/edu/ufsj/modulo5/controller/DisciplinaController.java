package br.edu.ufsj.modulo5.controller;

import br.edu.ufsj.modulo5.model.Disciplina;
import br.edu.ufsj.modulo5.repository.DisciplinaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        model.addAttribute("error", "");

        return "disciplinas/create";
    }

    @PostMapping("/novo")
    public String store(Model model, @ModelAttribute Disciplina disciplina) {
        if(repository.findFirstBySalaAndHorario(disciplina.getSala(), disciplina.getHorario()).isEmpty()) {
            repository.save(disciplina);

            return "redirect:/disciplinas";
        }

        model.addAttribute("disciplina", new Disciplina());
        model.addAttribute("error", "A sala já está ocupada nessa hora");
        return "disciplinas/create";
    }

    @GetMapping("/editar/{id}")
    public String edit(Model model, @PathVariable String id) {
        Optional<Disciplina> disciplina = repository.findById(Integer.parseInt(id));
        if(disciplina.isEmpty()) return "redirect:/disciplinas?erro=Disciplina+não+encontrada";

        model.addAttribute("disciplina", disciplina.get());
        model.addAttribute("error", "");

        return "disciplinas/edit";
    }

    @PostMapping("/editar/{id}")
    public String update(@PathVariable String id, Model model, @ModelAttribute Disciplina dis) {
        Optional<Disciplina> disciplina = repository.findById(Integer.parseInt(id));

        if(disciplina.isEmpty()) return "redirect:/disciplinas?erro=Disciplina+não+encontrada";

        if(repository.findFirstBySalaAndHorario(dis.getSala(), dis.getHorario()).isEmpty()) {
            repository.save(dis);

            model.addAttribute("disciplina", dis);
            model.addAttribute("updated", true);
            model.addAttribute("error", "A sala já está ocupada nessa hora");
            return "disciplinas/edit";
        }

        repository.save(dis);

        model.addAttribute("disciplina", dis);
        model.addAttribute("updated", true);
        model.addAttribute("error", "");
        return "disciplinas/edit";
    }

    @PostMapping("/confirmar/{disciplinaId}")
    public String confirm(@PathVariable String disciplinaId) {
        Optional<Disciplina> disciplina = repository.findById(Integer.parseInt(disciplinaId));
        if(disciplina.isEmpty()) return "redirect:/disciplinas?erro=Disciplina+não+encontrada";

        disciplina.get().setConfirmada(true);
        repository.save(disciplina.get());

        return "redirect:/disciplinas";
    }

    @PostMapping("/excluir/{id}")
    public String destroy(@PathVariable String id) {
        repository.deleteById(Integer.parseInt((id)));

        return "redirect:/disciplinas";
    }
}
