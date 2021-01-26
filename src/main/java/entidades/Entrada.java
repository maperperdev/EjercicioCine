package entidades;


import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ENTRADAS database table.
 * 
 */
@Entity
@Table(name="ENTRADAS")
@NamedQuery(name="Entrada.findAll", query="SELECT e FROM Entrada e")
public class Entrada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codentrada;

	private String comprador;

	private int numenfila;

	private int numfila;

	private int pvp;

	private Character vendido;

	//bi-directional many-to-one association to Pas
	@ManyToOne
	@JoinColumn(name="codpase")
	private Pas pas;

	public Entrada() {
		
	}

	public int getCodentrada() {
		return this.codentrada;
	}

	public void setCodentrada(int codentrada) {
		this.codentrada = codentrada;
	}

	public String getComprador() {
		return this.comprador;
	}

	public void setComprador(String comprador) {
		this.comprador = comprador;
	}

	public int getNumenfila() {
		return this.numenfila;
	}

	public void setNumenfila(int numenfila) {
		this.numenfila = numenfila;
	}

	public int getNumfila() {
		return this.numfila;
	}

	public void setNumfila(int numfila) {
		this.numfila = numfila;
	}

	public int getPvp() {
		return this.pvp;
	}

	public void setPvp(int pvp) {
		this.pvp = pvp;
	}

	public Character getVendido() {
		return this.vendido;
	}

	public void setVendido(Character vendido) {
		this.vendido = vendido;
	}

	public Pas getPas() {
		return this.pas;
	}

	public void setPas(Pas pas) {
		this.pas = pas;
	}

}