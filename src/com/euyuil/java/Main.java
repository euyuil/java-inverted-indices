package com.euyuil.java;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		final JFrame frame = new SearchFrame();
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				frame.dispose();
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}
}
