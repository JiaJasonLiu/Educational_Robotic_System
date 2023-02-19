package Steps;

//this class sets and gets the direction and the distance
public class Step {

	private String dir;
	private int dis;

	public Step(String dir, int dis) {
		this.setDir(dir);
		this.setDis(dis);
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public int getDis() {
		return dis;
	}

	public void setDis(int dis) {
		this.dis = dis;
	}

	public String toString() {
		return this.getDir() + ":" + this.getDis();
	}
}
