
package tanquesjpa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
/**
 * Clase que representa el puente entre el programa y la base de datos; En esta clase se actualizan los 
 * tanques del registro y se ingresan a la base de datos ya con los cambios.
 * @author MarianaMorales17235
 * @author DiegoSevilla17238
 */
public class BaseDatosTanques {
    RegistroTanques listatanques;
    EntityManagerFactory emf;  // para especificar la Persistent Unit y conexion a la base de datos
    EntityManager em; // manejador de las entidades en la base de datos
    /**
     * Constructor de la clase.
     */
    public BaseDatosTanques(){
        emf = javax.persistence.Persistence.createEntityManagerFactory("LABORATORIO5PU");
        em = emf.createEntityManager();
        listatanques = new RegistroTanques(); //registro de tanques vacio
    }
    /**
     * Metodo que cierra las operaciones realizadas por los atributos dedicados al almacenamiento 
     * de datos en mySQL JPA.
     */
    public void cerrarDB(){
        em.close();
        emf.close();
    }
    
    /**
     * Este metodo lee los tanques almacenados en la base de datos y los coloca
     * en el registro de tanques.
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
        
        // recuperar todas las valvulas de la base de datos:
        //Query v = em.createQuery("select e from Valvula e");
        //List<Valvula> valvulas = v.getResultList();
        
        // agregarlos a al registro de tanques.
        listatanques = new RegistroTanques(cilindricos,cubicos,ortogonales);
    }
    
    /**
     * Metodo que ingresa un nuevo tanque cilindrico a la base de datos.
     * @param id del tanque cilindrico
     * @param altura del tanque cilindrico
     * @param radio del tanque cilindrico
     */
    public void nuevoTanqueCilindrico(String id,double altura,double radio){
        TCilindrico cil = new TCilindrico();
        cil.setTCilindrico(id, altura, radio);
        listatanques.nuevoTanque(cil);  
        em.getTransaction().begin();
        em.persist(cil);
        em.getTransaction().commit();      
    }
    /**
     * Metodo que ingresa un nuevo tanque cubico a la base de datos.
     * @param id del tanque cubico
     * @param altura del tanque cubico
     */
    public void nuevoTanqueCubico(String id,double altura){
        TCubico cub = new TCubico();
        cub.setTCubico(id, altura);
        listatanques.nuevoTanque(cub);
        em.getTransaction().begin();
        em.persist(cub);
        em.getTransaction().commit();
    }
    /**
     * Metodo que ingresa un nuevo tanque ortogonal a la base de datos.
     * @param id del tanque ortogonal
     * @param base del tanque ortogonal
     * @param largo del tanque ortogonal
     * @param altura del tanque ortogonal
     */
    public void nuevoTanqueOrtogonal(String id,double base,double largo,double altura){
        TOrtogonal ort = new TOrtogonal();
        ort.setTOrtogonal(id, base, largo, altura);
        listatanques.nuevoTanque(ort);
        em.getTransaction().begin();
        em.persist(ort);
        em.getTransaction().commit();
    }
    /**
     * Metodo que retorna el atributo de tipo RegistroTanques de la clase.
     * @return listatanques
     */
    public RegistroTanques getListaTanques(){
        return listatanques;
    }
    
