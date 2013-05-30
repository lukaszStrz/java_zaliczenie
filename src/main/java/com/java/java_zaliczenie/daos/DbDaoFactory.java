/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.java_zaliczenie.daos;

import com.java.java_zaliczenie.daos.interfaces.*;

/**
 *
 * @author Wojciech
 */
public class DbDaoFactory extends DaoFactory {

    @Override
    public DaoBook getBookDao() {
        return new DbDaoBook();
    }

    @Override
    public DaoShelf getShelfDao() {
        return new DbDaoShelf();
    }

    @Override
    public DaoStand getStandDao() {
        return new DbDaoStand();
    }
}
