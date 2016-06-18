package poi.web.service;

import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.eclipse.jetty.io.RuntimeIOException;

public class HttpService {

	public InputStreamReader getInputStreamReaderOf(String url) {

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		HttpResponse response;
		try {
			response = client.execute(request);
		} catch (IOException e) {
			throw new RuntimeIOException(e);
		}
		int statusCode = response.getStatusLine().getStatusCode();
		if(statusCode >= 400) {
			throw new RuntimeException("Status code: " + statusCode);
		}
		HttpEntity entity = response.getEntity();
		InputStreamReader stream;
		try {
			stream = new InputStreamReader(entity.getContent());
		} catch (IOException e) {
			throw new RuntimeException("Error getting an InputStreamReader", e);
		}
		return stream;
	}
}
