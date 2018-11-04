package ar.com.fiuba.tpprof.hubin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.fiuba.tpprof.hubin.dto.FeedbackRequestDTO;
import ar.com.fiuba.tpprof.hubin.exception.InvalidFeedbackException;
import ar.com.fiuba.tpprof.hubin.service.FeedbackService;

@Controller
@CrossOrigin
@RequestMapping(value = "/feedback", consumes = "application/json", produces = "application/json")
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public void createDocumento(@RequestBody FeedbackRequestDTO feedbackRequestDTO) throws InvalidFeedbackException {
		feedbackService.crearFeedback(feedbackRequestDTO);
	}

}
