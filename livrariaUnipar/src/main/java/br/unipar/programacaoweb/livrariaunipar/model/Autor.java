package br.unipar.programacaoweb.livrariaunipar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    private String nacionalidade;

    private Date dataNascimento;

    private String email;


    // cascade = CascadeType.ALL ajuda no salvamento automático dos livros junto com o autor.
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // Evita que os livros sejam serializados automaticamente
    private List<Livro> livros = new ArrayList<>();


    //@ManyToOne
   // private Autor autor;

}
