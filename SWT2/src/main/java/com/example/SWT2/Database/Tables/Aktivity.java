package com.example.SWT2.Database.Tables;

import org.hibernate.annotations.Type;
import javax.persistence.*;


@Entity
@Table(name = "Aktivity")
public class Aktivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserNr" ,length = 10, nullable = false)
    private Integer ANr;

    @Column(name = "Jahreszeit" ,length = 10, nullable = false)
    private String Jahreszeit;

    @Column(name = "Beschreibung" , nullable = false)
    @Type(type= "text")
    private String Beschreibung;

    @Column(name = "Kosten" ,precision= 10, scale= 2, nullable = false)
    private double Kosten;
    
    @ManyToOne
    @JoinColumn(name="UnternehmenId", nullable=false)
    private Unternehmen UnternehmenId;
    
    @ManyToOne
    @JoinColumn(name="ANr", nullable=false)
    private Aktivity Alternativ;

    public void setJahreszeit(String Jahreszeit){this.Jahreszeit = Jahreszeit;}
    public String getJahreszeit(){return this.Jahreszeit;}

    public void setBeschreibung(String Beschreibung){this.Beschreibung = Beschreibung;}
    public String getBeschreibung(){return this.Beschreibung;}

    public void setKosten(double Kosten){this.Kosten = Kosten;}
    public double getKosten(){return this.Kosten;}

    public void setUnternehmen(Unternehmen UnternehmenId){this.UnternehmenId = UnternehmenId;}
    public Unternehmen getUnternehmen(){return this.UnternehmenId;}

    public void setAlternativ(Aktivity Alternativ){this.Alternativ = Alternativ;}
    public Aktivity getAlternativ(){return this.Alternativ;}
}