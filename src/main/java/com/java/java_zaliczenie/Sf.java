package com.java.java_zaliczenie;
// Generated 2013-05-29 19:46:30 by Hibernate Tools 3.2.1.GA



/**
 * Sf generated by hbm2java
 */
public class Sf  implements java.io.Serializable {


     private int categoryIdCategory;
     private Category category;
     private String sfMovie;

    public Sf() {
    }

	
    public Sf(int categoryIdCategory, Category category) {
        this.categoryIdCategory = categoryIdCategory;
        this.category = category;
    }
    public Sf(int categoryIdCategory, Category category, String sfMovie) {
       this.categoryIdCategory = categoryIdCategory;
       this.category = category;
       this.sfMovie = sfMovie;
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
    public String getSfMovie() {
        return this.sfMovie;
    }
    
    public void setSfMovie(String sfMovie) {
        this.sfMovie = sfMovie;
    }




}


