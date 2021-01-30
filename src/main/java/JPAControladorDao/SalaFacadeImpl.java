package JPAControladorDao;

import java.util.List;

import javax.persistence.TypedQuery;

import entidades.Sala;

public class SalaFacadeImpl extends AbstractFacadeJPAImpl<Sala> implements SalaFacade {
	public SalaFacadeImpl() {
		super(Sala.class);
	}
	@Override
	public List<Sala> salasPorGenerosFechaProduccion(String[] generos, String fecha) {
		// TODO Auto-generated method stub
		String listaGeneros = "";
		int i = 0;
		for (; i < generos.length - 1; i++) {
			listaGeneros += "'" + generos[i] + "', ";
		}
		listaGeneros += "'" + generos[i] + "'";
		TypedQuery<Sala> query = this.getEm().createQuery("select distinct sal " + "from Sala sal, Pas pas, Pelicula pel "
				+ "where sal.codsala = pas.sala.codsala " + "and pel.codpelicula = pas.pelicula.codpelicula "
				+ "and pel.genero in (" + listaGeneros + ") " + "and pel.fechaProd >= '" + fecha + "' "
				+ "order by sal.numasiporfilas * sal.numfilas DESC", Sala.class);
		return query.getResultList();
	}
	@Override
	public List<Sala> salasPorGeneroDePelicula(String genero) {
		TypedQuery<Sala> query = this.getEm().createQuery("select distinct sal "
				+ "from Sala sal, Pas pas, Pelicula pel "
				+ "where pas.pelicula.codpelicula = pel.codpelicula "
				+ "and pas.sala.codsala = sal.codsala and genero = '" + genero.toUpperCase() + "' "
				+ "group by sal.codsala, pel.genero", Sala.class);
		return query.getResultList();
	}

}
