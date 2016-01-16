
package persistencia.jpa;

import static javax.ejb.TransactionAttributeType.SUPPORTS;
import static javax.ejb.TransactionManagementType.CONTAINER;

import acesso.Grupo;
import static acesso.Papel.CLIENTE;
import static acesso.Papel.FUNCIONARIO;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;


@Stateless
@LocalBean
@TransactionManagement(CONTAINER)
@DeclareRoles({CLIENTE,FUNCIONARIO})
public class GrupoServico extends ServicoGenerico<Long,Grupo> {
    @TransactionAttribute(SUPPORTS)       
    @PermitAll    
    public Grupo getGrupo(String nomeGrupo) {
        return getEntidade(Grupo.GRUPO_POR_NOME, new Object[]{nomeGrupo});
    }
}
