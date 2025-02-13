package com.mx.EstudiantesAdministracion.servicios;

import java.util.List;

import com.mx.EstudiantesAdministracion.dominio.AdminEstudiantes;

public interface AdminEstudiantesServc {
	public void guardar(List<AdminEstudiantes> adminestudiantes);
	
	public void editar(AdminEstudiantes adminestudiantes);
	
	public void eliminar(AdminEstudiantes adminestudiantes);
	
	public AdminEstudiantes buscar(AdminEstudiantes adminestudiantes);
	
	public List<AdminEstudiantes> listar();
	
	public List<AdminEstudiantes> nombre(AdminEstudiantes adminestudiantes);
	
	public List<AdminEstudiantes> matricula(AdminEstudiantes adminestudiantes);
	
	public List<AdminEstudiantes> licenciatura(AdminEstudiantes adminestudiantes);
	
	List<AdminEstudiantes> findByEliminadoFalse();
    
	// Método para verificar si un CURP o matricula ya existe
    boolean existsByCurp(String curp);
    boolean existsByMatricula(String matricula);
	
}
