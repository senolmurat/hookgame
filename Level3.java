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

public class Level3 extends Application {

	boolean collisionDetected = false;
	Pane pane;
	ArrayList<Timeline> allAnims;

	public void start(Stage primaryStage) {

		Group group1 = new Group();
		Group group2 = new Group();
		Group group3 = new Group();
		allAnims = new ArrayList<>();

		// For Circle1

		Circle circle1 = new Circle();
		circle1.setCenterX(150);
		circle1.setCenterY(425);
		circle1.setRadius(35);
		circle1.setStroke(Color.DARKRED);
		circle1.setFill(Color.BROWN);

		Line C1thinHLine1 = new Line(185, 425, 727, 425);

		Arc C1thinArc1 = new Arc(727, 420, 5, 5, 270, 90);
		C1thinArc1.setType(ArcType.OPEN);
		C1thinArc1.setFill(null);
		C1thinArc1.setStroke(Color.BLACK);

		Line C1thinVLine1 = new Line(732, 420, 732, 405);

		Line C1thickHLine1 = new Line(717, 405, 747, 405);
		C1thickHLine1.setStrokeWidth(6);
		C1thickHLine1.setStrokeLineCap(null);

		Line C1thickVLine1 = new Line(732, 405, 732, 305);
		C1thickVLine1.setStrokeWidth(6);

		Arc C1Arc2 = new Arc(732, 260, 40, 40, 90, -180);
		C1Arc2.setType(ArcType.OPEN);
		C1Arc2.setFill(null);
		C1Arc2.setStroke(Color.BLACK);
		C1Arc2.setStrokeWidth(6);

		Line C1thickVLine2 = new Line(732, 220, 732, 122);
		C1thickVLine2.setStrokeWidth(6);

		circle1.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition st = new ScaleTransition(Duration.millis(150), circle1);
				st.setByX(0.15);
				st.setByY(0.15);
				st.setCycleCount(2);
				st.setAutoReverse(true);
				st.play();

				Timeline C1AnimationVertical = new Timeline(new KeyFrame(Duration.seconds((405 - 305) / 200.0),
						new KeyValue(C1thickVLine1.endYProperty(), 405)));
				C1AnimationVertical.play();

				Timeline C1AnimationHorizontalRight = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(C1thickHLine1.startXProperty(), 692)));
				C1AnimationHorizontalRight.play();

				Timeline C1AnimationHorizontalLeft = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(C1thickHLine1.endXProperty(), 772)));
				C1AnimationHorizontalLeft.play();

				Timeline C1AnimationVerticalEnd = new Timeline(new KeyFrame(Duration.seconds((405 - 122) / 200.0),
						new KeyValue(C1thickVLine2.endYProperty(), 405)));
				C1AnimationVerticalEnd.play();

				Timeline C1AnimationVerticalStart = new Timeline(new KeyFrame(Duration.seconds((405 - 220) / 200.0),
						new KeyValue(C1thickVLine2.startYProperty(), 405)));
				C1AnimationVerticalStart.play();

				Timeline C1AnimationArc = new Timeline(new KeyFrame(Duration.seconds((445 - 260) / 200.0),
						new KeyValue(C1Arc2.centerYProperty(), 445)));
				C1AnimationArc.play();

				Timeline C1ArcDisappear = new Timeline(
						new KeyFrame(Duration.millis(500), new KeyValue(C1Arc2.lengthProperty(), 0)));
				C1ArcDisappear.setDelay(Duration.seconds((392 - 300) / 200.0));
				C1ArcDisappear.play();

				Timeline[] animations = { C1AnimationVertical, C1AnimationHorizontalRight, C1AnimationHorizontalLeft,
						C1AnimationVerticalEnd, C1AnimationVerticalStart, C1AnimationArc, C1ArcDisappear };
				Collections.addAll(allAnims, animations);

				C1AnimationVerticalEnd.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						group1.getChildren().addAll(C1thinHLine1, C1thinArc1, C1thinVLine1, C1thickHLine1,
								C1thickVLine1, C1Arc2, C1thickVLine2, circle1);

						FadeTransition ft = new FadeTransition(Duration.millis(500), group1);
						ft.setFromValue(1.0);
						ft.setToValue(0.0);
						ft.play();

					}
				});
			}
		});

		///////////////////////////////////////////////////////////////////////////////////////////////

		// For Circle2

		Circle circle2 = new Circle();
		circle2.setCenterX(265);
		circle2.setCenterY(490);
		circle2.setRadius(35);
		circle2.setStroke(Color.DARKRED);
		circle2.setFill(Color.BROWN);

		Line C2thinHLine1 = new Line(300, 490, 510, 490);

		Arc C2thinArc1 = new Arc(510, 485, 5, 5, 270, 90);
		C2thinArc1.setType(ArcType.OPEN);
		C2thinArc1.setFill(null);
		C2thinArc1.setStroke(Color.BLACK);

		Line C2thinVLine1 = new Line(515, 485, 515, 265);

		Arc C2thinArc2 = new Arc(520, 265, 5, 5, 180, -90);
		C2thinArc2.setType(ArcType.OPEN);
		C2thinArc2.setFill(null);
		C2thinArc2.setStroke(Color.BLACK);

		Line C2thinHLine2 = new Line(520, 260, 535, 260);

		Line C2thickVLine1 = new Line(535, 245, 535, 275);
		C2thickVLine1.setStrokeWidth(6);
		C2thickVLine1.setStrokeLineCap(null);

		Line C2thickHLine1 = new Line(535, 260, 737, 260);
		C2thickHLine1.setStrokeWidth(6);

		circle2.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition st = new ScaleTransition(Duration.millis(150), circle2);
				st.setByX(0.15);
				st.setByY(0.15);
				st.setCycleCount(2);
				st.setAutoReverse(true);
				st.play();

				Timeline C2AnimationHorizontal = new Timeline(new KeyFrame(Duration.seconds((737 - 535) / 200.0),
						new KeyValue(C2thickHLine1.endXProperty(), 535)));
				C2AnimationHorizontal.play();

				Timeline C2AnimationVerticalUp = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(C2thickVLine1.startYProperty(), 220)));
				C2AnimationVerticalUp.play();

				Timeline C2AnimationVerticalDown = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(C2thickVLine1.endYProperty(), 300)));
				C2AnimationVerticalDown.play();

				Timeline[] animations = { C2AnimationHorizontal, C2AnimationVerticalUp, C2AnimationVerticalDown, };
				Collections.addAll(allAnims, animations);

				C2AnimationHorizontal.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						group2.getChildren().addAll(C2thinVLine1, C2thinArc1, C2thinArc2, C2thinHLine1, C2thinHLine2,
								C2thickVLine1, C2thickHLine1, circle2);

						FadeTransition ft = new FadeTransition(Duration.millis(500), group2);
						ft.setFromValue(1.0);
						ft.setToValue(0.0);
						ft.play();

					}
				});
			}
		});

		////////////////////////////////////////////////////////////////////////////////////////////////

		// For Circle3

		Circle circle3 = new Circle();
		circle3.setCenterX(400);
		circle3.setCenterY(575);
		circle3.setRadius(35);
		circle3.setStroke(Color.DARKRED);
		circle3.setFill(Color.BROWN);

		Line C3thinVLine1 = new Line(400, 540, 400, 132);

		Arc C3thinArc1 = new Arc(405, 132, 5, 5, 180, -90);
		C3thinArc1.setType(ArcType.OPEN);
		C3thinArc1.setFill(null);
		C3thinArc1.setStroke(Color.BLACK);

		Line C3thinHLine1 = new Line(405, 127, 420, 127);

		Line C3thickVLine1 = new Line(420, 112, 420, 142);
		C3thickVLine1.setStrokeWidth(6);
		C3thickVLine1.setStrokeLineCap(null);

		Line C3thickHLine1 = new Line(420, 127, 690, 127);
		C3thickHLine1.setStrokeWidth(6);

		Arc C3Arc2 = new Arc(732, 127, 40, 40, 0, 180);
		C3Arc2.setType(ArcType.OPEN);
		C3Arc2.setFill(null);
		C3Arc2.setStroke(Color.BLACK);
		C3Arc2.setStrokeWidth(6);

		Line C3thickHLine2 = new Line(772, 127, 850, 127);
		C3thickHLine2.setStrokeWidth(6);

		circle3.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition st = new ScaleTransition(Duration.millis(150), circle3);
				st.setByX(0.15);
				st.setByY(0.15);
				st.setCycleCount(2);
				st.setAutoReverse(true);
				st.play();

				Timeline C3AnimationHorizontal = new Timeline(new KeyFrame(Duration.seconds((690 - 420) / 200.0),
						new KeyValue(C3thickHLine1.endXProperty(), 420)));
				C3AnimationHorizontal.play();

				Timeline C3AnimationVerticalUp = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(C3thickVLine1.startYProperty(), 87)));
				C3AnimationVerticalUp.play();

				Timeline C3AnimationVerticalDown = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(C3thickVLine1.endYProperty(), 167)));
				C3AnimationVerticalDown.play();

				Timeline C3AnimationHorizontalEnd = new Timeline(new KeyFrame(Duration.seconds((850 - 420) / 200.0),
						new KeyValue(C3thickHLine2.endXProperty(), 420)));
				C3AnimationHorizontalEnd.play();

				Timeline C3AnimationHorizontalStart = new Timeline(new KeyFrame(Duration.seconds((772 - 420) / 200.0),
						new KeyValue(C3thickHLine2.startXProperty(), 420)));
				C3AnimationHorizontalStart.play();

				Timeline C3AnimationArc = new Timeline(new KeyFrame(Duration.seconds((732 - 380) / 200.0),
						new KeyValue(C3Arc2.centerXProperty(), 380)));
				C3AnimationArc.play();

				Timeline C3ArcDisappear = new Timeline(
						new KeyFrame(Duration.millis(500), new KeyValue(C3Arc2.lengthProperty(), 0)));
				C3ArcDisappear.setDelay(Duration.seconds((679 - 420) / 200.0));
				C3ArcDisappear.play();

				Timeline[] animations = { C3AnimationHorizontal, C3AnimationVerticalUp, C3AnimationVerticalDown,
						C3AnimationHorizontalEnd, C3AnimationHorizontalStart, C3AnimationArc, C3ArcDisappear };
				Collections.addAll(allAnims, animations);

				C3AnimationHorizontalEnd.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						group3.getChildren().addAll(C3thinVLine1, C3thinArc1, C3thinHLine1, C3thickVLine1,
								C3thickHLine1, C3thickHLine2, C3Arc2, circle3);

						FadeTransition ft = new FadeTransition(Duration.millis(500), group3);
						ft.setFromValue(1.0);
						ft.setToValue(0.0);
						ft.play();

					}
				});
			}
		});

		Timeline CollisionCheck = new Timeline(new KeyFrame(Duration.millis(50), event -> {

			checkShapeIntersection(C1Arc2, C2thickHLine1);
			if (collisionDetected) {

				for (Timeline anim : allAnims) {
					anim.pause();
				}

				circle1.setCenterX(150);
				circle1.setCenterY(425);

				C1thinHLine1.setStartX(185);
				C1thinHLine1.setStartY(425);
				C1thinHLine1.setEndX(727);
				C1thinHLine1.setEndY(425);

				C1thinArc1.setCenterX(727);
				C1thinArc1.setCenterY(420);

				C1thinVLine1.setStartX(732);
				C1thinVLine1.setStartY(420);
				C1thinVLine1.setEndX(732);
				C1thinVLine1.setEndY(405);

				C1thickHLine1.setStartX(717);
				C1thickHLine1.setStartY(405);
				C1thickHLine1.setEndX(747);
				C1thickHLine1.setEndY(405);

				C1thickVLine1.setStartX(732);
				C1thickVLine1.setStartY(405);
				C1thickVLine1.setEndX(732);
				C1thickVLine1.setEndY(305);

				C1thickVLine2.setStartX(732);
				C1thickVLine2.setStartY(220);
				C1thickVLine2.setEndX(732);
				C1thickVLine2.setEndY(122);

				C1Arc2.setCenterX(732);
				C1Arc2.setCenterY(260);

				//////

				circle2.setCenterX(265);
				circle2.setCenterY(490);

				C2thinHLine1.setStartX(300);
				C2thinHLine1.setStartY(490);
				C2thinHLine1.setEndX(510);
				C2thinHLine1.setEndY(490);

				C2thinArc1.setCenterX(510);
				C2thinArc1.setCenterY(485);

				C2thinVLine1.setStartX(515);
				C2thinVLine1.setStartY(485);
				C2thinVLine1.setEndX(515);
				C2thinVLine1.setEndY(265);

				C2thinArc2.setCenterX(520);
				C2thinArc2.setCenterY(265);

				C2thinHLine2.setStartX(520);
				C2thinHLine2.setStartY(260);
				C2thinHLine2.setEndX(535);
				C2thinHLine2.setEndY(260);

				C2thickVLine1.setStartX(535);
				C2thickVLine1.setStartY(245);
				C2thickVLine1.setEndX(535);
				C2thickVLine1.setEndY(275);

				C2thickHLine1.setStartX(535);
				C2thickHLine1.setStartY(260);
				C2thickHLine1.setEndX(737);
				C2thickHLine1.setEndY(260);

				////

				circle3.setCenterX(400);
				circle3.setCenterY(575);

				C3thinVLine1.setStartX(400);
				C3thinVLine1.setStartY(540);
				C3thinVLine1.setEndX(400);
				C3thinVLine1.setEndY(132);

				C3thinArc1.setCenterX(405);
				C3thinArc1.setCenterY(132);

				C3thinHLine1.setStartX(405);
				C3thinHLine1.setStartY(127);
				C3thinHLine1.setEndX(420);
				C3thinHLine1.setEndY(127);

				C3thickVLine1.setStartX(420);
				C3thickVLine1.setStartY(112);
				C3thickVLine1.setEndX(420);
				C3thickVLine1.setEndY(142);

				C3thickHLine1.setStartX(420);
				C3thickHLine1.setStartY(127);
				C3thickHLine1.setEndX(690);
				C3thickHLine1.setEndY(127);

				C3Arc2.setCenterX(732);
				C3Arc2.setCenterY(127);

				C3thickHLine2.setStartX(772);
				C3thickHLine2.setStartY(127);
				C3thickHLine2.setEndX(850);
				C3thickHLine2.setEndY(127);

				pane.getChildren().clear();
				pane.getChildren().addAll(circle1, C1thinHLine1, C1thinArc1, C1thinVLine1, C1thickHLine1, C1thickVLine1,
						C1Arc2, C1thickVLine2, circle2, C2thinHLine1, C2thinArc1, C2thinVLine1, C2thinArc2,
						C2thinHLine2, C2thickVLine1, C2thickHLine1, circle3, C3thinVLine1, C3thinArc1, C3thinHLine1,
						C3thickVLine1, C3thickHLine1, C3Arc2, C3thickHLine2);
			}

			checkShapeIntersection(C3Arc2, C1thickVLine2);
			if (collisionDetected) {

				for (Timeline anim : allAnims) {
					anim.pause();
				}

				circle1.setCenterX(150);
				circle1.setCenterY(425);

				C1thinHLine1.setStartX(185);
				C1thinHLine1.setStartY(425);
				C1thinHLine1.setEndX(727);
				C1thinHLine1.setEndY(425);

				C1thinArc1.setCenterX(727);
				C1thinArc1.setCenterY(420);

				C1thinVLine1.setStartX(732);
				C1thinVLine1.setStartY(420);
				C1thinVLine1.setEndX(732);
				C1thinVLine1.setEndY(405);

				C1thickHLine1.setStartX(717);
				C1thickHLine1.setStartY(405);
				C1thickHLine1.setEndX(747);
				C1thickHLine1.setEndY(405);

				C1thickVLine1.setStartX(732);
				C1thickVLine1.setStartY(405);
				C1thickVLine1.setEndX(732);
				C1thickVLine1.setEndY(305);

				C1thickVLine2.setStartX(732);
				C1thickVLine2.setStartY(220);
				C1thickVLine2.setEndX(732);
				C1thickVLine2.setEndY(122);

				C1Arc2.setCenterX(732);
				C1Arc2.setCenterY(260);

				//////

				circle2.setCenterX(265);
				circle2.setCenterY(490);

				C2thinHLine1.setStartX(300);
				C2thinHLine1.setStartY(490);
				C2thinHLine1.setEndX(510);
				C2thinHLine1.setEndY(490);

				C2thinArc1.setCenterX(510);
				C2thinArc1.setCenterY(485);

				C2thinVLine1.setStartX(515);
				C2thinVLine1.setStartY(485);
				C2thinVLine1.setEndX(515);
				C2thinVLine1.setEndY(265);

				C2thinArc2.setCenterX(520);
				C2thinArc2.setCenterY(265);

				C2thinHLine2.setStartX(520);
				C2thinHLine2.setStartY(260);
				C2thinHLine2.setEndX(535);
				C2thinHLine2.setEndY(260);

				C2thickVLine1.setStartX(535);
				C2thickVLine1.setStartY(245);
				C2thickVLine1.setEndX(535);
				C2thickVLine1.setEndY(275);

				C2thickHLine1.setStartX(535);
				C2thickHLine1.setStartY(260);
				C2thickHLine1.setEndX(737);
				C2thickHLine1.setEndY(260);

				////

				circle3.setCenterX(400);
				circle3.setCenterY(575);

				C3thinVLine1.setStartX(400);
				C3thinVLine1.setStartY(540);
				C3thinVLine1.setEndX(400);
				C3thinVLine1.setEndY(132);

				C3thinArc1.setCenterX(405);
				C3thinArc1.setCenterY(132);

				C3thinHLine1.setStartX(405);
				C3thinHLine1.setStartY(127);
				C3thinHLine1.setEndX(420);
				C3thinHLine1.setEndY(127);

				C3thickVLine1.setStartX(420);
				C3thickVLine1.setStartY(112);
				C3thickVLine1.setEndX(420);
				C3thickVLine1.setEndY(142);

				C3thickHLine1.setStartX(420);
				C3thickHLine1.setStartY(127);
				C3thickHLine1.setEndX(690);
				C3thickHLine1.setEndY(127);

				C3Arc2.setCenterX(732);
				C3Arc2.setCenterY(127);

				C3thickHLine2.setStartX(772);
				C3thickHLine2.setStartY(127);
				C3thickHLine2.setEndX(850);
				C3thickHLine2.setEndY(127);

				pane.getChildren().clear();
				pane.getChildren().addAll(circle1, C1thinHLine1, C1thinArc1, C1thinVLine1, C1thickHLine1, C1thickVLine1,
						C1Arc2, C1thickVLine2, circle2, C2thinHLine1, C2thinArc1, C2thinVLine1, C2thinArc2,
						C2thinHLine2, C2thickVLine1, C2thickHLine1, circle3, C3thinVLine1, C3thinArc1, C3thinHLine1,
						C3thickVLine1, C3thickHLine1, C3Arc2, C3thickHLine2, group1, group2, group3);
			}

		}));
		CollisionCheck.setCycleCount(Timeline.INDEFINITE);
		CollisionCheck.play();

		//////////////////////
		pane = new Pane();
		pane.setStyle("-fx-background-color: darkgrey");
		pane.getChildren().addAll(circle1, circle2, circle3, C2thinHLine1, C2thinArc1, C2thinVLine1, C2thinArc2,
				C2thinHLine2, C2thickVLine1, C2thickHLine1, C1thinHLine1, C1thinArc1, C1thinVLine1, C1Arc2,
				C1thickHLine1, C1thickVLine1, C1thickVLine2, C3thinVLine1, C3thinArc1, C3Arc2, C3thinHLine1,
				C3thickVLine1, C3thickHLine1, C3thickHLine2, group1, group2, group3);

		Scene scene = new Scene(pane, 1000, 700);

		primaryStage.setTitle("Level 3"); // Set the stage title
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
