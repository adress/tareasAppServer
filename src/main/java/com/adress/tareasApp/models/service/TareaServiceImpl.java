package com.adress.tareasApp.models.service;

import com.adress.tareasApp.models.dao.ITareaDao;
import com.adress.tareasApp.models.entity.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TareaServiceImpl implements  ITareaService{

    @Autowired(required = true)
    ITareaDao tareaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Tarea> findAll(Sort s) {
        return tareaDao.findAll(s);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tarea> findById(Long id) {
        return tareaDao.findById(id);
    }

    @Override
    @Transactional
    public Tarea save(Tarea tarea) {
        return tareaDao.save(tarea);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return findById(id).map((tarea) -> {
            tareaDao.deleteById(id);
            return true;
        }).orElse(false);
    }

    @Override
    public List<Tarea> busquedaParams(String busqueda, Boolean estado, String usuario, Sort fechaVencimiento) {
        return tareaDao.findByAndSort(busqueda, estado, usuario, fechaVencimiento);
    }
}
