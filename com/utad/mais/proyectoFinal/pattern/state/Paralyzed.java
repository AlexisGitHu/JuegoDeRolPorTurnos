package com.utad.mais.proyectoFinal.pattern.state;

import com.utad.mais.proyectoFinal.interfazGrafica.*;
import com.utad.mais.proyectoFinal.pattern.singleton.*;
import com.utad.mais.proyectoFinal.personajes.*;

//Estado de personaje "paralizado"
public class Paralyzed implements HealthTransitions
{
	//Atributo personaje al cual le añadiremos un estado
	private Personaje player;
	//Constructor del estado
	public Paralyzed(Personaje player) {
		this.player = player;
	}
	public void healthyTransition() {
		throw new IllegalStateException("Cant be healthy");
	}
	//Transición al estado "herido"
	public void injuredTransition() {
		System.out.println("Ending paralyzed state");
		//Cambiamos el estado del jugador
		this.player.setActualState(this.player.getInjuredState());
	}
	//Transición al estado "muy herido"
	public void severelyInjuredTransition() {
		System.out.println("Ending paralyzed state");
		//Cambiamos el estado del personaje
		this.player.setActualState(this.player.getSeverelyInjuredState());
		//Reducimos las estadísticas del personaje
		this.player.addAgilidad(-25);
		this.player.addFuerza(-25);
	}
	public void paralyzedTransition() {
		throw new IllegalStateException("I already am paralyzed");
	}
	//Transición al estado "muerto"
	public void deadTransition() {
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
	//Función que procesará cada ronda para ver si se tiene que transicionar a otro estado
	public void process() 
	{
		//Si los puntos de vida han bajado por debajo de un punto, transición al estado "muerto"
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
		else if(this.player.getVida() < 125 && this.player.getVida() >= 50) 
		{
			System.out.println("I am injured");
			if(this.player instanceof Jugador)
				InterfazGrafica.getInstance().añadirTextoStats("I am injured\n");
			else
				InterfazGrafica.getInstance().añadirTextoEnemy("I am injured\n");
			this.injuredTransition();
		}
	}
}
