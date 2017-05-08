package def;

import java.io.FilterInputStream;

public class Network {
	 private static final double A_CONST = 0.3;
	private static final double E_CONST = 0.7;
	double Error = 0.1;
	static double IdealValue = 1;
	static Layer firstLayer;
	static Layer secondLayer;
	static Layer thirdLayer;
	static Layer outLayer;
	 
public Network() {
  Neyron n1 = new Neyron(80 ,"n1");
  Neyron n2 = new Neyron(73,"n2");
  Neyron n3 = new Neyron(30,"n3");
  Neyron n4 = new Neyron(25,"n4");
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
  secondLayer.setRadomWeight(thirdLayer);
  thirdLayer.setRadomWeight(outLayer);
  
  NetwokrStart();
}
	
public Network(double n1, double n2, double n3, double n4){
	 	
	
	
		firstLayer.masNeyron[0].value = n1;
		firstLayer.masNeyron[1].value = n2;
		firstLayer.masNeyron[2].value = n3;
		firstLayer.masNeyron[3].value = n4;
		
		secondLayer.masNeyron[0].value = 0;
		secondLayer.masNeyron[1].value = 0;
		secondLayer.masNeyron[2].value = 0;
		secondLayer.masNeyron[3].value = 0;
		
		thirdLayer.masNeyron[0].value = 0;
		thirdLayer.masNeyron[1].value = 0;
		
		outLayer.masNeyron[0].value = 0;
		outLayer.masNeyron[0].activeVal = 0;
		
		
		
		System.out.println("-------------------------");
		NetwokrStart();
	
}
	
  void NetwokrStart(){
		  while (Error>0.0000000001){
		  firstLayer.active();
		  NetStart(firstLayer, secondLayer);
		  
		 
		  secondLayer.active();
		  NetStart(secondLayer, thirdLayer);
		  
		
		  thirdLayer.active();
		  NetStart(thirdLayer, outLayer);
		
		  outLayer.active();
		  
		
		  CountSigmaOut();
		  CountSigma(thirdLayer, outLayer);
		  CountSigma(secondLayer, thirdLayer);
		  CountSigma(firstLayer, secondLayer);
		  
		  CountGrad(thirdLayer, outLayer);
		  CountGrad(secondLayer, thirdLayer);
		  CountGrad(firstLayer, secondLayer);
		  
		  CountDelta(thirdLayer);
		  CountDelta(secondLayer);
		  CountDelta(firstLayer);
		  
		 
		  
		  firstLayer.toString();
		  secondLayer.toString();
		  thirdLayer.toString();
		  outLayer.masNeyron[0].OuttoString();
		   CountError();
		   System.out.println(" ");
		   
		   ChangeWeight(firstLayer);
		   ChangeWeight(secondLayer);
		   ChangeWeight(thirdLayer);
  }
   
   
   
}
  
  
	
	
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
	// MSE ������
	void CountError(){
		Error = (IdealValue-outLayer.masNeyron[0].activeVal);
		
		Error = (Math.pow(Error, 2));
		System.out.println("Error= "+Error);
		
	}
	
	void CountSigmaOut(){
	    double fIn= (IdealValue - outLayer.masNeyron[0].activeVal)*outLayer.masNeyron[0].activeVal;
	    double sigma = (IdealValue - outLayer.masNeyron[0].activeVal ) * fIn;
	    //System.out.println("///////////////////// "+ sigma);
	    outLayer.masNeyron[0].sigma = sigma;
	}
	
	void CountSigma(Layer curentLayer, Layer nextLayer){
		 double fIn= (IdealValue - outLayer.masNeyron[0].activeVal)*outLayer.masNeyron[0].activeVal;
		 	for (Neyron a: curentLayer.masNeyron){
		 		int iWiwght = 0; //Счетчик  нейрона(для перебора весов)
		 		double sigma = 0 ;
		 		for(Neyron b: nextLayer.masNeyron){
		 			 sigma = sigma + a.wieght[iWiwght]*b.sigma;
		 			 iWiwght++;
		 		}
		 		a.sigma = sigma;
		 		
		 	}
		
	}
	
	void CountGrad(Layer curentLayer, Layer nextLayer){
		
		
	 	for (Neyron a: curentLayer.masNeyron){
	 		a.grad = new double[nextLayer.masNeyron.length];
	 		int iGrad = 0;
	 		double grad = 0;
	 		
	 		for(Neyron b: nextLayer.masNeyron){
	 			grad = a.value * b.sigma;
	 			a.grad[iGrad] = grad;
	 			iGrad++;
	 			
	 		}
	 	}	
	} 
	
	void CountDelta(Layer curentLayer){
	
		int iDelta = 0;
		for (Neyron a: curentLayer.masNeyron){
			a.delta = new double [a.wieght.length];
			if (a.firstDelta == true){
				a.prevDelta = new double[a.delta.length];
				for(int j = 0;j<a.delta.length;j++){
					a.prevDelta[j]=0;
				}
				a.firstDelta = false;
			
			}
			for ( int g = 0; g<a.delta.length; g++){
			a.delta[g] = E_CONST * a.grad[g] + A_CONST*a.prevDelta[g];
			a.prevDelta[g] = a.delta[g]; 
			}
		}
	}
	
	void ChangeWeight(Layer curentLayer){
		for (Neyron a: curentLayer.masNeyron){
			 for(int i =0; i< a.wieght.length;i++){
				 a.wieght[i] = a.wieght[i] + a.delta[i];
			 }
		}
	}
}
