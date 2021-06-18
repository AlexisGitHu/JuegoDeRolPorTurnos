package com.utad.mais.proyectoFinal.interfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.utad.mais.proyectoFinal.pattern.singleton.*;
import com.utad.mais.proyectoFinal.personajes.*;


public class InterfazGrafica extends JFrame implements ActionListener
{
	private static InterfazGrafica gui = new InterfazGrafica();
	
	private JFrame logWindow = new JFrame();
	private JFrame statsWindow = new JFrame();
	private JFrame enemyWindow = new JFrame();
	private JFrame enemyPicture = new JFrame();
	private JFrame playerPicture = new JFrame();
	
	private JLabel enemyPic = new JLabel();;
	private JLabel playerPic = new JLabel();
	
	private JPanel panel_izq;
	private JTextArea areaLogs;
	private JTextArea areaStats;
	private JTextArea areaEnemy;

	private JButton atacar = new JButton("Atacar");
	private JButton curarme = new JButton("Curarme");
	private JButton equiparme = new JButton("Equiparme");
	private JButton defenderme = new JButton("Defenderme");
	
	private Jugador jugador;
	
	private Integer opcionGeneral = 0;
	private Integer opcionEquipacion = 0;
	private Integer opcionFinalizar = 0;
	
	private static final long serialVersionUID = 1L; //se añade para quitar el warning
	private static final int width = 500; //900
	private static final int height = 500; //700
	private static final int coord_x = 200;
	private static final int coord_y = 100;

	private InterfazGrafica()
	{
		this.logWindow.setTitle("Ventana de Acciones");
		this.logWindow.setSize(width,height);
		this.logWindow.setLocation(coord_x*2,coord_y);
		this.logWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.statsWindow.setTitle("Ventana de Jugador");
		this.statsWindow.setSize(width*2/3,height/2);
		this.statsWindow.setLocation(coord_x/2 - 20,coord_y);
		this.statsWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.enemyWindow.setTitle("Ventana de enemigo");
		this.enemyWindow.setSize(width*2/3,height/2);
		this.enemyWindow.setLocation(coord_x/2 + width*8/5 - 12,coord_y);
		this.enemyWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.enemyPicture.setTitle("Enemigo");
		this.enemyPicture.setSize(width*2/3, height/2);
		this.enemyPicture.setLocation(coord_x/2 + width*8/5 - 12,coord_y+height/2);
		this.enemyPicture.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.enemyPicture.add(this.enemyPic);
		
		this.playerPicture.setTitle("Jugador");
		this.playerPicture.setSize(width*2/3, height/2);
		this.playerPicture.setLocation(coord_x/2 - 20,coord_y+height/2);
		this.playerPicture.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.playerPicture.add(this.playerPic);
		
		this.createPannelsAndAreas();
		
		this.areaLogs.setText("Log de acciones:\n");
		this.areaStats.setText("Stats:\n");
		this.areaEnemy.setText("Enemy stats:\n");
	}
	
	public static InterfazGrafica getInstance()
	{
		return gui;
	}
	
	private void createPannelsAndAreas()
	{
		this.panel_izq = this.createPanIzq();
		
		this.areaLogs = this.createTextArea();
		JScrollPane scroll = new JScrollPane(this.areaLogs);
		
		this.areaStats = this.createTextArea();
		
		this.areaEnemy = this.createTextArea();
		
		this.logWindow.getContentPane().setLayout(new BorderLayout());
		this.logWindow.getContentPane().add(this.panel_izq, BorderLayout.CENTER);
		this.logWindow.getContentPane().add(scroll, BorderLayout.NORTH);
		
		this.statsWindow.getContentPane().setLayout(new BorderLayout());
		this.statsWindow.getContentPane().add(this.areaStats);
		
		this.enemyWindow.getContentPane().setLayout(new BorderLayout());
		this.enemyWindow.getContentPane().add(this.areaEnemy);
	}
	
	private void addButtonsCen(JPanel panel)
	{
		this.atacar.addActionListener(this);
		
		this.curarme.addActionListener(this);
		
		this.equiparme.addActionListener(this);
		
		this.defenderme.addActionListener(this);
		
		panel.add(atacar);
		panel.add(curarme);
		panel.add(equiparme);
		panel.add(defenderme);
	}
	
	private JPanel createPanIzq()
	{
		JPanel panel_izq = new JPanel();
		panel_izq.setLayout(new GridLayout(2,2));
		this.addButtonsCen(panel_izq);
		
		return panel_izq;
	}
	
	private JTextArea createTextArea()
	{
		JTextArea area = new JTextArea();
		area.setColumns(20);
		area.setRows(20);
		area.setBorder(BorderFactory.createLineBorder(Color.black));

		return area;
	}
	
