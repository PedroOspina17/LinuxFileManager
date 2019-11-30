/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 * Pedro Nel Ospina Graciano
 * Melissa Córdoba Molina
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
    
    //PUNTO 1: Método que permita hacer consignaciones  
    public String consignarDinero(int codigoNumerico,double valor)
    {
        String resultado="No fue posible realizar la consignación.";
        NodoBinario cuentaConsignacion = obtenerCuenta(raiz,codigoNumerico,false);
        if(cuentaConsignacion != null)
        {
            return cuentaConsignacion.getCuenta().consignarDinero(valor);
        }
        return resultado;
    }
    
    
    //PUNTO 1: Método que permita retirar dinero
    public String retirarDinero(int codigoNumerico,double valor)
    {
        String resultado="No fue posible realizar la consignación.";
        NodoBinario cuentaRetiro = obtenerCuenta(raiz,codigoNumerico,false);
        if(cuentaRetiro != null)
        {
            return cuentaRetiro.getCuenta().retirarDinero(valor);
        }
        return resultado;
    }
    
    public void print() {
        print(raiz);
    }
    
    private void print(NodoBinario r) {
        if (r != null) {
            System.out.println("\n" + r.getCuenta().toString());
            print(r.getHijoIzquierdo());            
            print(r.getHijoDerecho());
        }
    }
    
    public void mostrarArbol() {
        mostrarArbol(raiz, "");
    }
    
    private void mostrarArbol(NodoBinario r, String espacios) {
        if (r != null) {
            mostrarArbol(r.getHijoDerecho(), espacios + "      ");
            System.out.println(espacios + r.getCuenta().getCodigoNumerico());
            mostrarArbol(r.getHijoIzquierdo(), espacios + "      ");
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
        return filtrarCuentasAhorros(raiz , new Banco()).promedioCuentas();
    }
    
    // Punto 2 - e    
    public Banco listadoCuentasCorrientes()
    {
        return filtrarCuentasCorrientes(raiz , new Banco());
    }
    public Banco listadoCuentasAhorros()
    {
        return filtrarCuentasAhorros(raiz , new Banco());
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
                       
            if(retorno == null)
            {
                retorno = buscarCadena(r.getHijoIzquierdo(), cadena);
            }
            if(retorno == null)
            {
                retorno = buscarCadena(r.getHijoDerecho(), cadena);
            }
            

        }
        return retorno;
    }
    
    // Punto 2 - g
    public double promedioMujeresCuentaAhorro() {
        Banco cuentasCorrientes = filtrarCuentasAhorros(raiz, new Banco());
        return filtrarGenero(cuentasCorrientes.getRaiz() ,new Banco(), false).promedioCuentas();
    }      
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
    private Banco filtrarCuentasCorrientes(NodoBinario r, Banco subArbol) {
        if (r != null) {
            CuentaBancaria cuenta = r.getCuenta();
            if(cuenta.esCuentaCorriente())
            {
                subArbol.agregar(cuenta);
            }
            subArbol = filtrarCuentasCorrientes(r.getHijoDerecho(), subArbol);
            subArbol = filtrarCuentasCorrientes(r.getHijoIzquierdo(),subArbol);
            
                   
        }
        return subArbol;
    }
    
    private Banco filtrarCuentasAhorros(NodoBinario r, Banco subArbol) {
        if (r != null) {
            CuentaBancaria cuenta = r.getCuenta();
            if(cuenta.esCuentaCorriente() == false)
            {
                subArbol.agregar(cuenta);
            }
            subArbol = filtrarCuentasAhorros(r.getHijoDerecho(), subArbol);
            subArbol = filtrarCuentasAhorros(r.getHijoIzquierdo(),subArbol);
            
                   
        }
        return subArbol;
    }
    
    private Banco filtrarGenero(NodoBinario r, Banco subArbol, boolean filtrarHombre) {
        if (r != null) {
            CuentaBancaria cuenta = r.getCuenta();
            if(cuenta.esHombre() == filtrarHombre)
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
        return obtenerCuenta(raiz, numero, false) != null;
        
    }
    
    private void concatenarArbol(NodoBinario r) {
        if (r != null) {
            this.agregar(r.getCuenta());
            concatenarArbol(r.getHijoIzquierdo());    
            concatenarArbol(r.getHijoDerecho());
        }
    }
    
    public boolean eliminarCuenta(int numero)
    {
        if(raiz==null)
        {
            return false;
        }
        else
        {
            NodoBinario nodoEliminar;
            if(raiz.getCuenta().getCodigoNumerico()==numero)
            {
                nodoEliminar=raiz;
                if(raiz.tieneHijoIzquierdo())
                {
                    raiz=raiz.getHijoIzquierdo();
                }
                else if(raiz.tieneHijoDerecho())
                {
                    raiz=raiz.getHijoDerecho();
                }
                else
                {
                    raiz=null;
                    return true;
                }
            }
            else
            {
                nodoEliminar = obtenerCuenta(raiz,numero,true);
            }

            if(nodoEliminar!=null)
            {
                concatenarArbol(nodoEliminar.getHijoIzquierdo());
                concatenarArbol(nodoEliminar.getHijoDerecho());
            }
            return nodoEliminar!=null;
        }
        

    }
    
    private boolean nodoEliminado = false;
    private NodoBinario obtenerCuenta(NodoBinario r, int numero, boolean borrarReferencia ) {
        NodoBinario retorno = null;
        if (r != null) {
            
            CuentaBancaria cuenta = r.getCuenta();
            if(r.getCuenta().getCodigoNumerico() == numero)        
                retorno = r;
                       
            if(retorno == null)
            {
                retorno = obtenerCuenta(r.getHijoIzquierdo(), numero, borrarReferencia);             
                if(retorno != null ){
                    if(borrarReferencia && !this.nodoEliminado)
                    {
                        r.setHijoIzquierdo(null);
                        this.nodoEliminado = true;
                    }  
                }else
                {
                    retorno = obtenerCuenta(r.getHijoDerecho(), numero,borrarReferencia);
                    if(retorno != null ){
                        if(borrarReferencia && !this.nodoEliminado)
                        {
                            r.setHijoDerecho(null);
                            this.nodoEliminado = true;
                        }
                    }
                }
            }                        
            

        }
        return retorno;
    }
    
                
}
