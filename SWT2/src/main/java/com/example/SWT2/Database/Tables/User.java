package com.example.SWT2.Database.Tables;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserNr" ,length = 10, nullable = false)
    private Integer UserNr;

    @Column(name = "Nachname" ,length = 20, nullable = false)
    private String Nachname;

    @Column(name = "Vorname" ,length = 20, nullable = false)
    private String Vorname;

    @Column(name = "GeburtsDat" ,nullable = false)
    private java.sql.Date GeburtsDat;

    @Column(name = "Password" ,length = 30, nullable = false)
    private String Password;

    @Column(name = "KontoNr" ,length = 10, nullable = false)
    private Integer KontoNr;

    @Column(name= "Rolle" ,length= 1, nullable= false)
    private Integer Rolle; //1=Admin 0=User

    public void setNachname(String Nachname){this.Nachname = Nachname;}
    public String getNachname(){ return this.Nachname; }

    public void setVorname(String Vorname){this.Vorname = Vorname;}
    public String getVorname(){ return this.Vorname; }

    public String getUserNr(){ return this.Vorname; }

    public void setGeburtsDat(java.sql.Date GeburtsDat){this.GeburtsDat = GeburtsDat;}
    public java.sql.Date getGeburtsDat(){ return this.GeburtsDat; }

    public void setKontoNr(Integer KontoNr){this.KontoNr = KontoNr;}
    public Integer getKontoNr(){ return this.KontoNr; }

    public void setRolle(Integer Rolle){this.Rolle = Rolle;}
    public Integer getRolle(){ return this.Rolle; }

    public void setPassword(String Password){this.Password= Password;}
    public String getPassword(){return this.Password;}
}