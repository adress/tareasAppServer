package com.adress.tareasApp.models.service;

import com.adress.tareasApp.models.entity.Tarea;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ITareaService {
        List<Tarea> findAll(Sort s);

        Optional<Tarea> findById(Long id);

        Tarea save(Tarea tarea);

        boolean delete(Long id);

        List<Tarea> busquedaParams(String busqueda, Boolean estado, String usuario, Sort fechaVencimiento);
}
