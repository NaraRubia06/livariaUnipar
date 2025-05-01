package br.unipar.programacaoweb.livrariaunipar.service;

import br.unipar.programacaoweb.livrariaunipar.model.Autor;
import br.unipar.programacaoweb.livrariaunipar.model.Livro;
import br.unipar.programacaoweb.livrariaunipar.repository.AutorRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor salvar(Autor autor) {
        return autorRepository.save(autor);
    }

    public Autor buscarPorId(Long id) {
        return autorRepository.findById(id).orElse(null);
    }

    public List<Autor> buscarAutorNome(String nome){
        return autorRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    @Transactional // Garante que a transação seja gerenciada corretamente
    public List<Autor> listarTodosComLivros() {
        // Recupera todos os autores, garantindo o carregamento dos livros
        List<Autor> autores = autorRepository.findAll();

        // Força o carregamento dos livros (e faz o Lazy Loading acontecer corretamente)
        autores.forEach(autor -> Hibernate.initialize(autor.getLivros())); // Força o carregamento dos livros
        return autores;
    }

    public Autor editar(Long id, Autor novoAutor) {
        Autor autorExistente = buscarPorId(id);
        if (autorExistente != null) {
            autorExistente.setNome(novoAutor.getNome());
            autorExistente.setNacionalidade(novoAutor.getNacionalidade());
            autorExistente.setDataNascimento(novoAutor.getDataNascimento());
            autorExistente.setEmail(novoAutor.getEmail());
            return autorRepository.save(autorExistente);
        }
        return null;
    }

    public void excluir(Long id) {
        autorRepository.deleteById(id);
    }
}
