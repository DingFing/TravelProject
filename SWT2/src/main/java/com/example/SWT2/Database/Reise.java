package com.example.SWT2.Database;

import org.hibernate.annotations.Type;
import javax.persistence.*;

@Entity
@Table(name = "Reise")
public class Reise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "ReiseNr" ,length= 10, nullable= false)
    private Integer ReiseNr;

    @Column(name= "Beschreibung" ,nullable= false)
    @Type(type= "text")
    private String Beschreibung;

    @Column(name= "Ort", length= 20, nullable= false)
    private String Ort;

    @Column(name= "Region", length= 20, nullable= false)
    private String Region;

    @Column(name= "Land", length= 20, nullable= false)
    private String Land;

    @Column(name= "Preis", precision= 10, scale= 2, nullable= false)
    private double Preis;

    @Column(name= "Jahreszeit", length= 10, nullable= false)
    private String Jahreszeit;

    @JoinColumn(name="UnternehmenId", nullable=false)
    private Unternehmen UnternehmenId;

    public Unternehmen getUnternehmenId(){return UnternehmenId;}
    public void setUnternehmenId(Unternehmen UnternehmenId){this.UnternehmenId= UnternehmenId;}

    public String getJahreszeit(){return Jahreszeit;}
    public void setJahreszeit(String Jahreszeit){this.Jahreszeit= Jahreszeit;}

    public double getPreis(){return Preis;}
    public void setPreis(double Preis){this.Preis= Preis;}

    public String getLand(){return Land;}
    public void setLand(String Land){this.Land= Land;}

    public String getRegion(){return Region;}
    public void setRegion(String Region){this.Region= Region;}

    public String getOrt(){return Ort;}
    public void setOrt(String Ort){this.Ort= Ort;}

    public String getBeschreibung(){return Beschreibung;}
    public void setBeschreibung(String Beschreibung){this.Beschreibung= Beschreibung;}
}