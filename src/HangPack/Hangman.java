package HangPack;

import java.awt.Font;
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
	int wordsi;
	int kscore;
	JLabel won;
	JLabel gameover;
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
		kscore = -1;
		won = new JLabel();
		gameover = new JLabel();
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
		fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String wordstr = JOptionPane.showInputDialog("How many words would you like to answer?");
		wordsi = Integer.parseInt(wordstr);
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/HangPack/dictionary.txt"));

			int ranas;

			Random ran = new Random();
			for (int i = 0; i < wordsi; i++) {
				ranas = ran.nextInt(2998);
				for (int j = 0; j <= ranas; j++) {
					String line = br.readLine();
					if (ranas == j) {
						ranas = ran.nextInt(2998);
						line = br.readLine();
						System.out.println(line);
						words.add(line);
						br.close();
						br = new BufferedReader(new FileReader("src/HangPack/dictionary.txt"));

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

		String currentchar = "";
		System.out.println(currentword);
		chars = new ArrayList<Character>();
		char key = e.getKeyChar();
		dashchars = new ArrayList<Character>();
		String fkey = "" + key;
		ctwords += fkey;
		if (currentword.contains(fkey)) {

		} else {
			lives--;
		}
		if (lives == 0) {
			fram.remove(pan);
			fram.add(gameover);
			gameover.setText("You Lost");
		}
		for (int i = 0; i < currentword.length(); i++) {
			chars.add(currentword.charAt(i));
		}
		for (int i = 0; i < labelText.length(); i++) {
			dashchars.add(labelText.charAt(i));
		}

		for (int i = 0; i < chars.size(); i++) {
			if (chars.get(i).equals(key)) {

				labelText = labelText.substring(0, i) + fkey + labelText.substring(i + 1, labelText.length());
				System.out.println("printing");

			} else {
				System.out.println("life");
			}

		}
		System.out.println(labelText);
		dashes.setText(labelText);
		

		if (labelText.contains("-")) {

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
		Font font = new Font("Papyrus", Font.BOLD, 200);
		kscore++;
		if(kscore == wordsi) {
			fram.remove(pan);
			fram.add(won);
			won.setFont(font);
			won.setText("You Won");
		}
		System.out.println(finwords.peek());
		currentword = finwords.pop();
		
		letters.clear();

		labelText = "";
		for (int j = 0; j < currentword.length(); j++) {
			labelText += "-";

		}
		dashes.setText(labelText);

	
	}

}
