package com.adress.tareasApp.controllers;

import com.adress.tareasApp.models.entity.Tarea;
import com.adress.tareasApp.models.service.ITareaService;
import com.adress.tareasApp.models.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tareas")
public class TareasController {

    @Autowired
    ITareaService tareaService;

    @Autowired
    IUsuarioService usuarioService;

    @GetMapping("/consultar")
    @Secured("ROLE_USER")
    public List<Tarea> index(@RequestParam(required = false) String busqueda,
                             @RequestParam(required = false) String usuario,
                             @RequestParam(required = false, defaultValue = "") String finalizada,
                             @RequestParam(required = false, defaultValue = "") String orden,
                             Authentication authentication) {

        Boolean paramFinalizada = null;
        if (finalizada.equals("si") || finalizada.equals("no")) {
            paramFinalizada = finalizada.equals("si");
        }

        if (usuario != null) {
            usuario = usuario.isEmpty() ? null : usuario.equals("me") ? authentication.getName() : usuario;
        }

        if (busqueda != null) {
            busqueda = busqueda.isEmpty() ? null : busqueda;
        }

        Sort ordenameinto = orden.equals("desc")
                ? Sort.by(Sort.Direction.DESC, "fechaVencimiento")
                : Sort.by(Sort.Direction.ASC, "fechaVencimiento");

        if ((busqueda == null) && (paramFinalizada == null) && (usuario == null)) {
            return tareaService.findAll(ordenameinto);
        }

        return tareaService.busquedaParams(busqueda, paramFinalizada, usuario, ordenameinto);
    }

    @Secured("ROLE_USER")
    @GetMapping("/ver/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<Tarea> tarea;
        Map<String, Object> response = new HashMap<>();

        try {
            tarea = tareaService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", String.format("Error al consultar la tarea con ID %d", id));
            response.put("error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            response.put("ok", false);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (tarea.isEmpty()) {
            response.put("mensaje", String.format("No se encontro una tarea con el ID = %d", id));
            response.put("ok", false);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Tarea>(tarea.get(), HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @PostMapping("/crear")
    public ResponseEntity<?> store(@Valid @RequestBody Tarea tarea, BindingResult result, Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        Tarea tareaNueva = null;

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("mensaje", "Error al guardar la tarea verifique los datos");
            response.put("errors", errors);
            response.put("ok", false);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            tarea.setUsuario(usuarioService.findByUsername(authentication.getName()));
            tareaNueva = tareaService.save(tarea);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al guardar la tarea");
            response.put("ok", false);
            response.put("error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", String.format("La tarea %s ha sido creada con éxito", tarea.getTitulo()));
        response.put("ok", "true");
        response.put("tarea", tareaNueva);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Secured("ROLE_USER")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Tarea newTarea, BindingResult result,
                                    Authentication authentication) {

        Map<String, Object> response = new HashMap<>();
        Optional<Tarea> oldTareaOptional = tareaService.findById(id);
        Tarea tareaUpdated = null;

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("mensaje", "Error al actualizar la tarea verifique los datos");
            response.put("errors", errors);
            response.put("ok", false);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (oldTareaOptional.isEmpty()) {
            response.put("mensaje", String.format("No se encontro la con el ID = %d", id));
            response.put("ok", false);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        if (!oldTareaOptional.get().getUsuario().getUsername().equals(authentication.getName())) {
            response.put("mensaje", "No tene permisos para editar esta tarea");
            response.put("ok", false);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.FORBIDDEN);
        }

        try {
            Tarea oldTarea = oldTareaOptional.get();
            oldTarea.setDescripcion(newTarea.getDescripcion());
            oldTarea.setFinalizada(newTarea.isFinalizada());
            oldTarea.setFechaVencimiento(newTarea.getFechaVencimiento());
            oldTarea.setTitulo(newTarea.getTitulo());
            tareaUpdated = tareaService.save(oldTarea);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar la tarea en la base de datos");
            response.put("error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("ok", true);
        response.put("mensaje", String.format("El la tarea %s ha sido actualizada con éxito!", tareaUpdated.getTitulo()));
        response.put("tarea", tareaUpdated);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Secured("ROLE_USER")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> destroy(@PathVariable Long id, Authentication authentication) {
        Map<String, Object> response = new HashMap<>();

        Optional<Tarea> tarea = tareaService.findById(id);

        if (tarea.isEmpty()) {
            response.put("mensaje", String.format("No se encontro la con el ID = %d", id));
            response.put("ok", false);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        if (!tarea.get().getUsuario().getUsername().equals(authentication.getName())) {
            response.put("mensaje", "No tene permisos para eliminar esta tarea");
            response.put("ok", false);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.FORBIDDEN);
        }

        try {
            tareaService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar la tarea de la base de datos");
            response.put("error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
