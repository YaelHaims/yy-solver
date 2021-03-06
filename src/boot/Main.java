package boot;

import server_side.CacheManager;
import server_side.FileCacheManager;
import server_side.MyClientHandler;
import server_side.MySerialServer;
import server_side.MyTestClientHandler;
import server_side.StringReverser;

public class Main {

	public static void main(String[] args) {
		MySerialServer server = new MySerialServer();
		int port = 9000;
		MyClientHandler c = new MyClientHandler();
		server.open(port, c);
	}
}
