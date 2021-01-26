package JPAControladorDao;



import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import util.UtilJPA;


public abstract class AbstractFacadeJPAImpl<T> implements AbstractFacadeJPA<T> {
	private Class<T> entityClass;
	protected EntityManager em;
	
	public AbstractFacadeJPAImpl(Class<T> entityClass){
		this.entityClass=entityClass;
		em=UtilJPA.getEntityManager();
		
	}
	
	
	/* (non-Javadoc)
	 * @see JPAControladorDao.AbstractFacadeJPA#create(T)
	 */
	@Override
	public Boolean create(T entity){
		em.getTransaction().begin();
        try{
            em.persist(entity);
            em.getTransaction().commit();
            return true;

        }catch(Exception ex){
            em.getTransaction().rollback();
            System.out.println("Ha ocurrido un error al GUARDAR, clave duplicada");
            return false;

        }
		
		
	}
	
	
	/* (non-Javadoc)
	 * @see JPAControladorDao.AbstractFacadeJPA#update(T)
	 */
	@Override
	public Boolean update(T entity){
		EntityTransaction tx=em.getTransaction();
        tx.begin();
        try{
        	
        	em.merge(entity);
            
            tx.commit();
            System.out.println(entity +"  Actualizacion exitosa");
            return true;
        }catch(Exception ex){
            tx.rollback();
            System.out.println("Ha ocurrido un erro al actualizar");
            ex.printStackTrace();
            return false;
        }
       
	}
	
	/* (non-Javadoc)
	 * @see JPAControladorDao.AbstractFacadeJPA#remove(T)
	 */
	@Override
	public void remove(T entity){
		EntityTransaction tx=em.getTransaction();
        tx.begin();
		em.remove(em.merge(entity));
		tx.commit();
		
	}
	
	/* (non-Javadoc)
	 * @see JPAControladorDao.AbstractFacadeJPA#find(java.lang.Object)
	 */
	@Override
	public T find(Object id){
		return em.find(entityClass, id);
	}


	public EntityManager getEm() {
		return em;
	}
	
//	public List<Product> getProductList() {
//        return em.createQuery("select p from Product p order by p.id").getResultList();
//    }

//	try{
//        persona=em.find(Persona.class, id);
//    }catch(Exception ex){
//        System.out.println("upss!! ha ocurrido un error");
//        ex.printStackTrace();
//    }
//    finally{
//        em.close();
//    }
//    return persona;
	
	
}
