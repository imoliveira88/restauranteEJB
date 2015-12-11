/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.NotBlank;
import persistencia.UsuarioDAOJPA;

/**
 *
 * @author MagalhÃ£es Oliveira
 */

@Entity
@NamedQueries(value = {
  @NamedQuery(name = "Usuario.RetornaSenha",
              query= " SELECT u.senha FROM Usuario u " +
                     " WHERE u.telefone = :tel")
})
@Table(name = "TB_USUARIO")
@ManagedBean(name = "usuario")
@SessionScoped
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DISC_USUARIO", discriminatorType = DiscriminatorType.STRING, length = 1)
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public Usuario(String nome, String senha, String tel, Endereco end){
        this.nome = nome;
        this.senha = senha;
        this.telefone = tel;
        this.endereco = end;
    }
    
    public Usuario(){
        this.nome = "";
        this.endereco = new Endereco();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(min = 2, max = 40)
    @NotNull
    @Column(name = "NOME")
    private String nome;
    
    @NotBlank
    @Column(name = "TELEFONE")
    private String telefone;
    
    @NotBlank
    @Column(name = "SENHA")
    private String senha;
    
    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ENDERECO", referencedColumnName = "ID_ENDERECO")
    private Endereco endereco;
    
    public String getHorario() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        return "Atualizado em " + sdf.format(new Date());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    //Compara se o telefone digitado corresponde a um usuário válido, e, correspondendo,
    //compara a senha fornecida, com a senha que há no banco
    public boolean validaUsuario(){
        UsuarioDAOJPA ud = new UsuarioDAOJPA();
        return this.telefone.equals(ud.retornaSenha(this.telefone));
    }
    
    public String doLogin() {
        boolean valido = this.validaUsuario();
         try {   
             if (!valido) {
               FacesContext.getCurrentInstance().validationFailed();
               return "/cadastro_cliente.xhtml?faces-redirect=true";
             }
             
             return "/cadastro_cliente.xhtml?faces-redirect=true";
         } catch (Exception e) {
             FacesContext.getCurrentInstance().validationFailed();
             e.printStackTrace();
             return "";
         }
   
      }
    
    @Override
    public String toString(){
        String s = "";
        
        s += "Nome: " + this.getNome() + " EndereÃ§o: " + this.getEndereco();
        
        return s;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
