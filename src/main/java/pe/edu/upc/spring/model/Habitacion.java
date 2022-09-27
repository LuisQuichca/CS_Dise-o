package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="habitacion")
public class Habitacion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idHabitacion;
	
	@Column(name="descripcion", length=60, nullable=false)
	private String desHabitacion;
	
	@Column(name="direccion", length=60, nullable=false)
	private String direccion;
	
	@ManyToOne
	@JoinColumn(name="idDistrito", nullable=false)
	private Distrito distrito;
	
	@ManyToOne
	@JoinColumn(name="idArrendador", nullable=true)
	private Arrendador arrendador;

	public Habitacion() {
		super();
	}

	public Habitacion(int idHabitacion, String desHabitacion, String direccion, Distrito distrito,
			Arrendador arrendador) {
		super();
		this.idHabitacion = idHabitacion;
		this.desHabitacion = desHabitacion;
		this.direccion = direccion;
		this.distrito = distrito;
		this.arrendador = arrendador;
	}

	public int getIdHabitacion() {
		return idHabitacion;
	} 

	public void setIdHabitacion(int idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	public String getDesHabitacion() {
		return desHabitacion;
	}

	public void setDesHabitacion(String desHabitacion) {
		this.desHabitacion = desHabitacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public Arrendador getArrendador() {
		return arrendador;
	}

	public void setArrendador(Arrendador arrendador) {
		this.arrendador = arrendador;
	}
	
}