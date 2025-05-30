import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Martinez_Raquel_Juego extends JFrame {
    public Martinez_Raquel_Juego() {
        setTitle("Juego de Adivinar Palabra");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] palabras = {
            "HONDURAS", "ELEFANTE", "PROGRAMAR", "LENGUAJE", "SISTEMA",
            "INTERNET", "ESCOLAR", "EDUCANDO", "TECLADO", "VENTANA"
        };

        JPanel panelinicio = new JPanel(new GridLayout(3, 1));
        JButton btnjugar = new JButton("Jugar");
        JButton btncambio = new JButton("Cambiar Palabras");
        JButton btnSalir = new JButton("Salir");

        add(panelinicio, BorderLayout.CENTER);
        panelinicio.add(btnjugar);
        panelinicio.add(btncambio);
        panelinicio.add(btnSalir);

        revalidate();
        repaint();

        btnjugar.addActionListener(e -> {
            getContentPane().removeAll();

            Random rand = new Random();
            String palabra = palabras[rand.nextInt(10)];
            char[] oculta = new char[palabra.length()];
            for (int i = 0; i < oculta.length; i++) oculta[i] = '_';

            int[] oportunidades = {5};

            JPanel panelJuego = new JPanel(new GridLayout(5, 1));
            JLabel lblPalabra = new JLabel(String.valueOf(oculta).replace("", " ").trim(), SwingConstants.CENTER);
            JLabel lblMensaje = new JLabel("Oportunidades: 5", SwingConstants.CENTER);
            JTextField txtLetra = new JTextField();
            JButton btnProbar = new JButton("Probar");

            panelJuego.add(lblPalabra);
            panelJuego.add(lblMensaje);
            panelJuego.add(new JLabel("Ingresa una letra:", SwingConstants.CENTER));
            panelJuego.add(txtLetra);
            panelJuego.add(btnProbar);

            add(panelJuego, BorderLayout.CENTER);
            revalidate();
            repaint();

            btnProbar.addActionListener(ae -> {
                String input = txtLetra.getText().toUpperCase();
                txtLetra.setText("");
                if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                    lblMensaje.setText("Ingresa solo UNA letra.");
                    return;
                }

                char letra = input.charAt(0);
                boolean correctas = false;

                for (int i = 0; i < palabra.length(); i++) {
                    if (palabra.charAt(i) == letra && oculta[i] == '_') {
                        oculta[i] = letra;
                        correctas = true;
                    }
                }

                if (!correctas) {
                    oportunidades[0]--;
                    lblMensaje.setText("Incorrecto. Oportunidades: " + oportunidades[0]);
                } else {
                    lblMensaje.setText("¡Le pegaste! Oportunidades: " + oportunidades[0]);
                }

                lblPalabra.setText(String.valueOf(oculta).replace("", " ").trim());

                if (String.valueOf(oculta).equals(palabra)) {
                    JOptionPane.showMessageDialog(null, ":P ¡Ganaste! La palabra era: " + palabra);
                    getContentPane().removeAll();
                    add(panelinicio, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                } else if (oportunidades[0] == 0) {
                    JOptionPane.showMessageDialog(null, ":O Perdiste. La palabra era: " + palabra);
                    getContentPane().removeAll();
                    add(panelinicio, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                }
            });
        });

        btncambio.addActionListener(e -> {
            JPanel panelCambiar = new JPanel(new GridLayout(12, 1));
            JTextField[] campos = new JTextField[10];

            for (int i = 0; i < 10; i++) {
                panelCambiar.add(new JLabel("Palabra " + (i + 1) + ":"));
                campos[i] = new JTextField(palabras[i]);
                panelCambiar.add(campos[i]);
            }

            int opcion = JOptionPane.showConfirmDialog(null, panelCambiar, "Cambiar Palabras",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (opcion == JOptionPane.OK_OPTION) {
                for (int i = 0; i < 10; i++) {
                    String texto = campos[i].getText().trim().toUpperCase();
                    if (!texto.isEmpty() && texto.matches("[A-Z]+")) {
                        palabras[i] = texto;
                    } else {
                        JOptionPane.showMessageDialog(null, "Todas las letra deben ser válidas (solo una letras).");
                        return;
                    }
                }
            }
        });

        btnSalir.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Martinez_Raquel_Juego().setVisible(true));
    }
}
