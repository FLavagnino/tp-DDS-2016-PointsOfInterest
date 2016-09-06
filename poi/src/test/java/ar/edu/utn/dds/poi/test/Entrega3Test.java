package ar.edu.utn.dds.poi.test;

import org.junit.Test;
import com.vividsolutions.jts.util.Assert;
import org.junit.Before;

import org.joda.time.*;

import ar.edu.utn.dds.poi.exception.InvalidUserException;
import ar.edu.utn.dds.poi.service.AuthService;
import ar.edu.utn.dds.poi.service.POIService;

public class Entrega3Test 
{	
	private AuthService authService;
	private POIService poiService;

	@Before
	public void setUp()
	{
		poiService = new POIService();
		authService = new AuthService();
	}
	
	@Test
	public void loginOKTest()
	{
		String userName = "luisk";
		String password = "1234";
		String token = "";
		
		token = this.authService.login(userName, password);
		Assert.isTrue(!token.isEmpty());
	}
	
	@Test
	public void loginNotOKTest()
	{
		String userName = "luisk";
		String password = "1111";
		String token = "";
		
		token = this.authService.login(userName, password);
		Assert.isTrue(token.isEmpty());
	}
	
	@Test
	public void tokenOKTest()
	{
		String userName = "luisk";
		String password = "1234";
		String token = "";
		boolean result = false;
		
		token = this.authService.login(userName, password);
		result = this.authService.validate(userName, token);
		
		Assert.isTrue(result);
	}
	
	@Test
	public void tokenNotOKTest()
	{
		String userName = "luisk";
		String password = "1234";
		String token = "";
		boolean result = false;
		
		this.authService.login(userName, password);
		
		token = "token-no-valido";
		result = this.authService.validate(userName, token);
		
		Assert.isTrue(!result);
	}
	
	@Test
	public void reportTotalSearchQtyByDateTest() throws InvalidUserException
	{
		String userName = "t_caballito";
		String password = "5555";
		String token = "";
		
		token = this.authService.login(userName, password);
		
		this.poiService.search("Rio", userName, token);
		this.poiService.search("Santander", userName, token);
		this.poiService.search("Boedo", userName, token);
		
		this.poiService.totalSearchQtyByDateReport(DateTime.now());
	}
}
