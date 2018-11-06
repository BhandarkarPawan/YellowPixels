import javax.swing.*;
import java.awt.*;

public class test {

    public static void main(String[] args) {

        String fonts[] =
                GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

               SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Launch GUI

                new MainForm();
            }
        });

    }
}
