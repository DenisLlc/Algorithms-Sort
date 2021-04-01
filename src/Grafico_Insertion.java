import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Random;

public class Grafico_Insertion extends JPanel {
	
	Graphics2D g2D;
	private int[] a;
	private int mayor;
	private int velocidad;
	
	public Grafico_Insertion(int[] arr, int velocidad) {
		this.a = Arrays.copyOf(arr, arr.length);;
		this.velocidad = velocidad;
		mayor = mayor(a);
	}
	
	public void updateData(int[] arr, int velocidad) {
		this.a = Arrays.copyOf(arr, arr.length);
		this.velocidad = velocidad;
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
	public void paintComponent(Graphics g) {
		g2D = (Graphics2D) g;
		limpiarventana(g);
		g2D.setColor(Color.BLACK);
		Dimension d = getSize();
		
		int space = (int) ((d.width/(a.length+1))*0.10);
		int space2 = (int) (d.height*0.15);
		int w = (int) ((d.width/(a.length))*0.90);
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
	
	public void runInsertion(Graphics g) {
		Dimension d = getSize();
		
		int space = (int) ((d.width/(a.length+1))*0.1);
		int space2 = (int) (d.height*0.15);
		int w = (int) ((d.width/(a.length))*0.9);
		int n = a.length;
		
		//pinto la primera interaccion
		try {
        	//cambia de en blanco su destino
        	int h = (int)(((double)d.height*((double)a[0]/mayor)));
        	g.setColor(Color.RED);
        	g.fillRect((0+1)*space+(0)*w, (int) ((space2 + (double)d.height - (double)h)*0.85), w, h);
        	Thread.sleep(velocidad);
        	g.clearRect((0+1)*space+(0)*w,0, w, d.height);
        	Thread.sleep(velocidad);
        	
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//insertion
        for (int i = 1; i < n; ++i) { 
            int key = a[i]; 
            int j = i - 1; 
            
            try {
            	//cambia de en blanco su destino
            	int h = (int)(((double)d.height*((double)a[i]/mayor)));
            	//g2D.setColor(Color.GREEN);
            	g.setColor(Color.RED);
            	g.fillRect((i+1)*space+(i)*w, (int) ((space2 + (double)d.height - (double)h)*0.85), w, h);
            	Thread.sleep(velocidad);
            	
			} catch (Exception e) {
				System.out.println(e);
			}
  
            /* Mover elementos de arr [0..i-1], que son  mayores que la clave, a una posición adelante
               de su posicion actual */
            while (j >= 0 && a[j] > key) { 
                a[j + 1] = a[j]; 
                
                try {
                	//pone en blanco su posicion origen
                	int h = (int)(((double)d.height*((double)a[j]/mayor)));
                	g.setColor(Color.BLUE);
                	g.fillRect((j+1)*space+(j)*w, (int) ((space2 + (double)d.height - (double)h)*0.85), w, h);
                	Thread.sleep(velocidad);
                	g.clearRect((j+1)*space+(j)*w,0, w, d.height);
                	Thread.sleep(velocidad);
    			} catch (Exception e) {
    				System.out.println(e);
    			}
                try {
                	//pone en blanco su posicion origen
                	int h = (int)(((double)d.height*((double)a[j+1]/mayor)));
                	g.setColor(Color.BLACK);
                	g.fillRect((j+1+1)*space+(j+1)*w, (int) ((space2 + (double)d.height - (double)h)*0.85), w, h);
                	Thread.sleep(velocidad);
    			} catch (Exception e) {
    				System.out.println(e);
    			}
                
                j = j - 1; 
                
            } 
            
            a[j + 1] = key;
            
            try {
				//lo pone en blanco
            	g.setColor(Color.BLACK);
            	int h = (int)(((double)d.height*((double)a[i]/mayor)));
            	g.fillRect((i+1)*space+(i)*w, (int) ((space2 + (double)d.height - (double)h)*0.85), w, h);
            	Thread.sleep(velocidad);
            	//lo cambia al menor
            	h = (int)(((double)d.height*((double)a[j+1]/mayor)));
            	g.setColor(Color.BLACK);
            	g.fillRect((j+1+1)*space+(j+1)*w, (int) ((space2 + (double)d.height - (double)h)*0.85), w, h);
            	Thread.sleep(velocidad);
			} catch (Exception e) {
				System.out.println(e);
			}          
        }
	}
	
	public int[] getArreglo() {
		return a;
	}
	
	public int getMayor() {
		return mayor;
	}
	
	public int mayor(int[] a) {
		int mayor = 0;
		for(int i = 0; i < a.length; i++) {
			if(a[i] > mayor) {
				mayor = a[i];
			}
		}
		return mayor;
	}
	
	
}
