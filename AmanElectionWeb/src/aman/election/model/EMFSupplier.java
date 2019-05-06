package aman.election.model;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;

public class EMFSupplier {
	
	// Singleton supplies Entity Manager Factory for persistence unit
	private static EMFSupplier emfSupplier = null;
	private EntityManagerFactory emf = null;
	// the String in the next line must match the name in persistence.xml
	private String EMFJndiName ="java:/AmanElectionEMF";

	private EMFSupplier() {
		if (emf == null) {
			try {
				InitialContext ctx = new InitialContext();
				emf = (EntityManagerFactory) ctx.lookup( EMFJndiName);
			} catch (NamingException ne) {
				System.out.println("Please correct persistence unit: check persistence.xml");
				ne.printStackTrace();
			} 
		}
	}
	
    public static EMFSupplier getInstance() {
    	if (emfSupplier == null ) {
    		emfSupplier = new EMFSupplier();
    	}
    	return emfSupplier;
    }

    public EntityManagerFactory getEMF() {
    	return emf;
    }
}
