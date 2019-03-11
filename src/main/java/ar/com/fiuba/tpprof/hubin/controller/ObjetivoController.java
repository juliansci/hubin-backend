package ar.com.fiuba.tpprof.hubin.controller;

import ar.com.fiuba.tpprof.hubin.dto.ObjetivoResponseDTO;
import ar.com.fiuba.tpprof.hubin.exception.InvalidObjetivoException;
import ar.com.fiuba.tpprof.hubin.service.AlumnoService;
import ar.com.fiuba.tpprof.hubin.service.ObjetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value = "/objetivo", consumes = "application/json", produces = "application/json")
public class ObjetivoController {

    @Autowired
    private AlumnoService alumnoService;
    @Autowired
    private ObjetivoService objetivoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<ObjetivoResponseDTO> getObjetivos() throws InvalidObjetivoException {
        return objetivoService.getAll();
    }

    @RequestMapping(value = "/alumno/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<ObjetivoResponseDTO> getObjetivosAlumno(@PathVariable("id") int id) throws InvalidObjetivoException {
        return objetivoService.getAllAlumno(id);
    }

}
