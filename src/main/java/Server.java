import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public Server() {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            socket.setBroadcast(true);

            Webcam webcam = Webcam.getDefault();
            webcam.setViewSize(WebcamResolution.VGA.getSize());

            WebcamPanel panel = new WebcamPanel(webcam);
            panel.setFPSDisplayed(true);
            panel.setDisplayDebugInfo(true);
            panel.setImageSizeDisplayed(true);
            //panel.setMirrored(true);

            JFrame window = new JFrame("Test webcam panel");
            window.add(panel);
            window.setResizable(true);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.pack();
            window.setVisible(true);

            int port = 4242;
            while (webcam.isOpen()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(webcam.getImage(), "jpg", baos);
                byte[] image_buffer = baos.toByteArray();
                DatagramPacket packet = new DatagramPacket(image_buffer, image_buffer.length, address, port);
                socket.send(packet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
