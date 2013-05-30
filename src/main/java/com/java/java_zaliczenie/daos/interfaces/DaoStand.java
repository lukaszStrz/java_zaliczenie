package com.java.java_zaliczenie.daos.interfaces;

import com.java.java_zaliczenie.*;
import java.util.*;

public interface DaoStand
{
    void addStand(Stand stand);

    void closeSession();

    void deleteStand(int id);

    List<Stand> getAllStands();

    Stand getStandById(int id);

    void updateStand(Stand stand);
}