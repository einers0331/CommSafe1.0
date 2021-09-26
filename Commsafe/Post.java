
/**
 * Write a description of class Post here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Post
{
    // instance variables - replace the example below with your own
    private int identificador;
    private String nombreC; 
    private String descripcion;
    private String ubicacion;
    private String multimedia;
    private String datetime;

    /**
     * Constructor for objects of class Post
     */
    public Post(int identificador, String nombre, String desc, String ubicacion)
    {
        nombreC = nombre;
        descripcion = desc;
        this.identificador = identificador;
        this.ubicacion = ubicacion;
        datetime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }
    public void setDescripcion(String newdescripcion){
        this.descripcion = newdescripcion;
    }
    public String getUbicacion(){
        return this.ubicacion;
    }
    public void setIdentificador(int id){
        this.identificador = id;
    }
    
    //El atributo foto es una ruta ('/foto.jpg')
    public void setMultimedia(String foto){
        this.multimedia = foto;
    }
    
    public String getMultimedia(){
        return this.multimedia;
    }
    
    public void showPost(){
        String info = "---------------------------------------------------------- \n";
        info +=  identificador + " |      " + nombreC + "|"+"    "+ ubicacion +"      |      " + datetime + " |\n";
        info += "---------------------------------------------------------- \n\n";
        info += descripcion + "\n\n";
        if(multimedia != null){
            info += multimedia;
        }
        System.out.println(info + "\n");
    }
}