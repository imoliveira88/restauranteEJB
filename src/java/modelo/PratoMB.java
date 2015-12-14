package modelo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.event.FileUploadEvent;
import persistencia.PratoDAOJPA;

@ManagedBean(name = "pratoMB")
@SessionScoped
public class PratoMB{

    private Prato prato;
    private List<Prato> pratos;

    public PratoMB() {
        this.prato = new Prato();
        this.pratos = new ArrayList<>();
        this.pratos = new PratoDAOJPA().findAll();
    }

    public List<Prato> getPratos() {
        return this.pratos;
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

    public void salvar() {
        PratoDAOJPA pra = new PratoDAOJPA();
        pra.save(prato);
        this.pratos.add(prato);

        this.prato = new Prato();
    }
}
