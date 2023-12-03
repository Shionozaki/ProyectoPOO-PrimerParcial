/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.juego;

/**
 *
 * @author user
 */
public class Ficha 
{
    protected int lado1;
    protected int lado2;
    
    //CONSTRUCTOR--------------------------------------------
    public Ficha(int lado1, int lado2) 
    {
        this.lado1 = lado1;
        this.lado2 = lado2;
    }
    
    //GETTERS------------------------------------------------
    public int getLado1() 
    {
        return lado1;
    }

    public int getLado2() 
    {
        return lado2;
    }
    
    //METODOS-------------------------------------------------
    public String toString()
    {
        return (this.lado1 + ":" +this.lado2);
    }   
}
