import javafx.stage.Stage;
import javafx.util.Duration;

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

public class Level4 extends Application {

	ArrayList<Timeline> allAnims;
	boolean collisionDetected = false;
	Pane pane;

	public void start(Stage primaryStage) {

		Group group1 = new Group();
		Group group2 = new Group();
		allAnims = new ArrayList<>();

		Arc DiscArc1 = new Arc(350, 400, 40, 40, 0, 360);
		DiscArc1.setType(ArcType.OPEN);
		DiscArc1.setFill(null);
		DiscArc1.setStroke(Color.BLACK);
		DiscArc1.setStrokeWidth(4);

		Line DiscLine1 = new Line(350, 440, 350, 360);
		DiscLine1.setStrokeWidth(3);

		Circle connectorCircle1 = new Circle();
		connectorCircle1.setCenterX(350);
		connectorCircle1.setCenterY(400);
		connectorCircle1.setRadius(40);
		connectorCircle1.setOpacity(0.0);

		connectorCircle1.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				int i = (int) (DiscLine1.getRotate() / 90);

				Timeline DiscLine1Animation = new Timeline(
						new KeyFrame(Duration.millis(500), new KeyValue(DiscLine1.rotateProperty(), (i + 1) * 90)));
				DiscLine1Animation.play();

			}

		});

		////////////////////////////////////////////////////////////////////////////////

		// For Circle1

		Circle circle1 = new Circle();
		circle1.setCenterX(175);
		circle1.setCenterY(400);
		circle1.setRadius(35);
		circle1.setStroke(Color.DARKRED);
		circle1.setFill(Color.BROWN);

		Line C1thinHLine1 = new Line(210, 400, 310, 400);

		Line C1thinHLine2 = new Line(390, 400, 745, 400);

		Arc C1thinArc1 = new Arc(745, 395, 5, 5, 270, 90);
		C1thinArc1.setType(ArcType.OPEN);
		C1thinArc1.setFill(null);
		C1thinArc1.setStroke(Color.BLACK);

		Line C1thinVLine1 = new Line(750, 395, 750, 380);

		Line C1thickHLine1 = new Line(735, 380, 765, 380);
		C1thickHLine1.setStrokeWidth(6);
		C1thickHLine1.setStrokeLineCap(null);

		Line C1thickVLine1 = new Line(750, 380, 750, 170);
		C1thickVLine1.setStrokeWidth(6);

		circle1.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition st = new ScaleTransition(Duration.millis(150), circle1);
				st.setByX(0.15);
				st.setByY(0.15);
				st.setCycleCount(2);
				st.setAutoReverse(true);

				if (DiscLine1.getRotate() % 180 == 90) {
					st.play();

					Timeline C1AnimationVertical = new Timeline(new KeyFrame(Duration.seconds((380 - 170) / 200.0),
							new KeyValue(C1thickVLine1.endYProperty(), 380)));
					C1AnimationVertical.play();

					Timeline C1AnimationHorizontalRight = new Timeline(
							new KeyFrame(Duration.millis(150), new KeyValue(C1thickHLine1.startXProperty(), 710)));
					C1AnimationHorizontalRight.play();

					Timeline C1AnimationHorizontalLeft = new Timeline(
							new KeyFrame(Duration.millis(150), new KeyValue(C1thickHLine1.endXProperty(), 790)));
					C1AnimationHorizontalLeft.play();

					Timeline[] animations = { C1AnimationVertical, C1AnimationHorizontalRight,
							C1AnimationHorizontalLeft };
					Collections.addAll(allAnims, animations);

					C1AnimationVertical.setOnFinished(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {

							group1.getChildren().addAll(C1thinVLine1, C1thinArc1, C1thinHLine1, C1thinHLine2,
									C1thickVLine1, C1thickHLine1, circle1);

							FadeTransition ft = new FadeTransition(Duration.millis(500), group1);
							ft.setFromValue(1.0);
							ft.setToValue(0.0);
							ft.play();

						}
					});
				}

			}
		});

		////////////////////////////////////////////////////////////////////////////////

		// For Circle2

		Circle circle2 = new Circle();
		circle2.setCenterX(350);
		circle2.setCenterY(575);
		circle2.setRadius(35);
		circle2.setStroke(Color.DARKRED);
		circle2.setFill(Color.BROWN);

		Line C2thinVLine1 = new Line(350, 540, 350, 440);

		Line C2thinVLine2 = new Line(350, 360, 350, 180);

		Arc C2thinArc1 = new Arc(355, 180, 5, 5, 180, -90);
		C2thinArc1.setType(ArcType.OPEN);
		C2thinArc1.setFill(null);
		C2thinArc1.setStroke(Color.BLACK);

		Line C2thinHLine1 = new Line(355, 175, 370, 175);

		Line C2thickVLine1 = new Line(370, 160, 370, 190);
		C2thickVLine1.setStrokeWidth(6);
		C2thickVLine1.setStrokeLineCap(null);

		Line C2thickHLine1 = new Line(370, 175, 707, 175);
		C2thickHLine1.setStrokeWidth(6);

		Arc C2Arc2 = new Arc(750, 175, 40, 40, 0, 180);
		C2Arc2.setType(ArcType.OPEN);
		C2Arc2.setFill(null);
		C2Arc2.setStroke(Color.BLACK);
		C2Arc2.setStrokeWidth(6);

		Line C2thickHLine2 = new Line(792, 175, 820, 175);
		C2thickHLine2.setStrokeWidth(6);

		circle2.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition st = new ScaleTransition(Duration.millis(150), circle2);
				st.setByX(0.15);
				st.setByY(0.15);
				st.setCycleCount(2);
				st.setAutoReverse(true);

				if (DiscLine1.getRotate() % 180 == 0) {
					st.play();

					Timeline C2AnimationHorizontal = new Timeline(new KeyFrame(Duration.seconds((707 - 370) / 200.0),
							new KeyValue(C2thickHLine1.endXProperty(), 370)));
					C2AnimationHorizontal.play();

					Timeline C2AnimationVerticalUp = new Timeline(
							new KeyFrame(Duration.millis(150), new KeyValue(C2thickVLine1.startYProperty(), 135)));
					C2AnimationVerticalUp.play();

					Timeline C2AnimationVerticalDown = new Timeline(
							new KeyFrame(Duration.millis(150), new KeyValue(C2thickVLine1.endYProperty(), 215)));
					C2AnimationVerticalDown.play();

					Timeline C2AnimationHorizontalEnd = new Timeline(new KeyFrame(Duration.seconds((820 - 370) / 200.0),
							new KeyValue(C2thickHLine2.endXProperty(), 370)));
					C2AnimationHorizontalEnd.play();

					Timeline C2AnimationHorizontalStart = new Timeline(new KeyFrame(
							Duration.seconds((792 - 370) / 200.0), new KeyValue(C2thickHLine2.startXProperty(), 370)));
					C2AnimationHorizontalStart.play();

					Timeline C2AnimationArc = new Timeline(new KeyFrame(Duration.seconds((750 - 330) / 200.0),
							new KeyValue(C2Arc2.centerXProperty(), 330)));
					C2AnimationArc.play();

					Timeline C2ArcDisappear = new Timeline(
							new KeyFrame(Duration.millis(500), new KeyValue(C2Arc2.lengthProperty(), 0)));
					C2ArcDisappear.setDelay(Duration.seconds((695 - 370) / 200.0));
					C2ArcDisappear.play();

					Timeline[] animations = { C2AnimationHorizontal, C2AnimationVerticalUp, C2AnimationVerticalDown,
							C2AnimationHorizontalEnd, C2AnimationHorizontalStart, C2AnimationArc, C2ArcDisappear };
					Collections.addAll(allAnims, animations);

					C2AnimationHorizontalEnd.setOnFinished(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {

							group2.getChildren().addAll(C2thinVLine1, C2thinVLine2, C2thinArc1, C2thinHLine1,
									C2thickVLine1, C2thickHLine1, C2thickHLine2, C2Arc2, circle2, DiscArc1, DiscLine1);

							FadeTransition ft = new FadeTransition(Duration.millis(500), group2);
							ft.setFromValue(1.0);
							ft.setToValue(0.0);
							ft.play();

						}
					});
				}

			}
		});

		Timeline C2CollisionCheck = new Timeline(new KeyFrame(Duration.millis(50), event -> {

			checkShapeIntersection(C2Arc2, C1thickVLine1);
			if (collisionDetected) {

				for (Timeline anim : allAnims) {
					anim.pause();
				}

				pane.getChildren().clear();

				circle2.setCenterX(350);
				circle2.setCenterY(575);

				C2thinVLine1.setStartX(350);
				C2thinVLine1.setStartY(540);
				C2thinVLine1.setEndX(350);
				C2thinVLine1.setEndY(440);

				C2thinVLine2.setStartX(350);
				C2thinVLine2.setStartY(360);
				C2thinVLine2.setEndX(350);
				C2thinVLine2.setEndY(180);

				C2thinArc1.setCenterX(355);
				C2thinArc1.setCenterY(180);

				C2thinHLine1.setStartX(355);
				C2thinHLine1.setStartY(175);
				C2thinHLine1.setEndX(370);
				C2thinHLine1.setEndY(175);

				C2thickVLine1.setStartX(370);
				C2thickVLine1.setStartY(160);
				C2thickVLine1.setEndX(370);
				C2thickVLine1.setEndY(190);

				C2thickHLine1.setStartX(370);
				C2thickHLine1.setStartY(175);
				C2thickHLine1.setEndX(707);
				C2thickHLine1.setEndY(175);

				C2Arc2.setCenterX(750);
				C2Arc2.setCenterY(175);

				C2thickHLine2.setStartX(792);
				C2thickHLine2.setStartY(175);
				C2thickHLine2.setEndX(820);
				C2thickHLine2.setEndY(175);
				pane.getChildren().addAll(circle1, C1thinHLine1, C1thinArc1, C1thinHLine2, C1thinVLine1, C1thickHLine1,
						C1thickVLine1, DiscArc1, DiscLine1, connectorCircle1, circle2, C2thinVLine1, C2thinVLine2,
						C2thinArc1, C2thinHLine1, C2thickVLine1, C2thickHLine1, C2Arc2, C2thickHLine2,group1, group2);
			}

		}));
		C2CollisionCheck.setCycleCount(Timeline.INDEFINITE);
		C2CollisionCheck.play();

		//////////////////////
		pane = new Pane();

		pane.setStyle("-fx-background-color: darkgrey");
		pane.getChildren().addAll(circle1, C1thinHLine1, C1thinArc1, circle2, C2thinVLine1, C1thinHLine2, C1thinVLine1,
				C1thickHLine1, C2thinVLine2, C2thinArc1, C2thinHLine1, C2thickVLine1, C2Arc2, C2thickHLine1,
				C1thickVLine1, C2thickHLine2, DiscArc1, DiscLine1, connectorCircle1, group1, group2);

		Scene scene = new Scene(pane, 1000, 700);

		primaryStage.setTitle("Level 4"); // Set the stage title
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
