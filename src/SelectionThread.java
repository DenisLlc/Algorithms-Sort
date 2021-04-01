import java.awt.Graphics2D;

import javax.swing.JApplet;

public class SelectionThread extends Thread{
	
	int[] a;
	Grafico_Selection gs;
	int velocidad;
	int mayor;
	
	public SelectionThread(Grafico_Selection gs, int velocidad) {
		this.gs = gs;
		this.a = gs.getArreglo();
		this.velocidad = velocidad;
		this.mayor = gs.mayor(a);
	}
	
	@Override
	public void run() {
		gs.runSelection(gs.getGraphics());
	}
}
