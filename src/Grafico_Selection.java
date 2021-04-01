import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Grafico_Selection extends JPanel {
	Graphics2D g2D;
	private int[] a;
	private int mayor;
	private int velocidad;
	
	
	public Grafico_Selection(int[] arr, int vel) {
		this.a = Arrays.copyOf(arr, arr.length);
		this.velocidad = vel;
		this.mayor = mayor(a);
	}
	
	public void updateData(int[] arr, int vel) {
		this.a = Arrays.copyOf(arr, arr.length);
		this.velocidad = vel;
		this.mayor = mayor(a);
	}
	
	public void setVelocidad(int vel) {
		this.velocidad = vel;
	}
	
	public void limpiarventana(Graphics g) {
		Dimension d = getSize();
		g.clearRect(0, 0, d.width, d.height);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		
		g2D = (Graphics2D) g;
		limpiarventana(g);
		g2D.setColor(Color.BLACK);
		Dimension d = getSize();
		
		int space = (int) ((d.width/(a.length+1))*0.1);
		int space2 = (int) (d.height*0.15);
		int w = (int) ((d.width/(a.length))*0.9);
		int xPost, yPost;
		xPost = space;
		
		//se dibuja el cuadro inicial
		try {
			for(int i = 0; i < a.length; i++) {
				int h = (int)(((double)d.height*((double)a[i]/mayor)));
				yPost = (int) ((space2 + (double)d.height - (double)h)*0.85);
				g2D.fillRect(xPost, yPost, w, h);
				xPost += (space+w);	
			}
    		xPost = space;	
         } catch (Exception e) {
            System.out.println(e);
         }
	}
	
	void runSelection(Graphics g) {
		Dimension d = getSize();
		
		int space = (int) ((d.width/(a.length+1))*0.1);
		int space2 = (int) (d.height*0.15);
		int w = (int) ((d.width/(a.length))*0.9);
		int n = a.length; 
		
		  
        // Uno a uno arregla los subarrays generados
        for (int i = 0; i < n-1; i++) 
        { 
            // Encuentra al menor elemento en el arreglo
            int min_idx = i; 
            for (int j = i+1; j < n; j++) 
                if (a[j] < a[min_idx]) 
                    min_idx = j; 
            
            // Al encontrar un elemento menor lo cambia con el primero
                       
            int temp = a[min_idx];
            try {
            	//pone en blanco su posicion origen
            	int h = (int)(((double)d.height*((double)a[min_idx]/mayor)));
            	g.setColor(Color.BLUE);
            	g.fillRect((min_idx+1)*space+(min_idx)*w, (int) ((space2 + (double)d.height - (double)h)*0.85), w, h);
            	Thread.sleep(velocidad);
            	g.clearRect((min_idx+1)*space+(min_idx)*w,0, w, d.height);
            	Thread.sleep(velocidad);
			} catch (Exception e) {
				System.out.println(e);
			}
            
            a[min_idx] = a[i];
            
            try {
            	//cambia de en blanco su destino
            	int h = (int)(((double)d.height*((double)a[i]/mayor)));
            	g.setColor(Color.RED);
            	g.fillRect((i+1)*space+(i)*w, (int) ((space2 + (double)d.height - (double)h)*0.85), w, h);
            	Thread.sleep(velocidad);
            	g.clearRect((i+1)*space+(i)*w, 0, w, d.height);
            	Thread.sleep(velocidad);        	
			} catch (Exception e) {
				System.out.println(e);
			}
            
            a[i] = temp;
            try {
				//lo pone en blanco
            	g.setColor(Color.BLACK);
            	int h = (int)(((double)d.height*((double)a[i]/mayor)));
            	g.fillRect((i+1)*space+(i)*w, (int) ((space2 + (double)d.height - (double)h)*0.85), w, h);
            	Thread.sleep(velocidad);
            	//lo cambia al menor
            	h = (int)(((double)d.height*((double)a[min_idx]/mayor)));
            	g.setColor(Color.BLACK);
            	g.fillRect((min_idx+1)*space+(min_idx)*w, (int) ((space2 + (double)d.height - (double)h)*0.85), w, h);
            	Thread.sleep(velocidad);
			} catch (Exception e) {
				System.out.println(e);
			}               
        }
	}
	
	public int mayor(int[] arr) {
		int mayor = 0;
		for(int i = 0; i < a.length; i++) {
			if(arr[i] > mayor) {
				mayor = arr[i];
			}
		}
		return mayor;
	}
	
	public int[] getArreglo() {
		return a;
	}
	
	public int getMayor() {
		return mayor;
	}

}