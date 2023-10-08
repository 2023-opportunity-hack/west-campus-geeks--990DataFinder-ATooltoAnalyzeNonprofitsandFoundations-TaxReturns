import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.sql.*;

public class Frame extends JFrame{
    //PRIVATE DECLARATIONS
    private JLabel titleLabel;
    private JLabel stateLabel;
    private JLabel cityLabel;
    private JLabel typeQuestionLabel;
    private JLabel grossRevLabel;
    private JLabel netRevLabel;
    private JCheckBox nonprofitButton;
    private JCheckBox foundationButton;
    private JTextField cityInput;
    private JComboBox stateInput;
    private JComboBox grossRevInput;
    private JComboBox netRevInput;
    private JButton getResultButton;
    private JPanel foundationQPanel;
    private JPanel foundationPanel;
    private JPanel questionPanel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel titlePanel;
    private JPanel cityStatePanel;
    private JPanel cityPanel;
    private JPanel statePanel;
    private JPanel centerPanel;
    private JPanel grossPanel;
    private JPanel netPanel;
    private JPanel grossNetPanel;
    private JPanel panel;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panel7;
    private JPanel panel8;
    private JPanel panel9;
    private JPanel panel10;
    private JPanel panel11;
    private JPanel panel12;
    private JPanel searchPanel;
    private JPanel resultPanel;
    private String[] resultArray;
    private JTextArea resultArea;
    private JScrollPane resultScroller;
    private final int FRAME_HEIGHT = 700;
    private final int FRAME_WIDTH = 900;
    private final String[] states = {"N/A", "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID",
            "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
            "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV",
            "WI", "WY", "PR", "VI", "CNMI", "GU", "AS"};
    private final String[] grossIntervals = {"N/A", "<25k", "25-49k", "50-75k", "76-100k", "100k+"};
    private final String[] netIntervals = {"N/A", "<25k", "25-49k", "50-75k", "76-100k", "100k+"};

    /*private final String databaseURL = "jdbc:sqlite:/path/to/your/database.db";//URL for database to be connected to

        //Attempt to establish connection to database
        private void connectAndQueryDB(String query) {
             try { (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();

             //Queries
             ResultSet resultSetExample = statement.executeQuery("SELECT * FROM your_table")) {

             }
             ResultSet resultSet1 = statement.executeQuery("SELECT * FROM your_table")) {

             }
             ResultSet resultSet2 = statement.executeQuery("SELECT * FROM your_table")) {

             }
             ResultSet resultSetX = statement.executeQuery("SELECT * FROM your_table")) {

             }


            while (resultSetExample.next()) {
                try ( int EIN = resultSet.getInt("EIN");
                String name = resultSet.getString("name_column_name");
                double salary = resultSet.getDouble("salary_column_name");


            }
             } catch (SQLException e) {
            e.printStackTrace(); //When unable to connect to database print error report
        }
     }*/

    //CONSTRUCTOR
    public Frame() {
        this.setSize(FRAME_HEIGHT,FRAME_WIDTH);
        this.setTitle("Legends Animation Tax Search");
        this.setResizable(false);
        createComponents();
    }

