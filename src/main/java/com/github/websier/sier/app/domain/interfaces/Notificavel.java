package com.github.websier.sier.app.domain.interfaces;

import com.github.websier.sier.app.domain.dtos.Alerta;

/**
 * Entidade
 */
public interface Notificavel {
    public Alerta toAlerta();
}