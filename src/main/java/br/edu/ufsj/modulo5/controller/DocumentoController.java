package br.edu.ufsj.modulo5.controller;

import br.edu.ufsj.modulo5.model.Documento;
import br.edu.ufsj.modulo5.repository.DocumentoRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

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
    public String store(
            @ModelAttribute("documento") Documento documento,
            Model model,
            @RequestParam MultipartFile file
    ) throws IOException {
        documento.setArquivo(file.getBytes());
        documento.setNomeOriginal(file.getOriginalFilename());
        documento.setMimetype(file.getContentType());

        repository.save(documento);

        return "redirect:/documentos";
    }

    @GetMapping("/editar/{documentoId}")
    public String edit(Model model, @PathVariable String documentoId) {
        Optional<Documento> doc = repository.findById(Integer.parseInt(documentoId));

        if(doc.isEmpty()) return "redirect:/documentos?erro=Documento+não+encontrada";
        model.addAttribute("documento", doc.get());
        model.addAttribute("updated", false);

        return "documentos/edit";
    }

    @PostMapping("/editar/{id}")
    public String update(
            @ModelAttribute("documento") Documento documento,
            Model model,
            @RequestParam MultipartFile file,
            @PathVariable String id
    ) throws IOException{
        Optional<Documento> doc = repository.findById(Integer.parseInt(id));

        if(doc.isEmpty()) return "redirect:/documentos?erro=Documento+não+encontrada";

        documento.setArquivo(file.getBytes());
        documento.setNomeOriginal(file.getOriginalFilename());
        documento.setMimetype(file.getContentType());

        repository.save(documento);

        model.addAttribute("documento", documento);
        model.addAttribute("updated", true);

        return "/documentos/edit";
    }

    @PostMapping("/excluir/{id}")
    public String destroy(@PathVariable String id) {
        repository.deleteById(Integer.parseInt(id));

        return "redirect:/documentos";
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        Documento doc = repository.getOne(Integer.parseInt(fileId));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getMimetype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + doc.getNomeOriginal() + "\"")
                .body(new ByteArrayResource(doc.getArquivo()));
    }


}
