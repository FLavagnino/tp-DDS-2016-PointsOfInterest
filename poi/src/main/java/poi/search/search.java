package poi.search;

import java.time.DayOfWeek;
import org.joda.time.*;
import poi.constant.Service;
import java.util.Scanner;
import poi.domain.*;

public class search {
		Scanner in = new Scanner(System.in);
		
		public void searchPOIBusStop(){
			
			String nameBus = in.nextLine();
			Coordenate busCoord = new Coordenate(-34.619160, -58.425443);
			
			BusStop poiBusStop = new BusStop(nameBus, busCoord );
			System.out.println("the bus stops are: " + poiBusStop.getName() + 
					poiBusStop.getAddress());
		}
		public void searchPOICategory(){
			String nameShop = in.nextLine();
			Coordenate shopCoord = new Coordenate(-34.619160, -58.425443);
			
			Shop poiShop = new Shop(nameShop, shopCoord );
			System.out.println("the shops are:" + poiShop.getName() + 
					poiShop.getAddress());
		}
		public void searchPOIPrimaryKey(){
			String primaryKey = in.nextLine();
			Coordenate primaryKeyCoord = new Coordenate(-34.619160, -58.425443);
			
			// POI poiPrimaryKey = new POI(primaryKey, primaryKeyCoord );
			// System.out.println("the shops are:" + poiPrimaryKey.getName() + 
			//		poiPrimaryKey.getAddress());
		}
		
		
}
