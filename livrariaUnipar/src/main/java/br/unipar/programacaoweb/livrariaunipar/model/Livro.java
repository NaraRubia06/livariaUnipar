package br.unipar.programacaoweb.livrariaunipar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;

    private Integer numero_Paginas;

    private String genero;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    @JsonBackReference // Evita a recursão infinita
    private Autor autor;


}
