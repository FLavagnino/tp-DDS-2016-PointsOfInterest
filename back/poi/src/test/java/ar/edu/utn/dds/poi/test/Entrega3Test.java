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
		authService = new AuthService();
		reportService = new ReportService();
		poiService = new POIService();
		
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

		reportService.setSearchResults(searches);
	}
	
	@Test
	// Prueba del login. Se ingresa un usuario valido y se espera que el login sea
	// satisfactorio.
	public void loginOKTest()
	{
		userName = "luisk";
		password = "1234";
		token = "";
		
		token = authService.login(userName, password);
		Assert.isTrue(!token.isEmpty());
	}
	
	@Test
	// Prueba del login. Se ingresa un usuario invalido y se espera que el login sea
	// falle.
	public void loginNotOKTest()
	{
		userName = "luisk";
		password = "1111";
		token = "";
		
		token = authService.login(userName, password);
		Assert.isTrue(token.isEmpty());
	}
	
	@Test
	// Prueba para controlar que el token se este generando correctamente al momento
	// de hacer el login al sistema.
	public void tokenOKTest()
	{
		userName = "luisk";
		password = "1234";
		token = "";
		boolean result = false;
		
		token = authService.login(userName, password);
		result = authService.validate(userName, token);
		
		Assert.isTrue(result);
	}
	
	@Test
	// Prueba para vlaidar el token sobre un usuario. Se ingresa un token invalido y
	// se espera que la validacion falle.
	public void tokenNotOKTest()
	{
		String userName = "luisk";
		String password = "1234";
		String token = "";
		boolean result = false;
		
		authService.login(userName, password);
		
		token = "token-no-valido";
		result = authService.validate(userName, token);
		
		Assert.isTrue(!result);
	}
	
	@Test
	// Prueba para generar el reporte de busquedas por dia. Imprime el resultado por consola.
	public void reportTotalSearchQtyByDateTest()
	{	
		reportService.totalSearchQtyByDate();
	}
	
	@Test
	//Prueba para generar el reporte de busquedas parciales por usuario. Imprime el resultado por consola.
	public void partialSearchQtyByUserTest()
	{	
		reportService.partialSearchQtyByUser();
	}
	
	@Test
	//Prueba para generar el reporte de busquedas por usuario. Imprime el resultado por consola.
	public void totalSearchQtyByUserTest()
	{	
		reportService.totalSearchQtyByUser();
	}
	
	@Test
	//Prueba para generar una busqueda con auditoria. Graba en el sistema los resultados.
	public void searchWithAuditTest() throws InvalidUserException
	{
		userName = "t_caballito";
		password = "5555";
		token = "";
		
		token = authService.login(userName, password);
		poiService.search("prueba_audit", userName, token);	
		reportService.totalSearchQtyByDate();
	}
	
	@Test
	//Prueba para generar una busqueda sin auditoria. No graba nada.
	public void searchWithoutAuditTest() throws InvalidUserException
	{
		userName = "luisk";
		password = "1234";
		token = "";
		
		token = authService.login(userName, password);
		poiService.search("prueba_no_audit", userName, token);
		reportService.totalSearchQtyByDate();
	}
}
