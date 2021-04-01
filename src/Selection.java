
public class Selection {
	public static void run(int[] arr) {
		int n = arr.length;
		//grafico inicial
		for (int i = 1; i < n; ++i) { 
            int key = arr[i]; 
            //grafico para cambiar de posicion
            int j = i - 1; 
            //grafica ya cambiado
            while (j >= 0 && arr[j] > key) { 
                arr[j + 1] = arr[j]; 
                j = j - 1; 
            } 
            arr[j + 1] = key; 
        } 
	}
	
	public static void main(String[] args) {
		int[] a = {4,2,4,2,6,2,6,8};
		for(int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}
		run(a);
		System.out.println("");
		for(int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}		
	}
}
