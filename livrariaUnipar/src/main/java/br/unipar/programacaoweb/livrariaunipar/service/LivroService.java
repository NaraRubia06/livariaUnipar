package br.unipar.programacaoweb.livrariaunipar.service;

import br.unipar.programacaoweb.livrariaunipar.model.Livro;
import br.unipar.programacaoweb.livrariaunipar.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro salvar(Livro livro){
        return livroRepository.save(livro);
    }

    public void editar(Livro livro){
        livroRepository.save(livro);
    }

    public void excluir(Long id){
        livroRepository.deleteById(id);
    }

    public Livro buscarPorId(Long id){
        return livroRepository.findById(id).orElse(null);
    }

    public List<Livro> buscarPorTitulo(String titulo){
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Livro> listarTodos(){
        return livroRepository.findAll();
    }

}
