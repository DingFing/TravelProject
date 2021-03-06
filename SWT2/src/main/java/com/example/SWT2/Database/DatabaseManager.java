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

    public Integer addBietetan(Aktivität anr, Reise reisenr){
        Session s = sf.openSession();
        Transaction tc = null;
        Integer BietetAnnr = null;
        try{
            tc = s.beginTransaction();
            BietetAn an = new BietetAn();
            an.setANr(anr);
            an.setReiseNr(reisenr);
            BietetAnnr = (Integer) s.save(an);
            tc.commit();
        }
        catch(HibernateException ex){
            if(tc!= null) tc.rollback();
            ex.printStackTrace();
        }finally {
            s.close();
        }
        return BietetAnnr;
    }

    public Integer adduser(String Nachname, String Vorname, java.sql.Date GeburtsDat, String Password, Integer Rolle){
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

    public Integer addReise(String Beschreibung, String Ort, String Region, String Land,Double Preis, String Jahreszeit){
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

    public Integer addAktivität(String Beschreibung){
        Session s = sf.openSession();
        Transaction tc = null;
        Integer AktivityNr = null;
        try{
            tc = s.beginTransaction();
            Aktivität ak = new Aktivität();
            ak.setBeschreibung(Beschreibung);
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

    public Integer addBewertung(User UserNr, Aktivität ANr, String Bewertung){
        Session s = sf.openSession();
        Transaction tc = null;
        Integer BewertungNr = null;
        try{
            tc = s.beginTransaction();
            Bewertung bewert = new Bewertung();
            bewert.setUserNr(UserNr);
            bewert.setANr(ANr);
            bewert.setBewertung(Bewertung);
            BewertungNr= (Integer) s.save(bewert);
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

    public Integer addBuchung1(java.sql.Date Von, java.sql.Date Bis, Reise ReiseNr, User UserNr,Double Preis){
        Session s = sf.openSession();
        Transaction tc = null;
        Integer BuchungNr = null;
        try{
            tc = s.beginTransaction();
            Buchung buch = new Buchung();
            buch.setPreis(Preis);
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

    public Reise getReisebyId(int id){
        Session s = sf.openSession();
        Reise reise = null;
        try{
            reise = (Reise) s.get(Reise.class, id);
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            s.close();
        }
        return reise;
    }

    public User getUserbyId(int id){
        Session s = sf.openSession();
        User user = null;
        try{
            user = (User) s.get(User.class, id);
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            s.close();
        }
        return user;
    }

    public Aktivität getAktivitätbyId(int id){
        Session s = sf.openSession();
        Aktivität aa = null;
        try{
            aa = (Aktivität) s.get(Aktivität.class, id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            s.close();
        }
        return aa;
    }

    public Integer addUrlaubsprofil(User UserNr, Reise ReiseNr, String name){
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

    public Integer addReiseBietetAnAktivity(Reise ReiseNr, Aktivität ANr){
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
        Integer a = Integer.parseInt(query.getSingleResult().toString());
        if(a == 0)
            return false;
        else
            return true;
    }

    public List<Object[]> gebuchteReisenVonUser(String vorname, String nachname){
        Session s = sf.openSession();
        String Ab = "Select r.Reisenr, r.Beschreibung, r.Ort, r.Region, r.Land, b.Von, b.Bis, b.Kosten from Reise r, Buchung b where b.usernr = (select usernr from user where vorname='"+vorname+"' and nachname='"+nachname+"') and r.reisenr = b.reisenr;";
        SQLQuery query = s.createSQLQuery(Ab);
        List<Object []> l = query.list();
        List<Object[]> ret = new ArrayList<Object[]>();
        for(Object[] r : l){
            SQLQuery query1 = s.createSQLQuery("Select a.Anr, a.Beschreibung from Aktivität a, bietetan bi where bi.Reisenr = "+r[0]+" and a.Anr = bi.Anr");
            Object [] t = new Object[9];
            for(int i=0;i<r.length;i++){
                t[i] = r[i];
            }
            t[8] = query1.list();
            ret.add(t);
        }
        /*for(int y=0;y<ret.size();y++){
            for(int i=0;i<8;i++)
                System.out.print(ret.get(y)[i]+"  ");
                System.out.println();
                System.out.println("Das ist ein neues Objekt");
                List<Object[]> q = (List<Object[]>) ret.get(y)[8];
                for(Object[] z: q){
                    for(int g=0;g<z.length;g++){
                        System.out.print(z[g]+" ");
                    }
                    System.out.println();
            }
        }*/
        return ret;
    }

    public  List<Object []> suchReisen(String option, String suche){
        Session s = sf.openSession();
        String Ab = "Select * from Reise where "+option+" = '"+suche+"'";
        SQLQuery query = s.createSQLQuery(Ab);
        List<Object []> l = query.list();
        List<Object[]> ret = new ArrayList<Object[]>();
        for(Object[] r : l){
            SQLQuery query1 = s.createSQLQuery("Select a.Anr, a.Beschreibung from Aktivität a, bietetan bi where bi.Reisenr = "+r[0]+" and a.Anr = bi.Anr");
            Object [] t = new Object[8];
            for(int i=0;i<r.length;i++){
                t[i] = r[i];
            }
            t[7] = query1.list();
            ret.add(t);
        }
        return ret;
    }

    public List<Object[]> gebuchteReiseId(String vorname, String nachname){
        Session s = sf.openSession();
        String Ab = "Select DISTINCT r.Reisenr from Reise r, Buchung b where b.usernr = (select usernr from user where vorname='"+vorname+"' and nachname='"+nachname+"') and r.reisenr = b.reisenr;";
        SQLQuery query = s.createSQLQuery(Ab);
        return query.list();
    }

    public List<Object[]> getAktivitäten(String option, String suche){
        Session s = sf.openSession();
        String Ab = "Select Distinct Anr from bietetan where Reisenr IN (Select Reisenr from Reise where "+option+" = '"+suche+"');";
        SQLQuery query = s.createSQLQuery(Ab);
        return query.list();
    }

    public  List<Object[]> suchReisennr(String option, String suche){
        Session s = sf.openSession();
        String Ab = "Select Reisenr from Reise where "+option+" = '"+suche+"'";
        SQLQuery query = s.createSQLQuery(Ab);
        return query.list();
    }

    public double getPreisvonReise(int reisenr){
        Session s = sf.openSession();
        SQLQuery query = s.createSQLQuery("Select Preis from Reise where Reisenr = "+reisenr);
        return Double.parseDouble(query.getSingleResult().toString());
    }

    public int getUsernr(String vorname, String nachname){
        Session s = sf.openSession();
        SQLQuery query = s.createSQLQuery("Select usernr from user where vorname = '"+vorname+"' and nachname = '"+nachname+"'");
        return Integer.parseInt(query.getSingleResult().toString());
    }

    public List<Object []> getNochNichtBewerteteAktivitätenVonUser(String vorname, String nachname){
        Session s = sf.openSession();
        SQLQuery query = s.createSQLQuery("Select DISTINCT bi.Anr from bietetan bi, buchung b where b.Reisenr = bi.Reisenr and bi.Anr NOT IN(Select be.Anr from bewertung be where be.Usernr = (Select usernr from user where vorname = '"+vorname+"' and nachname = '"+nachname+"'));");
        return query.list();
    }

    public List<Object[]> getBewertungbyTd(String AktivätId){
        Session s = sf.openSession();
        SQLQuery query = s.createSQLQuery("Select Bewertung from bewertung where Anr = "+Integer.parseInt(AktivätId));
        return query.list();
    }

    public List<Object[]> getProfilNamen(String vorname, String nachname){
        Session s = sf.openSession();
        SQLQuery query = s.createSQLQuery("Select u.name from Urlaubsprofile u where Usernr = (Select Usernr from user where Vorname = '"+vorname+"' and nachname = '"+nachname+"')");
        return query.list();
    }

    public Integer getReisebyProfilId(String name){
        Session s = sf.openSession();
        SQLQuery query = s.createSQLQuery("Select Reisenr from Urlaubsprofile where Profilnr = (Select Profilnr from Urlaubsprofile where name = '"+name+"');");
        return Integer.parseInt(query.getSingleResult().toString());
    }

    public List<Object[]> showAlleUser(){
        Session s = sf.openSession();
        SQLQuery query = s.createSQLQuery("Select usernr,geburtsdat,vorname,nachname,rolle from User");
        List<Object[]> a = query.list();
        /*for(Object[] rr : a){
            for(int i=0;i<5;i++)
                System.out.println(rr[i]+"###########################");
            System.out.println();
        }*/
        return a;
    }

    public List<Object[]> showAllUserID(){
        Session s = sf.openSession();
        SQLQuery query = s.createSQLQuery("Select Usernr from User ORDER BY usernr;");
        return query.list();
    }

    public void setRolleByUserId(int id, int rolle){
        Session s = sf.openSession();
        Transaction tc = null;
        try{
            tc = s.beginTransaction();
            User us = getUserbyId(id);
            us.setRolle(rolle);
            s.saveOrUpdate(us);
            tc.commit();
        }
        catch(HibernateException ex){
            if(tc!= null) tc.rollback();
            ex.printStackTrace();
        }finally{
            s.close();
        }
    }

    public List<Object[]> getAllAktivtyId(){
        Session s = sf.openSession();
        SQLQuery query = s.createSQLQuery("Select Anr from Aktivität ORDER BY Anr;");
        return query.list();
    }

    public List<Object[]> getAllReiseId(){
        Session s = sf.openSession();
        SQLQuery query = s.createSQLQuery("Select ReiseNr from Reise ORDER BY ReiseNr;");
        return query.list();
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