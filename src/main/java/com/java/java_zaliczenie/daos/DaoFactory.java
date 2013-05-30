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
public abstract class DaoFactory {

    public abstract DaoBook getBookDao();

    public abstract DaoShelf getShelfDao();

    public abstract DaoStand getStandDao();
}
