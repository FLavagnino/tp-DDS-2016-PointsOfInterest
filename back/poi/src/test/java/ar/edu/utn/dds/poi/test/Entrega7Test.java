package ar.edu.utn.dds.poi.test;

import ar.edu.utn.dds.poi.domain.*;
import ar.edu.utn.dds.poi.service.Historical;
import ar.edu.utn.dds.poi.utils.MongoUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Entrega7Test {
	private MongoUtil mongoUtil;

	@Before
	public void setUp() {
		mongoUtil = new MongoUtil();
	}

	@Test
	public void saveAndRemovePOI() {
		Coordenate coordenate = new Coordenate(-34.619109, -58.425454);
		BusStop busStop = new BusStop("Linea 84", coordenate, 84, "Colectivo,Linea84,84,Parada");

		Assert.assertFalse(mongoUtil.existPoi(busStop.getName()));

		mongoUtil.savePoi(busStop);

		Assert.assertTrue(mongoUtil.existPoi(busStop.getName()));

		Assert.assertEquals(1, mongoUtil.getPois(busStop.getName()).size());

		mongoUtil.removePoi(busStop);

		Assert.assertFalse(mongoUtil.existPoi(busStop.getName()));
		Assert.assertEquals(0, mongoUtil.getPois(busStop.getName()).size());
	}

	@Test
	public void lala() {
		Historical historical = new Historical();
		historical.search("Rio", "Facundo");
	}
}