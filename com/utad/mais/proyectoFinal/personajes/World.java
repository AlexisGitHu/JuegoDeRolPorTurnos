package com.utad.mais.proyectoFinal.personajes;

public enum World 
{
	/*
	 * Clase enumerada World.
	 * 
	 * Instancia de forma parametrizada el factor de complejidad correspondiente al nivel o mundo al que pertenece, así como
	 * una referencia al nivel explícito en el que se encuentra.
	 * 
	 * Esta clase permite definir los distintos mundos por los que viaja el jugador de forma parametrizada, ya que estos mundos
	 * vienen establecidos por defecto.
	 * 
	 * Sus métodos solo incluyen los getters y los setters de sus atrivutos correspondientes.
	 */
	LEVEL1(1.0, 1),LEVEL2(1.2, 2),LEVEL3(1.3, 3);
	private Integer level;
	private Double factorComplejidad;
	private World(Double factorComplejidad, Integer level)
	{
		this.setFactorComplejidad(factorComplejidad);
		this.setLevel(level);
	}
	public Double getFactorComplejidad() {
		return this.factorComplejidad;
	}
	public void setFactorComplejidad(Double factorComplejidad) {
		this.factorComplejidad = factorComplejidad;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
}
