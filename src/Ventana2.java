import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Ventana2{
	
	private JFrame v1 = new JFrame();
	private JPanel panel, pnlComando, pnlAlgoritmos;
	private JRadioButton rbtnAleatorio, rbtnOrdenadoDecreciente, rbtnOrdenado;
	private JButton btnOrdenar, btnParar, btnCerrar;
	private JSlider sliderCantidad, sliderVelocidad;
	private Grafico_Selection panel1;
	private Grafico_Insertion panel2;
	private Grafico_Bubble panel3;
	private Grafico_Merge panel4;
	private Grafico_Heap panel5;
	private Thread t1, t2,t3,t4,t5;
	private int cant = 15;
	private int vel = 30;
	
	public Ventana2() {
		v1.setSize(1280, 720);
		v1.setLocationRelativeTo(null);
		v1.setTitle("Sorting Algorithms");
		v1.setResizable(false);
		v1.getContentPane().setBackground(Color.GRAY);
		v1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		iniciarComponentes();
	}
	
	private void iniciarComponentes() {
		colocarPaneles();
	}
	
	private void actualizarDatos() {
		int[] auxVector = new int[0];
		
		int auxCantidad = sliderCantidad.getValue();
		this.cant = auxCantidad;
		int auxVelocidad = sliderVelocidad.getValue();
		
		if(rbtnAleatorio.isSelected()) {
			auxVector = generarVectorAleatorio(auxCantidad);
		}else if(rbtnOrdenadoDecreciente.isSelected()) {
			auxVector = generarVectorDecreciente(auxCantidad);
		}else if(rbtnOrdenado.isSelected()) {
			auxVector = generarVectorCasiOrdenado(auxCantidad);
		}
		
		panel1.updateData(auxVector, auxVelocidad);
		panel1.update(panel1.getGraphics());
		panel2.updateData(auxVector, auxVelocidad);
		panel2.update(panel2.getGraphics());
		panel3.updateData(auxVector, auxVelocidad);
		panel3.update(panel3.getGraphics());
		panel4.updateData(auxVector, auxVelocidad);
		panel4.update(panel4.getGraphics());
		panel5.updateData(auxVector, auxVelocidad);
		panel4.update(panel5.getGraphics());
	}
	
	private void colocarPaneles() {
		panel = new JPanel(); //Creacion de un panel
		v1.getContentPane().add(panel);//Agregamos el panel a la ventana
		panel.setLayout(new BorderLayout());
		
		panelComandoLayout();
		
		panelAlgoritmosLayout();
	}
	
	private void panelComandoLayout(){
		pnlComando = new JPanel(new GridLayout(5, 1));
		
		Border brdComando = new TitledBorder(new EtchedBorder());
		pnlComando.setBorder(brdComando);
		
		JLabel lblTitle = new JLabel("Sorting Algorithm");
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setFont(new Font("lato", Font.BOLD, 24));
		lblTitle.setHorizontalAlignment(0);
		
		pnlComando.add(lblTitle);
		colocarOrdenDatos();
		colocarCantidadDatos();
		colocarVelocidad();
		colocarBotones();
		
		panel.add(pnlComando, BorderLayout.WEST);
	}
	
	private void panelAlgoritmosLayout() {
		pnlAlgoritmos = new JPanel(new GridLayout(2,2));
		int[] a = generarVectorAleatorio(sliderCantidad.getValue());
		//Panel 1
		Border brdPanel1 = new TitledBorder(new EtchedBorder(), "Selection");
		panel1= new Grafico_Selection(Arrays.copyOf(a, a.length), vel);
		panel1.setBorder(brdPanel1);
		
		//Panel 2
		Border brdPanel2 = new TitledBorder(new EtchedBorder(), "Insertion");
		panel2 = new Grafico_Insertion(Arrays.copyOf(a, a.length), vel);
		panel2.setBorder(brdPanel2);
		//Panel 3
		Border brdPanel3 = new TitledBorder(new EtchedBorder(), "Bubble");
		panel3 = new Grafico_Bubble(Arrays.copyOf(a, a.length), vel);
		panel3.setBorder(brdPanel3);
		//Panel 4
		Border brdPanel4 = new TitledBorder(new EtchedBorder(), "Merge");
		panel4 = new Grafico_Merge(Arrays.copyOf(a, a.length), vel);
		panel4.setBorder(brdPanel4);
		//Panel 5
		Border brdPanel5 = new TitledBorder(new EtchedBorder(), "Heap");
		panel5 = new Grafico_Heap(Arrays.copyOf(a, a.length), vel);
		panel5.setBorder(brdPanel5);
		//Insertamos los paneles hacia pnlAlgortihm
		pnlAlgoritmos.add(panel1);
		pnlAlgoritmos.add(panel2);
		pnlAlgoritmos.add(panel3);
		pnlAlgoritmos.add(panel4);
		pnlAlgoritmos.add(panel5);
		//Insertamos pnlAlgorithm en panel
		panel.add(pnlAlgoritmos, BorderLayout.CENTER);
		
	}
	
	private void colocarEtiquetas() {
		JLabel lblTitle,lblOrder, lblImage;
		
		lblImage = new JLabel();
		ImageIcon imagen = new ImageIcon("sortingAlgo.png");
		lblImage.setSize(200, 100);
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH));
		lblImage.setIcon(icono);
		
		pnlComando.add(lblImage);
	}
	
	private void colocarOrdenDatos() {
		JPanel pnlOrdenDatos = new JPanel();
		pnlOrdenDatos.setLayout(new GridLayout(3, 1));
		ButtonGroup btnGrpRadioBotones = new ButtonGroup();
		
		Border brdOrden = new TitledBorder(new EtchedBorder(), "Orden de datos");
		pnlOrdenDatos.setBorder(brdOrden);
		
		rbtnAleatorio = new JRadioButton("Aleatorio", true);
		rbtnAleatorio.setFont(new Font("Arial", Font.PLAIN, 18));
		rbtnOrdenadoDecreciente = new JRadioButton("Ordenado Decreciente", false);
		rbtnOrdenadoDecreciente.setFont(new Font("Arial", Font.PLAIN, 18));
		rbtnOrdenado = new JRadioButton("Casi Ordenado", false);
		rbtnOrdenado.setFont(new Font("Arial", Font.PLAIN, 18));
		
		btnGrpRadioBotones.add(rbtnAleatorio);
		btnGrpRadioBotones.add(rbtnOrdenadoDecreciente);
		btnGrpRadioBotones.add(rbtnOrdenado);
		
		rbtnAleatorio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pararThread();
				actualizarDatos();
			}
		});
		
		rbtnOrdenadoDecreciente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pararThread();
				actualizarDatos();
			}
		});
		
		rbtnOrdenado.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pararThread();
				actualizarDatos();
			}
		});
		
		pnlOrdenDatos.add(rbtnAleatorio);
		pnlOrdenDatos.add(rbtnOrdenadoDecreciente);
		pnlOrdenDatos.add(rbtnOrdenado);
		
		pnlComando.add(pnlOrdenDatos);
		
	}
	
	private void colocarCantidadDatos() {
		JPanel pnlCantidadDatos = new JPanel();
		
		Border brdCantidad = new TitledBorder(new EtchedBorder(), "Cantidad de datos");
		pnlCantidadDatos.setBorder(brdCantidad);
		
		sliderCantidad = new JSlider(JSlider.HORIZONTAL, 10, 50, cant);
		sliderCantidad.setPaintTicks(true);
		sliderCantidad.setMajorTickSpacing(5);
		sliderCantidad.setMinorTickSpacing(5);
		sliderCantidad.setPaintLabels(true);
		sliderCantidad.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent event) {
				pararThread();
				actualizarDatos();
			}
		});

		pnlCantidadDatos.add(sliderCantidad);
		pnlComando.add(pnlCantidadDatos);
	}
	
	
	private void colocarVelocidad() {
		JPanel pnlVelocidad = new JPanel();
		
		Border brdVelocidad = new TitledBorder(new EtchedBorder(), "Velocidad");
		pnlVelocidad.setBorder(brdVelocidad);
		
		sliderVelocidad = new JSlider(JSlider.HORIZONTAL, 10, 610, vel);
		sliderVelocidad.setPaintTicks(true);
		sliderVelocidad.setMajorTickSpacing(100);
		sliderVelocidad.setMinorTickSpacing(100);
		sliderVelocidad.setPaintLabels(true);
		sliderVelocidad.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int vel = sliderVelocidad.getValue();
				setVelocidad(vel);
				panel1.setVelocidad(vel);
				panel2.setVelocidad(vel);
				panel3.setVelocidad(vel);
				panel4.setVelocidad(vel);
				panel5.setVelocidad(vel);
			}
		});
		
		pnlVelocidad.add(sliderVelocidad);
		pnlComando.add(pnlVelocidad);
	}
	
	private void colocarBotones() {
		JPanel pnlBotones = new JPanel();
		pnlBotones.setLayout(new GridLayout(3, 1, 0, 7));
		
		
		//Boton ordenar
		btnOrdenar = new JButton();
		btnOrdenar.setText("Ordenar");
		btnOrdenar.setMnemonic('o');
		btnOrdenar.setBackground(new Color(28, 139, 0));
		btnOrdenar.setBorderPainted(false);
		btnOrdenar.setForeground(Color.WHITE);
		btnOrdenar.setFont(new Font("Lato", Font.BOLD, 16));
		//Boton ordenar
		btnParar = new JButton();
		btnParar.setText("Parar");
		btnParar.setMnemonic('p');
		btnParar.setBackground(new Color(139, 10, 10));
		btnParar.setBorderPainted(false);
		btnParar.setForeground(Color.WHITE);
		btnParar.setFont(new Font("Lato", Font.BOLD, 16));
		//Boton ordenar
		btnCerrar = new JButton();
		btnCerrar.setText("Cerrar");
		btnCerrar.setMnemonic('l');
		btnCerrar.setBackground(new Color(10, 139, 130));
		btnCerrar.setBorderPainted(false);
		btnCerrar.setForeground(Color.WHITE);
		btnCerrar.setFont(new Font("Lato", Font.BOLD, 16));
		
		//añadimos al panel
		pnlBotones.add(btnOrdenar);
		pnlBotones.add(btnParar);
		pnlBotones.add(btnCerrar);
		
		btnParar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {		
				pararThread();
				btnOrdenar.setText("Reanudar");
			}
		});
		
		
		btnOrdenar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				t1 = new SelectionThread(panel1,vel);
				t2 = new InsertionThread(panel2,vel);
				t3 = new BubbleTread(panel3, vel);
				t4 = new MergeThread(panel4,vel);
				t5 = new HeapThread(panel5, vel);
				t1.start();
				t2.start();
				t3.start();
				t4.start();
				t5.start();
				btnOrdenar.setText("Ordenar");
			}
		});
		
		btnCerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		pnlComando.add(pnlBotones);
		
		
	}
	
	private void pararThread() {
		if(t1 != null && t2 != null && t3 != null && t4!=null) {
			t1.stop();
			t2.stop();
			t3.stop();
			t4.stop();
			t5.stop();
		}
	}
	
	public int[] generarVectorAleatorio(int n) {
		Random rd = new Random();
		int[] a = new int[n];
		for(int i = 0; i < n;i++) {
			a[i] = (int)rd.nextInt(n)+1;
		}
		return a;
	}
	public int[] generarVectorDecreciente(int n) {
		int[] a = new int[n];
		int numFinal = 2*n;
		for(int i = 0; i < n; i++) {
			a[i] = numFinal;
			numFinal -=2;
		}
		return a;
	}
	
	public int[] generarVectorCasiOrdenado(int n) {
		Random rd = new Random();
		int[] a = new int[n];
		for(int i = 0; i < n;i++) {
			a[i] = (int)rd.nextInt(n)+1;
		}
		for(int i = 0; i<n/2;i++) {
			a[i*2] = (i+1)*2;
		}
		return a;
	}
	
	public int getMayor(int[] arr) {
		int mayor = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] > mayor) {
				mayor = arr[i];
			}
		}
		return mayor;
	}
	
	public void setVelocidad(int vel) {
		this.vel = vel;
	}
	
	public static void main(String[] args) {
		Ventana2 form = new Ventana2();
		form.v1.setVisible(true);
	}
}
