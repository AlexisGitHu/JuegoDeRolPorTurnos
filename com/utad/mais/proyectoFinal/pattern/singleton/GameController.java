package com.utad.mais.proyectoFinal.pattern.singleton;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

import com.utad.mais.proyectoFinal.interfazGrafica.*;
import com.utad.mais.proyectoFinal.pattern.abstractFactory.*;
import com.utad.mais.proyectoFinal.pattern.decorator.Equipamiento;
import com.utad.mais.proyectoFinal.pattern.decorator2.MultiplicadorNormal;
import com.utad.mais.proyectoFinal.pattern.state.Paralyzed;
import com.utad.mais.proyectoFinal.pattern.strategy.*;
import com.utad.mais.proyectoFinal.pattern.template.*;
import com.utad.mais.proyectoFinal.personajes.*;

public class GameController 
{
	public static GameController gameController = new GameController(EnemyFactoryWorld1.getInstance());
	private AbstractEnemyFactory abstractEnemyFactory; //Atributo contexto del patr�n abstract Factory
	private static Integer enemyNumber; //N�mero de enemigos actuales del mundo.
	private static final Integer totalEnemies = 11;
	private static Integer puntosTotales; //Atributo que tiene en cuenta la puntuaci�n del jugador.
	private static Integer contadorEnemigosMatados; //Atributo que tiene en cuenta el n�mero de enemigos matados.
	private Personaje jugador; //Atributo jugador
	private Calculadora calculadora = Calculadora.getInstance(); //Instancia la calculadora
	private ProbabilityTemplate strategyToApply; //Atributo del contexto del Template Method
	private List<Enemy> enemigos; //Lista de enemigos actuales del mundo.
	private World actualWorld;//Mundo actual en el que se encuentra el juego.
	private InterfazGrafica gui = InterfazGrafica.getInstance(); //Instancia la interfaz grafica
	
	//Constructor por par�metros del GameController, solo necesita que se le pase por defecto la factor�a.
	private GameController(AbstractEnemyFactory abstractEnemyFactory)
	{
		this.abstractEnemyFactory= abstractEnemyFactory;
		this.strategyToApply=new RandomProbabilityGeneratorFair();
		this.setEnemigos(new ArrayList<Enemy>());
		this.actualWorld=World.LEVEL1;
		GameController.enemyNumber=0;
		GameController.puntosTotales=0;
		GameController.contadorEnemigosMatados=0;
		this.jugador=new Jugador();
		
		//Hacemos visible todas las ventanas
    	this.gui.getLogWindow().setVisible(true);
    	this.gui.getStatsWindow().setVisible(true);
    	this.gui.getEnemyWindow().setVisible(true);
    	this.gui.getEnemyPicture().setVisible(true);
    	this.gui.getPlayerPicture().setVisible(true);
    	
    	this.gui.setJugador((Jugador)this.jugador); //Seteamos el jugador
    	
    	//Seteamos las imagenes para los botones para poder diferenciar entre mundos
    	this.setImagesForButtons();
    	
    	//Seteamos la imagen para el jugador
    	this.setImageForPlayer();
    	
    	this.gui.a�adirTextoLog("******************\nMUNDO 1\n******************\n");
	}
	
