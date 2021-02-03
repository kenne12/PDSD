/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Categoriestructurecom;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface CategoriestructurecomFacadeLocal {

    void create(Categoriestructurecom categoriestructurecom);

    void edit(Categoriestructurecom categoriestructurecom);

    void remove(Categoriestructurecom categoriestructurecom);

    Categoriestructurecom find(Object id);

    List<Categoriestructurecom> findAll();

    List<Categoriestructurecom> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;

}
