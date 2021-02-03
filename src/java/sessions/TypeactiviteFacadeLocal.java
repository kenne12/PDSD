/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Typeactivite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface TypeactiviteFacadeLocal {

    void create(Typeactivite typeactivite);

    void edit(Typeactivite typeactivite);

    void remove(Typeactivite typeactivite);

    Typeactivite find(Object id);

    List<Typeactivite> findAll();

    List<Typeactivite> findRange(int[] range);

    int count();

    int nextId();

    List<Typeactivite> findByNom(String nom);

    List<Typeactivite> findAllRange();
}
