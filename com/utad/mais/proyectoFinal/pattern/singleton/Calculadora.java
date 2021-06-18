package com.utad.mais.proyectoFinal.pattern.singleton;

import com.utad.mais.proyectoFinal.interfazGrafica.*;
import com.utad.mais.proyectoFinal.pattern.strategy.*;
import com.utad.mais.proyectoFinal.personajes.Enemy;
import com.utad.mais.proyectoFinal.personajes.Personaje;

public class Calculadora
{
	private static Calculadora miCalculadora = new Calculadora(); //Instanciamos la única calculadora 
	
	private Calculadora() {}
	
	public static Calculadora getInstance()
	{	
		return miCalculadora;
	}
	
	private Integer formulaDaño(Personaje atacante)
	{
		return this.formulaDaño(atacante, 0.1); //Mandamos por defecto la defensa como 0.1 a su método sobrecargado
	}
	
	private Integer formulaDaño(Personaje atacante, Double defensa)
	{
		return (int)((atacante.getFuerza() + atacante.getAgilidad() + atacante.getIntelecto()) * defensa); //Formula
	}
	private Integer formulaVida(Personaje objetivo)
	{
		return this.formulaVida(objetivo, 0.3); //Mandamos por defecto el multiplicador como 0.3 a su método sobrecargado
	}
	private Integer formulaVida(Personaje objetivo, Double multiplicador)
	{
		return (int)(objetivo.getIntelecto()*multiplicador); //Formula
	}
	private Integer formulaBuffo(Personaje objetivo)
	{
		return (int)((objetivo.getAgilidad()+objetivo.getFuerza()+objetivo.getIntelecto())*0.01); //Formula
	}
	public void calculaDañoRecibido( Personaje atacante, Personaje objetivo) //strategy con dos parámetros
	{
//		Ataque normal
		if(objetivo instanceof Enemy)
		{
			if(((Enemy) objetivo).getStrategy() instanceof DefensiveStrategy)
			{
				this.calculaDañoRecibido(atacante, objetivo, this.formulaDaño(atacante,0.05)); //Calculamos el daño, a pesar de que el objetivo recibirá menos daño al estar a la defensiva
			}
			else
			{
				this.calculaDañoRecibido(atacante, objetivo, this.formulaDaño(atacante)); //Calculamos el daño
			}
		}
		else
		{
			this.calculaDañoRecibido(atacante, objetivo, this.formulaDaño(atacante)); //Calculamos el daño
		}
	}
	public void calculaDañoRecibido(Personaje atacante, Personaje objetivo, Integer formula)
	{
		objetivo.setVida(objetivo.getVida()-formula); //Seteamos la vida debidamente
		InterfazGrafica.getInstance().añadirTextoLog(atacante.getName() + " ha inflingido " + formula + " puntos de daño a " + objetivo.getName() + "\n"); //Anexamos el texto en el log
		System.out.println("Daño infligido:"+formula);
	}
	public void calculaCuraRecibida(Personaje objetivo)
	{
		if(objetivo.getVida()<20)
		{
			this.calculaCuraRecibida(objetivo, this.formulaVida(objetivo, 0.5)); //En caso de tener poca vida nos curaremos más
		}
		this.calculaCuraRecibida(objetivo, this.formulaVida(objetivo)); //Calculamos la cura
	}
	public void calculaCuraRecibida(Personaje objetivo, Integer formula)
	{
		objetivo.setVida((int)(objetivo.getVida()+formula)); //Seteamos la vida debidamente
		InterfazGrafica.getInstance().añadirTextoLog(objetivo.getName() + " se curó " + formula + " puntos\n"); //Anexamos el texto en el log
		System.out.println("Cura realizada:"+formula);
	}
	public void calculaDefendido(Personaje atacante, Personaje objetivo)
	{
		InterfazGrafica.getInstance().añadirTextoLog(atacante.getName() + " se defendió\n"); //Anexamos el texto en el log
		this.calculaDañoRecibido(atacante, objetivo,this.formulaDaño(atacante,0.04)); //Calculamos el daño inflingido
		this.calculaCuraRecibida(atacante,this.formulaVida(atacante, 0.05)); //Calculamos la cura recibida
	}
	
	//Calculamos la actualizacion de las estadísitcas tras el buffado
	public void calculaBuffado(Personaje objetivo)
	{
		InterfazGrafica.getInstance().añadirTextoLog(objetivo.getName() + " se buffó\n"); //Anexamos el texto en el log
		
		//Calculamos el buffado de cada una de las estadísticas
		this.calculaBuffadoFuerza(objetivo); 
		this.calculaBuffadoAgilidad(objetivo);
		this.calculaBuffadoIntelecto(objetivo);	
	}
	
	//Métodos para el buffado y seteamos las estadísticas debidamente
	public void calculaBuffadoIntelecto(Personaje objetivo)
	{
		objetivo.setIntelecto(objetivo.getIntelecto()+this.formulaBuffo(objetivo));
	}
	public void calculaBuffadoAgilidad(Personaje objetivo)
	{
		objetivo.setAgilidad(objetivo.getAgilidad()+this.formulaBuffo(objetivo));
	}
	public void calculaBuffadoFuerza(Personaje objetivo)
	{
		objetivo.setFuerza(objetivo.getFuerza()+this.formulaBuffo(objetivo));
	}
}