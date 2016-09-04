package ar.edu.utn.dds.poi.test;

import org.junit.Test;
import com.vividsolutions.jts.util.Assert;
import org.junit.Before;
import ar.edu.utn.dds.poi.service.AuthService;

public class Entrega3Test 
{	
	private AuthService authService;

	@Before
	public void setUp()
	{
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
}
