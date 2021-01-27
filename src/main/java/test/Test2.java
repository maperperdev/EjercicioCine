package test;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import JPAControladorDao.PasFacade;
import JPAControladorDao.PasFacadeImpl;
import entidades.Pelicula;
import entidades.Sala;

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

//		System.out.println("*******************");
//		System.out.println("*** Ejercicio 1 ***");
//		System.out.println("*******************");
//
//		TypedQuery<String> query = emanager.createQuery("select distinct peli.genero from Pelicula peli "
//				+ "where peli.genero not in (select distinct pas.pelicula.genero from Pas pas)", String.class);
//		List<String> resultList = query.getResultList();
//		System.out.println(Arrays.toString(resultList.toArray()));

		
		/*2. Mostrar los campos título, codpelicula, cantidad de pases y recaudación de todas las películas.

		select PEL.titulo, PEL.codpelicula, count(PAS.codpase) "número de pases", SUM(ENT.pvp) "precio"
		from PASES PAS, PELICULAS PEL, ENTRADAS ENT
		where PAS.codpelicula = PEL.codpelicula
		and ENT.codpase = PAS.codpase
		group by PAS.codpase*/

//		System.out.println("*******************");
//		System.out.println("*** Ejercicio 2 ***");
//		System.out.println("*******************");
//
//		TypedQuery<Object[]> query = emanager.createQuery(
//				"select pel.titulo, pel.codpelicula, count(pas.codpase), SUM(ent.pvp) " + 
//				"from Pas pas, Pelicula pel, Entrada ent " + 
//				"where pel.codpelicula = pas.pelicula.codpelicula " + 
//				"and ent.pas.codpase = pas.codpase " + 
//				"group by pas.codpase", Object[].class);
//		List<Object[]> resultList = query.getResultList();
//		
//		for (Object[] obj : resultList) {
//			System.out.println("Titulo: " + obj[0] + ", CodPelícula: " +  obj[1] 
//					+ ", Número de pases" +  obj[2] + ", Recaudación: " + obj[3]);
//		}

		
		/*
		4. Obtener la información de todos las salas de cine en las que se haya proyectado alguna película de
		género TERROR o COMEDIA y cuya fecha de producción haya sido posterior a 2008. El listado debe
		aparecer ordenado por número de asientos en orden descendente.
		
		select DISTINCT SAL.codsala, SAL.tipo_sonido, SAL.numfilas, SAL.numasiporfilas
		from SALAS SAL, PASES PAS, PELICULAS PEL
		where SAL.codsala = PAS.codsala
		AND PEL.codpelicula = PAS.codpelicula
		AND PEL.genero in ('COMEDIA', 'TERROR')
		AND PEL.fecha_prod >= '2009-01-01'
		order by numasiporfilas * numfilas DESC
		 */
//		System.out.println("*******************");
//		System.out.println("*** Ejercicio 4 ***");
//		System.out.println("*******************");
//
//		TypedQuery<Object[]> query = emanager.createQuery(
//				"select distinct sal.codsala, sal.tipoSonido, sal.numfilas, sal.numasiporfilas " + 
//				"from Sala sal, Pas pas, Pelicula pel " + 
//				"where sal.codsala = pas.sala.codsala " + 
//				"and pel.codpelicula = pas.pelicula.codpelicula " +
//				"and pel.genero in ('COMEDIA', 'TERROR') " +
//				"and pel.fechaProd >= '2009-01-01' " + 
//				"order by sal.numasiporfilas * sal.numfilas DESC", Object[].class);
//		List<Object[]> resultList = query.getResultList();
//		for (Object[] obj : resultList) {
//		System.out.println("CodSala: " + obj[0] + ", Tipo de Sonido: " +  obj[1] 
//				+ ", Número de filas" +  obj[2] + ", Número de asientos por filas: " + obj[3]);
//		}
		
		
//		System.out.println("*******************");
//		System.out.println("*** Ejercicio 5 ***");
//		System.out.println("*******************");
		/*
			select PEL.codpelicula, PEL.titulo, PEL.fecha_prod, PEL.genero
			from PELICULAS PEL, SALAS SAL, PASES PAS
			where PEL.codpelicula = PAS.codpelicula
			and SAL.codsala = PAS.codsala    
			and SAL.tipo_sonido = 'DOLBY';
		 */
		
