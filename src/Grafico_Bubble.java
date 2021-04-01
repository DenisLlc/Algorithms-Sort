import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.border.Border;

public class Grafico_Bubble extends JPanel{
	Graphics2D g2D;
	int[] a;
	private int mayor;
	private int velocidad;
	
	public Grafico_Bubble(int[] arr, int velocidad) {
		this.a = Arrays.copyOf(arr, arr.length);
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
	protected void paintComponent(Graphics g) {
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
	
	public void runBuddle(Graphics g) {
		Dimension d = getSize();
		
		int space = (int) ((d.width/(a.length+1))*0.1);
		int space2 = (int) (d.height*0.15);
		int w = (int) ((d.width/(a.length))*0.9);
		int n = a.length;
		 
        for (int i = 0; i < n-1; i++) {
        	for (int j = 0; j < n-i-1; j++) {
        		
        		//Pintar j y a j+1
        		try {
        			int h = (int)(((double)d.height*((double)a[j]/mayor)));
        			g.setColor(Color.RED);
                	g.fillRect((j+1)*space+(j)*w, (int) ((space2 + (double)d.height - (double)h)*0.85), w, h);
                	int h1 = (int)(((double)d.height*((double)a[j+1]/mayor)));
                	g.fillRect((j+1+1)*space+(j+1)*w, (int) ((space2 + (double)d.height - (double)h1)*0.85), w, h1);
                	Thread.sleep(velocidad);
				} catch (Exception e) {
					System.out.println(e);
				}
        		        		
            	if (a[j] > a[j+1]) 
                { 
                    // swap arr[j+1] and arr[i] 
                    int temp = a[j];
                    try {
                    	int h = (int)(((double)d.height*((double)a[j]/mayor)));
                    	g.clearRect((j+1)*space+(j)*w,0, w, d.height);
                    	int h1 = (int)(((double)d.height*((double)a[j+1]/mayor)));
                    	g.clearRect((j+1+1)*space+(j+1)*w,0, w, d.height);
					} catch (Exception e) {
						System.out.println(e);
					}
                    
                    a[j] = a[j+1]; 
                    
                    
                    a[j+1] = temp;
                    try {
                    	int h = (int)(((double)d.height*((double)a[j]/mayor)));
                    	g.setColor(Color.BLACK);
                    	g.fillRect((j+1)*space+(j)*w, (int) ((space2 + (double)d.height - (double)h)*0.85), w, h);
                    	int h1 = (int)(((double)d.height*((double)a[j+1]/mayor)));
                    	g.setColor(Color.BLACK);
                    	g.fillRect((j+1+1)*space+(j+1)*w, (int) ((space2 + (double)d.height - (double)h1)*0.85), w, h1);
                    	Thread.sleep(velocidad);
					} catch (Exception e) {
						System.out.println(e);
					}
                    
                }else {
                	
                	//
                	try {
                		int h = (int)(((double)d.height*((double)a[j]/mayor)));
                		g.setColor(Color.BLACK);
                    	g.fillRect((j+1)*space+(j)*w, (int) ((space2 + (double)d.height - (double)h)*0.85), w, h);
                    	Thread.sleep(velocidad);
					} catch (Exception e) {
						System.out.println(e);
					}
                	
                }
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
