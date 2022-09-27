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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name="Estudiante")
public class Estudiante implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEstudiante;
	
	@NotEmpty(message = "Ingrese su nombre")
	@Column (name="nom_estudiante", nullable=false)
	private String nomEstudiante;
	
	@NotEmpty(message = "Ingrese su apellido")
	@Column(name="apeEstudiante", nullable=false, length=60)
	private String apeEstudiante;
	
	@Size(min = 8, max = 8)
	@NotEmpty(message = "Ingrese DNI")
	@Column (name="dni_estudiante", nullable=false)
	private String dniEstudiante;
	
	
	@Column(name="edad_estudiante", nullable=false)
	private int edadEstudiante;
	
	@Size(min = 6, max = 9)
	@NotEmpty(message = "Ingrese su telefono")
	@Column(name="cel_estudiante", nullable=false)
	private String celEstudiante;
	
	@NotEmpty(message = "Ingrese su correo")
	@Column(name="email_estudiante", nullable=false)
	private String emailEstudiante;
	
	@ManyToOne
	@JoinColumn(name="idUniversidad", nullable=false)
	private Universidad universidad;
	
	@NotEmpty(message = "Ingrese su Usuario")
	@Column(name="username", nullable=false)
	private String username;
	
	@NotEmpty(message = "Ingrese su contraseña")
	@Column(name="password", nullable=false)
	private String password;

	public Estudiante() {
		super();
	}

	public Estudiante(int idEstudiante, @NotEmpty(message = "Ingrese su nombre") String nomEstudiante,
			@NotEmpty(message = "Ingrese su apellido") String apeEstudiante,
			@Size(min = 8, max = 8) @NotEmpty(message = "Ingrese DNI") String dniEstudiante, int edadEstudiante,
			@Size(min = 6, max = 9) @NotEmpty(message = "Ingrese su telefono") String celEstudiante,
			@NotEmpty(message = "Ingrese su correo") String emailEstudiante, Universidad universidad,
			@NotEmpty(message = "Ingrese su Usuario") String username,
			@NotEmpty(message = "Ingrese su contraseña") String password) {
		super();
		this.idEstudiante = idEstudiante;
		this.nomEstudiante = nomEstudiante;
		this.apeEstudiante = apeEstudiante;
		this.dniEstudiante = dniEstudiante;
		this.edadEstudiante = edadEstudiante;
		this.celEstudiante = celEstudiante;
		this.emailEstudiante = emailEstudiante;
		this.universidad = universidad;
		this.username = username;
		this.password = password;
	}

	public int getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(int idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public String getNomEstudiante() {
		return nomEstudiante;
	}

	public void setNomEstudiante(String nomEstudiante) {
		this.nomEstudiante = nomEstudiante;
	}

	public String getApeEstudiante() {
		return apeEstudiante;
	}

	public void setApeEstudiante(String apeEstudiante) {
		this.apeEstudiante = apeEstudiante;
	}

	public String getDniEstudiante() {
		return dniEstudiante;
	}

	public void setDniEstudiante(String dniEstudiante) {
		this.dniEstudiante = dniEstudiante;
	}

	public int getEdadEstudiante() {
		return edadEstudiante;
	}

	public void setEdadEstudiante(int edadEstudiante) {
		this.edadEstudiante = edadEstudiante;
	}

	public String getCelEstudiante() {
		return celEstudiante;
	}

	public void setCelEstudiante(String celEstudiante) {
		this.celEstudiante = celEstudiante;
	}

	public String getEmailEstudiante() {
		return emailEstudiante;
	}

	public void setEmailEstudiante(String emailEstudiante) {
		this.emailEstudiante = emailEstudiante;
	}

	public Universidad getUniversidad() {
		return universidad;
	}

	public void setUniversidad(Universidad universidad) {
		this.universidad = universidad;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
