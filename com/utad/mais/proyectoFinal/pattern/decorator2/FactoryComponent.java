package com.utad.mais.proyectoFinal.pattern.decorator2;

public interface FactoryComponent 
{
	/*
	 * Interfaz base del patrón decorador 2. 
	 * Incluye los métodos que afectan a las estadísticas del jugador para incrementarlas según sea pertinente.
	 */
	public Integer getVida();
	public void setVida(Integer vida);
	public Integer getFuerza();
	public void setFuerza(Integer fuerza);
	public Integer getIntelecto();
	public void setIntelecto(Integer intelecto);
	public Integer getAgilidad();
	public void setAgilidad(Integer agilidad);
}
