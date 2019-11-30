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
        //Sumatoria = 1.150.909 - Promedio ctas ahorro = 230181
        banco.agregar("Melissa", "Córdoba", 593, 1, 200000, "mcordoba@gmail.com", 1);
        banco.agregar("Natalia", "Villegas", 188, 1, 50000, "nvillegas@gmail.com", 1);
        banco.agregar("Estefania", "Villa", 711, 1, 900000, "evilla@gmail.com", 1);
        banco.agregar("Mónica", "Osorio", 895, 2, 900, "mosorio@gmail.com", 1);
        banco.agregar("Carlina", "de Villegas", 61, 2, 9, "cdevillegas@gmail.com", 1);        
        
        
        // Sumatoria = 1.280.000. Promedio = 320.000
        banco.agregar("José", "Betancur", 803, 1, 0, "jbetancur@gmail.com", 2);
        banco.agregar("Carlos", "Córdoba", 144, 1, 980000, "ccordoba@gmail.com", 2);
        banco.agregar("Liliana", "Ceballos", 331, 2, 0, "lceballos@gmail.com", 2);
        
        
        banco.agregar("Santiago", "Espinosa", 177, 2, 300000, "sespinosa@gmail.com", 2);
        
        
        //PUNTO 1: Imprimir información de cuentas bancarias
        System.out.println("------- Listado de cuentas ------------");
        banco.print();        
        System.out.println("---------------------------------------");
        System.out.println("");
        
        
         System.out.println("------- Realizar consignación de $20.000 a la cuenta 593 ------------");
        System.out.println(banco.consignarDinero(593, 20000));
        banco.print();
        System.out.println("---------------------------------------");
        System.out.println("");
        
        
        System.out.println("------- Realizar retiro de $900.000 a la cuenta 803 ------------");
        System.out.println(banco.retirarDinero(803, 900000));
        System.out.println("---------------------------------------");
        System.out.println("");
        
        
        System.out.println("------- Realizar retiro de $1000 a la cuenta 593 ------------");
        System.out.println(banco.retirarDinero(593, 1000));
        banco.print();
        System.out.println("---------------------------------------");
        System.out.println("");
        

        
        //PUNTO 2.b: Retornar el valor total de dinero almacenado en el banco 
        System.out.println();
        System.out.println("PUNTO 2.b: El valor total de dinero almacenado en el banco es:");
        System.out.print(banco.retornarTotal());
        System.out.println();
        
        System.out.println("---------------------------------------");
        System.out.println("");
        
        
        //PUNTO 2.c: Eliminar cuenta 
        System.out.println();
        System.out.println("PUNTO 2.c: Eliminar cuenta");
        System.out.print("El árbol antes de eliminar era:");
        System.out.println();
        System.out.println();
        banco.mostrarArbol();
        System.out.println();
        System.out.print("Al eliminar 188, el árbol queda así:");
        System.out.println();
        System.out.println();
        banco.eliminarCuenta(593);
        banco.mostrarArbol();
        
        System.out.println("---------------------------------------");
        System.out.println("");
        
        System.out.println("-------- Creando duplicados ------------");
        System.out.println("La cuenta 302 " + (banco.agregar("Maria", "Rodriguez", 302, 1, 0, "mrodriguez@gmail.com", 1) ? "" : "no") +" fue creada.");
        System.out.println("La cuenta 188 " + (banco.agregar("Maria", "Rodriguez", 188, 1, 0, "mrodriguez@gmail.com", 1) ? "" : "no") +" fue creada.");
        

        System.out.println("---------------------------------------");
        System.out.println("");
        
//Punto 2.d. Defina un método que retorne el saldo promedio de las cuentas de ahorros.
        
        System.out.println("");
        System.out.println("Punto 2.d: Saldo promedio cuentas de ahorro");
        banco.listadoCuentasAhorros().print();
        System.out.println(banco.promedioCuentasAhorro());
        System.out.println("");
        
        System.out.println("---------------------------------------");
        System.out.println("");
        
       
        System.out.println("---------------------------------------");
        System.out.println("");
        //Punto 2e. Defina un método que retorne en una lista todas la cuentas corrientes.
        System.out.println("");
        System.out.println("Punto 2.e: Listado cuentas corrientes");
        banco.listadoCuentasCorrientes().print();
        System.out.println("");

        //Punto 2f. Defina un método que reciba una cadena de búsqueda y retorne el primer nodo que contenga esa cadena en nombre, mail o dirección. 
        //          En caso de no encontrar ningún nodo con esa condición, debe retornar null.
        
        System.out.println("---------------------------------------");
        System.out.println("");
        System.out.println("");
        System.out.println("Punto 2.f: Buscar cadena");
        System.out.println(banco.buscarCadena("Vill").toString());
        System.out.println("");



        //Punto 2g. Defina un método que retorne el saldo promedio de las mujeres en las cuentas de ahorros.
       
        System.out.println("---------------------------------------");
        System.out.println("");
        System.out.println("");
        System.out.println("Punto 2.g: Saldo promedio cuentas de ahorro de mujeres");
        System.out.println(banco.promedioMujeresCuentaAhorro());
        System.out.println("");
        
        
        //Punto 2h. Defina un método que reciba un código y retorne true si el código existe en el ABB o falso en caso contrario.  
        
        System.out.println("---------------------------------------");
        System.out.println("");
        System.out.println("");
        System.out.println("Punto 2.h: Consultar cuenta");
        System.out.println(banco.existeCuenta(111) ? "La cuenta 111 existe" : "La cuenta 111 no existe");
        System.out.println(banco.existeCuenta(188) ? "La cuenta 188 existe" : "La cuenta 188 no existe");
        System.out.println("");
    }
    
}
