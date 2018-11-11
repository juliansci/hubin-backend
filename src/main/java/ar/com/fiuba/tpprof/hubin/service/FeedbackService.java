package ar.com.fiuba.tpprof.hubin.service;

import java.text.ParseException;

import ar.com.fiuba.tpprof.hubin.exception.InvalidDocumentoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import ar.com.fiuba.tpprof.hubin.dto.FeedbackRequestDTO;
import ar.com.fiuba.tpprof.hubin.exception.InvalidFeedbackException;
import ar.com.fiuba.tpprof.hubin.model.Alumno;
import ar.com.fiuba.tpprof.hubin.model.Feedback;
import ar.com.fiuba.tpprof.hubin.repository.AlumnoDao;
import ar.com.fiuba.tpprof.hubin.repository.FeedbackDao;

@Service
public class FeedbackService {
	
	@Autowired
	private AlumnoDao alumnoDao;
	
	@Autowired
	private FeedbackDao feedbackDao;

	public void crearFeedback(FeedbackRequestDTO feedbackRequestDTO) throws InvalidFeedbackException {
		
		if (!feedbackRequestDTO.isValid())
			throw new InvalidFeedbackException("Datos incompletos");


		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Alumno alumno = alumnoDao.findByUsername(userDetails.getUsername());
		if (alumno == null)
			throw new InvalidFeedbackException("Alumno desconocido");
		
		Feedback feedback = null;
		try {
			feedback = new Feedback(feedbackRequestDTO);
		} catch (ParseException e) {
			throw new InvalidFeedbackException("Formato de fecha incorrecto");
		}
		feedback.setCreador(alumno);
		
		feedbackDao.save(feedback);		
	}

}
