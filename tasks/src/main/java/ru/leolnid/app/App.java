package ru.leolnid.app;

import org.json.simple.parser.ParseException;
import ru.leolnid.app.clock.*;
import ru.leolnid.app.shop.ClockShop;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Leonid Dyukov <leonid.dyukov@gmail.com>
 * @version 0.0.1
 */
public class App extends ClockShop {
    private final static Color backgroundColor = Color.WHITE;

    public static class ChangeClockDialog extends JDialog {

        ChangeClockDialog(ClockComponent clockComponent, Clock clock) {
            super();

            GridBagConstraints constraints = new GridBagConstraints();
            this.setLayout(new GridBagLayout());

            this.setSize(350, 150);
            this.setModal(true);
            this.setResizable(false);

            Label typeLabel = new Label("Type: ");
            constraints.gridy = 0;
            constraints.gridx = 0;
            constraints.gridwidth = 1;
            this.add(typeLabel, constraints);

            String[] clockTypes = Arrays.stream(ClockType.values()).map(Enum::toString).toArray(String[]::new);
            JComboBox<String> typeInput = new JComboBox<>(clockTypes);
            typeInput.setSelectedItem(clock.getType());
            constraints.gridy = 0;
            constraints.gridx = 1;
            constraints.gridwidth = 4;
            this.add(typeInput, constraints);

            Label timeLabel = new Label("Time: ");
            constraints.gridy = 1;
            constraints.gridx = 0;
            constraints.gridwidth = 1;
            this.add(timeLabel, constraints);


            TextField timeInput = new TextField(22);
            constraints.gridy = 1;
            constraints.gridx = 1;
            constraints.gridwidth = 4;
            timeInput.setText(Float.toString(clock.getTime()));
            this.add(timeInput, constraints);

            Label priceLabel = new Label("Price: ");
            constraints.gridy = 2;
            constraints.gridx = 0;
            constraints.gridwidth = 1;
            this.add(priceLabel, constraints);

            TextField priceInput = new TextField(5);
            constraints.gridy = 2;
            constraints.gridx = 1;
            constraints.gridwidth = 1;
            priceInput.setText(Float.toString(clock.getPrice()));
            this.add(priceInput, constraints);

            Label markLabel = new Label("Mark: ");
            constraints.gridy = 2;
            constraints.gridx = 2;
            constraints.gridwidth = 1;
            this.add(markLabel, constraints);

            TextField markInput = new TextField(5);
            constraints.gridy = 2;
            constraints.gridx = 3;
            constraints.gridwidth = 1;
            markInput.setText(clock.getMark());
            this.add(markInput, constraints);

            Button add = new Button();
            add.setLabel("APPLY");
            constraints.gridy = 2;
            constraints.gridx = 4;
            this.add(add, constraints);

            add.addActionListener(e -> {
                try {
                    if (typeInput.getSelectedItem() == "" || markInput.getText().equals("") || priceInput.getText().equals("")) {
                        JOptionPane.showMessageDialog(ChangeClockDialog.this, "Please, type mark, price and type");
                        return;
                    }

                    ClockType type = ClockType.valueOf(Objects.requireNonNull(typeInput.getSelectedItem()).toString());
                    float time = Float.parseFloat(timeInput.getText());

                    Clock newClock = ClockFabric.create(type, markInput.getText(), Float.parseFloat(priceInput.getText()));
                    newClock.setTime(time);
                    clockComponent.replace(newClock);
                    ChangeClockDialog.this.dispatchEvent(new WindowEvent(ChangeClockDialog.this, WindowEvent.WINDOW_CLOSING));
                } catch (IllegalArgumentException | NullPointerException | ClockException error) {
                    System.err.println(error.getMessage());
                    JOptionPane.showMessageDialog(ChangeClockDialog.this, error.getMessage());
                }
            });
        }
    }

