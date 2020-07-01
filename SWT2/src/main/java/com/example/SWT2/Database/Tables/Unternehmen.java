package com.example.SWT2.Database.Tables;

import javax.persistence.*;

@Entity
@Table(name = "Unternehmen")
public class Unternehmen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "Unternehmenid" ,length= 10, nullable= false)
    private Integer Unternehmenid;

    @Column(name= "Kontonr" ,length= 10, nullable= false)
    private Integer Kontonr;

    public Integer getKontoNr(){return Kontonr;}
    public void setKontoNr(Integer Kontonr){this.Kontonr = Kontonr;}

    public Integer getUnternehmenId(){return Unternehmenid;}
}