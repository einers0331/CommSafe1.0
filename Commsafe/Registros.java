
/**
 * Write a description of class Registro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Iterator;

public class Registros
{
    // instance variables - replace the example below with your own
    private ArrayList<Ciudadano> registros = new ArrayList<>();

    /**
     * Constructor for objects of class Registro
     */
    public Registros()
    {
        // initialise instance variables

    }

    public void registrarCiudadano(String nombre, String apellido, String contrasena, int cedula, int celular, String direccion,String ciudad)
    {
        // put your code here
        Ciudadano ciudadano = new Ciudadano(nombre,apellido,contrasena,cedula,celular,direccion,ciudad);
        registros.add(ciudadano);
    }

    public ArrayList<Ciudadano> getCuidadanos()
    {
        return registros;
    }

    public Ciudadano validacion(int cedula, String contrasena){
        //ArrayList<Ciudadano> listaciudadanos = registros.getCuidadanos();
        boolean buscando = true;
        Iterator<Ciudadano> ite = registros.iterator();
        while(buscando && ite.hasNext())
        {
            Ciudadano c = ite.next();
            if((c.getCedula() == cedula) && (c.getContrasena().equals(contrasena)))
            {  
                buscando = false;
                return c;
            }

        }
        return null;
    }

}
