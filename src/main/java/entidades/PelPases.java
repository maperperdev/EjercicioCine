package entidades;

public class PelPases {
	private String titulo;
	private int codpelicula;
	private int numpases;
	private int recaudacion;
	@Override
	public String toString() {
		return "PelPases [titulo=" + titulo + ", codpelicula=" + codpelicula + ", numpases=" + numpases
				+ ", recaudacion=" + recaudacion + "]";
	}
	public String getTitulo() {
		return titulo;
	}
	public int getCodpelicula() {
		return codpelicula;
	}
	public int getNumpases() {
		return numpases;
	}
	public int getRecaudacion() {
		return recaudacion;
	}
	public PelPases(String titulo, int codpelicula, int numpases, int recaudacion) {
		super();
		this.titulo = titulo;
		this.codpelicula = codpelicula;
		this.numpases = numpases;
		this.recaudacion = recaudacion;
	}
	public PelPases() {
		this.numpases = 0;
		this.recaudacion = 0;
	}
	
}
