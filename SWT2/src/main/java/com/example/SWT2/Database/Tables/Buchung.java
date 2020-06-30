package com.example.SWT2.Database.Tables;

import javax.persistence.*;

@Entity
@Table(name = "Buchung")
public class Buchung {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BuchungID" ,length = 10, nullable = false)
    private Integer BuchungId;

    @Column(name = "Von" , nullable = false)
    private java.sql.Date Von;

    @Column(name = "Bis" , nullable = false)
    private java.sql.Date Bis;

    @ManyToOne
    @JoinColumn(name="ReiseNr", nullable=false)
    private Reise ReiseNr;

    public void setBis(java.sql.Date Bis){ this.Bis = Bis; }
    public java.sql.Date getBis(){return this.Bis;}

    public void setVon(java.sql.Date Von){ this.Von = Von; }
    public java.sql.Date getVon(){return this.Von;}

    public Integer getBuchungId(){return this.BuchungId;}

    public void setReise(Reise ReiseNr){this.ReiseNr = ReiseNr;}
    public Reise getReise(){return this.ReiseNr;}
}