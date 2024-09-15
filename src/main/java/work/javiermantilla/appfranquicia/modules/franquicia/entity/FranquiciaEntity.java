package work.javiermantilla.appfranquicia.modules.franquicia.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "franquicia", uniqueConstraints = {
	    @UniqueConstraint(name = "nombre_UNIQUE", columnNames = {"nombre"})
	})
public class FranquiciaEntity implements Serializable {

	private static final long serialVersionUID = 1504389609407931486L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Boolean estado;
    
}
