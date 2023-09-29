package Assignment;

public class Computer {
	private String brand;
	  private String model;
	  private long Sn;
	  private double price;
	  private static int sncounter =0;
	  
	  public Computer(String brand, String model, double price) {
		  brand = "Apple";
		  model = "mm";
		  price = 1600;
		  Sn = sncounter;
		  sncounter++;
	  }
	  
	  public Computer(String br,String md,long sn,double pr ) {
		  this.brand = br;
		  this.model= md;
		  this.price= pr;
		  this.Sn = sncounter;
		  sncounter++;
		  
	  }
	  public Computer(Computer copy) {
		  brand= copy.brand;
		  model= copy.model; 
		  price= copy.price;
		  Sn = sncounter;
		  sncounter++;
	  }


	public String getBrand() {
		return brand;
	}
	public void setBrand(String br) {
		brand = br;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String md) {
		model = md;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double pr) {
		price = pr;
	}
	  public int findNumofCreatedComputers() {
		  return sncounter;
	  }
	public long getSn() {
		return Sn;
	}

	public void setSn(long sn) {
		Sn = sn;
	}

	@Override
	public String toString() {
		return "Computer [brand=" + brand + ", model=" + model + ", Sn=" + Sn + ", price=" + price + ", sncounter="
				+ sncounter + "]";
	}
	  public void showInfo(Computer c) {
		  System.out.println("Brand = " + c.getBrand() + "model = "+ c.getModel() + "sn = " + Sn + "price " + c.getPrice() + "$");
	  }
	  public boolean equals(Computer eq) {
		  if(brand==eq.brand && model == eq.model && price ==eq.price) {
			  return true;	  
		  }
		  return false;
	  }
	

}

