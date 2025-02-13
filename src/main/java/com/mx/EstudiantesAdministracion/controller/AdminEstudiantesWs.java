package com.mx.EstudiantesAdministracion.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mx.EstudiantesAdministracion.servicios.AdminEstudiantesServc;
import com.mx.EstudiantesAdministracion.dominio.AdminEstudiantes;

@RestController
@RequestMapping("AdminEstudiantesWs")
@CrossOrigin
public class AdminEstudiantesWs {
	@Autowired
	AdminEstudiantesServc adminEstudiantesServc;
	
	//Validar una CURP (Clave Única de Registro de Población) en México.
	private static final String CURP_REGEX = "^[A-Z]{4}\\d{6}[HM]{1}[A-Z]{5}[0-9A-Z]{2}$";
	
	// Endpoint para listar todos los objetos
	// URL: http://localhost:9000/AdminEstudiantesWs/listar
	@GetMapping("listar")
	public List<AdminEstudiantes> listar() {
		var lista = adminEstudiantesServc.listar();
		return lista; 	
	}
	
	// Guardar estudiantes
    // URL: http://localhost:9000/AdminEstudiantesWs/guardar
    @PostMapping("guardar")
    public ResponseEntity<String> guardar(@RequestBody List<AdminEstudiantes> estudiantes) {
        for (AdminEstudiantes estudiante : estudiantes) {
            if (!Pattern.matches(CURP_REGEX, estudiante.getCurp())) {
                return ResponseEntity.badRequest().body("CURP inválida: " + estudiante.getCurp());
            }
            if (adminEstudiantesServc.existsByCurp(estudiante.getCurp()) ||
                adminEstudiantesServc.existsByMatricula(estudiante.getMatricula())) {
                return ResponseEntity.badRequest().body("CURP o matrícula ya existen: " + estudiante.getNombre());
            }
        }
        adminEstudiantesServc.guardar(estudiantes);
        return ResponseEntity.ok("Estudiantes guardados exitosamente.");
    }
    
    // Editar estudiante
    // URL: http://localhost:9000/AdminEstudiantesWs/editar
    @PutMapping("editar")
    public ResponseEntity<String> editar(@RequestBody AdminEstudiantes estudiante) {
        Optional<AdminEstudiantes> existente = Optional.ofNullable(adminEstudiantesServc.buscar(estudiante));
        if (existente.isEmpty()) {
            return ResponseEntity.badRequest().body("Estudiante no encontrado.");
        }
        adminEstudiantesServc.editar(estudiante);
        return ResponseEntity.ok("Estudiante actualizado.");
    }
    
    // Eliminar estudiante
    // URL: http://localhost:9000/AdminEstudiantesWs/eliminar
    @DeleteMapping("eliminar")
    public ResponseEntity<String> eliminar(@RequestBody AdminEstudiantes estudiante) {
        AdminEstudiantes existente = adminEstudiantesServc.buscar(estudiante);
        if (existente == null) {
            return ResponseEntity.badRequest().body("Estudiante no encontrado.");
        }
        
        // Eliminado lógico de un alumno < 20 
        int edad = calcularEdad(existente.getFechaNacimiento());
        if (edad < 20) {
            return ResponseEntity.badRequest().body("No se puede eliminar. La edad es menor de 20 años.");
        }

        existente.setEliminado(true); // Eliminado lógico
        adminEstudiantesServc.editar(existente);
        return ResponseEntity.ok("Estudiante eliminado lógicamente.");
    }
    
    // Buscar estudiante por ID
    // URL: http://localhost:9000/AdminEstudiantesWs/buscar
    @PostMapping("buscar")
    public AdminEstudiantes buscar(@RequestBody AdminEstudiantes estudiante) {
        return adminEstudiantesServc.buscar(estudiante);
    }
    
    // Buscar estudiantes por nombre
    // URL: http://localhost:9000/AdminEstudiantesWs/nombre
    @PostMapping("nombre")
    public List<AdminEstudiantes> nombre(@RequestBody AdminEstudiantes estudiante) {
    	var lista = adminEstudiantesServc.nombre(estudiante);
        return lista;
    }

    // Buscar estudiantes por matrícula
    // URL: http://localhost:9000/AdminEstudiantesWs/matricula
    @PostMapping("matricula")
    public List<AdminEstudiantes> matricula(@RequestBody AdminEstudiantes estudiante) {
    	var lista = adminEstudiantesServc.matricula(estudiante);
        return lista;
    }

    // Buscar estudiantes por licenciatura
    // URL: http://localhost:9000/AdminEstudiantesWs/licenciatura
    @PostMapping("licenciatura")
    public List<AdminEstudiantes> licenciatura(@RequestBody AdminEstudiantes estudiante) {
    	var lista = adminEstudiantesServc.licenciatura(estudiante);
        return lista;
    }
    
    // Convert Date to LocalDate and calculate age
    private int calcularEdad(Date fechaNacimiento) {
        if (fechaNacimiento == null) {
            return 0;
        }
        // Usa getTime() en lugar de toInstant()
        LocalDate localDate = new java.sql.Date(fechaNacimiento.getTime()).toLocalDate();
        return Period.between(localDate, LocalDate.now()).getYears();
    }

}
