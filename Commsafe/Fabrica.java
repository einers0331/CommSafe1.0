
/**
 * Write a description of class Fabrica here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fabrica
{
    Registros registros = new Registros();
    
    /**
     * Constructor for objects of class Fabrica
     */
    public Fabrica()
    {
    // registrando cuidadanos
    registros.registrarCiudadano("pedro", "gomez", "1", 1, 313456789, "calle 50 13-90","medellin");
    registros.registrarCiudadano("martha", "lopez", "contrasena", 98677127, 312847879, "carrera 76 18-90","bucaramanga");
    registros.registrarCiudadano("paco", "venoso", "holamundo", 3112331, 312425353, "avenida circunvlar calle 34-12","bogota");
    //agregar publicaciones a cada ciudadano registrado
    registros.getCuidadanos().get(0).addPost("sujeto sospechoso","cumbre");
    registros.getCuidadanos().get(0).addPost("bomba sospechoso","cumbre");
    registros.getCuidadanos().get(0).addPost("bombazo","cumbre");
    registros.getCuidadanos().get(0).addPost("bombita","cumbre");
    registros.getCuidadanos().get(1).addPost("robo a mano armada","cumbre");
    registros.getCuidadanos().get(2).addPost("hurto con arma blanca","cumbre");

    }

}
