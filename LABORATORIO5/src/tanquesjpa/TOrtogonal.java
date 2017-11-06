
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
public class TOrtogonal extends Tanque implements Serializable {

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    //atributos
    protected double base;
    protected double largo;
    
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    
    /**
     * Constructor for objects of class TOrtogonal: Utilizado por la base de datos
     */
    public TOrtogonal(){
        super.setTanque("", 0);
        this.base = 0;
        this.largo = 0;
    }
    
    public void setTOrtogonal(String id, double base, double largo, double altura){
        super.setTanque(id, altura);
        this.base = base;
        this.largo = largo;
        double volumen = calcularVolumen(altura,largo,base);
        capacidad = volumen;
        volumentemp= volumen;
        volumenminimo = volumen/4;
    }
    /**
     * Metodo que calcula el volumen de un tanque ortogonal
     * @param altura del tanque
     * @param base del tanque
     * @param largo del tanque
     * @return el volumen del tanque ortogonal
     */    
    public double calcularVolumen(double altura, double base, double largo){
        double volumen = base * largo * altura;
        return volumen;        
    }
    
    public double getBase(){
        return base;
    }
    
    public double getLargo(){
        return largo;
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
        if (!(object instanceof TOrtogonal)) {
            return false;
        }
        TOrtogonal other = (TOrtogonal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tanquesjpa.TOrtogonal[ id=" + id + " ]";
    }   
    
}
