package adapter;

public class CorreoLegacy {
    public void send_email(String to, String body) {
        System.out.println("Para: " + to);
        System.out.println(body);
    }
}
