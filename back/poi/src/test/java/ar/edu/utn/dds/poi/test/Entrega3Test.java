package ar.edu.utn.dds.poi.test;

import org.junit.Test;
import com.vividsolutions.jts.util.Assert;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.*;

import ar.edu.utn.dds.poi.domain.Admin;
import ar.edu.utn.dds.poi.domain.Log;
import ar.edu.utn.dds.poi.domain.Terminal;
import ar.edu.utn.dds.poi.domain.User;
import ar.edu.utn.dds.poi.exception.InvalidUserException;
import ar.edu.utn.dds.poi.service.*;

public class Entrega3Test 
{	
	private AuthService authService;
	private ReportService reportService;
	private POIService poiService;
	private String userName;
	private String password;
	private String token;

	@Before
	public void setUp()
	{
		this.authService = new AuthService();
		this.reportService = new ReportService();
		this.poiService = new POIService();
		
		List<User> userList = new ArrayList<User>();
		
		User aLuis = new Admin();
		aLuis.setUserName("luisk");
		aLuis.setPassword("1234");
		aLuis.setToken("");
		aLuis.setAuditMode(false);
		aLuis.setEmail("luiskahrs@gmail.com");
		userList.add(aLuis);
		
		User aJuan = new Admin();	
		aJuan.setUserName("juanc");
		aJuan.setPassword("1111");
		aJuan.setToken("");
		aJuan.setAuditMode(false);
		aJuan.setEmail("luiskahrs@gmail.com");
		userList.add(aJuan);
		
		User aFacuL = new Admin();
		aFacuL.setUserName("facul");
		aFacuL.setPassword("2222");
		aFacuL.setToken("");
		aFacuL.setAuditMode(false);
		aFacuL.setEmail("luiskahrs@gmail.com");
		userList.add(aFacuL);
		
		User aFacuB = new Admin();
		aFacuB.setUserName("facub");
		aFacuB.setPassword("3333");
		aFacuB.setToken("");
		aFacuB.setAuditMode(false);
		aFacuB.setEmail("luiskahrs@gmail.com");
		userList.add(aFacuB);
		
		User tAbasto = new Terminal();
		tAbasto.setUserName("t_abasto");
		tAbasto.setPassword("4444");
		tAbasto.setToken("");
		tAbasto.setAuditMode(true);
		tAbasto.setEmail("luiskahrs@gmail.com");
		userList.add(tAbasto);
		
		User tCaballito = new Terminal();	
		tCaballito.setUserName("t_caballito");
		tCaballito.setPassword("5555");
		tCaballito.setToken("");
		tCaballito.setAuditMode(true);
		tCaballito.setEmail("luiskahrs@gmail.com");
		userList.add(tCaballito);
		
		User tBoedo = new Terminal();	
		tBoedo.setUserName("t_boedo");
		tBoedo.setPassword("6666");
		tBoedo.setToken("");
		tBoedo.setAuditMode(true);
		tBoedo.setEmail("luiskahrs@gmail.com");
		userList.add(tBoedo);
		
		//this.authService.setUsers(userList);
		
		List<Log> searches = new ArrayList<Log>();
		DateTime date = new DateTime(2016, 6, 13, 0, 0, 0, 0);
		Log histSearch1 = new Log("t_abasto", "filtro abasto", 20, 15000, date);
		searches.add(histSearch1);
		date = new DateTime(2016, 6, 14, 0, 0, 0, 0);
		Log histSearch2 = new Log("t_abasto", "filtro abasto", 30, 12000, date);
		searches.add(histSearch2);		
		date = new DateTime(2016, 6, 15, 0, 0, 0, 0);
		Log histSearch3 = new Log("t_abasto", "filtro abasto", 42, 16000, date);
		searches.add(histSearch3);	
		
		date = new DateTime(2016, 6, 13, 0, 0, 0, 0);
		Log histSearch4 = new Log("t_caballito", "filtro caballito", 25, 15000, date);
		searches.add(histSearch4);
		date = new DateTime(2016, 6, 14, 0, 0, 0, 0);
		Log histSearch5 = new Log("t_caballito", "filtro caballito", 102, 12000, date);
		searches.add(histSearch5);		
		date = new DateTime(2016, 6, 15, 0, 0, 0, 0);
		Log histSearch6 = new Log("t_caballito", "filtro caballito", 35, 16000, date);
		searches.add(histSearch6);	
		
		date = new DateTime(2016, 6, 13, 0, 0, 0, 0);
		Log histSearch7 = new Log("t_boedo", "filtro boedo", 23, 15000, date);
		searches.add(histSearch7);
		date = new DateTime(2016, 6, 14, 0, 0, 0, 0);
		Log histSearch8 = new Log("t_boedo", "filtro boedo", 58, 12000, date);
		searches.add(histSearch8);		
		date = new DateTime(2016, 6, 15, 0, 0, 0, 0);
		Log histSearch9 = new Log("t_boedo", "filtro boedo", 15, 16000, date);
		searches.add(histSearch9);	

		this.reportService.setSearchResults(searches);
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
	public void reportTotalSearchQtyByDateTest()
	{	
		this.reportService.totalSearchQtyByDate();
	}
	
	@Test
	public void partialSearchQtyByUserTest()
	{	
		this.reportService.partialSearchQtyByUser();
	}
	
	@Test
	public void totalSearchQtyByUserTest()
	{	
		this.reportService.totalSearchQtyByUser();
	}
	
	@Test
	public void searchWithAuditTest() throws InvalidUserException
	{
		userName = "t_caballito";
		password = "5555";
		token = "";
		
		token = this.authService.login(userName, password);
		this.poiService.search("prueba_audit", userName, token);	
		this.reportService.totalSearchQtyByDate();
	}
	
	@Test
	public void searchWithoutAuditTest() throws InvalidUserException
	{
		userName = "luisk";
		password = "1234";
		token = "";
		
		token = this.authService.login(userName, password);
		this.poiService.search("prueba_no_audit", userName, token);
		this.reportService.totalSearchQtyByDate();
	}
}
