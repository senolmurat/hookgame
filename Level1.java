import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Level1 extends Application {

	boolean collisionDetected = false;
	ArrayList<Timeline> allAnims;
	Pane pane;

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {

		Group group1 = new Group();
		Group group2 = new Group();
		allAnims = new ArrayList<>();

		// For Circle1
		Circle circle1 = new Circle();
		circle1.setCenterX(600);
		circle1.setCenterY(600);
		circle1.setRadius(35);
		circle1.setStroke(Color.DARKRED);
		circle1.setFill(Color.BROWN);

		Line C1thinVLine1 = new Line(600, 565, 600, 465);

		Line C1thickHLine1 = new Line(585, 465, 615, 465);
		C1thickHLine1.setStrokeWidth(5);
		C1thickHLine1.setStrokeLineCap(null);

		Line C1thickVLine1 = new Line(600, 465, 600, 185);
		C1thickVLine1.setStrokeWidth(6);

		circle1.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition st = new ScaleTransition(Duration.millis(150), circle1);
				st.setByX(0.15);
				st.setByY(0.15);
				st.setCycleCount(2);
				st.setAutoReverse(true);
				st.play();

				Timeline C1AnimationVertical = new Timeline(new KeyFrame(Duration.seconds((465 - 185) / 200.0),
						new KeyValue(C1thickVLine1.endYProperty(), 465)));
				C1AnimationVertical.play();

				Timeline C1AnimationHorizontalRight = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(C1thickHLine1.startXProperty(), 560)));
				C1AnimationHorizontalRight.play();

				Timeline C1AnimationHorizontalLeft = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(C1thickHLine1.endXProperty(), 640)));
				C1AnimationHorizontalLeft.play();

				Timeline[] animations = { C1AnimationVertical, C1AnimationHorizontalRight, C1AnimationHorizontalLeft };
				Collections.addAll(allAnims, animations);

				C1AnimationVertical.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						group1.getChildren().addAll(C1thickVLine1, C1thickHLine1, C1thinVLine1, circle1);
						FadeTransition ft = new FadeTransition(Duration.millis(500), group1);
						ft.setFromValue(1.0);
						ft.setToValue(0.0);
						ft.play();

					}
				});
			}
		});

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// For Circle2
		Circle circle2 = new Circle();
		circle2.setCenterX(850);
		circle2.setCenterY(500);
		circle2.setRadius(35);
		circle2.setStroke(Color.DARKRED);
		circle2.setFill(Color.BROWN);

		Line C2thinHLine1 = new Line(815, 500, 155, 500);

		Arc C2thinArc1 = new Arc(155, 495, 5, 5, 270, -90);
		C2thinArc1.setType(ArcType.OPEN);
		C2thinArc1.setFill(null);
		C2thinArc1.setStroke(Color.BLACK);

		Line C2thinVLine1 = new Line(150, 495, 150, 205);

		Line C2thinHLine2 = new Line(155, 200, 170, 200);

		Arc C2thinArc2 = new Arc(155, 205, 5, 5, 180, -90);
		C2thinArc2.setType(ArcType.OPEN);
		C2thinArc2.setFill(null);
		C2thinArc2.setStroke(Color.BLACK);

		Line C2thickVLine1 = new Line(170, 185, 170, 215);
		C2thickVLine1.setStrokeWidth(5);
		C2thickVLine1.setStrokeLineCap(null);

		Line C2thickHLine1 = new Line(170, 200, 555, 200);
		C2thickHLine1.setStrokeWidth(6);

		Arc C2Arc3 = new Arc(600, 200, 40, 40, 0, 180);
		C2Arc3.setType(ArcType.OPEN);
		C2Arc3.setFill(null);
		C2Arc3.setStroke(Color.BLACK);
		C2Arc3.setStrokeWidth(6);

		Line C2thickHLine2 = new Line(645, 200, 800, 200);
		C2thickHLine2.setStrokeWidth(6);

		circle2.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition st = new ScaleTransition(Duration.millis(150), circle2);
				st.setByX(0.15);
				st.setByY(0.15);
				st.setCycleCount(2);
				st.setAutoReverse(true);
				st.play();

				Timeline C2AnimationHorizontal = new Timeline(new KeyFrame(Duration.seconds((555 - 170) / 200.0),
						new KeyValue(C2thickHLine1.endXProperty(), 170)));
				C2AnimationHorizontal.play();

				Timeline C2AnimationVerticalUp = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(C2thickVLine1.startYProperty(), 160)));
				C2AnimationVerticalUp.play();

				Timeline C2AnimationVerticalDown = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(C2thickVLine1.endYProperty(), 240)));
				C2AnimationVerticalDown.play();

				Timeline C2AnimationHorizontalEnd = new Timeline(new KeyFrame(Duration.seconds((800 - 170) / 200.0),
						new KeyValue(C2thickHLine2.endXProperty(), 170)));
				C2AnimationHorizontalEnd.play();

				Timeline C2AnimationHorizontalStart = new Timeline(new KeyFrame(Duration.seconds((645 - 170) / 200.0),
						new KeyValue(C2thickHLine2.startXProperty(), 170)));
				C2AnimationHorizontalStart.play();

				Timeline C2AnimationArc = new Timeline(new KeyFrame(Duration.seconds((600 - 130) / 200.0),
						new KeyValue(C2Arc3.centerXProperty(), 130)));
				C2AnimationArc.play();

				Timeline C2ArcDisappear = new Timeline(
						new KeyFrame(Duration.millis(500), new KeyValue(C2Arc3.lengthProperty(), 0)));
				C2ArcDisappear.setDelay(Duration.seconds((547 - 170) / 200.0));
				C2ArcDisappear.play();

				Timeline[] animations = { C2AnimationHorizontal, C2AnimationVerticalUp, C2AnimationVerticalDown,
						C2AnimationHorizontalEnd, C2AnimationHorizontalStart, C2AnimationArc, C2ArcDisappear };
				Collections.addAll(allAnims, animations);

				C2AnimationHorizontalEnd.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						group2.getChildren().addAll(C2thinVLine1, C2thinArc1, C2thinArc2, C2thinHLine1, C2thinHLine2,
								C2thickVLine1, C2thickHLine1, C2thickHLine2, C2Arc3, circle2);

						FadeTransition ft = new FadeTransition(Duration.millis(500), group2);
						ft.setFromValue(1.0);
						ft.setToValue(0.0);
						ft.play();

					}
				});
			}
		});

		Timeline CollisionCheck = new Timeline(new KeyFrame(Duration.millis(50), event -> {

			checkShapeIntersection(C2Arc3, C1thickVLine1);

			if (collisionDetected) {

				for (Timeline anim : allAnims) {
					anim.pause();
				}

				pane.getChildren().clear();

				circle2.setCenterX(850);
				circle2.setCenterY(500);

				C2thinHLine1.setStartX(815);
				C2thinHLine1.setStartY(500);
				C2thinHLine1.setEndX(155);
				C2thinHLine1.setEndY(500);

				C2thinVLine1.setStartX(150);
				C2thinVLine1.setStartY(495);
				C2thinVLine1.setEndX(150);
				C2thinVLine1.setEndY(205);

				C2thinHLine2.setStartX(155);
				C2thinHLine2.setStartY(200);
				C2thinHLine2.setEndX(170);
				C2thinHLine2.setEndY(200);

				C2thickVLine1.setStartX(170);
				C2thickVLine1.setStartY(185);
				C2thickVLine1.setEndX(170);
				C2thickVLine1.setEndY(215);

				C2thickHLine1.setStartX(170);
				C2thickHLine1.setStartY(200);
				C2thickHLine1.setEndX(555);
				C2thickHLine1.setEndY(200);

				C2thickHLine2.setStartX(645);
				C2thickHLine2.setStartY(200);
				C2thickHLine2.setEndX(800);
				C2thickHLine2.setEndY(200);

				C2thinArc1.setCenterX(155);
				C2thinArc1.setCenterY(495);

				C2thinArc2.setCenterX(155);
				C2thinArc2.setCenterY(205);

				C2Arc3.setCenterX(600);
				C2Arc3.setCenterY(200);

				pane.getChildren().addAll(circle1, C1thinVLine1, C1thickHLine1, C1thickVLine1, circle2, C2thinHLine1,
						C2thinVLine1, C2thinHLine2, C2thickVLine1, C2thickHLine1, C2thickHLine2, C2thinArc1, C2thinArc2,
						C2Arc3, group1, group2);
			}

		}));
		CollisionCheck.setCycleCount(Timeline.INDEFINITE);
		CollisionCheck.play();
		//////////////////////////
		// Create a pane to hold the circle
		pane = new Pane();
		pane.setStyle("-fx-background-color: darkgrey");

		pane.getChildren().addAll(circle1, C1thinVLine1, C1thickHLine1, C1thickVLine1, circle2, C2thinHLine1,
				C2thinVLine1, C2thinHLine2, C2thickVLine1, C2thickHLine1, C2thinArc1, C2thickHLine2, C2thinArc2, C2Arc3,
				group1, group2);

		// Create a scene and place it in the stage
		Scene scene = new Scene(pane, 1000, 700);
		primaryStage.setTitle("Level 1"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void checkShapeIntersection(Shape block, Shape shape) {

		collisionDetected = false;
		// If the reference shape is not itself
		if (shape != block) {

			// Check whether there is intersection area between shapes.
			Shape intersect = Shape.intersect(block, shape);
			// If there is no such area method returns a negative number
			if (intersect.getBoundsInLocal().getHeight() > -1) {
				collisionDetected = true;
			}

		}

	}
}
