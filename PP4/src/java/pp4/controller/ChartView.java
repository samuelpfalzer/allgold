/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pp4.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;
import pp4.model.Inventar;
import pp4.model.Produkt;
import pp4.model.Verkaufsstelle;

/**
 *
 * @author samu
 */
@ManagedBean
public class ChartView implements Serializable {
    private PieChartModel pie;

    @EJB
    private pp4.controller.ProduktFacade produktFacade;
    
    
    @EJB
    private pp4.controller.InventarFacade inventarFacade;
    
    
    @PostConstruct
    public void init() {
        createPieModels();
    }
    
    public PieChartModel getPie() {
        return pie;
    }
    
    private void createPieModels() {
        // Anteil eines Produkts am Gesamtlagerbestand
        pie = new PieChartModel();
        List<Produkt> produktListe = produktFacade.findAll();
        
        for (Produkt p : produktListe) {
            pie.set(p.getName(), inventarFacade.getProduktGesamtMenge(p));
        }
    }    
    
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
 
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
