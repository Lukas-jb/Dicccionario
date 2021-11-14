package Diccionario;

import java.io.*;
import java.nio.channels.ScatteringByteChannel;
import java.util.Scanner;
import javax.swing.*;


public class Diccionario {


    public static void main(String[] args) {

        boolean entrada = true;
        int desicion = 0;
        String error;
        Scanner sc = new Scanner(System.in);
        JOptionPane.showMessageDialog(null, "---Bienvenido al Gestor de Diccionarios---\n\n" +
                "---Seleccione el diccionario que desea utilizar---\n\n");

        //Seleccionamos el archivo .txt
        JFileChooser j = new JFileChooser();
        j.showOpenDialog(j);
        String ruta = j.getSelectedFile().getAbsolutePath();
        //buscarPalabra(ruta);
        //leerTodo(ruta);
        while (entrada) {
            System.out.println("\n\n¿Qué acción desea realizar?");
            System.out.println("ingrese la opcíon deseada");
            System.out.println("1. Buscar una palabra en el diccionario");
            System.out.println("2. Imprimir todo el contenido del diccionario");
            System.out.println("3. Ingresar una palabra nueva en el diccionario");
            System.out.println("0. terminar y cerrar el programa");

            try {
                desicion = Integer.parseInt(sc.nextLine());
                if (!desicion(desicion)) {
                    System.out.println("Valor ingresado no valido, ingrese un numero del 1 al 3");
                    continue;
                }

            } catch (Exception ex) {
                error = ex.getMessage();
                System.out.println("Caracter ingresado no valido " + error + " ,ingrese un numero del 1 al 3");
            }
            switch (desicion) {
                case 1: {
                    buscarPalabra(ruta);
                    break;
                }
                case 2: {
                    mostrarTodo(ruta);
                    break;
                }
                case 3: {
                    String pEspañol = null, pIngles = null, ingreso;
                    System.out.println("Para ingresar una palabra al diccionario siga los siguientes pasos");
                    System.out.println("\nIngrese la palabra en Español");
                    pEspañol = sc.nextLine();
                    System.out.println("Ingrese la palabra en Ingles");
                    pIngles = sc.nextLine();
                    ingreso = pEspañol + " = " + pIngles;
                    if (ingPalabra(ruta, ingreso)) {
                        System.out.println("\nIngreso realizado con exito");
                        System.out.println("___________________________________________");
                    } else
                        System.out.println("\nAlgo ha fallado, intente de nuevo");
                    break;
                }
                case 0: {
                    System.out.println("\n-------------------Hasta pronto-------------------");
                    entrada = false;
                    break;
                }

            }

        }

    }

    //metodos
    public static void buscarPalabra(String ruta) {

        String texto, error;
        Scanner entrada = null;
        String linea;
        Scanner sc = new Scanner(System.in);
        int numeroLinea = 1;
        boolean contiene = false;

        //Introducimos palabra a buscar
        System.out.println("Ingrese la palabra que desea buscar");
        texto = sc.nextLine();

        try {

            //guardamos ruta del fichero
            File f = new File(ruta);                              //creamos un objeto relacionado al fichero
            entrada = new Scanner(f);                            //para leer el archivo

            System.out.println("El texto buscado es: " + texto);

            while (entrada.hasNext()) {                          //Recorre hasta el final del archivo
                linea = entrada.nextLine();                     //lee la linea
                if (linea.contains(texto)) {                   //si contiene el texto se imprime
                    System.out.println("Español-------Ingles");
                    System.out.println(linea);
                    contiene = true;
                }
                numeroLinea++;
                break;
            }

            if (!contiene) {
                System.out.println("No se ha encontrado el texto");
            }

        } catch (Exception e) {
            error = e.getMessage();
            System.out.println(error);
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }

    }

    public static void mostrarTodo(String ruta) {
        Scanner entrada = null;
        String linea;
        String texto;
        try {
            File f = new File(ruta);
            entrada = new Scanner(f);
            System.out.println("Español-------Ingles");

            while (entrada.hasNext()) {
                linea = entrada.nextLine();
                System.out.println(linea);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.toString() + "No ha seleccionado ningún archivo");
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    static boolean desicion(int desicion) {
        if (desicion > 3 || desicion < 0)
            return false;
        return true;

    }

    static boolean ingPalabra(String ruta, String ingreso) {
        Scanner entrada = null;
        String linea;
        FileWriter escribir;
        PrintWriter escribirLinea;

        try {
            escribir = new FileWriter(ruta, true);      //permite ingresar informacion al final del txt
            escribirLinea = new PrintWriter(escribir);
            escribirLinea.println(ingreso);
            escribirLinea.close();
            escribir.close();
            return true;

        } catch (IOException e) {
            System.out.println(e.toString() + "No ha seleccionado ningún archivo");
            return false;
        }
    }
}