	public static GameController getInstance()
	{
		return gameController;
	}
	public static void setInstance(GameController gameController)
	{
		GameController.gameController=gameController;
	}
	public AbstractEnemyFactory getAbstractFactory() 
	{
		return this.abstractEnemyFactory;
	}
	//M�todo que cambia la factor�a que se debe usar para la creaci�n de enemigos y llama al m�todo que permite cambiar el mundo
	public void setAbstractFactory(AbstractEnemyFactory abstractEnemyFactory) 
	{
		this.abstractEnemyFactory = abstractEnemyFactory;
		this.changeWorld();
	}
	private void changeWorld() //M�todo que permite cambiar el mundo actual.
	{
		if(GameController.getInstance().getAbstractFactory().getClass().equals(EnemyFactoryWorld1.class))
		{
			this.actualWorld=World.LEVEL1;
		}
		else if(GameController.getInstance().getAbstractFactory().getClass().equals(EnemyFactoryWorld2.class))
		{
			this.actualWorld=World.LEVEL2;
		}
		else
		{
			this.actualWorld=World.LEVEL3;
		}
		
		//Seteamos las imagenes para los botones en caso de cambiar de mundo
		this.setImagesForButtons();
	}
	public Personaje getJugador() 
	{
		return jugador;
	}
	public void setJugador(Personaje jugador) 
	{
		this.jugador = jugador;
	}
	public Calculadora getCalculadora() 
	{
		return calculadora;
	}
	public void setCalculadora(Calculadora calculadora) 
	{
		this.calculadora = calculadora;
	}
	//M�todo que delega por composici�n la creaci�n de FelBats a abstract factory
	public FelBat createFelBat(ActivityStrategy strategy)
	{
		return this.abstractEnemyFactory.createFelBat(strategy);
	}
	//M�todo que delega por composici�n la creaci�n de Infernals a  abstract factory
	public Infernal createInfernal(ActivityStrategy strategy)
	{
		return this.abstractEnemyFactory.createInfernal(strategy);
	}
	//M�todo que delega por composici�n la creaci�n de Inquisitors a abstract factory
	public Inquisitor createInquisitor(ActivityStrategy strategy)
	{
		return this.abstractEnemyFactory.createInquisitor(strategy);
	}
	//M�todo que delega por composici�n la implementaci�n del factory method de abstract factory
	public Enemy createEnemy(ActivityStrategy strategy)
	{
		return this.abstractEnemyFactory.createEnemy(strategy);
	}
	//M�todo que delega por composici�n la creaci�n de Bosses a abstract factory
	public Boss createBoss(ActivityStrategy strategy)
	{
		return this.abstractEnemyFactory.createBoss(strategy);
	}
	
	public void setImagesForButtons()
	{
		//Guardamos en variables las imagenes de cada boton
		Image atacar = new ImageIcon(this.getClass().getResource("/Imagenes/world"+this.actualWorld.getLevel()+"/Atacar.png")).getImage();
    	Image defenderme = new ImageIcon(this.getClass().getResource("/Imagenes/world"+this.actualWorld.getLevel()+"/Defenderme.png")).getImage();
    	Image curarme = new ImageIcon(this.getClass().getResource("/Imagenes/world"+this.actualWorld.getLevel()+"/Curarme.png")).getImage();
    	Image equiparme = new ImageIcon(this.getClass().getResource("/Imagenes/world"+this.actualWorld.getLevel()+"/Equiparme.png")).getImage();
    	
    	//Seteamos el icono del boton
    	this.gui.setButtons(new ImageIcon(atacar), new ImageIcon(defenderme), new ImageIcon(curarme), new ImageIcon(equiparme));
	}
	
	public void setImageForEnemy(String name)
	{
		ImageIcon imagen;
		if(name != "Boss") //En caso de no ser boss, la carpeta de la imagen donde se encuentra el enemigo es distinta de la de los bosses
			imagen = new ImageIcon(this.getClass().getResource("/Imagenes/"+name+".jpg"));
		else //Si es boss, tendremos que acceder a la carpeta del mundo actual y elegir el boss, ya que cada mundo tendr� un Boss distinto
			imagen = new ImageIcon(this.getClass().getResource("/Imagenes/world"+this.actualWorld.getLevel()+"/Boss.jpg"));
		
		//Seteamos la imagen del enemigo
		this.gui.setImageEnemy(imagen);
	}
	
	public void setImageForPlayer()
	{
		//Seteamos la imagen del jugador
		ImageIcon imagen = new ImageIcon(this.getClass().getResource("/Imagenes/Jugador.png"));
		this.gui.setImagePlayer(imagen);
	}
	
