package Frame;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.JComboBox;
import javax.swing.UIManager;

import java.awt.Color;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;


public class SetData extends JFrame {

	private JPanel contentPane;
	private JTextField alamat;
	private Controller.SetData main = new Controller.SetData();
	private JTextField from;
	private JTextField to;
	private JComboBox comboBox;
	String[] sheet;
	private JTable table;
	JScrollPane scrollPane;
	JCheckBox withoutAtt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetData frame = new SetData();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SetData() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JPanel panel = new JPanel();
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Input File Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		alamat = new JTextField();
		alamat.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		alamat.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setFont(new Font("Trebuchet MS", Font.BOLD, 11));
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alamat.setText(main.openFile());
			}
		});
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!alamat.getText().equals("")){
					main.setAlamat(alamat.getText());
					sheet= main.getSheet(alamat.getText());
					
					for(int i=comboBox.getItemCount()-1;i>=0;i--){
						comboBox.removeItemAt(i);
					}
					
					for(int i=0;i<sheet.length;i++){
						
						comboBox.addItem(sheet[i]);
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "alamat kosong");
				}
			}
		});
		btnCheck.setFont(new Font("Trebuchet MS", Font.BOLD, 11));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(alamat, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBrowse)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCheck))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCheck)
						.addComponent(btnBrowse)
						.addComponent(alamat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Select Data", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Sheet", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cell", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.getData(alamat.getText(), comboBox.getSelectedItem().toString());
				table.setModel(main.table());
				
				DefaultTableCellRenderer center = new DefaultTableCellRenderer();
				center.setHorizontalAlignment(SwingConstants.CENTER);
				for(int i=0;i<table.getColumnCount();i++){
					table.setValueAt(table.getValueAt(0, i).toString().toUpperCase(), 0, i);
				}
				table.getColumnModel().getColumn(0).setCellRenderer(center);
			}
		});
		
		JButton btnProcess = new JButton("Process");
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[][] data = main.getSelectedData(table);
				SetPusat sp = new SetPusat(main.getAtt(withoutAtt, data), main.getData(withoutAtt, data,table));
				sp.setVisible(true);
				
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(34)
					.addComponent(btnProcess, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
					.addGap(44))
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addGap(39)
							.addComponent(btnSelect, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
							.addGap(31))
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 127, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(16))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSelect, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnProcess)
					.addContainerGap(78, Short.MAX_VALUE))
		);
		
		JLabel lblFrom = new JLabel("From");
		
		from = new JTextField();
		from.setEnabled(false);
		from.setColumns(10);
		
		JLabel lblTo = new JLabel("to");
		
		to = new JTextField();
		to.setEnabled(false);
		to.setColumns(10);
		
		withoutAtt = new JCheckBox("without attribute");
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(withoutAtt)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFrom)
								.addComponent(lblTo))
							.addGap(18)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING, false)
								.addComponent(to, 0, 0, Short.MAX_VALUE)
								.addComponent(from, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFrom)
						.addComponent(from, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTo)
						.addComponent(to, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(withoutAtt, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		comboBox = new JComboBox();
		panel_3.add(comboBox);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Data Prieview", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel_6.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				from.setText(main.fromCell(table));
				to.setText(main.toCell(table));
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		table.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				from.setText(main.fromCell(table));
				to.setText(main.toCell(table));
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(panel_2, BorderLayout.NORTH);
		panel.add(panel_1, BorderLayout.WEST);
		panel.add(panel_6, BorderLayout.CENTER);
		
	}
	
	public void setAlamat(String alamat){
		this.alamat.setText(alamat);
	}
	
	public String getAlamat(){
		return alamat.getText();
	}
	
	@Override
	public Container getContentPane() {
		return super.getContentPane();
	}
}
