// ClientAgent.java
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class ClientAgent {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new SendDataTask(), 0, 5000); // Send data every 5 seconds
    }

    static class SendDataTask extends TimerTask {
        public void run() {
            try {
                URL url = new URL("http://localhost:8080/data");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                String jsonInputString = "{\"metric\": 100}";
                try(OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
                int code = conn.getResponseCode();
                System.out.println("Response code: " + code);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
