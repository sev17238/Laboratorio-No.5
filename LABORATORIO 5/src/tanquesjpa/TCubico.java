
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
public class TCubico extends Tanque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    protected double capacidad;
    protected double volumentemp;
    protected double volumenminimo;

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
    
    public void setTCubico(Long id, double altura){
        //super.setTanque(id, altura);
    }
    
    public void decrementarVolumen(){
        volumentemp = volumentemp - capacidad*0.05;
    }
    public double getCapacidad(){
        return capacidad;
    }
    
    public double getVolumenTemporal(){
        return volumentemp;
    }
    
    public double getVolumenMinimo(){
        return volumenminimo;
    }
    
    public void reestablecerTanque(){
        volumentemp = capacidad;
    }
}
