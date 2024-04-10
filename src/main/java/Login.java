import javax.security.auth.login.LoginException;

public class Login {
    // ----- Atributos -----
    public String[][] usuarios;
    public boolean logeado;
    public int contador = 0;

    // ----- Registrar usuario -----
    public void registrarUsuario(String usuario, String password) {
        try {
            usuarios[contador][0] = usuario;
            usuarios[contador][1] = password;
            contador++;
        } catch (IllegalArgumentException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
        }
    }



    // ----- Encontrar usuario -----
    public int encontrarUsuario(String usuario) {
        try {
            for (int i = 0; i < contador; i++) {
                if (usuarios[i][0].equals(usuario)) {
                    return i;
                }
            }
        } catch (NullPointerException e) {
            System.err.println("Error al encontrar usuario");
        }
        return -1;
    }

    // ----- Validar Password ----

    public boolean validarPassword(int posicionUsuario, String password) {
        boolean validacion = false;
        if (posicionUsuario > -1)
            validacion = usuarios[posicionUsuario][1].equals(password);
       return validacion;
    }

    // ----- Verificar existencia -----
    public boolean verificarExistencia(String usuario, String password) {
        return validarPassword(encontrarUsuario(usuario), password);
    }


    // ----- Manejar error -----
    private void manejarError(String mensaje) {
        System.err.println("Error al verificar credenciales: " + mensaje);
    }


    // ----- Iniciar Sesión -----
    public void iniciarSesion(String usuario, String password) {
        try {
            if (!verificarExistencia(usuario, password)) {
                throw new LoginException("Nombre de usuario o contraseña incorrectos");
            }
            logeado = true;
        } catch (LoginException e) {
            System.err.println("Error al iniciar sesión: " + e.getMessage());
        }
    }


}
