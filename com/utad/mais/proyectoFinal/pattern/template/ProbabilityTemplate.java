package com.utad.mais.proyectoFinal.pattern.template;

import com.utad.mais.proyectoFinal.pattern.strategy.ActivityStrategy;

public interface ProbabilityTemplate 
{
	/*
	 * Interfaz m�s abstracta del patr�n template method. Permite ejecutar los diferentes m�todos que incorpora el template.
	 * Permite la escalabilidad ya que se podr�an utilizar varios templates distintos sin necesidad de modificar la llamada inicial.
	 */
	public ActivityStrategy applyTemplateMethod();
}
