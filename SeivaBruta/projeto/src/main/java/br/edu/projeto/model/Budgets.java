package br.edu.projeto.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Budgets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "owner")
    private String owner;

    @NotNull
    @NotEmpty
	@Column(name = "size")
	@Size(min = 1, max = 64, message = "O tamanho deve ter no máximo 64 caracteres.")
    private String size;
	
	@NotNull
    @NotEmpty
	@Column(name = "spot")
	@Size(min = 1, max = 128, message = "O local deve ter no máximo 128 caracteres.")
    private String spot;
	
	@NotNull
    @NotEmpty
	@Column(name = "ref_1")
	@Size(min = 1, max = 256, message = "O link 1 deve ter no máximo 256 caracteres.")
    private String ref1;
	
	@Column(name = "ref_2")
	@Size(min = 1, max = 256, message = "O link 2 deve ter no máximo 256 caracteres.")
    private String ref2;
	
	@Column(name = "ref_3")
	@Size(min = 1, max = 256, message = "O link 3 deve ter no máximo 256 caracteres.")
    private String ref3;
	
	@Column(name = "ref_4")
	@Size(min = 1, max = 256, message = "O link 4 deve ter no máximo 256 caracteres.")
    private String ref4;
	
	@Column(name = "ref_5")
	@Size(min = 1, max = 256, message = "O link 5 deve ter no máximo 256 caracteres.")
    private String ref5;
    
    @Column(name = "delegate")
    private String delegate;
	
	@Column(name = "price")
	private float price;
    
    // GETTERS & SETTERS
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSpot() {
		return spot;
	}

	public void setSpot(String spot) {
		this.spot = spot;
	}

	public String getRef1() {
		return ref1;
	}

	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}
	
	public String getRef2() {
		return ref2;
	}

	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}
	
	public String getRef3() {
		return ref3;
	}

	public void setRef3(String ref3) {
		this.ref3 = ref3;
	}
	
	public String getRef4() {
		return ref4;
	}

	public void setRef4(String ref4) {
		this.ref4 = ref4;
	}
	
	public String getRef5() {
		return ref5;
	}

	public void setRef5(String ref5) {
		this.ref5 = ref5;
	}
	
	public String getDelegate() {
		return delegate;
	}

	public void setDelegate(String delegate) {
		this.delegate = delegate;
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}