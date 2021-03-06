
package tanquesjpa;
import java.util.List;
/**
 * 
 * @author MarianaMorales17235
 * @author DiegoSevilla17238
 */
public class RegistroTanques {
    private Tanque[] tanquesRegion;
    
    public RegistroTanques(){
        tanquesRegion = new Tanque[10];
    
    }
    
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
    
    public double calcular_m3_disponibles(){
        double volumentotal = 0;
        Tanque tanq = new Tanque();
        for(int i=0;i<tanquesRegion.length;i++){
            tanq = tanquesRegion[i];
            volumentotal = volumentotal + tanq.getVolumenTemporal();                    
        }
        return volumentotal;
    }
    
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
                IDS[m] = tanq.getID();
                m++;
            }
        }
        return IDS;
    }
        
    public Tanque[] getTanquesRegion(){
        return tanquesRegion;
    }
    
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
    
    public Tanque getTanqueBuscado(String ID){
        Tanque tanq = new Tanque();
        for(int i=0;i<tanquesRegion.length;i++){
            tanq = tanquesRegion[i];
            if(tanq.getID().equals(ID)){
                return tanq;
            }
        } 
        return null;
    }
    
    /*public void reestablecerTanqueBuscado(String ID){
        Tanque tanq = new Tanque();
        for(int i=0;i<tanquesRegion.length;i++){
            tanq = tanquesRegion[i];
            if(tanq.getID().equals(ID)){
                tanq.reestablecerTanque();
            }
        } 
    }*/
    
}
