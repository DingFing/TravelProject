package com.example.SWT2.Database;

import javax.persistence.*;
import java.util.Date;

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
    private Date GeburtsDat;

    @Column(name = "Password" ,length = 30, nullable = false)
    private String Password;

    @Column(name = "KontoNr" ,length = 10, nullable = false)
    private Integer KontoNr;

    public void setNachname(String Nachname){
        this.Nachname = Nachname;
    }
    public String getNachname(){ return this.Nachname; }

    public void setVorname(String Vorname){this.Vorname = Vorname;}
    public String getVorname(){ return this.Vorname; }

    public String getUserNr(){ return this.Vorname; }

    public void setGeburtsDat(Date GeburtsDat){this.GeburtsDat = GeburtsDat;}
    public Date getGeburtsDat(){ return this.GeburtsDat; }

    public void setKontoNr(Integer KontoNr){this.KontoNr = KontoNr;}
    public Integer getKontoNr(){ return this.KontoNr; }

    public void setPassword(String Password){this.Password= Password;}
    public String getPassword(){return this.Password;}
}