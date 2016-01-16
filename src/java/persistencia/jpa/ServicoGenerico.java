/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jpa;

import static acesso.Papel.FUNCIONARIO;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static javax.persistence.PersistenceContextType.TRANSACTION;
import javax.persistence.TypedQuery;

/**
 *
 * @author Iury
 * 
 */
@SuppressWarnings("unchecked")
public abstract class ServicoGenerico<PK, T> {
    
    protected Class<T> classe;
    
    //@PersistenceContext(name = "restaurante", type = TRANSACTION)
    @PersistenceContext(name = "restaurante", type = TRANSACTION)
    protected EntityManager entityManager;
 
    @TransactionAttribute(SUPPORTS)
    public T getById(PK pk) {
        return (T) entityManager.find(getTypeClass(), pk);
    }
 
    @TransactionAttribute(SUPPORTS)
    public void save(T entity) {
        entityManager.persist(entity);
    }
 
    @TransactionAttribute(SUPPORTS)
    public void update(T entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }
 
    @TransactionAttribute(SUPPORTS)
    @RolesAllowed({FUNCIONARIO})
    public void delete(T entity) throws Exception{
        entityManager.remove(entity);
    }
 
    @TransactionAttribute(SUPPORTS)
    public List<T> findAll() {
        return entityManager.createQuery(("FROM " + getTypeClass().getName())).getResultList();
    }
 
    @TransactionAttribute(SUPPORTS)
    private Class<?> getTypeClass() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        return clazz;
    }
    
    @TransactionAttribute(SUPPORTS)
    protected T getEntidade(String nomeQuery, Object[] parametros) {
        TypedQuery<T> query = entityManager.createNamedQuery(nomeQuery, classe);

        int i = 1;
        for (Object parametro : parametros) {
            query.setParameter(i++, parametro);
        }

        return query.getSingleResult();
    }
}