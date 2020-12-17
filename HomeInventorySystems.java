

package homeinventorysystems; 
import javax.swing.*;
import javax.swing.filechooser.*; 
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import com.toedter.calendar.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.awt.print.*;
public class HomeInventorySystems extends JFrame
{
    // Creating Toolbar
    JToolBar ToolBar = new JToolBar();
    JButton btn1 = new JButton();
    JButton btn2 = new JButton();
    JButton btn3 = new JButton();
    JButton btn4 = new JButton();
    JButton btn5 = new JButton();
    JButton btn6 = new JButton();
    JButton btn7 = new JButton();

    // Frame
    JLabel l1 = new JLabel();
    JTextField t1 = new JTextField();
    JLabel l2 = new JLabel();
    JComboBox locationComboBox = new JComboBox();
    JCheckBox cb1 = new JCheckBox();
    JLabel l3 = new JLabel();
    JTextField t2 = new JTextField();
    JLabel l4 = new JLabel();
    JTextField t3 = new JTextField();
    JLabel dateLabel = new JLabel();
    JDateChooser dateDateChooser = new JDateChooser();
    JLabel l5 = new JLabel();
    JTextField t4 = new JTextField();
    JLabel l6 = new JLabel();
    JTextField t5 = new JTextField();
    JLabel l7 = new JLabel();
    
    static JTextArea photoTextArea = new JTextArea();
    JButton photoButton = new JButton();
    JPanel searchPanel = new JPanel();
    JButton[] searchButton = new JButton[26];
    PhotoPanel photoPanel = new PhotoPanel();
    static final int maximumEntries = 300;
    static int numberEntries;
    static InventoryItem[] myInventory = new InventoryItem[maximumEntries];
    int currentEntry;
    static final int entriesPerPage = 2;
    static int lastPage;
    
