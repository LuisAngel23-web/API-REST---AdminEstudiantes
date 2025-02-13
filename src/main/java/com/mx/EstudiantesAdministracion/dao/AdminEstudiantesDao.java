package com.mx.EstudiantesAdministracion.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mx.EstudiantesAdministracion.dominio.AdminEstudiantes;

public interface AdminEstudiantesDao extends JpaRepository<AdminEstudiantes, Integer>{
	
	public List<AdminEstudiantes> findByNombre(String nombre);
	
	public List<AdminEstudiantes> findByMatricula(String matricula);
	
	public List<AdminEstudiantes> findByLicenciatura(String licenciatura);
	
	List<AdminEstudiantes> findByEliminadoFalse();
    
    boolean existsByCurp(String curp);
    boolean existsByMatricula(String matricula);
}
