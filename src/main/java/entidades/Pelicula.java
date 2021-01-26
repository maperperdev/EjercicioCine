package entidades;


import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PELICULAS database table.
 * 
 */
@Entity
@Table(name="PELICULAS")
@NamedQuery(name="Pelicula.findAll", query="SELECT p FROM Pelicula p")
public class Pelicula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codpelicula;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_prod")
	private Date fechaProd;

	private String genero;

	private String titulo;

	//bi-directional many-to-one association to Pas
	@OneToMany(mappedBy="pelicula")
	private List<Pas> pases;

	public Pelicula() {
	}

	public int getCodpelicula() {
		return this.codpelicula;
	}

	public void setCodpelicula(int codpelicula) {
		this.codpelicula = codpelicula;
	}

	public Date getFechaProd() {
		return this.fechaProd;
	}

	public void setFechaProd(Date fechaProd) {
		this.fechaProd = fechaProd;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Pas> getPases() {
		return this.pases;
	}

	public void setPases(List<Pas> pases) {
		this.pases = pases;
	}

	public Pas addPas(Pas pas) {
		getPases().add(pas);
		pas.setPelicula(this);

		return pas;
	}

	public Pas removePas(Pas pas) {
		getPases().remove(pas);
		pas.setPelicula(null);

		return pas;
	}

	@Override
	public String toString() {
		return "Pelicula [codpelicula=" + codpelicula + ", genero=" + genero + "]";
	}

}