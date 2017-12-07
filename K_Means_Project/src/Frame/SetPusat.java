package Frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;










import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.ListSelectionModel;

public class SetPusat extends JFrame {

	private JPanel contentPane;
	JSpinner spinner;
	JComboBox combo;
	private JTable tableInitial;
	private JTable dataTable;
	JScrollPane scrollPane_1;
	Controller.SetPusat main = new Controller.SetPusat();
	String[] atribut;
	String[][] data;
	String[][] dc;
	JButton btnRandom;
	JButton btnStart;
	JPanel panel_2;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] atribut, final String[][] data) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					SetPusat frame = new SetPusat(atribut,data);
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
	public SetPusat(String[] atribut, String[][] data) {
		setAtt(atribut); setData(data);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 583, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Number of Cluster", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		spinner = new JSpinner();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Initial Cluster", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		btnStart = new JButton("Start Clustering");
		btnStart.setEnabled(false);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dc = new String[tableInitial.getRowCount()][tableInitial.getColumnCount()];
				for(int i=0;i<tableInitial.getRowCount();i++){
					for(int x=0;x<tableInitial.getColumnCount();x++){
						dc[i][x] = (String) tableInitial.getValueAt(i, x);
					}
				}
				
				Iterasi it = new Iterasi(getAtt(), getData(), dc);
				it.setVisible(true);
			}
		});
		
		btnRandom = new JButton("Random Cluster Center");
		btnRandom.setEnabled(false);
		btnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int[] id = main.getIdRand(getData(), tableInitial);
				
				for(int i=0;i<tableInitial.getRowCount();i++){
					for(int x=1;x<tableInitial.getColumnCount();x++){
						tableInitial.setValueAt(dataTable.getValueAt(id[i], x), 
								i, x);
					}
				}
				//tableInitial.setValueAt(arg0, arg1, arg2);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 309, Short.MAX_VALUE)
					.addComponent(btnStart))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
						.addComponent(btnRandom))
					.addContainerGap())
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnStart, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 49, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRandom, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
		);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);
		
		panel_2.setVisible(false);
		
		dataTable = new JTable();
		dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(dataTable);
		
		
		dataTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
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
				for(int i=1;i<tableInitial.getColumnCount();i++){
					tableInitial.setValueAt(
							dataTable.getValueAt(dataTable.getSelectedRow(), i),
							tableInitial.getSelectedRow(), 
							i);
				}
			}
		});
		
		
		combo = new JComboBox();
		combo.addItem("1");
		JButton btnSet = new JButton("Set");
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tableInitial.setModel(main.tableInit((Integer)spinner.getValue(),getAtt()));
				btnRandom.setEnabled(true);
				btnStart.setEnabled(true);
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(spinner, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSet, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
					.addGap(3))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSet)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		tableInitial = new JTable();
		tableInitial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableInitial);
		contentPane.setLayout(gl_contentPane);
		
		tableInitial.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
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
				panel_2.setVisible(true);
				
			}
		});
		tableData();
	}
	
	public void tableData(){
		dataTable.setModel(main.tableData(atribut, data));
		scrollPane_1.setViewportView(dataTable);
	}
	
	public void setAtt(String[] att){
		this.atribut=att;
	}
	
	public void setData(String[][] data){
		
		this.data=data;
	}
	
	public String[] getAtt(){
		return this.atribut;
	}
	
	public String[][] getData(){
		return this.data;
	}
}
