/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.java_zaliczenie.daos;

import com.java.java_zaliczenie.Shelf;
import com.java.java_zaliczenie.Stand;
import com.java.java_zaliczenie.daos.interfaces.DaoShelf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Wojciech
 */
public class XmlDaoShelf implements DaoShelf {
    
    Logger logger = Logger.getLogger(XmlDaoShelf.class.getName());
    List<Stand> memory;
    
    public XmlDaoShelf(List<Stand> memory) {
        this.memory = memory;
    }
    
    public void addShelf(Shelf shelf) {
        List<Shelf> shelves = getAllShelfs();
        List<Integer> ids = new ArrayList<Integer>();
        for (Shelf shlf : shelves) {
            ids.add(shlf.getIdShelf());
        }
        Collections.sort(ids);
        shelf.setIdShelf(ids.get(ids.size() - 1) + 1);
        
        for (Stand s : memory) {
            if (s.getIdStand().equals(shelf.getStand().getIdStand())) {
                s.getShelfs().add(shelf);
                break;
            }
        }
        XmlDaoFactory.getInstance().serialize();
    }
    
    public void deleteShelf(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Shelf> getAllShelfs() {
        List<Shelf> shelves = new ArrayList();
        for (Stand stand : memory) {
            for (Object shelf : stand.getShelfs()) {
                //shelves.add((Shelf) DeepCopy.copy(shelf));
                shelves.add((Shelf) shelf);
            }
        }
        return shelves;
    }
    
    public Shelf getShelfById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void updateShelf(Shelf shelf) {
        for (Stand stand : memory) {
            for (Object shel : stand.getShelfs()) {
                if (((Shelf) shel).getIdShelf().equals(shelf.getIdShelf())) {
                    ((Shelf) shel).setBooks(shelf.getBooks());
                    ((Shelf) shel).setShelfName(shelf.getShelfName());
                    logger.trace(shelf.getStand().getIdStand() + " == " + ((Shelf) shel).getStand().getIdStand());
                    if (!shelf.getStand().getIdStand().equals(((Shelf) shel).getStand().getIdStand())) { // jak id regałów się różnią to przestawia
                        logger.trace("Przenoszę półkę na inny regał");
                        ((Shelf) shel).getStand().getShelfs().remove(shel);
                        ((Shelf) shel).setStand(shelf.getStand());
                        shelf.getStand().getShelfs().add(shel);
                    }
                }
            }
        }
        XmlDaoFactory.getInstance().serialize();
    }
}