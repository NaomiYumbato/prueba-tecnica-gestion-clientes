package com.prueba.tecnica.gestionclientes.repository;

import com.prueba.tecnica.gestionclientes.entity.TipoClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoClienteRepository extends JpaRepository<TipoClienteEntity, Integer> {
}
