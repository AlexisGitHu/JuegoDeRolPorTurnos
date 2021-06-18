package com.utad.mais.proyectoFinal.personajes;

import com.utad.mais.proyectoFinal.pattern.state.*;
public interface Personaje 
{
	/*
	 * Interfaz que implementa los métodos que caracterizan a todos los personajes (jugadores o enemigos).
	 * 
	 * Cualquier instancia de un personaje debe implementar la interfaz personaje.
	 * 
	 * Los métodos caracterizadores son, en general, los getters y setters de todos sus atributos, 
	 * métodos especiales que modifican sus estadísticas base y los getters de su estado actual y todos los estados posibles
	 * puesto que el personaje es el contexto del patrón State.
	 */
	public Integer getFuerza();
	public void setFuerza(Integer fuerza);
	public Integer getAgilidad();
	public void setAgilidad(Integer agilidad);
	public Integer getIntelecto();
	public void setIntelecto(Integer intelecto);
	public Integer getResistenciaMagica();
	public void setResistenciaMagica(Integer resistenciaMagica);
	public Integer getResistenciaFisica();
	public void setResistenciaFisica(Integer resistenciaFisica);
	public Integer getVida();
	public void setVida(Integer vida);
	public void addFuerza(Integer fuerza);
	public void addAgilidad(Integer agilidad);
	public void addIntelecto(Integer intelecto);
	public Integer getVidaInicial();
	public HealthState getActualState();
	public void setActualState(HealthState actualState);
	public String getName();
	public HealthState getSeverelyInjuredState();
	public HealthState getInjuredState();
	public HealthState getDeadState();
	public HealthState getHealthyState();
	public HealthState getParalyzedState();
}