	public JTextArea getArea()
	{
		return this.areaLogs;
	}
	
	public JFrame getLogWindow()
	{
		return this.logWindow;
	}
	
	public JFrame getStatsWindow()
	{
		return this.statsWindow;
	}
	
	public JFrame getEnemyWindow()
	{
		return this.enemyWindow;
	}
	
	public void setButtons(ImageIcon atacar, ImageIcon defenderme, ImageIcon curarme, ImageIcon equiparme)
	{
		this.atacar.setIcon(atacar);
		
		this.defenderme.setIcon(defenderme);
		
		this.curarme.setIcon(curarme);
		
		this.equiparme.setIcon(equiparme);
	}
	
	public JFrame getEnemyPicture()
	{
		return enemyPicture;
	}

	public void setEnemyPicture(JFrame enemyPicture)
	{
		this.enemyPicture = enemyPicture;
	}

	public JFrame getPlayerPicture() 
	{
		return playerPicture;
	}

	public void setPlayerPicture(JFrame playerPicture) 
	{
		this.playerPicture = playerPicture;
	}
	
	public Jugador getJugador()
	{
		return jugador;
	}

	public void setJugador(Jugador jugador)
	{
		this.jugador = jugador;
	}
	
	public Integer getOpcionGeneral() 
	{
		return opcionGeneral;
	}
	
	public Integer getOpcionEquipacion() 
	{
		return opcionEquipacion;
	}
	
	public Integer getOpcionFinalizar() 
	{
		return opcionFinalizar;
	}
	
	public void añadirTextoLog(String text)
	{
		this.areaLogs.append(text);
	}
	
	public void setTextoLog(String text)
	{
		this.areaLogs.setText(text);
	}
	
	public void añadirTextoStats(String text)
	{
		this.areaStats.append(text);
	}
	
	public void setTextoStats(String text)
	{
		this.areaStats.setText(text);
	}
	
	public void añadirTextoEnemy(String text)
	{
		this.areaEnemy.append(text);
	}
	
	public void setTextoEnemy(String text)
	{
		this.areaEnemy.setText(text);
	}
	
	public void setImageEnemy(ImageIcon image)
	{
		this.enemyPic.setIcon(image);
	}
	
	public void setImagePlayer(ImageIcon image)
	{
		this.playerPic.setIcon(image);
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		JButton clickedButton = (JButton) event.getSource();
		
		synchronized(this)
		{
			switch(clickedButton.getText())
			{
				case "Atacar":	
					this.opcionGeneral = 1;
					break;
				case "Curarme":
					this.opcionGeneral = 2;
					break;
				case "Equiparme":
					this.muestraventanaEquiparme();
					this.opcionGeneral = 3;
					break;
				case "Defenderme":
					this.opcionGeneral = 4;
					break;
			}
			this.notify();
		}
	}
	
	private void muestraventanaEquiparme()
	{
		String posibilidades = JOptionPane.showInputDialog(null,"Pulse 1 para equiparse una espada.\nPulse 2 para equiparse una poción.\nPulse 3 para equiparse unas botas.");
		if(posibilidades != null && (posibilidades.equals("1") || posibilidades.equals("2") || posibilidades.equals("3")))
			this.opcionEquipacion = Integer.parseInt(posibilidades);
		else
			this.opcionEquipacion = -1;
	}
	
	public void muestraPosibilidadFinal()
	{
			String posibilidades = JOptionPane.showInputDialog(null, "¿Desea continuar jugando?\nPulse 1 para continuar.\nPulse 2 para finalizar.");
			
			if(posibilidades != null && (posibilidades.equals("1") || posibilidades.equals("2")))
				this.opcionFinalizar = Integer.parseInt(posibilidades);
			else
				this.opcionFinalizar = -1;	
	}
	
	public void gameOver()
	{
		this.setTextoLog(this.jugador.getName()+" has sido derrotado por las hordas del infierno\n"+
					"Puntuación total: " + GameController.getPuntosTotales()+
					"\n----------------------------------------------\n"+
					"GAME OVER\n" +
					"-----------------------------------------------");

		this.delay();
	}
	
	public void victory()
	{
		this.setTextoLog("----------------------------------------------\n"
					+ "HAS GANADO\n"
					+"-----------------------------------------------\n"
					+this.jugador.getName()+" ERES UN AUTÉNTICO DOOM SLAYER\n"
					+"Puntuación total: "+GameController.getPuntosTotales());
		
		this.delay();
	}
	
	private void delay()
	{
		try 
		{
			synchronized(this)
			{
				this.wait(2500);
			}
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
