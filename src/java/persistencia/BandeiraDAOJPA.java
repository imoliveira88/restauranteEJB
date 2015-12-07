/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import modelo.Bandeira;

/**
 *
 * @author Iury
 */
public class BandeiraDAOJPA extends DAOGenericoJPA<Long, Bandeira> implements BandeiraDAO{

    public BandeiraDAOJPA() {
        super();
    }

    @Override
    public Bandeira getById(long pk) {
        return super.getById(pk);
    }
    
}
