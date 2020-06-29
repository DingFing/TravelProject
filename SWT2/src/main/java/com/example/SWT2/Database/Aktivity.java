package com.example.SWT2.Database;

import javax.persistence.*;


@Entity
@Table(name = "Aktivity")
public class Aktivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserNr" ,length = 10, nullable = false)
    private Integer UserNr;

    @Column(name = "Nachname" ,length = 20, nullable = false)
    private String Nachname;
}
