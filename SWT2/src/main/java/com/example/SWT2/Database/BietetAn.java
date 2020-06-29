package com.example.SWT2.Database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "BietetAn")
public class BietetAn {

    @JoinColumn(name="ReiseNr", nullable=false)
    private Reise ReiseNr;

    @JoinColumn(name="ANr", nullable=false)
    private Aktivity ANr;

    @Column(name = "Anzahl", lenght = 1, nullable=false)
    private Integer Anzahl;

    public void setAnzahl(Integer Anzahl) { this.Anzahl = Anzahl; }
    public Integer getAnzahl(){return Anzahl;}

    public void setANr(Aktivity ANr) { this.ANr = ANr; }
    public Aktivity getANr(){return ANr;}

    public void setReiseNr(Reise ReiseNr) { this.ReiseNr = ReiseNr; }
    public Reise getReiseNr(){return ReiseNr;}
}