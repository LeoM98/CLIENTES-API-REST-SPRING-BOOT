package com.leo.springboot.backend.apirest.models.service;

import java.util.List;

import com.leo.springboot.backend.apirest.models.entity.Cliente;

public interface ClienteService {
	
	public List<Cliente> listar();
	public Cliente guardar(Cliente cliente);
	public Cliente porId(Long id);
	public void eliminar(Long id);

}
