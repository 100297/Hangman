package HangPack;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman implements KeyListener {
	JFrame fram;
	JPanel pan;
	String currentword;
	public static void main(String[] args) {
		Hangman a = new Hangman();
		a.activate();
	}

	void activate() {
		fram = new JFrame();
		pan = new JPanel();
		fram.add(pan);
		fram.setVisible(true);
		fram.pack();
		pan.addKeyListener(this);
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<JLabel> letters = new ArrayList<JLabel>();
		String wordstr = JOptionPane.showInputDialog("How many words would you like to answer?");
		int wordsi = Integer.parseInt(wordstr);
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/HangPack/dictionary.txt"));

			int ranas;
			
			Random ran = new Random();
			for (int i = 0; i < wordsi; i++) {
				ranas = ran.nextInt(2998);
				for (int j = 0; j < 2998; j++) {
					if (ranas == j) {
						String line = br.readLine();
						words.add(line);
					}
				}

			}
			br.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean spelled = false;
		Stack<String> finwords = new Stack<String>();
		for (int i = 0; i < words.size(); i++) {
			finwords.push(words.get(i));
		}
		for (int i = 0; i < words.size(); i++) {
			currentword = finwords.pop();
			letters.clear();
			JLabel dashes = new JLabel();
			String labelText = "";
			for (int j = 0; j < currentword.length(); j++) {
				labelText += "-";

			}
			dashes.setText(labelText);
			pan.add(dashes);
			if(dashes.equals(currentword)) {
				spelled = true;
			}
			if(spelled == true) {
					
				}
			}

		}

	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		ArrayList<Character> chars = new ArrayList<Character>();
		char key = e.getKeyChar();
		String fkey = "" + key;
		for (int i = 0; i < currentword.length(); i++) {
			chars.add(currentword.indexOf(i));
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
