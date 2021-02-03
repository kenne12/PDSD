/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Categorieindicateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface CategorieindicateurFacadeLocal {

    void create(Categorieindicateur categorieindicateur);

    void edit(Categorieindicateur categorieindicateur);

    void remove(Categorieindicateur categorieindicateur);

    Categorieindicateur find(Object id);

    List<Categorieindicateur> findAll();

    List<Categorieindicateur> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;

    List<Categorieindicateur> find(String nom) throws Exception;

}
