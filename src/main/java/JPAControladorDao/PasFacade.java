package JPAControladorDao;




import entidades.Pas;

public interface PasFacade extends AbstractFacadeJPA<Pas>{
	 Long salaPorGenero();
}