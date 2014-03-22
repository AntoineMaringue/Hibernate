/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.sciencesu.sns.hibernate.builder;

import fr.sciencesu.sns.hibernate.utils.HibernateUtil;
import java.net.ConnectException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;



/**
 *
 * @author Antoine
 */
public class AbstractBDD {

    protected Session session = null;
    String LOG = "AbstractBDD";

    void debug(String msg) {
        System.out.println(msg);
    }

    public void connection() throws ConnectException{
        if (session == null) {           
            session = HibernateUtil.getSessionFactory().openSession();
            debug(LOG + " Connexion OK ");
        }
    }

    public void deconnection() {
        //fermeture de la session
        if (session != null && session.isOpen()) {
            session.close();
            debug(LOG + " Déconnexion OK ");
        }        
    }

    void print(String nameTable) {
        System.out.println("[" + nameTable + "]");

        String hql = "from " + nameTable;
        Query q = session.createQuery(hql);

        Set dataTable = (Set) q.list();

        for (Object object : dataTable) {
            System.out.println(object.toString());
        }
    }

    public static Calendar toCalendar(int day, int month, int year, REGION region) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE, day);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.YEAR, year);
        Date date = new Date(c.getTimeInMillis());

        switch (region) {
            case FR: {
                SimpleDateFormat simpleDateformatter = new SimpleDateFormat("dd/mm/yyyy");
                simpleDateformatter.format(date);
                break;
            }
            case EN: {
                SimpleDateFormat simpleDateformatter = new SimpleDateFormat("yyyy/mm/dd");
                simpleDateformatter.format(date);
                break;
            }
        }
        c.setTime(date);
        return c;
    }

    public static Calendar toCalendar(int day, int month, int year) {
        Calendar c = Calendar.getInstance();
        month -=1%12;//Corection des dates
        c.set(Calendar.DATE, day);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.YEAR, year);
 
        Date date = new Date(c.getTimeInMillis());

        SimpleDateFormat simpleDateformatter = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateformatter.format(date);

        c.setTime(new Date(date.getTime()));
  
        return c;
    }

    public enum REGION {

        FR, EN
    }
}
