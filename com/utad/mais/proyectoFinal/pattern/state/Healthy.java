package com.utad.mais.proyectoFinal.pattern.state;

import com.utad.mais.proyectoFinal.interfazGrafica.*;
import com.utad.mais.proyectoFinal.pattern.singleton.*;
import com.utad.mais.proyectoFinal.personajes.*;

//Estado de personaje "saludable"
public class Healthy implements HealthTransitions
{
	//Atributo personaje al cual le añadiremos un estado
	private Personaje player;
	
	//Constructor del estado
	public Healthy(Personaje player) 
	{
		this.player = player;
	}
	//Como ya estamos saludables, lo indicamos, y no hacemos nada más
	public void healthyTransition()
	{
		throw new IllegalStateException("Already Healthy");
	}
	//Transición del estado "saludable" al estado "herido"
	public void injuredTransition() 
	{
		//Cambiamos el estado actual del personaje
		this.player.setActualState(this.player.getInjuredState());
		//Bajamos las estadísticas del personaje
		this.player.addAgilidad(-25);
		this.player.addFuerza(-50);
	}
	
	//Transiciçon del estado "saludable" al estado "muy herido"
	public void severelyInjuredTransition() 
	{
		//Cambiamos el estado actual del personaje
		this.player.setActualState(this.player.getSeverelyInjuredState());
		//Reducimos más las estadísticas
		this.player.addAgilidad(-50);
		this.player.addFuerza(-75);
	}
	
	//Transición del estado "saludable" al estado "muerto"
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
			InterfazGrafica.getInstance().añadirTextoLog(this.player.getName()+" pasó a mejor vida\n");
			GameController.addEnemigoMatado();
			GameController.restaEnemyNumber();
			//Añadimos puntos en función de si el enemigo matado es un Jefe o no
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
	//Transición imposible de "saludable" a "paralizado"
	public void paralyzedTransition() 
	{
		throw new IllegalStateException("I am healthy, cant be paralyzed");
	}
	//Función que procesará cada ronda para ver si se tiene que transicionar a otro estado
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
				InterfazGrafica.getInstance().añadirTextoStats("I am severely injured\n");
			else
				InterfazGrafica.getInstance().añadirTextoEnemy("I am severely injured\n");
			
			this.severelyInjuredTransition();
		}
		//Si la vida del personaje baja por debajo de los 125 puntos de vida, pero más de 50, transicionamos a estado "herido"
		if(this.player.getVida() < 125 && this.player.getVida() >= 50)
		{
			System.out.println("I am injured");
			if(this.player instanceof Jugador)
				InterfazGrafica.getInstance().añadirTextoStats("I am injured\n");
			else
				InterfazGrafica.getInstance().añadirTextoEnemy("I am injured\n");
			
			this.injuredTransition();
		}
		else
		{
			System.out.println("Still healthy");
			if(this.player instanceof Jugador)
				InterfazGrafica.getInstance().añadirTextoStats("Still healthy\n");
			else
				InterfazGrafica.getInstance().añadirTextoEnemy("Still healthy\n");

		}
	}
}
