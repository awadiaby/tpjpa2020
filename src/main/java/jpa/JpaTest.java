package jpa;

import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.Date;

public class JpaTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        EntityManager manager = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();


        try {

            createTableaux(manager);
            createFiche(manager);

        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();


        manager.close();
        EntityManagerHelper.closeEntityManagerFactory();
        //		factory.close();
    }

    private static void createTableaux(EntityManager manager) {
        Section section = new Section();
        section.setStatus(TypeSection.ENCOURS);
        manager.persist(section);

        Tableau tableau = new Tableau();
        tableau.setNom("Kanban 1");
        tableau.setDateCreation(new Date());
        tableau.setSection(section);

        manager.persist(tableau);
    }

    private static void createFiche(EntityManager manager) {
        Collaborateur collaborateur = new Collaborateur();
        collaborateur.setPrenom("Awa");
        collaborateur.setEmail("awadiaby19@gmail.com");
        collaborateur.setNom("Diaby");
        manager.persist(collaborateur);

        Fiche fiche = new Fiche();
        fiche.setCommit("addd");
        fiche.setDate(new Date());
        fiche.setDureeTache(5 * 7 * 60L);
        fiche.setLibelle("Extraction de données");
        fiche.setUrl("jdkhzfkj");
        fiche.setNote("bien il manque des choses");
        fiche.setLieu("Rennes");
        fiche.setCollaborateur(collaborateur);

        manager.persist(fiche);
    }
}
