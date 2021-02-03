/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Thematique;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Simo
 */
@Local
public interface ThematiqueFacadeLocal {

    void create(Thematique thematique);

    void edit(Thematique thematique);

    void remove(Thematique thematique);

    Thematique find(Object id);

    List<Thematique> findAll();

    List<Thematique> findRange(int[] range);

    int nextId();

    List<Thematique> findbynom(String Nom);

    int count();

    List<Thematique> findByEtat(Boolean etat);

}
