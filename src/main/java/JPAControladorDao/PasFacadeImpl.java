package JPAControladorDao;

import java.util.List;
import javax.persistence.TypedQuery;

import entidades.Pas;

public class PasFacadeImpl extends AbstractFacadeJPAImpl<Pas> implements PasFacade {


public PasFacadeImpl() {
	super(Pas.class);
}


/*salas distintas donde se proyectan películas de un género determinado   */
public Long salaPorGenero() {
//	@SuppressWarnings("unchecked")
//	TypedQuery<Long> query = (TypedQuery<Long>) em.createQuery("select count(Sala s.genero) from Pelicula p "
//			+ "where p.codpelicula NOT IN "
//			+ "(select DISTINCT pas.codpelicula from Pas pas", Long.class);
	return 1l;
}


@Override
public List<Object[]> recaudacionPorPaseJunio2006() {
	TypedQuery<Object[]> query = this.getEm().createQuery(
			"select pas.tipoPase, pas.sala.codsala, sum(ent.pvp) " + 
			"from Entrada ent, Pas pas " + 
			"where ent.pas.codpase = pas.codpase " + 
			"and pas.fechaPase between '2006-06-01' AND '2006-06-30' " + 
			"group by pas.codpase, pas.sala.codsala", Object[].class);
	return query.getResultList();
}







}


