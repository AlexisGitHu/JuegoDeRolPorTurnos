package com.utad.mais.proyectoFinal.pattern.decorator;

public class PocionComponentDecorator implements ArmaComponentDecorator
{
	//Componente sobre el que se a�adir� la espada
	private ArmaComponent armaComponent;
	
	//Seteamos la forma en que afectar� la espada a las estad�sticas del jugador
	private Integer fuerza = 0;
	private Integer agilidad = 0;
	private Integer intelecto = this.getStat();
	
	public PocionComponentDecorator(ArmaComponent armaComponent)
	{
		super();
		this.armaComponent = armaComponent;
	}
	
	//Devolvemos el componente sobre el que se ha a�adido la pocion
	@Override
	public ArmaComponent getArmaComponent()
	{
		return armaComponent;
	}

	//Devolvemos la descripcion m�s este nuevo componente
	@Override
	public String getDesc() 
	{
		return this.armaComponent.getDesc() + ", Misteriosa pocion (50)";
	}
	
	//Mostramos de qu� forma afectar� a su estad�stica
	@Override
	public Integer getStat() 
	{
		return 50;
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
	
	//M�todo para quitar un cierto equipamiento
	@Override
	public ArmaComponent removeArma(ArmaComponent arma)
	{
		if(arma instanceof ArmaComponentDecorator) //Comprobamos que sea un decorador
        {
            if(this.getClass().equals(arma.getClass())) //En el caso de que el equipamiento sea �l mismo 
            {
                return this.armaComponent; //Devolvemos el equipamiento sobre el que se ha a�adido
            }
            else
            {
                this.armaComponent = this.armaComponent.removeArma(arma); //Seteamos el componente sin el arma (de forma recursiva)
                return this;
            }
        }
        else  //Si es el componente base se devuelve a s� mismo
        {
            return this;
        }
	}
}
