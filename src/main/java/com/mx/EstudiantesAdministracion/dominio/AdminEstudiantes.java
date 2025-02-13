package com.mx.EstudiantesAdministracion.dominio;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "estudiante")
@Data
public class AdminEstudiantes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "curp", nullable = false, length = 18, unique = true)
    private String curp;

    @Column(name = "matricula", nullable = false, length = 20, unique = true)
    private String matricula;

    @Column(name = "licenciatura", nullable = false, length = 100)
    private String licenciatura;

    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "estado_procedencia", nullable = false, length = 100)
    private String estadoProcedencia;

    @Column(name = "eliminado", nullable = false)
    private boolean eliminado = false; 
    
    //Constructor sin parametros
    public AdminEstudiantes() {
    	
	}
    
    // Método toString para representar el objeto como una cadena de texto.
	@Override
	public String toString() {
		return "AdminEstudiantes [id=" + id + ", nombre=" + nombre + ", curp=" + curp + ", matricula=" + matricula
				+ ", licenciatura=" + licenciatura + ", fechaNacimiento=" + fechaNacimiento + ", estadoProcedencia="
				+ estadoProcedencia + ", eliminado=" + eliminado + "]";
	}


	// Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getLicenciatura() {
		return licenciatura;
	}

	public void setLicenciatura(String licenciatura) {
		this.licenciatura = licenciatura;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getEstadoProcedencia() {
		return estadoProcedencia;
	}

	public void setEstadoProcedencia(String estadoProcedencia) {
		this.estadoProcedencia = estadoProcedencia;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
}
