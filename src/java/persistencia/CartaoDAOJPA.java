package persistencia;

import modelo.Cartao;
import javax.persistence.EntityManager;

/**
 *
 * @author Iury
 */
public class CartaoDAOJPA extends DAOGenericoJPA<Long, Cartao> implements CartaoDAO{

    public CartaoDAOJPA() {
        super();
    }

    @Override
    public Cartao getById(long pk) {
        return super.getById(pk);
    }
    
}
