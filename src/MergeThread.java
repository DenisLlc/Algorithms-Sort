
public class MergeThread extends Thread{
	int[] a;
	int l;
	int r;
	Grafico_Merge gm;
	int velocidad;
	int mayor;
	
	public MergeThread(Grafico_Merge gm, int velocidad) {
		this.gm = gm;
		this.a = gm.getArreglo();
		this.l = 0;
		this.r = a.length-1;
		this.velocidad = 50;
		this.mayor = gm.mayor(a);
	}
	
	@Override
	public void run() {
		gm.runMergeSort(a, l, r, gm.getGraphics());
	}
}
