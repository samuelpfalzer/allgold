/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pp4.controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pp4.model.Inventar;
import pp4.model.Produkt;

/**
 *
 * @author samu
 */
@Stateless
public class InventarFacade extends AbstractFacade<Inventar> {

    @PersistenceContext(unitName = "PP4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InventarFacade() {
        super(Inventar.class);
    }
    
    
    /* Eigene Methoden */
    public List<Inventar> inventarByProdukt(Produkt produktId) {
        return em.createNamedQuery("Inventar.findByProduktId").setParameter("produktId", produktId).getResultList();
    }
    
    public int getProduktGesamtMenge(Produkt p) {
         List<Inventar> inv = inventarByProdukt(p);
         int menge = 0;
         for (Inventar i : inv) {
             menge += i.getVorrat();
         }
         return menge;
    }
    
}
