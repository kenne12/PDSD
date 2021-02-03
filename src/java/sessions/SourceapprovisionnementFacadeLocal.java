/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Sourceapprovisionnement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface SourceapprovisionnementFacadeLocal {

    void create(Sourceapprovisionnement sourceapprovisionnement);

    void edit(Sourceapprovisionnement sourceapprovisionnement);

    void remove(Sourceapprovisionnement sourceapprovisionnement);

    Sourceapprovisionnement find(Object id);

    List<Sourceapprovisionnement> findAll();

    List<Sourceapprovisionnement> findRange(int[] range);

    int count();

    int nextId();

    public List<Sourceapprovisionnement> findByNom(String nom);

    List<Sourceapprovisionnement> findAllRange();

}
