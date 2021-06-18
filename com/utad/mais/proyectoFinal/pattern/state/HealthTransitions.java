package com.utad.mais.proyectoFinal.pattern.state;

//Interfaz de las distintas transiciones entre estados
public interface HealthTransitions extends HealthState{
	//Métodos para transicionar de un estado a otro
	public void healthyTransition();
	public void injuredTransition();
	public void severelyInjuredTransition();
	public void deadTransition();
	public void paralyzedTransition();
}
