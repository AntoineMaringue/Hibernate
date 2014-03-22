/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.sciencesu.sns.hibernate.events;

import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;


/**
 *
 * @author antoi_000
 */
public class PostInsertProduct implements PostInsertEventListener
{

    @Override
    public void onPostInsert(PostInsertEvent pie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
