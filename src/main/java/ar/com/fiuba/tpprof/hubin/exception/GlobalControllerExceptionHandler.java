package ar.com.fiuba.tpprof.hubin.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ InvalidAlumnoException.class, InvalidEntidadException.class, InvalidAreaException.class,
			InvalidMateriaException.class, InvalidProveedorException.class, InvalidPublicidadException.class,
			InvalidObjetivoException.class, MultipartException.class })
	@ResponseBody
	public ErrorInfo handleBadRequest(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(req.getRequestURL().toString(), ex);
	}

}
