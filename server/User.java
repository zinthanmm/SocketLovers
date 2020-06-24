package server;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import javafx.application.Platform;

public class User {
	
	HashMap<String, DataOutputStream> clientmap = new HashMap<String, DataOutputStream>();

	String str; 

	public synchronized void AddClient(String name, Socket socket) {
		try {
			clientmap.put(name, new DataOutputStream(socket.getOutputStream()));
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public synchronized void RemoveClient(String name) {
		try {
			clientmap.remove(name);
			sendMsg(name + " exit.", "Server");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public synchronized void sendMsg(String msg, String name) throws Exception {
		
		str = name + " : " + msg;
		Platform.runLater(() -> {
			ChatServer.logs.setText(ChatServer.logs.getText() + "\n" + str);
		});

		int j = 0;

		char[] chars = msg.toCharArray();
		int z = chars.length;
		char[] mydechar = new char[z];
		for (char f : chars) {

			f -= 5;
			mydechar[j] = f;
			j += 1;
		}

		String demsg = new String(mydechar);
		

	
		Iterator iterator = clientmap.keySet().iterator();
		while (iterator.hasNext()) {
			String clientname = (String) iterator.next();
			clientmap.get(clientname).writeUTF(name + " : " + demsg); 
		}
	}
}
