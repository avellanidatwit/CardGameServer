package files;

import javafx.application.Application;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Main file, executes the game.
 * @author evelyn
 */
public class Main {
	
	public static final String ERR_INPUT = "Invalid input. Please only input 1 letter, C or S.";
	public static TurnStates turn = null;

	public static void main(String[] args) {
		// Launches the GUI
        Application.launch(VFXMethods.class, args);
        
        // Server Setup
		Scanner input = new Scanner(System.in);
		System.out.printf("Are you opening a client, or a server? (C/S): ");
		try {
			char uInput = input.next().charAt(0);
			if(uInput == 'C') {
				//TODO: run client
			}
			else if(uInput == 'S'){
				//TODO: run server
			}
			else {
				System.out.printf(ERR_INPUT);
			}
		}
		catch (InputMismatchException e) {
			System.out.printf(ERR_INPUT);
		}
	}
}
