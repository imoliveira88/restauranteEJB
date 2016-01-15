package beans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import jsf.beans.Recaptcha;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author MASC
 */
@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

    @NotBlank
    private String login;
    @NotBlank
    private String senha;
    private FacesContext facesContext;

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
                return "/faces/login.xhtml?faces-redirect=true";
            }

        } catch (ServletException ex) {
            setLogin(null);
            adicionarMensagem("Senha ou usuário inválidos!");
            return "/faces/login.xhtml?faces-redirect=true";
        }

        return "/faces/cliente/homeC.xhtml";
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
