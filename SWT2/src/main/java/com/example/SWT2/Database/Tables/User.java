package com.example.SWT2.Database.Tables;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    public User(){
        this.Rolle = 0;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Usernr" ,length = 10, nullable = false)
    private Integer Usernr;

    @Column(name = "Nachname" ,length = 20, nullable = false)
    private String Nachname;

    @Column(name = "Vorname" ,length = 20, nullable = false)
    private String Vorname;

    @Column(name = "Geburtsdat" ,nullable = false)
    private java.sql.Date Geburtsdat;

    @Column(name = "Password" ,length = 30, nullable = false)
    private String Password;

    @Column(name= "Rolle" ,length= 1, nullable= false)
    private Integer Rolle; //0=User 1=Admin

    public void setNachname(String Nachname){this.Nachname = Nachname;}
    public String getNachname(){ return this.Nachname; }

    public void setVorname(String Vorname){this.Vorname = Vorname;}
    public String getVorname(){ return this.Vorname; }

    public Integer getUsernr(){ return this.Usernr; }

    public void setGeburtsDat(java.sql.Date Geburtsdat){this.Geburtsdat = Geburtsdat;}
    public java.sql.Date getGeburtsDat(){ return this.Geburtsdat; }

    public void setRolle(Integer Rolle){this.Rolle = Rolle;}
    public Integer getRolle(){ return this.Rolle; }

    public void setPassword(String Password){this.Password= Password;}
    public String getPassword(){return this.Password;}
}