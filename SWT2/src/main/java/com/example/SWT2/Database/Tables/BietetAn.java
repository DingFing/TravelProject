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
    private Aktivität Anr;

    public Integer getBnr(){return Bnr;}

    public void setANr(Aktivität Anr) { this.Anr = Anr;}
    public Aktivität getANr(){return Anr;}

    public void setReiseNr(Reise Reisenr) { this.Reisenr = Reisenr; }
    public Reise getReiseNr(){return Reisenr;}
}