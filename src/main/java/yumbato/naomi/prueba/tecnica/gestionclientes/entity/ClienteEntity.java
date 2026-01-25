package yumbato.naomi.prueba.tecnica.gestionclientes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "cliente")
public class ClienteEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre_completo", nullable = false, length = 50)
    private String nombreCompleto;
    @Column(name = "nro_documento", length = 8, nullable = false, unique = true)
    private String nroDocumento;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(nullable = false, length = 15)
    private String celular;
    @ManyToOne
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoClienteEntity tipoCliente;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;
    @LastModifiedDate
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
    @LastModifiedBy
    @Column(name = "modified_by")
    private String modifiedBy;
}
