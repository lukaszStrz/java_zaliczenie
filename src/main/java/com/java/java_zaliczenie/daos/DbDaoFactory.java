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
public class DbDaoFactory extends DaoFactory {

    private DbDaoBook daobook;
    private DbDaoShelf daoshelf;
    private DbDaoStand daostand;
    private static DbDaoFactory instance;

    private DbDaoFactory() {
    }

    public static DbDaoFactory getInstance() {
        if (instance == null) {
            instance = new DbDaoFactory();
        }
        return instance;
    }

    @Override
    public DaoBook getBookDao() {
        if (daobook == null) {
            daobook = new DbDaoBook();
        }
        return daobook;
    }

    @Override
    public DaoShelf getShelfDao() {
        if (daoshelf == null) {
            daoshelf = new DbDaoShelf();
        }
        return daoshelf;
    }

    @Override
    public DaoStand getStandDao() {
        if (daostand == null) {
            daostand = new DbDaoStand();
        }
        return daostand;
    }
}
