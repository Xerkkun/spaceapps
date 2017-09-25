import java.util.*;
import java.lang.Math;
public class Andrasolar-
{
	public static void main(String[] args)
        {
 /*
Programa para determinar parámetros solares.
       Variables:
    delta  : Ángulo de declinación, vector
    h      : Hora del día, vector
    phi    : Latitud, flotante
    hh	   : Huso Horario, flotante
    N      : Día del año, vector
    x      : Arreglo auxiliar, vector
    alfa   : Ángulo de elevación, vector
    azimut : Ángulo con respecto al norte, vector
    rise   : Hora de salida del sol, vector
    set    : Hora de puesta del sol, vector
    DH     : Horas de sol en el día, vector
    ts     : Corrección del tiempo en minutos, vector
    E	   : Le energy, vector.
    theta  : Horas angulares del día, vector
       Angulos del colector
    beta   : Ángulo con respecto al norte, vector
*/
//Declaración de variables

        double [][] delta = new double [365][24];
	int h, N, i, j, k, l;
	double [][] x = new double [365][24];
        double [] y= new double [365];
        int [] theta = new int [25];
	double [][] alfa = new double [365][25];
	double [][] azimut = new double [365][25];
	double [] rise1 = new double [365];
	double [] rise2 = new double [365];
	double [] set1 = new double [365];
	double [] set2 = new double [365];
        double [] et = new double [365];
	double [] DH = new double [365];
	double [][] ts = new double[365][24];
	double [] E = new double[181];
	double [][] I = new double[365][25];
        double [] beta = new double [181];
	double phi, hh, li, cont, pi;

        {
        for (i=0; i<25; i++)
        theta[i] = -180+15*i;
        }

        int [] mp;
        mp = new int [12];
        
        mp[0] = 0;
	mp[1] = 31;
	mp[2] = 59;
	mp[3] = 90;
	mp[4] = 120;
	mp[5] = 151;
	mp[6] = 181;
	mp[7] = 212;
	mp[8] = 243;
	mp[9] = 273;
	mp[10] = 304;
	mp[11] = 334;

//Declaración de constantes *provisional* (se le tienen que pedir al usuario

        phi=Math.toRadians(29);
        hh=7;
        pi=3.141592;
        cont=0;

	li=(phi+hh)/15;

        {
	for (N=0; N<364; N++)
	
                {
                for (h=0; h<23; h++)
                x[N][h] = (2*pi/365)*(N-1+((h-12)/24));

                delta[N][h] = (0.006918-0.399912*Math.cos(x[N][h])+0.070257*Math.sin(x[N][h])-0.006758*Math.cos(2*x[N][h])+0.000907*Math.sin(2*x[N][h])-0.002697*Math.cos(3*x[N][h])+0.001480*Math.sin(3*x[N][h]))/pi*180;

                y[N] = Math.toRadians((360*(N-1))/(365.2422));

	        et[N] = 12+(0.1236*Math.sin(y[N])-0.0043*Math.cos(y[N]))+(0.1583*Math.sin(2*y[N])+0.0608*Math.cos(2*y[N]));

                ts[N][h] = h-et[N]-li;

                }
	
	DH[N]=(Math.acos(-Math.tan(phi)*(Math.tan(delta[N][12]))))/7.5;
	
	rise1[N] = Math.acos(((-Math.sin(phi)*Math.sin(delta[N][12]))-Math.sin(-.8333))/(Math.cos(phi)*Math.cos(delta[N][12])));
        rise2[N] = 12-(rise1[N]/15);
        set1[N] = Math.acos(((-Math.sin(phi)*Math.sin(delta[N][12]))-Math.sin(-.8333))/(Math.cos(phi)*Math.cos(delta[N][12])));
        set2[N] = 12+(set1[N]/15);

        }
        
        {
        for (i=0; i<24; i++)
                {
                for (N=0; N<364; N++)
                alfa[N][i] = Math.asin(Math.sin(delta[N][12])*Math.sin(phi)+Math.cos(delta[N][12])*Math.cos(theta[i])*Math.cos(phi));
                azimut[N][i] = Math.acos((Math.sin(delta[N][12])*Math.cos(phi)-Math.cos(delta[N][12])*Math.cos(theta[i])*Math.sin(phi))/Math.cos(alfa[N][i]));
                }
         
        }

        {
	for (N=0; N<364; N++)
                {
                for (h=0; h<23; h++)
                        System.out.println(delta[N][h]);
                }
        }

        {
        for (j=0; j<180; j++)
        beta[j] = -90+j;
        beta[j] = Math.toRadians(beta[j]);
        }


        {
        for (k=0; k<180; k++)
                {
                for (l=0; l<24; l++)
                        {
                        for (N=0; N<364; N++)
                        I[N][l]=Math.sin(beta[k])*(Math.cos(alfa[N][l])*Math.cos(azimut[N][l]))+Math.sin(alfa[N][l])*Math.cos(beta[k]);
                        cont = cont+I[N][l];
                        }
                
                }
                E[k]=cont;
        }

        /*{
	for(i=0;i<180;i++)		
	System.out.println(beta[i]%180+" ");         
	}     

        {
        for(i=0;i<180;i++)		
	System.out.println(E[i]);
	}*/
        
        }

}
