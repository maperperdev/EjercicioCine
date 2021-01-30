package JPAControladorDao;

import java.util.List;

import javax.persistence.TypedQuery;

import entidades.Pelicula;

public class PeliculaFacadeImpl extends AbstractFacadeJPAImpl<Pelicula> implements PeliculaFacade {

	public PeliculaFacadeImpl() {
		super(Pelicula.class);
	}

	@Override
	public List<String> generosNuncaProyectadosEnCine() {
		TypedQuery<String> query = this.getEm().createQuery("select distinct peli.genero from Pelicula peli "
				+ "where peli.genero not in (select distinct pas.pelicula.genero from Pas pas)", String.class);
		return query.getResultList();
	}

	@Override
	public List<Object[]> peliculasPasesRecaudacion() {
		TypedQuery<Object[]> query = this.getEm()
				.createQuery("select pel.titulo, pel.codpelicula, count(pas.codpase), SUM(ent.pvp) "
						+ "from Pas pas, Pelicula pel, Entrada ent "
						+ "where pel.codpelicula = pas.pelicula.codpelicula " + "and ent.pas.codpase = pas.codpase "
						+ "group by pas.codpase", Object[].class);
		return query.getResultList();
	}

	@Override
	public List<Pelicula> peliculasPorTipoDeSonido(String sonido) {
		TypedQuery<Pelicula> query = this.getEm().createQuery("select pel " + "from Pelicula pel, Sala sal, Pas pas "
				+ "where pel.codpelicula = pas.pelicula.codpelicula " + "and sal.codsala = pas.sala.codsala "
				+ "and sal.tipoSonido = '" + sonido + "'", Pelicula.class);
		return query.getResultList();
	}

	@Override
	public List<Pelicula> peliculasEmitidasMasDeUnNumeroDeVeces(int numero) {
		TypedQuery<Pelicula> query = this.getEm()
				.createQuery("select pel " + "from Pelicula pel " + "where pel.codpelicula in "
						+ "(select pas.pelicula.codpelicula " + "from Pas pas " + "group by pas.pelicula.codpelicula "
						+ "having count(pas.pelicula.codpelicula) >= " + numero + ")", Pelicula.class);
		return query.getResultList();

	}

	@Override
	public List<Pelicula> ejercicio8() {
		TypedQuery<Pelicula> query = this.getEm().createQuery(
				"select pel from Pelicula pel " + "where pel.codpelicula in " + "(select pas.pelicula.codpelicula "
						+ "from Pas pas, Entrada ent, Sala sal " + "where ent.pas.codpase = pas.codpase "
						+ "and sal.codsala = pas.sala.codsala "
						+ "group by pas.pelicula.codpelicula, sal.numasiporfilas, sal.numfilas "
						+ "having count(pas.pelicula.codpelicula) > sal.numasiporfilas * sal.numfilas / 2)",
				Pelicula.class);
		return query.getResultList();
	}

}
