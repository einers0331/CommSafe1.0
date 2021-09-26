
/**
 * Write a description of class Ciudadano here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Scanner;
public class Ciudadano
{
    private String nombre;
    private String apellido;
    private String contrasena;
    private int cedula;
    private int celular;
    private String direccion;
    private String foto;
    private String ciudad;
    private ArrayList<String> perfil;
    private ArrayList<Post> posts;
    private int id;

    /**
     * Constructor for objects of class Ciudadano
     */
    public Ciudadano(String nombre, String apellido, String contrasena, int cedula, int celular, String direccion,String ciudad)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.cedula = cedula;
        this.celular = celular;
        this.ciudad = ciudad;
        this.direccion = direccion;
        posts = new ArrayList<>();
        id = 0;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getApellido(){
        return this.apellido;
    }
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    public int getCedula(){
        return this.cedula;
    }
    public void setCedula(int cedula){
        this.cedula = cedula;
    }
    
    public int getCelular(){
        return this.celular;
    }
    public void setCelular(int celular){
        this.celular = celular;
    }
    
    public String getDireccion(){
        return this.direccion;
    }
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    
     public String getContrasena(){
        return this.contrasena;
    }
    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }
    
    //Establece una ruta con la imagen
    public void setFoto(String ft){
        foto = ft;
    }
    
    public void eliminarPost(int id){ 
        posts.remove(id - 1);
        for(int i=0;i<posts.size();i++){
            posts.get(i).setIdentificador(i+1);
        }
    }
    
     public String getCiudad(){
        return this.ciudad;
    }
    public void setCiudad(String ciudad){
        this.ciudad = ciudad;
    }
    
    public void setPerfil(){
        perfil.add(nombre);
        perfil.add(apellido);
        perfil.add(foto);
    }
    
    public Post addPost(String desc,String ubicacion){
        id++;
        Post p = new Post(id,nombre,desc,ubicacion);
        posts.add(p);
        return p;
    }
    public ArrayList<Post> getPost()
    {
    return posts;
    }
    
    public void showPerfil(){
        String perfil = "";
        if (foto != null){
            perfil += "               "+ foto + "\n";
        }
        perfil += "------------" + nombre + " " + apellido + "------------\n";
        
        System.out.println(perfil);
        
        System.out.println("Publicaciones \n");
        
        if (posts != null){
            for(Post p : posts){
                p.showPost();
            }
        }
        else{
            System.out.println("Aun no ha hecho ningun post.");
        }
        
    }
}
