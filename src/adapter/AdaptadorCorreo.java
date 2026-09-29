package adapter;

public class AdaptadorCorreo implements Notificador {
    private CorreoLegacy correo;

    public AdaptadorCorreo(CorreoLegacy correo) {
        this.correo = correo;
    }

    @Override
    public void enviar(String destino, String mensaje) {
        this.correo.send_email(destino, mensaje);
    }
}
