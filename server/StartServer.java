package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Platform;

public class StartServer implements Runnable {
	Socket socket = null; 
	User user = new User(); 
	ServerSocket server_socket = null;

	int count = 0;
	Thread thread[] = new Thread[10];

	@Override
	public void run() {
		try {
			server_socket = new ServerSocket(8080);
			while (true) {
				socket = server_socket.accept(); // wait for requests from clients
				Date d1 = new Date();
				SimpleDateFormat df = new SimpleDateFormat("MM/dd/YYYY HH:mm a");
				String formattedDate = df.format(d1);
				
				Platform.runLater(() -> {
					ChatServer.logs.setText(ChatServer.logs.getText() + "\nNew Client Connected. " 
							+ formattedDate);
				});

				thread[count] = new Thread(new ChatService(user, socket));
				thread[count].start();
				count++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
