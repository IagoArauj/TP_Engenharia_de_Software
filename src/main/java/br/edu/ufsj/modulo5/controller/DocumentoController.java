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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
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

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        Documento doc = repository.getOne(Integer.parseInt(fileId));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getMimetype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + doc.getNomeOriginal() + "\"")
                .body(new ByteArrayResource(doc.getArquivo()));
    }


}
