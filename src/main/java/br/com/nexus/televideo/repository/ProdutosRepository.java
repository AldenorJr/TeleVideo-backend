package br.com.nexus.televideo.repository;

import br.com.nexus.televideo.domain.Produtos;
import br.com.nexus.televideo.service.ProdutosService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

    List<Produtos> findByCategoria(String categoria);
}
