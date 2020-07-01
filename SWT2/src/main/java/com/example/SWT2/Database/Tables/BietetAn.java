package com.example.SWT2.Database.Tables;

import javax.persistence.*;

@Entity
@Table(name = "BietetAn")
public class BietetAn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Bnr" ,length = 10, nullable = false)
    Integer Bnr;
    @ManyToOne
    @JoinColumn(name="Reisenr", nullable=false)
    private Reise Reisenr;
    @ManyToOne
    @JoinColumn(name="Anr", nullable=false)
    private Aktivity Anr;

    public Integer getBnr(){return Bnr;}

    public void setANr(Aktivity Anr) { this.Anr = Anr;}
    public Aktivity getANr(){return Anr;}

    public void setReiseNr(Reise Reisenr) { this.Reisenr = Reisenr; }
    public Reise getReiseNr(){return Reisenr;}
}