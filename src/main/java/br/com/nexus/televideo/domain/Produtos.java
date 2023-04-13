package br.com.nexus.televideo.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String name;
    private String marca;
    private String categoria;
    private String descricao;
    private String obs;
    private String data;
    @Lob
    private byte[] imagem;
    private Integer vendidos;
    private Integer quantidade;
    private Double preco;

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagemList) {
        this.imagem = imagemList;
    }

    public Long getID() {
        return ID;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getVendidos() {
        return vendidos;
    }

    public void setVendidos(Integer vendidos) {
        this.vendidos = vendidos;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

}
