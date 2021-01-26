package JPAControladorDao;

import java.util.List;


import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entidades.Pas;






public class PasFacadeImpl extends AbstractFacadeJPAImpl<Pas> implements PasFacade {


public PasFacadeImpl() {
	super(Pas.class);
}


/*salas distintas donde se proyectan películas de un género determinado   */
public Long salaPorGenero() {
	@SuppressWarnings("unchecked")
	TypedQuery<Long> query = (TypedQuery<Long>) em.createQuery("select count(Sala s.genero) from Pelicula p "
			+ "where p.codpelicula NOT IN "
			+ "(select DISTINCT pas.codpelicula from Pas pas", Long.class);
	return query.getSingleResult();
}







}


