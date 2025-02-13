package com.mx.EstudiantesAdministracion.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mx.EstudiantesAdministracion.dao.AdminEstudiantesDao;
import com.mx.EstudiantesAdministracion.dominio.AdminEstudiantes;

@Service
public class AdminEstudiantesServcImpl implements AdminEstudiantesServc{
	
	@Autowired
    AdminEstudiantesDao adminEstudiantesDao;
	
	@Override
	public void guardar(List<AdminEstudiantes> adminestudiantes) {
		adminEstudiantesDao.saveAll(adminestudiantes);
	}

	@Override
	public void editar(AdminEstudiantes adminestudiantes) {
		adminEstudiantesDao.save(adminestudiantes);	
	}

	@Override
	public void eliminar(AdminEstudiantes adminestudiantes) {
		adminestudiantes.setEliminado(false); // Eliminado lógico
		adminEstudiantesDao.save(adminestudiantes);
	}

	@Override
	public AdminEstudiantes buscar(AdminEstudiantes adminestudiantes) {
		return adminEstudiantesDao.findById(adminestudiantes.getId()).orElse(null);
	}

	@Override
	// Método para listar todos los objetos ACTIVOS
	public List<AdminEstudiantes> listar() {
		return adminEstudiantesDao.findByEliminadoFalse();
	}

	@Override
	// Método para buscar por nombre
	public List<AdminEstudiantes> nombre(AdminEstudiantes adminestudiantes) {
		return adminEstudiantesDao.findByNombre(adminestudiantes.getNombre());
	}

	@Override
	// Método para buscar por matricula
	public List<AdminEstudiantes> matricula(AdminEstudiantes adminestudiantes) {
		return adminEstudiantesDao.findByMatricula(adminestudiantes.getMatricula());
	}

	@Override
	// Método para buscar por licenciatura
	public List<AdminEstudiantes> licenciatura(AdminEstudiantes adminestudiantes) {
		return adminEstudiantesDao.findByLicenciatura(adminestudiantes.getLicenciatura());
	}
	
	@Override
    public boolean existsByCurp(String curp) {
        return adminEstudiantesDao.existsByCurp(curp);
    }

    @Override
    public boolean existsByMatricula(String matricula) {
        return adminEstudiantesDao.existsByMatricula(matricula);
    }
    
    @Override
    public List<AdminEstudiantes> findByEliminadoFalse() {
        return adminEstudiantesDao.findByEliminadoFalse();
    }
}
