package ar.com.fiuba.tpprof.hubin.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.fiuba.tpprof.hubin.model.Alumno;
import ar.com.fiuba.tpprof.hubin.model.Documento;

public class AlumnoDocumentosResponseDTO {

	private List<DocumentoResponseDTO> documentosCreados = new ArrayList<DocumentoResponseDTO>();

	private List<DocumentoResponseDTO> documentosConAcceso = new ArrayList<DocumentoResponseDTO>();

	public AlumnoDocumentosResponseDTO(Alumno alumno) {
		for (Documento documento : alumno.getDocumentosCreados()) {
			documentosCreados.add(new DocumentoResponseDTO(documento));
		}
		for (Documento documento : alumno.getDocumentosConAcceso()) {
			documentosConAcceso.add(new DocumentoResponseDTO(documento));
		}
	}

	public List<DocumentoResponseDTO> getDocumentosCreados() {
		return documentosCreados;
	}

	public void setDocumentosCreados(List<DocumentoResponseDTO> documentosCreados) {
		this.documentosCreados = documentosCreados;
	}

	public List<DocumentoResponseDTO> getDocumentosConAcceso() {
		return documentosConAcceso;
	}

	public void setDocumentosConAcceso(List<DocumentoResponseDTO> documentosConAcceso) {
		this.documentosConAcceso = documentosConAcceso;
	}

}
