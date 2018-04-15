public class Calendar {
private int Space[][]=new int[5][90];

public int getbooking(int id, int day) {
	return Space[id][day];
}
public void setbooking(int id, int day, int volume) {
	this.Space[id][day]=Space[id][day]+volume;
}
}
