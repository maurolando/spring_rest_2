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

import py.edu.facitec.model.Suscrito;
import py.edu.facitec.repository.SuscritoRepository;

@RestController //Arquitectura rest

@RequestMapping("/suscritos") //En plural en la parte Servidor. //URL que afecta a todos los métodos
public class SuscritoController {
	@Autowired //Inicializa en el contexto de Spring
	private SuscritoRepository suscritoRepository;
	
	@GetMapping //Responderá al verbo GET
	public ResponseEntity<List<Suscrito>>getAll(){
		//Realizamos la consulta y cargamos el objeto de Suscritos
		List<Suscrito>suscritos = suscritoRepository.findAll();
		
		//retornamos la lista con el status
		
		return new ResponseEntity<List<Suscrito>>(suscritos, HttpStatus.OK);
	}
		//Sirve para guardar															//
		@PostMapping												//Datos que vienen del cliente es el objeto request
		public ResponseEntity<Suscrito> create(@RequestBody Suscrito suscritoLlega){
			try {
				Suscrito suscritoRegistrado = suscritoRepository.save(suscritoLlega);
				
				System.out.println(suscritoRegistrado.toString());
				
				return new ResponseEntity<Suscrito>(suscritoRegistrado, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
						//recibimos el codigo
	@GetMapping(value = "/{codigo}")		//Cerramos la variable
	public ResponseEntity<Suscrito> getOne(@PathVariable Long codigo){
		//Ayuda para trabajar con valores nulos //Consulta por código
		Optional<Suscrito>suscritoConsulta = suscritoRepository.findById(codigo);
		
		if(suscritoConsulta.isPresent()) {
			return new ResponseEntity<Suscrito>(suscritoConsulta.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	//Verbo eliminar
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Suscrito> deleteById(@PathVariable Long codigo){
		
		//Lógica para eliminar a través de una tabla precargada.
		try {
			suscritoRepository.deleteById(codigo);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
}
