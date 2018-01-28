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
	ArrayList<Character> chars;
	ArrayList<String> words;
	ArrayList<JLabel> letters;
	Stack<String> finwords;
	String ctwords;
	boolean spelled;
	String labelText;
	JLabel dashes;
	ArrayList<Character> dashchars;
	int lives;
	JLabel jlives;
	boolean isadded;
	public static void main(String[] args) {
		Hangman a = new Hangman();
		a.activate();
	}

	void activate() {
		jlives = new JLabel();
		lives = 10;
		jlives.setText(lives + "");
		dashes = new JLabel();
		isadded = false;
		fram = new JFrame();
		pan = new JPanel();
		pan.add(dashes);
		pan.add(jlives);
		fram.add(pan);
		fram.setVisible(true);
		fram.pack();
		fram.addKeyListener(this);
		words = new ArrayList<String>();
		letters = new ArrayList<JLabel>();
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
		finwords = new Stack<String>();
		for (int i = 0; i < words.size(); i++) {
			finwords.push(words.get(i));
		}
		spellword();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(currentword);
		chars = new ArrayList<Character>();
		char key = e.getKeyChar();
		dashchars = new ArrayList<Character>();
		String fkey = "" + key;
		ctwords += fkey;
		for (int i = 0; i < currentword.length(); i++) {
			chars.add(currentword.charAt(i));
		}
		for (int i = 0; i < labelText.length(); i++) {
			dashchars.add(labelText.charAt(i));
		}

		for (int i = 0; i < chars.size(); i++) {
			if (chars.get(i).equals(key)) {
				labelText = labelText.substring(0, i) + fkey + labelText.substring(i+1, labelText.length());
				System.out.println("printing");
				
			} else {
				lives--;
			}
			
		}
		System.out.println(labelText);
		dashes.setText(labelText);
		if(labelText.contains("-")) {	
			
		} else {
			spellword();
		}
		jlives.setText(lives + "");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	void spellword() {

		currentword = finwords.pop();
		letters.clear();
		
		labelText = "";
		for (int j = 0; j < currentword.length(); j++) {
			labelText += "-";

		}
		dashes.setText(labelText);
		

	}

}
