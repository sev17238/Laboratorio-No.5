
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
    
    protected double radio;
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
    }
    
    public void calcularVolumen(double altura, double radio){
        double volumen = Math.PI* Math.pow(radio, 2)* altura;
        capacidad = volumen;
        volumentemp= volumen;
        volumenminimo = volumen/4;
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
