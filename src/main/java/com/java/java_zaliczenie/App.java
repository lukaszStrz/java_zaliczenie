package com.java.java_zaliczenie;

import com.java.java_zaliczenie.daos.DbDaoFactory;
import com.java.java_zaliczenie.daos.DaoFactory;
import com.java.java_zaliczenie.daos.interfaces.DaoBook;
import com.java.java_zaliczenie.daos.interfaces.DaoStand;
import com.java.java_zaliczenie.daos.interfaces.DaoShelf;
import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App
{

    private DaoFactory daoFactory;
    private DaoBook daoBook;
    private DaoShelf daoShelf;
    private DaoStand daoStand;
    private Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args)
    {
        new App().run();
    }

    private void run()
    {
        daoFactory = new DbDaoFactory();
        //daoFactory = new XmlDaoFactory();
        //daoFactory = new TxtDaoFactory();
        
        daoBook = daoFactory.getBookDao();
        daoShelf = daoFactory.getShelfDao();
        daoStand = daoFactory.getStandDao();
        
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do
        {
            System.out.println("1 - dodawanie regałów z półkami");
            System.out.println("2 - dodawanie książek");
            System.out.println("3 - przestawianie książek w ramach regałów i półek");
            System.out.println("4 - wyszukiwanie książek");
            System.out.println("5 - wypisywanie książek");
            System.out.println("6 - export danych o stanie biblioteki");
            System.out.println("0 - koniec");

            choice = scanner.nextInt();
            System.out.println();

            switch (choice)
            {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    for (Stand stand : daoStand.getAllStands())
                    {
                        System.out.println(stand.getStandName() + ":");
                        for (Object shelf : stand.getShelfs())
                        {
                            System.out.println("\t" + ((Shelf) shelf).getShelfName() + ":");
                            for (Object book : ((Shelf) shelf).getBooks())
                            {
                                System.out.println("\t\t" + ((Book) book).toString());
                            }
                        }
                    }
                    break;
                case 6:
                    break;
                default:
                    break;
            }
            System.out.println();
        } while (choice != 0);
        daoBook.closeSession();
        daoShelf.closeSession();
        daoStand.closeSession();
    }
}
