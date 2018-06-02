package ar.com.fiuba.tpprof.hubin.service;

import java.text.ParseException;

import ar.com.fiuba.tpprof.hubin.model.File;
import ar.com.fiuba.tpprof.hubin.util.DocumentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.fiuba.tpprof.hubin.dto.AlumnoDocumentosResponseDTO;
import ar.com.fiuba.tpprof.hubin.dto.AlumnoRequestDTO;
import ar.com.fiuba.tpprof.hubin.dto.AlumnoResponseDTO;
import ar.com.fiuba.tpprof.hubin.dto.AlumnoUpdateRequestDTO;
import ar.com.fiuba.tpprof.hubin.exception.InvalidAlumnoException;
import ar.com.fiuba.tpprof.hubin.model.Alumno;
import ar.com.fiuba.tpprof.hubin.repository.AlumnoDao;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoDao alumnoDao;

    public AlumnoResponseDTO crearAlumno(AlumnoRequestDTO alumnoRequestDTO) throws InvalidAlumnoException {
        if (alumnoRequestDTO.getUsername() == null || alumnoRequestDTO.getPassword() == null) {
            throw new InvalidAlumnoException("Datos incompletos");
        }
        if (alumnoDao.findByUsername(alumnoRequestDTO.getUsername()) != null) {
            throw new InvalidAlumnoException("El nombre de usuario ya existe");
        }
        Alumno alumno = null;
        try {
            alumno = new Alumno(alumnoRequestDTO);
        } catch (ParseException e) {
            throw new InvalidAlumnoException("Formato de fecha incorrecto");
        }
        alumnoDao.save(alumno);
        return new AlumnoResponseDTO(alumno);
    }

    public AlumnoResponseDTO getAlumno(String username) throws InvalidAlumnoException {
        Alumno alumno = alumnoDao.findByUsername(username);
        if (alumno == null) {
            throw new InvalidAlumnoException("El usuario no existe");
        }
        return new AlumnoResponseDTO(alumno);
    }

    public AlumnoResponseDTO getAlumno(int id) throws InvalidAlumnoException {
        Alumno alumno = alumnoDao.findOne(id);
        if (alumno == null) {
            throw new InvalidAlumnoException("El usuario no existe");
        }
        return new AlumnoResponseDTO(alumno);
    }

    public AlumnoResponseDTO updateAlumno(int id, AlumnoUpdateRequestDTO alumnoUpdateRequestDTO) throws InvalidAlumnoException {
        Alumno alumno = alumnoDao.findOne(id);
        if (alumno == null) {
            throw new InvalidAlumnoException("El usuario no existe");
        }
        try {
            alumno.update(alumnoUpdateRequestDTO);
        } catch (ParseException e) {
            throw new InvalidAlumnoException("Formato de fecha incorrecto");
        }
        alumnoDao.save(alumno);
        return new AlumnoResponseDTO(alumno);
    }

    public AlumnoDocumentosResponseDTO getDocumentos(int id) throws InvalidAlumnoException {
        Alumno alumno = alumnoDao.findOne(id);
        if (alumno == null) {
            throw new InvalidAlumnoException("El usuario no existe");
        }
        return new AlumnoDocumentosResponseDTO(alumno);
    }

    public AlumnoResponseDTO addImageProfile(int id, MultipartFile profileImage) throws InvalidAlumnoException {
        Alumno alumno = alumnoDao.findOne(id);
        if (alumno == null) {
            throw new InvalidAlumnoException("El usuario no existe");
        }
        if (profileImage.getSize() > 0) {
            if (!DocumentUtil.isAJPG(profileImage.getOriginalFilename())) {
                throw new InvalidAlumnoException("Imagen no es jpg");
            }
            Long maxSize = 2000000L;
            if (profileImage.getSize() > maxSize) {
                throw new InvalidAlumnoException("Imagen jpg pesa mas de 2mb");
            }
            String[] filenameSplit = profileImage.getOriginalFilename().split("\\.");
            String extension = filenameSplit[filenameSplit.length - 1];
            try {
                File foto = null;
                if (alumno.getFoto() != null) {
                    foto = alumno.getFoto();
                } else {
                    foto = new File();
                }
                foto.setExtension(extension);
                foto.setData(profileImage.getBytes());
                alumno.setFoto(foto);
                alumnoDao.save(alumno);
            } catch (Exception e) {
                throw new InvalidAlumnoException("Error en procesamiento de imagen");
            }
        }
        return new AlumnoResponseDTO(alumno);
    }

}
