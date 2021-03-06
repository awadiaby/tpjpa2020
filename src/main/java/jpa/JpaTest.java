package jpa;

import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

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
            recupererCollaborateurs(manager);
            recupererFiche(manager);
            recupererFicheAujoudhui(manager);

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
        CollaborateurMetier collaborateur = new CollaborateurMetier();
        collaborateur.setPrenom("Awa");
        collaborateur.setEmail("awadiaby19@gmail.com");
        collaborateur.setNom("Diaby");
        collaborateur.setRole("RelationClient");
        manager.persist(collaborateur);

        CollaborateurTechnique collaborateur1 = new CollaborateurTechnique();
        collaborateur1.setRoleTech("Developpeur");
        collaborateur1.setPrenom("Amadou");
        collaborateur1.setEmail("amadia@gmail.com");
        collaborateur1.setNom("Dia");
        collaborateur1.setRoleTech("Dev");
        manager.persist(collaborateur1);

        Fiche fiche = new Fiche();
        fiche.setCommit("addd");
        fiche.setDate(new Date());
        fiche.setDureeTache(5 * 7 * 60L);
        fiche.setLibelle("Extraction de données");
        fiche.setUrl("jdkhzfkj");
        fiche.setNote("bien il manque des choses");
        fiche.setLieu("Rennes");
        fiche.setCollaborateur(collaborateur);
        collaborateur.getFiches().add(fiche);

        manager.persist(fiche);
    }

    private static void recupererCollaborateurs(EntityManager manager) {
        String s = "select c from Collaborateur as c"; //requete normal
        Query query = manager.createQuery(s, Collaborateur.class);

        List<Collaborateur> res = query.getResultList();
        for (Collaborateur collaborateur : res) {
            System.out.println("Affich ensemble collabaorateur : " + collaborateur);
        }
        String p = "select c from Collaborateur as c join Fiche as f on c.id = f.collaborateur.id";

        Query q = manager.createQuery(p, Collaborateur.class); //q.setParameter("prenom", "Awa");
        List<Collaborateur> res1 = q.getResultList();
        for (Collaborateur collaborateur : res1) {
            System.out.println("Affich ensemble collabaorateur : " + collaborateur);
        }
//
        System.err.println(res.size());
        //System.err.println(res.get(0).getNom());

        //  manager.close();

    }

    private static void recupererFiche(EntityManager manager) {
        Query query = manager.createNamedQuery("TouteslesFiches");
        List<Fiche> res = query.getResultList();
        for (Fiche fiche : res) {
            System.out.println("Affiche ensemble des fiches : " + fiche);
        }
    }

    private static void recupererFicheAujoudhui(EntityManager manager) {
        Query query = manager.createNamedQuery("ToutesLesFichesAujoudhui");
        query.setParameter("date", new Date());
        List<Fiche> res = query.getResultList();
        for (Fiche fiche : res) {
            System.out.println("Affiche ensemble des fiches Aujoud'hui : " + fiche);
        }
    }

}
