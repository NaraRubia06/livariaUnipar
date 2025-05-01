package br.unipar.programacaoweb.livrariaunipar.controller;

import br.unipar.programacaoweb.livrariaunipar.dto.AutorComLivrosDTO;
import br.unipar.programacaoweb.livrariaunipar.model.Autor;
import br.unipar.programacaoweb.livrariaunipar.model.Livro;
import br.unipar.programacaoweb.livrariaunipar.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorController {

    private AutorService autorService;

    @Autowired
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    // GET: listar todos os autores
    @GetMapping("/listar")
    public ResponseEntity<List<Autor>> listarTodos() {
        List<Autor> autores = autorService.listarTodos();
        if (autores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autores);
    }

    // GET: buscar autor por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Autor> buscarAutorPorId(@PathVariable Long id) {
        Autor autor = autorService.buscarPorId(id);
        if (autor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autor);
    }

    // GET: buscar livro autor pelo nome
    @GetMapping("/buscar/autor/{nome}")
    public ResponseEntity<List<Autor>> buscarAutorPeloNome(@PathVariable String nome){
        List<Autor> autor = autorService.buscarAutorNome(nome);
        if(autor.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autor);
    }

    // POST: criar um novo autor
    @PostMapping("/salvar")
    public ResponseEntity<Autor> salvarAutor(@RequestBody Autor autor) {
        Autor autorSalvo = autorService.salvar(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(autorSalvo);
    }

    // PUT: editar autor existente
    @PutMapping("/editar/{id}")
    public ResponseEntity<Autor> editarAutor(@PathVariable Long id,
                                             @RequestBody Autor autor) {
        Autor autorAtual = autorService.buscarPorId(id);
        if(autorAtual == null){
            return ResponseEntity.notFound().build();
        }

        autorAtual.setNome(autor.getNome());
        autorAtual.setNacionalidade(autor.getNacionalidade());
        autorAtual.setDataNascimento(autor.getDataNascimento());
        autorAtual.setEmail(autor.getEmail());

        return ResponseEntity.ok(autorService.salvar(autorAtual));
    }

    // DELETE: deletar autor
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirAutor(@PathVariable Long id) {
        Autor autor = autorService.buscarPorId(id);
        if (autor == null) {
            return ResponseEntity.notFound().build();
        }
        autorService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    // GET: autores com seus livros
    @GetMapping("/com-livros")
    public ResponseEntity<List<AutorComLivrosDTO>> listarAutoresComLivros() {
        List<Autor> autores = autorService.listarTodosComLivros();
        List<AutorComLivrosDTO> dtos = autores.stream()
                .map(AutorComLivrosDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

}
