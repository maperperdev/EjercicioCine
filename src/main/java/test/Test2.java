package test;

import java.util.Arrays;
import java.util.List;

//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.TypedQuery;

import JPAControladorDao.PasFacadeImpl;
import JPAControladorDao.PeliculaFacadeImpl;
import JPAControladorDao.SalaFacadeImpl;
import entidades.Pelicula;
import entidades.Sala;

public class Test2 {

	public static void main(String[] args) {
		PeliculaFacadeImpl peliFacImpl = new PeliculaFacadeImpl();
		SalaFacadeImpl salaFacImpl = new SalaFacadeImpl();
		PasFacadeImpl pasFacImpl = new PasFacadeImpl();

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Persistencia");
//		EntityManager emanager = emfactory.createEntityManager();

		/**************************************************************************************************/
		/*
		 * 1. Los géneros de los que nunca se haya proyectado una película en el
		 * multicine.
		 * 
		 * Consulta SQL:
		 * 
		 * select distinct genero from PELICULAS where genero not in (select distinct
		 * genero from PASES pas, PELICULAS pel where pas.codpelicula = pel.codpelicula)
		 */

		System.out.println();
		System.out.println("*******************");
		System.out.println("*** Ejercicio 1 ***");
		System.out.println("*******************");

//		TypedQuery<String> query = emanager.createQuery("select distinct peli.genero from Pelicula peli "
//				+ "where peli.genero not in (select distinct pas.pelicula.genero from Pas pas)", String.class);
//		List<String> resultList = query.getResultList();
//		System.out.println(Arrays.toString(resultList.toArray()));

		/**
		 * Implementación con Facades
		 */
		List<String> generosNoProyectados = peliFacImpl.generosNuncaProyectadosEnCine();
		String listaGeneros = "";
		int i = 0;
		for (; i < generosNoProyectados.size() - 1; i++) {
			listaGeneros += generosNoProyectados.get(i) + ", ";
		}
		listaGeneros += generosNoProyectados.get(i) + ".";
		
		System.out.println("Los géneros de los que nunca se haya proyectado una película en el multicine son: ");
		System.out.println(listaGeneros);

		/*
		 * 2. Mostrar los campos título, codpelicula, cantidad de pases y recaudación de
		 * todas las películas.
		 * 
		 * Consulta SLQ:
		 * 
		 * select PEL.titulo, PEL.codpelicula, count(PAS.codpase) "número de pases",
		 * SUM(ENT.pvp) "precio" from PASES PAS, PELICULAS PEL, ENTRADAS ENT where
		 * PAS.codpelicula = PEL.codpelicula and ENT.codpase = PAS.codpase group by
		 * PAS.codpase
		 */

		System.out.println();
		System.out.println("*******************");
		System.out.println("*** Ejercicio 2 ***");
		System.out.println("*******************");

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

		/**
		 * Implementación con Facades
		 */
		List<Object[]> resultList2 = peliFacImpl.peliculasPasesRecaudacion();
		for (Object[] obj : resultList2) {
			System.out.println("Titulo: " + obj[0] + ", CodPelícula: " + obj[1] + ", Número de pases" + obj[2]
					+ ", Recaudación: " + obj[3]);
		}

		/*
		 * 3. Mostrar cuántas salas distintas existen, donde se proyectan películas de
		 * un género determinado. select distinct sal.codsala, sal.tipo_sonido,
		 * sal.numfilas, sal.numasiporfilas from SALAS sal, PASES pas, PELICULAS pel
		 * where pas.codpelicula = pel.codpelicula and pas.codsala = sal.codsala and
		 * genero = 'COMEDIA' group by sal.codsala, pel.genero
		 */

		System.out.println();
		System.out.println("*******************");
		System.out.println("*** Ejercicio 3 ***");
		System.out.println("*******************");

//		TypedQuery<Sala> query = emanager.createQuery("select distinct sal "
//				+ "from Sala sal, Pas pas, Pelicula pel "
//				+ "where pas.pelicula.codpelicula = pel.codpelicula "
//				+ "and pas.sala.codsala = sal.codsala and genero = 'COMEDIA' "
//				+ "group by sal.codsala, pel.genero", Sala.class);
//				
//		List<Sala> resultList3 = query.getResultList();
//		for (Sala obj : resultList3) {
//			System.out.println("Código Sala: " + obj.getCodsala() + ", Tipo Sonido: " + obj.getTipoSonido() + ", Número de filas" + obj.getNumfilas()
//					+ ", Asientos por fila: " + obj.getNumasiporfilas());
//		}

		/**
		 * Implementación con Facades
		 */
		String[] arrayGeneros = { "C.FICCION", "BELICO", "COMEDIA", "TERROR", "ACCION" };

		for (String genero : arrayGeneros) {
			System.out.println("Salas donde se emiten películas de género " + genero.toLowerCase() + ":");
			List<Sala> resultList3 = salaFacImpl.salasPorGeneroDePelicula(genero);
			if (resultList3.size() != 0) {
				for (Sala obj : resultList3) {
					System.out.println("CodSala: " + obj.getCodsala() + ", Tipo de Sonido: " + obj.getTipoSonido()
							+ ", Número de filas: " + obj.getNumasiporfilas());
				}
			} else {
				System.out.println("No hay salas donde se reproduzcan películas de género " + genero.toLowerCase());
			}

			System.out.println();
		}

		/*
		 * 4. Obtener la información de todos las salas de cine en las que se haya
		 * proyectado alguna película de género TERROR o COMEDIA y cuya fecha de
		 * producción haya sido posterior a 2008. El listado debe aparecer ordenado por
		 * número de asientos en orden descendente.
		 * 
		 * select DISTINCT SAL.codsala, SAL.tipo_sonido, SAL.numfilas,
		 * SAL.numasiporfilas from SALAS SAL, PASES PAS, PELICULAS PEL where SAL.codsala
		 * = PAS.codsala AND PEL.codpelicula = PAS.codpelicula AND PEL.genero in
		 * ('COMEDIA', 'TERROR') AND PEL.fecha_prod >= '2009-01-01' order by
		 * numasiporfilas * numfilas DESC
		 */

		System.out.println();
		System.out.println("*******************");
		System.out.println("*** Ejercicio 4 ***");
		System.out.println("*******************");

//		TypedQuery<Sala> query = emanager.createQuery("select distinct sal " + "from Sala sal, Pas pas, Pelicula pel "
//				+ "where sal.codsala = pas.sala.codsala " + "and pel.codpelicula = pas.pelicula.codpelicula "
//				+ "and pel.genero in ('COMEDIA', 'TERROR') " + "and pel.fechaProd >= '2009-01-01' "
//				+ "order by sal.numasiporfilas * sal.numfilas DESC", Sala.class);
//		List<Sala> resultList = query.getResultList();
//		for (Sala obj : resultList) {
//			System.out.println(
//					"CodSala: " + obj.getCodsala() + ", Tipo de Sonido: " + obj.getTipoSonido() + ", Número de filas"
//							+ obj.getNumfilas() + ", Número de asientos por filas: " + obj.getNumasiporfilas());
//		}

		/**
		 * Implementación con Facades
		 */
		String[] generos = { "COMEDIA", "TERROR" };
		List<Sala> resultList4 = salaFacImpl.salasPorGenerosFechaProduccion(generos, "2009-01-01");

		for (Sala obj : resultList4) {
			System.out.println(
					"CodSala: " + obj.getCodsala() + ", Tipo de Sonido: " + obj.getTipoSonido() + ", Número de filas"
							+ obj.getNumfilas() + ", Número de asientos por filas: " + obj.getNumasiporfilas());
		}

		// 5. Mostrar un listado con la información de las películas proyectadas en
		// salas DOLBY.

		System.out.println();
		System.out.println("*******************");
		System.out.println("*** Ejercicio 5 ***");
		System.out.println("*******************");

		/*
		 * Consulta SQL
		 * 
		 * select PEL.codpelicula, PEL.titulo, PEL.fecha_prod, PEL.genero from PELICULAS
		 * PEL, SALAS SAL, PASES PAS where PEL.codpelicula = PAS.codpelicula and
		 * SAL.codsala = PAS.codsala and SAL.tipo_sonido = 'DOLBY';
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

		/**
		 * Implementación con Facades
		 */

		List<Pelicula> resultList5 = peliFacImpl.peliculasPorTipoDeSonido("DOLBY");
		for (Pelicula obj : resultList5) {
			System.out.println("CodPelicula: " + obj.getCodpelicula() + ", Título: " + obj.getTitulo()
					+ ", Fecha producción: " + obj.getFechaProd() + ", Género: " + obj.getGenero());
		}

		// 6. Obtener un listado de todas las películas que han sido proyectadas en más
		// de 5 ocasiones.

		System.out.println();
		System.out.println("*******************");
		System.out.println("*** Ejercicio 6 ***");
		System.out.println("*******************");

		/*
		 * select * from PELICULAS where codpelicula in (select codpelicula from PASES
		 * group by codpelicula having count(codpelicula) > 5);
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

		/**
		 * Implementación con Facades
		 */

		List<Pelicula> resultList6 = peliFacImpl.peliculasEmitidasMasDeUnNumeroDeVeces(5);
		for (Pelicula obj : resultList6) {
			System.out.println("CodPelicula: " + obj.getCodpelicula() + ", Título: " + obj.getTitulo()
					+ ", Fecha producción: " + obj.getFechaProd() + ", Género: " + obj.getGenero());
		}

		/*
		 * 7. Obtener un listado con la recaudación total de las salas de cine agrupada
		 * por tipo de pase durante el mes de junio de 2006.
		 * 
		 * select tipo_pase, codsala, sum(pvp) "recaudación" from ENTRADAS ENT, PASES
		 * PAS where ENT.codpase = PAS.codpase and fecha_pase between '2006-06-01' AND
		 * '2006-06-30' group by PAS.codpase, codsala
		 */
		
		System.out.println();
		System.out.println("*******************");
		System.out.println("*** Ejercicio 7 ***");
		System.out.println("*******************");

//		TypedQuery<Object[]> query = emanager.createQuery(
//				"select pas.tipoPase, pas.sala.codsala, sum(ent.pvp) " + 
//				"from Entrada ent, Pas pas " + 
//				"where ent.pas.codpase = pas.codpase " + 
//				"and pas.fechaPase between '2006-06-01' AND '2006-06-30' " + 
//				"group by pas.codpase, pas.sala.codsala", Object[].class);

		/**
		 * Implementación con Facades
		 */

		List<Object[]> resultList7 = pasFacImpl.recaudacionPorPaseJunio2006();
		if (resultList7.size() == 0) {
			System.out.println("No hubo recaudación en ese periodo");
		} else {
			for (Object[] obj : resultList7) {
				System.out.println(
						"Tipo de pase: " + obj[0] + ", Código de sala: " + obj[1] + ", Recaudación: " + obj[2]);
			}
		}

		/*
		 * 8. Mostrar un listado con la información de todas las películas proyectadas
		 * en salas de más de 100 asientos y con una venta de entradas de al menos el
		 * 50% del total del aforo de la sala.
		 * 
		 * Consulta SQL
		 * 
		 * select * from PELICULAS where codpelicula in (select pas.codpelicula from
		 * ENTRADAS ent, PASES pas, SALAS sal where ent.codpase = pas.codpase and
		 * sal.codsala = pas.codsala group by codpelicula, sal.numasiporfilas,
		 * sal.numfilas having count(pas.codpelicula) > numasiporfilas * sal.numfilas
		 * /2)
		 */
		System.out.println();
		System.out.println("*******************");
		System.out.println("*** Ejercicio 8 ***");
		System.out.println("*******************");

//		TypedQuery<Pelicula> query = emanager.createQuery(
//				"select pel from Pelicula pel " + "where pel.codpelicula in " + "(select pas.pelicula.codpelicula "
//						+ "from Pas pas, Entrada ent, Sala sal " + "where ent.pas.codpase = pas.codpase "
//						+ "and sal.codsala = pas.sala.codsala "
//						+ "group by pas.pelicula.codpelicula, sal.numasiporfilas, sal.numfilas "
//						+ "having count(pas.pelicula.codpelicula) > sal.numasiporfilas * sal.numfilas / 2)",
//				Pelicula.class);
//		List<Pelicula> resultList = query.getResultList();
//		for (Pelicula pelicula : resultList) {
//			System.out.println("Código película: " + pelicula.getCodpelicula() + ", Título: " + pelicula.getTitulo()
//					+ ", Fecha producción: " + pelicula.getFechaProd() + ", Género: " + pelicula.getGenero());
//		}

		/**
		 * Implementación con Facades
		 */

		List<Pelicula> resultList8 = peliFacImpl.ejercicio8();
		if (resultList8.size() == 0) {
			System.out.println("No existe ninguna película que reuna esos requisitos");
		} else {
			for (Pelicula pelicula : resultList8) {
				System.out.println("Código película: " + pelicula.getCodpelicula() + ", Título: " + pelicula.getTitulo()
						+ ", Fecha producción: " + pelicula.getFechaProd() + ", Género: " + pelicula.getGenero());
			}
		}
	}
}