    /**
     * Metodo que abre una valvula de cualquiera tanque y lo ingresa con el cambio a la base de datos.
     * @param ID del tanque
     * @param numvalvula del tanque
     * @param tanq del tanque
     */
    public void abrirValvulaAlgunTanque(String ID,int numvalvula,Tanque tanq){
        if(tanq instanceof TCilindrico){
            // recuperar de la base de datos un tanque con el ID dado:
            Query q = em.createQuery("select d from TCilindrico d where d.identificacion = :id");
            q.setParameter("id", ID);
            TCilindrico cil = (TCilindrico) q.getSingleResult();
            if(cil != null){
                cil.abrirValvulaCualquiera(numvalvula);
                em.getTransaction().begin();// grabar el tanque en la base de datos
                em.persist(cil);
                em.getTransaction().commit();
            } 
        }else if(tanq instanceof TCubico){
            // recuperar de la base de datos un tanque con el ID dado:
            Query q = em.createQuery("select d from TCubico d where d.identificacion = :id");
            q.setParameter("id", ID);
            TCubico cub = (TCubico) q.getSingleResult();
            if(cub != null){
                cub.abrirValvulaCualquiera(numvalvula);
                em.getTransaction().begin();// grabar el tanque en la base de datos
                em.persist(cub);
                em.getTransaction().commit();
            }  
        }else if(tanq instanceof TOrtogonal){
            // recuperar de la base de datos un tanque con el ID dado:
            Query q = em.createQuery("select d from TOrtogonal d where d.identificacion = :id");
            q.setParameter("id", ID);
            TOrtogonal ort = (TOrtogonal) q.getSingleResult();
            if(ort != null){
                ort.abrirValvulaCualquiera(numvalvula);
                em.getTransaction().begin();// grabar el tanque en la base de datos
                em.persist(ort);
                em.getTransaction().commit();
            }        
        }
    }
    
    /**
     * Metodo que cierra una valvula de cualquiera tanque y lo ingresa con el cambio a la base de datos.
     * @param ID del tanque
     * @param numvalvula de alguna valvula del tanque
     * @param tanq el tanque
     */
    public void cerrarValvulaAlgunTanque(String ID,int numvalvula,Tanque tanq){
        if(tanq instanceof TCilindrico){
            // recuperar de la base de datos un tanque con el ID dado:
            Query q = em.createQuery("select d from TCilindrico d where d.identificacion = :id");
            q.setParameter("id", ID);
            TCilindrico cil = (TCilindrico) q.getSingleResult();
            if(cil != null){
                cil.cerrarValvulaCualquiera(numvalvula);
                em.getTransaction().begin();// grabar el tanque en la base de datos
                em.persist(cil);
                em.getTransaction().commit();
            } 
        }else if(tanq instanceof TCubico){
            // recuperar de la base de datos un tanque con el ID dado:
            Query q = em.createQuery("select d from TCubico d where d.identificacion = :id");
            q.setParameter("id", ID);
            TCubico cub = (TCubico) q.getSingleResult();
            if(cub != null){
                cub.cerrarValvulaCualquiera(numvalvula);
                em.getTransaction().begin();// grabar el tanque en la base de datos
                em.persist(cub);
                em.getTransaction().commit();
            }    
        }else if(tanq instanceof TOrtogonal){
            // recuperar de la base de datos un tanque con el ID dado:
            Query q = em.createQuery("select d from TOrtogonal d where d.identificacion = :id");
            q.setParameter("id",ID);
            TOrtogonal ort = (TOrtogonal) q.getSingleResult();
            if(ort != null){
                ort.cerrarValvulaCualquiera(numvalvula);
                em.getTransaction().begin();// grabar el tanque en la base de datos
                em.persist(ort);
                em.getTransaction().commit();
            }  
        }
    }
   
    /**
     * Metodos para asignar un municipio a alguna de las valvulas de cualquiera de los tres tipos de tanque.
     * @param ID del tanque
     * @param numvalvula  de alguna valvula del tanque
     * @param Municipio  de alguna valvula del tanque
     * @param tanq el tanque
     */
    public void asignarMuniValvulaTanque(String ID,int numvalvula,String Municipio,Tanque tanq){
        if(tanq instanceof TCilindrico){
            // recuperar de la base de datos un tanque con el ID dado:
            Query q = em.createQuery("select d from TCilindrico d where d.identificacion = :id");
            q.setParameter("id", ID);
            TCilindrico cil = (TCilindrico) q.getSingleResult();
            if(cil != null){
                cil.asignarMunicipioValvulaCualquiera(numvalvula, Municipio);
                em.getTransaction().begin();// grabar el tanque en la base de datos
                em.persist(cil);
                em.getTransaction().commit();
            }        
        }else if(tanq instanceof TCubico){
            // recuperar de la base de datos un tanque con el ID dado:
            Query q = em.createQuery("select d from TCubico d where d.identificacion = :id");
            q.setParameter("id", ID);
            TCubico cub = (TCubico) q.getSingleResult();
            if(cub != null){
                cub.asignarMunicipioValvulaCualquiera(numvalvula, Municipio);
                em.getTransaction().begin();// grabar el tanque en la base de datos
                em.persist(cub);
                em.getTransaction().commit();
            }
        }else if(tanq instanceof TOrtogonal){
            // recuperar de la base de datos un tanque con el ID dado:
            Query q = em.createQuery("select d from TOrtogonal d where d.identificacion = :id");
            q.setParameter("id", ID);
            TOrtogonal ort = (TOrtogonal) q.getSingleResult();
            if(ort != null){
                ort.asignarMunicipioValvulaCualquiera(numvalvula, Municipio);
                em.getTransaction().begin();// grabar el tanque en la base de datos
                em.persist(ort);
                em.getTransaction().commit();
            }  
        }
    }
    
