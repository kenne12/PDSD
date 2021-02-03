/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubriquemortalite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface RubriquemortaliteFacadeLocal {

    void create(Rubriquemortalite rubriquemortalite);

    void edit(Rubriquemortalite rubriquemortalite);

    void remove(Rubriquemortalite rubriquemortalite);

    Rubriquemortalite find(Object id);

    List<Rubriquemortalite> findAll();

    List<Rubriquemortalite> findRange(int[] range);

    int count();

    int nextId();

    public List<Rubriquemortalite> findByNom(String nom);

    public List<Rubriquemortalite> findAllRange();
}
