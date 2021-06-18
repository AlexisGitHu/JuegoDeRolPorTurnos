package com.utad.mais.proyectoFinal.pattern.state;

import com.utad.mais.proyectoFinal.interfazGrafica.InterfazGrafica;
import com.utad.mais.proyectoFinal.pattern.singleton.*;
import com.utad.mais.proyectoFinal.personajes.*;

//Estado de personaje "herido"
public class Injured implements HealthTransitions
{
	//Atributo personaje al cual le a�adiremos un estado
	private Personaje player;
	
	//Constructor del estado
	public Injured(Personaje player) {
		this.player = player;
	}
	//Funci�n adicional que utilizaremos para la probabilidad de ser paralizado
	public static double getRandomDoubleBetweenRange(double min, double max){
		return ( Math.random()*(max-min) )+min;
	}
	//Transici�n del estado "herido" al estado "saludable"
	public void healthyTransition() {
		//Cambiamos el estado actual del personaje
		this.player.setActualState(this.player.getHealthyState());
		//Aumentamos las estad�sticas del personaje a sus niveles predeterminados
		this.player.addAgilidad(+25);
		this.player.addFuerza(+50);
	}
	//Transici�n que informa que ya estamos en el estado "herido"
	public void injuredTransition() {
		throw new IllegalStateException("Already injured");
	}
	//Transici�n del estado "herido" al estado "muy herido"
	public void severelyInjuredTransition() {
		//Cambiamos el estado actual del personaje
		this.player.setActualState(this.player.getSeverelyInjuredState());
		//Reducimos las estad�sticas del personaje
		this.player.addAgilidad(-25);
		this.player.addFuerza(-25);
	}
	//Transici�n del estado "saludable" al estado "muerto"
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
	//Transici�n del estado "herido" a "paralizado"
	public void paralyzedTransition() 
	{
		//Cambiamos el estado actual del jugador a "paralizado"
		this.player.setActualState(this.player.getParalyzedState());
	}
	//Funci�n que procesar� cada ronda para ver si se tiene que transicionar a otro estado
	public void process() 
	{
		//ejecutamos la funci�n auxiluar para calcular una probabilidad
		double experimentoAleatorio = getRandomDoubleBetweenRange(0, 100);
		//Si los puntos de vida han bajado por debajo de un punto, transici�n al estado "muerto"
		if(this.player.getVida() < 1) 
		{
			System.out.println("I am dead");
			this.deadTransition();
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
		//Si los puntos de salud se reducen hasta los 50 puntos, transici�n al estado "muy herido"
		else if(this.player.getVida() < 50) 
		{
			System.out.println("I am severely injured");
			if(this.player instanceof Jugador)
				InterfazGrafica.getInstance().a�adirTextoStats("I am severely injured\n");
			else
				InterfazGrafica.getInstance().a�adirTextoEnemy("I am severely injured\n");
			this.severelyInjuredTransition();
		}
		//Probabilidad del 50%
		//Caso paralizado
		else if(experimentoAleatorio < 50) 
		{
			System.out.println("Paralyzed");
			if(this.player instanceof Jugador)
				InterfazGrafica.getInstance().a�adirTextoStats("Paralyzed\n");
			else
				InterfazGrafica.getInstance().a�adirTextoEnemy("Paralyzed\n");
			this.paralyzedTransition();
		}
		//Caso en el que no se paraliza al personaje
		else 
		{
			System.out.println("Still injured");
			if(this.player instanceof Jugador)
				InterfazGrafica.getInstance().a�adirTextoStats("Still injured\n");
			else
				InterfazGrafica.getInstance().a�adirTextoEnemy("Still injured\n");
		}
	}
	
}
