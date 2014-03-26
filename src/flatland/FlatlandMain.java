package flatland;

import com.google.gson.Gson;

public class FlatlandMain {
	
	public static void main(String[] args) {
		Board world = new Board();
		System.out.println(new Gson().toJson(world));
	}
}
