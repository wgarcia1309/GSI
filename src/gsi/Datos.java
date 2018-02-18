/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsi;

import java.math.BigInteger;

/**
 *
 * @author PC
 */
public class Datos {
    private String max,min;
    private BigInteger sz,sum=new BigInteger("-1");

    public Datos(String data, BigInteger sz,BigInteger prom) {
            this.max = data;
            this.min = data;
            this.sz=sz;
            if(prom!=null)this.sum = prom;
    }
    public void update(String s){
        if(s.compareToIgnoreCase(max)>0)max=s;
        if(s.compareToIgnoreCase(min)<0)min=s;
    }
    
    public void update(BigInteger s){
        if(s.compareTo(new BigInteger(max))>0)max=s.toString();
        if(s.compareTo(new BigInteger(min))<0)min=s.toString();
        sum=sum.add(s);
    }

    public String getMax() {
        return max;
    }

    public String getMin() {
        return min;
    }
    
    public BigInteger getSz() {
        return sz;
    }
    
    public BigInteger getSum() {
        return sum;
    }
    
    
}
