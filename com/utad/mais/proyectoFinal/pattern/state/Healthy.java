package com.utad.mais.proyectoFinal.pattern.state;

import com.utad.mais.proyectoFinal.interfazGrafica.*;
import com.utad.mais.proyectoFinal.pattern.singleton.*;
import com.utad.mais.proyectoFinal.personajes.*;

//Estado de personaje "saludable"
public class Healthy implements HealthTransitions
{
	//Atributo personaje al cual le a�adiremos un estado
	private Personaje player;
	
	//Constructor del estado
	public Healthy(Personaje player) 
	{
		this.player = player;
	}
	//Como ya estamos saludables, lo indicamos, y no hacemos nada m�s
	public void healthyTransition()
	{
		throw new IllegalStateException("Already Healthy");
	}
	//Transici�n del estado "saludable" al estado "herido"
	public void injuredTransition() 
	{
		//Cambiamos el estado actual del personaje
		this.player.setActualState(this.player.getInjuredState());
		//Bajamos las estad�sticas del personaje
		this.player.addAgilidad(-25);
		this.player.addFuerza(-50);
	}
	
	//Transici�on del estado "saludable" al estado "muy herido"
	public void severelyInjuredTransition() 
	{
		//Cambiamos el estado actual del personaje
		this.player.setActualState(this.player.getSeverelyInjuredState());
		//Reducimos m�s las estad�sticas
		this.player.addAgilidad(-50);
		this.player.addFuerza(-75);
	}
	
	//Transici�n del estado "saludable" al estado "muerto"
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
	//Transici�n imposible de "saludable" a "paralizado"
	public void paralyzedTransition() 
	{
		throw new IllegalStateException("I am healthy, cant be paralyzed");
	}
	//Funci�n que procesar� cada ronda para ver si se tiene que transicionar a otro estado
	public void process()
	{
		//Si la vida del personaje baja por debajo de un punto de vida, transicionamos a estado "muerto"
		if(this.player.getVida() < 1) 
		{
			System.out.println("I am dead");
			this.deadTransition();
		}
		//Si la vida del personaje baja por debajo de los 50 puntos de vida, transicionamos a estado "muy herido"
		else if(this.player.getVida() < 50) 
		{
			System.out.println("I am severely injured");
			
			if(this.player instanceof Jugador)
				InterfazGrafica.getInstance().a�adirTextoStats("I am severely injured\n");
			else
				InterfazGrafica.getInstance().a�adirTextoEnemy("I am severely injured\n");
			
			this.severelyInjuredTransition();
		}
		//Si la vida del personaje baja por debajo de los 125 puntos de vida, pero m�s de 50, transicionamos a estado "herido"
		if(this.player.getVida() < 125 && this.player.getVida() >= 50)
		{
			System.out.println("I am injured");
			if(this.player instanceof Jugador)
				InterfazGrafica.getInstance().a�adirTextoStats("I am injured\n");
			else
				InterfazGrafica.getInstance().a�adirTextoEnemy("I am injured\n");
			
			this.injuredTransition();
		}
		else
		{
			System.out.println("Still healthy");
			if(this.player instanceof Jugador)
				InterfazGrafica.getInstance().a�adirTextoStats("Still healthy\n");
			else
				InterfazGrafica.getInstance().a�adirTextoEnemy("Still healthy\n");

		}
	}
}
