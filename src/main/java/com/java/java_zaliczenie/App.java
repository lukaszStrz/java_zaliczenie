package com.java.java_zaliczenie;

import com.java.java_zaliczenie.daos.DbDaoFactory;
import com.java.java_zaliczenie.daos.DaoFactory;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class App {

    private DaoFactory daoFactory;
    private Logger logger;
    Scanner scanner;

    public static void main(String[] args) {
        new App().run();
    }

    public App() {
        this.scanner = new Scanner(System.in);
        this.logger = Logger.getLogger(App.class.getName());
    }

    private void run() {
        daoFactory = selectDao();

        int choice;
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
                logger.debug("Choice is not a number");
                choice = -1;
                scanner = new Scanner(System.in); /*to jest jakieś gówno...
                 * na bank nie jest to optymalne, ale bez tego np po wpisaniu "dupa", on tę "dupę"
                 * trzyma cały czas w bufforze i... znów ją daje do .nextInt()... */
            }
            System.out.println();


            switch (choice) {
                case 1:
                    //zmienić nazwę ;)?
                    task1();
                    break;
                case 2: {
                    Stand stand = selectStand();
                    Shelf shelf = selectShelf(stand);
                    addBook(shelf);
                }
                break;
                case 3:
                    //to będzie ciekawe...
                    break;
                case 4:
                    //do poprawek
                    findBook();
                    break;
                case 5:
                    showAllBooks();
                    break;
                case 6:
                    //tutaj trzeba pewnie zastosować znów jakiś wzorzec, żeby się przepinać
                    //chyba coś takiego aksenczer pokazywał
                    exportLibrary();
                    break;
                case 9:
                    daoFactory = selectDao();
                    break;
                default:
                    break;
            }
            System.out.println();
        } while (choice != 0);
    }

    private DaoFactory selectDao() {
        do {
            int choice;
            System.out.println("Wybierz źródło danych:");
            System.out.println("1 - baza danych");
            System.out.println("2 - plik tekstowy");
            System.out.println("3 - plik XML");

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ex) {
                logger.debug("Choice is not a number");
                choice = -1;
                scanner = new Scanner(System.in);
            }

            switch (choice) {
                case 1:
                    return DbDaoFactory.getInstance();
                default:
                    break;
            }
        } while (true);
    }

    private void task1() {
        int choice;
        do {
            System.out.println();
            System.out.println("1 - dodawanie regałów ");
            System.out.println("2 - dodawanie półek");
            System.out.println("0 - koniec");

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ex) {
                logger.debug("Choice is not a number");
                choice = -1;
                scanner = new Scanner(System.in);
            }
            System.out.println();


            switch (choice) {
                case 1:
                    addStand();
                    break;
                case 2:
                    Stand stand = selectStand();
                    addShelf(stand);
                    break;
                default:
                    break;
            }
            System.out.println();
        } while (choice != 0);
    }

    private void addStand() {
        System.out.println("Podaj nazwę, jaka ma zostać przypisana:");
        String name = "";
        do {
            name = scanner.nextLine();
        } while (name.equals(""));
        Stand stand = new Stand(name);
        daoFactory.getStandDao().addStand(stand);
    }

    private Stand selectStand() {
        System.out.println("Wybierz regał:");
        List<Stand> stands = daoFactory.getStandDao().getAllStands();
        for (int i = 0; i < stands.size(); i++) {
            System.out.print(i);
            System.out.println(" - " + stands.get(i).getStandName() + "(" + stands.get(i).getIdStand().toString() + ")");
        }
        do {
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ex) {
                logger.debug("Choice is not a number");
                System.out.println("Zły wybór, spróbuj jeszcze raz");
                choice = -1;
                scanner = new Scanner(System.in);
            }
            if (choice >= 0 && choice < stands.size()) {
                return stands.get(choice);
            }
        } while (true);
    }

    private void addShelf(Stand stand) {
        System.out.println("Podaj nazwę, jaka ma zostać przypisana:");
        String name = "";
        do {
            name = scanner.nextLine();
        } while (name.equals(""));

        Shelf shelf = new Shelf(stand, name);
        daoFactory.getShelfDao().addShelf(shelf);
    }

    private Shelf selectShelf(Stand stand) {
        System.out.println("Wybierz półkę:");
        Object[] shelves = stand.getShelfs().toArray();
        for (int i = 0; i < shelves.length; i++) {
            System.out.print(i);
            System.out.println(" - " + ((Shelf) shelves[i]).getShelfName() + "(" + ((Shelf) shelves[i]).getIdShelf().toString() + ")");
        }
        do {
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ex) {
                logger.debug("Choice is not a number");
                choice = -1;
                scanner = new Scanner(System.in);
            }
            if (choice >= 0 && choice < shelves.length) {
                return ((Shelf) shelves[choice]);
            }
        } while (true);
    }

    private void addBook(Shelf shelf) {
        System.out.println("Podaj tytuł:");
        String title = "";
        do {
            title = scanner.nextLine();
        } while (title.equals(""));

        System.out.println("Podaj autora:");
        String author = "";
        do {
            author = scanner.nextLine();
        } while (author.equals(""));

        System.out.println("Podaj opis:");
        String description = "";
        do {
            description = scanner.nextLine();
        } while (description.equals(""));

        System.out.println("Podaj isbn:");
        String isbn = "";
        do {
            isbn = scanner.nextLine();
            if (isbn.length() != 13 && !isbn.equals("")) {
                isbn = "";
                System.out.println("ISBN musi być długości 13 znaków. Podaj ponownie:");
            }
        } while (isbn.equals(""));

        System.out.println("Podaj cenę:");
        BigDecimal price;
        do {
            try {
                price = scanner.nextBigDecimal();
            } catch (InputMismatchException ex) {
                logger.debug("Price is not a number");
                price = BigDecimal.valueOf(-1.0);
                scanner = new Scanner(System.in);
            }
        } while (price.compareTo(BigDecimal.ZERO) == -1);

        Book book = new Book();
        int choice;
        do {
            System.out.println("Kategoria:");
            System.out.println("1 - dramat");
            System.out.println("2 - książka przygodowa");
            System.out.println("3 - SF");

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ex) {
                logger.debug("Choice is not a number");
                choice = -1;
                scanner = new Scanner(System.in);
            }

            switch (choice) {
                case 1:
                    book = new DramaBook();
                    choice = 0;
                    break;
                case 2:
                    System.out.println("Podaj informacje dodatkowe:");
                    String informations = "";
                    do {
                        informations = scanner.nextLine();
                    } while (informations.equals(""));
                    book = new AdventureBook();
                    ((AdventureBook) book).setAdventureInformations(informations);
                    choice = 0;
                    break;
                case 3:
                    System.out.println("Podaj informacje dodatkowe:");
                    String movie = "";
                    do {
                        movie = scanner.nextLine();
                    } while (movie.equals(""));
                    book = new Sfbook();
                    ((Sfbook) book).setSfMovie(movie);
                    choice = 0;
                    break;
                default:
                    break;
            }
            book.setBookAuthor(author);
            book.setBookDescription(description);
            book.setBookIsbn(isbn);
            book.setBookPrice(price);
            book.setBookTitle(title);
            book.setShelf(shelf);
        } while (choice != 0);
        daoFactory.getBookDao().addBook(book);
    }

    private void exportLibrary() {
        int exportType = 0;

        System.out.println("Wybierz sposób eksportu:");
        System.out.println("1 - eksport do XML");
        System.out.println("2 - eksport do JSON");
        System.out.println("3 - eksport do CSV");

        try {
            exportType = scanner.nextInt();
        } catch (InputMismatchException ex) {
            logger.debug("ExportType is not a number");
            exportType = -1;
            scanner = new Scanner(System.in); /* jak wcześniej */
        }

        switch (exportType) {
            case 1:
                System.out.println(exportToXML());
                break;
            case 2:
                System.out.println(exportToJSON());
                break;
            case 3:
                System.out.println(exportToCSV());
                break;
            default:
                break;
        }
    }

    private String exportToXML() {
        String result = "piękny xml";
        return result;
    }

    private String exportToJSON() {
        String result = new JSONSerializer().deepSerialize(daoFactory.getStandDao().getAllStands());
        return result;
    }

    private String exportToCSV() {
        String result = "csv";
        return result;
    }

    private void findBook() {
        int choice;
        do {
            System.out.println("Wyszukuj wedłg pola:");
            System.out.println("1 - Cena");
            System.out.println("2 - Tytuł");
            System.out.println("3 - Autor");
            System.out.println("4 - Kategoria*");
            System.out.println("0 - Zakończ wyszukiwanie*");

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ex) {
                logger.debug("Choice is not a number");
                choice = -1;
                scanner = new Scanner(System.in);
            }
            System.out.println();

            if (choice != 0) {
                System.out.println("Szukana wartość:");
                if (choice == 4) {
                    System.out.println("Kategoria:");
                    System.out.println("1 - dramat");
                    System.out.println("2 - książka przygodowa");
                    System.out.println("3 - SF");
                }
                String value = scanner.next();
                System.out.println();

                List<Book> books = daoFactory.getBookDao().getAllBooks();

                switch (choice) {
                    case 1:
                        for (Book b : books) {
                            if (b.getBookPrice().toString().equals(value)) {
                                System.out.println("price " + b.getBookTitle());
                            }
                        }
                        break;
                    case 2:
                        for (Book b : books) {
                            if (b.getBookTitle().contains(value)) {
                                System.out.println("tit " + b.getBookTitle());
                            }
                        }
                        break;
                    case 3:
                        for (Book b : books) {
                            if (b.getBookAuthor().contains(value)) {
                                System.out.println("a " + b.getBookTitle());
                            }
                        }
                        break;
                    case 4:
                        for (Book b : books) {
                            if (value.equals("1")) {
                                if (b instanceof DramaBook) {
                                    System.out.println(b.getBookTitle() + "DramaBook");
                                }
                            } else if (value.equals("2")) {
                                if (b instanceof AdventureBook) {
                                    System.out.println(b.getBookTitle() + "AdventureBook");
                                }
                            } else if (value.equals("3")) {
                                if (b instanceof Sfbook) {
                                    System.out.println(b.getBookTitle() + "Sfbook");
                                }
                            } else {
                            }
                            //w: może poprawić to jakoś :P?
                        }
                        break;
                    case 0:
                        return;
                    default:
                        break;
                }
                System.out.println();
            }
        } while (choice != 0);
    }

    private void showAllBooks() {
        for (Stand stand : daoFactory.getStandDao().getAllStands()) {
            System.out.println(stand.getStandName() + ":");
            for (Object shelf : stand.getShelfs()) {
                System.out.println("\t" + ((Shelf) shelf).getShelfName() + ":");
                for (Object book : ((Shelf) shelf).getBooks()) {
                    System.out.println("\t\t1" + ((Book) book).toString());
                    String tmp = new JSONSerializer().deepSerialize((Book) book);
                    Book b = new JSONDeserializer<Book>().deserialize(tmp);
                    System.out.println("\t\t2" + ((Book) book).toString());
                }
            }
        }
    }
}
