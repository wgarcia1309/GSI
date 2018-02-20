
package gsi;

import Views.M_win;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;
import javax.swing.JOptionPane;

public class GSI {

    public static ArrayList<ArrayList<String>> buscar(String dato, int n) {
        ArrayList temp= new ArrayList();
        Set<BigInteger> keys = tablahs.keySet();
        if(n!=0){
            for (BigInteger  key:keys) {
                if(n>0){
                    ArrayList<String> campos=tablahs.get(key);
                    if(campos.get(n-1).compareTo(dato)==0){
                        ArrayList<String> aux= new ArrayList();
                        aux.add(key.toString());
                        for (String ci:campos) {
                            aux.add(ci);
                        }
                        temp.add(aux);
                    }
                }
            }
        }else {
            ArrayList<String> campos=tablahs.get(new BigInteger(dato));
            if(campos!=null){
                ArrayList<String> aux= new ArrayList();
                aux.add(dato);
                for (String ci:campos) {
                    aux.add(ci);
                }
                temp.add(aux);
            }
        }
        return temp;
    }
    public static String moda(int n) {
        Hashtable<String,BigInteger> tablam= new Hashtable();
        Set<BigInteger> keys = tablahs.keySet();
        BigInteger valor=new BigInteger("-1");
         String moda=null;
        if(n!=0){
            for (BigInteger  key:keys) {
                if(n>0){
                    ArrayList<String> campos=tablahs.get(key);
                    String item=campos.get(n-1);
                    if(tablam.get(item)==null){
                        tablam.put(item,BigInteger.ONE);
                    }else{
                        tablam.put(item, tablam.get(item).add(BigInteger.ONE));
                    }
                    if(valor.compareTo(tablam.get(item))<0){
                        moda=item;
                        valor=tablam.get(item);
                    }
                }
            }
            return moda;
        }else {
              return "No Aplica";
        }
    }
    private BigInteger n,c;
    private static Hashtable<BigInteger,ArrayList<String>> tablahs;
    private static ArrayList<Datos> datos;
    private static Random rn= new Random();
    private static Vector<BigInteger> vec;
    
    public static Datos getDt(int indx) {
        return datos.get(indx);
    }

    public static Vector<BigInteger> sort() {
           return vec;
    }
    
    public static void main(String[] args) {
        M_win v1= new M_win();
        v1.setLocationRelativeTo(null);
        v1.setVisible(true);
    }
    
    public GSI(String n, String c) {
        this.n=new BigInteger(n);
        this.c=new BigInteger(c);
        tablahs = new Hashtable<BigInteger,ArrayList<String>>();
        datos=new ArrayList<>();
        vec= new Vector();
        this.create();
    }
    public void create(){
        //Inicio Primer Registro
        Scanner sc= new Scanner(System.in);
        BigInteger num=BigInteger.ONE;
        String text=JOptionPane.showInputDialog(null,"Digite el tamaño de la clave ");
        //verficiar si es posible
        BigInteger tam=new BigInteger(text);
        BigInteger key=num(tam);
        vec.add(key);
        tablahs.put(key, new ArrayList());
        datos.add(new Datos(key.toString(),tam,key));
        ArrayList lista_hs=tablahs.get(key);
        BigInteger temp=BigInteger.ZERO;
        while(temp.compareTo(c)!=0){
            text=JOptionPane.showInputDialog(null,"Digite el tipo de dato del campo "+temp.add(BigInteger.ONE)+"\n0 - Numerico\n1 - Alfanumerico");
            int tipo=Integer.parseInt(text);
            text=JOptionPane.showInputDialog(null,"Digite el tamaño del campo "+temp.add(BigInteger.ONE));
            tam=new BigInteger(text);
            if(tipo==0){
                key=num(tam);
                lista_hs.add(key.toString());
                datos.add(new Datos(key.toString(),tam,key));//inicializa sum,max y min;
            }else{
                String an=word(tam);
                lista_hs.add(an);
                datos.add(new Datos(an,tam,null));//inicializa sum,max y min;
            }
            temp=temp.add(BigInteger.ONE);
        }
        //Fin Primer registro
       //Inicio Otros Registros
            num.add(BigInteger.ONE);
        while(num.compareTo(n)<0){
            //Llave
            int indx= 0;
            tam=datos.get(indx).getSz();
            key=num(tam);
            while(tablahs.containsKey(key)){
                key=num(tam);
            }
            vec.add(key);
            tablahs.put(key, new ArrayList());
            datos.get(indx).update(key);//actualiza sum,max y min si es necesario;
            lista_hs=tablahs.get(key);
            //Otros campos
            temp=BigInteger.ZERO;
            while(temp.compareTo(c)!=0){
                indx++;
                Datos aux2=datos.get(indx);
                if(aux2.getSum().compareTo(new BigInteger("-1"))!=0){//Numerico
                    key=num(aux2.getSz());
                    lista_hs.add(key.toString());
                    aux2.update(key);
                }else{//Alfanumerico
                    String an=word(aux2.getSz());
                    lista_hs.add(an);
                    aux2.update(an);
                }
                temp=temp.add(BigInteger.ONE);
            }
            num=num.add(BigInteger.ONE);
            
        }
        //Fin otros registros
        Collections.sort(vec);
    }
    public static BigInteger num(BigInteger temp) {
        String ans = "";
        while (temp.compareTo(BigInteger.ZERO)!=0) {
            ans += (rn.nextInt(10));
            temp=temp.subtract(BigInteger.ONE);
        }
        return new BigInteger(ans);
    }
    public static String word(BigInteger temp) {
        String ans = "";
        while (temp.compareTo(BigInteger.ZERO)!=0) {
            int mamn = rn.nextInt(2);
            if (mamn == 0) {
                ans += (char) (rn.nextInt(26) + 65);
            } else {
                ans += (char) (rn.nextInt(26) + 97);
            }
            temp=temp.subtract(BigInteger.ONE);
        }
        return ans;
    }
    private void show() {
        Enumeration dt = tablahs.keys();
        while(dt.hasMoreElements()) {
           BigInteger llave = (BigInteger) dt.nextElement();
           System.out.print("Key: " +llave+ " Valores: ");
           ArrayList auxL=tablahs.get(llave);
             for (int i = 0; i < auxL.size(); i++) {
                 System.out.print(auxL.get(i)+" ");
            }
             System.out.println("");
        }
        for (int i = 0; i < 2; i++) {
                    Datos aux=datos.get(i);
                  System.out.print(aux.getMin()+" "+aux.getMax()+" ");
                  if(aux.getSum().compareTo(new BigInteger("-1"))!=0)System.out.println(aux.getSum().divide(n));
                  else System.out.println("Nope");
            }
    }
    public static Hashtable<BigInteger, ArrayList<String>> getTablahs() {
        return tablahs;
    }
    
}
