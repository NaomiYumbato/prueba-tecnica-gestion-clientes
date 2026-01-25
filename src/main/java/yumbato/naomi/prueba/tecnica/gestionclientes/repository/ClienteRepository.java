package yumbato.naomi.prueba.tecnica.gestionclientes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yumbato.naomi.prueba.tecnica.gestionclientes.entity.ClienteEntity;

import java.util.List;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
    @Query("""
        SELECT c FROM ClienteEntity c
        WHERE (
            :nombre IS NULL 
            OR c.nombreCompleto ILIKE CONCAT('%', CAST(:nombre AS string), '%')
        )
        AND (:nroDocumento IS NULL OR c.nroDocumento = :nroDocumento)
        AND (:tipoCliente IS NULL OR c.tipoCliente.id = :tipoCliente)
        """)
    Page<ClienteEntity> buscarClientesPorFiltro(
            @Param("nombre") String nombre,
            @Param("nroDocumento") String nroDocumento,
            @Param("tipoCliente") Integer tipoCliente,
            Pageable pageable
    );
    boolean existsByNroDocumento(String nroDocumento);
    boolean existsByNroDocumentoAndIdNot(String nroDocumento, Integer id);
    @Query(value = "SELECT * FROM rpt_totales_clientes_por_tipo()", nativeQuery = true)
    List<Object[]> obtenerTotalesPorTipo();
}
