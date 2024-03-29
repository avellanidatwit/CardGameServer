package files;

import javafx.animation.FadeTransition;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Main Function
 * Use fadetransition for text
 * @author Dominic Avellani
 */
public class VFXMethods extends Application {
	
	// Card dimensions
	public final int CARD_HEIGHT = 180;
	public final int CARD_WIDTH = 128;
	
	// Play window dimensions
	public final int WINDOW_HEIGHT = 768;
	public final int WINDOW_WIDTH = 1024;
	
	public static TurnStates turn = null;
	public static final String ERR_INPUT = "Invalid input. Please only input 1 letter, C or S.";
	public static final String ERR_CONNECTION_FAIL = "Connection failed. Exiting program.\n Stack Trace: ";
	
	public User player;
	
	public HBox hand = null;
	public VBox center = null;

	public static void main(String[] args) {Application.launch(args);}

	@Override
	public void start(Stage primaryStage) throws Exception {
		player = new User("Player 1");
		
		// Create panes
		BorderPane layout = new BorderPane();
		hand = new HBox();
		center = new VBox();
		VBox actions = new VBox();
		
		// Left setup
		Button drawCardButton = new Button();
		drawCardButton.setText("Draw a Card");
		drawCardButton.setMaxWidth(100);
		drawCardButton.setMinWidth(100);
		drawCardButton.setOnAction(e -> {addCardToHand(player.drawCard());});
		
		Button textTestButton = new Button();
		textTestButton.setText("Test text");
		textTestButton.setMaxWidth(100);
		textTestButton.setMinWidth(100);
		textTestButton.setOnAction(e -> {textNotification("Welcome to earth!!");});
		
		actions.setAlignment(Pos.CENTER);
		actions.setSpacing(10);
		actions.setMaxWidth(120);
		actions.setMinWidth(120);
		actions.getChildren().addAll(drawCardButton, textTestButton);
		
		// Center setup
		center.setAlignment(Pos.CENTER);
		center.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN, CornerRadii.EMPTY, Insets.EMPTY))); // Background color
		
		// Bottom bar setup
		hand.setMinHeight(CARD_HEIGHT + (CARD_HEIGHT/5)); // Bar height
		hand.setMaxHeight(CARD_HEIGHT + (CARD_HEIGHT/5));
		hand.setSpacing(10); // Card Spacing
		hand.setAlignment(Pos.CENTER);
		hand.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY))); // Background color
		
		// BorderPane setup
		layout.setCenter(center);
		layout.setRight(actions);
		layout.setBottom(hand);
		layout.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
				
		// Adding test cards
		addCardToHand(CardCreator.getInstance().createCard("Stick"));
		addCardToHand(CardCreator.getInstance().createCard("Stick"));
		addCardToHand(CardCreator.getInstance().createCard("Stick"));
		addCardToHand(CardCreator.getInstance().createCard("Sharp Stone"));
		addCardToHand(CardCreator.getInstance().createCard("Sharp Stone"));
		addCardToHand(CardCreator.getInstance().createCard("Rope"));
		addCardToHand(CardCreator.getInstance().createCard("Rope"));
		
		
		// Stage setup
		primaryStage.setTitle("Wasteland"); // Window title
		primaryStage.setScene(new Scene(layout, WINDOW_WIDTH, WINDOW_HEIGHT)); // Set start scene
		primaryStage.show();
	}
	
	/**
	 * Makes the cards interactable by adding mouse behaviors.
	 */
	public void makeDraggable(ImageView node) {
		final Delta start = new Delta();
		final Delta dragDelta = new Delta();

	    node.setOnMousePressed(e -> {
	    	start.x = node.getTranslateX();
			start.y = node.getTranslateY();
			node.setViewOrder(-1);
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
	        	discardCard(node);
	        }
	        else {
	        	boolean snap = true;
		        for (Node child : hand.getChildren()) {
		            if (child instanceof ImageView && child.equals(node) == false) {
			        	if (child.getBoundsInParent().contains(dropPoint)) {
			        		Card result = CardCreator.getInstance().canCraft((Card)node.getUserData(), (Card)child.getUserData());
			        		if(result != null) {
			        			hand.getChildren().remove(node);
				        		hand.getChildren().remove(child);
				        		player.discard.addCard(result);
				        		textNotification("Successfully crafted " + result.getName() + ".");
				        		snap = false;
			        		}
			        		break;
			        	}
		            }
		        }
		        
		        if (snap) {
		        	// Reset to start position
		        	node.setTranslateX(start.x);
		        	node.setTranslateY(start.y);
		        	node.setViewOrder(0);
		        }
	        }
	    });
	}
	
	/**
	 * Takes a card object and creates a visual representation in the players hand.
	 */
	public void addCardToHand(Card card) {
		ImageView image = new ImageView(card.getImage());
		image.setFitHeight(CARD_HEIGHT);
		image.setFitWidth(CARD_WIDTH);
		image.setUserData(card);
		hand.getChildren().add(image);
		makeDraggable(image); // Testing draggable
	}
	
	/**
	 * Removes the visual card from the player and adds the object to the discard pile.
	 */
	public void discardCard(ImageView node) {
		Card card = (Card) node.getUserData();
		hand.getChildren().remove(node);
		player.discardCard(card);
		textNotification(card.getName() + " was discarded.");}
	
	/**
	 * A method that creates a text label in the center of the screen to display information to the user.
	 */
	public void textNotification(String text) {
		Label label = new Label();
		label.setFont(new Font("Papyrus", 30));
		label.setTextFill(Color.WHITE);
		label.setText(text);
		
		center.getChildren().add(label);
		
		FadeTransition ft = new FadeTransition(Duration.millis(5000), label);
	    ft.setFromValue(1.0);
	    ft.setToValue(0.0);
	    ft.setCycleCount(1);
	    ft.setAutoReverse(false);
	    
	    ft.setOnFinished(e -> {center.getChildren().remove(label);});
	 
	    ft.play();
	}
	
	class Delta {
        double x, y;
    }
}
