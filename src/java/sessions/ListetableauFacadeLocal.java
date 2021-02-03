/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listetableau;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Abdel BEIN-INFO
 */
@Local
public interface ListetableauFacadeLocal {

    void create(Listetableau listetableau);

    void edit(Listetableau listetableau);

    void remove(Listetableau listetableau);

    Listetableau find(Object id);

    List<Listetableau> findAll();

    List<Listetableau> findRange(int[] range);

    int count();
    
    public List<Listetableau> findByNom(String nom);
    
    public int nextId();

    
}
