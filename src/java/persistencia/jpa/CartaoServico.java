package persistencia.jpa;

import static acesso.Papel.CLIENTE;
import static acesso.Papel.FUNCIONARIO;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.CONTAINER;
import modelo.Cartao;
import javax.persistence.NoResultException;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;



@Stateless
@LocalBean
@DeclareRoles({FUNCIONARIO, CLIENTE})
@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED) 
@ValidateOnExecution(type = ExecutableType.NON_GETTER_METHODS)
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
