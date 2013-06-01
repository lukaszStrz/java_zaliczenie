/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.java_zaliczenie.daos;

import com.java.java_zaliczenie.*;
import com.java.java_zaliczenie.daos.interfaces.DaoBook;
import com.java.java_zaliczenie.daos.interfaces.DaoShelf;
import com.java.java_zaliczenie.daos.interfaces.DaoStand;
import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 *
 * @author Wojciech
 */
public class XmlDaoFactory extends DaoFactory {

    Logger logger = Logger.getLogger(DbDaoShelf.class.getName());
    private XmlDaoBook daobook;
    private XmlDaoShelf daoshelf;
    private XmlDaoStand daostand;
    private static XmlDaoFactory instance;
    File db;
    private List<Stand> memory;

    private XmlDaoFactory() {
        db = new File("database.xml");
        if (!db.exists()) {
            try {
                db.createNewFile();
            } catch (IOException ex) {
                logger.error(ex);
            }
        }
        memory = deserialize();
        
//        XStream xstream = new XStream();
//        String xml = xstream.toXML(memory);
//        System.out.println(xml);
    }

    public void serialize() {
        XStream xstream = new XStream();
        String xml = xstream.toXML(memory);

        PrintWriter writer;
        try {
            writer = new PrintWriter(db);
            writer.print(xml);
            writer.close();
        } catch (FileNotFoundException ex) {
            logger.error(ex);
        }

    }

    private List<Stand> deserialize() {
        XStream xstream = new XStream();

        Scanner scanner;
        StringBuilder sb = new StringBuilder();
        try {
            scanner = new Scanner(db);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                sb.append(line);
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            logger.error(ex);
        }

        String xml = sb.toString();
        return (List<Stand>) xstream.fromXML(xml);
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
            daobook = new XmlDaoBook(memory);
        }
        return daobook;
    }

    @Override
    public DaoShelf getShelfDao() {
        if (daoshelf == null) {
            daoshelf = new XmlDaoShelf(memory);
        }
        return daoshelf;
    }

    @Override
    public DaoStand getStandDao() {
        if (daostand == null) {
            daostand = new XmlDaoStand(memory);
        }
        return daostand;
    }
}
