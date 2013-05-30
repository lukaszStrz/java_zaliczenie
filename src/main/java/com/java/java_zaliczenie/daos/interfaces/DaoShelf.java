package com.java.java_zaliczenie.daos.interfaces;

import com.java.java_zaliczenie.*;
import java.util.*;

public interface DaoShelf
{
    void addShelf(Shelf shelf);

    void deleteShelf(int id);

    List<Shelf> getAllShelfs();

    Shelf getShelfById(int id);

    void updateShelf(Shelf shelf);
}