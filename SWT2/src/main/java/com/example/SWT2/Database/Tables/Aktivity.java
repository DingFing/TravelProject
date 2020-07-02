package com.example.SWT2.Database.Tables;

import org.hibernate.annotations.Type;
import javax.persistence.*;


@Entity
@Table(name = "Aktivity")
public class Aktivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Anr" ,length = 10, nullable = false)
    private Integer Anr;

    @Column(name = "Jahreszeit" ,length = 10, nullable = false)
    private String Jahreszeit;

    @Column(name = "Beschreibung" , nullable = false)
    @Type(type= "text")
    private String Beschreibung;

    @Column(name = "Kosten" ,precision= 10, scale= 2, nullable = false)
    private double Kosten;
    
    @ManyToOne
    @JoinColumn(name="UnternehmenId", nullable=false)
    private Unternehmen Unternehmenid;
    
    @ManyToOne
    @JoinColumn(name="Alternativ", nullable=false)
    private Aktivity Alternativ;

    public Integer getAnr(){return Anr;}
    
    public void setJahreszeit(String Jahreszeit){this.Jahreszeit = Jahreszeit;}
    public String getJahreszeit(){return this.Jahreszeit;}

    public void setBeschreibung(String Beschreibung){this.Beschreibung = Beschreibung;}
    public String getBeschreibung(){return this.Beschreibung;}

    public void setKosten(double Kosten){this.Kosten = Kosten;}
    public double getKosten(){return this.Kosten;}

    public void setUnternehmen(Unternehmen Unternehmenid){this.Unternehmenid = Unternehmenid;}
    public Unternehmen getUnternehmen(){return this.Unternehmenid;}

    public void setAlternativ(Aktivity Alternativ){this.Alternativ = Alternativ;}
    public Aktivity getAlternativ(){return this.Alternativ;}
}