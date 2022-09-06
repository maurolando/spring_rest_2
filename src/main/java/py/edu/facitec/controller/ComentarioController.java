package py.edu.facitec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.facitec.model.Comentario;
import py.edu.facitec.repository.ComentarioRepository;

@RestController //Arquitectura rest

@RequestMapping("/comentarios") //En plural en la parte Servidor. //URL que afecta a todos los métodos
public class ComentarioController {
	@Autowired //Inicializa en el contexto de Spring
	private ComentarioRepository comentarioRepository;
	
	@GetMapping //Responderá al verbo GET
	public ResponseEntity<List<Comentario>>getAll(){
		//Realizamos la consulta y cargamos el objeto de Comentarios
		List<Comentario>comentarios = comentarioRepository.findAll();
		
		//retornamos la lista con el status
		
		return new ResponseEntity<List<Comentario>>(comentarios, HttpStatus.OK);
	}
		//Sirve para guardar															//
		@PostMapping												//Datos que vienen del cliente es el objeto request
		public ResponseEntity<Comentario> create(@RequestBody Comentario comentarioLlega){
			try {
				Comentario comentarioRegistrado = comentarioRepository.save(comentarioLlega);
				
				System.out.println(comentarioRegistrado.toString());
				
				return new ResponseEntity<Comentario>(comentarioRegistrado, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
						//recibimos el codigo
	@GetMapping(value = "/{codigo}")		//Cerramos la variable
	public ResponseEntity<Comentario> getOne(@PathVariable Long codigo){
		//Ayuda para trabajar con valores nulos //Consulta por código
		Optional<Comentario>comentarioConsulta = comentarioRepository.findById(codigo);
		
		if(comentarioConsulta.isPresent()) {
			return new ResponseEntity<Comentario>(comentarioConsulta.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	//Verbo eliminar
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Comentario> deleteById(@PathVariable Long codigo){
		
		//Lógica para eliminar a través de una tabla precargada.
		try {
			comentarioRepository.deleteById(codigo);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
}
