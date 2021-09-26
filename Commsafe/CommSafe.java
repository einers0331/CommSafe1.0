
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
                st += "1. Iniciar Sesion\n2. Olvide mi contraseña \n3. Registro \n4. Salir";

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
                                System.out.println("Cedula:");
                                myObj = new Scanner(System.in);
                                int cedula = myObj.nextInt();
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

                                            String mb = "--------Menu Barra-------- \n";
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
                                                    System.out.println("Muro");
                                                    for(Post iterar2: Muro)
                                                    {
                                                        iterar2.showPost();
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
                                                    do{

                                                        Ciudadano cp = null;
                                                        for (Ciudadano oper11 : sesion.keySet()) 
                                                        {
                                                            if(oper11.getCedula() == cedula)
                                                            {   cp = oper11;
                                                                cp.showPerfil();
                                                            }

                                                        }
                                                        String op13 = inputString("1. Eliminar  |  2. Modificar Post  | 3.Modificar Perfil 4. Salir");
                                                        switch(op13)
                                                        {
                                                            case "1":// eliminar post
                                                                System.out.println(); 
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
                                                                String opcstr = "Que dato desea modificar: \n";
                                                                opcstr += "1. nombre \n";
                                                                opcstr += "2. apellido \n";
                                                                opcstr += "3. contraseña \n";
                                                                opcstr += "4. cedula \n";
                                                                opcstr += "5. celular \n";
                                                                opcstr += "6. dirección \n";
                                                                opcstr += "7. ciudad \n";
                                                                opcstr += "8. foto \n";

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
                                                                    case "1":// cambiar nombre
                                                                        cp.setNombre(inputString("Ingrese el nombre:"));
                                                                        break;
                                                                    case "2":// cambiar apellido
                                                                        cp.setApellido(inputString("Ingrese el apellido:"));
                                                                        break;
                                                                    case "3":// cambiar contasena
                                                                        cp.setContrasena(inputString("Ingrese la contraseña:"));
                                                                        break;
                                                                    case "4":// cambiar cedula
                                                                        cp.setCedula(inputInt("Ingrese la cedula:"));
                                                                        break;
                                                                    case "5":// cambiar celular
                                                                        cp.setCelular(inputInt("Ingrese su número de celular:"));
                                                                        break;
                                                                    case "6":// cambiar direccion
                                                                        cp.setDireccion(inputString("Ingrese su dirección:"));
                                                                        break;
                                                                    case "7":// cambiar ciudad
                                                                        cp.setCiudad(inputString("Ingrese su ciudad:"));
                                                                        break;
                                                                    case "8":// cambiar foto
                                                                        cp.setFoto(inputString("Ingrese la ruta del archivo:"));
                                                                        break;

                                                                }
                                                            case "4":// retorna al menu anterior

                                                                verperfil = false;

                                                                break;

                                                            default:
                                                                System.out.println("Elija una opcion entre 1 y 4");
                                                                inputString("Presione cualquier tecla para volver");                                            
                                                                clrscr();

                                                        }

                                                        clrscr();
                                                    }
                                                    while(verperfil);

                                                    break;

                                                case "4": // cerrar sesion

                                                    s = false;

                                                    break;
                                                default:
                                                    System.out.println("Elija una opcion entre 1 y 4");
                                                    inputString("Presione cualquier tecla para volver");                                            
                                                    clrscr();

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

                    case "2":  //Olvide mi contraseña
                        break;
                    case "3":  //Registro
                        ArrayList<String> datos = new ArrayList<>();
                        datos.add("Nombres");
                        datos.add("Apellidos");
                        datos.add("Contrasena");
                        datos.add("Direccion");
                        datos.add("Ciudad");

                        ArrayList<String> datos1 = new ArrayList<>();

                        for(String str: datos )
                        {
                            System.out.println(str);
                            myObj = new Scanner(System.in);
                            datos1.add(myObj.nextLine());

                        }
                        System.out.println("Cedula");
                        myObj = new Scanner(System.in);
                        int cedula = myObj.nextInt();
                        System.out.println("Celular");
                        myObj = new Scanner(System.in);
                        int celular = myObj.nextInt();

                        clrscr();
                        System.out.println("1. Confirmar registro \n 2. Cancelar registro");
                        myObj = new Scanner(System.in);
                        int op = myObj.nextInt();
                        clrscr();
                        if (op==1)
                        {
                            registros.registrarCiudadano(datos1.get(0), datos1.get(1), datos1.get(2), cedula, celular,datos1.get(3), datos1.get(4));
                        }

                        break;

                    case "4":  //Salir
                        inicio = false;
                        break;
                    default:
                        System.out.println("Elija una opcion entre 1 y 4");

                }
            }
            catch(Exception e){
                System.out.println("Ingrese una opción valida");
            }
        }
    }
}