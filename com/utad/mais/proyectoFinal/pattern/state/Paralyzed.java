package com.utad.mais.proyectoFinal.pattern.state;

import com.utad.mais.proyectoFinal.interfazGrafica.*;
import com.utad.mais.proyectoFinal.pattern.singleton.*;
import com.utad.mais.proyectoFinal.personajes.*;

//Estado de personaje "paralizado"
public class Paralyzed implements HealthTransitions
{
	//Atributo personaje al cual le a�adiremos un estado
	private Personaje player;
	//Constructor del estado
	public Paralyzed(Personaje player) {
		this.player = player;
	}
	public void healthyTransition() {
		throw new IllegalStateException("Cant be healthy");
	}
	//Transici�n al estado "herido"
	public void injuredTransition() {
		System.out.println("Ending paralyzed state");
		//Cambiamos el estado del jugador
		this.player.setActualState(this.player.getInjuredState());
	}
	//Transici�n al estado "muy herido"
	public void severelyInjuredTransition() {
		System.out.println("Ending paralyzed state");
		//Cambiamos el estado del personaje
		this.player.setActualState(this.player.getSeverelyInjuredState());
		//Reducimos las estad�sticas del personaje
		this.player.addAgilidad(-25);
		this.player.addFuerza(-25);
	}
	public void paralyzedTransition() {
		throw new IllegalStateException("I already am paralyzed");
	}
	//Transici�n al estado "muerto"
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
		else if(this.player.getVida() < 125 && this.player.getVida() >= 50) 
		{
			System.out.println("I am injured");
			if(this.player instanceof Jugador)
				InterfazGrafica.getInstance().a�adirTextoStats("I am injured\n");
			else
				InterfazGrafica.getInstance().a�adirTextoEnemy("I am injured\n");
			this.injuredTransition();
		}
	}
}