	public void play() //m�todo fachada de la clase gameController, solo se necesita ejecutar este m�todo para empezar a jugar.
	{
		this.generateEnemies(); //generamos los enemigos del mundo actual.
		
		while(enemyNumber!=0 || this.actualWorld!=World.LEVEL3 ) //Bucle que comprueba que no haya terminado el juego
		{
			this.checkPlayerWorld(); //M�todo que comprueba y cambia el mundo en el que se encuentra el jugador en funci�n de los enemigos matados.
			
			this.setImageForEnemy(this.enemigos.get(contadorEnemigosMatados).getName()); //Seteamos la imagen del enemigo
			
			System.out.println(this.enemigos.get(contadorEnemigosMatados).getName()+" se enfrenta con "+this.jugador.getName());
			this.gui.a�adirTextoLog("\n"+this.enemigos.get(contadorEnemigosMatados).getName()+" se enfrenta con "+this.jugador.getName() + "\n"); //Anexamos al log un texto
			
			this.mostrarTodo(); //Mostramos las estad�sticas de todos los personajes actuales.
			
			this.applyEnemyCommand();//Llamamos al m�todo que se encarga de decirle al enemigo que realice su acci�n
			
			this.applyPlayerCommand();//Llamos al m�todo que se encarga de decirle al jugador que realice su acci�n
		}
		
		
		if(this.continuarJugando()) //Condicional que llama al m�todo que permite elegir al jugador si continua jugando
		{
			this.play(); //Llamada al m�todo recursivo play que permite seguir jugando con un incremento de la dificultad.
		}
		else
		{
			this.victory();//Llamada al m�todo que permite poner fin al juego indicando la puntuaci�n y si ha ganado.
		}
		
	}
	public ProbabilityTemplate getStrategyToApply() 
	{
		return strategyToApply;
	}
	public void setStrategyToApply(ProbabilityTemplate strategyToApply) 
	{
		this.strategyToApply = strategyToApply;
	}
	public static Integer getEnemyNumber() 
	{
		return enemyNumber;
	}
	public static void setEnemyNumber(Integer enemyNumber) 
	{
		GameController.enemyNumber = enemyNumber;
	}
	public static void addEnemy() //M�todo que a�ade enemigos al contador de enemigos actuales
	{
		enemyNumber++;
	}
	public List<Enemy> getEnemigos() 
	{
		return enemigos;
	}
	public void setEnemigos(List<Enemy> enemigos) 
	{
		this.enemigos = enemigos;
	}
	private void generateEnemies() //M�todo que se encarga generar los enemigos
	{
		if(!enemigos.isEmpty()) //Condicional que borra los elementos enemigos actuales de la lista de enemigos y pone el contador a 0 de enemigos matados a 0.
		{
			enemigos.clear();
			contadorEnemigosMatados=0;
		}
		for(int i =0; i<totalEnemies-1;i++) //Bucle que se encarga de generar los 10 enemigos del mundo de forma aleatoria
		{
			enemigos.add(this.createEnemy(this.strategyToApply.applyTemplateMethod()));
		}
		enemigos.add(this.createBoss(this.strategyToApply.applyTemplateMethod())); //M�todo que se encarga de generar al boss del mundo actual.
	}
	private boolean continuarJugando() //M�todo que se encarga de preguntar al player si sigue jugando o no
	{
		this.reiniciarEnemigo();
		
		int accion = -1;
		boolean continuePlaying = false;
		System.out.println("�Desea continuar jugando?\nPulse 1 para continuar.\nPulse 2 para finalizar.");
		this.gui.muestraPosibilidadFinal(); //Mostramos las distintas posibilidades de final
		
		//Obtenemos la opcion de finalizar y controlamos los errores
		if(this.gui.getOpcionFinalizar() > 3 || this.gui.getOpcionFinalizar() < 0)
		{
			this.gui.setTextoLog("ERROR: Opcion no valida, seleccione entre 1 y 2\n"); //Seteamos el texto de logs
			this.continuarJugando(); //Volvemos a pedir el input hasta q sea v�lido
		}
		
		accion = this.gui.getOpcionFinalizar();
				
		switch(accion) 
		{
			case 1: //En caso de ser 1, reiniciamos todo
				continuePlaying=true;
				//Reseteo e incremento de la dificultad
				this.setStrategyToApply(new RandomProbabilityGeneratorUnfair());
				EnemyFactoryWorld1.getInstance().setFactoryComponent(
						new MultiplicadorNormal(EnemyFactoryWorld1.getInstance().getFactoryComponent()));
				EnemyFactoryWorld2.getInstance().setFactoryComponent(
						new MultiplicadorNormal(EnemyFactoryWorld2.getInstance().getFactoryComponent()));
				EnemyFactoryWorld3.getInstance().setFactoryComponent(
						new MultiplicadorNormal(EnemyFactoryWorld3.getInstance().getFactoryComponent()));
				this.setAbstractFactory(EnemyFactoryWorld1.getInstance());
				this.setActualWorld(World.LEVEL1); //Seteamos de nuevo el mundo 1
				this.gui.setTextoLog("******************\nMUNDO 1\n****************\n"); //Seteamos el texto de los logs a mundo 1
				((Jugador)this.jugador).setequipamiento(new Equipamiento());//resetear equipamiento

				break;
			case 2: //En caso de ser 2, no queremos seguir.
				continuePlaying=false;
				break;
			default:
				break;
				
		}
		
		return continuePlaying;
	}
	
