package emilylights.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import emilylights.scene.SceneHandler;

public class WebServer {

	private SceneHandler animationHandler;

	public WebServer(SceneHandler animationHandler) {
		this.animationHandler = animationHandler;
	}

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

			List<String> body = new ArrayList<String>(); 
			String line; 
			BufferedReader r = new BufferedReader(new InputStreamReader(t.getRequestBody())); 
			while ((line=r.readLine()) != null) { 
				body.add(line); 
			} 
			System.out.println("Body: " + Arrays.toString(body.toArray(new String[0]))); 
			
			String theBody = null;
			if(body.size()> 0) {
				theBody= body.get(0);
			}

			String response = getResponse(t.getRequestURI().toString().substring(1).split("/"), theBody);

			//add all the flags n stuff
			t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

			//No need to return anything else besides 200 cause yeah
			t.sendResponseHeaders(200, response.getBytes().length);

			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}

	private String getResponse(String[] urlParts, String body) {

		System.out.println("Request got: " + Arrays.toString(urlParts));

		try {
			if(urlParts[0].equals("scenes.json")) {
				return new String(Files.readAllBytes(new File("files\\scenes.json").toPath()));
			}
			else if(urlParts[0].equals("set")) {
				animationHandler.setScene(Integer.parseInt(urlParts[1]));
			}
			else if(urlParts[0].equals("delete")) {
				animationHandler.deleteScene(Integer.parseInt(urlParts[1]));
			}
			else if(urlParts[0].equals("add")) {
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}


		return Arrays.toString(urlParts);
	}



}
