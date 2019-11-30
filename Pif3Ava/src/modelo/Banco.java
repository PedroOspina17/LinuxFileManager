/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Melissa
 */

public class Banco {
    
    private NodoBinario raiz;
    
    public Banco()
    {
        raiz=null;
    }

    public NodoBinario getRaiz() {
        return raiz;
    }
    
    
    //PUNTO 2.a: Agregar una nueva cuenta
   public boolean agregar(String nom,String ape,int codNum,int gen,double sal,String ema,int tipCue) {
        CuentaBancaria cuenta = new CuentaBancaria(nom,ape,codNum,gen,sal,ema,tipCue);
        return agregar(cuenta);
   }
    
    public boolean agregar(CuentaBancaria cuenta) {
        if (raiz != null) {
            NodoBinario actual = raiz;
            
            while (actual != null) {
                if (cuenta.getCodigoNumerico() < actual.getCuenta().getCodigoNumerico()) {
                    if (!actual.tieneHijoIzquierdo()) {
                        actual.setHijoIzquierdo(new NodoBinario(cuenta));
                        return true;
                    }
                    actual = actual.getHijoIzquierdo();
                } else if (cuenta.getCodigoNumerico() > actual.getCuenta().getCodigoNumerico()) {
                    if (!actual.tieneHijoDerecho()) {
                        actual.setHijoDerecho(new NodoBinario(cuenta));
                        return true;
                    }
                    actual = actual.getHijoDerecho();
                } else {
                    actual = null;
                }
            }
            return false;
        } else {
            raiz = new NodoBinario(cuenta);
            return true;
        }
    }
    
    
    public void print() {
        print(raiz);
    }
    
    private void print(NodoBinario r) {
        if (r != null) {
            print(r.getHijoIzquierdo());            
            System.out.println("\n" + r.getCuenta().toString());
            print(r.getHijoDerecho());
        }
    }
    
    //PUNTO 2.b: Retornar el valor total de dinero almacenado en el banco 
    public double retornarTotal()
    {
        return retornarTotal(raiz);
    }
    
    public double retornarTotal(NodoBinario r)
    {
        if(r!=null)
        {
            return r.getCuenta().getSaldo() + retornarTotal(r.getHijoIzquierdo())+retornarTotal(r.getHijoDerecho());
        }
        return 0;
    }
    
    
    private double promedioCuentas()
    {
        return retornarTotal() / contarNodos();
    }
    
    public int contarNodos() {
        return contarNodos(raiz,0);
    }
    private int contarNodos(NodoBinario r, int nNodos) {
        if (r != null) {

            nNodos++;
            nNodos = contarNodos(r.getHijoDerecho(), nNodos);
            nNodos = contarNodos(r.getHijoIzquierdo(), nNodos);

        }
        return nNodos;
    }
    
        
    
    // Punto 2 - d
    public double  promedioCuentasAhorro() {
        return filtrarTipoCuenta(raiz , new Banco(), false).promedioCuentas();
    }
    
    // Punto 2 - e    
    public Banco listadoCuentasCorrientes()
    {
        return filtrarTipoCuenta(raiz , new Banco(), true);
    }
    
    // Punto 2 - f    
    public NodoBinario buscarCadena(String cadena) {
        return buscarCadena(raiz, cadena);
        
    }
    private NodoBinario buscarCadena(NodoBinario r, String cadena) {
        NodoBinario retorno = null;
        if (r != null) {
            
            CuentaBancaria cuenta = r.getCuenta();
            if(cuenta.getNombre().contains(cadena) || cuenta.getApellido().contains(cadena) || cuenta.getEmail().contains(cadena))        
                retorno = r;            
            else 
                retorno = null;
                       
            if(retorno != null)
            {
                retorno = buscarCadena(r.getHijoIzquierdo(), cadena);
            }
            if(retorno != null)
            {
                retorno = buscarCadena(r.getHijoDerecho(), cadena);
            }
            

        }
        return retorno;
    }
    
    // Punto 2 - g
    public double promedioMujeresCuentaAhorro() {
        Banco cuentasCorrientes = filtrarTipoCuenta(raiz, new Banco(),false);
        return filtrarGenero(cuentasCorrientes.getRaiz() ,new Banco(), false).promedioCuentas();
    }      
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
    private Banco filtrarTipoCuenta(NodoBinario r, Banco subArbol, boolean filtrarCorriente) {
        if (r != null) {
            CuentaBancaria cuenta = r.getCuenta();
            if(!cuenta.esCuentaCorriente() == filtrarCorriente)
            {
                subArbol.agregar(cuenta);
            }
            else{
                subArbol.agregar(cuenta);
            }
            subArbol = filtrarTipoCuenta(r.getHijoDerecho(),subArbol, filtrarCorriente);
            subArbol = filtrarTipoCuenta(r.getHijoIzquierdo(),subArbol, filtrarCorriente);
            
                   
        }
        return subArbol;
    }
    
    private Banco filtrarGenero(NodoBinario r, Banco subArbol, boolean filtrarHombre) {
        if (r != null) {
            CuentaBancaria cuenta = r.getCuenta();
            if(!cuenta.esHombre() == filtrarHombre)
            {
                subArbol.agregar(cuenta);
            }
            else{
                subArbol.agregar(cuenta);
            }
            subArbol = filtrarGenero(r.getHijoDerecho(),subArbol, filtrarHombre);
            subArbol = filtrarGenero(r.getHijoIzquierdo(),subArbol, filtrarHombre);                               
        }
        return subArbol;
    }
    
    // Punto 2 - h
    public boolean existeCuenta(int numero) {
        return existeCuenta(raiz, numero);
        
    }
    private boolean existeCuenta(NodoBinario r, int numero) {
        boolean retorno = false;
        if (r != null) {
            
            CuentaBancaria cuenta = r.getCuenta();
            if(r.getCuenta().getCodigoNumerico() == numero)        
                retorno = true;            
            else 
                retorno = false;
                       
            if(!retorno)
            {
                retorno = existeCuenta(r.getHijoIzquierdo(), numero);
            }
            if(!retorno)
            {
                retorno = existeCuenta(r.getHijoDerecho(), numero);
            }
            

        }
        return retorno;
    }
    
                
}
