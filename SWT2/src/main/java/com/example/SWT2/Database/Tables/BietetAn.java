package com.example.SWT2.Database.Tables;

import javax.persistence.*;

@Entity
@Table(name = "BietetAn")
public class BietetAn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BNr" ,length = 10, nullable = false)
    Integer BNr;
    @ManyToOne
    @JoinColumn(name="ReiseNr", nullable=false)
    private Reise ReiseNr;
    @ManyToOne
    @JoinColumn(name="ANr", nullable=false)
    private Aktivity ANr;

    @Column(name = "Anzahl", length = 1, nullable=false)
    private Integer Anzahl;

    public void setAnzahl(Integer Anzahl) { this.Anzahl = Anzahl; }
    public Integer getAnzahl(){return Anzahl;}

    public void setANr(Aktivity ANr) { this.ANr = ANr; }
    public Aktivity getANr(){return ANr;}

    public void setReiseNr(Reise ReiseNr) { this.ReiseNr = ReiseNr; }
    public Reise getReiseNr(){return ReiseNr;}

    public Integer getBNr(){return BNr;}
}