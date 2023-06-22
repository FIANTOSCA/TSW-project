package ton.unisa;

import java.io.Serializable;

/*JavaBean per il prodotto con i suoi getter e setter*/
public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;

	int ID;
	String NOME;
	String CATEGORIA;
	int QTA_DISP;
	String MARCA_MOD;
	float PREZZO;

	public ProductBean(){
		ID = -1;
		NOME = "";
		CATEGORIA = "";
		QTA_DISP = 0;
		MARCA_MOD="";
		PREZZO= -1;
	}

	

	public int getID() {
		return ID;
	}



	public void setID(int iD) {
		ID = iD;
	}



	public String getNOME() {
		return NOME;
	}



	public void setNOME(String nOME) {
		NOME = nOME;
	}



	public String getCATEGORIA() {
		return CATEGORIA;
	}



	public void setCATEGORIA(String cATEGORIA) {
		CATEGORIA = cATEGORIA;
	}



	public int getQTA_DISP() {
		return QTA_DISP;
	}



	public void setQTA_DISP(int qTA_DISP) {
		QTA_DISP = qTA_DISP;
	}



	public String getMARCA_MOD() {
		return MARCA_MOD;
	}



	public void setMARCA_MOD(String mARCA_MOD) {
		MARCA_MOD = mARCA_MOD;
	}



	public float getPREZZO() {
		return PREZZO;
	}


	public void setPREZZO(float pREZZO) {
		PREZZO = pREZZO;
	}



	@Override
	public String toString() {
		return "ProductBean [ID=" + ID + ", NOME=" + NOME + ", CATEGORIA=" + CATEGORIA + ", QTA_DISP=" + QTA_DISP
				+ ", MARCA_MOD=" + MARCA_MOD + ", PREZZO=" + PREZZO + "]";
	}



	
	
}	


