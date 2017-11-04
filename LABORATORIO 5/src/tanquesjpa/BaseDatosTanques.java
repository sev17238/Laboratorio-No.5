
package tanquesjpa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
/**
 *
 * @author DiegoSevilla17238
 * @author MarianaMorales17235
 */
public class BaseDatosTanques {
    RegistroTanques listatanques;
    EntityManagerFactory emf;  // para especificar la Persistent Unit y conexion a la base de datos
    EntityManager em; // manejador de las entidades en la base de datos
    
    public BaseDatosTanques(){
        emf = javax.persistence.Persistence.createEntityManagerFactory("LABORATORIO_5PU");
        em = emf.createEntityManager();
        listatanques = new RegistroTanques();
    }
    
    public void cerrarDB(){
        em.close();
        emf.close();
    }
    
    /**
     * Leer los trabajadores almacenados en la base de datos
     * y colocarlos en la universidad
     */
    public void recuperarTanques(){
        // recuperar todos los tanques cilindricos de la base de datos:
        Query q = em.createQuery("select d from TCilindrico d");
        List<TCilindrico> cilindricos = q.getResultList();
        
        // recuperar todos los tanques cubicos de la base de datos:
        Query q1 = em.createQuery("select nd from TCubico nd");
        List<TCubico> cubicos = q1.getResultList();
        
        // recuperar todos los tanques ortogonales de la base de datos:
        Query q2 = em.createQuery("select c from TOrtogonal c");
        List<TOrtogonal> ortogonales = q2.getResultList();
        
        // agregarlos a la universidad
        listatanques = new RegistroTanques(cilindricos,cubicos,ortogonales);
    }
    
    public void nuevoTanqueCilindrico(String id,double altura,double radio){
        TCilindrico cil = new TCilindrico();
        cil.setTCilindrico(id, altura, radio);
        
        
    }
    
    public void nuevoTanqueCubico(){
        
    }
    
    public void nuevoTanqueOrtogonal(){
        
    }
    
    public RegistroTanques getListaTanques(){
        return listatanques;
    }
}
