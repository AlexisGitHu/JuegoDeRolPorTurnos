package com.utad.mais.proyectoFinal.pattern.template;

import com.utad.mais.proyectoFinal.pattern.strategy.ActivityStrategy;

public interface ProbabilityTemplate 
{
	/*
	 * Interfaz más abstracta del patrón template method. Permite ejecutar los diferentes métodos que incorpora el template.
	 * Permite la escalabilidad ya que se podrían utilizar varios templates distintos sin necesidad de modificar la llamada inicial.
	 */
	public ActivityStrategy applyTemplateMethod();
}
