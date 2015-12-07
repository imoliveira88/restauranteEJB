/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import javax.persistence.EntityManager;
import modelo.Endereco;

/**
 *
 * @author Magalhães Oliveira
 */
public class EnderecoDAOJPA extends DAOGenericoJPA<Long, Endereco> implements EnderecoDAO{
    public EnderecoDAOJPA() {
        super();
    }
    
    @Override
    public Endereco getById(long pk) {
        return super.getById(pk);
    }
}
