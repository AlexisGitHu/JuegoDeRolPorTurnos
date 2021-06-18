package com.utad.mais.proyectoFinal.pattern.decorator;

public class EspadaComponentDecorator implements ArmaComponentDecorator
{
	//Componente sobre el que se añadirá la espada
	private ArmaComponent armaComponent;
	
	//Seteamos la forma en que afectará la espada a las estadísticas del jugador
	private Integer fuerza = this.getStat();
	private Integer agilidad = 0;
	private Integer intelecto = 0;
	
	public EspadaComponentDecorator(ArmaComponent armaComponent)
	{
		super();
		this.armaComponent = armaComponent;
	}
	
	//Devolvemos el componente sobre el que se ha añadido la espada
	@Override
	public ArmaComponent getArmaComponent()
	{
		return this.armaComponent;
	}

	//Devolvemos la descripcion más este nuevo componente
	@Override
	public String getDesc() 
	{
		return this.armaComponent.getDesc() + ", Gran espada (150)";
	}

	//Mostramos de qué forma afectará a su estadística
	@Override
	public Integer getStat() 
	{
		return 150;
	}
	
	//GETTERS Y SETTERS
	@Override
	public Integer getFuerza() 
	{
		return this.fuerza+this.armaComponent.getFuerza(); //Recursivo al igual que la descripcion
	}

	@Override
	public Integer getAgilidad() 
	{
		return this.agilidad+this.armaComponent.getAgilidad(); //Recursivo al igual que la descripcion
	}

	@Override
	public Integer getIntelecto() 
	{
		return this.intelecto+this.armaComponent.getIntelecto(); //Recursivo al igual que la descripcion
	}

	@Override
	public void setFuerza(Integer stat)
	{
		this.fuerza = stat;
	}

	@Override
	public void setAgilidad(Integer stat)
	{
		this.agilidad = stat;
	}

	@Override
	public void setIntelecto(Integer stat)
	{
		this.intelecto = stat;
	}

	//Método para quitar un cierto equipamiento
	@Override
	public ArmaComponent removeArma(ArmaComponent arma)
	{
		if(arma instanceof ArmaComponentDecorator) //Comprobamos que sea un decorador
        {
            if(this.getClass().equals(arma.getClass())) //En el caso de que el equipamiento sea él mismo 
            {
                return this.armaComponent; //Devolvemos el equipamiento sobre el que se ha añadido
            }
            else
            {
                this.armaComponent = this.armaComponent.removeArma(arma); //Seteamos el componente sin el arma (de forma recursiva)
                return this;
            }
        }
        else  //Si es el componente base se devuelve a sí mismo
        {
            return this;
        }
	}
}
