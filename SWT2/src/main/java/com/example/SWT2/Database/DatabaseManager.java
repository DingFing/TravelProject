package com.example.SWT2.Database;

import com.example.SWT2.Database.Tables.*;
import org.hibernate.*;

import org.hibernate.cfg.*;

public class DatabaseManager {

    private static SessionFactory sf;
    public Integer adduser(String Nachname, String Vorname, java.sql.Date GeburtsDat, String Password, Integer KontoNr, Integer Rolle){
        Session s = sf.openSession();
        Transaction tc = null;
        Integer UserNr = null;
        try{
            tc = s.beginTransaction();
            User user = new User();
            user.setNachname(Nachname);
            user.setVorname(Vorname);
            user.setGeburtsDat(GeburtsDat);
            user.setPassword(Password);
            user.setKontoNr(KontoNr);
            user.setRolle(Rolle);
            UserNr = (Integer) s.save(user);
            tc.commit();
        }
        catch(HibernateException ex){
            if(tc!= null) tc.rollback();
            ex.printStackTrace();
        }finally {
            s.close();
        }
        return UserNr;
    }

    public Integer AddReise(String Beschreibung, String Ort, String Region, String Land,Double Preis, String Jahreszeit){
        Session s = sf.openSession();
        Transaction tc = null;
        Integer ReiseNr = null;
        try{
            tc = s.beginTransaction();
            Reise reis = new Reise();
            reis.setBeschreibung(Beschreibung);
            reis.setOrt(Ort);
            reis.setRegion(Region);
            reis.setLand(Land);
            reis.setPreis(Preis);
            reis.setJahreszeit(Jahreszeit);
            ReiseNr = (Integer) s.save(reis);
            tc.commit();
        }
        catch(HibernateException ex){
            if(tc!= null) tc.rollback();
            ex.printStackTrace();
        }finally{
            s.close();
        }
        return ReiseNr;
    }

    public Integer AddUnternehmen(Integer KontoNr){
        Session s = sf.openSession();
        Transaction tc = null;
        Integer UnternehmenNr = null;
        try{
            tc = s.beginTransaction();
            Unternehmen un = new Unternehmen();
            un.setKontoNr(KontoNr);
            UnternehmenNr = (Integer) s.save(un);
            tc.commit();
        }
        catch(HibernateException ex){
            if(tc!= null) tc.rollback();
            ex.printStackTrace();
        }finally{
            s.close();
        }
        return UnternehmenNr;
    }

    public Integer AddAktivity(Integer KontoNr){
        Session s = sf.openSession();
        Transaction tc = null;
        Integer UnternehmenNr = null;
        try{
            tc = s.beginTransaction();
            Unternehmen un = new Unternehmen();
            un.setKontoNr(KontoNr);
            UnternehmenNr = (Integer) s.save(un);
            tc.commit();
        }
        catch(HibernateException ex){
            if(tc!= null) tc.rollback();
            ex.printStackTrace();
        }finally{
            s.close();
        }
        return UnternehmenNr;
    }
}