package com.utad.mais.proyectoFinal.pattern.singleton;

import com.utad.mais.proyectoFinal.interfazGrafica.*;
import com.utad.mais.proyectoFinal.pattern.strategy.*;
import com.utad.mais.proyectoFinal.personajes.Enemy;
import com.utad.mais.proyectoFinal.personajes.Personaje;

public class Calculadora
{
	private static Calculadora miCalculadora = new Calculadora(); //Instanciamos la �nica calculadora 
	
	private Calculadora() {}
	
	public static Calculadora getInstance()
	{	
		return miCalculadora;
	}
	
	private Integer formulaDa�o(Personaje atacante)
	{
		return this.formulaDa�o(atacante, 0.1); //Mandamos por defecto la defensa como 0.1 a su m�todo sobrecargado
	}
	
	private Integer formulaDa�o(Personaje atacante, Double defensa)
	{
		return (int)((atacante.getFuerza() + atacante.getAgilidad() + atacante.getIntelecto()) * defensa); //Formula
	}
	private Integer formulaVida(Personaje objetivo)
	{
		return this.formulaVida(objetivo, 0.3); //Mandamos por defecto el multiplicador como 0.3 a su m�todo sobrecargado
	}
	private Integer formulaVida(Personaje objetivo, Double multiplicador)
	{
		return (int)(objetivo.getIntelecto()*multiplicador); //Formula
	}
	private Integer formulaBuffo(Personaje objetivo)
	{
		return (int)((objetivo.getAgilidad()+objetivo.getFuerza()+objetivo.getIntelecto())*0.01); //Formula
	}
	public void calculaDa�oRecibido( Personaje atacante, Personaje objetivo) //strategy con dos par�metros
	{
//		Ataque normal
		if(objetivo instanceof Enemy)
		{
			if(((Enemy) objetivo).getStrategy() instanceof DefensiveStrategy)
			{
				this.calculaDa�oRecibido(atacante, objetivo, this.formulaDa�o(atacante,0.05)); //Calculamos el da�o, a pesar de que el objetivo recibir� menos da�o al estar a la defensiva
			}
			else
			{
				this.calculaDa�oRecibido(atacante, objetivo, this.formulaDa�o(atacante)); //Calculamos el da�o
			}
		}
		else
		{
			this.calculaDa�oRecibido(atacante, objetivo, this.formulaDa�o(atacante)); //Calculamos el da�o
		}
	}
	public void calculaDa�oRecibido(Personaje atacante, Personaje objetivo, Integer formula)
	{
		objetivo.setVida(objetivo.getVida()-formula); //Seteamos la vida debidamente
		InterfazGrafica.getInstance().a�adirTextoLog(atacante.getName() + " ha inflingido " + formula + " puntos de da�o a " + objetivo.getName() + "\n"); //Anexamos el texto en el log
		System.out.println("Da�o infligido:"+formula);
	}
	public void calculaCuraRecibida(Personaje objetivo)
	{
		if(objetivo.getVida()<20)
		{
			this.calculaCuraRecibida(objetivo, this.formulaVida(objetivo, 0.5)); //En caso de tener poca vida nos curaremos m�s
		}
		this.calculaCuraRecibida(objetivo, this.formulaVida(objetivo)); //Calculamos la cura
	}
	public void calculaCuraRecibida(Personaje objetivo, Integer formula)
	{
		objetivo.setVida((int)(objetivo.getVida()+formula)); //Seteamos la vida debidamente
		InterfazGrafica.getInstance().a�adirTextoLog(objetivo.getName() + " se cur� " + formula + " puntos\n"); //Anexamos el texto en el log
		System.out.println("Cura realizada:"+formula);
	}
	public void calculaDefendido(Personaje atacante, Personaje objetivo)
	{
		InterfazGrafica.getInstance().a�adirTextoLog(atacante.getName() + " se defendi�\n"); //Anexamos el texto en el log
		this.calculaDa�oRecibido(atacante, objetivo,this.formulaDa�o(atacante,0.04)); //Calculamos el da�o inflingido
		this.calculaCuraRecibida(atacante,this.formulaVida(atacante, 0.05)); //Calculamos la cura recibida
	}
	
	//Calculamos la actualizacion de las estad�sitcas tras el buffado
	public void calculaBuffado(Personaje objetivo)
	{
		InterfazGrafica.getInstance().a�adirTextoLog(objetivo.getName() + " se buff�\n"); //Anexamos el texto en el log
		
		//Calculamos el buffado de cada una de las estad�sticas
		this.calculaBuffadoFuerza(objetivo); 
		this.calculaBuffadoAgilidad(objetivo);
		this.calculaBuffadoIntelecto(objetivo);	
	}
	
	//M�todos para el buffado y seteamos las estad�sticas debidamente
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