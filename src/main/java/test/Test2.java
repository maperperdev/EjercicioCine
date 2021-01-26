package test;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import JPAControladorDao.PasFacade;
import JPAControladorDao.PasFacadeImpl;

public class Test2 {

	public static void main(String[] args) {
		PasFacade pases = new PasFacadeImpl();

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Persistencia");
		EntityManager emanager = emfactory.createEntityManager();

		/**************************************************************************************************/
		/*
		 * 1. Los géneros de los que nunca se haya proyectado una película en el
		 * multicine.
		 */

		System.out.println("*******************");
		System.out.println("*** Ejercicio 1 ***");
		System.out.println("*******************");

		TypedQuery<String> query = emanager.createQuery("select distinct peli.genero from Pelicula peli "
				+ "where peli.genero not in (select distinct pas.pelicula.genero from Pas pas)", String.class);
		List<String> resultList = query.getResultList();
		System.out.println(Arrays.toString(resultList.toArray()));

	}

}
