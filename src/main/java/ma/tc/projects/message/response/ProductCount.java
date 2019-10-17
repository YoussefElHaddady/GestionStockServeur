package ma.tc.projects.message.response;

import ma.tc.projects.entity.Magasin;

public class ProductCount {

	private Magasin magasin;
	private int product_count;
	
	
	public ProductCount(Magasin magasin, int product_count) {
		super();
		this.magasin = magasin;
		this.product_count = product_count;
	}

	public Magasin getMagasin() {
		return magasin;
	}


	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}


	public int getProduct_count() {
		return product_count;
	}


	public void setProduct_count(int product_count) {
		this.product_count = product_count;
	}
	
	
}
