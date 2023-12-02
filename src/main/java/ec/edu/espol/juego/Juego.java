/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espol.juego;
import java.util.Scanner;
/**
 *
 * @author Allan
 */
public class Juego {

    public static void main(String[] args) {
        JuegoPro juego = new JuegoPro();
        
        juego.agregarJugador("0");
        
        juego.agregarJugador("1");
        
        Jugador jugador1 = juego.getJugadores().get(0);
        Jugador jugador2 = juego.getJugadores().get(1);
        Scanner sc = new Scanner(System.in);
        boolean condicionfinal2 = true;
        boolean condicionfinal = true;
        
        
        System.out.println("Bienvenido al juego Dominó");
        System.out.println("<------------------------->");
        
        while(((jugador1.getMano().size()!=0)&&(jugador2.getMano().size()!=0)) && (condicionfinal && condicionfinal2) )
        {
           
            if(condicionfinal)
            {
                System.out.println("Jugador "+jugador1.getNombre()+": Mano --> "+jugador1.imprimirMano());
            
                System.out.println("Linea de juego --> "+juego.mostrarLinea());
                int indice = juego.Maquina(jugador1);
            
                
                if(indice!=-1)
                   {
                       System.out.println("Ingrese el indice de ficha para jugar(0 es el primero): ");
                       System.out.println(indice);
                       boolean condc = juego.jugar(jugador1, indice);
                    
                   }
                else
                   {
                    System.out.println("Upss! Jugador "+jugador1.getNombre()+", tiene una mano no jugable Perdio el juego, intentelo de nuevo ");
                    condicionfinal2 = false; // indica q el jugador 0 perdio
                   }
                  
                
            }
            
            
            if(condicionfinal2)
            {
                System.out.println("Jugador "+jugador2.getNombre()+": Mano --> "+jugador2.imprimirMano());
            
                System.out.println("Linea de juego --> "+juego.mostrarLinea());
                boolean condicion2 = false;
                boolean jugable2 = juego.manoJugable(jugador2);
                
            
                do{
                    
                    if(jugable2)
                      {
                        System.out.println("Ingrese el indice de ficha para jugar(0 es el primero): ");
                        int indice = sc.nextInt();
                        boolean condc1 = juego.jugar(jugador2, indice);
                        condicion2 = condc1;
                      }
                    else
                      {   
                        System.out.println("Upss! Jugador "+jugador2.getNombre()+", tiene una mano no jugable Perdio el juego, intentelo de nuevo");
                        condicion2 = true;
                        condicionfinal = false; // indica q el jugador 1 perdio
                      }
                 }    
                 while(!condicion2);
            }
            
            
       }  
        
        
        
       if(condicionfinal== false)
       {
           System.out.println("Felicidades!! "+jugador1.getNombre() +" ha ganado el juego Dominous Dominó");
       }
       else
       {
           System.out.println("Felicidades!! "+jugador2.getNombre() +" ha ganado el juego Dominous Dominó");
       }
       
 
    }
    

}