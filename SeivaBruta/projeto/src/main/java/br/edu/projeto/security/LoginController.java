package br.edu.projeto.security;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.projeto.model.Admins;

@Named 
@RequestScoped
public class LoginController {

	@Inject
    private FacesContext facesContext;

    @Inject
    private SecurityContext securityContext;

    private Admins admin;

    @PostConstruct
    public void startAdmin() {
        admin = new Admins();
    }

    public void login() throws IOException{
    	if (facesContext.getExternalContext().getAuthType() != null) {
    		try {
    			throw new Exception();       	
            } catch (Exception e) {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Já existe um usuário logado.", ""));
            }
    	} else {
	    	Credential credential = new UsernamePasswordCredential(admin.getLogin(), new Password(admin.getPassword()));
	    	AuthenticationStatus status = securityContext.authenticate(
	    	        	(HttpServletRequest)facesContext.getExternalContext().getRequest(),
	    	        	(HttpServletResponse)facesContext.getExternalContext().getResponse(),
	    	            AuthenticationParameters.withParams().credential(credential));
	    	if (status.equals(AuthenticationStatus.SUCCESS))
	    		facesContext.getExternalContext().redirect("default.xhtml");
	    	else if (status.equals(AuthenticationStatus.SEND_FAILURE)) {
	    		admin = new Admins();
	    		try {
	            	throw new Exception();       	
	            } catch (Exception e) {
	                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Inválido!", "Usuário ou senha incorretos."));
	            }
	    	}
    	}
    }
    
    public void logout() throws IOException {
    	facesContext.getExternalContext().invalidateSession();
    	facesContext.getExternalContext().redirect("logout.xhtml");
    }
    
	public Admins getAdmin() {
		return admin;
	}

	public void setUsuario(Admins admin) {
		this.admin = admin;
	}
}