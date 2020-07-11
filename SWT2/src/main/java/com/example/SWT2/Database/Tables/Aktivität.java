package com.example.SWT2.Database.Tables;

import org.hibernate.annotations.Type;
import javax.persistence.*;

@Entity
@Table(name = "Aktivität")
public class Aktivität {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Anr" ,length = 10, nullable = false)
    private Integer Anr;

    @Column(name = "Beschreibung" , nullable = false)
    @Type(type= "text")
    private String Beschreibung;

    @ManyToOne
    @JoinColumn(name="Alternativ", nullable=false)
    private Aktivität Alternativ;

    public Integer getAnr(){return Anr;}

    public void setBeschreibung(String Beschreibung){this.Beschreibung = Beschreibung;}
    public String getBeschreibung(){return this.Beschreibung;}

    public void setAlternativ(Aktivität Alternativ){this.Alternativ = Alternativ;}
    public Aktivität getAlternativ(){return this.Alternativ;}
}