/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pp4.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pp4.model.Verkaufsstelle;

/**
 *
 * @author samu
 */
@Stateless
public class VerkaufsstelleFacade extends AbstractFacade<Verkaufsstelle> {

    @PersistenceContext(unitName = "PP4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VerkaufsstelleFacade() {
        super(Verkaufsstelle.class);
    }
    
}
