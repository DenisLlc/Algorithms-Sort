
public class InsertionThread extends Thread{
	int[] a;
	Grafico_Insertion gi;
	int time;
	int mayor;
	
	public InsertionThread(Grafico_Insertion gi, int time) {
		this.gi = gi;
		this.a = gi.getArreglo();
		this.time = 50;
		this.mayor = gi.mayor(a);
	}
	
	@Override
	public void run() {
		gi.runInsertion(gi.getGraphics());
	}
	
}
