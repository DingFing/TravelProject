package com.example.SWT2.Database;

import org.hibernate.annotations.Type;
import javax.persistence.*;


@Entity
@Table(name = "Aktivity")
public class Aktivity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserNr" ,length = 10, nullable = false)
    private Integer ANr;

    @Column(name = "Jahreszeit" ,length = 10, nullable = false)
    private String Jahreszeit;

    @Column(name = "Beschreibung" , nullable = false)
    @Type(type= "text")
    private String Beschreibung;

    @Column(name = "Kosten" ,precision= 10, scale= 2, nullable = false)
    private double Kosten;

    @JoinColumn(name="UnternehmenId", nullable=false)
    private Unternehmen UnternehmenId;

    @JoinColumn(name="ANr", nullable=false)
    private Aktivity Alternativ;
}