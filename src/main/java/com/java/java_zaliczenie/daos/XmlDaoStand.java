/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.java_zaliczenie.daos;

import com.java.java_zaliczenie.Stand;
import com.java.java_zaliczenie.daos.interfaces.DaoStand;
import com.java.java_zaliczenie.utils.DeepCopy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Wojciech
 */
public class XmlDaoStand implements DaoStand {

    Logger logger = Logger.getLogger(XmlDaoStand.class.getName());
    List<Stand> memory;

    public XmlDaoStand(List<Stand> memory) {
        this.memory = memory;
    }

    public void addStand(Stand stand) {
        List<Stand> stands = getAllStands();
        List<Integer> ids = new ArrayList<Integer>();
        for (Stand stnd : stands) {
            ids.add(stnd.getIdStand());
        }
        Collections.sort(ids);
        stand.setIdStand(ids.get(ids.size() - 1) + 1);
        memory.add(stand);
        XmlDaoFactory.getInstance().serialize();
    }

    public void deleteStand(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Stand> getAllStands() {
        return memory;
    }

    public Stand getStandById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateStand(Stand stand) {
        for (Stand st : memory) {
            if (st.getIdStand().equals(stand.getIdStand())) {
                st.setShelfs(stand.getShelfs());
                st.setStandName(stand.getStandName());
            }
        }
        XmlDaoFactory.getInstance().serialize();
    }
}
