package br.edu.projeto.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Portfolios{
	
	@Id
	@NotEmpty
	@Column(name = "link")
	@Size(min = 1, max = 256, message = "O link deve possuir no máximo 256 caracteres.")
	private String link;
	
	@NotNull
	@NotEmpty
	@Column(name = "date")
	private Timestamp date;
	
	@NotNull
	@Column(name = "highlight")
	private boolean highlight;
	
	@NotNull
	@Column(name = "owner")
	private String owner;
	
	// GETTERS & SETTERS
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public Timestamp getDate() {
		return date;
	}
	
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public boolean getHighlight() {
		return highlight;
	}
	
	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}