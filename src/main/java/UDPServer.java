import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class UDPServer {
    private final DatagramSocket socket;
    private final List<Client> clients;

    public UDPServer(String host) {
        try {
            socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName(host);
            clients = new ArrayList<>();
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(byte[] message) {

    }

    public void stream() {

    }
}
