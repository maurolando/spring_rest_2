package py.edu.facitec.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass //Sirve para indicar herencia
public class General {
	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Recurre al auto incremento de la base de datos
	private Long id;
	
	public General() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "General [id=" + id + "]";
	}
	
}

