/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.juego;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Allan
 */
public class JuegoPro {
    
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
        
        String[] posiciones = new String[2];
        posiciones[0] = "inicio";
        posiciones[1] = "fin";

        
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
            
            Random dado = new Random();
            
            if(lineaJuego.size()==0)
            {
                System.out.println("Movimiento valido");
                lineaJuego.add(ficha);
                int lado1 =0;
                int lado2 =0;
                if(ficha.getLado1()==-1 && ficha.getLado2()==-1)
                {
                   System.out.println("Ingrese el lado1 de su ficha: ");
                   lado1 = sc.nextInt();
                   System.out.println("Ingrese el lado2 de su ficha: ");
                   lado2 = sc.nextInt();
                }
                else
                {
                    System.out.println("Ingrese el lado1 de su ficha: ");
                    lado1 = (dado.nextInt(6)+1);
                    System.out.println(lado1);
                    System.out.println("Ingrese el lado2 de su ficha: ");
                    lado2 = (dado.nextInt(6)+1);
                    System.out.println(lado2);
                }
                comodin.setLado1(lado1);
                comodin.setLado2(lado2);
                jugador.removerFicha(ficha);
                r = true;
                
            }
            else
            {
               
                System.out.println("Movimiento valido");
                String posicion = "";
                if(ficha.getLado1()==-1 && ficha.getLado2()==-1)
                {
                    System.out.println("Ingrese la posicion INICIO/FIN: ");
                    posicion = sc.next();
                    
                }
                else
                {
                    System.out.println("Ingrese la posicion INICIO/FIN: ");
                    posicion = posiciones[dado.nextInt(2)];
                    System.out.println(posicion);
                }
                
                
                if(posicion.toUpperCase().equals("INICIO"))
                {
                    lineaJuego.add(0, ficha);
                    int lado1 =0;
                    if(ficha.getLado1()==-1 && ficha.getLado2()==-1)
                    {
                        System.out.println("Ingrese el lado1 de su ficha: ");
                        lado1 = sc.nextInt();
                    }
                    else
                    {
                        System.out.println("Ingrese el lado1 de su ficha: ");
                        lado1 = (dado.nextInt(6)+1);
                        System.out.println(lado1);
                    }
                    comodin.setLado1(lado1);
                    comodin.setLado2(-1);
                    jugador.removerFicha(ficha);
                    
                }
                else
                {
                    lineaJuego.add(ficha);
                    int lado2 =0;
                    if(ficha.getLado1()==-1 && ficha.getLado2()==-1)
                    {
                        System.out.println("Ingrese el lado2 de su ficha: ");
                        lado2 = sc.nextInt();
                    }
                    else
                    {
                        System.out.println("Ingrese el lado2 de su ficha: ");
                        lado2 = (dado.nextInt(6)+1);
                        System.out.println(lado2);
                    }
                    comodin.setLado2(lado2);
                    comodin.setLado1(-1);
                    
                    jugador.removerFicha(ficha);
                }
                
                r = true;
            }
        }
        return r;
    }
    
    public boolean manoJugable(Jugador jugador)
    {
        boolean condicion1 = false;
        
        for(Ficha ficha: jugador.getMano())
        {
            if(ficha instanceof FichaComodin)
            {
                condicion1=true;// que hay al menos una ficha comodin en la mano
            }
        }
        
        if(!condicion1) // va entrar si no hay un ficha comodin
        {
            for(Ficha ficha : jugador.getMano())
            {
              
                if(ficha.getLado2()==this.obtenerValorInicioLinea())
                {
                    condicion1=true;
                }
                else if(ficha.getLado1()==this.obtenerValorFinLinea())
                {
                    condicion1=true;
                }
                
                
            }
        }

        return condicion1;
        
    }
   
    
    public boolean jugar(Jugador jugador1, int indice)
            
    {
            
            Ficha ficha1 = jugador1.getFicha(indice);
            boolean condicion = this.agregarFichaLinea(ficha1, jugador1);
            
            if(condicion || ficha1 instanceof FichaComodin)
              {
                if(ficha1 instanceof FichaComodin)
                {
                    System.out.println("Nueva linea de juego --> "+this.mostrarLinea());
                    System.out.println("");
                    System.out.println("----------------------");
                            
          
                }
                else
                {
                    System.out.println("Movimiento Valido");
                    System.out.println("Nueva linea de juego --> "+this.mostrarLinea());
                    System.out.println("");
                    System.out.println("----------------------");
                }
                
              }
            else
               {
                System.out.println("Ficha tenia "+ficha1+" No puedo jugar esa ficha, intentalo de nuevo");
                
                   
               }
                
            return condicion;
    }
   
    
    
    
    public int Maquina(Jugador jugador)
    {
        int indice = -1;
        boolean condicion1 = false;
        int cont =0;
        
        for(int i =0 ; i<jugador.getMano().size(); i++)
            {
                Ficha ficha = jugador.getMano().get(i);
                
                if(this.lineaJuego.size()!=0)
                {
                    if((ficha.getLado2()==this.obtenerValorInicioLinea()) && cont==0)
                      {
                        indice = i;
                        cont++;
                      }
                    else if((ficha.getLado1()==this.obtenerValorFinLinea()) && cont ==0)
                      {
                        indice = i;
                        cont++;
                      }
                
                }
                else if(cont==0)
                {
                    
                    indice = i;
                    cont++;
                }
                
            }
        
        if(indice== -1) // va entrar si no hay un ficha comodin
        {
             for(int i =0; i<jugador.getMano().size() ; i++)
                {
                    Ficha ficha = jugador.getMano().get(i);
                    
                    if(ficha instanceof FichaComodin)
                       {
                        ((FichaComodin) ficha).setLado1(-2);
                      
                        ((FichaComodin) ficha).setLado2(-2);
                     
                         indice = i;
                       }
                }
        }
        
       
        return indice;
    }
    
    
}

