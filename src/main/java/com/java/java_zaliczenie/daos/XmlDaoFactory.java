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

    @Override
    public DaoBook getBookDao() {
        return new XmlDaoBook();
    }

    @Override
    public DaoShelf getShelfDao() {
        return new XmlDaoShelf();
    }

    @Override
    public DaoStand getStandDao() {
        return new XmlDaoStand();
    }
}
