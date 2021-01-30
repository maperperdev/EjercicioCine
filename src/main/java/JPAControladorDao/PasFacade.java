package JPAControladorDao;
import java.util.List;

import entidades.Pas;

public interface PasFacade extends AbstractFacadeJPA<Pas>{
	 
	public List<Object[]> recaudacionPorPaseJunio2006();
}