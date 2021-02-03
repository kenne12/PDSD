/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubriqueinfosanitaire;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hp
 */
@Local
public interface RubriqueinfosanitaireFacadeLocal {

    void create(Rubriqueinfosanitaire rubriqueinfosanitaire);

    void edit(Rubriqueinfosanitaire rubriqueinfosanitaire);

    void remove(Rubriqueinfosanitaire rubriqueinfosanitaire);

    Rubriqueinfosanitaire find(Object id);

    List<Rubriqueinfosanitaire> findAll();

    List<Rubriqueinfosanitaire> findRange(int[] range);

    int count();

    public int nextId();

    public List<Rubriqueinfosanitaire> findByNom(String nom);

    public List<Rubriqueinfosanitaire> findAllRange();

}
