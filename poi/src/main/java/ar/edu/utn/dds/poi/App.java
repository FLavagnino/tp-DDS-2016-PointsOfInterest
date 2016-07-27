package ar.edu.utn.dds.poi;

public class App {

	public static void main(String[] args) {
	
		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s,%s&destinations=%s,%s&mode=walking&language=es&key=AIzaSyCaiXB_SY0hgO5l3sLfXnx_L4lB2GRTWOQ";
		
		
		System.out.println(String.format(url, "-34.605796", "-58.453531", "-34.608766", "-58.436654"));
	}
}
