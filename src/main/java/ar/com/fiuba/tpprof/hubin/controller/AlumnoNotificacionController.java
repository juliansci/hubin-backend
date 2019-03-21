package ar.com.fiuba.tpprof.hubin.controller;

import ar.com.fiuba.tpprof.hubin.dto.*;
import ar.com.fiuba.tpprof.hubin.exception.InvalidAlumnoException;
import ar.com.fiuba.tpprof.hubin.exception.InvalidFeedbackException;
import ar.com.fiuba.tpprof.hubin.exception.InvalidNotificacionException;
import ar.com.fiuba.tpprof.hubin.service.AlumnoService;
import ar.com.fiuba.tpprof.hubin.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value = "/alumno", consumes = "application/json", produces = "application/json")
public class AlumnoNotificacionController {

    @Autowired
    private NotificacionService notificacionService;



    @RequestMapping(value="/notificacion/suscripcion", method = RequestMethod.POST)
    @ResponseBody
    public void suscribirseAEntidadNotificacion(@RequestBody AlumnoNotificacionRequestDTO alumnoNotificacionRequestDTO) throws InvalidNotificacionException {
        notificacionService.suscribirse(alumnoNotificacionRequestDTO);
    }

    @RequestMapping(value="/notificacion/desuscripcion", method = RequestMethod.POST)
    @ResponseBody
    public void desuscribirseAEntidadNotificacion(@RequestBody AlumnoNotificacionRequestDTO alumnoNotificacionRequestDTO) throws InvalidNotificacionException {
        notificacionService.desuscribirse(alumnoNotificacionRequestDTO);
    }


}
