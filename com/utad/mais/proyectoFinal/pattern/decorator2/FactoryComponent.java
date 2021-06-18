package com.utad.mais.proyectoFinal.pattern.decorator2;

public interface FactoryComponent 
{
	/*
	 * Interfaz base del patr�n decorador 2. 
	 * Incluye los m�todos que afectan a las estad�sticas del jugador para incrementarlas seg�n sea pertinente.
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
