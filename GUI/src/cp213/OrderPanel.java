package cp213;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.print.*;
import javax.swing.*;

/**
 * The GUI for the Order class.
 *
 * @author Iman Chaudhry
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2023-07-05
 */
@SuppressWarnings("serial")
public class OrderPanel extends JPanel {

    // Attributes
    private Menu menu = null; // Menu attached to panel.
    private final Order order = new Order(); // Order to be printed by panel.
    // GUI Widgets
    private final JButton printButton = new JButton("Print");
    private final JLabel subtotalLabel = new JLabel("0");
    private final JLabel taxLabel = new JLabel("0");
    private final JLabel totalLabel = new JLabel("0");

    private JLabel nameLabels[] = null;
    private JLabel priceLabels[] = null;
    // TextFields for menu item quantities.
    private JTextField quantityFields[] = null;

    /**
     * Displays the menu in a GUI.
     *
     * @param menu The menu to display.
     */
    public OrderPanel(final Menu menu) {
	this.menu = menu;
	this.nameLabels = new JLabel[this.menu.size()];
	this.priceLabels = new JLabel[this.menu.size()];
	this.quantityFields = new JTextField[this.menu.size()];
	this.layoutView();
	this.registerListeners();
    }

    /**
     * Implements an ActionListener for the 'Print' button. Prints the current
     * contents of the Order to a system printer or PDF.
     */
    private class PrintListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {
	    
	    final PrinterJob job = PrinterJob.getPrinterJob();
	    job.setPrintable(OrderPanel.this.order);
	    
	    if (job.printDialog()) {
		try {
		    job.print();
		} catch (final Exception printException) {
		    printException.printStackTrace();
		}
	    }
	    

	}
    }

    /**
     * Implements a FocusListener on a JTextField. Accepts only positive integers,
     * all other values are reset to 0. Adds a new MenuItem to the Order with the
     * new quantity, updates an existing MenuItem in the Order with the new
     * quantity, or removes the MenuItem from the Order if the resulting quantity is
     * 0.
     */
    private class QuantityListener implements FocusListener {
	private MenuItem item = null;

	QuantityListener(final MenuItem item) {
	    this.item = item;
	}

	@Override
	public void focusGained(final FocusEvent event) {
	    if (event.getComponent() instanceof JTextField) {
		JTextField field = (JTextField) event.getComponent();
		field.selectAll();
	    }
	}

	@Override
	public void focusLost(final FocusEvent event) {
	    for (int i=0; i< OrderPanel.this.quantityFields.length; i++) {
		int amnt = 0;
		
		try {
		    amnt = Integer.parseInt(OrderPanel.this.quantityFields[i].getText());
		} catch (final java.lang.NumberFormatException e) {
		    amnt = 0;
		    OrderPanel.this.order.update(OrderPanel.this.menu.getItem(i), amnt);
		} finally {
		    OrderPanel.this.order.update(OrderPanel.this.menu.getItem(i), amnt);
		}
	    }
	    
	    OrderPanel.this.subtotalLabel.setText(String.format("$%.2f", OrderPanel.this.order.getSubTotal()));
	    OrderPanel.this.taxLabel.setText(String.format("$%.2f", OrderPanel.this.order.getTaxes()));
	    OrderPanel.this.totalLabel.setText(String.format("$%.2f", OrderPanel.this.order.getTotal()));
	}
    }

    /**
     * Layout the panel.
     */
    private void layoutView() {
	this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	this.setLayout(new GridLayout(5 + this.menu.size(), 3));
	
	this.add(new JLabel("Item"));
	this.add(new JLabel("Price"));
	this.add(new JLabel("Quantity"));
	
	// menu items
	for (int i = 0; i < this.menu.size(); i++) {
	    MenuItem item = this.menu.getItem(i);

	    JLabel nameLabel = new JLabel(item.getName());
	    JLabel priceLabel = new JLabel(String.format("$%.2f", item.getPrice()));
	    JTextField quantityField = new JTextField("0");

	    priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	    quantityField.setHorizontalAlignment(JTextField.RIGHT);

	    this.add(nameLabel);
	    this.add(priceLabel);
	    this.add(quantityField);

	    nameLabels[i] = nameLabel;
	    priceLabels[i] = priceLabel;
	    quantityFields[i] = quantityField;
	}
	
	this.add(new JLabel("Subtotal:"));
	this.add(new JLabel(""));
	subtotalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	subtotalLabel.setText("$0.00");
	this.add(subtotalLabel);

	this.add(new JLabel("Tax:"));
	this.add(new JLabel(""));
	taxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	taxLabel.setText("$0.00");
	this.add(taxLabel);

	this.add(new JLabel("Total:"));
	this.add(new JLabel(""));
	totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	totalLabel.setText("$0.00");
	this.add(totalLabel);
	
	// print
	this.add(new JLabel(""));
	this.add(printButton);
	this.add(new JLabel(""));
    }

    /**
     * Register the widget listeners.
     */
    private void registerListeners() {
	// Register the PrinterListener with the print button.
	this.printButton.addActionListener(new PrintListener());

	for (int i = 0; i < this.quantityFields.length; i++) {
	    this.quantityFields[i].addFocusListener(new QuantityListener(menu.getItem(i)));
	}
    }

}