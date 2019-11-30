/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author pedro.ospina
 */
public class NodoBinario {
    private NodoBinario hijoIzquierdo;
    private NodoBinario hijoDerecho;
    private CuentaBancaria cuenta;
    
    public NodoBinario( CuentaBancaria cuenta)
    {
        this.cuenta = cuenta;
    }
    
    public NodoBinario getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(NodoBinario hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public NodoBinario getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(NodoBinario hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public CuentaBancaria getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }
    
    
    
    public boolean esHoja(){
        return hijoIzquierdo==null && hijoDerecho==null;
    }
    
    public boolean tieneHijoIzquierdo()
    {
        return hijoIzquierdo != null;
    }
    
    public boolean tieneHijoDerecho()
    {
        return hijoDerecho != null;
    }
    
    
}
