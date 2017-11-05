
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
public class TCubico extends Tanque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
    //atributos
    

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    
    /**
     * Constructor for objects of class TCubico: Utilizado por la base de datos
     */
    public TCubico(){
        super.setTanque("", 0);
        
    }
    
    public void setTCubico(String id, double altura){
        super.setTanque(id, altura);
        double volumen = calcularVolumen(altura);
        capacidad = volumen;
        volumentemp = volumen;
        volumenminimo = volumen/4;
    }
    /**
     * Metodo que calcula el volumen de un tanque cubico con la ayuda de calcularVolumen de Tanque.
     * @param altura
     * @return 
     */
    @Override
    public double calcularVolumen(double altura){
        double volumen = super.calcularVolumen(altura);
        return volumen;
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
        if (!(object instanceof TCubico)) {
            return false;
        }
        TCubico other = (TCubico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tanquesjpa.TCubico[ id=" + id + " ]";
    }
         
}
