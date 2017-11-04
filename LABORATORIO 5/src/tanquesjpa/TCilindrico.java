
package tanquesjpa;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author DiegoSevilla17238
 * @author MarianaMorales17235
 */
@Entity
public class TCilindrico extends Tanque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    //atributos
    protected double radio;
    
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
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
        if (!(object instanceof TCilindrico)) {
            return false;
        }
        TCilindrico other = (TCilindrico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tanquesjpa.TCilindrico[ id=" + id + " ]";
    }
    
    public void setTCilindrico(String id, double altura, double radio){
        super.setTanque(id, altura);
        double volumen = calcularVolumen(altura,radio);
        capacidad = volumen;
        volumentemp= volumen;
        volumenminimo = volumen/4;
        
    }
    /**
     * Metodo que calcula el volumen de un tanque cilindrico
     * @param altura altura del tanque cilindrico
     * @param radio radio del tanque cilindrico
     * @return el volumen del tanque 
     */
    public double calcularVolumen(double altura, double radio){
        double volumen = Math.PI* Math.pow(radio, 2)* altura;
        return volumen;
    }
    
   
}
