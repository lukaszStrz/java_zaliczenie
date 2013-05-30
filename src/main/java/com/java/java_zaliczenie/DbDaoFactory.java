/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.java_zaliczenie;

import com.java.java_zaliczenie.daos.DbDaoBook;
import com.java.java_zaliczenie.daos.DbDaoShelf;
import com.java.java_zaliczenie.daos.DbDaoStand;
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
