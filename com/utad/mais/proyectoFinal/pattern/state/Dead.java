package com.utad.mais.proyectoFinal.pattern.state;

import com.utad.mais.proyectoFinal.personajes.Personaje;

//Estado de personaje "muerto"
public class Dead implements HealthTransitions
{
	private Personaje personaje;
	public Dead(Personaje personaje) 
	{
		super();
		this.setPersonaje(personaje);
	}
	public void healthyTransition() {
		throw new IllegalStateException("Already dead");
	}
	public void injuredTransition() {
		throw new IllegalStateException("Already dead");
	}
	public void severelyInjuredTransition() {
		throw new IllegalStateException("Already dead");
	}
	public void paralyzedTransition() {
		throw new IllegalStateException("Already dead");
	}
	public void deadTransition() {
		throw new IllegalStateException("Already dead");
	}
	public void process() {
		System.out.println("GAME OVER");
	}
	public Personaje getPersonaje() {
		return personaje;
	}
	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}
}
