import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Arrays;

import javax.swing.JPanel;

public class Grafico_Merge extends JPanel{
	Graphics2D g2D;
	int[] a;
	int l;
	int r;
	private int mayor;
	private int velocidad;
	
	public Grafico_Merge(int[] arr, int velocidad) {
		this.a = Arrays.copyOf(arr, arr.length);
		this.l = 0;
		this.r = a.length-1;
		this.velocidad = velocidad;
		mayor = mayor(a);
	}
	
	public void updateData(int[] arr, int velocidad) {
		this.a = Arrays.copyOf(arr, arr.length);;
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
	
	public int[] getArreglo() {
		return a;
	}
	
	public int getMayor() {
		return mayor;
	}
	
	public int getL() {
		return l;
	}
	
	public int getR() {
		return r;
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
	
	public void runMergeSort(int[] a, int l, int r, Graphics g) {
		if (l < r) { 
            // Find the middle point 
            int m = (l+r)/2; 
  
            // Sort first and second halves
            //izquierda
            runMergeSort(a, l, m, g);
            //derecha
            runMergeSort(a , m+1, r, g); 
  
            // Merge the sorted halves 
            merge(a, l, m, r, g); 
        }
	}
	
	public void merge(int arr[], int l, int m,int r, Graphics g) {
		
		Dimension d = getSize();
		int space = (int) ((d.width/(a.length+1))*0.1);
		int space2 = (int) (d.height*0.15);
		int w = (int) ((d.width/(a.length))*0.9);
		
		// Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        /* Create temp arrays */
        int[] L = new int [n1]; 
        int[] R = new int [n2]; 
  
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i) {
        	L[i] = arr[l + i];
        }   
        for (int j=0; j<n2; ++j) {
        	R[j] = arr[m + 1+ j];
        }             
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2) { 
            if (L[i] <= R[j]) {
            	try {
                	int h = (int)(((double)d.height*((double)a[k]/mayor)));        		
                	g.clearRect((k+1)*space+(k)*w,0, w, d.height);
                	Thread.sleep(velocidad);
    			} catch (Exception e) {
    				System.out.println(e);
    			}
                arr[k] = L[i];
                try {
                	int h = (int)(((double)d.height*((double)a[k]/mayor)));
        			g.setColor(Color.GREEN);
                	g.fillRect((k+1)*space+(k)*w, (int) ((space2 + (double)d.height - (double)h)*0.85), w, h);
                	Thread.sleep(velocidad);
				} catch (Exception e) {
					System.out.println(e);
				}
                i++; 
            } 
            else {
            	//borrar k bien
            	try {
                	int h = (int)(((double)d.height*((double)a[k]/mayor)));        		
                	g.clearRect((k+1)*space+(k)*w,0, w, d.height);
                	Thread.sleep(velocidad);
				} catch (Exception e) {
					System.out.println(e);
				}
                arr[k] = R[j];
                //pintar bien
                try {
                	int h = (int)(((double)d.height*((double)a[k]/mayor)));
        			g.setColor(Color.RED);
                	g.fillRect((k+1)*space+(k)*w, (int) ((space2 + (double)d.height - (double)h)*0.85), w, h);
                	Thread.sleep(velocidad);
				} catch (Exception e) {
					System.out.println(e);
				}
                j++; 
            } 
            k++; 
        } 
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
        	try {
            	int h = (int)(((double)d.height*((double)a[k]/mayor)));        		
            	g.clearRect((k+1)*space+(k)*w,0, w, d.height);
            	Thread.sleep(velocidad);
			} catch (Exception e) {
				System.out.println(e);
			}
            arr[k] = L[i];
            try {
            	int h = (int)(((double)d.height*((double)a[k]/mayor)));
    			g.setColor(Color.RED);
            	g.fillRect((k+1)*space+(k)*w, (int) ((space2 + (double)d.height - (double)h)*0.85), w, h);
            	Thread.sleep(velocidad);
			} catch (Exception e) {
				System.out.println(e);
			}
            i++; 
            k++; 
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
        	try {
            	int h = (int)(((double)d.height*((double)a[k]/mayor)));        		
            	g.clearRect((k+1)*space+(k)*w,0, w, d.height);
            	Thread.sleep(velocidad);
			} catch (Exception e) {
				System.out.println(e);
			}
            arr[k] = R[j];
            try {
            	int h = (int)(((double)d.height*((double)a[k]/mayor)));
    			g.setColor(Color.RED);
            	g.fillRect((k+1)*space+(k)*w, (int) ((space2 + (double)d.height - (double)h)*0.85), w, h);
            	Thread.sleep(velocidad);
			} catch (Exception e) {
				System.out.println(e);
			}
            j++; 
            k++; 
        }
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
