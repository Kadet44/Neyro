package def;

public interface Layer_Interface {
	
	
	Neyron[] getLayer(int numLayer);
	//Задать случайные веса 
	
	void setRadomWeight(Layer nextlayer);

}
