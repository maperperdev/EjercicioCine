package JPAControladorDao;

import java.util.List;

import entidades.Sala;

public interface SalaFacade {
	public List<Sala> salasPorGenerosFechaProduccion(String[] generos, String fceha);
	public List<Sala> salasPorGeneroDePelicula(String genero);

}
