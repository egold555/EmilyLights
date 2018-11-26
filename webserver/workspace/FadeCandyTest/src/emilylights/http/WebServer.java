package emilylights.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Inet4Address;
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
	public static InetSocketAddress IP_AND_PORT;

	public WebServer(SceneHandler animationHandler) {
		this.animationHandler = animationHandler;
	}

	public void start(String ip) {
		try {
			IP_AND_PORT = new InetSocketAddress(ip, 8000); //get correct 1.9 or 10. address and not 0.0.0.0
			System.out.println("Host: " + IP_AND_PORT.getHostName() + ":" + IP_AND_PORT.getPort());
			HttpServer server = HttpServer.create(IP_AND_PORT, 0);
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
			//System.out.println("Body: " + Arrays.toString(body.toArray(new String[0]))); 

			String theBody = null;
			if(body.size()> 0) {
				theBody= body.get(0);
			}

			String[] urlParts = t.getRequestURI().toString().substring(1).split("/");

			OutputStream os = t.getResponseBody();

			//add all the flags n stuff
			t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

			if(urlParts.length > 1 && urlParts[0].equals("previmgs")){
				try {
					File file = new File("files"+ File.separator + "previmgs" + File.separator + urlParts[1] + ".gif");
					if(file.exists()) {
						t.sendResponseHeaders(200, file.length());
						Files.copy(file.toPath(), os);
					} 
					else {
						String response = "Could not find " + urlParts[1];
						t.sendResponseHeaders(200, response.getBytes().length);
						os.write(response.getBytes());
						System.err.println(response);
					}
				}
				catch(Exception e) {
					e.printStackTrace();
					String response = "Could not find " + urlParts[1];
					t.sendResponseHeaders(404, response.getBytes().length);
					os.write(response.getBytes());
					System.err.println(response);
				}
			} 
			else if(urlParts[0].equals("www")) {
				
				String joined = String.join(File.separator, urlParts);
				
				int i = joined.indexOf("?");
				if(i > 0) {
					joined = joined.substring(0, i);
				}
				
				if(urlParts.length == 1) {
					joined = "www"+ File.separator + "index.html";
				}

				File file = new File("files" + File.separator + joined);
				
				if(file.exists()) {
					t.sendResponseHeaders(200, file.length());
					Files.copy(file.toPath(), os);
				} 
				else {
					String response = "Could not find " + urlParts[1];
					t.sendResponseHeaders(404, response.getBytes().length);
					os.write(response.getBytes());
					System.err.println(response);
				}
				
			}
			else {
				String response = getResponse(urlParts, theBody);
				t.sendResponseHeaders(200, response.getBytes().length);
				os.write(response.getBytes());
			}

			os.close();
		}
	}

	private String getResponse(String[] urlParts, String body) {

		//System.out.println("Request got: " + Arrays.toString(urlParts));

		try {
			if(urlParts[0].equals("scenes.json")) {
				return new String(Files.readAllBytes(new File("files" + File.separator +"scenes.json").toPath()));
			}
			else if(urlParts[0].equals("set")) {
				animationHandler.setScene(Integer.parseInt(urlParts[1]));
			}
			else if(urlParts[0].equals("delete")) {
				animationHandler.deleteScene(Integer.parseInt(urlParts[1]));
			}
			else if(urlParts[0].equals("add")) {
				animationHandler.addScene(body);
			}
			else if(urlParts[0].equals("preview")) {
				animationHandler.previewScene(body);
			}
			else if(urlParts[0].equals("scenedata")) {
				animationHandler.sceneData(body, urlParts);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}


		return Arrays.toString(urlParts);
	}



}
