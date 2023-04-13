package br.com.nexus.televideo.service;

import br.com.nexus.televideo.domain.Produtos;
import br.com.nexus.televideo.repository.ProdutosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Service
public class ProdutosService {

    private final ProdutosRepository produtosRepository;

    public ProdutosService(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    public List<Produtos> findAll() {
        return produtosRepository.findAll();
    }

    public List<Produtos> findAllCategoria(String categoria) {
        return produtosRepository.findByCategoria(categoria);
    }

    public Produtos findByID(Long ID) {
        return produtosRepository.findById(ID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void addImagem(Produtos produtos, MultipartFile multipartFile) {
        try {
            produtos.setImagem(multipartFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        save(produtos);
    }

    public Produtos replace(Produtos produtos) {
        findByID(produtos.getID());
        return save(produtos);
    }

    public Produtos save(Produtos produtos) {
        return produtosRepository.save(produtos);
    }

    public void delete(Long ID) {
        produtosRepository.delete(findByID(ID));
    }
}
