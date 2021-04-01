
public class BubbleTread extends Thread {
	int[] a;
	Grafico_Bubble gb;
	int time;
	int mayor;
	
	public BubbleTread(Grafico_Bubble gb, int time) {
		this.gb = gb;
		this.a = gb.getArreglo();
		this.time = 50;
		this.mayor = gb.mayor(a);
	}
	
	@Override
	public void run() {
		gb.runBuddle(gb.getGraphics());
	}
	
}
