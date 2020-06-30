package com.example.SWT2.Database;

import com.example.SWT2.Database.Tables.User;
import org.hibernate.*;

import org.hibernate.cfg.*;

public class DatabaseManager {

    private static SessionFactory sf;
    public Integer adduser(String Nachname, String Vorname, java.sql.Date GeburtsDat, String Password, Integer KontoNr){
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
}