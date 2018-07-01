package emilylights.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class WebServer {

	public void start() {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
			server.createContext("/", new MyHandler());
			server.setExecutor(null); // creates a default executor
			server.start();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	private class MyHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange t) throws IOException {

			String response = getResponse(t.getRequestURI().toString().substring(1).split("/"));

			//No need to return anything else besides 200 cause yeah
			t.sendResponseHeaders(200, response.getBytes().length);

			//add all the flags n stuff
			t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
			if (t.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
				t.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
				t.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
				t.sendResponseHeaders(204, -1);
				return;
			}


			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}

	private String getResponse(String[] urlParts) {
		return Arrays.toString(urlParts);
	}

}
