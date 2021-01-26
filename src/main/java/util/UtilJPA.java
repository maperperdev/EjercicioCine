package util;
import javax.persistence.EntityManager;

import javax.persistence.Persistence;

public class UtilJPA {

	
	    private static final EntityManager em;
	    static{
	        try{
	            em=Persistence.createEntityManagerFactory("Persistencia").createEntityManager();
	        }catch(Throwable t){
	            System.out.println("Error a inicializar el Entity Manager  "+t);
	            t.printStackTrace();
	            throw new ExceptionInInitializerError();
	        }
	    }

	    public static EntityManager getEntityManager(){
	            return em;
	    }
	
}
