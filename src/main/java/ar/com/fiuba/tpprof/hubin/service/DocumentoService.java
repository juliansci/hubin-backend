package ar.com.fiuba.tpprof.hubin.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ar.com.fiuba.tpprof.hubin.dto.DocumentoRequestDTO;
import ar.com.fiuba.tpprof.hubin.dto.DocumentoResponseDTO;
import ar.com.fiuba.tpprof.hubin.dto.FileResponseDTO;
import ar.com.fiuba.tpprof.hubin.exception.InvalidDocumentoException;
import ar.com.fiuba.tpprof.hubin.model.Alumno;
import ar.com.fiuba.tpprof.hubin.model.Documento;
import ar.com.fiuba.tpprof.hubin.model.Entidad;
import ar.com.fiuba.tpprof.hubin.model.File;
import ar.com.fiuba.tpprof.hubin.model.Idioma;
import ar.com.fiuba.tpprof.hubin.model.Materia;
import ar.com.fiuba.tpprof.hubin.model.Nivel;
import ar.com.fiuba.tpprof.hubin.repository.AlumnoDao;
import ar.com.fiuba.tpprof.hubin.repository.DocumentoDao;
import ar.com.fiuba.tpprof.hubin.repository.EntidadDao;
import ar.com.fiuba.tpprof.hubin.repository.IdiomaDao;
import ar.com.fiuba.tpprof.hubin.repository.MateriaDao;
import ar.com.fiuba.tpprof.hubin.repository.NivelDao;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoDao documentoDao;

    @Autowired
    private AlumnoDao alumnoDao;

    @Autowired
    private MateriaDao materiaDao;

    @Autowired
    private EntidadDao entidadDao;

    @Autowired
    private IdiomaDao idiomaDao;

    @Autowired
    private NivelDao nivelDao;

    public DocumentoResponseDTO crearDocumento(DocumentoRequestDTO documentoRequestDTO) throws InvalidDocumentoException {

        if (!documentoRequestDTO.isValid())
            throw new InvalidDocumentoException("Datos incompletos");

        Documento documento = null;
        try {
            documento = new Documento(documentoRequestDTO);
        } catch (ParseException e) {
            throw new InvalidDocumentoException("Formato de fecha incorrecto");
        }

        Materia materia = materiaDao.findOne(Integer.parseInt(documentoRequestDTO.getIdMateria()));
        if (materia == null)
            throw new InvalidDocumentoException("Materia desconocida");

        Entidad entidad = entidadDao.findOne(Integer.parseInt(documentoRequestDTO.getIdEntidad()));
        if (entidad == null)
            throw new InvalidDocumentoException("Entidad desconocida");

        Idioma idioma = idiomaDao.findOne(Integer.parseInt(documentoRequestDTO.getIdIdioma()));
        if (idioma == null)
            throw new InvalidDocumentoException("Idioma desconocido");

        Nivel nivel = nivelDao.findOne(Integer.parseInt(documentoRequestDTO.getIdNivel()));
        if (nivel == null)
            throw new InvalidDocumentoException("Nivel desconocido");

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Alumno alumno = alumnoDao.findByUsername(userDetails.getUsername());
        if (alumno == null)
            throw new InvalidDocumentoException("Alumno desconocido");

        documento.setCreador(alumno);
        documento.setMateria(materia);
        documento.setEntidad(entidad);
        documento.setIdioma(idioma);
        documento.setNivel(nivel);

        documentoDao.save(documento);

        return new DocumentoResponseDTO(documento);
    }

    public DocumentoResponseDTO updateDocumento(int id, DocumentoRequestDTO documentoRequestDTO) throws InvalidDocumentoException {

        if (!documentoRequestDTO.isValid())
            throw new InvalidDocumentoException("Datos incompletos");

        Documento documento = documentoDao.findOne(id);
        if (documento == null)
            throw new InvalidDocumentoException("Documento inexistente");

       /*if (!documentoRequestDTO.getCompartidos().isEmpty()) {
            for (String idAlumno : documentoRequestDTO.getCompartidos()) {
                Alumno alumno = alumnoDao.findOne(Integer.parseInt(idAlumno));
                if (alumno == null) {
                    throw new InvalidDocumentoException("Alumno a compartir desconocido");
                } else {
                    List<Alumno> compartidos = documento.getCompartidos();
                    if (!compartidos.contains(alumno)) {
                        compartidos.add(alumno);
                    }
                }
            }
        }*/

        Materia materia = materiaDao.findOne(Integer.parseInt(documentoRequestDTO.getIdMateria()));
        if (materia == null)
            throw new InvalidDocumentoException("Materia desconocida");

        Entidad entidad = entidadDao.findOne(Integer.parseInt(documentoRequestDTO.getIdEntidad()));
        if (entidad == null)
            throw new InvalidDocumentoException("Entidad desconocida");

        Idioma idioma = idiomaDao.findOne(Integer.parseInt(documentoRequestDTO.getIdIdioma()));
        if (idioma == null)
            throw new InvalidDocumentoException("Idioma desconocido");

        Nivel nivel = nivelDao.findOne(Integer.parseInt(documentoRequestDTO.getIdNivel()));
        if (nivel == null)
            throw new InvalidDocumentoException("Nivel desconocido");

        documento.setIdioma(idioma);
        documento.setNivel(nivel);
        documento.setEntidad(entidad);
        documento.setMateria(materia);
        documento.setNombre(documentoRequestDTO.getNombre());
        documento.setDescripcion(documentoRequestDTO.getDescripcion());

        documentoDao.save(documento);

        return new DocumentoResponseDTO(documento);
    }

    public DocumentoResponseDTO crearVersion(int id, MultipartFile file) throws InvalidDocumentoException {

        Documento documento = documentoDao.findOne(id);
        if (documento == null)
            throw new InvalidDocumentoException("Documento inexistente");

        if (file.getSize() > 0) {
            Long maxSize = 10000000L;
            if (file.getSize() > maxSize) {
                throw new InvalidDocumentoException("Imagen jpg pesa mas de 10mb");
            }
            String[] filenameSplit = file.getOriginalFilename().split("\\.");
            String extension = filenameSplit[filenameSplit.length - 1];
            try {
                File version = new File();
                version.setExtension(extension);
                version.setData(file.getBytes());
                documento.addVersion(version);
                documentoDao.save(documento);
            } catch (Exception e) {
                throw new InvalidDocumentoException("Error en procesamiento de archivo");
            }
        }

        return new DocumentoResponseDTO(documento);
    }

    public DocumentoResponseDTO getDocumento(int idDocumento) throws InvalidDocumentoException {
        Documento documento = documentoDao.findOne(idDocumento);
        if (documento == null)
            throw new InvalidDocumentoException("Documento inexistente");
        return new DocumentoResponseDTO(documento);
    }

    public FileResponseDTO getDocumento(int idDocumento, int idVersion) throws InvalidDocumentoException {
        Documento documento = documentoDao.findOne(idDocumento);
        if (documento == null)
            throw new InvalidDocumentoException("Documento inexistente");
        List<File> versiones = documento.getVersiones();
        for (File version : versiones) {
            if (version.getId().equals(idVersion)) {
                return new FileResponseDTO(version);
            }
        }
        throw new InvalidDocumentoException("Version de documento inexistente");
    }

    public List<DocumentoResponseDTO> getDocumentos(String nombre, List<Integer> idEntidad, List<Integer> idMateria, List<Integer> idIdioma, List<Integer> idNivel) {
        List<Documento> documentos = documentoDao.buscarDocumentos(
                nombre,
                idEntidad == null ? null : true, idEntidad,
                idMateria == null ? null : true, idMateria,
                idIdioma == null ? null : true, idIdioma,
                idNivel == null ? null : true, idNivel);
        List<DocumentoResponseDTO> documentosResponse = new ArrayList<DocumentoResponseDTO>();
        for (Documento documento : documentos) {
            documentosResponse.add(new DocumentoResponseDTO(documento));
        }
        return documentosResponse;
    }

}
