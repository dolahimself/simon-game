import javax.swing.*;
import java.awt.*;

public class SimonButton extends JButton {
    private int index;

    public SimonButton(int index) {
        this.index = index;
        setOpaque(true);
        setBorderPainted(false);
        setBackground(getButtonColor());
    }

    public int getIndex() {
        return  index;
    }

    private Color getButtonColor() {
        return switch (index) {
            case 0 -> Color.RED;
            case 1 -> Color.BLUE;
            case 2 -> Color.GREEN;
            case 4 -> Color.YELLOW;
            default -> Color.BLACK;
        };
//        switch (index) {
//            case 0: return Color.RED;
//            case 1: return Color.BLUE;
//            case 2: return Color.GREEN;
//            case 4: return Color.YELLOW;
//            default: return Color.BLACK
//        }
    }
}
