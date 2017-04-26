package def;

public class Layer implements Layer_Interface {
	Neyron[] masNeyron;
	
	public Layer(Neyron[] masNeyron) {
		this.masNeyron = masNeyron;
	}
	
	@Override
	public String toString() {
		for(Neyron a: masNeyron){ a.toString(); System.out.println();}
		return super.toString();
	}


	//���������� ������ �������� �� �������� ������
	public Neyron[] getLayer(int numLayer) {
		
		return this.masNeyron;
	}
    // ��������� ���������  �����
	@Override
	public void setRadomWeight(Layer nextlayer) {
		int numNeyNextLayer = nextlayer.masNeyron.length;

		for (Neyron i: this.masNeyron ){
			
			double[] masWeiht = new double[numNeyNextLayer];
			
			// ������� ��� �������� � ��������� ���� � ������� ������ ����� ��� �������� ���� 
			for (int a=0; a < numNeyNextLayer;a++){
				double random = Math.random();
				masWeiht[a] = random;
			}
			
			i.setWeight(masWeiht);
		}
		
	}
	
	void active(){
		for(Neyron n : this.masNeyron){
			n.activate();
		}
	}
	
	

	
	

}
