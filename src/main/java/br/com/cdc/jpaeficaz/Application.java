package br.com.cdc.jpaeficaz;

import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Application {
	
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws SQLException {
    	
    	final String PERSISTENCE_UNIT = "CDC_JPA_EFICAZ_PU";
    	
        startStandaloneApp();

        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        Pessoa pessoa = new Pessoa("Abraão", "Santana");
        
        entityManager.getTransaction().begin();
        entityManager.persist(pessoa);
        entityManager.getTransaction().commit();

        LOG.info("Acesso ao H2 Database em http://localhost:8082/");
        LOG.info("Utilizar JDBC URL: jdbc:h2:mem:jpaeficaz");
        LOG.info("User Name: cdc");
        LOG.info("Password: ");

    }

    private static void startStandaloneApp() throws SQLException {
        new Server().runTool("-tcp", "-web", "-ifNotExists");
    }
}