    public static void main(String args[])
    {
        // create frame
        new HomeInventorySystems().show();
    }
    
    
    public HomeInventorySystems()
    {
        // frame constructor
        setTitle("Home Inventory Manager");
        setResizable(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent evt)
            {
                exitForm(evt);
            }
        });
        
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints;
        ToolBar.setFloatable(false);
        ToolBar.setBackground(Color.BLUE);
        ToolBar.setOrientation(SwingConstants.VERTICAL);
        
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.gridheight = 8;
        gridConstraints.fill = GridBagConstraints.VERTICAL;
        getContentPane().add(ToolBar, gridConstraints);
        
        ToolBar.addSeparator();
        Dimension bSize = new Dimension(70, 50);
        
        btn1.setText("New");
        sizeButton(btn1, bSize);
        btn1.setToolTipText("Add New Item");
        btn1.setHorizontalTextPosition(SwingConstants.CENTER);
        btn1.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn1.setFocusable(false);
        ToolBar.add(btn1);

        btn1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btn1ActionPerformed(e);
            }
        });
        
        btn2.setText("Delete");
        sizeButton(btn2, bSize);
        btn2.setToolTipText("Delete Current Item");
        btn2.setHorizontalTextPosition(SwingConstants.CENTER);
        btn2.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn2.setFocusable(false);
        ToolBar.add(btn2);
        btn2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btn2ActionPerformed(e);
            }
        });
        
        btn3.setText("Save");
        sizeButton(btn3, bSize);
        btn3.setToolTipText("Save Current Item");
        btn3.setHorizontalTextPosition(SwingConstants.CENTER);
        btn3.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn3.setFocusable(false);
        ToolBar.add(btn3);
        btn3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btn3ActionPerformed(e);
            }
        });
        
        ToolBar.addSeparator();
        
        btn4.setText("Previous");
        sizeButton(btn4, bSize);
        btn4.setToolTipText("Display Previous Item");
        btn4.setHorizontalTextPosition(SwingConstants.CENTER);
        btn4.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn4.setFocusable(false);
        ToolBar.add(btn4);
        btn4.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btn4ActionPerformed(e);
            }
        });
        
        btn5.setText("Next");
        sizeButton(btn5, bSize);
        btn5.setToolTipText("Display Next Item");
        btn5.setHorizontalTextPosition(SwingConstants.CENTER);
        btn5.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn5.setFocusable(false);
        ToolBar.add(btn5);
        btn5.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btn5ActionPerformed(e);
            }
        });
        
        ToolBar.addSeparator();

        btn6.setText("Print");
        sizeButton(btn6, bSize);
        btn6.setToolTipText("Print Inventory List");
        btn6.setHorizontalTextPosition(SwingConstants.CENTER);
        btn6.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn6.setFocusable(false);
        ToolBar.add(btn6);
        btn6.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btn6ActionPerformed(e);
            }
        });
        
        btn7.setText("Exit");
        sizeButton(btn7, bSize);
        btn7.setToolTipText("Exit Program");
        btn7.setFocusable(false);
        ToolBar.add(btn7);
        btn7.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btn7ActionPerformed(e);
            }
        });
        
        l1.setText("Inventory Item");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;

        gridConstraints.insets = new Insets(10, 10, 0, 10);
        gridConstraints.anchor = GridBagConstraints.EAST;
        getContentPane().add(l1, gridConstraints);
        
        t1.setPreferredSize(new Dimension(400, 25));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 0;
        gridConstraints.gridwidth = 5;
        gridConstraints.insets = new Insets(10, 0, 0, 10);
        gridConstraints.anchor = GridBagConstraints.WEST;
        getContentPane().add(t1, gridConstraints);
        t1.addActionListener(new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                t1ActionPerformed(e);
            }
        });
        
        l2.setText("Location");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        gridConstraints.anchor = GridBagConstraints.EAST;
        getContentPane().add(l2, gridConstraints);
        
        locationComboBox.setPreferredSize(new Dimension(270, 25));
        locationComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
        locationComboBox.setEditable(true);
        locationComboBox.setBackground(Color.WHITE);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth = 3;
        gridConstraints.insets = new Insets(10, 0, 0, 10);
        gridConstraints.anchor = GridBagConstraints.WEST;
        getContentPane().add(locationComboBox, gridConstraints);        
        locationComboBox.addActionListener(new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                locationComboBoxActionPerformed(e);
            }
        });
        
        cb1.setText("Marked?");
        cb1.setFocusable(false);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 5;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(10, 10, 0, 0);
        gridConstraints.anchor = GridBagConstraints.WEST;
        getContentPane().add(cb1, gridConstraints);
        
        l3.setText("Serial Number");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 2;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        gridConstraints.anchor = GridBagConstraints.EAST;
        getContentPane().add(l3, gridConstraints);
        
        t2.setPreferredSize(new Dimension(270, 25));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 2;
        gridConstraints.gridwidth = 3;
        gridConstraints.insets = new Insets(10, 0, 0, 10);
        gridConstraints.anchor = GridBagConstraints.WEST;
        getContentPane().add(t2, gridConstraints);
        t2.addActionListener(new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                t2ActionPerformed(e);
            }
        });
        
        l4.setText("Purchase Price");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 3;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        gridConstraints.anchor = GridBagConstraints.EAST;
        getContentPane().add(l4, gridConstraints);
        
        t3.setPreferredSize(new Dimension(160, 25));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 3;
        gridConstraints.gridwidth = 2;
        gridConstraints.insets = new Insets(10, 0, 0, 10);
        gridConstraints.anchor = GridBagConstraints.WEST;
        getContentPane().add(t3, gridConstraints);
        t3.addActionListener(new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                t3ActionPerformed(e);
            }
        });
        
        dateLabel.setText("Date Purchased");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 4;
        gridConstraints.gridy = 3;
        gridConstraints.insets = new Insets(10, 10, 0, 0);
        gridConstraints.anchor = GridBagConstraints.WEST;
        getContentPane().add(dateLabel, gridConstraints);

        dateDateChooser.setPreferredSize(new Dimension(120, 25));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 5;
        gridConstraints.gridy = 3;
        gridConstraints.gridwidth = 2;
        gridConstraints.insets = new Insets(10, 0, 0, 10);
        gridConstraints.anchor = GridBagConstraints.WEST;
        getContentPane().add(dateDateChooser, gridConstraints);
        
        dateDateChooser.addPropertyChangeListener(new PropertyChangeListener()
        {
            public void propertyChange(PropertyChangeEvent e)
            {
                dateDateChooserPropertyChange(e);
            }
        });
        
        l5.setText("Store/Website");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 4;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        gridConstraints.anchor = GridBagConstraints.EAST;
        getContentPane().add(l5, gridConstraints);
        
        t4.setPreferredSize(new Dimension(400, 25));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 4;
        gridConstraints.gridwidth = 5;
        gridConstraints.insets = new Insets(10, 0, 0, 10);
        gridConstraints.anchor = GridBagConstraints.WEST;
        getContentPane().add(t4, gridConstraints);
        t4.addActionListener(new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                t4ActionPerformed(e);
            }
        });
        
        l6.setText("Note");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 5;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        gridConstraints.anchor = GridBagConstraints.EAST;
        getContentPane().add(l6, gridConstraints);
        
        t5.setPreferredSize(new Dimension(400, 25));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 5;
        gridConstraints.gridwidth = 5;
        gridConstraints.insets = new Insets(10, 0, 0, 10);
        gridConstraints.anchor = GridBagConstraints.WEST;
        getContentPane().add(t5, gridConstraints);
        t5.addActionListener(new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                t5ActionPerformed(e);
            }
        });
        
        l7.setText("Photo");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 6;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        gridConstraints.anchor = GridBagConstraints.EAST;
        getContentPane().add(l7, gridConstraints);
        
        photoTextArea.setPreferredSize(new Dimension(350, 35));
        photoTextArea.setFont(new Font("Arial", Font.PLAIN, 12));
        photoTextArea.setEditable(false);
        photoTextArea.setLineWrap(true);
        photoTextArea.setWrapStyleWord(true);
        photoTextArea.setBackground(new Color(255, 255, 192));
        photoTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        photoTextArea.setFocusable(false);
        
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 6;
        gridConstraints.gridwidth = 4;
        gridConstraints.insets = new Insets(10, 0, 0, 10);
        gridConstraints.anchor = GridBagConstraints.WEST;
        getContentPane().add(photoTextArea, gridConstraints);
        
        photoButton.setText("...");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 6;
        gridConstraints.gridy = 6;
        gridConstraints.insets = new Insets(10, 0, 0, 10);
        gridConstraints.anchor = GridBagConstraints.WEST;
        getContentPane().add(photoButton, gridConstraints);
            
        photoButton.addActionListener(new ActionListener ()
        {
        public void actionPerformed(ActionEvent e)
        {
        photoButtonActionPerformed(e);
        }
        });
        
        searchPanel.setPreferredSize(new Dimension(240, 160));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Item Search"));
        searchPanel.setLayout(new GridBagLayout());
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;

        gridConstraints.gridy = 7;
        gridConstraints.gridwidth = 3;
        gridConstraints.insets = new Insets(10, 0, 10, 0);
        gridConstraints.anchor = GridBagConstraints.CENTER;
        getContentPane().add(searchPanel, gridConstraints);
        
        int x = 0, y = 0;
        // create and position 26 buttons
        for (int i = 0; i < 26; i++)
        {
            // create new button
            searchButton[i] = new JButton();
            // set text property
            searchButton[i].setText(String.valueOf((char) (65 + i)));
            searchButton[i].setFont(new Font("Arial", Font.BOLD, 12));
            searchButton[i].setMargin(new Insets(-10, -10, -10, -10));
            sizeButton(searchButton[i], new Dimension(37, 27));
            searchButton[i].setBackground(Color.YELLOW);
            searchButton[i].setFocusable(false);
            gridConstraints = new GridBagConstraints();
            gridConstraints.gridx = x;
            gridConstraints.gridy = y;
            searchPanel.add(searchButton[i], gridConstraints);
            // add method
            searchButton[i].addActionListener(new ActionListener (){
                public void actionPerformed(ActionEvent e)
                {
                    searchButtonActionPerformed(e);
                }
                });
            x++;
            // six buttons per row
            if (x % 6 == 0)
            {
                x = 0;  
                y++;
            } 
        }
        
        photoPanel.setPreferredSize(new Dimension(240, 160));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 4;
        gridConstraints.gridy = 7;
        gridConstraints.gridwidth = 3;
        gridConstraints.insets = new Insets(10, 0, 10, 10);
        gridConstraints.anchor = GridBagConstraints.CENTER;
        getContentPane().add(photoPanel, gridConstraints);
        pack();
        
        Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());
        int n;
        // open file for entries
        try
        {
            BufferedReader inputFile = new BufferedReader(new FileReader("inventory.txt"));
            numberEntries = Integer.valueOf(inputFile.readLine()).intValue();
            if (numberEntries != 0)
            {
                for (int i = 0; i < numberEntries; i++)
                {
                    myInventory[i] = new InventoryItem();
                    myInventory[i].description = inputFile.readLine();
                    myInventory[i].location = inputFile.readLine();
                    myInventory[i].serialNumber = inputFile.readLine();
                    myInventory[i].marked =
                    Boolean.valueOf(inputFile.readLine()).booleanValue();
                    myInventory[i].purchasePrice =

                    inputFile.readLine();
                    myInventory[i].purchaseDate = inputFile.readLine();
                    myInventory[i].purchaseLocation =
                    inputFile.readLine();
                    myInventory[i].note = inputFile.readLine();
                    myInventory[i].photoFile = inputFile.readLine();
                }
            }
        // read in combo box elements
    n = Integer.valueOf(inputFile.readLine()).intValue();
    if (n != 0)
    {
        for (int i = 0; i < n; i++)
        {
            locationComboBox.addItem(inputFile.readLine());
        }
    }
    
    inputFile.close();
    currentEntry = 1;
    showEntry(currentEntry);
        }
    catch (Exception ex)
    {
    numberEntries = 0;
    currentEntry = 0;
    }
    if (numberEntries == 0)
    {
    btn1.setEnabled(false);
    btn2.setEnabled(false);
    btn5.setEnabled(false);
    btn4.setEnabled(false);
    btn6.setEnabled(false);
    } }

