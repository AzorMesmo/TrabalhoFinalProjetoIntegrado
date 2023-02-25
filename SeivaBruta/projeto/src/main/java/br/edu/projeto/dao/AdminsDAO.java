package br.edu.projeto.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edu.projeto.model.Admins;

@Stateful
public class AdminsDAO{

	@Inject
    private EntityManager em;
	
	public Admins findLogin(String login) {
        return em.find(Admins.class, login);
    }
	
	public Boolean isUnique(String login) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Admins> criteria = cb.createQuery(Admins.class);
        Root<Admins> admin = criteria.from(Admins.class);
        criteria.select(admin);
        criteria.where(cb.like(admin.get("login"), login));
        if (em.createQuery(criteria).getResultList().isEmpty())
        	return true;
        return false;
    }

	public List<Admins> listAll() {
	    return em.createQuery("SELECT a FROM Usuario a ", Admins.class).getResultList();      
	}
	
	public void save(Admins admin) {
		em.persist(admin);
	}
	
	public void update(Admins admin) {
		em.merge(admin);
	}
	
	public void delete(Admins admin) {
		em.remove(em.getReference(Admins.class, admin.getLogin()));
	}
	
	public boolean isAdmin(String login) {
		if (em.find(Admins.class, login) != null) {
			return true;
		} else {
			return false;
		}
	}
	
}
