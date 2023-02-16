public class Sort implements Runnable{
	
	int[] number;
	Runnable runnable;
	
	public Sort(int[] number, Runnable runnable) {
		this.number = number;
		this.runnable = runnable;
	}
	
    public void quick(int[] number) {
        sort(number, 0, number.length-1);
    }
    
    private void sort(int[] number, int left, int right) {
        if(left < right) { 
            int q = partition(number, left, right); 
            sort(number, left, q-1); 
            sort(number, q+1, right); 
        } 

    }

    private  int partition(int number[], int left, int right) {  
        int i = left - 1; 
        for(int j = left; j < right; j++) { 
            if(number[j] <= number[right]) { 
                i++; 
                swap(number, i, j); 
            }
            
            try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            runnable.run();
            System.out.println("wait");
        } 
        swap(number, i+1, right); 
        return i+1; 
    } 

    private void swap(int[] number, int i, int j) {
        int t = number[i]; 
        number[i] = number[j]; 
        number[j] = t;
    }

	@Override
	public void run() {
		quick(number);
		System.out.println("run!");
	}
}