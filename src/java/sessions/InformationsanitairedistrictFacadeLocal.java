/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Informationsanitairedistrict;
import entities.Rubriqueinfosanitaire;
import entities.Structure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hp
 */
@Local
public interface InformationsanitairedistrictFacadeLocal {

    void create(Informationsanitairedistrict informationsanitairedistrict);

    void edit(Informationsanitairedistrict informationsanitairedistrict);

    void remove(Informationsanitairedistrict informationsanitairedistrict);

    Informationsanitairedistrict find(Object id);

    List<Informationsanitairedistrict> findAll();

    List<Informationsanitairedistrict> findRange(int[] range);

    int count();

    public Integer nextId() throws Exception;

    public List<Informationsanitairedistrict> find(Structure structure, Rubriqueinfosanitaire rubriqueinfosanitaire, District district) throws Exception;

    public List<Informationsanitairedistrict> find(Structure structure) throws Exception;

}