    public static class AddNewClockDialog extends JDialog {
        AddNewClockDialog(ShopControllerComponent shopController) {
            super();

            GridBagConstraints constraints = new GridBagConstraints();
            this.setLayout(new GridBagLayout());

            this.setSize(350, 150);
            this.setModal(true);
            this.setResizable(false);

            Label typeLabel = new Label("Type: ");
            constraints.gridy = 0;
            constraints.gridx = 0;
            constraints.gridwidth = 1;
            this.add(typeLabel, constraints);

            String[] clockTypes = Arrays.stream(ClockType.values()).map(Enum::toString).toArray(String[]::new);
            JComboBox<String> typeInput = new JComboBox<>(clockTypes);
            constraints.gridy = 0;
            constraints.gridx = 1;
            constraints.gridwidth = 4;
            this.add(typeInput, constraints);

            Label priceLabel = new Label("Price: ");
            constraints.gridy = 1;
            constraints.gridx = 0;
            constraints.gridwidth = 1;
            this.add(priceLabel, constraints);

            TextField priceInput = new TextField(5);
            constraints.gridy = 1;
            constraints.gridx = 1;
            constraints.gridwidth = 1;
            this.add(priceInput, constraints);

            Label markLabel = new Label("Mark: ");
            constraints.gridy = 1;
            constraints.gridx = 2;
            constraints.gridwidth = 1;
            this.add(markLabel, constraints);

            TextField markInput = new TextField(5);
            constraints.gridy = 1;
            constraints.gridx = 3;
            constraints.gridwidth = 1;
            this.add(markInput, constraints);

            Button add = new Button();
            add.setLabel("ADD");
            constraints.gridy = 1;
            constraints.gridx = 4;
            this.add(add, constraints);

            add.addActionListener((ActionEvent e) -> {
                try {
                    if (typeInput.getSelectedItem() == "" || markInput.getText().equals("") || priceInput.getText().equals("")) {
                        JOptionPane.showMessageDialog(this, "Please, type mark, price and type");
                        return;
                    }

                    ClockType type = ClockType.valueOf(Objects.requireNonNull(typeInput.getSelectedItem()).toString());
                    Clock clock = ClockFabric.create(type, markInput.getText(), Float.parseFloat(priceInput.getText()));
                    shopController.addClock(clock);
                    this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                } catch (IllegalArgumentException | NullPointerException | ClockException error) {
                    System.err.println(error.getMessage());
                    JOptionPane.showMessageDialog(this, error.getMessage());
                }
            });
        }
    }

    public static class ShopControllerComponent extends JPanel {
        ClockShopComponent clockShopComponent;

        ShopControllerComponent(ClockShopComponent clockShopComponent) {
            this.clockShopComponent = clockShopComponent;
            this.setVisible(true);
            LayoutManager layout = new FlowLayout();
            this.setLayout(layout);


            Button save = new Button();
            save.setLabel("Save shop schema");
            save.setVisible(true);
            this.add(save);

            save.addActionListener((ActionEvent e) -> {
                JFileChooser loadDialog = new JFileChooser();
                loadDialog.setFileFilter(new FileNameExtensionFilter("JSON", "json"));
                loadDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = loadDialog.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = loadDialog.getSelectedFile();
                        String name = file.getName();
                        if (!name.contains(".") || !name.substring(name.lastIndexOf('.')).equals(".json"))
                            file = new File(file.getAbsolutePath() + ".json");

                        clockShopComponent.save(file);
                    } catch (IOException error) {
                        JOptionPane.showMessageDialog(this, "Data can't be saved: " + error.getMessage());
                        error.printStackTrace();
                    }
                }
            });


            Button load = new Button();
            load.setLabel("Load shop schema");
            load.setVisible(true);
            this.add(load);