    //ACTION LISTENERS
    public class nonprofitListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            //displays other questions if certain buttons selected
            if (nonprofitButton.isSelected()) {
                grossNetPanel.setVisible(true);
            }
            else if (!nonprofitButton.isSelected()) {
                grossNetPanel.setVisible(false);
            }

        }
    }
    public class searchListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            resultArea.setText("");
            /*
            checkNonprofit()  //returns boolean if nonprofit isSelected
            checkFoundation()  //returns boolean if nonprofit isSelected
            checkCity()  //returns null otherwise
            checkState()  //returns null otherwise
            checkGross()  //returns returns null otherwise
            checkNet()  //returns null otherwise
            for (String e : database_array){
                //iterate through the database array to find
            }
            */
        }
    }



    //METHODS
    private void createComponents(){
        createLabels();
        createbuttons();
        createPanels();
        addToPanels();
    }

    private void createLabels(){
        titleLabel = new JLabel("Legends Animation Tax Search"); //creates labels
        titleLabel.setFont(new Font("Label.font", Font.BOLD, 18));
        stateLabel = new JLabel("State");
        cityLabel = new JLabel("City");
        typeQuestionLabel = new JLabel("Organization Type");
        grossRevLabel = new JLabel("Gross Revenue");
        netRevLabel = new JLabel("Net Revenue");
    }

    private void createPanels(){
        foundationQPanel = new JPanel(new GridLayout(1,2));
        foundationPanel = new JPanel(new GridLayout(2,1));
        questionPanel = new JPanel();
        titlePanel = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        cityPanel = new JPanel(new GridLayout(1,2));
        statePanel = new JPanel(new GridLayout(1,2));
        panel3= new JPanel();
        panel4  = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        grossPanel = new JPanel(new GridLayout(1,2));
        netPanel = new JPanel(new GridLayout(1,2));
        panel7= new JPanel();
        panel8 = new JPanel();
        panel9 = new JPanel();
        panel10 = new JPanel();
        cityStatePanel = new JPanel(new GridLayout(1,2));
        grossNetPanel = new JPanel(new GridLayout(1,2));
        grossNetPanel.setVisible(false);
        centerPanel = new JPanel(new GridLayout(4  ,1));
        panel12 = new JPanel();
        searchPanel = new JPanel();
        panel11 = new JPanel();
        resultPanel = new JPanel();
        resultPanel.setBorder(new TitledBorder(new EtchedBorder(),"Results:"));
        panel = new JPanel(new BorderLayout());
    }

    private void createbuttons(){
        //Always visible components
        nonprofitButton = new JCheckBox("Nonprofit");
        ActionListener listener = new nonprofitListener(); //implements action listener to button
        nonprofitButton.addActionListener(listener);
        foundationButton = new JCheckBox("Foundation", true);
        cityInput = new JTextField(15);
        stateInput = new JComboBox(states);
        getResultButton = new JButton("Search");

        //Non-profit foundation buttons
        grossRevInput = new JComboBox(grossIntervals);
        netRevInput = new JComboBox(netIntervals);

        //Construct Result Area
        resultArea = new JTextArea(20, 60);
        resultArea.setEditable(false);
        resultScroller = new JScrollPane(resultArea);
    }
    private void addToPanels(){
        titlePanel.add(titleLabel); //adding to title panel

        panel1.add(nonprofitButton); //adding to foundation panel
        panel2.add(foundationButton);
        foundationQPanel.add(panel1);
        foundationQPanel.add(panel2);
        questionPanel.add(typeQuestionLabel);
        foundationPanel.add(questionPanel);
        foundationPanel.add(foundationQPanel);

        panel5.add(cityLabel); //adding to city-state panel
        cityPanel.add(panel5);
        panel3.add(cityInput);
        cityPanel.add(panel3);
        panel6.add(stateLabel);
        statePanel.add(panel6);
        panel4.add(stateInput);
        statePanel.add(panel4);
        cityStatePanel.add(cityPanel);
        cityStatePanel.add(statePanel);

        panel7.add(grossRevLabel); //adding to gross-net panel
        grossPanel.add(panel7);
        panel8.add(grossRevInput);
        grossPanel.add(panel8);
        panel9.add(netRevLabel);
        netPanel.add(panel9);
        panel10.add(netRevInput);
        netPanel.add(panel10);
        grossNetPanel.add(grossPanel);
        grossNetPanel.add(netPanel);

        panel12.add(getResultButton); //adding to search panel
        searchPanel.add(panel12);

        centerPanel.add(foundationPanel); //adding to center panel
        centerPanel.add(cityStatePanel);
        centerPanel.add(grossNetPanel);
        centerPanel.add(searchPanel);
        panel11.add(resultScroller);
        resultPanel.add(panel11);

        panel.add(titlePanel, BorderLayout.NORTH); //adding to whole panel
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(resultPanel, BorderLayout.SOUTH);

        this.add(panel); //adding to frame
    }


}