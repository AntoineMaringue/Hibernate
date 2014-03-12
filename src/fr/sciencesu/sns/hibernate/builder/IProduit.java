/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.sciencesu.sns.hibernate.builder;

/**
 *
 * @author antoi_000
 */
public interface IProduit<T> {

    public void Create(T element);

    public void Read(String table, String field, String value);

    public String Read(String table);

    public void Update(T element, int idxStock);

    public void Delete(T element);

}
