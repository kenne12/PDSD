/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Acteursfacteurs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface ActeursfacteursFacadeLocal {

    void create(Acteursfacteurs acteursfacteurs);

    void edit(Acteursfacteurs acteursfacteurs);

    void remove(Acteursfacteurs acteursfacteurs);

    Acteursfacteurs find(Object id);

    List<Acteursfacteurs> findAll();

    List<Acteursfacteurs> findRange(int[] range);

    int count();

    int nextId();

    List<Acteursfacteurs> findAllRange();

    public List<Acteursfacteurs> findByNom(String nom);
}
