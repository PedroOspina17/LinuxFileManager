/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pif3ava;
import modelo.Banco;
/**
 *
 * @author Melissa
 */
public class Pif3Ava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Banco banco = new Banco();
        
        //PUNTO 2.a: Agregar una nueva cuenta
        
        banco.agregar("Melissa", "Córdoba", 593, 1, 200000, "mcordoba@gmail.com", 1);
        banco.agregar("Natalia", "Villegas", 188, 1, 50000, "nvillegas@gmail.com", 1);
        banco.agregar("Estefania", "Villa", 711, 1, 900000, "evilla@gmail.com", 1);
        banco.agregar("Mónica", "Osorio", 895, 1, 900, "mosorio@gmail.com", 1);
        banco.agregar("Carlina", "de Villegas", 61, 1, 9, "cdevillegas@gmail.com", 1);        
        
        banco.agregar("José", "Betancur", 803, 2, 0, "jbetancur@gmail.com", 2);
        banco.agregar("Carlos", "Córdoba", 144, 2, 980000, "ccordoba@gmail.com", 2);
        banco.agregar("Liliana", "Ceballos", 331, 2, 0, "lceballos@gmail.com", 2);
        
        banco.agregar("Santiago", "Espinosa", 177, 2, 300000, "sespinosa@gmail.com", 2);
        
        
        //PUNTO 1: Imprimir información de cuentas bancarias
        banco.print();
        
        System.out.println("Creando duplicados");
        if(banco.agregar("Maria", "Rodriguez", 302, 1, 0, "mrodriguez@gmail.com", 1))
        {
            System.out.println("Cuenta creada.");
        }
        if(!banco.agregar("Mónica", "Osorio", 895, 1, 900, "mosorio@gmail.com", 1))
        {
            System.out.println("La cuenta no fue agregada, el identificador de la cuenta ya se encuentra creado.");
        }
        
        
        
        //PUNTO 2.b: Retornar el valor total de dinero almacenado en el banco 
        System.out.println();
        System.out.println("PUNTO 2.b: El valor total de dinero almacenado en el banco es:");
        System.out.print(banco.retornarTotal());
        System.out.println();
        
        //Punto 2.d. Defina un método que retorne el saldo promedio de las cuentas de ahorros.
        
        System.out.println("");
        System.out.println("Punto 2.d: Saldo promedio cuentas de ahorro");
        System.out.println(banco.promedioCuentasAhorro());
        System.out.println("");
        
        
       
        
        //Punto 2e. Defina un método que retorne en una lista todas la cuentas corrientes.
        System.out.println("");
        System.out.println("Punto 2.e: Listado cuentas corrientes");
        System.out.println(banco.listadoCuentasCorrientes().toString());
        System.out.println("");

        //Punto 2f. Defina un método que reciba una cadena de búsqueda y retorne el primer nodo que contenga esa cadena en nombre, mail o dirección. 
        //          En caso de no encontrar ningún nodo con esa condición, debe retornar null.
        
        
        System.out.println("");
        System.out.println("Punto 2.f: Buscar cadena");
        System.out.println(banco.buscarCadena("Vill").toString());
        System.out.println("");



        //Punto 2g. Defina un método que retorne el saldo promedio de las mujeres en las cuentas de ahorros.
       
        
        System.out.println("");
        System.out.println("Punto 2.g: SAldo promedio cuentas de ahorro de mujeres");
        System.out.println(banco.promedioMujeresCuentaAhorro());
        System.out.println("");
        
        
        //Punto 2h. Defina un método que reciba un código y retorne true si el código existe en el ABB o falso en caso contrario.  
        
        
        System.out.println("");
        System.out.println("Punto 2.h: Consultar cuenta");
        System.out.println(banco.existeCuenta(111) ? "La cuenta 111 existe" : "La cuenta 111 no existe");
        System.out.println(banco.existeCuenta(188) ? "La cuenta 188 existe" : "La cuenta 188 no existe");
        System.out.println("");
        
    }
    
}