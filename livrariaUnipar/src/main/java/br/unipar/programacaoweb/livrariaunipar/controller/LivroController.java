package br.unipar.programacaoweb.livrariaunipar.controller;

import br.unipar.programacaoweb.livrariaunipar.model.Livro;
import br.unipar.programacaoweb.livrariaunipar.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    private LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    // GET: listar todos os livros
    @GetMapping("listar")
    public ResponseEntity <List<Livro>> listarLivros(){
        List<Livro> livros =livroService.listarTodos();
        if(livros.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(livros);
    }

    // GET: buscar livro por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable Long id){
        Livro livro = livroService.buscarPorId(id);
        if(livro == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(livro);
    }

    // GET: buscar livro pelo titulo
    @GetMapping("/buscar/titulo/{titulo}")
    public ResponseEntity<List<Livro>> buscarLivroPorTitulo(@PathVariable String titulo){
        List<Livro> livros = livroService.buscarPorTitulo(titulo);
        if(livros.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(livros);
    }

    // POST: criar um novo livro
    @PostMapping("/salvar")
    public ResponseEntity<Livro> salvarLivro(@RequestBody Livro livro){
        Livro livroSalvo = livroService.salvar(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroSalvo);
    }

    // DELETE: deletar livro
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirLivro(@PathVariable Long id){
        Livro livro = livroService.buscarPorId(id);
        if(livro == null){
            return ResponseEntity.notFound().build();
        }
        livroService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    // PUT: editar livro existente
    @PutMapping("editar/{id}")
    public ResponseEntity<Livro> editarLivro(@PathVariable Long id,
                                             @RequestBody Livro livro){
        Livro livroAtual = livroService.buscarPorId(id);
        if(livroAtual == null){
            return ResponseEntity.notFound().build();
        }

        livroAtual.setTitulo(livro.getTitulo());
        livroAtual.setNumero_Paginas(livro.getNumero_Paginas());
        livroAtual.setGenero(livro.getGenero());
        livroAtual.setAutor(livro.getAutor());

        return ResponseEntity.ok(livroService.salvar(livroAtual));
    }
}
