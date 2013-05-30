/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.java_zaliczenie;

import com.java.java_zaliczenie.daos.interfaces.*;

/**
 *
 * @author Wojciech
 */
public abstract class DaoFactory {

    public abstract DaoBook getBookDao();

    public abstract DaoShelf getShelfDao();

    public abstract DaoStand getStandDao();
}
