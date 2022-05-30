package com.adress.tareasApp.models.dao;

import com.adress.tareasApp.models.entity.Tarea;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ITareaDao extends JpaRepository<Tarea, Long> {

    @Query("select t from Tarea t inner join t.usuario as u " +
            "where (?#{[0]} is null or (titulo LIKE %?#{[0]}% OR descripcion LIKE %?#{[0]}%)) " +
            "and (?#{[1]} is null or t.finalizada = ?#{[1]}) " +
            "and (?#{[2]} is null or u.username = ?#{[2]})")
    List<Tarea> findByAndSort(
            @Param("busqueda") String busqueda,
            @Param("estado") Boolean estado,
            @Param("usuario") String usuario,
            Sort sort
    );


}
