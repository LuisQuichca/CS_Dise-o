package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Aviso")
public class Aviso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAviso;
	
	@Column(name="precio", length=60, nullable=false)
	private Double preHabitacion;
	
	@Column(name="rangoPrecio", length=60, nullable=false)
	private String rangoPrecio;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaPublicacion")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaPublicacion;
	
	@Column(name="foto", nullable=true)
	private String foto;

	@ManyToOne
	@JoinColumn(name="idHabitacion", nullable=false)
	private Habitacion habitacion;
	
	public Aviso() {
		super();
	}

	public Aviso(int idAviso, Double preHabitacion, String rangoPrecio, Date fechaPublicacion, String foto, Habitacion habitacion) {
		super();
		this.idAviso = idAviso;
		this.preHabitacion = preHabitacion;
		this.rangoPrecio = rangoPrecio;
		this.fechaPublicacion = fechaPublicacion;
		this.habitacion = habitacion;
		this.foto = foto;
	}

	public int getIdAviso() {
		return idAviso;
	}

	public void setIdAviso(int idAviso) {
		this.idAviso = idAviso;
	}

	public Double getPreHabitacion() {
		return preHabitacion;
	}

	public void setPreHabitacion(Double preHabitacion) {
		this.preHabitacion = preHabitacion;
	}

	public String getRangoPrecio() {
		return rangoPrecio;
	}

	public void setRangoPrecio(String rangoPrecio) {
		this.rangoPrecio = rangoPrecio;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
}
