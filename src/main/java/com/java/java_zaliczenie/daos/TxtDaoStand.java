/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.java_zaliczenie.daos;

import com.java.java_zaliczenie.Stand;
import com.java.java_zaliczenie.daos.interfaces.DaoStand;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Wojciech
 */
public class TxtDaoStand implements DaoStand {

    private List<Stand> stands = new ArrayList<Stand>();
    private Logger logger = Logger.getLogger(TxtDaoStand.class.getName());

    public void addStand(Stand stand) {
        String tmp = TxtDaoFactory.jsonText;
        stands = new JSONDeserializer<List<Stand>>().deserialize(tmp);
        stands.add(stand);
        TxtDaoFactory.jsonText = new JSONSerializer().deepSerialize(stands);
    }

    public void deleteStand(int id) {
        String tmp = TxtDaoFactory.jsonText;
        stands = new JSONDeserializer<List<Stand>>().deserialize(tmp);
        Stand stand = null;
        for (Stand s : stands) {
            if (s.getIdStand() == id) {
                stand = s;
                break;
            }
        }
        stands.remove(stand);
        TxtDaoFactory.jsonText = new JSONSerializer().deepSerialize(stands);
    }

    public List<Stand> getAllStands() {
        String tmp = TxtDaoFactory.jsonText;
        stands = new JSONDeserializer<List<Stand>>().deserialize(tmp);
        return stands;
    }

    public Stand getStandById(int id) {
        String tmp = TxtDaoFactory.jsonText;
        stands = new JSONDeserializer<List<Stand>>().deserialize(tmp);
        for (Stand s : stands) {
            if (s.getIdStand() == id) {
                return s;
            }
        }
        return null;
    }

    public void updateStand(Stand stand) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
