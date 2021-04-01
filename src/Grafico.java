import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.*;

import javax.swing.JApplet;

public class Grafico extends JApplet {
	Graphics2D g2D;
	//int[] a = {5,4,3,2,1};
	//int[] a = {5,4,3,2,1};
	int[] a = {5,4,3,2,1,6,2,7,2,5};
	@Override
	public void paint(Graphics g) {
		int mayor = mayor(a);
		
		g2D = (Graphics2D) g;
		g2D.setColor(Color.BLACK);
		Dimension d = getSize();
		
		g2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		
		int space = (int) ((d.width/(a.length+1))*0.1);
		int space2 = (int) (d.height*0.05);
		int w = (int) ((d.width/(a.length))*0.9);
		int xPost, yPost;
		xPost = space;
		
		//se dibuja el cuadro inicial
		try {
			for(int i = 0; i < a.length; i++) {
				int h = (int)(((double)d.height*((double)a[i]/mayor)));
				yPost = (int) ((space2 + (double)d.height - (double)h)*0.95);
				g2D.fillRect(xPost, yPost, w, h);
				xPost += (space+w);	
			}
    		xPost = space;
    		
    		Thread.sleep(500);
         } catch (Exception e) {
            System.out.println(e);
         }                    
		
		//selection
		
		int n = a.length; 
		  
        // One by one move boundary of unsorted subarray 
        for (int i = 0; i < n-1; i++) 
        { 
            // Encuentra al menor elemento en el arreglo
            int min_idx = i; 
            for (int j = i+1; j < n; j++) 
                if (a[j] < a[min_idx]) 
                    min_idx = j; 
  
            // Swap the found minimum element with the first 
            // element 
                       
            int temp = a[min_idx];
            try {
            	//pone en blanco su posicion origen
            	int h = (int)(((double)d.height*((double)a[min_idx]/mayor)));
            	g2D.setColor(Color.BLUE);
            	g2D.fillRect((min_idx+1)*space+(min_idx)*w, (int) ((space2 + (double)d.height - (double)h)*0.95), w, h);
            	Thread.sleep(300);
            	g2D.clearRect((min_idx+1)*space+(min_idx)*w, (int) ((space2 + (double)d.height - (double)h)*0.95), w, h);
            	Thread.sleep(300);
			} catch (Exception e) {
				System.out.println(e);
			}
            
            a[min_idx] = a[i];
            
            try {
            	//cambia de en blanco su destino
            	int h = (int)(((double)d.height*((double)a[i]/mayor)));
            	//g2D.setColor(Color.GREEN);
            	g2D.setColor(Color.RED);
            	g2D.fillRect((i+1)*space+(i)*w, (int) ((space2 + (double)d.height - (double)h)*0.95), w, h);
            	Thread.sleep(300);
            	g2D.clearRect((i+1)*space+(i)*w, (int) ((space2 + (double)d.height - (double)h)*0.95), w, h);
            	Thread.sleep(300);
            	
			} catch (Exception e) {
				System.out.println(e);
			}
            
            a[i] = temp;
            try {
				//lo pone en blanco
            	g2D.setColor(Color.BLACK);
            	int h = (int)(((double)d.height*((double)a[i]/mayor)));
            	g2D.fillRect((i+1)*space+(i)*w, (int) ((space2 + (double)d.height - (double)h)*0.95), w, h);
            	Thread.sleep(200);
            	//lo cambia al menor
            	h = (int)(((double)d.height*((double)a[min_idx]/mayor)));
            	g2D.setColor(Color.BLACK);
            	g2D.fillRect((min_idx+1)*space+(min_idx)*w, (int) ((space2 + (double)d.height - (double)h)*0.95), w, h);
            	Thread.sleep(200);
			} catch (Exception e) {
				System.out.println(e);
			}
            
            
        }
        
        

		//todo esto es para dibujar un arreglo a sera el arreglo
		/*try {
			for(int i = 0; i < a.length; i++) {
				int h = (int)(((double)d.height*((double)a[i]/mayor)));
				yPost = (int) ((space2 + (double)d.height - (double)h)*0.95);
				g2D.fillRect(xPost, yPost, w, h);
				xPost += (space+w);	
			}
            Thread.sleep(2000);
    		g2D.clearRect(0, 0, d.width, d.height);
    		Thread.sleep(1000);
         } catch (Exception e) {
            System.out.println(e);
         }*/
		
		
	}
	/*Grafico(){
 		this.a = new int[] {50,74,100,91, 150,35,200,87,46,250,125,300,291,350}; 
	}
	Grafico(int[] a){
		this.a = a;
	}*/
	
	static int mayor(int[] a) {
		int mayor = 0;
		for(int i = 0; i < a.length; i++) {
			if(a[i] > mayor) {
				mayor = a[i];
			}
		}
		return mayor;
	}
	
	void swap(int xp, int yp) {  
	    int temp = xp;  
	    xp = yp;  
	    yp = temp;  
	}
}
