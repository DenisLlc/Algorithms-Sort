import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Arrays;

import javax.swing.JPanel;

public class Grafico_Heap extends JPanel{
	Graphics2D g2D;
	int[] a;
	int l;
	int r;
	private int mayor;
	private int velocidad;
	
	public Grafico_Heap(int[] arr, int velocidad) {
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
	public void runHeapSort(Graphics g) {
		
		//HeapSorting
		int n = a.length;
		int[] sf = new int[n];
		//1-contruimos heap
		for(int i = n, j = 0; i > 0 ; i--, j++) {
			
			minHeap(a, i, g);
			
			sf[j] = a[0];
			
			a[0] = a[i-1];
			
		}
		
		
		for(int i = 0;i < n ; i++) {
			a[i] = sf[i];			
		}
		
	}
	
	public void minHeap(int[] arr, int n, int i) {
		
		int posPadre = i;	//si el nodo padre es  i	//tomamos 0 como nodo raiz			
	    int izq = (2*i)+1;//el nodo hijo izq. es  2i+1
	    int der = (2*i)+2;//and right child = 2i+2
		
		if(izq < n && arr[izq] < arr[posPadre]) {
			posPadre = izq;
		}
		
		if(der < n && arr[der] < arr[posPadre]) {
			posPadre = der;
		}
		
		if(posPadre != i) {
			int swap = arr[i]; 
            arr[i] = arr[posPadre]; 
            arr[posPadre] = swap;
            
            //recursivamente cubrimos hasta el nodo raiz
            minHeap(arr, n, posPadre);
		}
	}
	public void minHeap(int[] arr, int n, Graphics g) {
		
		for(int i = (n-1)/2; i >= 0; i--) {
			minHeap(arr, n, i);
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
