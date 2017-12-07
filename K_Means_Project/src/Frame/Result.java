package Frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import org.apache.commons.collections4.Put;
import org.apache.poi.ss.usermodel.Chart;
import org.apache.poi.xslf.usermodel.PieChartDemo;

import Class.MyTableCellRenderer;
import Class.PieChart;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JInternalFrame;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.EventObject;
import java.util.List;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.UIManager;

public class Result extends JFrame {

	private JPanel contentPane;
	public JTable tHasil;
	public String[][] keldata;
	Controller.Result main = new Controller.Result();
	private JTable table;
	JPanel panel_1;
	private JTable tableK;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Result frame = new Result();
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
	public Result() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 671, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Result Cluster Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Color", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_2, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
		);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		tableK = new JTable();
		scrollPane_1.setViewportView(tableK);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(27)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
		);
		panel_2.setLayout(gl_panel_2);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	 panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
	 
	 
	 
	 
	 
	}
	
	public void setData(){
		List<List<String>> listC = main.listC(tHasil, keldata);
		List<String> listE;
		
		ArrayList<Double> values = new ArrayList<Double>(listC.size());
		final ArrayList<Color> colors = new ArrayList<Color>(listC.size());
		
		double jumlah=0.0;
		 for(int i=0;i<listC.size();i++){
			listE = listC.get(i);
			System.out.println(listC.get(i));
			 
			values.add(Math.nextUp((double)listC.get(i).size()/(double)keldata.length*100));
			System.out.println(values.get(i));
			jumlah+=values.get(i);
			colors.add(Color.getHSBColor((float) i / listC.size(), 1, 1));
		 }
		 
		 System.out.println(jumlah);
		 table.setModel(main.setTable(listC,keldata));
		 
		 PieChart p = new PieChart(values, colors);
		 panel_1.add(p);
		 
		 tableK.setModel(main.tableK(colors));
		 MyTableCellRenderer mtr = new MyTableCellRenderer();
		 mtr.colors=colors;
		 tableK.setDefaultRenderer(Object.class,(TableCellRenderer) mtr);
		 
	}
	
	
	
	public void add(){
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
	}
	public void setThasil(JTable tHasil){
		this.tHasil=tHasil;
	}
	
	public void setKelData(String[][] keldata){
		this.keldata=keldata;
	}
}
