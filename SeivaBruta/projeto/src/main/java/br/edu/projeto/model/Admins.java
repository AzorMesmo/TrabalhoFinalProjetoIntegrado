package br.edu.projeto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Admins{
	
	@Id
	@NotEmpty
	@Column(name = "login")
	@Size(min = 1, max = 32, message = "O login deve possuir no máximo 32 caracteres.")
	private String login;
	
	@NotNull
	@NotEmpty
	@Column(name = "password")
	@Size(min = 1, max = 128, message = "A senha deve possuir no máximo 128 caracteres.")
	private String password;
	
	@Column(name = "name")
	@Size(min = 1, max = 64, message = "O nome deve possuir no máximo 64 caracteres.")
	private String name;
	
	@Column(name = "description")
	@Size(min = 1, max = 512, message = "A descrição deve possuir no máximo 512 caracteres.")
	private String description;
	
	@Column(name = "picture")
	@Size(min = 1, max = 256, message = "O link da foto deve possuir no máximo 256 caracteres.")
	private String picture;
	
	@Column(name = "contact")
	@Size(min = 1, max = 128, message = "O contato deve possuir no máximo 128 caracteres.")
	private String contact;
	
	// GETTERS & SETTERS
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPicture() {
		return picture;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public String getContact() {
		return contact;
	}
	
	public void setContact(String contact) {
		this.contact = contact;
	}
	
}