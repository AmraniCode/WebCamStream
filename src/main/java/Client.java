import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.*;

public class Client extends Thread {
    String name;
    int port;

    public Client(String name, int port) {
        this.name = name;
        this.port = port;
    }

    @Override
    public void run() {
        JFrame frame = new JFrame();
        frame.setBounds(0, 0, 700, 500);
        ImageIcon imageIcon = new ImageIcon();
        frame.add(new JLabel(imageIcon));
        frame.setVisible(true);
        try (DatagramSocket socket = new DatagramSocket(4242, InetAddress.getByName("0.0.0.0"))) {
            byte[] buffer = new byte[99999];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while (socket.isBound()) {
                socket.receive(packet);
                byte[] buff = packet.getData();
                ByteArrayInputStream bain = new ByteArrayInputStream(buff);
                BufferedImage image = ImageIO.read(bain);
                imageIcon.setImage(image);
                frame.repaint();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 4242;
        new Client(port + "", port).start();
    }
}
