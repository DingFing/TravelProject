package com.example.SWT2.Database;

import javax.persistence.*;

@Entity
@Table(name = "Unternehmen")
public class Unternehmen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "UnternehmenId" ,length= 10, nullable= false)
    private Integer UnternehmenId;

    @Column(name= "KontoNr" ,length= 10, nullable= false)
    private Integer KontoNr;

    public Integer getKontoNr(){return KontoNr;}
    public void setKontoNr(Integer KontoNr){this.KontoNr = KontoNr;}

    public Integer getUnternehmenId(){return UnternehmenId;}
    public void setUnternehmenId(Integer UnternehmenId){this.UnternehmenId= UnternehmenId}
}
