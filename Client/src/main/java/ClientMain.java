import network.NetworkManager;
import view.MainView;

public class ClientMain {
    public static void main(String[] args) {
        // Start client's connection to the server
        NetworkManager.getInstance().startServerConnection();
    }
}
