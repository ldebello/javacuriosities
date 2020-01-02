package ar.com.javacuriosities.parameters;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JVMParameters extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JPanel contentPane;
    private final JTextField fmtInitialMemory;
    private final JTextField fmtNewRatio;
    private final JTextField fmtSurvivorRatio;
    private final JLabel lblSurvivorRatio;
    private final JLabel lblNewRatio;
    private final JLabel lblInitialMemory;
    private final JButton btnCalculate;
    private final JLabel lblOldGeneration;
    private final JLabel lblEdenSpace;
    private final JLabel lblSurvivorSpace;
    private final JLabel lblMegas1;
    private final JLabel lblMegas2;
    private final JLabel lblMegas3;
    private final JLabel lblMegas4;
    private final JTextField fmtOldGeneration;
    private final JTextField fmtEdenSpace;
    private final JTextField fmtSurvivorSpace1;
    private final JTextField fmtSurvivorSpace2;
    private final JLabel lblMegas5;
    private final JLabel lblOldGenerationCalculation;
    private final JLabel lblEdenSpaceCalculation;
    private final JLabel lblSurvivor1Calculation;
    private final JLabel lblSurvivor2Calculation;

    /**
     * Create the frame.
     */
    public JVMParameters() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 557, 316);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new FormLayout(
                new ColumnSpec[]{FormFactory.RELATED_GAP_COLSPEC,
                        FormFactory.DEFAULT_COLSPEC,
                        FormFactory.RELATED_GAP_COLSPEC,
                        ColumnSpec.decode("max(101dlu;default):grow"),
                        FormFactory.RELATED_GAP_COLSPEC,
                        FormFactory.DEFAULT_COLSPEC,
                        FormFactory.RELATED_GAP_COLSPEC,
                        ColumnSpec.decode("default:grow"),
                        FormFactory.RELATED_GAP_COLSPEC,
                        FormFactory.DEFAULT_COLSPEC,}, new RowSpec[]{
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,}));

        lblInitialMemory = new JLabel("Xmx / Xms");
        contentPane.add(lblInitialMemory, "2, 2, right, default");

        fmtInitialMemory = new JTextField();
        fmtInitialMemory.setText("6144");
        contentPane.add(fmtInitialMemory, "4, 2, fill, default");
        fmtInitialMemory.setColumns(10);

        lblMegas1 = new JLabel("MB");
        contentPane.add(lblMegas1, "6, 2");

        lblNewRatio = new JLabel("New Ratio");
        contentPane.add(lblNewRatio, "2, 4, right, default");

        fmtNewRatio = new JTextField();
        fmtNewRatio.setText("2");
        contentPane.add(fmtNewRatio, "4, 4, fill, default");
        fmtNewRatio.setColumns(10);

        lblSurvivorRatio = new JLabel("Survivor Ratio");
        contentPane.add(lblSurvivorRatio, "2, 6, right, default");

        fmtSurvivorRatio = new JTextField();
        fmtSurvivorRatio.setText("10");
        contentPane.add(fmtSurvivorRatio, "4, 6, fill, default");
        fmtSurvivorRatio.setColumns(10);

        btnCalculate = new JButton("Calcular");
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double initialMemory = Double.valueOf(fmtInitialMemory
                        .getText());
                double newRatio = Double.valueOf(fmtNewRatio.getText());
                double survivorRatio = Double.valueOf(fmtSurvivorRatio
                        .getText());

                double oldGeneration = Math.round(initialMemory
                        - (initialMemory / (newRatio + 1)));
                double edenSpace = Math.round((initialMemory / (newRatio + 1))
                        * survivorRatio / (survivorRatio + 2));
                double survivorSpace = Math
                        .round((initialMemory / (newRatio + 1))
                                / (survivorRatio + 2));

                fmtOldGeneration.setText(String.valueOf(oldGeneration));
                fmtEdenSpace.setText(String.valueOf(edenSpace));
                fmtSurvivorSpace1.setText(String.valueOf(survivorSpace));
                fmtSurvivorSpace2.setText(String.valueOf(survivorSpace));
            }
        });
        contentPane.add(btnCalculate, "4, 8, right, default");

        lblOldGeneration = new JLabel("Old Generation");
        contentPane.add(lblOldGeneration, "2, 10, right, default");

        fmtOldGeneration = new JTextField();
        fmtOldGeneration.setEditable(false);
        contentPane.add(fmtOldGeneration, "4, 10, fill, default");
        fmtOldGeneration.setColumns(10);

        lblMegas2 = new JLabel("MB");
        contentPane.add(lblMegas2, "6, 10");

        lblOldGenerationCalculation = new JLabel(
                "(TotalHeapMemory / NewRatio+1) * NewRatio");
        contentPane.add(lblOldGenerationCalculation, "8, 10");

        lblEdenSpace = new JLabel("Eden Space");
        contentPane.add(lblEdenSpace, "2, 12, right, default");

        fmtEdenSpace = new JTextField();
        fmtEdenSpace.setEditable(false);
        contentPane.add(fmtEdenSpace, "4, 12, fill, default");
        fmtEdenSpace.setColumns(10);

        lblMegas3 = new JLabel("MB");
        contentPane.add(lblMegas3, "6, 12");

        lblEdenSpaceCalculation = new JLabel(
                "((TotalHeapMemory - OldGeneration) / (SurvivorRatio + 2)) * SurvivorRatio");
        contentPane.add(lblEdenSpaceCalculation, "8, 12");

        lblSurvivorSpace = new JLabel("Survivor Spaces");
        contentPane.add(lblSurvivorSpace, "2, 14, right, default");

        fmtSurvivorSpace1 = new JTextField();
        fmtSurvivorSpace1.setEditable(false);
        contentPane.add(fmtSurvivorSpace1, "4, 14, fill, default");
        fmtSurvivorSpace1.setColumns(10);

        lblMegas4 = new JLabel("MB");
        contentPane.add(lblMegas4, "6, 14");

        lblSurvivor1Calculation = new JLabel(
                "((TotalHeapMemory - OldGeneration - EdenSpace) / 2)");
        contentPane.add(lblSurvivor1Calculation, "8, 14, left, bottom");

        fmtSurvivorSpace2 = new JTextField();
        fmtSurvivorSpace2.setEditable(false);
        contentPane.add(fmtSurvivorSpace2, "4, 16, fill, default");
        fmtSurvivorSpace2.setColumns(10);

        lblMegas5 = new JLabel("MB");
        contentPane.add(lblMegas5, "6, 16");

        lblSurvivor2Calculation = new JLabel(
                "((TotalHeapMemory - OldGeneration - EdenSpace) / 2)");
        contentPane.add(lblSurvivor2Calculation, "8, 16");

        pack();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JVMParameters frame = new JVMParameters();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
