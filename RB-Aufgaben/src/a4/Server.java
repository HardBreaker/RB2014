package a4;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private int maxThreadCount;

	public static void main(String[] args) throws IOException {
		Server server = new Server(10);
		ServerSocket serverSocket = new ServerSocket(8080);
		server.listen(serverSocket);
	}

	public Server(int maxThreadCount) {
		this.maxThreadCount = maxThreadCount;
	}

	private void listen(ServerSocket serverSocket) {
		while (true) {
			Socket clientSocket = null;
			ExecutorService threadPool = Executors
					.newFixedThreadPool(maxThreadCount);
			try {
				clientSocket = serverSocket.accept();
				threadPool.execute(new ConnectionHandler(clientSocket));
			} catch (IOException IOEx) {
				IOEx.printStackTrace();
			}
		}
	}

	private static class ConnectionHandler extends Thread {
		private Socket clientSocket;
		HttpParser parser;

		public ConnectionHandler(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		public void run() {
			try {
				handleConnection();
			} catch (IOException IOEx) {
				IOEx.printStackTrace();
			}
		}

		private void handleConnection() throws IOException {
			parser = new HttpParser(clientSocket.getInputStream());
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
					true);
			System.out.println(parser.getHeaders().toString());
		}
	}

}
