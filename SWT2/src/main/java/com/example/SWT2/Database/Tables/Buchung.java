package com.example.SWT2.Database.Tables;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Buchung")
public class Buchung {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BuchungID" ,length = 10, nullable = false)
    private Integer BuchungId;

    @Column(name = "Von" , nullable = false)
    private Date Von;

    @Column(name = "Bis" , nullable = false)
    private Date Bis;

    public void setBis(Date Bis){ this.Bis = Bis; }
    public Date getBis(){return this.Bis;}

    public void setVon(Date Von){ this.Von = Von; }
    public Date getVon(){return this.Von;}

    public Integer getBuchungId(){return this.BuchungId;}
}
