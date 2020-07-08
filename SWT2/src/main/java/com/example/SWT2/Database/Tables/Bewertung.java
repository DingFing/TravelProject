package com.example.SWT2.Database.Tables;

import org.hibernate.annotations.Type;
import javax.persistence.*;

@Entity
@Table(name = "Bewertung")
public class Bewertung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Bewertungnr" ,length = 10, nullable = false)
    Integer Bewertungnr;

    @ManyToOne
    @JoinColumn(name="Usernr", nullable=false)
    private User Usernr;

    @ManyToOne
    @JoinColumn(name="Anr", nullable=false)
    private Aktivität Anr;

    @Column(name= "Bewertung" ,nullable= false)
    @Type(type= "text")
    private String Bewertung;

    @Column(name= "Note", precision= 1, scale= 1, nullable= false)
    private double Note;

    public Integer getBewertungnr(){return Bewertungnr;}

    public double getNote(){return Note;}
    public void setNote(double Note){this.Note= Note;}

    public Aktivität getANr(){return Anr;}
    public void setANr(Aktivität Anr){this.Anr= Anr;}

    public User getUserNr(){return Usernr;}
    public void setUserNr(User Usernr){this.Usernr= Usernr;}

    public String getBewertung(){return Bewertung;}
    public void setBewertung(String Bewertung){this.Bewertung = Bewertung;}

    public Integer getBewertungNr(){return Bewertungnr;}
}