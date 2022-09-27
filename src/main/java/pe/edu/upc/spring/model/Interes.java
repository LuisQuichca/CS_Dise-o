package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Interes")
public class Interes implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idInteres;
	
	@ManyToOne
	@JoinColumn(name="idEstudiante", nullable=false)
	private Estudiante estudiante;
	
	@ManyToOne
	@JoinColumn(name="idAviso", nullable=false)
	private Aviso aviso;
	
	public Interes() {
		super();
	}

	public Interes(int idInteres, Estudiante estudiante, Aviso aviso) {
		super();
		this.idInteres = idInteres;
		this.estudiante = estudiante;
		this.aviso = aviso;
	}

	public int getIdInteres() {
		return idInteres;
	}

	public void setIdInteres(int idInteres) {
		this.idInteres = idInteres;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Aviso getAviso() {
		return aviso;
	}

	public void setAviso(Aviso aviso) {
		this.aviso = aviso;
	}

	
}
