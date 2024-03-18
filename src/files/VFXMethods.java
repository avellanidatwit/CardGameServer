package files;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Main Function
 * Use fadetransition for text
 * @author Dominic Avellani
 */
public class VFXMethods extends Application {
	
	public final int CARD_HEIGHT = 180;
	public final int CARD_WIDTH = 128;
	
	public final int WINDOW_HEIGHT = 768;
	public final int WINDOW_WIDTH = 1024;
	
	public static final String ERR_INPUT = "Invalid input. Please only input 1 letter, C or S.";
	public static TurnStates turn = null;
	
	public User player = new User("Player 1");
	
	HashMap<String, Card> cardData = new HashMap<String, Card>(){{
	    put("Stick", new Card());
	}};

	public static void main(String[] args) {
		
		
		// Launches the GUI
        Application.launch(args);
        
        // Server Setup
//		Scanner input = new Scanner(System.in);
//		System.out.printf("Are you opening a client, or a server? (C/S): ");
//		try {
//			char uInput = input.next().charAt(0);
//			if(uInput == 'C') {
//				//TODO: run client
//			}
//			else if(uInput == 'S'){
//				//TODO: run server
//			}
//			else {
//				System.out.printf(ERR_INPUT);
//			}
//		}
//		catch (InputMismatchException e) {
//			System.out.printf(ERR_INPUT);
//		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// Create panes
		BorderPane layout = new BorderPane();
		HBox cardHand = new HBox();
		Pane actionSelection = new Pane();
		
		// BorderPane setup
		layout.setCenter(actionSelection);
		layout.setBottom(cardHand);
		layout.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		
		// Center setup
		actionSelection.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN, CornerRadii.EMPTY, Insets.EMPTY))); // Background color
		
		// Bottom bar setup
		cardHand.setMinHeight(CARD_HEIGHT + (CARD_HEIGHT/5)); // Bar height
		cardHand.setMaxHeight(CARD_HEIGHT + (CARD_HEIGHT/5));
		cardHand.setSpacing(10); // Card Spacing
		cardHand.setAlignment(Pos.CENTER);
		cardHand.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY))); // Background color
		
		// Adding 7 test cards
		for (int i = 1; i <= 7; i++) { 
			ImageView image = new ImageView(new Image("file:src/resources/Ingredient Deck_0" + i + ".png"));
			image.setFitHeight(CARD_HEIGHT);
			image.setFitWidth(CARD_WIDTH);
			image.setUserData(new LocationCard("Test Card: " + i, "This is a test description"));
			cardHand.getChildren().add(image);
			makeDraggable(image, cardHand); // Testing draggable
		}
		
		// Stage setup
		primaryStage.setTitle("Card Game"); // Window title
		primaryStage.setScene(new Scene(layout, WINDOW_WIDTH, WINDOW_HEIGHT)); // Set start scene
		primaryStage.show();
	}
	
	public void makeDraggable(ImageView node, Pane container) {
		final Delta start = new Delta();
		final Delta dragDelta = new Delta();
		
		// Enable transfer mode to allow dragging
	    node.setOnDragDetected(e -> {
	        node.startFullDrag();
	    });

	    node.setOnMousePressed(e -> {
	    	start.x = node.getTranslateX();
			start.y = node.getTranslateY();
	    	dragDelta.x = node.getTranslateX() - e.getSceneX();
	        dragDelta.y = node.getTranslateY() - e.getSceneY();
	    });

	    node.setOnMouseDragged(e -> {
	    	node.setTranslateX(e.getSceneX() + dragDelta.x);
        	node.setTranslateY(e.getSceneY() + dragDelta.y);
	    });

	    node.setOnMouseReleased(e -> {
	        Point2D dropPoint = new Point2D(e.getSceneX(), e.getSceneY()-(WINDOW_HEIGHT -(CARD_HEIGHT + (CARD_HEIGHT/5))));
	        
	        if (dropPoint.getY() < 0 ) {
	        	System.out.println("Use Card");
	        	Card source = (Card)node.getUserData();
	        	source.trigger();
	        	container.getChildren().remove(node);
	        }
	        else {
	        	boolean snap = true;
		        for (Node child : container.getChildren()) {
		            if (child instanceof ImageView && child.equals(node) == false) {
			        	if (child.getBoundsInParent().contains(dropPoint)) {
			        		Card target = (Card)child.getUserData(); // Get card information
			        		Card source = (Card)node.getUserData();
			        		System.out.println("Carfting " + source.getName() + " with " + target.getName()); // Print card name
			        		snap = false;
			        		container.getChildren().remove(node);
			        		container.getChildren().remove(child);
			        		break;
			        	}
		            }
		        }
		        
		        if (snap) {
		        	// Reset to start position
		        	node.setTranslateX(start.x);
		        	node.setTranslateY(start.y);
		        }
	        }
	    });

	    
	}
	
	class Delta {
        double x, y;
    }
}
