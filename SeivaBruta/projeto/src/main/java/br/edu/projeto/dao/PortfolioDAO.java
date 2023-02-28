package br.edu.projeto.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edu.projeto.model.Portfolios;

@Stateful
public class PortfolioDAO{

	@Inject
    private EntityManager em;
	
	public Portfolios findLink(String link) {
        return em.find(Portfolios.class, link);
    }
	
	public Boolean isUnique(String link) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Portfolios> criteria = cb.createQuery(Portfolios.class);
        Root<Portfolios> portfolio = criteria.from(Portfolios.class);
        criteria.select(portfolio);
        criteria.where(cb.like(portfolio.get("link"), link));
        if (em.createQuery(criteria).getResultList().isEmpty())
        	return true;
        return false;
    }

	public List<Portfolios> listAll() {
	    return em.createQuery("SELECT p FROM Portfolios p ", Portfolios.class).getResultList();      
	}
	
	public void save(Portfolios portfolio) {
		em.persist(portfolio);
	}
	
	public void update(Portfolios portfolio) {
		em.merge(portfolio);
	}
	
	public void delete(Portfolios portfolio) {
		em.remove(em.getReference(Portfolios.class, portfolio.getLink()));
	}
	
}
