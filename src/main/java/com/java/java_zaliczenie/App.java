package com.java.java_zaliczenie;

import com.java.java_zaliczenie.daos.DbDaoFactory;
import com.java.java_zaliczenie.daos.DaoFactory;
import com.java.java_zaliczenie.daos.interfaces.DaoBook;
import com.java.java_zaliczenie.daos.interfaces.DaoStand;
import com.java.java_zaliczenie.daos.interfaces.DaoShelf;
import com.java.java_zaliczenie.utils.HibernateUtil;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class App {

    private DaoFactory daoFactory;
    private Logger logger = Logger.getLogger(App.class.getName());
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        daoFactory = selectDao();

        int choice = 0;
        do {
            System.out.println();
            System.out.println("1 - dodawanie regałów z półkami");
            System.out.println("2 - dodawanie książek");
            System.out.println("3 - przestawianie książek w ramach regałów i półek");
            System.out.println("4 - wyszukiwanie książek");
            System.out.println("5 - wypisywanie książek");
            System.out.println("6 - export danych o stanie biblioteki");
            System.out.println("9 - zmiana źródła danych");
            System.out.println("0 - koniec");

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ex) {
                logger.error("Choice is not a number");
                System.out.print("Zły wybór, spróbuj jeszcze raz");
                choice = -1;
                scanner = new Scanner(System.in); /*to jest jakieś gówno...
                 * na bank nie jest to optymalne, ale bez tego np po wpisaniu "dupa", on tę "dupę"
                 * trzyma cały czas w bufforze i... znów ją daje do .nextInt()... */
            }
            System.out.println();
            

            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    for (Stand stand : daoFactory.getStandDao().getAllStands()) {
                        System.out.println(stand.getStandName() + ":");
                        for (Object shelf : stand.getShelfs()) {
                            System.out.println("\t" + ((Shelf) shelf).getShelfName() + ":");
                            for (Object book : ((Shelf) shelf).getBooks()) {
                                System.out.println("\t\t" + ((Book) book).toString());
                            }
                        }
                    }
                    break;
                case 6:
                    int exportType = 0;

                    System.out.println("Wybierz sposób eksportu:");
                    System.out.println("1 - eksport do XML");
                    System.out.println("2 - eksport do JSON");
                    System.out.println("3 - eksport do CSV");

                    try {
                        exportType = scanner.nextInt();
                    } catch (InputMismatchException ex) {
                        logger.error("ExportType is not a number");
                        System.out.print("Zły wybór typu eksportu, spróbuj jeszcze raz");
                        exportType = -1;
                        scanner = new Scanner(System.in); /* jak wcześniej */
                    }

                    switch (exportType) {
                        case 1:
                            break;
                        case 2:
                            break;
                        default:
                            break;
                    }
                    break;
                case 9:
                    daoFactory = selectDao();
                default:
                    break;
            }
            System.out.println();
        } while (choice != 0);

        HibernateUtil.closeSession();
    }

    private DaoFactory selectDao() {
        int choice = 0;
        do {
            System.out.println("Wybierz źródło danych:");
            System.out.println("1 - baza danych");
            System.out.println("2 - plik tekstowy");
            System.out.println("3 - plik XML");

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ex) {
                logger.error("Choice is not a number");
                System.out.print("Zły wybór, spróbuj jeszcze raz");
                choice = -1;
                scanner = new Scanner(System.in);
            }

            switch (choice) {
                case 1:
                    return DbDaoFactory.getInstance();
                default:
                    choice = -1;
                    break;
            }
        } while (choice == -1);
        logger.error("Nie wybrano DAO");
        throw new UnsupportedOperationException("Nie wybrano DAO");
    }
}
