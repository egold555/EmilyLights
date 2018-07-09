package emilylights.http;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.Arrays;

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

			String response = getResponse(t.getRequestURI().toString().substring(1).split("/"));

			//add all the flags n stuff
			t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

			//No need to return anything else besides 200 cause yeah
			t.sendResponseHeaders(200, response.getBytes().length);

			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}

	private String getResponse(String[] urlParts) {

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
		}
		catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}


		return Arrays.toString(urlParts);
	}



}
