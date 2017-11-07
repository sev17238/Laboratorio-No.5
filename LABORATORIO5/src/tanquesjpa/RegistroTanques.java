
package tanquesjpa;
import java.util.List;
/**
 * Clase que representa el registro de los tanques del acueducto de Albear
 * @author MarianaMorales17235
 * @author DiegoSevilla17238
 */
public class RegistroTanques {
    private Tanque[] tanquesRegion;
    /**
     * Constructor principal de la clase con un array de 10 posiciones.
     */
    public RegistroTanques(){
        tanquesRegion = new Tanque[10];
    
    }
    /**
     * Constructor secundario de la clase que es usado en la clase de BaseDatosTanques para obtener los 
     * tanques alamacenados en ella.
     * @param cilindricos
     * @param cubicos
     * @param ortogonales 
     */
    public RegistroTanques(List<TCilindrico> cilindricos,List<TCubico> cubicos,List<TOrtogonal> ortogonales){
        tanquesRegion = new Tanque[10];
        Tanque tanq = new Tanque();
        int count = 0;
        for(int i=0;i<cilindricos.size();i++){
            tanq = cilindricos.get(i);
            tanquesRegion[count] = tanq;
            count ++;
        }
        for(int i=0;i<cubicos.size();i++){
            tanq = cubicos.get(i);
            tanquesRegion[count] = tanq;
            count ++;
        }
        for(int i=0;i<ortogonales.size();i++){
            tanq = ortogonales.get(i);
            tanquesRegion[count] = tanq;
            count ++;
        } 
    }
    /**
    * Método que calcula la cantidad total de metros cúbicos de agua disponible que quedan para la región.
    * @return volumen total del tanque
    */ 
    public double calcular_m3_disponibles(){
        double volumentotal = 0;
        Tanque tanq = new Tanque();
        for(int i=0;i<tanquesRegion.length;i++){
            tanq = tanquesRegion[i];
            if(tanq != null){
                volumentotal = volumentotal + tanq.getVolumenTemporal();   
            }
        }
        return volumentotal;
    }
    
    /**
     * metodo que cuenta la cantidad de valvulas abiertas de los tanques cilindricos de la lista tanques
     * @return valvulas abiertas
     */
    public int cantidadValvulasCilindricos(){
        int valvulasabiertas = 0;
        Tanque tanq = new Tanque();
        for(int i=0;i<tanquesRegion.length;i++){
            tanq = tanquesRegion[i];
            if(tanq instanceof TCilindrico){
                Valvula valv = new Valvula();
                for(int e=0;e<tanq.getValvulas().length;e++){
                    valv = tanq.getValvulas()[e];
                    if(valv.getEstado()==true){
                        valvulasabiertas++; 
                    }
                }
            }                    
        }
        return valvulasabiertas;
    }
    /**
     * Metodo que retorna una lista con los id de cada uno de los tanques que haya en la lista TanquesRegion
     * @return IDS
     */
    public String[] retornarListaIDTanques(){
        int tanques = 0;
        Tanque tanq = new Tanque();
        for(int i=0;i<tanquesRegion.length;i++){
            tanq = tanquesRegion[i];
            if(tanq != null){
                tanques++;
            }
        }        
        String[] IDS = new String[tanques];
        int m=0;
        for(int i=0;i<tanquesRegion.length;i++){
            tanq = tanquesRegion[i];
            if(tanq != null){
                IDS[m] = tanq.getIdentificacion();
                m++;
            }
        }
        return IDS;
    }
    /**
     * Metodo retorna la lista en donde se almacenan todos los tanques ingresados
     * @return tanquesRegion
     */    
    public Tanque[] getTanquesRegion(){
        return tanquesRegion;
    }
    /**
     * Metodo que ingresa un nuevo tanque a la lista de los tanques de la region.
     * @param tanque un tanque de cualquier tipo
     */
    public void nuevoTanque(Tanque tanque){
        Tanque tanq = new Tanque();
        for(int i=0;i<tanquesRegion.length;i++){
            tanq = tanquesRegion[i];
            if(tanq == null){
                tanquesRegion[i] = tanque;
                break;
            }
        }        
    }
    /**
     * Metodo que retorna el tanque con el ID ingresado
     * @param ID del tanque que se quiere encontrar
     * @return el tanque al que le pertenece la ID especificada
     */
    public Tanque getTanqueBuscado(String ID){
        Tanque tanq = new Tanque();
        for(int i=0;i<tanquesRegion.length;i++){
            tanq = tanquesRegion[i];
            if(tanq.getIdentificacion().equals(ID)){
                return tanq;
            }
        } 
        return null;
    }
    
}