            load.addActionListener((ActionEvent e) -> {
                JFileChooser loadDialog = new JFileChooser();
                loadDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
                loadDialog.setFileFilter(new FileNameExtensionFilter("JSON", "json"));
                int result = loadDialog.showSaveDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = loadDialog.getSelectedFile();
                        clockShopComponent.load(file);
                    } catch (IOException | ParseException | ClockException error) {
                        JOptionPane.showMessageDialog(this, "Data can't be loaded: " + error.getMessage());
                        error.printStackTrace();
                    }
                }
            });

            Button add = new Button();
            add.setLabel("Add clock");
            add.setVisible(true);
            this.add(add);

            add.addActionListener((ActionEvent e) -> {
                AddNewClockDialog dialog = new AddNewClockDialog(this);
                dialog.setVisible(true);
            });

            this.setVisible(true);
        }

        public void addClock(Clock clock) {
            clockShopComponent.addClock(clock);
        }
    }

    public static class ClockComponent extends JPanel {
        private final ClocksListComponent clocksListComponent;
        Label[] labels = new Label[5];
        Clock clock;

        ClockComponent(ClocksListComponent clocksListComponent, Clock clock) {
            this.clocksListComponent = clocksListComponent;
            this.clock = clock;
            GridBagConstraints constraints = new GridBagConstraints();
            this.setBackground(backgroundColor);
            this.setBorder(new BevelBorder(BevelBorder.RAISED));
            this.setLayout(new GridBagLayout());

            constraints.gridx = 0;
            constraints.gridy = 0;
            this.add(new Label("Mark: "), constraints);

            constraints.gridx = 1;
            constraints.gridy = 0;
            labels[0] = new Label(clock.getMark());
            this.add(labels[0], constraints);

            constraints.gridx = 2;
            constraints.gridy = 0;
            this.add(new Label("Price: "), constraints);

            constraints.gridx = 3;
            constraints.gridy = 0;
            labels[1] = new Label(Float.toString(clock.getPrice()));
            this.add(labels[1], constraints);

            constraints.gridx = 0;
            constraints.gridy = 1;
            this.add(new Label("Time: "), constraints);

            constraints.gridx = 1;
            constraints.gridy = 1;
            labels[2] = new Label(clock.getHours() + "h");
            this.add(labels[2], constraints);

            constraints.gridx = 2;
            constraints.gridy = 1;
            labels[3] = new Label(clock.getMinutes() + "m");
            this.add(labels[3], constraints);

            constraints.gridx = 3;
            constraints.gridy = 1;
            labels[4] = new Label(clock.getSeconds() + "s");
            this.add(labels[4], constraints);

            constraints.gridx = 4;
            constraints.gridy = 0;
            constraints.gridheight = 2;
            constraints.gridwidth = 2;

            Button change = new Button();
            change.setSize(100, 100);
            change.setLabel("CHANGE");

            change.addActionListener((ActionEvent e) -> {
                ChangeClockDialog dialog = new ChangeClockDialog(this, this.clock);
                dialog.setVisible(true);
            });
            this.add(change, constraints);

            constraints.gridx = 6;
            constraints.gridy = 0;
            constraints.gridheight = 2;
            constraints.gridwidth = 2;

            Button remove = new Button();
            remove.setSize(100, 100);
            remove.setLabel("REMOVE");

            remove.addActionListener((ActionEvent e) -> {
                this.clocksListComponent.removeC(this);
            });
            this.add(remove, constraints);

            this.setVisible(true);
        }

        public void refresh() {
            this.revalidate();
            this.repaint();
            clocksListComponent.refresh();
        }

        public void replace(Clock clock) {
            this.clock.replace(clock);

            labels[0].setText(clock.getMark());
            labels[1].setText(Float.toString(clock.getPrice()));
            labels[2].setText(clock.getHours() + "h");
            labels[3].setText(clock.getMinutes() + "m");
            labels[4].setText(clock.getSeconds() + "s");

            this.refresh();
        }
    }

    public static class ClocksListComponent extends JScrollPane {
        private ClockShopComponent clockShopComponent;
        private ClockShop clockShop;
        private JPanel panel;
        private List<ClockComponent> clockComponents;

        ClocksListComponent(JPanel panel) {
            super(panel);
        }

        public static ClocksListComponent init(ClockShopComponent clockShopComponent, ClockShop clockShop) {
            JPanel panel = new JPanel();
            panel.setBackground(Color.RED);

            LayoutManager layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
            panel.setLayout(layout);
            ClocksListComponent result = new ClocksListComponent(panel);

            result.clockShop = clockShop;
            result.panel = panel;
            result.clockShopComponent = clockShopComponent;
            result.clockComponents = new ArrayList<>();

            clockShop.getClocks().forEach(clock -> {
                ClockComponent clockComponent = new ClockComponent(result, clock);
                result.clockComponents.add(clockComponent);
                panel.add(clockComponent);
            });

            return result;
        }

        public void addClock(Clock clock) {
            clockShop.addClock(clock);
            ClockComponent clockComponent = new ClockComponent(this, clock);
            this.clockComponents.add(clockComponent);
            panel.add(clockComponent);
            this.refresh();
        }

        public void refresh() {
            this.revalidate();
            this.repaint();
            panel.revalidate();
            panel.repaint();
        }

        public void removeC(ClockComponent i) {
            this.remove(i);
            panel.remove(i);
            this.refresh();
        }

        public void clear() {
            this.clockComponents.forEach(c -> {
                this.remove(c);
                panel.remove(c);
            });
            this.refresh();
        }
    }

    public static class ClockShopComponent extends JFrame {
        ClockShop clockShop;
        ClocksListComponent clocksListComponent;

        ClockShopComponent(Dimension size, ClockShop clockShop) {
            this.setSize(size);
            this.clockShop = clockShop;
            Container pane = this.getContentPane();
            clocksListComponent = ClocksListComponent.init(this, clockShop);

            pane.add(new ShopControllerComponent(this), BorderLayout.NORTH);
            pane.add(clocksListComponent, BorderLayout.CENTER);

            this.setBackground(Color.BLACK);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);

            this.setResizable(false);
            this.setTitle("Clock shop app");
        }

        public void addClock(Clock clock) {
            clocksListComponent.addClock(clock);
        }

        public void save(File file) throws IOException {
            clockShop.save(file);
        }

        public void load(File file) throws IOException, ParseException, ClockException {
            clockShop.load(file);
            clocksListComponent.clear();

            clockShop.getClocks().forEach(c -> clocksListComponent.addClock(c));
        }
    }

    private static void createAndShowGUI(ClockShop clocksShop) {
        ClockShopComponent clockShopComponent = new ClockShopComponent(new Dimension(350, 500), clocksShop);
        clockShopComponent.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            ClockShop clocksShop = new ClockShop();
            clocksShop.addClock(ClockFabric.create(ClockType.CLOCK_WITH_SECONDS, "Mark1", 100));
            clocksShop.addClock(ClockFabric.create(ClockType.CLOCK_WITH_SECONDS, "Mark1", 100));
            clocksShop.addClock(ClockFabric.create(ClockType.CLOCK_WITH_SECONDS, "Mark1", 100));
            clocksShop.addClock(ClockFabric.create(ClockType.CLOCK_WITH_SECONDS, "Mark1", 100));
            clocksShop.addClock(ClockFabric.create(ClockType.CLOCK_WITH_SECONDS, "Mark1", 100));

            javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI(clocksShop));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
