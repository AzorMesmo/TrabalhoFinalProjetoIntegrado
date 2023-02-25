package br.edu.projeto.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import org.primefaces.PrimeFaces;

import br.edu.projeto.dao.AdminsDAO;
import br.edu.projeto.model.Admins;

@ViewScoped
@Named
public class AdminRegisterController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;
	
	@Inject
    transient private Pbkdf2PasswordHash passwordHash;
	
	@Inject
    private AdminsDAO adminDAO;
	
	private Admins admin;
	
	private List<Admins> adminList;

    @PostConstruct
    public void init() {
    	if (!adminDAO.isAdmin(this.facesContext.getExternalContext().getRemoteUser())) {
    		try {
				this.facesContext.getExternalContext().redirect("login-error.xhtml");
			} catch (IOException e) {e.printStackTrace();}
    	}
    }
	
	public void register() {
        this.setAdmin(new Admins());
    }
	
	public void save() {
        if (isValid()) {
        	try {
        		this.admin.setPassword(this.passwordHash.generate(this.admin.getPassword().toCharArray()));
        		if (this.admin.getLogin() == null) {
		        	this.adminDAO.save(this.admin);
		        	this.facesContext.addMessage(null, new FacesMessage("Admin Cadastrado"));
		        } else {
		        	this.adminDAO.update(this.admin);
		        	this.facesContext.addMessage(null, new FacesMessage("Admin Atualizado"));
		        }
		        this.adminList = adminDAO.listAll();
			    PrimeFaces.current().executeScript("PF('usuarioDialog').hide()");
			    PrimeFaces.current().ajax().update("form:messages", "form:dt-usuarios");
	        } catch (Exception e) {
	            String errorMessage = getErrorMessage(e);
	            this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
	        }
        }
	}	
	
	private boolean isValid() {		
		if (this.admin.getLogin() == null && !this.adminDAO.isUnique(this.admin.getLogin())) {
			this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Este login já está em uso.", null));
			return false;
		}
		return true;
	}
	
	public void delete() {
		try {
			this.adminDAO.delete(this.admin);
			this.adminList = adminDAO.listAll();
			this.admin = null;
	        this.facesContext.addMessage(null, new FacesMessage("Administrador Removido"));
	        PrimeFaces.current().ajax().update("form:messages", "form:dt-usuarios");
        } catch (Exception e) {
            String errorMessage = getErrorMessage(e);
            this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
        }
	}
	
	public void alter() {
		this.admin.setPassword("");
	}
	
	private String getErrorMessage(Exception e) {
        String error = "Aconteceu um erro!. Contacte um administrador do sistema.";
        if (e == null) 
            return error;
        Throwable t = e;
        while (t != null) {
            error = t.getLocalizedMessage();
            t = t.getCause();
        }
        return error;
    }
	
	public Admins getAdmin() {
		return admin;
	}

	public void setAdmin(Admins admin) {
		this.admin = admin;
	}

	public List<Admins> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<Admins> adminList) {
		this.adminList = adminList;
	}
}
