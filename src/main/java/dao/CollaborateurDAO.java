package dao;

import entities.Collaborateur;
import jpa.EntityManagerHelper;

import javax.persistence.EntityTransaction;

public class CollaborateurDAO {
    public void saveCollaborateur(Collaborateur clb) {
        EntityTransaction t = EntityManagerHelper.getEntityManager().getTransaction();
        t.begin();
        EntityManagerHelper.getEntityManager().persist(clb);
        t.commit();
    }
}