package yumbato.naomi.prueba.tecnica.gestionclientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yumbato.naomi.prueba.tecnica.gestionclientes.entity.TipoClienteEntity;

public interface TipoClienteRepository extends JpaRepository<TipoClienteEntity, Integer> {
}
