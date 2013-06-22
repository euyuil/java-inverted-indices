package com.euyuil.java;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchFrame extends JFrame {

	private static final long serialVersionUID = 595008560407711863L;
	
	private JTextField targetPathTextField = new JTextField();
	private JButton browseTargetButton = new JButton();
	private JTextField searchKeywordTextField = new JTextField();
	private JButton searchButton = new JButton();
	
	private InverseIndex index;

	public SearchFrame() {
		
		setResizable(false);
		setMinimumSize(new Dimension(500, 84));
		setTitle("Inverse Index Search");
		
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel targetPanel = new JPanel();
		targetPanel.setLayout(new BoxLayout(targetPanel, BoxLayout.X_AXIS));
		targetPanel.add(new JLabel("Target Directory"));
		targetPanel.add(targetPathTextField);
		targetPathTextField.setEnabled(false);
		targetPathTextField.setText("Click Browse to Choose a Directory.");
		targetPanel.add(browseTargetButton);
		browseTargetButton.setText("Browse...");
		browseTargetButton.setPreferredSize(new Dimension(96, 42));
		browseTargetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickBrowseTargetButton(e);
			}
		});
		getContentPane().add(targetPanel);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		searchPanel.add(new JLabel("Search Keywords"));
		searchPanel.add(searchKeywordTextField);
		searchPanel.add(searchButton);
		searchButton.setText("Search...");
		searchButton.setPreferredSize(new Dimension(96, 42));
		searchButton.setEnabled(false);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickSearchButton(e);
			}
		});
		getContentPane().add(searchPanel);
	}
	
	private void onClickBrowseTargetButton(ActionEvent event) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int r = chooser.showOpenDialog(this);
		if (r == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			targetPathTextField.setText(file.toString());
			index = new InverseIndex(file);
			searchButton.setEnabled(true);
		}
	}
	
	private void onClickSearchButton(ActionEvent event) {
		String keywords = searchKeywordTextField.getText();
		List<String> results = index.search(keywords);
		JFrame frame = new ResultFrame(results);
		frame.setVisible(true);
	}
}
