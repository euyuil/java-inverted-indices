package com.euyuil.java;

import java.awt.Dimension;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ResultFrame extends JFrame {

	private static final long serialVersionUID = -1765668004741492868L;

	public ResultFrame(List<String> results) {
		
		setMinimumSize(new Dimension(576, 343));

		JPanel resultPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(resultPanel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setContentPane(scrollPane);
		
		resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
		
		for (String result : results) {
			JButton resultButton = new JButton(result);
			resultPanel.add(resultButton);
		}
	}
}
