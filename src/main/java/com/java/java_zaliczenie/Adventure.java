package com.java.java_zaliczenie;
// Generated 2013-05-29 20:35:51 by Hibernate Tools 3.2.1.GA



/**
 * Adventure generated by hbm2java
 */
public class Adventure  implements java.io.Serializable {


     private int categoryIdCategory;
     private Category category;
     private String adventureInformations;

    public Adventure() {
    }

	
    public Adventure(int categoryIdCategory, Category category) {
        this.categoryIdCategory = categoryIdCategory;
        this.category = category;
    }
    public Adventure(int categoryIdCategory, Category category, String adventureInformations) {
       this.categoryIdCategory = categoryIdCategory;
       this.category = category;
       this.adventureInformations = adventureInformations;
    }
   
    public int getCategoryIdCategory() {
        return this.categoryIdCategory;
    }
    
    public void setCategoryIdCategory(int categoryIdCategory) {
        this.categoryIdCategory = categoryIdCategory;
    }
    public Category getCategory() {
        return this.category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    public String getAdventureInformations() {
        return this.adventureInformations;
    }
    
    public void setAdventureInformations(String adventureInformations) {
        this.adventureInformations = adventureInformations;
    }




}


