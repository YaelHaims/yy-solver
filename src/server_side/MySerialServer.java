package server_side;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MySerialServer implements Server {

	private boolean stop;
	
	@Override
	public void open(int port, ClientHandler c) {
		stop = false;

		new Thread(() -> {
			try {
				runServer(port, c);
			} catch (Exception e) {e.printStackTrace();}
		}).start();
	}
	
	private void runServer(int port, ClientHandler clientHandler) throws Exception {
		ServerSocket server = new ServerSocket(port);
		while (!stop) {
			try {
				Socket socket = server.accept();
				clientHandler.handleClient(socket.getInputStream(), socket.getOutputStream());
				
				socket.close();
				
			} catch (Exception e) {e.printStackTrace();}
			
		}
		server.close();
	}

	@Override
	public void stop() {
		stop = true;
	}

}
