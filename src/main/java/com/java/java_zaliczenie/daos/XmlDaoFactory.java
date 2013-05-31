/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.java_zaliczenie.daos;

import com.java.java_zaliczenie.daos.interfaces.DaoBook;
import com.java.java_zaliczenie.daos.interfaces.DaoShelf;
import com.java.java_zaliczenie.daos.interfaces.DaoStand;

/**
 *
 * @author Wojciech
 */
public class XmlDaoFactory extends DaoFactory {

    private XmlDaoBook daobook;
    private XmlDaoShelf daoshelf;
    private XmlDaoStand daostand;
    private static XmlDaoFactory instance;

    private XmlDaoFactory() {
    }

    public static XmlDaoFactory getInstance() {
        if (instance == null) {
            instance = new XmlDaoFactory();
        }
        return instance;
    }

    @Override
    public DaoBook getBookDao() {
        if (daobook == null) {
            daobook = new XmlDaoBook();
        }
        return new XmlDaoBook();
    }

    @Override
    public DaoShelf getShelfDao() {
        if (daoshelf == null) {
            daoshelf = new XmlDaoShelf();
        }
        return new XmlDaoShelf();
    }

    @Override
    public DaoStand getStandDao() {
        if (daostand == null) {
            daostand = new XmlDaoStand();
        }
        return new XmlDaoStand();
    }
}
