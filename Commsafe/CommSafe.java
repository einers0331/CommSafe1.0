
/**
 * Write a description of class CommSafe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.io.IOException;
import java.util.HashMap;

public class CommSafe
{
    public static void clrscr(){
        //Clears Screen in Java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }

    public static String inputString(String str){
        System.out.println(str);
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine();
    }

    public static int inputInt(String str){
        System.out.println(str);
        Scanner myObj = new Scanner(System.in);
        return myObj.nextInt();
    }

    public static void main(String[] args) 
    {
        clrscr();
        Fabrica objetos = new Fabrica();
        boolean inicio = true;
        Registros registros = objetos.registros;
        HashMap<Ciudadano,String> sesion = new HashMap<>();

        while(inicio)
        {
            try{
                String st = "------CommSafe------ \n";
                st += "Bienvenido Ciudadano \n";
                st += "1. Iniciar Sesion\n2. Registro \n3. Salir";

                String switch1 = inputString(st);
                Scanner myObj = new Scanner(System.in);
                clrscr();
                //menu inicial
                switch (switch1) 
                {
                    case "1":
                        boolean digitandodatos = true;
                        while(digitandodatos)
                        {
                            try{                               
                                int cedula = inputInt("Cedula: \n");
                                String contrasena = inputString("Contraseña:");
                                clrscr();

                                String op = inputString("1. Confirmar Sesion \n2. Cancelar Sesion");
                                clrscr();

                                if(op.equals("1"))
                                {
                                    //realiza la validación de los datos para iniciar sesión.
                                    Ciudadano c = registros.validacion(cedula,contrasena);

                                    if(c == null)
                                    {
                                        System.out.println("Error en la autenticación \n");
                                    }

                                    else  //La validación es correcta

                                    {  
                                        boolean s = true;
                                        //Se vuelve false para poder volver al primer menu cuando decida cerrar sesión.
                                        digitandodatos = false; 
                                        sesion.put(c,"cumbre"); //añade la ubicación actual

                                        while(s){
                                            String mb = "        "+c.getNombre()+" "+c.getApellido()+"        \n\n";
                                            mb += "--------Menu Barra-------- \n";
                                            mb += "1. Ver publicaciones \n2. Agregar Publicacion \n3. Perfil \n4. Cerrar sesion";
                                            String switch2 = inputString(mb);
                                            clrscr();

                                            switch(switch2)
                                            {
                                                case "1": // Lista de publicaciones (Muro)

                                                    String ubicacionactual = "";                                                   
                                                    //Obtiene la ubicación de la sesion
                                                    for (Ciudadano oper6 : sesion.keySet()) 
                                                    {
                                                        if(oper6.getCedula() == cedula)
                                                        {
                                                            ubicacionactual = sesion.get(oper6);
                                                        }

                                                    }

                                                    ArrayList<Ciudadano> ciudadanos = registros.getCuidadanos();
                                                    ArrayList<Post> Muro = new ArrayList<>();
                                                    
                                                    System.out.println("--------- Ubicación Actual: "+ubicacionactual+" ---------\n");
                                                    //Muestra todos las publicaciones de la ubicación actual 
                                                    for(Ciudadano iterar: ciudadanos )
                                                    {
                                                        ArrayList<Post> publicaciones = iterar.getPost();
                                                        for (Post iterar1: publicaciones)
                                                        {                                                              
                                                            if(ubicacionactual.equals(iterar1.getUbicacion()) )
                                                            {
                                                                Muro.add(iterar1); 
                                                            }
                                                        }
                                                    }
                                                    System.out.println("-------------- Muro ---------------");
                                                    for(Post iterar2: Muro)
                                                    {
                                                        System.out.print(iterar2.showPost().substring(68));
                                                    }
                                                    inputString("Presione cualquier tecla para volver");                                            
                                                    clrscr();
                                                    break;
                                                case "2":// añadir post
                                                    String desc = inputString("Por favor agrega una  Descripcion: \n");   

                                                    for (Ciudadano oper7 : sesion.keySet()) 
                                                    {
                                                        if(oper7.getCedula() == cedula)
                                                        {
                                                            Post oper8 = oper7.addPost(desc,sesion.get(oper7));
                                                            String op9 = inputString("¿Desea agregar archivo multimedia? \n1. SI \n2. NO");   
                                                            if(op9.equals("1"))
                                                            {
                                                                String op10 = inputString("Agrege ruta del archivo multimedia: \n");
                                                                oper8.setMultimedia(op10);
                                                            }
                                                        }

                                                    }

                                                    clrscr();

                                                    break;

                                                case "3":// mostrar perfil
                                                    boolean verperfil = true;
                                                    while(verperfil){

                                                        Ciudadano cp = null;
                                                        for (Ciudadano oper11 : sesion.keySet()) 
                                                        {
                                                            if(oper11.getCedula() == cedula)
                                                            {   cp = oper11;
                                                                cp.showPerfil();
                                                            }

                                                        }
                                                        String op13 = inputString("1. Eliminar  |  2. Modificar Post  | 3.Modificar Perfil 4. Volver");
                                                        switch(op13)
                                                        {
                                                            case "1":// eliminar post 
                                                                String op14 = inputString("Escriba el id de la publicacion que quiere borrar:");
                                                                int cast = Integer.parseInt(op14);
                                                                cp.eliminarPost(cast);

                                                                break;
                                                            case "2"://modificar perfil    
                                                                boolean modificando = true;

                                                                String op15 = inputString("Escriba el id de la publicacion que quiere modificar");
                                                                int cast1 = Integer.parseInt(op15);
                                                                while(modificando){

                                                                    String modi = "Que desea modificar?: \n";
                                                                    modi += "1. Descripción \n";
                                                                    modi += "2. Multimedia \n";
                                                                    modi += "3. Volver \n";

                                                                    String modst = inputString(modi);

                                                                    switch(modst){
                                                                        case "1":
                                                                            String op16 = inputString("Escriba una nueva descripción");
                                                                            cp.getPost().get(cast1 -1).setDescripcion(op16);
                                                                            break;
                                                                        case "2":
                                                                            String op17 = inputString("Escriba la ruta del archivo multimedia");
                                                                            cp.getPost().get(cast1 -1).setMultimedia(op17);
                                                                            break;
                                                                        case "3":
                                                                            modificando = false;
                                                                            break;
                                                                        default:
                                                                            System.out.println("Escriba una opción valida");
                                                                            inputString("Presione cualquier tecla para volver");                                            
                                                                            clrscr();
                                                                            break;
                                                                    }
                                                                }
                                                                break;
                                                            case "3":// modificar datos perfil
                                                                boolean modifi = true;
                                                                while(modifi){
                                                                    String opcstr = "Que dato desea modificar: \n";
                                                                    opcstr += "1. contraseña \n";
                                                                    opcstr += "2. celular \n";
                                                                    opcstr += "3. dirección \n";
                                                                    opcstr += "4. ciudad \n";
                                                                    opcstr += "5. foto \n";
                                                                    opcstr += "6. Volver \n";

                                                                    String opc  = inputString(opcstr);
                                                                    clrscr();

                                                                    cp = null;
                                                                    for (Ciudadano oper11 : sesion.keySet()) 
                                                                    {
                                                                        if(oper11.getCedula() == cedula)
                                                                        {   
                                                                            cp = oper11;
                                                                        }

                                                                    }
                                                                    switch(opc){
                                                                        case "1":// cambiar contrasena
                                                                            cp.setContrasena(inputString("Ingrese la contraseña:"));
                                                                            break;
                                                                        case "2":// cambiar celular
                                                                            cp.setCelular(inputInt("Ingrese su número de celular:"));
                                                                            break;
                                                                        case "3":// cambiar direccion
                                                                            cp.setDireccion(inputString("Ingrese su dirección:"));
                                                                            break;
                                                                        case "4":// cambiar ciudad
                                                                            cp.setCiudad(inputString("Ingrese su ciudad:"));
                                                                            break;
                                                                        case "5":// cambiar foto
                                                                            cp.setFoto(inputString("Ingrese la ruta del archivo:"));
                                                                            break;
                                                                        case "6":
                                                                            modifi = false;
                                                                            break;
                                                                        default:
                                                                            System.out.print("Ingrese un numero entre 1 y 6");
                                                                        break;
                                                                    }
                                                                }
                                                                break;
                                                            case "4":// retorna al menu anterior

                                                                verperfil = false;

                                                                break;

                                                            default:
                                                                System.out.println("Elija una opcion entre 1 y 4");
                                                                inputString("Presione cualquier tecla para volver");                                            
                                                                clrscr();
                                                                break;
                                                        }

                                                        clrscr();
                                                    }
                                                    

                                                    break;

                                                case "4": // cerrar sesion

                                                    s = false;

                                                    break;
                                                default:
                                                    System.out.println("Elija una opcion entre 1 y 4");
                                                    inputString("Presione cualquier tecla para volver");                                            
                                                    clrscr();
                                                    break;

                                            }
                                        }
                                    }
                                }
                                else 
                                {
                                    digitandodatos = false;
                                }
                            }
                            catch(Exception e)
                            {
                                System.out.println("Presione un valor numerico para la opcion");
                            }
                        }
                        break;
                    case "2":  //Registro
                        ArrayList<String> datos = new ArrayList<>();
                        datos.add("Nombres");
                        datos.add("Apellidos");
                        datos.add("Contrasena");
                        datos.add("Direccion");
                        datos.add("Ciudad");

                        ArrayList<String> datos1 = new ArrayList<>();

                        for(String str: datos )
                        {
                            datos1.add(inputString(str));
                        } 
                        int cedula = inputInt("Cedula");
                        int celular = inputInt("Celular");
                        clrscr();
                        
                        if (inputString("1. Confirmar registro \n 2. Cancelar registro").equals("1"))
                        {
                            registros.registrarCiudadano(datos1.get(0), datos1.get(1), datos1.get(2), cedula, celular,datos1.get(3), datos1.get(4));
                        }
                        clrscr();

                        break;

                    case "3":  //Salir
                        inicio = false;
                        break;
                    default:
                        System.out.println("Elija una opcion entre 1 y 3");
                        break;
                }
            }
            catch(Exception e){
                System.out.println("Ingrese una opción valida");
            }
        }
    }
}