package persistencia.jpa;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import modelo.Cartao;
import javax.persistence.NoResultException;

/**
 *
 * @author Iury
 */
public class CartaoServico extends ServicoGenerico<Long, Cartao>{

    public CartaoServico() {
        super();
    }

    @TransactionAttribute(SUPPORTS)
    @PermitAll 
    public Cartao getById(long pk) {
        return super.getById(pk);
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll 
    public boolean existeCartao(Cartao car){
        String query = "select e from Cartao e";
        List<Cartao> cartoes = entityManager.createQuery(query, Cartao.class).getResultList();
        try{
            for(Cartao cartao : cartoes){
                if(cartao.equals(car)) return true;
            }
            return false;
        }
        catch(NoResultException e){
            return false;
        }
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll 
    @Override
    public void save(Cartao b) {
        if(!existeCartao(b)){
            entityManager.persist(b);

        }
    }
    
}
