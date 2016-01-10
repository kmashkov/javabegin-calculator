package ru.javabegin.calc;

import com.jtattoo.plaf.luna.LunaLookAndFeel;
import ru.javabegin.calc.jcomponents.CalcButton;
import ru.javabegin.calc.jcomponents.MainFrame;
import ru.javabegin.calc.jcomponents.CalcPanel;
import ru.javabegin.calc.jcomponents.CalcInput;
import ru.javabegin.calc.listeners.CalcButtonListener;
import ru.javabegin.calc.listeners.InputFocusListener;

import javax.swing.*;
import java.awt.*;

public class Calculator {
    public static final String NUMBER_ONE_TEXT = "Number 1";
    public static final String NUMBER_TWO_TEXT = "Number 2";
    public static final String INPUT_NUMBER = "Input number";
    public static final String RESULT_TEXT = "Result";
    public static final String ADDITION_TEXT = "+";
    public static final String SUBTRACTION_TEXT = "-";
    public static final String MULTIPLICATION_TEXT = "*";
    public static final String DIVISION_TEXT = "/";
    public static final String NUMBERS_PANE_TEXT = "Numbers";
    public static final String OPERATIONS_PANE_TEXT = "Operations";
    public static final String RESULT_PANE_TEXT = "Result";
    public static final String FRAME_TEXT = "Calculator";
    public static final int INPUT_COLUMNS = 20;
    public static final int RESULT_INPUT_COLUMNS = 20;
    public static final Dimension PANEL_PREFERRED_SIZE = new Dimension(230, 60);
    public static final Dimension RESULT_PANEL_PREFERRED_SIZE = new Dimension(230, 40);
    public static final Dimension FRAME_MINIMUM_SIZE = new Dimension(230, 150);
    public static final FlowLayout NUMBERS_LAYOUT = new FlowLayout(FlowLayout.RIGHT);
    public static final FlowLayout OPERATIONS_LAYOUT = new FlowLayout();
    public static final FlowLayout RESULT_LAYOUT = new FlowLayout(FlowLayout.RIGHT);
    public static final LunaLookAndFeel LOOK_AND_FEEL = new LunaLookAndFeel();

    JLabel lblNumberOne;
    JLabel lblNumberTwo;
    JLabel lblResult;
    
    JTextField txtNumberOne;
    JTextField txtNumberTwo;
    JTextField txtResult;
    
    JButton addition;
    JButton subtraction;
    JButton multiplication;
    JButton division;
    
    JPanel numbersPane;
    JPanel operationsPane;
    JPanel resultPane;
    
    public Calculator() {
        createLookAndFeel();
        createLabels();
        createTextFields();
        createButtons();
        createPanels();
        createFrame();
    }

    private void createLookAndFeel() {
        try {
            UIManager.setLookAndFeel(LOOK_AND_FEEL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
    }
    
    private void createLabels() {
        lblNumberOne = new JLabel(NUMBER_ONE_TEXT);
        lblNumberTwo = new JLabel(NUMBER_TWO_TEXT);
        lblResult = new JLabel(RESULT_TEXT);
    }
    
    private void createTextFields() {
        txtNumberOne = new CalcInput(INPUT_NUMBER, INPUT_COLUMNS);
        txtNumberTwo = new CalcInput(INPUT_NUMBER, INPUT_COLUMNS);
        txtResult = new CalcInput((RESULT_INPUT_COLUMNS), Color.RED);
        txtResult.setEditable(false);
        txtResult.setFocusable(false);
        addTextFieldListeners();
    }
    
    private void createButtons() {
        addition = new CalcButton(ADDITION_TEXT);
        subtraction = new CalcButton(SUBTRACTION_TEXT);
        multiplication = new CalcButton(MULTIPLICATION_TEXT);
        division = new CalcButton(DIVISION_TEXT);
        addButtonListeners();
    }
    
    private void createPanels() {
        numbersPane = new CalcPanel(NUMBERS_PANE_TEXT);
        numbersPane.setPreferredSize(PANEL_PREFERRED_SIZE);
        numbersPane.setLayout(NUMBERS_LAYOUT);
        numbersPane.add(lblNumberOne);
        numbersPane.add(txtNumberOne);
        numbersPane.add(lblNumberTwo);
        numbersPane.add(txtNumberTwo);
        
        operationsPane = new CalcPanel(OPERATIONS_PANE_TEXT);
        operationsPane.setPreferredSize(PANEL_PREFERRED_SIZE);
        operationsPane.setLayout(OPERATIONS_LAYOUT);
        operationsPane.add(addition);
        operationsPane.add(subtraction);
        operationsPane.add(multiplication);
        operationsPane.add(division);
        
        resultPane = new CalcPanel(RESULT_PANE_TEXT);
        resultPane.setPreferredSize(RESULT_PANEL_PREFERRED_SIZE);
        resultPane.setLayout(RESULT_LAYOUT);
        resultPane.add(lblResult);
        resultPane.add(txtResult);
    }
    
    private void createFrame() {
        JFrame frame = new MainFrame(FRAME_TEXT);
        frame.setMinimumSize(FRAME_MINIMUM_SIZE);
        frame.setResizable(false);
        frame.getContentPane().add(numbersPane, BorderLayout.NORTH);
        frame.getContentPane().add(operationsPane, BorderLayout.CENTER);
        frame.getContentPane().add(resultPane, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    
    private void addButtonListeners() {
        CalcButtonListener bl = new CalcButtonListener(txtNumberOne, txtNumberTwo, txtResult);
        addition.addActionListener(bl);
        subtraction.addActionListener(bl);
        multiplication.addActionListener(bl);
        division.addActionListener(bl);
    }
    
    private void addTextFieldListeners() {
        txtNumberOne.addFocusListener(new InputFocusListener(txtNumberOne));
        txtNumberTwo.addFocusListener(new InputFocusListener(txtNumberTwo));
    }

    // Main method
    public static void main(String[] args) {
        new Calculator();
    }
}
