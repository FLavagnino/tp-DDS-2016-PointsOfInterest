package ar.edu.utn.dds.poi.constant;

public class Constant
{
	private Constant(){ };
	
	public static final int maxlength = 2;
    public static final int LEVENSHTEIN_ACCEPTED_DIST = 2;
    
    // Web Service
    public final static String BANK_SEARCH_URL = "http://trimatek.org/Consultas/banco";
    public final static String BANK_SEARCH_ALL_PARAMS = "?banco=%s&servicio=%s";
    public final static String BANK_SEARCH_SERVICE_PARAM = "?servicio=%s";
    public final static String BANK_SEARCH_BANK_PARAM = "?banco=%s";
    public final static String CGP_SEARCH_URL = "http://trimatek.org/Consultas/centro";
    public final static String CGP_SEARCH_PARAM = "?zona=%s";
    public final static String GOOGLE_DIST_URL = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s,%s&destinations=%s,%s&mode=walking&language=es&key=AIzaSyCaiXB_SY0hgO5l3sLfXnx_L4lB2GRTWOQ";
    public final static String HTTP_SERVICE_STATUS_MSG = "Status code: ";
    public final static int HTTP_SERVICE_STATUS_CODE_BAD_REQUEST = 400;
    public final static String HTTP_SERVICE_ERROR_MSG = "Error getting an InputStreamReader";
    public final static String POISERVICE_AUTH_ERROR_MSG = "Authentication error: Please do the login again.";
    public final static String POISERVICE_INVALID_POI_MSG = "Invalid POI. Please check all the coordenates and the name.";
    public final static String ACTION_ADDED_TO_USER = "Action list has been updated";
    
    // Domain
	public final static int BANK_ISCLOSERTO_DIST = 500;
	public final static int BUSSTOP_ISCLOSERTO_DIST = 100;
	public final static int JODATIME_COMPARE_YEAR = 1;
	public final static int JODATIME_COMPARE_MONTH = 1;
	public final static int JODATIME_COMPARE_DAY = 1;
	public final static String EMAIL = "ar.edu.utn.dds.poi@gmail.com";
	public final static String PASSWORD = "!S1st3m4s"; 
	
	// Utils
	public final static double HAVERSINE_EARTH_RADIUS_METERS = 6372000.8; 
	public final static String METERS_FROM_TO_VALUE_STRING = "value"; 
	public final static String JSONFACTORY_FROMJSON_ERROR_MSG = "Error reading an InputStreamReader";
	
	// Audit
	public final static int MAX_SEARCH_TIME = 1000;
	public final static String MAIL_SMTP_AUTH_VALUE = "true";
	public final static String MAIL_SMTP_STARTTLS_ENABLE_VALUE = "true";
	public final static String MAIL_SMTP_HOST_VALUE = "smtp.gmail.com";
	public final static String MAIL_SMTP_PORT_VALUE = "587";
	public final static String MAIL_SUBJECT = "[DDS-POI] Search Time Notification";
	public final static String MAIL_TEXT = "The search service has exceded the default time.";
	public final static String MAIL_ERROR = "Could not send email to ";
	
	// Jobs
	public final static String UPDATE_SHOP_FILE_PATH = "C:\\test\\update.txt";
	public final static String UPDATE_SHOP_FILE_NOT_FOUND_ERROR_MSG = "File not found";
	public final static String UPDATE_SHOP_FILE_ERROR_MSG = "File error";
	public final static String UPDATE_SHOP_FILE_INVALID_POI_ERROR_MSG = "Invalid POI.";
}
