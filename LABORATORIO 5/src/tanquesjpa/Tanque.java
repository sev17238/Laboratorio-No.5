
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
public class Tanque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // atributos: 
    protected String ID;
    protected double altura;
    protected Valvula[] valvulas;
    protected double capacidad;
    protected double volumentemp;
    protected double volumenminimo;
    
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
        if (!(object instanceof Tanque)) {
            return false;
        }
        Tanque other = (Tanque) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tanquesjpa.Tanque[ id=" + id + " ]";
    }
    
    public Tanque(){
        
    }
    
    public void setTanque(String ID,double altura){
        this.ID = ID;
        this.altura = altura;
        
        for(int i=0;i<valvulas.length;i++){
            Valvula valv = new Valvula();            
            valvulas[i] = valv;
        }        
    }
    /**
     * Metodo que calcula el volumen de un tanque cubico.
     * @param altura del tanque 
     * @return volumen del tanque cubico
     */
    public double calcularVolumen(double altura){
        double volumen = Math.pow(altura, 3);
        return volumen;
    }
    
    
    
    public void abrirValvulaCualquiera(int numvalvula){        
        valvulas[numvalvula].abrirValvula();
    }
   
    public void cerrarValvulaCualquiera(int numvalvula){
        valvulas[numvalvula].cerrarValvula();
    }
    
    public String getID(){
        return ID;
    }
    
    public Valvula[] getValvulas(){
        return valvulas;
    }
    
    public void cerrarValvulas(){
        for(int i=0;i<valvulas.length;i++){            
            valvulas[i].cerrarValvula();            
        }   
    }
   //-------------------------------------
    
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
