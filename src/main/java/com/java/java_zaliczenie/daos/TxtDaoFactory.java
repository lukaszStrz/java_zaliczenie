/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.java_zaliczenie.daos;

import com.java.java_zaliczenie.Stand;
import com.java.java_zaliczenie.daos.interfaces.DaoBook;
import com.java.java_zaliczenie.daos.interfaces.DaoShelf;
import com.java.java_zaliczenie.daos.interfaces.DaoStand;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wojciech
 */
public class TxtDaoFactory extends DaoFactory {

    TxtDaoBook daobook;
    TxtDaoShelf daoshelf;
    TxtDaoStand daostand;
    
    public static String jsonText = "[{\"class\":\"com.java.java_zaliczenie.Stand\",\"idStand\":1,\"shelfs\":[{\"books\":[{\"adventureInformations\":\"informacje jakieś do przygodówki\",\"bookAuthor\":\"jakiśtam autor\",\"bookDescription\":\"fjdksafhdjkvba,vbuiahfuirealhfn\",\"bookIsbn\":\"1234567890123\",\"bookPrice\":9.000000000000000000000000000000,\"bookTitle\":\"pupa pupa pupa\",\"class\":\"com.java.java_zaliczenie.AdventureBook\"}],\"class\":\"com.java.java_zaliczenie.Shelf\",\"idShelf\":2,\"shelfName\":\"Półka nr 1 na regale 1\"}],\"standName\":\"Regał nr 1\"},{\"class\":\"com.java.java_zaliczenie.Stand\",\"idStand\":2,\"shelfs\":[{\"books\":[{\"bookAuthor\":\"nowy autor\",\"bookDescription\":\"blabla blablu\",\"bookIsbn\":\"0000000000000\",\"bookPrice\":8.880000000000000000000000000000,\"bookTitle\":\"jakis nowy tytul\",\"class\":\"com.java.java_zaliczenie.Sfbook\",\"sfMovie\":\"jakistam film\"}],\"class\":\"com.java.java_zaliczenie.Shelf\",\"idShelf\":1,\"shelfName\":\"Półka nr 1 na regale 2\"}],\"standName\":\"Regał nr 2\"},{\"class\":\"com.java.java_zaliczenie.Stand\",\"idStand\":5,\"shelfs\":[{\"books\":[],\"class\":\"com.java.java_zaliczenie.Shelf\",\"idShelf\":3,\"shelfName\":\"sasasasas\"}],\"standName\":\"Nowy regal\"},{\"class\":\"com.java.java_zaliczenie.Stand\",\"idStand\":6,\"shelfs\":[{\"books\":[{\"bookAuthor\":\"2\",\"bookDescription\":\"3\",\"bookIsbn\":\"0123456789123\",\"bookPrice\":1.000000000000000000000000000000,\"bookTitle\":\"1\",\"class\":\"com.java.java_zaliczenie.Sfbook\",\"sfMovie\":\"sajensfikszyn\"}],\"class\":\"com.java.java_zaliczenie.Shelf\",\"idShelf\":4,\"shelfName\":\"superPolka\"}],\"standName\":\"testregalw\"}]";
    
    private List<Stand> library;
    
    private static TxtDaoFactory instance;

    private TxtDaoFactory() {
        library = new ArrayList<Stand>();
    }

    public static TxtDaoFactory getInstance() {
        if (instance == null) {
            instance = new TxtDaoFactory();
        }
        return instance;
    }

    @Override
    public DaoBook getBookDao() {
        if (daobook == null) {
            daobook = new TxtDaoBook();
        }
        return daobook;
    }

    @Override
    public DaoShelf getShelfDao() {
        if (daoshelf == null) {
            daoshelf = new TxtDaoShelf();
        }
        return daoshelf;
    }

    @Override
    public DaoStand getStandDao() {
        if (daostand == null) {
            daostand = new TxtDaoStand();
        }
        return daostand;
    }
}