    /**
     * Metodo para cerrar todas las valvulas de un tanque cualquiera.
     * @param ID del tanque
     * @param tanq el tanque
     */
    public void cerrarTodasValvulasTanque(String ID,Tanque tanq){
        if(tanq instanceof TCilindrico){
            // recuperar de la base de datos un tanque con el ID dado:
            Query q = em.createQuery("select d from TCilindrico d where d.identificacion = :id");
            q.setParameter("id", ID);
            TCilindrico cil = (TCilindrico) q.getSingleResult();
            if(cil != null){
                cil.cerrarValvulas();
                em.getTransaction().begin();// grabar el tanque en la base de datos
                em.persist(cil);
                em.getTransaction().commit();
            } 
        }else if(tanq instanceof TCubico){
            // recuperar de la base de datos un tanque con el ID dado:
            Query q = em.createQuery("select d from TCubico d where d.identificacion = :id");
            q.setParameter("id", ID);
            TCubico cub = (TCubico) q.getSingleResult();
            if(cub != null){
                cub.cerrarValvulas();
                em.getTransaction().begin();// grabar el tanque en la base de datos
                em.persist(cub);
                em.getTransaction().commit();
            }  
        }else if(tanq instanceof TOrtogonal){
            // recuperar de la base de datos un tanque con el ID dado:
            Query q = em.createQuery("select d from TOrtogonal d where d.identificacion = :id");
            q.setParameter("id", ID);
            TOrtogonal ort = (TOrtogonal) q.getSingleResult();
            if(ort != null){
                ort.cerrarValvulas();
                em.getTransaction().begin();// grabar el tanque en la base de datos
                em.persist(ort);
                em.getTransaction().commit();
            } 
        }        
    }
    
    /**
     * Metodo que reestablecen un tanque cualquiera.
     * @param ID del tanque
     * @param tanq el tanque
     */
    public void reestablecerTanqueCualquiera(String ID, Tanque tanq){
        // recuperar de la base de datos un tanque con el ID dado:
        if(tanq instanceof TCilindrico){
            Query q = em.createQuery("select d from TCilindrico d where d.identificacion = :id");
            q.setParameter("id", ID);
            TCilindrico cil = (TCilindrico) q.getSingleResult();
            if(cil != null){
                cil.reestablecerTanque();
                em.getTransaction().begin();// grabar el tanque en la base de datos
                em.persist(cil);
                em.getTransaction().commit();
            }    
        }else if(tanq instanceof TCubico){
            Query q = em.createQuery("select d from TCubico d where d.identificacion = :id");
            q.setParameter("id", ID);
            TCubico cub = (TCubico) q.getSingleResult();
            if(cub != null){
                cub.reestablecerTanque();
                em.getTransaction().begin();// grabar el tanque en la base de datos
                em.persist(cub);
                em.getTransaction().commit();
            }    
        }else if(tanq instanceof TOrtogonal){
            Query q = em.createQuery("select d from TOrtogonal d where d.identificacion = :id");
            q.setParameter("id", ID);
            TOrtogonal ort = (TOrtogonal) q.getSingleResult();
            if(ort != null){
                ort.reestablecerTanque();
                em.getTransaction().begin();// grabar el tanque en la base de datos
                em.persist(ort);
                em.getTransaction().commit();
            }    
        }
            
    }
}