//		TypedQuery<Pelicula> query = emanager.createQuery(
//				"select pel " + 
//				"from Pelicula pel, Sala sal, Pas pas " + 
//				"where pel.codpelicula = pas.pelicula.codpelicula " + 
//				"and sal.codsala = pas.sala.codsala " + 
//				"and sal.tipoSonido = 'DOLBY'", Pelicula.class);
//		List<Pelicula> resultList = query.getResultList();
//		for (Pelicula obj : resultList) {
//		System.out.println("CodPelicula: " + obj.getCodpelicula() + ", Título: " +  obj.getTitulo() 
//				+ ", Fecha producción: " +  obj.getFechaProd() + ", Género: " + obj.getGenero());
//		}
		
		
//		System.out.println("*******************");
//		System.out.println("*** Ejercicio 6 ***");
//		System.out.println("*******************");
		/*
			select * 
			from PELICULAS
			where codpelicula in
								(select codpelicula
			                    from PASES
			                    group by codpelicula
			                    having count(codpelicula) > 5);
		 */
		
//		TypedQuery<Pelicula> query = emanager.createQuery(
//				"select pel " + 
//				"from Pelicula pel " + 
//				"where pel.codpelicula in " + 
//				"(select pas.pelicula.codpelicula " + 
//				"from Pas pas " + 
//				"group by pas.pelicula.codpelicula " + 
//				"having count(pas.pelicula.codpelicula) >= 5)", Pelicula.class);
//		List<Pelicula> resultList = query.getResultList();
//		for (Pelicula obj : resultList) {
//		System.out.println("CodPelicula: " + obj.getCodpelicula() + ", Título: " +  obj.getTitulo() 
//				+ ", Fecha producción: " +  obj.getFechaProd() + ", Género: " + obj.getGenero());
//		}
		

//		System.out.println("*******************");
//		System.out.println("*** Ejercicio 7 ***");
//		System.out.println("*******************");
		/*
			7. Obtener un listado con la recaudación total de las salas de cine agrupada 
			por tipo de pase durante el mes de junio de 2006. 
			
			select tipo_pase, codsala, sum(pvp) "recaudación"
			from ENTRADAS ENT, PASES PAS
			where ENT.codpase = PAS.codpase
			and fecha_pase between '2006-06-01' AND '2006-06-30'
			group by PAS.codpase, codsala
		 */
		
//		TypedQuery<Object[]> query = emanager.createQuery(
//				"select pas.tipoPase, pas.sala.codsala, sum(ent.pvp) " + 
//				"from Entrada ent, Pas pas " + 
//				"where ent.pas.codpase = pas.codpase " + 
//				"and pas.fechaPase between '2006-06-01' AND '2006-06-30' " + 
//				"group by pas.codpase, pas.sala.codsala", Object[].class);
//		TypedQuery<Object[]> query = emanager.createQuery(
//				"select pas.tipoPase, pas.sala.codsala, sum(ent.pvp) " + 
//				"from Entrada ent, Pas pas " + 
//				"where ent.pas.codpase = pas.codpase " + 
//				"group by pas.codpase, pas.sala.codsala", Object[].class);
//		List<Object[]> resultList = query.getResultList();
//		for (Object[] obj : resultList) {
//			System.out.println("Tipo de pase: " + obj[0] + ", Código de sala: " +
//				obj[1] + ", Recaudación: " +  obj[2]);
//		}
	
	System.out.println("*******************");
	System.out.println("*** Ejercicio 8 ***");
	System.out.println("*******************");
	
	/*8. Mostrar un listado con la información de todas las películas proyectadas
	en salas de más de 100 asientos y con una venta de entradas de al menos el 
	50% del total del aforo de la sala. 

	select * from SALAS where numfilas * numasiporfilas > 100
	and
	codsala in (
	SELECT codsala
	FROM
	(select subtabla.aforo, subtabla.codsala, SAL.numfilas * SAL.numasiporfilas "aforoTotal"
	FROM
	(select count(codpase) "aforo", codsala from PASES group by codsala) as subtabla,
	SALAS SAL
	WHERE SAL.codsala = subtabla.codsala) subtabla1
	WHERE aforo > aforoTotal / 2)
	*/
	
	}
}
