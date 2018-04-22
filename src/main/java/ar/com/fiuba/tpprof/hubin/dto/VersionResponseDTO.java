package ar.com.fiuba.tpprof.hubin.dto;

import ar.com.fiuba.tpprof.hubin.model.Version;

public class VersionResponseDTO {
	
	private String id;
	
	private String data;

	public VersionResponseDTO(Version version) {
		this.id = String.valueOf(version.getId());
		this.data = new String(version.getData());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
