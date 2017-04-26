package def;

public class Neyron implements Neyro_Interface {
	
	String name;
	double sigma;
    double[] wieght = {};
	double value ;
	double activeVal;
	public Neyron() {
		
	}
	public Neyron(double value,String name) {
		this.value = value;
		this.name = name;
	}
    // Функция активации сигмоид
	@Override
	public void activate() {
		double nowValue = this.value; 
		activeVal = 1/(1+Math.pow( 2.71, -nowValue));
		//System.out.println(activeVal);
		this.activeVal = activeVal;
	}
	//Установка весов всем синапсам нейрона 
	@Override
	public void setWeight(double[] d) {
		this.wieght = d; // TODO Auto-generated method stub
		
	}
	 @Override
	public String toString() {
		System.out.println("name = "+ name);
		System.out.println("value = "+ value);
		System.out.println("activeVal = "+ activeVal);
		System.out.print("weight = ");
		for (double a: wieght){
			System.out.println(a);
		}
		return super.toString();
	}

}
