package beans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import jsf.beans.Recaptcha;
import org.hibernate.validator.constraints.NotBlank;
import persistencia.jpa.UsuarioServico;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

    @NotBlank
    private String login;
    @NotBlank
    private String senha;
    private FacesContext facesContext;
    
    @EJB
    private UsuarioServico us;

    public String login() {
        try {
            facesContext = FacesContext.getCurrentInstance();
            Recaptcha recaptcha = new Recaptcha(facesContext);

            if (recaptcha.validar()) {
                HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
                request.login(login, senha);
                facesContext.getExternalContext().getSession(true);
            } else {
                setLogin(null);
                adicionarMensagem("Captcha inválido!");
                return "/faces/public/login.xhtml";
            }
        } catch (ServletException ex) {
            setLogin(null);
            adicionarMensagem("Senha ou usuário inválidos!");
            return "/faces/public/login.xhtml";
        }

        if(us.tipoUsuario(login,senha).equals("C")) return "/faces/cliente/homeC.xhtml";
        else return "/faces/funcionario/homeF.xhtml";     
        
    }

    private void adicionarMensagem(String mensagem) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, null);
        facesContext.addMessage(null, message);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}