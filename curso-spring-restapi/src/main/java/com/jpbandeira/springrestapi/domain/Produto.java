package com.jpbandeira.springrestapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Produto  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double preco;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Categoria_id")
    private Categoria categoria;
    @JsonIgnore
    @OneToMany(mappedBy = "id.produto", cascade = CascadeType.ALL)
    private Set<ItemPedido> itens = new HashSet<>();

    public Produto(){}

    public Produto(String nome, Double preco, Categoria categoria) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    @JsonIgnore
    public List<Pedido> getPedidos(){
        List<Pedido> listaPedidos = new ArrayList<>();
        for(ItemPedido itemPedido : itens){
            listaPedidos.add(itemPedido.getPedido());
        }
        return listaPedidos;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", categoria=" + categoria +
                ", itens=" + itens +
                '}';
    }
    /*@JsonIgnore verifica se no outro lado da associação já foi buscado os objetos, então ele omite a lista
     * de categorias para cada produto*/
}
