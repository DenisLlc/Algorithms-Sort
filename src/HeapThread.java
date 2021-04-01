
public class HeapThread extends Thread{
	int[] a;
	Grafico_Heap gh;
	int time;
	int mayor;
	
	public HeapThread(Grafico_Heap gh, int time) {
		this.gh = gh;
		this.a = gh.getArreglo();
		this.time = 50;
		this.mayor = gh.mayor(a);
	}
	@Override
	public void run() {
		gh.runHeapSort(gh.getGraphics());
	}
}
