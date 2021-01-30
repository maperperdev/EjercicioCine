package JPAControladorDao;
import java.util.List;

import entidades.Pelicula;

public interface PeliculaFacade {

	public List<String> generosNuncaProyectadosEnCine();
	public List<Object[]> peliculasPasesRecaudacion();
	public List<Pelicula> peliculasPorTipoDeSonido(String sonido);
	public List<Pelicula> peliculasEmitidasMasDeUnNumeroDeVeces(int numero);
	public List<Pelicula> ejercicio8();
}
