package com.leo.springboot.backend.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leo.springboot.backend.apirest.models.entity.Cliente;
import com.leo.springboot.backend.apirest.models.service.ClienteServiceImpl;

//Para saber de cual local host se va a recibir la información
@CrossOrigin(origins = "http://localhost:4200")
//Determina que es una api de consumo
@RestController
//Inicio de la api en el navegador después del localhost
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	private ClienteServiceImpl clienteService;

	//Ruta de acceso para filtrar clientes
	@GetMapping("/clientes")
	public List<Cliente> listar(){
		return clienteService.listar();
	}
	
	//Se va a crear un objeto por eso el post y el RequestBody es para que cuando venga desde el cliente en formato json pueda tratarse acá
	@PostMapping("/clientes")
	//ResponseEntity para poder hacer el control de errores
	public ResponseEntity<?> guardar(@RequestBody Cliente cliente){
		
		//Creamos un clinere nuevo igualandolo a null para poder hacer la excepción
		//Con el mapa vamos a guardar la mayoría de datos
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			
			clienteNew = clienteService.guardar(cliente);
		}catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Ha ocurrido un error al intentar agregar al cliente ");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//Si todo sale bien que se haga esto
		response.put("mensaje", "Cliente guardado con éxito");
		response.put("cliente", clienteNew);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> buscarId(@PathVariable Long id){
		
		Cliente clienteEncontrar = clienteService.porId(id);
		Map<String, Object> response = new HashMap<>();
		if(clienteEncontrar == null) {
			
			response.put("mensaje", "Ha ocurrido un error al intentar buscar al cliente con id "+id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		response.put("mensaje", "Cliente encontrado con éxito");
		response.put("cliente", clienteEncontrar);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
		
	}
	
	//Se va a crear un objeto por eso el post y el RequestBody es para que cuando venga desde el cliente en formato json pueda tratarse acá
	@PutMapping("/clientes/{id}")
	//ResponseEntity para poder hacer el control de errores
	public ResponseEntity<?> editar(@RequestBody Cliente cliente, @PathVariable Long id){
		
		//Creamos un clinere nuevo igualandolo a null para poder hacer la excepción
		//Con el mapa vamos a guardar la mayoría de datos
		Cliente clienteEdit = clienteService.porId(id);
		Map<String, Object> response = new HashMap<>();

		if (clienteEdit == null) {
			response.put("mensaje", "Ha ocurrido un error al intentar editar al cliente ");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
		
		//Si todo sale bien que se haga esto
		clienteEdit.setNombre(cliente.getNombre());
		clienteEdit.setApellido(cliente.getApellido());
		clienteEdit.setEmail(cliente.getEmail());
		clienteEdit.setCreateAt(cliente.getCreateAt());
		clienteEdit = clienteService.guardar(clienteEdit);
		response.put("mensaje", "Cliente editado con éxito");
		response.put("cliente", clienteEdit);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		Map<String, Object> response = new HashMap<>();
		try {
			
			clienteService.eliminar(id);
		}catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Ha ocurrido un error al intentar eliminar al cliente con id "+id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Cliente eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
