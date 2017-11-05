
package tanquesjpa;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author MarianaMorales17235
 * @author DiegoSevilla17238
 */
@Entity
public class Valvula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   

    //atributos:
    protected boolean estado;
    protected String municipio;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valvula)) {
            return false;
        }
        Valvula other = (Valvula) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tanquesjpa.Valvula[ id=" + id + " ]";
    }
    
    /**
     * Constructor for objects of class Valvula: Utilizado por la base de datos
     */
    public Valvula(){
        this.estado = false;
        this.municipio = "";
    }
    
    public Valvula(boolean estado, String municipio){
        this.estado = estado;
        this. municipio = municipio;
    }
    
    public boolean getEstado(){
        return estado;
    }
    
    public String getMunicipio(){
        return municipio;
    }
    
    public void asignarMunicipio(String municipio){
        this.municipio = municipio;
    }
    
    public void abrirValvula(){
        estado = true;
    }
    
    public void cerrarValvula(){
        estado = false;
    }
}
