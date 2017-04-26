package def;

import java.io.FilterInputStream;

public class Network {
	 double Error;
	 double IdealValue = 1;
	 Layer firstLayer;
	 Layer secondLayer;
	 Layer thirdLayer;
	 Layer outLayer;
	public Network() {
  Neyron n1 = new Neyron(0.8 ,"n1");
  Neyron n2 = new Neyron(10,"n2");
  Neyron n3 = new Neyron(0.7,"n3");
  Neyron n4 = new Neyron(0.45,"n4");
  Neyron[] neyromas = {n1,n2,n3,n4};
  firstLayer = new Layer(neyromas);
  
  Neyron n21 = new Neyron(0,"n21");
  Neyron n22 = new Neyron(0,"n22");
  Neyron n23 = new Neyron(0,"n23");
  Neyron n24 = new Neyron(0,"n24");
  Neyron[] neyromas2 = {n21,n22,n23,n24};
  secondLayer = new Layer(neyromas2);
  
  Neyron n31 = new Neyron(0,"n31");
  Neyron n32 = new Neyron(0,"n32");
  Neyron[] neyromas3 = {n31,n32};
  thirdLayer = new Layer(neyromas3);
 
  
  Neyron outNeyron = new Neyron(0,"outNeyron");
  Neyron[] neyron4 = {outNeyron};
  outLayer = new Layer(neyron4);
  
  
  
  firstLayer.setRadomWeight(secondLayer);
  firstLayer.active();
  NetStart(firstLayer, secondLayer);
  
  secondLayer.setRadomWeight(thirdLayer);
  secondLayer.active();
  NetStart(secondLayer, thirdLayer);
  
  thirdLayer.setRadomWeight(outLayer);
  thirdLayer.active();
  NetStart(thirdLayer, outLayer);

  outLayer.active();
  
   secondLayer.toString();
   thirdLayer.toString();
   outLayer.toString();
   CountError();
   //outLayer.active();
   
}
	
	// Передача сигналов на след. уровень, установка value
	void NetStart(Layer prevLayer, Layer nextLayer){
	
		for (Neyron prev : prevLayer.masNeyron){
			int i=0;
		  for(Neyron next: nextLayer.masNeyron){
			  
			 next.value =next.value + (prev.activeVal * prev.wieght[i]);
			 //System.out.print(next.value + "   ");
			 i++;
		  }
			// System.out.println("");
			
		  
		}
	

	}
	// MSE ошибка
	void CountError(){
		Error = (IdealValue-outLayer.masNeyron[0].activeVal);
		
		Error = (Math.pow(Error, 2));
		System.out.println("Error= "+Error);
		
	}
	
	void CountSigmaOut(){
    double fIn= (1 - outLayer.masNeyron[0].activeVal)*outLayer.masNeyron[0].activeVal;
    double sigma = (IdealValue - outLayer.masNeyron[0].activeVal ) * fIn; 
	}
	
	void CountSigma(Layer curentLayer, Layer nextLayer){
		
	}
}
