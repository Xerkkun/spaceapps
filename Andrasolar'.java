import java.util.*;
import java.lang.Math;
public class Andrasolar
{
	public static void main(String[] args)
        {
 /*
Programa para determinar parámetros solares.
       Variables:
    delta  : Ángulo de declinación
    h      : Hora del día
    omega  : Ángulo horario
    phi    : Latitud
    N      : Día del año
    x      : Arreglo auxiliar
    alfa   : Ángulo de elevación
    azimut : Ángulo con respecto al norte
    rise   : Hora de salida del sol
    set    : Hora de puesta del sol
    DH     : Horas de sol en el día
    t      : Corrección del tiempo en minutos
       Angulos del colector
    beta   : Ángulo con respecto al norte
    psi    : Ángulo con respecto al sur			

*/
//Declaración de variables

        double delta, h, omega, phi, N, alfa, azimut, rise, set, DH, t;
	double [][] x;
	x = new double [365][24];
        double [] y;
        y = new double [365];
        int [] theta;
        theta = new int [25];

        {
        for (int i=0; i<24; i++)
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

//Declaración de constantes *provisional* (se le tienen que pedir al usuario)

        double phi, lambda, pi, cont;
        phi=29;
        lambda=99;
        pi=3.141592;
        cont=0;

        {
        for (int h=0; h<23; h++)
                {
                for (int N=1; N<365; N++)
                x[N][h] = (2*pi/365)*(N-1+((h-12)/24));

                delta[N][h] = (0.006918-0.399912*cos(x[N][h])+0.070257*sin(x[N][h])-0.006758*cos(2*x[N][h])+0.000907*sin(2*x[N][h])-0.002697*cos(3*x[N][h])+0.001480*sin(3*x[N][h]))/pi*180;

                rise1[N] = real(acosd(((-sind(phi)*sind(delta[N][12]))-sind(-.8333))/(cosd(phi)*cosd(delta[N][12]))));
                rise2[N] = 12-(rise1[N]/15);
                set1[N] = real(acosd(((-sind(phi)*sind(delta[N][12]))-sind(-.8333))/(cosd(phi)*cosd(delta[N][12]))));
                set2[N] = 12+(set1[N]/15);
                
                y[N] = (360*(N-1))/(365.2422);

                et[N] = 12+(0.1236*sind(y[N])-0.0043*cosd(y[N]))+(0.1583*sind(2*y[N])+0.0608*cosd(2*y[N]));

                li=(phi+7)/15;

                ts[N][h] = h - et[N] - li;

                DH[N]=(real(acosd(-tand(phi)*(tand(delta[N][12])))))/7.5;
                }

        }

        {
        for (int i=0; i<24; i++)
                {
                for (int N=1; N<365; N++)
                alfa[N][i] = asind(sind(delta[N][12])*sind(phi)+cosd(delta[N][12])*cosd(theta[i])*cosd(phi));
                azimut[N][i] = real(acosd((sind(delta[N][12])*cosd(phi)-cosd(delta[N][12])*cosd(theta[i])*sind(phi))/cosd(alfa[N][i])));
                }
        }

        double [] beta;
        beta = new double [181];

        {
        for (int j=0; j<180; j++)
        beta[j] = -90+j;
        }

        E[180] = 0;

        {
        for (int k=0; k<180; k++)
                {
                for (int l=0; l<24; l++)
                        {
                        for (int N=1; N<365; N++)
                        I[N][l][k]=sind(beta[k])*(cosd(alfa[N][l])*cosd(azimut[N][l]))+sind(alfa[N][l])*cosd(beta[k]);
                        E[k] = cont+I[N][l][k];
                        }
                }
        }
        
        }

}
