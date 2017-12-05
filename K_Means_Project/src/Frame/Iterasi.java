package Frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Iterasi extends JFrame {

	private JPanel contentPane;
	private JTable tableC;
	private JTable tableIt;
	Controller.Iterasi main = new Controller.Iterasi();
	String[][]data;
	String[][]dc;
	JLabel numIt;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] att, final String[][] data, final String[][] dc) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Iterasi frame = new Iterasi(att,data,dc);
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
	public Iterasi(final String[] att, String[][] data, String[][] dc) {
		setData(data);
		setDc(dc);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 619, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cluster", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		
		JLabel lblIteration = new JLabel("Iteration");
		
		numIt = new JLabel("0");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblIteration)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(numIt)
					.addGap(539))
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(numIt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblIteration, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
		);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);
		
		tableIt = new JTable();
		scrollPane_1.setViewportView(tableIt);
		panel.setLayout(new BorderLayout(0, 0));
		
		
		tableIt.setModel(main.dataIt(att, data, dc, 
				main.hitungJarakP(data, dc), 
				main.kelompokData(main.hitungJarakP(data, dc))));
		
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		tableC = new JTable();
		scrollPane.setViewportView(tableC);
		tableC.setModel(main.clusterT(att, dc));
		
		JButton btnN = new JButton("Next -->");
		btnN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double[][] hasil = main.hitungJarakP(getData(), getDc());
				String[][] keldata = main.kelompokData(hasil);
				String[][] dcB = main.pusatBaru(keldata,getData());
				if(main.checkStop(getDc(), dcB)){
					tableC.setModel(main.clusterT(att, dcB));
					tableIt.setModel(main.dataIt(att, getData(), dcB, hasil, keldata));
					setDc(dcB);
					numIt.setText(Integer.toString(Integer.parseInt(numIt.getText())+1));
				}else{
					JOptionPane.showMessageDialog(null, "berhenti pada iterasi ke-"+numIt.getText());
				}
				
				
			}
		});
		
		JButton buttonP = new JButton("<-- Prev");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(buttonP)
					.addPreferredGap(ComponentPlacement.RELATED, 429, Short.MAX_VALUE)
					.addComponent(btnN)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnN)
						.addComponent(buttonP))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
		
	}

	public void setData(String[][] data2) {
		this.data=data2;
		
	}
	
	public void setDc(String[][] dc2){
		this.dc=dc2;
	}
	
	public String[][] getData(){
		return this.data;
	}
	
	public String[][] getDc(){
		return this.dc;
	}
	
	

}
