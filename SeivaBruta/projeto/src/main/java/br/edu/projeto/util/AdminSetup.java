package br.edu.projeto.util;

import javax.inject.Inject;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.edu.projeto.dao.AdminsDAO;
import br.edu.projeto.model.Admins;

@WebListener
public class AdminSetup implements ServletContextListener {

	@Inject
    private Pbkdf2PasswordHash passwordHash;

    @Inject
    private AdminsDAO adminDAO;

    private Admins admin;
    
    public void contextInitialized(ServletContextEvent event) {
        if (adminDAO.isUnique("admin")){ 	
	    	admin = new Admins();
	        admin.setLogin("admin");
	        String defaultPassword = "admin";
	        admin.setPassword(passwordHash.generate(defaultPassword.toCharArray()));
	        adminDAO.save(admin);
        }
    }
}
