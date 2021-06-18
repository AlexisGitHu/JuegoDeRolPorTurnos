package com.utad.mais.proyectoFinal.personajes;

import com.utad.mais.proyectoFinal.pattern.singleton.*;
import com.utad.mais.proyectoFinal.pattern.state.*;
import com.utad.mais.proyectoFinal.pattern.strategy.*;

public abstract class Enemy implements Personaje 
{
	/*
	 * Clase abstracta que implementa la interfaz jugador. 
	 * 
	 * Todos los enemigos del juego deben heredar de esta clase.
	 * 
	 * Los métodos caracterizadores son, en general, los getters y setters de todos sus atributos, 
	 * métodos especiales que modifican sus estadísticas base y los getters de su estado actual y todos los estados posibles
	 * puesto que cualquier personaje es el contexto del patrón State.
	 * 
	 * Además, todos los enemigos implementan características especiales de su raza y guardan una referencia al GameController.
	 * 
	 * Todos los enemigos al no ser controlados por el usuario, deben implementar una estrategia 
	 * que determina el carácter de sus acciones (patrón strategy).
	 */
	private Integer fuerza;
	private Integer agilidad;
	private Integer intelecto;
	private Integer resistenciaFisica;
	private Integer resistenciaMagica;
	private Integer vida;
	private ActivityStrategy strategy;
	private GameController gameController;
	private String name;
	private Caracteristicas caracteristica;
	private HealthState actualState;
	private Healthy healthyState = new Healthy(this);
	private Injured injuredState=new Injured(this);
	private SeverelyInjured severelyInjuredState= new SeverelyInjured(this);
	private Paralyzed paralyzedState= new Paralyzed(this);
	private Dead deadState = new Dead(this);
	private final Integer vidaInicial;
	public Enemy(Integer fuerza, Integer agilidad, Integer intelecto, Integer resistenciaFisica
			, Integer resistenciaMagica, Integer vida,String name,Caracteristicas caracteristica,ActivityStrategy strategy)
	{
		this.fuerza=fuerza;
		this.agilidad=agilidad;
		this.intelecto=intelecto;
		this.resistenciaFisica=resistenciaFisica;
		this.resistenciaMagica=resistenciaMagica;
		this.vida=vida;
		this.name=name;
		this.setCaracteristica(caracteristica);
		this.strategy=strategy;
		this.setGameController(GameController.getInstance());
		this.actualState=new Healthy(this);
		this.vidaInicial=vida;
	}
	public Integer getFuerza()
	{
		return this.fuerza;
	}
	public Integer getAgilidad()
	{
		return this.agilidad;
	}
	public Integer getIntelecto()
	{
		return this.intelecto;
	}
	public Integer getResistenciaMagica()
	{
		return this.resistenciaMagica;
	}
	public Integer getResistenciaFisica()
	{
		return this.resistenciaFisica;
	}
	public Integer getVida()
	{
		return this.vida;
	}
	public String getName()
	{
		return this.name;
	}
	public void setFuerza(Integer fuerza)
	{
		this.fuerza=fuerza;
	}
	public void setAgilidad(Integer agilidad)
	{
		this.agilidad=agilidad;
	}
	public void setIntelecto(Integer intelecto)
	{
		this.intelecto=intelecto;
	}
	public void setResistenciaMagica(Integer resistenciaMagica)
	{
		this.resistenciaMagica=resistenciaMagica;
	}
	public void setResistenciaFisica(Integer resistenciaFisica)
	{
		this.resistenciaFisica=resistenciaFisica;
	}
	public void setVida(Integer vida)
	{
		if(vida>this.vidaInicial)
		{
			vida=this.vidaInicial;
		}
		this.vida=vida;
	}
	public ActivityStrategy getStrategy()
	{
		return this.strategy;
	}
	public void setStrategy(ActivityStrategy strategy)
	{
		this.strategy=strategy;
	}
	public void applyStrategy(Personaje personaje2)
	{
		this.strategy.applyStrategy(this,personaje2);
	}
	public GameController getGameController() {
		return gameController;
	}
	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}
	@Override
	public HealthState getActualState() {
		return actualState;
	}
	public void setActualState(HealthState actualState) {
		this.actualState = actualState;
	}
	public HealthState getSeverelyInjuredState() {
		// TODO Auto-generated method stub
		return this.severelyInjuredState;
	}
	public void setSeverelyInjuredState(SeverelyInjured severelyInjuredState)
	{
		this.severelyInjuredState=severelyInjuredState;
	}
	public HealthState getInjuredState() {
		// TODO Auto-generated method stub
		return this.injuredState;
	}
	public void setInjuredState(Injured injuredState)
	{
		this.injuredState=injuredState;
	}
	public HealthState getDeadState() {
		// TODO Auto-generated method stub
		return this.deadState;
	}
	public void setDeadState(Dead deadState)
	{
		this.deadState=deadState;
	}
	public HealthState getHealthyState() {
		// TODO Auto-generated method stub
		return this.healthyState;
	}
	public void setHealthyState(Healthy healthyState)
	{
		this.healthyState=healthyState;
	}
	public HealthState getParalyzedState() {
		// TODO Auto-generated method stub
		return this.paralyzedState;
	}
	public void setParalyzedState(Paralyzed paralyzedState)
	{
		this.paralyzedState=paralyzedState;
	}
	public void setCaracteristica(Caracteristicas caracteristica) {
		this.caracteristica = caracteristica;
	}
	public Caracteristicas getCaracteristica()
	{
		return this.caracteristica;
	}
	public String toString()
	{
		return "Name: "+this.name+" Strategy: "+this.strategy.toString();
	}
	public Integer getVidaInicial() {
		return this.vidaInicial;
	}
	public void addFuerza(Integer fuerza)
	{
		this.fuerza+=fuerza;
	}
	public void addAgilidad(Integer agilidad)
	{
		this.agilidad+=agilidad;
	}
	public void addIntelecto(Integer intelecto)
	{
		this.intelecto+=intelecto;
	}
	
}
