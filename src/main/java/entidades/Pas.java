package entidades;


import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PASES database table.
 * 
 */
@Entity
@Table(name="PASES")
@NamedQuery(name="Pas.findAll", query="SELECT p FROM Pas p")
public class Pas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codpase;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_pase")
	private Date fechaPase;

	@Column(name="tipo_pase")
	private String tipoPase;

	//bi-directional many-to-one association to Entrada
	@OneToMany(mappedBy="pas")
	private List<Entrada> entradas;

	//bi-directional many-to-one association to Sala
	@ManyToOne
	@JoinColumn(name="codsala")
	private Sala sala;

	//bi-directional many-to-one association to Pelicula
	@ManyToOne
	@JoinColumn(name="codpelicula")
	private Pelicula pelicula;

	public Pas() {
	}

	public int getCodpase() {
		return this.codpase;
	}

	public void setCodpase(int codpase) {
		this.codpase = codpase;
	}

	public Date getFechaPase() {
		return this.fechaPase;
	}

	public void setFechaPase(Date fechaPase) {
		this.fechaPase = fechaPase;
	}

	public String getTipoPase() {
		return this.tipoPase;
	}

	public void setTipoPase(String tipoPase) {
		this.tipoPase = tipoPase;
	}

	public List<Entrada> getEntradas() {
		return this.entradas;
	}

	public void setEntradas(List<Entrada> entradas) {
		this.entradas = entradas;
	}

	public Entrada addEntrada(Entrada entrada) {
		getEntradas().add(entrada);
		entrada.setPas(this);

		return entrada;
	}

	public Entrada removeEntrada(Entrada entrada) {
		getEntradas().remove(entrada);
		entrada.setPas(null);

		return entrada;
	}

	public Sala getSala() {
		return this.sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Pelicula getPelicula() {
		return this.pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	@Override
	public String toString() {
		return "Pas [codpase=" + codpase + ", fechaPase=" + fechaPase + ", tipoPase=" + tipoPase + ", entradas="
				+ entradas + ", sala=" + sala + ", pelicula=" + pelicula + "]";
	}

}