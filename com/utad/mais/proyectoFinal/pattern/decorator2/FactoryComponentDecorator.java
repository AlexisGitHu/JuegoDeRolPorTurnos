package com.utad.mais.proyectoFinal.pattern.decorator2;

public interface FactoryComponentDecorator extends FactoryComponent
{
	/*
	 * Interfaz decorador del patrón decorador2. 
	 * 
	 * Incluye los métodos que aseguran que cada objeto decorador pueda devolver la instancia del objeto que está decorando.
	 */
	public FactoryComponent getFactoryComponent();
}
