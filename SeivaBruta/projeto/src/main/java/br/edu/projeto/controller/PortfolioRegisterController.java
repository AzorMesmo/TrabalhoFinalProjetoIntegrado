package br.edu.projeto.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.sql.Date;
import java.sql.Timestamp;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import br.edu.projeto.dao.PortfolioDAO;
import br.edu.projeto.model.Portfolios;

@ViewScoped
@Named
public class PortfolioRegisterController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;
	
	@Inject
    private PortfolioDAO portfolioDAO;
	
	private Portfolios portfolio;
	
	private List<Portfolios> portfolioList;
	
	public void register() {
        this.setPortfolio(new Portfolios());
    }
	
	public void save() {
    	try {
    		this.portfolio.setDate(new Timestamp(System.currentTimeMillis()));
    		this.portfolio.setHighlight(false);
    		this.portfolio.setOwner(this.facesContext.getExternalContext().getRemoteUser());
    		this.portfolioDAO.save(this.portfolio);
        	this.facesContext.addMessage(null, new FacesMessage("Imagem Enviada"));
        	this.facesContext.getExternalContext().redirect("portfolio.xhtml");
        } catch (Exception e) {
            String errorMessage = getErrorMessage(e);
            this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
        }
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
	
	public Portfolios getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolios portfolio) {
		this.portfolio = portfolio;
	}

	public List<Portfolios> getPortfolioList() {
		return portfolioList;
	}

	public void setAdminList(List<Portfolios> portfolioList) {
		this.portfolioList = portfolioList;
	}
}
