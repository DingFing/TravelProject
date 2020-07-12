package com.example.SWT2.Database.Tables;


import javax.persistence.*;

@Entity
@Table(name = "Urlaubsprofile")
public class Urlaubsprofile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Profilnr" ,length = 10, nullable = false)
    private Integer Profilnr;
    @ManyToOne
    @JoinColumn(name="Usernr", nullable=false)
    private User Usernr;
    @ManyToOne
    @JoinColumn(name="Reisenr", nullable=false)
    private Reise Reisenr;

    @Column(name = "Name", length = 20, nullable=false)
    private String name;

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public void setReiseNr(Reise Reisenr){this.Reisenr = Reisenr;}
    public Reise getReiseNr(){return Reisenr;}

    public void setUserNr(User Usernr){this.Usernr = Usernr;}
    public User getUserNr(){return Usernr;}

    public Integer getProfilnr(){return Profilnr;}
}