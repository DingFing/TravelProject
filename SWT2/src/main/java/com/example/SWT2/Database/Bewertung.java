package com.example.SWT2.Database;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Bewertung")
public class Bewertung {

    @JoinColumn(name="UserNr", nullable=false)
    private User UserNr;

    @JoinColumn(name="ANr", nullable=false)
    private Aktivity ANr;

    @Column(name= "Bewertung" ,nullable= false)
    @Type(type= "text")
    private String Bewertung;

    @Column(name= "Note", precision= 1, scale= 1, nullable= false)
    private double Note;

    public double getNote(){return Note;}
    public void setNote(double Note){this.Note= Note;}

    public Aktivity getANr(){return ANr;}
    public void setANr(Aktivity ANr){this.ANr= ANr;}

    public User getUserNr(){return UserNr;}
    public void setANr(User UserNr){this.UserNr= UserNr;}
}