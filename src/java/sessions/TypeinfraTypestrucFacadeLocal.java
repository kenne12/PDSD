/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TypeinfraTypestruc;
import entities.Typestructure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface TypeinfraTypestrucFacadeLocal {

    void create(TypeinfraTypestruc typeinfraTypestruc);

    void edit(TypeinfraTypestruc typeinfraTypestruc);

    void remove(TypeinfraTypestruc typeinfraTypestruc);

    TypeinfraTypestruc find(Object id);

    List<TypeinfraTypestruc> findAll();

    List<TypeinfraTypestruc> findRange(int[] range);

    int count();
    
    int nextId();
    
    public List<TypeinfraTypestruc> findByTypestructure(Typestructure typestructure) throws Exception;
    
}
