package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import modelo.Prato;
import org.primefaces.event.FileUploadEvent;
import persistencia.jpa.PratoServico;

@ManagedBean(name = "pratoMB")
@SessionScoped
public class PratoMB{
    
    @EJB
    PratoServico pra;

    private Prato prato;
    private List<Prato> pratos;
    private String mensagem;
    
    @PostConstruct
    public void iniciar(){
        this.prato = new Prato();
        this.pratos = new ArrayList<>();
        this.pratos = pra.findAll();
    }

    public List<Prato> getPratos() {
        return this.pratos;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setPratos(List<Prato> listarPratos) {
        this.pratos = listarPratos;
    }

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato pr) {
        this.prato = pr;
    }

    public void uploadAction(FileUploadEvent event) {
        this.prato.setImagem(event.getFile().getFileName());   
    }

    public String salvar() {
        if(!pra.existePrato(this.prato)){
            pra.save(prato);
            this.pratos.add(prato);
            this.prato = new Prato();
            this.setMensagem("Prato adicionado com sucesso!");
            return "/faces/funcionario/cadastro_prato.xhtml";
        }
        else{
            this.setMensagem("Prato não adicionado! Este nome de prato já existe!");
            return "/faces/funcionario/cadastro_prato.xhtml";
        }
    }
    
    public String excluir() throws Exception{
        pra.delete(prato);
        this.pratos.remove(prato);
        this.setMensagem("Prato removido com sucesso!");
        return "/faces/funcionario/cadastro_prato.xhtml";
    }
}
