package files;

import java.util.Iterator;

import javafx.animation.FadeTransition;
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
 * Main Function Use fadetransition for text
 *
 * @author Dominic Avellani
 * @category JavaFX
 * @category GameSystems
 */
public class VFXMethods extends Application {

	// Card dimensions
	public final int CARD_HEIGHT = 180;
	public final int CARD_WIDTH = 128;

	// Play window dimensions
	public final int WINDOW_HEIGHT = 768;
	public final int WINDOW_WIDTH = 1024;

	public boolean locked = false;

	public JavaClient connectionToServer;

	public HBox hand, top;
	public VBox center;
	public Label status, actionsLeft;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Create panes
		BorderPane layout = new BorderPane();
		hand = new HBox();
		top = new HBox();
		center = new VBox();

		VBox actions = new VBox();

		// Left setup
		Button button1 = new Button();
		button1.setText("Draw a Card");
		button1.setMaxWidth(100);
		button1.setMinWidth(100);
		button1.setOnAction(e -> {
			connectionToServer.client.sendTCP(new Request("Draw Card"));
		});

		Button button2 = new Button();
		button2.setText("Print Hand");
		button2.setMaxWidth(100);
		button2.setMinWidth(100);
		button2.setOnAction(e -> {
			connectionToServer.client.sendTCP(new Request("Print Hand"));
		});

		Button button3 = new Button();
		button3.setText("Freeze Hand");
		button3.setMaxWidth(100);
		button3.setMinWidth(100);
		button3.setOnAction(e -> {
			freezeHand();
		});

		Button button4 = new Button();
		button4.setText("Unfreeze Hand");
		button4.setMaxWidth(100);
		button4.setMinWidth(100);
		button4.setOnAction(e -> {
			unfreezeHand();
		});

		actions.setAlignment(Pos.CENTER);
		actions.setSpacing(10);
		actions.setMaxWidth(120);
		actions.setMinWidth(120);
		actions.getChildren().addAll(button1, button2, button3, button4);

		// Top setup
		top.setAlignment(Pos.CENTER);
		top.setSpacing(30);
		top.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN, CornerRadii.EMPTY, Insets.EMPTY))); // Background
																														// color
		// Status label
		status = new Label();
		status.setFont(new Font("Papyrus", 20));
		status.setTextFill(Color.WHITE);
		status.setText("Status:");
		top.getChildren().add(status);
		// Actions label
		actionsLeft = new Label();
		actionsLeft.setFont(new Font("Papyrus", 20));
		actionsLeft.setTextFill(Color.WHITE);
		actionsLeft.setText("Actions Left:");
		top.getChildren().add(actionsLeft);

		// Center setup
		center.setAlignment(Pos.CENTER);
		center.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN, CornerRadii.EMPTY, Insets.EMPTY))); // Background
																															// color

		// Bottom bar setup
		hand.setMinHeight(CARD_HEIGHT + (CARD_HEIGHT / 5)); // Bar height
		hand.setMaxHeight(CARD_HEIGHT + (CARD_HEIGHT / 5));
		hand.setSpacing(10); // Card Spacing
		hand.setAlignment(Pos.CENTER);
		hand.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY))); // Background
																													// color

		// BorderPane setup
		layout.setCenter(center);
		layout.setRight(actions);
		layout.setTop(top);
		layout.setBottom(hand);
		layout.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

		// Server setup
		try {
			connectionToServer = new JavaClient(this);
		} catch (Exception e) {
			System.exit(0);
		}

		// Player setup
		connectionToServer.client.sendTCP(new Request("Init User"));

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
			Point2D dropPoint = new Point2D(e.getSceneX(),
					e.getSceneY() - (WINDOW_HEIGHT - (CARD_HEIGHT + (CARD_HEIGHT / 5))));

			if (dropPoint.getY() < 0) {
				// USING A CARD
				Card card = (Card) node.getUserData();
				hand.getChildren().remove(node);
				connectionToServer.client.sendTCP(card);
			} else {
				boolean snap = true;
				for (Node child : hand.getChildren()) {
					if (child instanceof ImageView && !child.equals(node)) {
						if (child.getBoundsInParent().contains(dropPoint)) {
							// CRAFTING
							Card result = CardCreator.getInstance().canCraft((Card) node.getUserData(),
									(Card) child.getUserData());
							if (result != null) {
								// Add new card
								result.action = Action.CRAFT;
								connectionToServer.client.sendTCP(result);

								// Remove first card
								Card card = (Card) node.getUserData();
								card.action = Action.REMOVEFROMHAND;
								connectionToServer.client.sendTCP(card);
								hand.getChildren().remove(node);

								// Remove second card
								card = (Card) child.getUserData();
								card.action = Action.REMOVEFROMHAND;
								connectionToServer.client.sendTCP(card);
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

	/**
	 * Freezes the cards by removing the mouse behaviors.
	 */
	public void freeze(ImageView node) {

		node.setOnMousePressed(e -> {
		});

		node.setOnMouseDragged(e -> {
		});

		node.setOnMouseReleased(e -> {
		});
	}

	/**
	 * Freezes the player's hand.
	 */
	public void freezeHand() {
		Iterator<Node> it = hand.getChildren().iterator();
		while (it.hasNext()) {
			ImageView child = (ImageView) it.next();
			freeze(child);
		}
		locked = true;
	}

	/**
	 * Unfreezes the player's hand.
	 */
	public void unfreezeHand() {
		Iterator<Node> it = hand.getChildren().iterator();
		while (it.hasNext()) {
			ImageView child = (ImageView) it.next();
			makeDraggable(child);
		}
		locked = false;
	}

	/**
	 * Takes a card object and creates a visual representation in the players hand.
	 */
	public void removeCardHromHand(Card card) {
		Iterator<Node> it = hand.getChildren().iterator();
		while (it.hasNext()) {
			Node child = it.next();
			if (child instanceof ImageView && child.getUserData().equals(card)) {
				it.remove();
			}
		}
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
		if (!locked) {
			makeDraggable(image);
		}
	}

	/**
	 * A method that creates a text label in the center of the screen to display
	 * information to the user.
	 */
	public void textNotification(String text) {
		Label label = new Label();
		label.setFont(new Font("Papyrus", 20));
		label.setTextFill(Color.WHITE);
		label.setText(text);

		center.getChildren().add(label);

		FadeTransition ft = new FadeTransition(Duration.millis(5000), label);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);

		ft.setOnFinished(e -> {
			center.getChildren().remove(label);
		});

		ft.play();
	}

	/**
	 * Changes the status bar at the top of the screen
	 */
	public void changeStatus(String text) {
		status.setText("Player " + connectionToServer.client.getID() + " Status: " + text);
	}

	/**
	 * Changes the status bar at the top of the screen
	 */
	public void changeActions(int actions) {
		actionsLeft.setText("Actions Left: " + actions + "/5");
	}

	// A 2D point
	class Delta {
		double x, y;
	}
}