private void exitForm(WindowEvent evt)
{

    if (JOptionPane.showConfirmDialog(null, "Any unsaved changes will be lost.\nAre you sure you want to exit?", "Exit Program", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION)
        return;
    // write entries back to file
    try
    {
        PrintWriter outputFile = new PrintWriter(new BufferedWriter(new
        FileWriter("inventory.txt")));
        outputFile.println(numberEntries);
        if (numberEntries != 0)
        {
            for (int i = 0; i < numberEntries; i++)
            {
                outputFile.println(myInventory[i].description);
                outputFile.println(myInventory[i].location);
                outputFile.println(myInventory[i].serialNumber);
                outputFile.println(myInventory[i].marked);
                outputFile.println(myInventory[i].purchasePrice);
                outputFile.println(myInventory[i].purchaseDate);
                outputFile.println(myInventory[i].purchaseLocation);
                outputFile.println(myInventory[i].note);
                outputFile.println(myInventory[i].photoFile);} }
    // write combo box entries
    outputFile.println(locationComboBox.getItemCount());
    if (locationComboBox.getItemCount() != 0)
    {
        for (int i = 0; i < locationComboBox.getItemCount(); i++)
        outputFile.println(locationComboBox.getItemAt(i));
    }
    outputFile.close();
    }

    catch (Exception ex)
    {}
    System.exit(0);
    }
    private void btn1ActionPerformed(ActionEvent e)
    {
        checkSave();
        blankValues();
    }
        private void btn2ActionPerformed(ActionEvent e)
        {
        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?",
        "Delete Inventory Item", JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION)
        return;
        deleteEntry(currentEntry);
        if (numberEntries == 0)
        {
        currentEntry = 0;
        blankValues();
        }
        else
        {
        currentEntry--;
        if (currentEntry == 0)
        currentEntry = 1;
        showEntry(currentEntry);
        } }
    private void btn3ActionPerformed(ActionEvent e)
    {
    // check for description

    t1.setText(t1.getText().trim());
    if (t1.getText().equals(""))
    {
    JOptionPane.showConfirmDialog(null, "Must have item description.", "Error",
    JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
    t1.requestFocus();
    return;
    }
    if (btn1.isEnabled())
    {
    // delete edit entry then resave
    deleteEntry(currentEntry);
    }
    // capitalize first letter
    String s = t1.getText();
    t1.setText(s.substring(0, 1).toUpperCase() + s.substring(1));
    numberEntries++;
    // determine new current entry location based on description
    currentEntry = 1;
    if (numberEntries != 1)
    {
    do
    {
    if
    (t1.getText().compareTo(myInventory[currentEntry - 1].description) < 0)
    break;
    currentEntry++;
    }
    while (currentEntry < numberEntries);
    }
    // move all entries below new value down one position unless at end
    if (currentEntry != numberEntries)
    {
    for (int i = numberEntries; i >= currentEntry + 1; i--)
    {
    myInventory[i - 1] = myInventory[i - 2];

    myInventory[i - 2] = new InventoryItem();
    } }
    myInventory[currentEntry - 1] = new InventoryItem();
    myInventory[currentEntry - 1].description = t1.getText();
    myInventory[currentEntry - 1].location =
    locationComboBox.getSelectedItem().toString();
    myInventory[currentEntry - 1].marked = cb1.isSelected();
    myInventory[currentEntry - 1].serialNumber = t2.getText();
    myInventory[currentEntry - 1].purchasePrice = t3.getText();
    myInventory[currentEntry - 1].purchaseDate =
    dateToString(dateDateChooser.getDate());
    myInventory[currentEntry - 1].purchaseLocation = t4.getText();
    myInventory[currentEntry - 1].photoFile = photoTextArea.getText();
    myInventory[currentEntry - 1].note = t5.getText();
    showEntry(currentEntry);
    if (numberEntries < maximumEntries)
    btn1.setEnabled(true);
    else
    btn1.setEnabled(false);
    btn2.setEnabled(true);
    btn6.setEnabled(true);
    }
    private void btn4ActionPerformed(ActionEvent e)
    {
    checkSave();
    currentEntry--;
    showEntry(currentEntry);
    }
    private void btn5ActionPerformed(ActionEvent e)
    {
    checkSave();
    currentEntry++;
    showEntry(currentEntry);

    }
    private void btn6ActionPerformed(ActionEvent e)
    {
    lastPage = (int) (1 + (numberEntries - 1) / entriesPerPage);
    PrinterJob inventoryPrinterJob = PrinterJob.getPrinterJob();
    inventoryPrinterJob.setPrintable(new InventoryDocument());
    if (inventoryPrinterJob.printDialog())
    {
    try
    {
    inventoryPrinterJob.print();
    }
    catch (PrinterException ex)
    {
    JOptionPane.showConfirmDialog(null, ex.getMessage(), "Print Error",
    JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
    } } }
    private void btn7ActionPerformed(ActionEvent e)
    {
    exitForm(null);
    }
    private void photoButtonActionPerformed(ActionEvent e)
    {
        JFileChooser openChooser = new JFileChooser();
        openChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        openChooser.setDialogTitle("Open Photo File");
        openChooser.addChoosableFileFilter(new FileNameExtensionFilter("Photo Files",
        "jpg"));
        if (openChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            showPhoto(openChooser.getSelectedFile().toString());
    }

    private void searchButtonActionPerformed(ActionEvent e)
    {
        int i;
        if (numberEntries == 0)
            return;
    // search for item letter
        String letterClicked = e.getActionCommand();
        i = 0;
        do
        {
            if (myInventory[i].description.substring(0, 1).equals(letterClicked))
            {
                currentEntry = i + 1;
                showEntry(currentEntry);
                return;
            }
            i++;
        }
        while (i < numberEntries);
        JOptionPane.showConfirmDialog(null, "No " + letterClicked + " inventory items.",
        "None Found", JOptionPane.DEFAULT_OPTION,
        JOptionPane.INFORMATION_MESSAGE);
    }
    private void t1ActionPerformed(ActionEvent e)
    {
        locationComboBox.requestFocus();
    }
    private void locationComboBoxActionPerformed(ActionEvent e)
    {
    // If in list - exit method
        if (locationComboBox.getItemCount() != 0)
        {
        for (int i = 0; i < locationComboBox.getItemCount(); i++)
        {
            if (locationComboBox.getSelectedItem().toString().equals(locationComboBox.getItemAt(i).toString()))
        {
        t2.requestFocus();
        return;
    } 
        }
        }
    // If not found, add to list box
    locationComboBox.addItem(locationComboBox.getSelectedItem());
    t2.requestFocus();
    }
    private void t2ActionPerformed(ActionEvent e)
    {
    t3.requestFocus();
    }
    private void t3ActionPerformed(ActionEvent e)
    {
    dateDateChooser.requestFocus();
    }
    private void dateDateChooserPropertyChange(PropertyChangeEvent e)
    {
    t4.requestFocus();
    }
    private void t4ActionPerformed(ActionEvent e)
    {
    t5.requestFocus();
    }
    private void t5ActionPerformed(ActionEvent e)
    {
    photoButton.requestFocus();
    }

private void sizeButton(JButton b, Dimension d)
{
    b.setPreferredSize(d);
    b.setMinimumSize(d);
    b.setMaximumSize(d);
}
private void showEntry(int j)
{
    // display entry j (1 to numberEntries)
    t1.setText(myInventory[j - 1].description);
    locationComboBox.setSelectedItem(myInventory[j - 1].location);
    cb1.setSelected(myInventory[j - 1].marked);
    t2.setText(myInventory[j - 1].serialNumber);
    t3.setText(myInventory[j - 1].purchasePrice);
    dateDateChooser.setDate(stringToDate(myInventory[j - 1].purchaseDate));
    t4.setText(myInventory[j - 1].purchaseLocation);
    t5.setText(myInventory[j - 1].note);
    showPhoto(myInventory[j - 1].photoFile);
    btn5.setEnabled(true);
    btn4.setEnabled(true);
    if (j == 1)
    btn4.setEnabled(false);
    if (j == numberEntries)
    btn5.setEnabled(false);
    t1.requestFocus();
}
private Date stringToDate(String s)
{
    int m = Integer.valueOf(s.substring(0, 2)).intValue() - 1;
    int d = Integer.valueOf(s.substring(3, 5)).intValue();
    int y = Integer.valueOf(s.substring(6)).intValue() - 1900;
    return(new Date(y, m, d));
}

private String dateToString(Date dd)
{
    String yString = String.valueOf(dd.getYear() + 1900);
    int m = dd.getMonth() + 1;
    String mString = new DecimalFormat("00").format(m);
    int d = dd.getDate();
    String dString = new DecimalFormat("00").format(d);
    return(mString + "/" + dString + "/" + yString);
}
private void showPhoto(String photoFile)
{
    if (!photoFile.equals(""))
    {
        try
        {
        photoTextArea.setText(photoFile);
        }
        catch (Exception ex)
        {
        photoTextArea.setText("");
        } 
    }
    else
    {
        photoTextArea.setText("");
    }
    photoPanel.repaint();
}
private void blankValues()
{
    // blank input screen
    btn1.setEnabled(false);
    btn2.setEnabled(false);
    btn3.setEnabled(true);
    btn4.setEnabled(false);

    btn5.setEnabled(false);
    btn6.setEnabled(false);
    t1.setText("");
    locationComboBox.setSelectedItem("");
    cb1.setSelected(false);
    t2.setText("");
    t3.setText("");
    dateDateChooser.setDate(new Date());
    t4.setText("");
    t5.setText("");
    photoTextArea.setText("");
    photoPanel.repaint();
    t1.requestFocus();
}
private void deleteEntry(int j)
{
    // delete entry j
    if (j != numberEntries)
    {
    // move all entries under j up one level
    for (int i = j; i < numberEntries; i++)
    {
    myInventory[i - 1] = new InventoryItem();
    myInventory[i - 1] = myInventory[i];
    } }
    numberEntries--;
}
private void checkSave()
{
    boolean edited = false;
    if (!myInventory[currentEntry - 1].description.equals(t1.getText()))
    edited = true;
    else if (!myInventory[currentEntry - 1].location.equals(locationComboBox.getSelectedItem().toString()))
    edited = true;
    else if (myInventory[currentEntry - 1].marked != cb1.isSelected())
    edited = true;
    else if (!myInventory[currentEntry - 1].serialNumber.equals(t2.getText()))
    edited = true;
    else if (!myInventory[currentEntry - 1].purchasePrice.equals(t3.getText()))
    edited = true;
    else if (!myInventory[currentEntry - 1].purchaseDate.equals(dateToString(dateDateChooser.getDate())))
    edited = true;
    else if (!myInventory[currentEntry - 1].purchaseLocation.equals(t4.getText()))
    edited = true;
    else if (!myInventory[currentEntry - 1].note.equals(t5.getText()))
    edited = true;
    else if (!myInventory[currentEntry - 1].photoFile.equals(photoTextArea.getText()))
    edited = true;
    if (edited)
    {
    if (JOptionPane.showConfirmDialog(null, "You have edited this item. Do you want to save the changes?", "Save Item", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
        btn3.doClick();
    } } }
class PhotoPanel extends JPanel
{
    public void paintComponent(Graphics g)
    {
    Graphics2D g2D = (Graphics2D) g;
    super.paintComponent(g2D);
    // draw border
    g2D.setPaint(Color.BLACK);

    g2D.draw(new Rectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1));
    // show photo
    Image photoImage = new
    ImageIcon(HomeInventorySystems.photoTextArea.getText()).getImage();
    int w = getWidth();
    int h = getHeight();
    double rWidth = (double) getWidth() / (double) photoImage.getWidth(null);
    double rHeight = (double) getHeight() / (double) photoImage.getHeight(null);
    if (rWidth > rHeight)
    {
        // leave height at display height, change width by amount height is changed
        w = (int) (photoImage.getWidth(null) * rHeight);
    }
    else
    {
        // leave width at display width, change height by amount width is changed
        h = (int) (photoImage.getHeight(null) * rWidth);
    }
    // center in panel
    g2D.drawImage(photoImage, (int) (0.5 * (getWidth() - w)), (int) (0.5 * (getHeight() -
    h)), w, h, null);
    g2D.dispose();
} }


class InventoryDocument implements Printable
{
    public int print(Graphics g, PageFormat pf, int pageIndex)
    {
    Graphics2D g2D = (Graphics2D) g;
    if ((pageIndex + 1) > HomeInventorySystems.lastPage)
    {
        return NO_SUCH_PAGE;
    }
    int i, iEnd;
    // here you decide what goes on each page and draw it

    // header
    g2D.setFont(new Font("Arial", Font.BOLD, 14));
    g2D.drawString("Home Inventory Items - Page " + String.valueOf(pageIndex + 1),(int) pf.getImageableX(), (int) (pf.getImageableY() + 25));
    // get starting y
    int dy = (int) g2D.getFont().getStringBounds("S",
    g2D.getFontRenderContext()).getHeight();
    int y = (int) (pf.getImageableY() + 4 * dy);
    iEnd = HomeInventorySystems.entriesPerPage * (pageIndex + 1);
    if (iEnd > HomeInventorySystems.numberEntries)
    iEnd = HomeInventorySystems.numberEntries;
for (i = 0 + HomeInventorySystems.entriesPerPage * pageIndex; i < iEnd; i++)
{
    // dividing line
    Line2D.Double dividingLine = new Line2D.Double(pf.getImageableX(), y, pf.getImageableX() + pf.getImageableWidth(), y);
    g2D.draw(dividingLine);
    y += dy;
    g2D.setFont(new Font("Arial", Font.BOLD, 12));
    g2D.drawString(HomeInventorySystems.myInventory[i].description, (int) pf.getImageableX(), y);
    y += dy;
    g2D.setFont(new Font("Arial", Font.PLAIN, 12));
    g2D.drawString("Location: " + HomeInventorySystems.myInventory[i].location, (int)(pf.getImageableX() + 25), y);
    y += dy;
    if (HomeInventorySystems.myInventory[i].marked)
        g2D.drawString("Item is marked with identifying information.", (int)(pf.getImageableX() + 25), y);
    else
    g2D.drawString("Item is NOT marked with identifying information.", (int)(pf.getImageableX() + 25), y);
    y += dy;
    g2D.drawString("Serial Number: " + HomeInventorySystems.myInventory[i].serialNumber, (int) (pf.getImageableX() + 25), y);
    y += dy;
    g2D.drawString("Price: $" + HomeInventorySystems.myInventory[i].purchasePrice + ",Purchased on: " + HomeInventorySystems.myInventory[i].purchaseDate, (int) (pf.getImageableX() +
    25), y);
    y += dy;
    g2D.drawString("Purchased at: " + HomeInventorySystems.myInventory[i].purchaseLocation, (int) (pf.getImageableX() + 25), y);
    y += dy;
    g2D.drawString("Note: " + HomeInventorySystems.myInventory[i].note, (int)(pf.getImageableX() + 25), y);
    y += dy;
    try
    {
        // maintain original width/height ratio
        Image inventoryImage = new ImageIcon(HomeInventorySystems.myInventory[i].photoFile).getImage();
        double ratio = (double) (inventoryImage.getWidth(null)) / (double)
        inventoryImage.getHeight(null);
        g2D.drawImage(inventoryImage, (int) (pf.getImageableX() + 25), y, (int) (100 *ratio), 100, null);
    }
    catch (Exception ex)
    {
    // have place to go in case image file doesn't open
    }
    y += 2 * dy + 100;
    }
    return PAGE_EXISTS;
    } 
}


