package br.unipar.programacaoweb.livrariaunipar.dto;

import br.unipar.programacaoweb.livrariaunipar.model.Autor;
import br.unipar.programacaoweb.livrariaunipar.model.Livro;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class AutorComLivrosDTO {

    private int id;
    private String nome;
    private String nacionalidade;
    private Date dataNascimento;
    private String email;
    private List<Livro> livros;

    public AutorComLivrosDTO(Autor autor) {
        this.id = autor.getId();
        this.nome = autor.getNome();
        this.nacionalidade = autor.getNacionalidade();
        this.dataNascimento = autor.getDataNascimento();
        this.email = autor.getEmail();
        this.livros = autor.getLivros();
    }
}

// Tive que criar o pacote de DTO pq ao fazer return ResponseEntity.ok(autores);,
// o Spring retornava tudo que estava na entidade Autor, inclusive a lista de livros (porque ela faz parte do model).

//Mesmo usando anotações como @JsonIgnore, isso estava dando errado tbm.

//Quando usava @JsonIgnore, resolvia o problema do endpoint /listar,
// mas dava erro no /com-livros, porque a lista de livros não vinha mais.
