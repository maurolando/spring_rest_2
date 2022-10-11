package py.edu.facitec.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Suscrito {
	
	@Id
	@GeneratedValue
	private Long codigo;
	private String nombre;
	private String correo;
	
	@JsonIgnore //Cuando la lista no es importante
	@OneToMany(mappedBy = "suscrito")
	private List<Comentario>comentarios;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public String toString() {
		return "Suscrito [codigo=" + codigo + ", nombre=" + nombre + ", correo=" + correo + ", comentarios="
				+ comentarios + "]";
	}
	
	
	


	
}
