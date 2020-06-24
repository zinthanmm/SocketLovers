package server;

import java.io.DataInputStream;
import java.net.Socket;

public class ChatService implements Runnable {

	Socket socket;
	DataInputStream in;
	String name;
	User user = new User();

	public ChatService(User user, Socket socket) throws Exception {
		this.user = user;
		this.socket = socket;

		in = new DataInputStream(socket.getInputStream());

		this.name = in.readUTF(); 
		user.AddClient(name, socket);
	}

	public void run() {
		try {
			while (true) {
				String msg = in.readUTF();
				user.sendMsg(msg, name);
			}
		} catch (Exception e) {
			user.RemoveClient(this.name);
		}
	}
}
