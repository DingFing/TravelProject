package com;

import java.sql.Date;

public class Suchanfrage {
    private String suche;
    private String option;
    private java.sql.Date von;
    private java.sql.Date bis;

    public String getSuche(){return suche;}
    public void setSuche(String suche){this.suche = suche;}

    public String getOption(){ return option;}
    public void setOption(String option){this.option = option;}

    public java.sql.Date getVon(){ return von;}
    public void setVon(java.sql.Date von){this.von = von;}

    public java.sql.Date getBis(){ return bis;}
    public void setBis(java.sql.Date bis){this.bis = bis;}
}