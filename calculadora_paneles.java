import java.util.Scanner;

public class calculadora_paneles {
  public static void main(String[] args) {

int rango1 = 0, rango2 = 0, contador = 0;

/*
Definimos un objeto de la clase Scanner que se encargara de 
pedir los datos, si los datos se pediran desde el teclado debemos de
escribir System.in
*/
Scanner teclado = new Scanner (System.in);

System.out.println ("Escriba el primero numero del rango: ");

/*
En esta linea le decimos que capture el dato desde el teclado y que
ese dato lo transforme a un valor entero (int), eso porque la variable
es de tipo entero (int).
Si tuvieramos que guardar el valor para otro tipo de dato tenemos que
cambiar el next- por otro de acuerdo a la variable que va a guardarlo
Tenemos los siguientes:
nextLine para String
nextInt para int
nextDouble para double
*/
rango1 = teclado.nextInt();

System.out.println ("Escriba el segundo numero del rango: ");

rango2 = teclado.nextInt();

contador = rango1;

//Escribiendo los rangos de acuerdo a los ingresados desde el usuario
while(contador <= rango2 )
{
  System.out.println ("Numero: " + contador);
  contador++;
}

  }
}
 