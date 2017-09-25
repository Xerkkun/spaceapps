import java.util.*;
import java.lang.Math;
public class Ferny
{
	public static void main(String[] args)
	{
 /*
Variables:

	lamda			:Distancia del Sol
	Ms				:Anomalía promedio del Sol
	l				:Longitud media de la Luna
	Mm				:Anomalía promedio de la Luna
	N				:Nodo de ascensión
	D				:Número de día
	Ev				:Evección
	Ae				:Ecuación anual
	Atres			:Tercera corrección
	Mmm			:Más correcciones
	Ec				:Ecuación del centro
	Acuatro			:Otra corrección
	ll				:Longitud corregida
	v				:Variación, corrección final aplicada
	lll				:Longitud real
	lamdam			:latitud elíptica
	betam			:longitud
	NN				:longitud corregida del nodo
	omega			:vector de días				

*/
//Declaración de variables
	double lamda,Ms,l,Mm,N,Ev,Ae,Atres,Mmm,Ec,Ecc,Acuatro,ll,v,lll,lamdam,betam,NN,h,k,x,y,D,delta,xx,yy,alpha;
	int[] omega;
	omega=new int[12];
	omega[0]=0;
	omega[1]=31;
	omega[2]=59;
	omega[3]=90;
	omega[4]=120;
	omega[5]=151;
	omega[6]=181;
	omega[7]=212;
	omega[8]=243;
	omega[9]=273;
	omega[10]=304;
	omega[11]=334;
//Declaración de constantes	
	double l0,P0,N0,i,e,a,theta0,pi0,epsilong,omegag,epsilon;
	l0=91.929336;
	P0=130.143076;
	N0=291.682547;
	i=Math.toRadians(5.145396);
	e=0.0549;
	a=384401;  //km
	theta0=0.5151;
	pi0=0.9507;
	epsilong=279.557208;
	omegag=283.112438;
	epsilon=Math.toRadians(23.438055);
//Obtener fecha y hora actuales
//Instanciamos el objeto Calendar
        //en fecha obtenemos la fecha y hora del sistema
        Calendar fecha = new GregorianCalendar();
        //Obtenemos el valor del año, mes, día,
        //hora, minuto y segundo del sistema
        //usando el método get y el parámetro correspondiente
        int year = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        System.out.println("Fecha Actual: "
                           + dia + "/" + (mes) + "/" + year);
        System.out.printf("Hora Actual: %02d:%02d:%02d %n",
                                              hora, minuto, segundo);
	D=365.25*(year-2010)+omega[mes]+dia+.5;
	//Consideración por años bisiestos
	if (year%4==0);
	{
		D=D+1;
	}
	h=(360*((D-1)*24)+hora)/8766;
	if (h>360) {
		while (true) {
		h=h-360;
			if (h<360) {break;}
		}
	}
	Ms= h + epsilong - omegag;
	Ecc=(360/Math.PI)*e*Math.toDegrees(Math.sin(Math.toRadians(Ms)));
	lamda=h+Ecc+epsilong;
	if (lamda>360) {
		while (true) {
		lamda=lamda-360;
			if (lamda<360) {break;}
		}
	}
	l=13.1763966 * D + l0;
	if (l>360){
		while (true) {
		l=l-360;
			if (l<360) {break;}
		}
	}
	Mm= l - (0.1114041 * D) - P0;
		if (Mm>360){
		while (true) {
		Mm=Mm-360;
			if (Mm<360) {break;}
		}
	}
	N= N0 - (0.0529539 * D);
		if (N>360){
		while (true) {
		N=N-360;
			if (N<360) {break;}
		}
	}
	Ev= 1.2739 * Math.toDegrees(Math.sin(Math.toRadians(2*(l-lamda) - Mm)));
	Ae= 0.1858 * Math.toDegrees(Math.sin(Math.toRadians(Ms)));
	Atres= 0.37 * Math.toDegrees(Math.sin(Math.toRadians(Ms)));
	Mmm= Mm + Ev - Ae - Atres;
	Ec= 6.2886 * Math.toDegrees(Math.sin(Math.toRadians(Mmm)));
	Acuatro= 0.214 * Math.toDegrees(Math.sin(Math.toRadians(2*Mmm)));
	ll= l + Ev + Ec - Ae + Acuatro;
	v= 0.06583*Math.toDegrees(Math.sin(Math.toRadians(2*(ll-lamda))));
	lll= ll + v;
	NN= N - (0.16*Math.toDegrees(Math.sin(Math.toRadians(Ms))));
	y=Math.sin(lll-NN)*Math.cos(i);
	x=Math.cos(Math.toRadians(lll-NN));
	k=Math.atan(y/x);
	if(x<0){
	k=k+Math.PI;
	}
	if(y<0&&x>0){
	k=k+(2*Math.PI);
	}
	lamdam=k+Math.toRadians(NN);
	betam=Math.asin(Math.sin(Math.toRadians(lll-NN))*Math.sin(i));
	delta=Math.toDegrees(Math.asin((Math.sin(betam)*Math.cos(epsilon))+Math.cos(betam)*Math.sin(epsilon)*Math.sin(lamdam)));
	yy=Math.sin(lamdam)*Math.sin(epsilon)-Math.tan(betam)*Math.sin(epsilon);
	xx=Math.cos(lamdam);
	alpha=Math.toDegrees(Math.atan(yy/xx));
	if(xx<0){
	alpha=alpha+180;
	}
	if(yy<0&&xx>0){
	alpha=alpha+360;
	}
	//conversiones
	int hor = (int) alpha/15;
	double alpha_dec = alpha/15 - hor;
	int min = (int) ((alpha_dec)*60);
	double min_dec = (alpha_dec*60) - min;
	int seg = (int) (min_dec*60);
	int grad = (int) delta;
	double delta_dec = delta - grad;
	if(grad<0) {
	delta_dec = delta_dec*-(1);
	}
	int mins = (int) ((delta_dec)*60);
	double mins_dec = (delta_dec*60) - mins;
	int segs = (int) (mins_dec*60);
	System.out.println("alpha= " + alpha/15);
	System.out.println("delta= " + delta);
	System.out.println("altitud= "+ hor + "h " + min + "m " + seg + "s " );
	System.out.println("declinacion= "+ grad + "°" + mins + "'" + segs + "''" );
	}
}
