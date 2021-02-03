/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Typerecette;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface TyperecetteFacadeLocal {

    void create(Typerecette typerecette);

    void edit(Typerecette typerecette);

    void remove(Typerecette typerecette);

    Typerecette find(Object id);

    List<Typerecette> findAll();

    List<Typerecette> findRange(int[] range);

    int count();

    int nextId();

    public List<Typerecette> findByNom(String nom);
    
    List<Typerecette> findAllRange();

}
