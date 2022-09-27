package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity 
@Table(name="arrendador") 
public class Arrendador implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idArrendador; 
	
	@NotEmpty(message = "Ingrese su nombre")
	@Pattern(regexp = "[^0-9]*", message = "El nombre no debe contener números")
	@Column(name="nomArrendador", nullable=false, length=60)
	private String nomArrendador;
	
	@NotEmpty(message = "Ingrese su apellido")
	@Pattern(regexp = "[^0-9]*", message = "El apellido no debe contener números")
	@Column(name="apeArrendador", nullable=false, length=60)
	private String apeArrendador;
	
	@Size(min = 8, max = 8)
	@NotEmpty(message = "Ingrese DNI")
	@Column (name="dni_arrendador", nullable=false, length = 8)
	private String dniArrendador;
	
	@Column(name="edad_arrendador", nullable=false)
	private int edadArrendador;
	
	@Size(min = 6, max = 9, message = "El celular debe tener 9 digitos")
	@Pattern(regexp = "[^0-9]+", message = "El celular debe contener solo numeros")
	@NotEmpty(message = "Ingrese su telefono")
	@Column(name="cel_arrendador", nullable=false, length=9)
	private String celArrendador;
	
	@NotEmpty(message = "Ingrese su correo")
	@Column(name="email_arrendador", nullable=false, length=60)
	private String emailArrendador;
	
	@NotEmpty(message = "Ingrese su Usuario")
	@Column(name="username", nullable=false)
	private String username;
	
	@NotEmpty(message = "Ingrese su contraseña")
	@Column(name="password", nullable=false)
	private String password;

	public Arrendador() {
		super();
	}

	public Arrendador(int idArrendador, @NotEmpty(message = "Ingrese su nombre") String nomArrendador,
			@NotEmpty(message = "Ingrese su apellido") String apeArrendador,
			@Size(min = 8, max = 8) @NotEmpty(message = "Ingrese DNI") String dniArrendador, int edadArrendador,
			@Size(min = 6, max = 9) @NotEmpty(message = "Ingrese su telefono") String celArrendador,
			@NotEmpty(message = "Ingrese su correo") String emailArrendador,
			@NotEmpty(message = "Ingrese su Usuario") String username,
			@NotEmpty(message = "Ingrese su contraseña") String password) {
		super();
		this.idArrendador = idArrendador;
		this.nomArrendador = nomArrendador;
		this.apeArrendador = apeArrendador;
		this.dniArrendador = dniArrendador;
		this.edadArrendador = edadArrendador;
		this.celArrendador = celArrendador;
		this.emailArrendador = emailArrendador;
		this.username = username;
		this.password = password;
	}

	public int getIdArrendador() {
		return idArrendador;
	}

	public void setIdArrendador(int idArrendador) {
		this.idArrendador = idArrendador;
	}

	public String getNomArrendador() {
		return nomArrendador;
	}

	public void setNomArrendador(String nomArrendador) {
		this.nomArrendador = nomArrendador;
	}

	public String getApeArrendador() {
		return apeArrendador;
	}

	public void setApeArrendador(String apeArrendador) {
		this.apeArrendador = apeArrendador;
	}

	public String getDniArrendador() {
		return dniArrendador;
	}

	public void setDniArrendador(String dniArrendador) {
		this.dniArrendador = dniArrendador;
	}

	public int getEdadArrendador() {
		return edadArrendador;
	}

	public void setEdadArrendador(int edadArrendador) {
		this.edadArrendador = edadArrendador;
	}

	public String getCelArrendador() {
		return celArrendador;
	}

	public void setCelArrendador(String celArrendador) {
		this.celArrendador = celArrendador;
	}

	public String getEmailArrendador() {
		return emailArrendador;
	}

	public void setEmailArrendador(String emailArrendador) {
		this.emailArrendador = emailArrendador;
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