	private void checkPlayerWorld() //M�todo que se encarga de comprobar si se debe cambiar el mundo o no.
	{
		if(GameController.enemyNumber==0 && this.actualWorld==World.LEVEL1)
		{
			System.out.println("Cambiando de mundo al 2");
			this.gui.setTextoLog("******************\nMUNDO 2\n******************\n"); //Seteamos el texto de logs al mundo2
			this.actualWorld=World.LEVEL2;
			this.setAbstractFactory(EnemyFactoryWorld2.getInstance());
			this.generateEnemies();
		}
		else if(GameController.enemyNumber==0 && this.actualWorld==World.LEVEL2)
		{
			System.out.println("Cambiando de mundo al 3");
			this.gui.setTextoLog("******************\nMUNDO 3\n******************\n"); //Seteamos el texto de logs al mundo3
			this.actualWorld=World.LEVEL3;
			this.setAbstractFactory(EnemyFactoryWorld3.getInstance());
			this.generateEnemies();
		}
	}
	private void applyPlayerCommand()
	{
		if(this.isParalizado(this.jugador)==false)
		{
			int accion;
			
			synchronized(this.gui) //Nos sincronizamos con la interfaz gr�fica para esperar una respuesta hasta que se nos notifique
			{
				try 
				{
					this.gui.wait();
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				accion = this.gui.getOpcionGeneral(); //Una vez notificados obtenemos la opcion elegida
			}
			
			switch(accion)
			{
				case 1:
					this.gui.a�adirTextoLog(this.jugador.getName()+" atac� a "+this.enemigos.get(contadorEnemigosMatados).getName() + "\n"); //Anexamos el texto de logs
					this.calculadora.calculaDa�oRecibido(this.jugador, this.enemigos.get(contadorEnemigosMatados)); //Calculamos el da�o recibido por el enemigo
					break;
				case 2:
					this.gui.a�adirTextoLog(this.jugador.getName()+" se cur� a s� mismo\n"); //Anexamos el texto de logs
					this.calculadora.calculaCuraRecibida(this.jugador); //Calculamos la cura recibida
					break;
				case 3:
					if(this.gui.getOpcionEquipacion() < 4 && this.gui.getOpcionEquipacion() > 0) //Control de errores en la opcion del equipamiento
					{
						Integer equipamiento = this.gui.getOpcionEquipacion(); //Obtenemos el equipamiento
						((Jugador)this.jugador).equiparseObjeto(equipamiento); //Equipamos al jugador con el equipamiento debido
						this.gui.a�adirTextoLog("El equipo actual de "+jugador.getName()+" es: "+((Jugador)this.jugador).getequipamiento().getDesc() + "\n"); //Anexamos el texto de logs
					}
					else
					{
						this.gui.a�adirTextoLog("Equipamiento no valido...\n"); //Anexamos el texto de logs
					}
					break;
				case 4:
					this.calculadora.calculaDefendido(this.jugador, this.enemigos.get(contadorEnemigosMatados)); //Calculamos el da�o al enemigo y cura recibida
					break;
				default:
					break;
			}
			this.checkEnemyState();
		}
	}
	
	private void applyEnemyCommand() //M�todo que se encarga de aplicar la acci�n de la estrategia dele enemigo y comprobar el estado del jugador acto seguido
	{
		if(this.isParalizado(this.enemigos.get(contadorEnemigosMatados))==false) //Si el enemigo est� paralizado, entonces no se aplica ninguna acci�n
		{
			this.enemigos.get(contadorEnemigosMatados).applyStrategy(this.jugador);
		}
		this.checkPlayerState();//M�todo que se encarga de comprobar el estado del jugador.
		this.mostrarTodo(); //Mostramos todas las estad�sticas tanto del jugador como del enemigo
	}
	
	private void checkEnemyState() //M�todo que se encarga de comprobar el estado del enemigo
	{
		System.out.println("Estado enemigo: ");
		this.enemigos.get(contadorEnemigosMatados).getActualState().process();

	}
	
	private void checkPlayerState() //M�todo que se encarga de comprobar el estado del jugador
	{
		System.out.println("Estado jugador: ");
		
		this.jugador.getActualState().process();
	}
	
	private void mostrarTodo()
	{
		//Seteamos el nombre, estad�sticas, estado del jugador y n�mero de enemigos matados en el mundo X
		this.gui.setTextoStats("Nombre: " + this.jugador.getName()+"\nVida: "+this.jugador.getVida()+"\n");
		this.gui.a�adirTextoStats("Agilidad: " + this.jugador.getAgilidad() + "\n"+
				"Fuerza: " + this.jugador.getFuerza() + "\n"+
				"Intelecto: " + this.jugador.getIntelecto() + "\n");
		this.gui.a�adirTextoStats("Estado jugador: "+this.jugador.getActualState().getClass().getSimpleName()+"\n");

		this.gui.a�adirTextoStats("Enemigos matados en el mundo: " + contadorEnemigosMatados + "\n");
		
		//Seteamos el nombre, estad�sticas y estado del enemigo
		this.gui.setTextoEnemy("Nombre: " +this.enemigos.get(contadorEnemigosMatados).getName()+"\nVida: "+this.enemigos.get(contadorEnemigosMatados).getVida()+ "\n");
		this.gui.a�adirTextoEnemy("Estado actual: " +this.enemigos.get(contadorEnemigosMatados).getActualState().getClass().getSimpleName()+ "\n");
		
	}
	
	private void reiniciarEnemigo()
	{
		this.enemigos.get(contadorEnemigosMatados - 1).setVida(0); //Seteamos la vida del �ltimo enemigo a 0
		
		//Actualizamos las estad�sticas del enemigo
		this.gui.setTextoEnemy("Nombre: " +this.enemigos.get(contadorEnemigosMatados-1).getName()+"\nVida: "+this.enemigos.get(contadorEnemigosMatados-1).getVida()+ "\n");
		this.gui.a�adirTextoEnemy("Estado actual: " +this.enemigos.get(contadorEnemigosMatados-1).getActualState().getClass().getSimpleName()+ "\n");
	}
	
	public void gameOver()
	{
		this.jugador.setVida(0); //Seteamos la vida del jugador a 0
		this.mostrarTodo(); //Mostramos todas las estad�sitcas actulaizadas
		
		this.gui.gameOver(); //Mostramos el game Over de la interfaz gr�fica
		System.out.println(this.jugador.getName()+" has sido derrotado por las hordas del infierno");
		System.out.println("Puntuaci�n total: "+GameController.getPuntosTotales());
		System.out.println("----------------------------------------------\n"
											+ "GAME OVER\n"
						  +"-----------------------------------------------");
		
		System.exit(0); //Salimos del programa
	}
	private boolean isParalizado(Personaje personaje) //M�todo que se encarga de comprobar si el personaje est� paralizado o no
	{
		if(personaje.getActualState().getClass().equals(Paralyzed.class))
		{
			System.out.println(personaje.getName()+" est� paralizado y no pudo realizar acci�n");
			this.gui.a�adirTextoLog(personaje.getName()+" est� paralizado y no pudo realizar acci�n\n"); //Anexamos el texto en el log
			return true;
		}
		else
		{
			return false;
		}
	}
	private void victory()
	{
		this.gui.victory(); //Mostramos el victory de la interfaz gr�fica
		
		System.out.println("----------------------------------------------\n"
											+ "HAS GANADO\n"
							+"-----------------------------------------------");
		System.out.println(this.jugador.getName()+" ERES UN AUT�NTICO DOOM SLAYER");
		System.out.println("Puntuaci�n total: "+GameController.getPuntosTotales());
		
		System.exit(0); //Salimos del programa
	}
	public static Integer getPuntosTotales() 
	{
		return puntosTotales;
	}
	public static void setPuntosTotales(Integer puntosTotales)
	{
		GameController.puntosTotales = puntosTotales;
	}
	public static void addPuntosTotales(Integer puntosASumar)
	{
		GameController.puntosTotales+=puntosASumar;
	}
	public static Integer getContadorEnemigosMatados()
	{
		return GameController.contadorEnemigosMatados;
	}
	public static void setContadorEnemigosMatados(Integer contadorEnemigosMatados)
	{
		GameController.contadorEnemigosMatados=contadorEnemigosMatados;
	}
	public static void addEnemigoMatado()
	{
		GameController.contadorEnemigosMatados++;
	}
	public static void restaEnemyNumber() //M�todo que se encarga de restar el n�mero de enemigos actuales del mundo
	{
		GameController.enemyNumber--;
	}
	public void setActualWorld(World actualWorld)
	{
		this.actualWorld=actualWorld;
		this.setImagesForButtons(); //Seteamos las imagenes de los botones en caso de que se actualice el mundo
	}
	public World getActualWorld()
	{
		return this.actualWorld;
	}
	
}
