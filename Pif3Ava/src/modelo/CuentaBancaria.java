/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Melissa
 */
public class CuentaBancaria {
    
    
    private String nombre;
    private String apellido;
    private int codigoNumerico;
    private int genero;
    private double saldo; 
    private String email;
    private int tipoCuenta;
    
    //Constructor
    public CuentaBancaria(String nom,String ape,int codNum,int gen,double sal,String ema,int tipCue)
    {        
        this.nombre = nom;
        this.apellido=ape;
        this.codigoNumerico=codNum;
        this.genero=gen;
        this.saldo=sal;
        this.email=ema;
        this.tipoCuenta=tipCue;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCodigoNumerico() {
        return codigoNumerico;
    }

    public void setCodigoNumerico(int codigoNumerico) {
        this.codigoNumerico = codigoNumerico;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    public boolean esHombre() {
        return this.genero == 2;
    }
    
    public boolean esCuentaCorriente() {
        return this.tipoCuenta == 2;
    }
    
    //PUNTO 1: Escribir el método toString
    @Override
    public String toString() {
        String cadena = "";
        String gen = "";
        String tipCue="";
        
        switch(genero)
        {
            case 1: gen="Femenino"; break;
            case 2: gen="Masculino"; break;
        }
        
        switch(tipoCuenta)
        {
            case 1: tipCue="Ahorros";break;
            case 2: tipCue="Corriente";break;
        }
        cadena="Nombre completo: "+nombre+" "+apellido
                + "\n Género: "+gen
                + "\n Email: "+email
                + "\n Tipo de cuenta: "+tipCue
                + "\n Número:"+codigoNumerico
                + "\n Saldo: $ "+saldo;
        return cadena;
    }
    
    //PUNTO 1: Método que permita hacer consignaciones     
    public String consignarDinero(double valor)
    {
        saldo=saldo+valor;
        return "La consignación ha sido realizada.";
    }
    
    //PUNTO 1: Método que permita retirar dinero
    public String retirarDinero(double valor)
    {
        if(valor <= saldo)
        {
            saldo=saldo - valor;
            return "El retiro ha sido realizado.";
        }
        else 
        {
            return "Lamentablemente no cuenta con fondos para realizar el retiro.";
        }
    }
}
