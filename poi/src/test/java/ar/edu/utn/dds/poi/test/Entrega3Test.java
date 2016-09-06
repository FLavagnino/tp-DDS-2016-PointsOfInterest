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
	private String userName;
	private String password;
	private String token;

	@Before
	public void setUp()
	{
		poiService = new POIService();
		authService = new AuthService();
	}
	
	@Test
	public void loginOKTest()
	{
		userName = "luisk";
		password = "1234";
		token = "";
		
		token = this.authService.login(userName, password);
		Assert.isTrue(!token.isEmpty());
	}
	
	@Test
	public void loginNotOKTest()
	{
		userName = "luisk";
		password = "1111";
		token = "";
		
		token = this.authService.login(userName, password);
		Assert.isTrue(token.isEmpty());
	}
	
	@Test
	public void tokenOKTest()
	{
		userName = "luisk";
		password = "1234";
		token = "";
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
		int userid = 1;
		String password = "5555";
		String token = "";
		
		token = this.authService.login(userName, password);
		
		this.poiService.search("Rio", userid, token);
		this.poiService.search("Santander", userid, token);
		this.poiService.search("Boedo", userid, token);
		
		this.poiService.totalSearchQtyByDateReport(DateTime.now());
	}
}
