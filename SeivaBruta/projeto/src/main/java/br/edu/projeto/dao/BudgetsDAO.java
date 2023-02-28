package br.edu.projeto.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edu.projeto.model.Budgets;
import br.edu.projeto.model.Admins;

@Stateful
public class BudgetsDAO implements Serializable{

	@Inject
    private EntityManager em;
	
	public Budgets findId(Integer id) {
        return em.find(Budgets.class, id);
    }
	
	public List<Budgets> listAll() {
	    return em.createQuery("SELECT b FROM Budgets b ", Budgets.class).getResultList();      
	}
	
	public List<Budgets> listByAdmin(String login) {
	    return em.createQuery("SELECT b FROM Budgets b WHERE delegate = :login", Budgets.class).setParameter("login", login).getResultList();      
	}
	
	public List<Budgets> listNotDelegated() {
		return em.createQuery("SELECT b FROM Budgets b WHERE delegate IS NULL", Budgets.class).getResultList();
	}
	
	public void save(Budgets budget) {
		em.persist(budget);
	}
	
	public void update(Budgets budget) {
		em.merge(budget);
	}
	
	public void delete(Budgets budget) {
		em.remove(em.getReference(Budgets.class, budget.getId()));
	}
	
}
