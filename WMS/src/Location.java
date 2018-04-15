
public class Location {

	private String city;
	private int x;
	private int y;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public double calcDistance(Location city){
		double distance =  Math.sqrt(((this.getX()-city.getX())*(this.getX()-city.getX()))+((this.getY()-city.getY())*(this.getY()-city.getY())));
		return distance;
	}
	
	
}