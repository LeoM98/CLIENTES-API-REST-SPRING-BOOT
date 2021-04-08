package com.leo.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//Declara y da a conocer a spring que está clase es una entidad
@Entity
//Nombre de la tabla
@Table(name = "clientes")
public class Cliente implements Serializable {

	//Mediante id se le dice a la base de datos que debe ser único y por el valor que debe ser incremental
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Los atributos no pueden ser falsos
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false)
	private String apellido;
	private String email;
	
	//Renombrando columna y datos temporales
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	//Antes de crearse un nuevo valor para la tabla se crea una fecha
	@PrePersist
	public void prepersist() {
		this.createAt = new Date();
	}
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Date getCreateAt() {
		return createAt;
	}



	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	private static final long serialVersionUID = 1L;

}
