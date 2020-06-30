package com.example.SWT2.Database.Tables;


import javax.persistence.*;

@Entity
@Table(name = "Aktivity")
public class Urlaubsprofile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ProfilNr" ,length = 10, nullable = false)
    private Integer ProfilNr;
    @ManyToOne
    @JoinColumn(name="UserNr", nullable=false)
    private User UserNr;
    @ManyToOne
    @JoinColumn(name="ReiseNr", nullable=false)
    private Reise ReiseNr;

    @Column(name = "Name", length = 20, nullable=false)
    private String name;

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public void setReiseNr(Reise ReiseNr){this.ReiseNr = ReiseNr;}
    public Reise getReiseNr(){return ReiseNr;}

    public void setUserNr(User UserNr){this.UserNr = UserNr;}
    public User getUserNr(){return UserNr;}

    public void setProfilNr(Integer ProfilNr){this.ProfilNr = ProfilNr;}
    public Integer getProfilNr(){return ProfilNr;}
}