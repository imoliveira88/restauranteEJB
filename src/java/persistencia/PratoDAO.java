/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import modelo.Prato;

/**
 *
 * @author Magalhães Oliveira
 */
public interface PratoDAO {
 
    public void save(Prato b);
 
    //public void update(Prato b);
 
    public void delete(Prato b);
 
    //public List<Prato> findAll();
}
