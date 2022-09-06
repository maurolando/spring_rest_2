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

import py.edu.facitec.model.Post;
import py.edu.facitec.model.Post;
import py.edu.facitec.repository.PostRepository;

@RestController //Arquitectura rest

@RequestMapping("/posts") //En plural en la parte Servidor. //URL que afecta a todos los métodos
public class PostController {
	@Autowired //Inicializa en el contexto de Spring
	private PostRepository postRepository;
	
	@GetMapping //Responderá al verbo GET
	public ResponseEntity<List<Post>>getAll(){
		//Realizamos la consulta y cargamos el objeto de Posts
		List<Post>posts = postRepository.findAll();
		
		//retornamos la lista con el status
		
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
		//Sirve para guardar															//
		@PostMapping												//Datos que vienen del cliente es el objeto request
		public ResponseEntity<Post> create(@RequestBody Post postLlega){
			try {
				Post postRegistrado = postRepository.save(postLlega);
				
				System.out.println(postRegistrado.toString());
				
				return new ResponseEntity<Post>(postRegistrado, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
						//recibimos el codigo
	@GetMapping(value = "/{codigo}")		//Cerramos la variable
	public ResponseEntity<Post> getOne(@PathVariable Long codigo){
		//Ayuda para trabajar con valores nulos //Consulta por código
		Optional<Post>postConsulta = postRepository.findById(codigo);
		
		if(postConsulta.isPresent()) {
			return new ResponseEntity<Post>(postConsulta.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	//Verbo eliminar
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Post> deleteById(@PathVariable Long codigo){
		
		//Lógica para eliminar a través de una tabla precargada.
		try {
			postRepository.deleteById(codigo);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
}
