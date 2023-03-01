package br.edu.projeto.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

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

import br.edu.projeto.dao.BudgetsDAO;
import br.edu.projeto.model.Budgets;

@ViewScoped
@Named
public class BudgetRegisterController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;
	
	@Inject
    private BudgetsDAO budgetDAO;
	
	private Budgets budget;
	
	private List<Budgets> budgetList;

	public void register() {
        this.setBudget(new Budgets());
    }
	
	public void save() {
		try {
			register();
			this.budget.setOwner(this.facesContext.getExternalContext().getRemoteUser());
			this.budgetDAO.save(this.budget);
			this.facesContext.addMessage(null, new FacesMessage("Orçamento Enviado"));
			this.facesContext.getExternalContext().redirect("index.xhtml");
		} catch (Exception e) {
			String errorMessage = getErrorMessage(e);
			this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
		}
	}
	
	public void update() {
		this.budgetDAO.update(this.budget);
		this.facesContext.addMessage(null, new FacesMessage("Orçamento Editado"));
		try {
			this.facesContext.getExternalContext().redirect("view_budget.xhtml");
		} catch (Exception e) {
			String errorMessage = getErrorMessage(e);
            this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
		}
	}
	
	public void delete() {
		try {
			this.budgetDAO.delete(this.budget);
			this.budgetList = budgetDAO.listAll();
			this.budget = null;
	        this.facesContext.addMessage(null, new FacesMessage("Orçamento Removido"));
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
	
	public Budgets getBudget() {
		return budget;
	}

	public void setBudget(Budgets budget) {
		this.budget = budget;
	}

	public List<Budgets> getBudgetList() {
		return budgetList;
	}

	public void setBudgetList(List<Budgets> budgetList) {
		this.budgetList = budgetList;
	}
}
