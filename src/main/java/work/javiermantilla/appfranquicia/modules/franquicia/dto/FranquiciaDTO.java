package work.javiermantilla.appfranquicia.modules.franquicia.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FranquiciaDTO implements Serializable {

	private static final long serialVersionUID = 6198043062145430662L;
	
	@Null
	private Integer id;
	@NotNull
	@Size(min = 2, max = 45)
	private String nombre;
	@Null
	private Boolean estado;
}
