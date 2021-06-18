package com.utad.mais.proyectoFinal.pattern.state;

import com.utad.mais.proyectoFinal.interfazGrafica.*;
import com.utad.mais.proyectoFinal.pattern.singleton.*;
import com.utad.mais.proyectoFinal.personajes.*;

//Estado de personaje "muy herido"
public class SeverelyInjured implements HealthTransitions
{
	//Atributo personaje al cual le a�adiremos un estado
	private Personaje player;
	
	//Constructor del estado
	public SeverelyInjured(Personaje player)
	{
		this.player = player;
	}
	//Transici�n al estado "saludable"
	public void healthyTransition() 
	{
		//Cambiamos el estado actual del personaje
		this.player.setActualState(this.player.getHealthyState());
		//Aumentamos las estad�sticas al valor predeterminado
		this.player.addAgilidad(+50);
		this.player.addFuerza(+75);
	}
	//Transici�n al estado "herido"
	public void injuredTransition()
	{
		//Cambiamos el estado actual del personaje
		this.player.setActualState(this.player.getInjuredState());
		//Aumentamos las estad�sticas
		this.player.addAgilidad(+25);
		this.player.addFuerza(+25);
	}
	
	public void severelyInjuredTransition() 
	{
		throw new IllegalStateException("Already Severely Injured");
	}
	
	public void paralyzedTransition() 
	{
		throw new IllegalStateException("Cant be paralyzed");
	}
	//Transici�n al estado "muerto"
	public void deadTransition() 
	{
		//Cambiamos el estado actual del personaje
		this.player.setActualState(this.player.getDeadState());
		//Si el personaje es el jugador, terminamos el juego
		if(this.player instanceof Jugador)
		{
			GameController.getInstance().gameOver();
		}
		//Si no es un jugador, informamos al jugador que el enemigo ha muerto, y generamos otro enemigo
		else 
		{
			InterfazGrafica.getInstance().a�adirTextoLog(this.player.getName()+" pas� a mejor vida\n");
			GameController.addEnemigoMatado();
			GameController.restaEnemyNumber();
			//A�adimos puntos en funci�n de si el enemigo matado es un Jefe o no
			if(this.player instanceof Boss)
			{
				GameController.addPuntosTotales(100);
			}
			else
			{
				GameController.addPuntosTotales(10);
			}
		}
	}
	//Funci�n que procesar� cada ronda para ver si se tiene que transicionar a otro estado
	public void process() 
	{
		//Si los puntos de vida han bajado por debajo de un punto, transici�n al estado "muerto"
		if(this.player.getVida() < 1)
		{
			System.out.println("I am dead");
			this.deadTransition();
		}
		//Si la vida del personaje baja por debajo de los 125 puntos de vida, pero m�s de 50, transicionamos a estado "herido"
		else if(this.player.getVida() >= 50 && this.player.getVida()< 125) 
		{
			System.out.println("Health recovered, but I am injured");
			if(this.player instanceof Jugador)
				InterfazGrafica.getInstance().a�adirTextoStats("Health recovered, but I am injured\n");
			else
				InterfazGrafica.getInstance().a�adirTextoEnemy("Health recovered, but I am injured\n");
			this.injuredTransition();
		}
		//Si los puntos de vida bajan suben hasta los 125 puntos o m�s de salud, transici�n al estado "saludable"
		else if(this.player.getVida() >= 125)
		{
			System.out.println("Health recovered");
			if(this.player instanceof Jugador)
				InterfazGrafica.getInstance().a�adirTextoStats("Health recovered\n");
			else
				InterfazGrafica.getInstance().a�adirTextoEnemy("Health recovered\n");
			this.healthyTransition();
		}
		else
		{
			System.out.println("Still severely injured");	
			if(this.player instanceof Jugador)
				InterfazGrafica.getInstance().a�adirTextoStats("Still severely injured\n");
			else
				InterfazGrafica.getInstance().a�adirTextoEnemy("Still severely injured\n");
		}
	}
}
