
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "pedido")
@SessionScoped
@Entity
@Table(name="TB_PEDIDO")
public class Pedido implements Serializable {
    
    public Pedido(){
        this.total = 0;
        this.itens = new ArrayList<>();
        this.data = new Date();
        this.atendido = 'N';
    }
       
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO")
    private Long id;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final List<ItemPedido> itens;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    private Cliente cliente;
   
    @Column(name = "PEDIDO_DATA")
    private Date data;

    @Column(name = "PEDIDO_TOTAL") 
    private double total;
    
    @Column(name = "PEDIDO_ATENDIDO")
    private char atendido;
    
    public Long getId() {
        return id;
    }

    public char getAtendido() {
        return atendido;
    }

    public void setAtendido(char atendido) {
        this.atendido = atendido;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void addItem(ItemPedido item) {
        this.itens.add(item);
        this.total += item.getSubtotal();
    }

    public double getTotal() {
        return Math.round(100*this.total)/100;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
}

