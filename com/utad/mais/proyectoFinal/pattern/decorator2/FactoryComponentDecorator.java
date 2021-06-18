package com.utad.mais.proyectoFinal.pattern.decorator2;

public interface FactoryComponentDecorator extends FactoryComponent
{
	/*
	 * Interfaz decorador del patr�n decorador2. 
	 * 
	 * Incluye los m�todos que aseguran que cada objeto decorador pueda devolver la instancia del objeto que est� decorando.
	 */
	public FactoryComponent getFactoryComponent();
}
