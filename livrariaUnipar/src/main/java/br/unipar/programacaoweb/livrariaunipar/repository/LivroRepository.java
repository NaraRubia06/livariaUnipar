package br.unipar.programacaoweb.livrariaunipar.repository;

import br.unipar.programacaoweb.livrariaunipar.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
    List<Livro> findByGeneroContainingIgnoreCase(String genero);

    @Query("SELECT t FROM Livro t WHERE t.genero = :genero AND t.numero_Paginas >= :numeroPaginas")
    List<Livro> findByGeneroNumeroPaginas(@Param("genero") String genero,
                                          @Param("numeroPaginas") Integer numeroPaginas);

}
