/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.juego;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
/**
 *
 * @author allan moises chacha leon 
 */
public class Juego {
     private ArrayList<Ficha> lineaJuego;
    private ArrayList<Jugador> jugadores;
    
    Scanner sc = new Scanner(System.in);
    
    public JuegoPro()
    {
        lineaJuego = new ArrayList<>();
        jugadores = new ArrayList<>();
    }

    public ArrayList<Ficha> getLineaJuego() {
        return lineaJuego;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    
    
    
    public void agregarJugador(String nombre)
    {
        Jugador jugador = new Jugador(nombre,Utilitaria.crearManoJugador());
        jugadores.add(jugador);
    }
    
    public int obtenerValorInicioLinea()
    {
        int lado1 = lineaJuego.get(0).getLado1();
        return lado1;
    }
    
    public int obtenerValorFinLinea()
    {
        int lado2 = lineaJuego.get(lineaJuego.size()-1).getLado2();
        return lado2;
    }
    
    public String mostrarLinea()
    {
        StringBuilder m = new StringBuilder();
        int cont =0;
        for(Ficha ficha: lineaJuego)
        {
            if(cont<(lineaJuego.size()-1))
            {
                m.append(ficha.toString()+"-");
            }
            else
            {
                m.append(ficha.toString());
            }
            cont++;
        }
        
        return m.toString();
    }
    
    public boolean agregarFichaLinea(Ficha ficha, Jugador jugador )
    {

        
        boolean r =false;
        if(!(ficha instanceof FichaComodin))
        {
            if(lineaJuego.size()==0)
            {
                lineaJuego.add(ficha);
                jugador.removerFicha(ficha);
                r= true;
            }
            else
            {
                if((ficha.getLado2()==this.obtenerValorInicioLinea()) || (ficha.getLado1()==this.obtenerValorFinLinea()))
                {
                    if(ficha.getLado2()==this.obtenerValorInicioLinea())
                    {
                        lineaJuego.add(0, ficha);
                        jugador.removerFicha(ficha);
                    }
                    else
                    {
                        lineaJuego.add(ficha);
                        jugador.removerFicha(ficha);
                    }
                    
                    
                    
                    r= true;
                    
                }
                else
                {
                    r= false;
                }
                
                
            }
        }
        else
        {
            FichaComodin comodin = (FichaComodin)ficha;
            
            if(lineaJuego.size()==0)
            {
                System.out.println("Movimiento valido");
                lineaJuego.add(ficha);
                System.out.println("Ingrese el lado1 de su ficha: ");
                int lado1 = sc.nextInt();
                System.out.println("Ingrese el lado2 de su ficha: ");
                int lado2 = sc.nextInt();
                comodin.setLado1(lado1);
                comodin.setLado2(lado2);
                jugador.removerFicha(ficha);
                r = true;
                
            }
            else
            {
                System.out.println("Movimiento valido");
                System.out.println("Ingrese la posicion INICIO/FIN: ");
                String pos = sc.next();
                if(pos.toUpperCase().equals("INICIO"))
                {
                    lineaJuego.add(0, ficha);
                    System.out.println("Ingrese el lado1 de su ficha: ");
                    int lado1 = sc.nextInt();
                    comodin.setLado1(lado1);
                    jugador.removerFicha(ficha);
                    
                }
                else
                {
                    lineaJuego.add(ficha);
                    System.out.println("Ingrese el lado2 de su ficha: ");
                    int lado2 = sc.nextInt();
                    comodin.setLado2(lado2);
                    jugador.removerFicha(ficha);
                }
                
                r = true;
            }
        }
        return r;
    }
    
}
