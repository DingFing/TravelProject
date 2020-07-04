package com.example.SWT2.Database;

import java.util.ArrayList;
import java.util.List;
import com.example.SWT2.Database.Tables.*;
import org.hibernate.*;
import org.hibernate.cfg.*;


public class DatabaseManager {
    private SessionFactory sf;
    public DatabaseManager(){
        sf = new Configuration().configure().buildSessionFactory();
    }


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

    public Integer AddAktivity(String Jahreszeit, String Beschreibung, double Kosten, Unternehmen UnternehmenId, Aktivity alternativ){
        Session s = sf.openSession();
        Transaction tc = null;
        Integer AktivityNr = null;
        try{
            tc = s.beginTransaction();
            Aktivity ak = new Aktivity();
            ak.setAlternativ(alternativ);
            ak.setBeschreibung(Beschreibung);
            ak.setJahreszeit(Jahreszeit);
            ak.setKosten(Kosten);
            ak.setUnternehmen(UnternehmenId);
            AktivityNr= (Integer) s.save(ak);
            tc.commit();
        }
        catch(HibernateException ex){
            if(tc!= null) tc.rollback();
            ex.printStackTrace();
        }finally{
            s.close();
        }
        return AktivityNr;
    }

    public Integer AddBuchung(java.sql.Date Von, java.sql.Date Bis, Reise ReiseNr, User UserNr,Double Preis){
        Session s = sf.openSession();
        Transaction tc = null;
        Integer BuchungNr = null;
        SQLQuery query = s.createSQLQuery("SELECT DATEDIFF('"+Bis+"', '"+Von+"') AS days;");
        try{
            tc = s.beginTransaction();
            Buchung buch = new Buchung();
            buch.setPreis(ReiseNr.getPreis()* (Integer) query.getSingleResult());
            buch.setBis(Bis);
            buch.setVon(Von);
            buch.setReise(ReiseNr);
            buch.setUser(UserNr);
            BuchungNr= (Integer) s.save(buch);
            tc.commit();
        }
        catch(HibernateException ex){
            if(tc!= null) tc.rollback();
            ex.printStackTrace();
        }finally{
            s.close();
        }
        return BuchungNr;
    }

    public Integer AddUrlaubsprofil(Integer ProfilNr, User UserNr, Reise ReiseNr, String name){
        Session s = sf.openSession();
        Transaction tc = null;
        Integer UrlaubsprofilNr = null;
        try{
            tc = s.beginTransaction();
            Urlaubsprofile Profil = new Urlaubsprofile();
            Profil.setName(name);
            Profil.setReiseNr(ReiseNr);
            Profil.setUserNr(UserNr);
            UrlaubsprofilNr= (Integer) s.save(Profil);
            tc.commit();
        }
        catch(HibernateException ex){
            if(tc!= null) tc.rollback();
            ex.printStackTrace();
        }finally{
            s.close();
        }
        return UrlaubsprofilNr;
    }

    public Integer AddBewertung(User UserNr, Aktivity ANr, String Bewertung, double Note){
        Session s = sf.openSession();
        Transaction tc = null;
        Integer BewertungNr = null;
        try{
            tc = s.beginTransaction();
            Bewertung bew = new Bewertung();
            bew.setANr(ANr);
            bew.setUserNr(UserNr);
            bew.setBewertung(Bewertung);
            bew.setNote(Note);
            BewertungNr= (Integer) s.save(bew);
            tc.commit();
        }
        catch(HibernateException ex){
            if(tc!= null) tc.rollback();
            ex.printStackTrace();
        }finally{
            s.close();
        }
        return BewertungNr;
    }

    public Integer AddReiseBietetAnAktivity(Reise ReiseNr, Aktivity ANr){
        Session s = sf.openSession();
        Transaction tc = null;
        Integer BANr = null;
        try{
            tc = s.beginTransaction();
            BietetAn BA = new BietetAn();
            BA.setANr(ANr);
            BA.setReiseNr(ReiseNr);
            BANr= (Integer) s.save(BA);
            tc.commit();
        }
        catch(HibernateException ex){
            if(tc!= null) tc.rollback();
            ex.printStackTrace();
        }finally{
            s.close();
        }
        return BANr;
    }

    public boolean userVorhanden(String vorname, String nachname, String password){
        Session s = sf.openSession();
        SQLQuery query = s.createSQLQuery("Select * from User where vorname = '"+vorname+"' and nachname = '"+nachname+"' and password = '"+password+"'");
        ArrayList a = new ArrayList(query.getResultList());
        if(a.isEmpty())
            return false;
        return true;
    }

    public boolean userAdmin(User user){
        Session s = sf.openSession();
        SQLQuery query = s.createSQLQuery("Select Rolle from User where vorname = '"+user.getVorname()+"' and nachname = '"+user.getNachname()+"' and password = '"+user.getPassword()+"';");
        if(query.getSingleResult().toString() == "0")
            return false;
        return true;
    }

    public ArrayList<Object> gebuchteReisenVonUser(String vorname, String nachname){
        Session s = sf.openSession();
        ArrayList<Object> aReise = new ArrayList<Object>();
        String Ab = "Select r.Beschreibung,r.Ort,r.Region,r.Land,b.Von, b.Bis, b.Kosten from Reise r, Buchung b where b.usernr = (select usernr from user where vorname='"+vorname+"' and nachname='"+nachname+"';";
        SQLQuery query = s.createSQLQuery(Ab);
        List<Object[]> l = query.list();
        aReise.add(query.list());
        /*for(Object[] r : l){
            for(int i=0;i<7;i++)
                System.out.println(r[i].toString()+"  ");
        }*/
        return aReise;
    }
    //Unternhemen anhand von Parametern ausgeben
    //Reise anhand von Parametern ausgeben
    //User anhand von Parametern ausgeben
    //Aktivität für eine Reise ausgeben
    //Buchung eines Users anzeigen
    //Bewertungen anzeien für eine Aktivität
    //Bewertungen anzeigen die man schon gemacht hat
    //Urlaubsprofile anzeigen

    //User Parameter ändern
    //Aktivität zu einer Reise hinzufügen als Unternehmen
    
}