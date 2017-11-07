
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
    protected String identificacion;
    protected double altura;
    protected Valvula[] valvulas;// este array tendra 10 posiciones porque un tanque solo puede tener 10 valvulas
    protected double capacidad;
    protected double volumentemp;
    protected double volumenminimo;
    
    public Tanque(){
        identificacion = "";
        altura = 0;
        valvulas = new Valvula[10];
        capacidad = 0;
        volumentemp = 0;
        volumenminimo = 0;
    }
    /**
     * Metodo que crea un nuevo tanque
     * @param identificacion y altura del tanque
     */
    public void setTanque(String identificacion,double altura){
        this.identificacion = identificacion;
        this.altura = altura;
        
        //con este ciclo se crea un objeto tipo valvula para cada una de las diez posiciones del array
        for(int i=0;i<valvulas.length;i++){
            Valvula valv = new Valvula();            
            valvulas[i] = valv;
        }        
    }
    /**
     * Get id de tanque
     * @return id
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    /**
     * Metodo que calcula el estado de cualquier valvula
     * @param numero de la valvula
     * @return true o false
     */
    public boolean getEstadoAlgunaValvula(int numvalvula){  
        if(valvulas[numvalvula-1].getEstado()==true){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Metodo que abre las valvulas
     * @param numero de la valvula
     */
    public void abrirValvulaCualquiera(int numvalvula){        
        valvulas[numvalvula-1].abrirValvula();
        decrementarVolumen();
    }
   
    /**
     * Metodo que cierra las valvulas
     * @param numero de valvula
     */
    public void cerrarValvulaCualquiera(int numvalvula){
        valvulas[numvalvula-1].cerrarValvula();
    }
    
    /**
     * Metodo que asigna un municipio a una valvula
     * @param numero valvula 
     * @param municipio 
     */
    public void asignarMunicipioValvulaCualquiera(int numvalvula,String municipio){
        for(int i=0;i<valvulas.length;i++){            
            valvulas[numvalvula-1].asignarMunicipio(municipio);
        }
    }
    
    /**
     * Metodo que devuelve la identificacion 
     * @return identificacion 
     */
    public String getIdentificacion(){
        return identificacion;
    }
    
    /**
     * Metodo que devulve la valvua
     * @return valvula
     */
    public Valvula[] getValvulas(){
        return valvulas;
    }
    
    /**
     * Metodo que cierra las valvulas utilizando un ciclo que recorre la lista
     */
    public void cerrarValvulas(){
        for(int i=0;i<valvulas.length;i++){            
            valvulas[i].cerrarValvula();            
        }   
    }
   //-------------------------------------
    
    /**
     * Metodo que decremente el volumen actual del cilindro un 10%
     */
    public void decrementarVolumen(){
        volumentemp = volumentemp - capacidad*0.10;
    }
    
    /**
     * Metodo que devuelve la capacidad del cilindro
     * @return capacidad
     */
    public double getCapacidad(){
        return capacidad;
    }
    
    /**
     * Metodo que devuelve el volumen temporal del cilindro
     * @return volumen temporal del cilindro
     */
    public double getVolumenTemporal(){
        return volumentemp;
    }
    
    /**
     * Metodo que devuelve el volumen minimo del cilindro
     * @return volumen minimo
     */
    public double getVolumenMinimo(){
        return volumenminimo;
    }
    
    /**
     * Metodo que restablece la capacidad del tanque a su capacidad maxima
     */
    public void reestablecerTanque(){
        volumentemp = capacidad;
    }
    
    /**
     * Metodo que devuelve la altura del cilindro
     * @return altura 
     */
    public double getAltura(){
        return altura;
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
}
