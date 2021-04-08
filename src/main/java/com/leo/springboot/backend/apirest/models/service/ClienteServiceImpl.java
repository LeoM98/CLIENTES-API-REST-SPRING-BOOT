package com.leo.springboot.backend.apirest.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.springboot.backend.apirest.models.dao.ClienteDao;
import com.leo.springboot.backend.apirest.models.entity.Cliente;

//Se denota la clase como un servicio
@Service
public class ClienteServiceImpl implements ClienteService {
	
	//Inyectamos la dependencia de la instancia que se encuentre de dicha clase
	@Autowired
	private ClienteDao clienteDao;

	@Override
	//El método solo es de lectura
	@Transactional(readOnly = true)
	public List<Cliente> listar() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public Cliente guardar(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteDao.save(cliente);
	}

	@Override
	public Cliente porId(Long id) {
		// TODO Auto-generated method stub
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		clienteDao.deleteById(id);
		
	}

}
