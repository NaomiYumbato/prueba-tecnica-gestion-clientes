package com.prueba.tecnica.gestionclientes.repository;

import com.prueba.tecnica.gestionclientes.dto.ClienteResponseDTO;
import com.prueba.tecnica.gestionclientes.dto.TotalPorTipoDTO;
import com.prueba.tecnica.gestionclientes.entity.ClienteEntity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
    @Query("""
        SELECT new com.prueba.tecnica.gestionclientes.dto.ClienteResponseDTO(
            c.nombreCompleto, c.nroDocumento, c.email, c.celular, c.tipoCliente.descripcion)
        FROM ClienteEntity c
        WHERE (
            :nombre IS NULL 
            OR c.nombreCompleto ILIKE CONCAT('%', CAST(:nombre AS string), '%')
        )
        AND (:nroDocumento IS NULL OR c.nroDocumento = :nroDocumento)
        AND (:tipoCliente IS NULL OR c.tipoCliente.id = :tipoCliente)
        """)
    Page<ClienteResponseDTO> buscarClientesPorFiltro(
            @Param("nombre") String nombre,
            @Param("nroDocumento") String nroDocumento,
            @Param("tipoCliente") Integer tipoCliente,
            Pageable pageable
    );
    boolean existsByNroDocumento(String nroDocumento);

    @Query(value = "SELECT * FROM rpt_totales_clientes_por_tipo()", nativeQuery = true)
    List<TotalPorTipoDTO> obtenerTotalesPorTipo();
}
