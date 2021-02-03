/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Profilpersonnel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface ProfilpersonnelFacadeLocal {

    void create(Profilpersonnel profilpersonnel);

    void edit(Profilpersonnel profilpersonnel);

    void remove(Profilpersonnel profilpersonnel);

    Profilpersonnel find(Object id);

    List<Profilpersonnel> findAll();

    List<Profilpersonnel> findRange(int[] range);

    int count();

    int nextId();

    public List<Profilpersonnel> findByNom(String nom);

    List<Profilpersonnel> findAllRange();
}
