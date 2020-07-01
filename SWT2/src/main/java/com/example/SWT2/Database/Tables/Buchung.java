package com.example.SWT2.Database.Tables;

import javax.persistence.*;

@Entity
@Table(name = "Buchung")
public class Buchung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Buchungid" ,length = 10, nullable = false)
    private Integer Buchungid;

    
    @Column(name = "Von" , nullable = false)
    private java.sql.Date Von;

    @Column(name = "Bis" , nullable = false)
    private java.sql.Date Bis;

    @ManyToOne
    @JoinColumn(name="Reisenr", nullable=false)
    private Reise Reisenr;

    @ManyToOne
    @JoinColumn(name="Usernr", nullable=false)
    private User Usernr;

    public void setBis(java.sql.Date Bis){ this.Bis = Bis; }
    public java.sql.Date getBis(){return this.Bis;}

    public void setVon(java.sql.Date Von){ this.Von = Von; }
    public java.sql.Date getVon(){return this.Von;}

    public Integer getBuchungid(){return this.Buchungid;}

    public void setReise(Reise Reisenr){this.Reisenr = Reisenr;}
    public Reise getReise(){return this.Reisenr;}

    public void setUser(User Usernr){this.Usernr= Usernr;}
    public User getUser(){return this.Usernr;}
}