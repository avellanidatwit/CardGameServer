package files;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * TODO: something's broken with git and i dont know what
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
	public HBox hand = null;
	
	private HashMap<ArrayList<String>, Card> recipes = new HashMap<ArrayList<String>, Card>();

	public static void main(String[] args) {Application.launch(args);}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// Crafting recipes
		recipes.put(new ArrayList<String>() {{ add("Stick"); add("Sharp Stone");}}, CardCreator.createCard("Axe"));
		recipes.put(new ArrayList<String>() {{ add("Stick"); add("Stick");}}, CardCreator.createCard("Log"));
		
		// Create panes
		BorderPane layout = new BorderPane();
		hand = new HBox();
		Pane center = new Pane();
		VBox actions = new VBox();
		
		// Left setup
		Button drawCard = new Button();
		drawCard.setText("Draw a Card");
		drawCard.setMaxWidth(100);
		drawCard.setMinWidth(100);
		drawCard.setOnAction(e -> {addCardToHand(player.drawCard());});
		
		actions.setAlignment(Pos.CENTER);
		actions.setSpacing(10);
		actions.setMaxWidth(120);
		actions.setMinWidth(120);
		actions.getChildren().add(drawCard);
		
		// Center setup
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
		addCardToHand(CardCreator.createCard("Stick"));
		addCardToHand(CardCreator.createCard("Stick"));
		addCardToHand(CardCreator.createCard("Stick"));
		addCardToHand(CardCreator.createCard("Sharp Stone"));
		addCardToHand(CardCreator.createCard("Sharp Stone"));
		
		// Stage setup
		primaryStage.setTitle("Wasteland"); // Window title
		primaryStage.setScene(new Scene(layout, WINDOW_WIDTH, WINDOW_HEIGHT)); // Set start scene
		primaryStage.show();
	}
	
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
			        		Card result = canCraft((Card)node.getUserData(), (Card)child.getUserData());
			        		System.out.println(result);
			        		if(result != null) {
			        			player.discard.addCard(result);
			        			hand.getChildren().remove(node);
				        		hand.getChildren().remove(child);
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
	
	public void addCardToHand(Card card) {
		ImageView image = new ImageView(card.getImage());
		image.setFitHeight(CARD_HEIGHT);
		image.setFitWidth(CARD_WIDTH);
		image.setUserData(card);
		hand.getChildren().add(image);
		makeDraggable(image); // Testing draggable
	}
	
	public void discardCard(ImageView node) {
		Card card = (Card) node.getUserData();
		hand.getChildren().remove(node);
		player.discard(card);
	}
	
	public Card canCraft(Card card1, Card card2) {
		String[] input1 = {card1.getName(), card2.getName()};
		ArrayList<String> input = new ArrayList<String>() {{ add(card1.getName()); add(card2.getName());}};
		Collections.sort(input);
		System.out.println(input);
		for(ArrayList<String> key : recipes.keySet()) {
			ArrayList<String> temp = new ArrayList<>(key);
			Collections.sort(temp);
			System.out.println(temp);
			if(input.equals(temp)) {
				return recipes.get(key);
			}
		}
		return null;
	}
	
	class Delta {
        double x, y;
    }
}
