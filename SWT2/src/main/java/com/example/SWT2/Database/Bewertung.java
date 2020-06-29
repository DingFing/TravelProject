package com.example.SWT2.Database;

import org.hibernate.annotations.Type;
import javax.persistence.*;

@Entity
@Table(name = "Bewertung")
public class Bewertung {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BewertungNr" ,length = 10, nullable = false)
    Integer BewertungNr;

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

    public String getBewertung(){return Bewertung;}
    public void setBewertung(String Bewertung){this.Bewertung = Bewertung;}

    public Integer getBewertungNr(){return BewertungNr;}
}