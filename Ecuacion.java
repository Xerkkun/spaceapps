package progra_2;/* <---BORRA ESTO */
import java.util.*;
import java.lang.Math;
/**
 *
 * @author Feliponchon ME LA PELAS LUIS
 */
public class Ecuacion{
    public static void main(String[] args) {
      int dia,mes,año;
      double D,ls,e,Eg,Wg,D2,Ms,Po,Ev,Ae,A3,Ec,A4,V,y,x,k,lm,beta,i;
      i=Math.toRadians(5.145396);
      Po=Math.toRadians(130.143076);
      e=0.0549;
      Eg=Math.toRadians(279.557208);
      Wg=Math.toRadians(283.112438);      
      int omega[] = new int[]{0,31,59,90,120,151,181,212,243,273,304,334};
      double L[] =new double[] {Math.toRadians(91.929336),0,0};
      double Mm[] =new double[2];
      double N[] =new double[]{Math.toRadians(291.682547),0};
      Scanner teclado = new Scanner(System.in);
      System.out.println("DIA:");
      dia=teclado.nextInt();
      System.out.println("MES:");
      mes=teclado.nextInt();
      System.out.println("AÑO:");
      año=teclado.nextInt();
      D=365.25*(año-2010)+omega[mes]+dia;
      if (D % 4 == 0) {
          D=D+1;
        }
      D2=Math.toRadians(360/365*D);
      Ms=D2+Eg-Wg;
      ls=D2+(2*e*Math.sin(Ms))+Eg;
      L[0]=Math.toRadians(13.1763966*D)+L[0];
      Mm[0]=L[0]-Math.toRadians(0.114041*D)-Po;
      N[0]=N[0]-Math.toRadians(0.0529539*D);
      Ev=1.2739*Math.sin(2*(L[0]-ls)-Mm[0]);
      Ae=0.1858*Math.sin(Ms);
      A3=0.37*Math.sin(Ms);
      Mm[1]=Mm[0]+Ev-Ae-A3;
      Ec=6.2886*Math.sin(Mm[1]);
      A4=0.214*Math.sin(2*Mm[1]);
      L[1]=L[0]+Ev+Ec-Ae+A4;
      V=0.06583*Math.sin(2*(L[1]-ls));
      L[2]=L[1]+V;
      N[1]=N[0]-0.16*Math.sin(Ms);
      y=Math.sin(L[2]-N[1])*Math.cos(i);
      x=Math.cos(L[2]-N[1]);
      k=Math.atan(y/x);
      if (x<0) {
          k=k+Math.PI;
      }
      if(y<0||x>0) {
          k=k+(2*Math.PI);
      }
      lm=k+N[1];
      beta=Math.asin(y*Math.cos(i));
      lm=Math.toDegrees(lm);
      beta=Math.toDegrees(beta);
      System.out.println("lm= "+lm);
      System.out.println("ßm= "+beta);
    }
}
