
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
                System.out.println("CommSafe");
                System.out.println("Bienvenido Ciudadano");
                System.out.println("1. Iniciar Sesion\n2. Olvide mi contraseña \n3. Registro \n4. Salir");

                Scanner myObj = new Scanner(System.in);
                int opcion = myObj.nextInt();
                clrscr();

                switch (opcion) 
                {
                    case 1:
                        boolean digitandodatos = true;
                        while(digitandodatos)
                        {
                            try{
                                System.out.println("Cedula \n ");
                                myObj = new Scanner(System.in);
                                int cedula = myObj.nextInt();
                                System.out.println("Contraseña");
                                myObj = new Scanner(System.in);
                                String contrasena = myObj.nextLine();
                                clrscr();
                                System.out.println("1. Confirmar Sesion \n 2. Cancelar Sesion");
                                myObj = new Scanner(System.in);
                                int op = myObj.nextInt();
                                clrscr();

                                if(op == 1)
                                {

                                    Ciudadano c = registros.validacion(cedula,contrasena);

                                    if(c == null)
                                    {
                                        System.out.println("Error en la autenticación \n");
                                    }

                                    else

                                    {  
                                        boolean s = true;
                                        digitandodatos = false;
                                        sesion.put(c,"cumbre"); //añade la ubicación actual

                                        while(s){

                                            System.out.println("Menu Barra");
                                            System.out.println("1. Ver publicaciones \n2. Agregar Publicacion \n3. Perfil \n4. Cerrar sesion");
                                            myObj = new Scanner(System.in);
                                            int op1 = myObj.nextInt();
                                            clrscr();

                                            switch(op1)
                                            {
                                                case 1: // Lista de publicaciones (Muro)
                                                    ArrayList<Ciudadano> ciudadanos = registros.getCuidadanos();
                                                    ArrayList<Post> Muro = new ArrayList<>();
                                                    for(Ciudadano iterar: ciudadanos )
                                                    {
                                                        ArrayList<Post> publicaciones = iterar.getPost();
                                                        for (Post iterar1: publicaciones)
                                                        {   String ubicacionactual = "";
                                                            for (Ciudadano oper6 : sesion.keySet()) 
                                                            {
                                                                if(oper6.getCedula() == cedula)
                                                                {
                                                                    ubicacionactual = sesion.get(oper6);
                                                                }

                                                            }

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
                                                    System.out.println("Presione cualquier tecla para volver"); 
                                                    myObj = new Scanner(System.in);
                                                    String op4 = myObj.nextLine();                                            
                                                    clrscr();
                                                    break;
                                                case 2:// añadir post
                                                    System.out.println("Por favor agrega una  Descripcion"); 
                                                    myObj = new Scanner(System.in);
                                                    String op5 = myObj.nextLine();   

                                                    for (Ciudadano oper7 : sesion.keySet()) 
                                                    {
                                                        if(oper7.getCedula() == cedula)
                                                        {
                                                            Post oper8 = oper7.addPost(op5,sesion.get(oper7));
                                                            System.out.println("¿Desea agregar archivo multimedia? \n1. SI \n2. NO");
                                                            myObj = new Scanner(System.in);
                                                            String op9 = myObj.nextLine();  
                                                            if(op9.equals("1"))
                                                            {
                                                                System.out.println("Agrege ruta del archivo multimedia");
                                                                myObj = new Scanner(System.in);
                                                                String op10 = myObj.nextLine();
                                                                oper8.setMultimedia(op10);
                                                            }
                                                        }

                                                    }

                                                    clrscr();

                                                    break;

                                                case 3:// mostrar perfil
                                                    boolean verperfil = true;
                                                    do{
                                                        System.out.println("1. Eliminar  |  2. Modificar  | 3. Salir");
                                                        Ciudadano cp = null;
                                                        for (Ciudadano oper11 : sesion.keySet()) 
                                                        {
                                                            if(oper11.getCedula() == cedula)
                                                            {   cp = oper11;
                                                                cp.showPerfil();
                                                            }

                                                        }
                                                        myObj = new Scanner(System.in);
                                                        String op13 = myObj.nextLine();
                                                        switch(op13)
                                                        {
                                                            case "1":// eliminar post
                                                                System.out.println("Escriba el id de la publicacion que quiere borrar"); 
                                                                myObj = new Scanner(System.in);
                                                                String op14 = myObj.nextLine();
                                                                int cast = Integer.parseInt(op14);
                                                                cp.eliminarPost(cast);

                                                                break;
                                                            case "2"://modificar perfil
                                                                System.out.println("Escriba el id de la publicacion que quiere modificar"); 
                                                                myObj = new Scanner(System.in);
                                                                String op15 = myObj.nextLine();
                                                                int cast1 = Integer.parseInt(op15);

                                                                System.out.println("Desea modificar la descripción  \n 1. SI \n 2. NO");
                                                                String op16 = new Scanner(System.in).nextLine();
                                                                if(op16.equals("1")){
                                                                    System.out.println("Escriba una nueva descripción");
                                                                    op16 = new Scanner(System.in).nextLine();
                                                                    cp.getPost().get(cast1 -1).setDescripcion(op16);
                                                                }
                                                                System.out.println("Desea modificar la multimedia  \n 1. SI \n 2. NO");
                                                                op16 = new Scanner(System.in).nextLine();
                                                                if(op16.equals("1")){
                                                                    System.out.println("Escriba la ruta del archivo multimedia");
                                                                    op16 = new Scanner(System.in).nextLine();
                                                                    cp.getPost().get(cast1 -1).setMultimedia(op16);
                                                                }

                                                                break;
                                                            case "3":// retorna al menu anterior

                                                                verperfil = false;

                                                                break;

                                                        }
                                                        System.out.println("Presione cualquier tecla para volver"); 
                                                        myObj = new Scanner(System.in);
                                                        String op12 = myObj.nextLine();                                            
                                                        clrscr();
                                                    }
                                                    while(verperfil);

                                                    break;

                                                case 4: // cerrar sesion
                                                   
                                                  s = false;
                                                    


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

                    case 2:
                        break;
                    case 3:
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

                    case 4:
                        inicio = false;
                        break;
                }
            }
            catch(Exception e){
                System.out.println("Ingrese una opción valida");
            }
        }
    }
}