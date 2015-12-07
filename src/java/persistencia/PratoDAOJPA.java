/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import modelo.Prato;
import javax.persistence.EntityManager;

/**
 *
 * @author Iury
 */
public class PratoDAOJPA extends DAOGenericoJPA<Long, Prato> implements PratoDAO{

    public PratoDAOJPA() {
        super();
    }

    public Prato getById(long pk) {
        return super.getById(pk);
    }
    
}
