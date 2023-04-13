package br.com.nexus.televideo.controller;

import br.com.nexus.televideo.domain.Produtos;
import br.com.nexus.televideo.service.ProdutosService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    private final ProdutosService produtosService;

    public ProdutosController(ProdutosService produtosService) {
        this.produtosService = produtosService;
    }

    @GetMapping
    public ResponseEntity<List<Produtos>> findAll() {
        return new ResponseEntity<>(produtosService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{ID}")
    public ResponseEntity<Produtos> findByID(@PathVariable Long ID) {
        return new ResponseEntity<>(produtosService.findByID(ID), HttpStatus.OK);
    }

    @GetMapping("/categorias/{categoria}")
    public ResponseEntity<List<Produtos>> findAllCategoria(@PathVariable String categoria) {
        return new ResponseEntity<>(produtosService.findAllCategoria(categoria), HttpStatus.OK);
    }

    @GetMapping("/imagem/{ID}")
    @ResponseBody
    public byte[] getImageProduto(@PathVariable Long ID) {
        return produtosService.findByID(ID).getImagem();
    }

    @PostMapping
    public ResponseEntity<Produtos> saveProduto(@RequestBody Produtos produtos) {
        return new ResponseEntity<>(produtosService.save(produtos), HttpStatus.CREATED);
    }

    @PutMapping ("/imagem/{ID}")
    public ResponseEntity<Void> addImagem(@RequestParam("file") MultipartFile multipartFile, @PathVariable Long ID) {
        produtosService.addImagem(produtosService.findByID(ID), multipartFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Produtos> replace(@RequestBody Produtos produtos) {
        return new ResponseEntity<>(produtosService.replace(produtos), HttpStatus.OK);
    }

    @DeleteMapping("/{ID}")
    public ResponseEntity<Void> delete(@PathVariable Long ID) {
        produtosService.delete(ID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
