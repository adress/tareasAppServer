package com.adress.tareasApp.models.service;

import com.adress.tareasApp.models.entity.Usuario;

public interface IUsuarioService {
    Usuario findByUsername(String username);

    String getUsuarioName(Long id);
}
