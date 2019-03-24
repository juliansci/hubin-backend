package ar.com.fiuba.tpprof.hubin.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ar.com.fiuba.tpprof.hubin.dto.*;
import ar.com.fiuba.tpprof.hubin.model.*;
import ar.com.fiuba.tpprof.hubin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ar.com.fiuba.tpprof.hubin.exception.InvalidDocumentoException;


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

    @Autowired
    private ComentarioDao comentarioDao;

    @Autowired
    private NotificacionService notificacionService;


    @Autowired
    private ObjetivoAlumnoService objetivoAlumnoService;

    @Autowired
    private AlumnoDocumentoObservableDao alumnoDocumentoObservableDao;

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
        objetivoAlumnoService.checkUploads(alumno);
        notificacionService.notificarNuevoDocumento(documento, materia);
        AlumnoDocumentoObservable alumnoDocumentoObservable = new AlumnoDocumentoObservable();
        alumnoDocumentoObservable.setAlumno(alumno);
        alumnoDocumentoObservable.setDocumento(documento);
        alumnoDocumentoObservableDao.save(alumnoDocumentoObservable);
        return new DocumentoResponseDTO(documento);
    }

    public DocumentoResponseDTO updateDocumento(int id, DocumentoRequestDTO documentoRequestDTO) throws InvalidDocumentoException {

        if (!documentoRequestDTO.isValid())
            throw new InvalidDocumentoException("Datos incompletos");

        Documento documento = documentoDao.findOne(id);
        if (documento == null)
            throw new InvalidDocumentoException("Documento inexistente");

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
        notificacionService.notificarActualizacion(documento);

        return new DocumentoResponseDTO(documento);
    }

    public DocumentoResponseDTO crearVersion(int id, MultipartFile file) throws InvalidDocumentoException {

        Documento documento = documentoDao.findOne(id);
        if (documento == null)
            throw new InvalidDocumentoException("Documento inexistente");
        if (documento.isEliminado())
            throw new InvalidDocumentoException("Documento eliminado");

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
        if (documento.isEliminado())
            throw new InvalidDocumentoException("Documento eliminado");

        return new DocumentoResponseDTO(documento);
    }

    public FileResponseDTO getDocumento(int idDocumento, int idVersion) throws InvalidDocumentoException {
        Documento documento = documentoDao.findOne(idDocumento);
        if (documento == null)
            throw new InvalidDocumentoException("Documento inexistente");
        if (documento.isEliminado())
            throw new InvalidDocumentoException("Documento eliminado");

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

    public List<ComentarioResponseDTO> getComentarios(int idDocumento) throws InvalidDocumentoException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Alumno alumno = alumnoDao.findByUsername(userDetails.getUsername());
        if (alumno == null)
            throw new InvalidDocumentoException("Alumno desconocido");

        Documento documento = documentoDao.findOne(idDocumento);
        if (documento == null)
            throw new InvalidDocumentoException("Documento desconocido");
        if (documento.isEliminado())
            throw new InvalidDocumentoException("Documento eliminado");

        List<Comentario> comentarios = comentarioDao.findByDocumentoOrderByIdDesc(documento);
        List<ComentarioResponseDTO> comentariosResponse = new ArrayList<ComentarioResponseDTO>();
        for (Comentario comentario : comentarios) {
            comentariosResponse.add(new ComentarioResponseDTO(comentario));
        }
        return comentariosResponse;
    }

    public List<DocumentoResponseDTO> getDocumentosRelacionados(int idDocumento) throws InvalidDocumentoException {
        Documento documento = documentoDao.findOne(idDocumento);
        if (documento == null) {
            throw new InvalidDocumentoException("Documento inexistente");
        }
        if (documento.isEliminado())
            throw new InvalidDocumentoException("Documento eliminado");

        List<Documento> documentosRelacionadosEntidad = documentoDao.buscarPorIdEntidad(documento.getEntidad().getId());
        documentosRelacionadosEntidad.remove(documento);
        List<Documento> documentosRelacionadosMateria = documentoDao.buscarPorIdMateria(documento.getMateria().getId());
        documentosRelacionadosMateria.remove(documento);
        List<Documento> documentosRelacionados = new ArrayList<>();
        documentosRelacionados.addAll(documentosRelacionadosMateria);
        documentosRelacionados.addAll(documentosRelacionadosEntidad);
        List<DocumentoResponseDTO> documentosResponse = new ArrayList<>();
        for (Documento documentoActual : documentosRelacionados) {
            documentosResponse.add(new DocumentoResponseDTO(documentoActual));
        }
        return documentosResponse;
    }


    public void deleteDocumento(int id) throws InvalidDocumentoException {
        Documento documento = documentoDao.findOne(id);
        if (documento == null)
            throw new InvalidDocumentoException("Documento inexistente");

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Alumno alumno = alumnoDao.findByUsername(userDetails.getUsername());
        if (alumno == null)
            throw new InvalidDocumentoException("Alumno no logueado");

        if (documento.getCreador().getId() != alumno.getId()) {
            throw new InvalidDocumentoException("Documento inexistente");
        }
        documento.setEliminado(true);
        AlumnoDocumentoObservable alumnoDocumentoObservable = alumnoDocumentoObservableDao.getAlumnoDocumentoObservableByAlumnoAndDocumento(alumno, documento);
        if (alumnoDocumentoObservable != null) {
            alumnoDocumentoObservableDao.delete(alumnoDocumentoObservable);
        }
        documentoDao.save(documento);
        return;
    }

    public void restoreDocumento(int id) throws InvalidDocumentoException {
        Documento documento = documentoDao.findOne(id);
        if (documento == null)
            throw new InvalidDocumentoException("Documento inexistente");

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Alumno alumno = alumnoDao.findByUsername(userDetails.getUsername());
        if (alumno == null)
            throw new InvalidDocumentoException("Alumno no logueado");

        if (documento.getCreador().getId() != alumno.getId()) {
            throw new InvalidDocumentoException("Documento inexistente");
        }
        documento.setEliminado(false);
        documentoDao.save(documento);
        return;
    }

    public void removeVersionDocumento(int idDocumento, int idVersion) throws InvalidDocumentoException {
        Documento documento = documentoDao.findOne(idDocumento);
        if (documento == null)
            throw new InvalidDocumentoException("Documento inexistente");
        if (documento.isEliminado())
            throw new InvalidDocumentoException("Documento eliminado");

        if (documento.getVersiones().size() <= 1) {
            throw new InvalidDocumentoException("El documento debe tener como mínimo una versión activa");
        }
        File versionToRemove = null;
        List<File> versiones = documento.getVersiones();
        for (File version : versiones) {
            if (version.getId().equals(idVersion)) {
                versionToRemove = version;
            }
        }
        documento.getVersiones().remove(versionToRemove);
        documentoDao.save(documento);
    }

    public List<AlumnoCompartirResponseDTO> getAlumnosCompartir(int idDocumento) throws InvalidDocumentoException {
        Documento documento = documentoDao.findOne(idDocumento);
        if (documento == null)
            throw new InvalidDocumentoException("Documento inexistente");
        List<AlumnoCompartirResponseDTO> alumnosResponse = new ArrayList<>();
        for (Alumno alumno : alumnoDao.findAll()) {
            if (alumno.getId() != documento.getCreador().getId() &&
                    !documento.getCompartidos().contains(alumno)) {
                AlumnoCompartirResponseDTO alumnoCompartir = new AlumnoCompartirResponseDTO(alumno);
                alumnosResponse.add(alumnoCompartir);
            }
        }
        return alumnosResponse;
    }

    public DocumentoResponseDTO addAlumnoShared(int idDocumento, int idAlumno) throws InvalidDocumentoException {
        Documento documento = documentoDao.findOne(idDocumento);
        if (documento == null)
            throw new InvalidDocumentoException("Documento inexistente");
        Alumno alumno = alumnoDao.findOne(idAlumno);
        if (alumno == null)
            throw new InvalidDocumentoException("Alumno inexistente");
        if (!documento.getCompartidos().contains(alumno)) {
            documento.addCompartido(alumno);
            documentoDao.save(documento);
        }

        return new DocumentoResponseDTO(documento);
    }

    public DocumentoResponseDTO removeAlumnoShared(int idDocumento, int idAlumno) throws InvalidDocumentoException {
        Documento documento = documentoDao.findOne(idDocumento);
        if (documento == null)
            throw new InvalidDocumentoException("Documento inexistente");
        Alumno alumno = alumnoDao.findOne(idAlumno);
        if (alumno == null)
            throw new InvalidDocumentoException("Alumno inexistente");
        if (documento.getCompartidos().contains(alumno)) {
            documento.removeCompartido(alumno);
            documentoDao.save(documento);
        }
        return new DocumentoResponseDTO(documento);
    }

    public boolean checkFollow(int id) throws InvalidDocumentoException{
        Documento documento = documentoDao.findOne(id);
        if (documento == null)
            throw new InvalidDocumentoException("Documento inexistente");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Alumno alumno = alumnoDao.findByUsername(userDetails.getUsername());
        if (alumno == null)
            throw new InvalidDocumentoException("Alumno desconocido");
        AlumnoDocumentoObservable alumnoDocumentoObservable = alumnoDocumentoObservableDao.getAlumnoDocumentoObservableByAlumnoAndDocumento(alumno, documento);
        return (alumnoDocumentoObservable != null);
    }
}
