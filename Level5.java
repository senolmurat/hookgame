import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;
import java.util.Collections;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Level5 extends Application {
	// Creating timer to schedule collision method
	// Creating ArrayList to use it later
	ArrayList<Timeline> allAnims;
	boolean collisionDetected = false;
	Pane pane;

	@Override
	public void start(Stage primaryStage) {

		Group group1 = new Group();
		Group group2 = new Group();
		Group group3 = new Group();
		Group group4 = new Group();

		allAnims = new ArrayList<>();
		///////////////////////////////////

		Arc DiscArc1 = new Arc(350, 465, 40, 40, 0, 360);
		DiscArc1.setType(ArcType.OPEN);
		DiscArc1.setFill(null);
		DiscArc1.setStroke(Color.BLACK);
		DiscArc1.setStrokeWidth(4);

		Line DiscLine1 = new Line(310, 465, 390, 465);
		DiscLine1.setStrokeWidth(3);

		Circle connectorCircle1 = new Circle();
		connectorCircle1.setCenterX(350);
		connectorCircle1.setCenterY(465);
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

		Arc DiscArc2 = new Arc(350, 350, 40, 40, 0, 360);
		DiscArc2.setType(ArcType.OPEN);
		DiscArc2.setFill(null);
		DiscArc2.setStroke(Color.BLACK);
		DiscArc2.setStrokeWidth(4);

		Line DiscLine2 = new Line(350, 310, 350, 390);
		DiscLine2.setStrokeWidth(3);

		Circle connectorCircle2 = new Circle();
		connectorCircle2.setCenterX(350);
		connectorCircle2.setCenterY(350);
		connectorCircle2.setRadius(40);
		connectorCircle2.setOpacity(0.0);

		connectorCircle2.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				int i = (int) (DiscLine2.getRotate() / 90);

				Timeline DiscLine2Animation = new Timeline(
						new KeyFrame(Duration.millis(500), new KeyValue(DiscLine2.rotateProperty(), (i + 1) * 90)));
				DiscLine2Animation.play();
			}

		});

		///////////////////////////////////

		// For Circle1

		Circle circle1 = new Circle();
		circle1.setCenterX(700);
		circle1.setCenterY(620);
		circle1.setRadius(35);
		circle1.setStroke(Color.DARKRED);
		circle1.setFill(Color.BROWN);

		// Fade Animation
		/*
		 * FadeTransition ft = new FadeTransition(Duration.millis(400),
		 * circle1); ft.setFromValue(1.0); ft.setToValue(0.1); ft.play();
		 */

		Line C1thinVLine1 = new Line(700, 620, 700, 180);

		Arc C1thinArc1 = new Arc(695, 180, 5, 5, 0, 90);
		C1thinArc1.setType(ArcType.OPEN);
		C1thinArc1.setFill(null);
		C1thinArc1.setStroke(Color.BLACK);

		Line C1thinHLine1 = new Line(695, 175, 685, 175);

		Line C1thickVLine1 = new Line(685, 160, 685, 190);
		C1thickVLine1.setStrokeWidth(6);
		C1thickVLine1.setStrokeLineCap(null);

		Line C1thickHLine1 = new Line(685, 175, 590, 175);
		C1thickHLine1.setStrokeWidth(6);

		// Animations for Group1

		circle1.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				// To enlarge and shrink circle when pressed
				ScaleTransition st = new ScaleTransition(Duration.millis(150), circle1);
				st.setByX(0.15);
				st.setByY(0.15);
				st.setCycleCount(2);
				st.setAutoReverse(true);
				st.play();

				// To shorten line over time
				Timeline C1AnimationHorizontal = new Timeline(new KeyFrame(Duration.seconds((685 - 590) / 200.0),
						new KeyValue(C1thickHLine1.endXProperty(), 685)));
				C1AnimationHorizontal.play();

				Timeline C1AnimationVerticalUp = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(C1thickVLine1.startYProperty(), 135)));
				C1AnimationVerticalUp.play();

				Timeline C1AnimationVerticalDown = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(C1thickVLine1.endYProperty(), 215)));
				C1AnimationVerticalDown.play();

				// Registering animations to allAnims ArrayList to use it in
				// ChackShapeInersection method
				Timeline[] animations = { C1AnimationHorizontal, C1AnimationVerticalUp, C1AnimationVerticalDown };
				Collections.addAll(allAnims, animations);

				C1AnimationHorizontal.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						group1.getChildren().addAll(C1thinVLine1, C1thinArc1, C1thinHLine1, C1thickVLine1,
								C1thickHLine1, circle1);

						FadeTransition ft = new FadeTransition(Duration.millis(500), group1);
						ft.setFromValue(1.0);
						ft.setToValue(0.0);
						ft.play();

					}
				});

			}
		});

		///////////////////////////////////

		// For Circle2

		Circle circle2 = new Circle();
		circle2.setCenterX(350);
		circle2.setCenterY(620);
		circle2.setRadius(35);
		circle2.setStroke(Color.DARKRED);
		circle2.setFill(Color.BROWN);

		Line C2thinVLine1 = new Line(350, 620, 350, 505);
		Line C2thinVLine2 = new Line(350, 425, 350, 390);
		Line C2thinVLine3 = new Line(350, 310, 350, 110);

		Arc C2thinArc1 = new Arc(355, 110, 5, 5, 180, -90);
		C2thinArc1.setType(ArcType.OPEN);
		C2thinArc1.setFill(null);
		C2thinArc1.setStroke(Color.BLACK);

		Line C2thinHLine2 = new Line(355, 105, 365, 105);

		Line C2thickVLine1 = new Line(365, 90, 365, 120);
		C2thickVLine1.setStrokeWidth(6);
		C2thickVLine1.setStrokeLineCap(null);

		Line C2thickHLine1 = new Line(365, 105, 550, 105);
		C2thickHLine1.setStrokeWidth(6);

		Arc C2Arc1 = new Arc(590, 105, 40, 40, 0, 180);
		C2Arc1.setType(ArcType.OPEN);
		C2Arc1.setFill(null);
		C2Arc1.setStroke(Color.BLACK);
		C2Arc1.setStrokeWidth(6);

		Line C2thickHLine2 = new Line(630, 105, 750, 105);
		C2thickHLine2.setStrokeWidth(6);

		// Animations for Group2

		circle2.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition st = new ScaleTransition(Duration.millis(150), circle2);
				st.setByX(0.15);
				st.setByY(0.15);
				st.setCycleCount(2);
				st.setAutoReverse(true);

				if (DiscLine1.getRotate() % 180 == 90) {
					if (DiscLine2.getRotate() % 180 == 0) {
						st.play();

						Timeline C2AnimationHorizontal1 = new Timeline(
								new KeyFrame(Duration.seconds((550 - 365) / 200.0),
										new KeyValue(C2thickHLine1.endXProperty(), 365)));
						C2AnimationHorizontal1.play();

						Timeline C2AnimationHorizontal2End = new Timeline(
								new KeyFrame(Duration.seconds((750 - 365) / 200.0),
										new KeyValue(C2thickHLine2.endXProperty(), 365)));
						C2AnimationHorizontal2End.play();

						Timeline C2AnimationHorizontal2Start = new Timeline(
								new KeyFrame(Duration.seconds((630 - 365) / 200.0),
										new KeyValue(C2thickHLine2.startXProperty(), 365)));
						C2AnimationHorizontal2Start.play();

						Timeline C2AnimationVerticalUp = new Timeline(
								new KeyFrame(Duration.millis(150), new KeyValue(C2thickVLine1.startYProperty(), 65)));
						C2AnimationVerticalUp.play();

						Timeline C2AnimationVerticalDown = new Timeline(
								new KeyFrame(Duration.millis(150), new KeyValue(C2thickVLine1.endYProperty(), 145)));
						C2AnimationVerticalDown.play();

						Timeline C2AnimationArc = new Timeline(new KeyFrame(Duration.seconds((590 - 325) / 200.0),
								new KeyValue(C2Arc1.centerXProperty(), 325)));
						C2AnimationArc.play();

						Timeline C2ArcDisappear = new Timeline(
								new KeyFrame(Duration.millis(500), new KeyValue(C2Arc1.lengthProperty(), 0)));
						C2ArcDisappear.setDelay(Duration.seconds((535 - 365) / 200.0));
						C2ArcDisappear.play();

						Timeline[] animations = { C2AnimationHorizontal1, C2AnimationHorizontal2End,
								C2AnimationHorizontal2Start, C2AnimationVerticalUp, C2AnimationVerticalDown,
								C2AnimationArc, C2ArcDisappear };
						Collections.addAll(allAnims, animations);

						C2AnimationHorizontal2End.setOnFinished(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {

								group2.getChildren().addAll(C2thinVLine1, C2thinVLine2, C2thinVLine3, C2thinArc1,
										C2thinHLine2, C2thickVLine1, C2thickHLine1, C2Arc1, C2thickHLine2, circle2,
										DiscArc2, DiscLine2);

								FadeTransition ft = new FadeTransition(Duration.millis(500), group2);
								ft.setFromValue(1.0);
								ft.setToValue(0.0);
								ft.play();

							}
						});

					}
				}

			}
		});

		///////////////////////////////////

		// For Circle3

		Circle circle3 = new Circle();
		circle3.setCenterX(100);
		circle3.setCenterY(465);
		circle3.setRadius(35);
		circle3.setStroke(Color.DARKRED);
		circle3.setFill(Color.BROWN);

		Line C3thinHLine1 = new Line(100, 465, 310, 465);
		Line C3thinHLine2 = new Line(390, 465, 740, 465);

		Arc C3thinArc1 = new Arc(740, 460, 5, 5, -90, 90);
		C3thinArc1.setType(ArcType.OPEN);
		C3thinArc1.setFill(null);
		C3thinArc1.setStroke(Color.BLACK);

		Line C3thinVLine1 = new Line(745, 460, 745, 450);

		Line C3thickHLine1 = new Line(730, 450, 760, 450);
		C3thickHLine1.setStrokeWidth(6);
		C3thickHLine1.setStrokeLineCap(null);

		Line C3thickVLine1 = new Line(745, 450, 745, 145);
		C3thickVLine1.setStrokeWidth(6);

		Arc C3Arc1 = new Arc(745, 105, 40, 40, 90, -180);
		C3Arc1.setType(ArcType.OPEN);
		C3Arc1.setFill(null);
		C3Arc1.setStroke(Color.BLACK);
		C3Arc1.setStrokeWidth(6);

		Line C3thickVLine2 = new Line(745, 65, 745, 35);
		C3thickVLine2.setStrokeWidth(6);

		// Animations for Group3

		circle3.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition st = new ScaleTransition(Duration.millis(150), circle3);
				st.setByX(0.15);
				st.setByY(0.15);
				st.setCycleCount(2);
				st.setAutoReverse(true);

				if (DiscLine1.getRotate() % 180 == 0) {
					st.play();

					Timeline C3AnimationVertical1 = new Timeline(new KeyFrame(Duration.seconds((450 - 145) / 200.0),
							new KeyValue(C3thickVLine1.endYProperty(), 450)));
					C3AnimationVertical1.play();

					Timeline C3AnimationVertical2End = new Timeline(new KeyFrame(Duration.seconds((450 - 35) / 200.0),
							new KeyValue(C3thickVLine2.endYProperty(), 450)));
					C3AnimationVertical2End.play();

					Timeline C3AnimationVertical2Start = new Timeline(new KeyFrame(Duration.seconds((450 - 65) / 200.0),
							new KeyValue(C3thickVLine2.startYProperty(), 450)));
					C3AnimationVertical2Start.play();

					Timeline C3AnimationHorizontalLeft = new Timeline(
							new KeyFrame(Duration.millis(150), new KeyValue(C3thickHLine1.startXProperty(), 705)));
					C3AnimationHorizontalLeft.play();

					Timeline C3AnimationHorizontalRight = new Timeline(
							new KeyFrame(Duration.millis(150), new KeyValue(C3thickHLine1.endXProperty(), 785)));
					C3AnimationHorizontalRight.play();

					Timeline C3AnimationArc = new Timeline(new KeyFrame(Duration.seconds((490 - 105) / 200.0),
							new KeyValue(C3Arc1.centerYProperty(), 490)));
					C3AnimationArc.play();

					Timeline C3ArcDisappear = new Timeline(
							new KeyFrame(Duration.millis(500), new KeyValue(C3Arc1.lengthProperty(), 0)));
					C3ArcDisappear.setDelay(Duration.seconds((435 - 145) / 200.0));
					C3ArcDisappear.play();

					Timeline[] animations = { C3AnimationVertical1, C3AnimationVertical2End, C3AnimationVertical2Start,
							C3AnimationHorizontalLeft, C3AnimationHorizontalRight, C3AnimationArc, C3ArcDisappear };
					Collections.addAll(allAnims, animations);

					C3AnimationVertical2End.setOnFinished(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {

							group3.getChildren().addAll(C3thinHLine1, C3thinHLine2, C3thinArc1, C3thinVLine1,
									C3thickHLine1, C3thickVLine1, C3Arc1, C3thickVLine2, circle3, DiscArc1, DiscLine1);

							FadeTransition ft = new FadeTransition(Duration.millis(500), group3);
							ft.setFromValue(1.0);
							ft.setToValue(0.0);
							ft.play();

						}
					});

				}

			}
		});

		///////////////////////////////////

		// For Circle4

		Circle circle4 = new Circle();
		circle4.setCenterX(100);
		circle4.setCenterY(350);
		circle4.setRadius(35);
		circle4.setStroke(Color.DARKRED);
		circle4.setFill(Color.BROWN);

		Line C4thinHLine1 = new Line(135, 350, 310, 350);
		Line C4thinHLine2 = new Line(390, 350, 585, 350);

		Arc C4thinArc1 = new Arc(585, 345, 5, 5, -90, 90);
		C4thinArc1.setType(ArcType.OPEN);
		C4thinArc1.setFill(null);
		C4thinArc1.setStroke(Color.BLACK);

		Line C4thinVLine1 = new Line(590, 345, 590, 335);

		Line C4thickHLine1 = new Line(575, 335, 605, 335);
		C4thickHLine1.setStrokeWidth(6);
		C4thickHLine1.setStrokeLineCap(null);

		Line C4thickVLine1 = new Line(590, 335, 590, 215);
		C4thickVLine1.setStrokeWidth(6);

		Arc C4Arc1 = new Arc(590, 175, 40, 40, 90, 180);
		C4Arc1.setType(ArcType.OPEN);
		C4Arc1.setFill(null);
		C4Arc1.setStroke(Color.BLACK);
		C4Arc1.setStrokeWidth(6);

		Line C4thickVLine2 = new Line(590, 135, 590, 105);
		C4thickVLine2.setStrokeWidth(6);

		// Animations for Group4

		circle4.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition st = new ScaleTransition(Duration.millis(150), circle4);
				st.setByX(0.15);
				st.setByY(0.15);
				st.setCycleCount(2);
				st.setAutoReverse(true);

				if (DiscLine2.getRotate() % 180 == 90) {
					st.play();

					Timeline C4AnimationVertical1 = new Timeline(new KeyFrame(Duration.seconds((335 - 215) / 200.0),
							new KeyValue(C4thickVLine1.endYProperty(), 335)));
					C4AnimationVertical1.play();

					Timeline C4AnimationVertical2End = new Timeline(new KeyFrame(Duration.seconds((335 - 105) / 200.0),
							new KeyValue(C4thickVLine2.endYProperty(), 335)));
					C4AnimationVertical2End.play();

					Timeline C4AnimationVertical2Start = new Timeline(new KeyFrame(
							Duration.seconds((335 - 135) / 200.0), new KeyValue(C4thickVLine2.startYProperty(), 335)));
					C4AnimationVertical2Start.play();

					Timeline C4AnimationHorizontalLeft = new Timeline(
							new KeyFrame(Duration.millis(150), new KeyValue(C4thickHLine1.startXProperty(), 550)));
					C4AnimationHorizontalLeft.play();

					Timeline C4AnimationHorizontalRight = new Timeline(
							new KeyFrame(Duration.millis(150), new KeyValue(C4thickHLine1.endXProperty(), 630)));
					C4AnimationHorizontalRight.play();

					Timeline C4AnimationArc = new Timeline(new KeyFrame(Duration.seconds((375 - 175) / 200.0),
							new KeyValue(C4Arc1.centerYProperty(), 375)));
					C4AnimationArc.play();

					Timeline C4ArcDisappear = new Timeline(
							new KeyFrame(Duration.millis(500), new KeyValue(C4Arc1.lengthProperty(), 0)));
					C4ArcDisappear.setDelay(Duration.seconds((280 - 175) / 200.0));
					C4ArcDisappear.play();

					Timeline[] animations = { C4AnimationVertical1, C4AnimationVertical2End, C4AnimationVertical2Start,
							C4AnimationHorizontalLeft, C4AnimationHorizontalRight, C4AnimationArc, C4ArcDisappear };
					Collections.addAll(allAnims, animations);

					C4AnimationVertical2End.setOnFinished(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {

							group4.getChildren().addAll(C4thinHLine1, C4thinHLine2, C4thinArc1, C4thinVLine1,
									C4thickHLine1, C4thickVLine1, C4Arc1, C4thickVLine2, circle4);

							FadeTransition ft = new FadeTransition(Duration.millis(500), group4);
							ft.setFromValue(1.0);
							ft.setToValue(0.0);
							ft.play();

						}
					});

				}

			}
		});

		Timeline C2CollisionCheck = new Timeline(new KeyFrame(Duration.millis(50), event -> {

			checkShapeIntersection(C2Arc1, C4thickVLine2);
			if (collisionDetected) {

				for (Timeline anim : allAnims) {
					anim.pause();
				}

				pane.getChildren().clear();

				C1thickHLine1.setStartX(685);
				C1thickHLine1.setStartY(175);
				C1thickHLine1.setEndX(590);
				C1thickHLine1.setEndY(175);

				circle2.setCenterX(350);
				circle2.setCenterY(620);

				C2thinVLine1.setStartX(350);
				C2thinVLine1.setStartY(585);
				C2thinVLine1.setEndX(350);
				C2thinVLine1.setEndY(505);

				C2thinVLine2.setStartX(350);
				C2thinVLine2.setStartY(425);
				C2thinVLine2.setEndX(350);
				C2thinVLine2.setEndY(390);

				C2thinVLine3.setStartX(350);
				C2thinVLine3.setStartY(310);
				C2thinVLine3.setEndX(350);
				C2thinVLine3.setEndY(110);

				C2thinArc1.setCenterX(355);
				C2thinArc1.setCenterY(110);

				C2thinHLine2.setStartX(355);
				C2thinHLine2.setStartY(105);
				C2thinHLine2.setEndX(365);
				C2thinHLine2.setEndY(105);

				C2thickVLine1.setStartX(365);
				C2thickVLine1.setStartY(90);
				C2thickVLine1.setEndX(365);
				C2thickVLine1.setEndY(120);

				C2thickHLine1.setStartX(365);
				C2thickHLine1.setStartY(105);
				C2thickHLine1.setEndX(550);
				C2thickHLine1.setEndY(105);

				C2Arc1.setCenterX(590);
				C2Arc1.setCenterY(105);

				C2thickHLine2.setStartX(630);
				C2thickHLine2.setStartY(105);
				C2thickHLine2.setEndX(750);
				C2thickHLine2.setEndY(105);

				/////
				
				DiscLine1.setStartX(310);
				DiscLine1.setStartY(365);
				DiscLine1.setEndX(390);
				DiscLine1.setEndY(365);
				
				DiscLine2.setStartX(350);
				DiscLine2.setStartY(310);
				DiscLine2.setEndX(350);
				DiscLine2.setEndY(390);

				circle3.setCenterX(100);
				circle3.setCenterY(465);

				C3thinHLine1.setStartX(135);
				C3thinHLine1.setStartY(465);
				C3thinHLine1.setEndX(310);
				C3thinHLine1.setEndY(465);

				C3thinHLine2.setStartX(390);
				C3thinHLine2.setStartY(465);
				C3thinHLine2.setEndX(740);
				C3thinHLine2.setEndY(465);

				C3thinArc1.setCenterX(740);
				C3thinArc1.setCenterY(460);

				C3thinVLine1.setStartX(745);
				C3thinVLine1.setStartY(460);
				C3thinVLine1.setEndX(745);
				C3thinVLine1.setEndY(450);

				C3thickHLine1.setStartX(730);
				C3thickHLine1.setStartY(450);
				C3thickHLine1.setEndX(760);
				C3thickHLine1.setEndY(450);

				C3thickVLine1.setStartX(745);
				C3thickVLine1.setStartY(450);
				C3thickVLine1.setEndX(745);
				C3thickVLine1.setEndY(145);

				C3Arc1.setCenterX(745);
				C3Arc1.setCenterY(105);

				C3thickVLine2.setStartX(745);
				C3thickVLine2.setStartY(65);
				C3thickVLine2.setEndX(745);
				C3thickVLine2.setEndY(35);

				////////

				circle4.setCenterX(100);
				circle4.setCenterY(350);

				C4thinHLine1.setStartX(135);
				C4thinHLine1.setStartX(350);
				C4thinHLine1.setEndX(310);
				C4thinHLine1.setEndY(350);

				C4thinHLine2.setStartX(390);
				C4thinHLine2.setStartY(350);
				C4thinHLine2.setEndX(585);
				C4thinHLine2.setEndY(350);

				C4thinArc1.setCenterX(585);
				C4thinArc1.setCenterY(345);

				C4thinVLine1.setStartX(590);
				C4thinVLine1.setStartY(345);
				C4thinVLine1.setEndX(590);
				C4thinVLine1.setEndY(335);

				C4thickHLine1.setStartX(575);
				C4thickHLine1.setStartY(335);
				C4thickHLine1.setEndX(605);
				C4thickHLine1.setEndY(335);

				C4thickVLine1.setStartX(590);
				C4thickVLine1.setStartY(335);
				C4thickVLine1.setEndX(590);
				C4thickVLine1.setEndY(215);

				C4Arc1.setCenterX(590);
				C4Arc1.setCenterY(175);

				C4thickVLine2.setStartX(590);
				C4thickVLine2.setStartY(135);
				C4thickVLine2.setEndX(590);
				C4thickVLine2.setEndY(105);

				pane.getChildren().addAll(C1thinVLine1, C1thinHLine1, C1thinArc1, C1thickVLine1, C1thickHLine1, circle1,
						circle2, C2thinVLine1, C2thinVLine2, C2thinVLine3, C2thinArc1, C2thinHLine2, C2thickVLine1,
						C2thickHLine1, C2Arc1, C2thickHLine2, circle3, C3thinHLine1, C3thinHLine2, C3thinArc1,
						C3thinVLine1, C3thickHLine1, C3thickVLine1, C3Arc1, C3thickVLine2, circle4, C4thinHLine1,
						C4thinHLine2, C4thinArc1, C4thinVLine1, C4thickHLine1, C4thickVLine1, C4Arc1, C4thickVLine2,
						DiscLine1, DiscArc1, connectorCircle1, DiscLine2, DiscArc2, connectorCircle2, group1, group2,
						group3, group4);
			}

			checkShapeIntersection(C3Arc1, C2thickHLine2);
			if (collisionDetected) {

				for (Timeline anim : allAnims) {
					anim.pause();
				}
				pane.getChildren().clear();

				C1thickHLine1.setStartX(685);
				C1thickHLine1.setStartY(175);
				C1thickHLine1.setEndX(590);
				C1thickHLine1.setEndY(175);

				circle2.setCenterX(350);
				circle2.setCenterY(620);

				C2thinVLine1.setStartX(350);
				C2thinVLine1.setStartY(585);
				C2thinVLine1.setEndX(350);
				C2thinVLine1.setEndY(505);

				C2thinVLine2.setStartX(350);
				C2thinVLine2.setStartY(425);
				C2thinVLine2.setEndX(350);
				C2thinVLine2.setEndY(390);

				C2thinVLine3.setStartX(350);
				C2thinVLine3.setStartY(310);
				C2thinVLine3.setEndX(350);
				C2thinVLine3.setEndY(110);

				C2thinArc1.setCenterX(355);
				C2thinArc1.setCenterY(110);

				C2thinHLine2.setStartX(355);
				C2thinHLine2.setStartY(105);
				C2thinHLine2.setEndX(365);
				C2thinHLine2.setEndY(105);

				C2thickVLine1.setStartX(365);
				C2thickVLine1.setStartY(90);
				C2thickVLine1.setEndX(365);
				C2thickVLine1.setEndY(120);

				C2thickHLine1.setStartX(365);
				C2thickHLine1.setStartY(105);
				C2thickHLine1.setEndX(550);
				C2thickHLine1.setEndY(105);

				C2Arc1.setCenterX(590);
				C2Arc1.setCenterY(105);

				C2thickHLine2.setStartX(630);
				C2thickHLine2.setStartY(105);
				C2thickHLine2.setEndX(750);
				C2thickHLine2.setEndY(105);

				/////
				
				DiscLine1.setStartX(310);
				DiscLine1.setStartY(365);
				DiscLine1.setEndX(390);
				DiscLine1.setEndY(365);
				
				DiscLine2.setStartX(350);
				DiscLine2.setStartY(310);
				DiscLine2.setEndX(350);
				DiscLine2.setEndY(390);

				circle3.setCenterX(100);
				circle3.setCenterY(465);

				C3thinHLine1.setStartX(135);
				C3thinHLine1.setStartY(465);
				C3thinHLine1.setEndX(310);
				C3thinHLine1.setEndY(465);

				C3thinHLine2.setStartX(390);
				C3thinHLine2.setStartY(465);
				C3thinHLine2.setEndX(740);
				C3thinHLine2.setEndY(465);

				C3thinArc1.setCenterX(740);
				C3thinArc1.setCenterY(460);

				C3thinVLine1.setStartX(745);
				C3thinVLine1.setStartY(460);
				C3thinVLine1.setEndX(745);
				C3thinVLine1.setEndY(450);

				C3thickHLine1.setStartX(730);
				C3thickHLine1.setStartY(450);
				C3thickHLine1.setEndX(760);
				C3thickHLine1.setEndY(450);

				C3thickVLine1.setStartX(745);
				C3thickVLine1.setStartY(450);
				C3thickVLine1.setEndX(745);
				C3thickVLine1.setEndY(145);

				C3Arc1.setCenterX(745);
				C3Arc1.setCenterY(105);

				C3thickVLine2.setStartX(745);
				C3thickVLine2.setStartY(65);
				C3thickVLine2.setEndX(745);
				C3thickVLine2.setEndY(35);

				////////

				circle4.setCenterX(100);
				circle4.setCenterY(350);

				C4thinHLine1.setStartX(135);
				C4thinHLine1.setStartX(350);
				C4thinHLine1.setEndX(310);
				C4thinHLine1.setEndY(350);

				C4thinHLine2.setStartX(390);
				C4thinHLine2.setStartY(350);
				C4thinHLine2.setEndX(585);
				C4thinHLine2.setEndY(350);

				C4thinArc1.setCenterX(585);
				C4thinArc1.setCenterY(345);

				C4thinVLine1.setStartX(590);
				C4thinVLine1.setStartY(345);
				C4thinVLine1.setEndX(590);
				C4thinVLine1.setEndY(335);

				C4thickHLine1.setStartX(575);
				C4thickHLine1.setStartY(335);
				C4thickHLine1.setEndX(605);
				C4thickHLine1.setEndY(335);

				C4thickVLine1.setStartX(590);
				C4thickVLine1.setStartY(335);
				C4thickVLine1.setEndX(590);
				C4thickVLine1.setEndY(215);

				C4Arc1.setCenterX(590);
				C4Arc1.setCenterY(175);

				C4thickVLine2.setStartX(590);
				C4thickVLine2.setStartY(135);
				C4thickVLine2.setEndX(590);
				C4thickVLine2.setEndY(105);

				pane.getChildren().addAll(C1thinVLine1, C1thinHLine1, C1thinArc1, C1thickVLine1, C1thickHLine1, circle1,
						circle2, C2thinVLine1, C2thinVLine2, C2thinVLine3, C2thinArc1, C2thinHLine2, C2thickVLine1,
						C2thickHLine1, C2Arc1, C2thickHLine2, circle3, C3thinHLine1, C3thinHLine2, C3thinArc1,
						C3thinVLine1, C3thickHLine1, C3thickVLine1, C3Arc1, C3thickVLine2, circle4, C4thinHLine1,
						C4thinHLine2, C4thinArc1, C4thinVLine1, C4thickHLine1, C4thickVLine1, C4Arc1, C4thickVLine2,
						DiscLine1, DiscArc1, connectorCircle1, DiscLine2, DiscArc2, connectorCircle2, group1, group2,
						group3, group4);
			}

			checkShapeIntersection(C4Arc1, C1thickHLine1);
			if (collisionDetected) {

				for (Timeline anim : allAnims) {
					anim.pause();
				}
				pane.getChildren().clear();

				C1thickHLine1.setStartX(685);
				C1thickHLine1.setStartY(175);
				C1thickHLine1.setEndX(590);
				C1thickHLine1.setEndY(175);

				circle2.setCenterX(350);
				circle2.setCenterY(620);

				C2thinVLine1.setStartX(350);
				C2thinVLine1.setStartY(585);
				C2thinVLine1.setEndX(350);
				C2thinVLine1.setEndY(505);

				C2thinVLine2.setStartX(350);
				C2thinVLine2.setStartY(425);
				C2thinVLine2.setEndX(350);
				C2thinVLine2.setEndY(390);

				C2thinVLine3.setStartX(350);
				C2thinVLine3.setStartY(310);
				C2thinVLine3.setEndX(350);
				C2thinVLine3.setEndY(110);

				C2thinArc1.setCenterX(355);
				C2thinArc1.setCenterY(110);

				C2thinHLine2.setStartX(355);
				C2thinHLine2.setStartY(105);
				C2thinHLine2.setEndX(365);
				C2thinHLine2.setEndY(105);

				C2thickVLine1.setStartX(365);
				C2thickVLine1.setStartY(90);
				C2thickVLine1.setEndX(365);
				C2thickVLine1.setEndY(120);

				C2thickHLine1.setStartX(365);
				C2thickHLine1.setStartY(105);
				C2thickHLine1.setEndX(550);
				C2thickHLine1.setEndY(105);

				C2Arc1.setCenterX(590);
				C2Arc1.setCenterY(105);

				C2thickHLine2.setStartX(630);
				C2thickHLine2.setStartY(105);
				C2thickHLine2.setEndX(750);
				C2thickHLine2.setEndY(105);

				/////

				DiscLine1.setStartX(310);
				DiscLine1.setStartY(465);
				DiscLine1.setEndX(390);
				DiscLine1.setEndY(465);
				
				DiscLine2.setStartX(350);
				DiscLine2.setStartY(310);
				DiscLine2.setEndX(350);
				DiscLine2.setEndY(390);
				
				circle3.setCenterX(100);
				circle3.setCenterY(465);

				C3thinHLine1.setStartX(135);
				C3thinHLine1.setStartY(465);
				C3thinHLine1.setEndX(310);
				C3thinHLine1.setEndY(465);

				C3thinHLine2.setStartX(390);
				C3thinHLine2.setStartY(465);
				C3thinHLine2.setEndX(740);
				C3thinHLine2.setEndY(465);

				C3thinArc1.setCenterX(740);
				C3thinArc1.setCenterY(460);

				C3thinVLine1.setStartX(745);
				C3thinVLine1.setStartY(460);
				C3thinVLine1.setEndX(745);
				C3thinVLine1.setEndY(450);

				C3thickHLine1.setStartX(730);
				C3thickHLine1.setStartY(450);
				C3thickHLine1.setEndX(760);
				C3thickHLine1.setEndY(450);

				C3thickVLine1.setStartX(745);
				C3thickVLine1.setStartY(450);
				C3thickVLine1.setEndX(745);
				C3thickVLine1.setEndY(145);

				C3Arc1.setCenterX(745);
				C3Arc1.setCenterY(105);

				C3thickVLine2.setStartX(745);
				C3thickVLine2.setStartY(65);
				C3thickVLine2.setEndX(745);
				C3thickVLine2.setEndY(35);

				////////

				circle4.setCenterX(100);
				circle4.setCenterY(350);

				C4thinHLine1.setStartX(135);
				C4thinHLine1.setStartX(350);
				C4thinHLine1.setEndX(310);
				C4thinHLine1.setEndY(350);

				C4thinHLine2.setStartX(390);
				C4thinHLine2.setStartY(350);
				C4thinHLine2.setEndX(585);
				C4thinHLine2.setEndY(350);

				C4thinArc1.setCenterX(585);
				C4thinArc1.setCenterY(345);

				C4thinVLine1.setStartX(590);
				C4thinVLine1.setStartY(345);
				C4thinVLine1.setEndX(590);
				C4thinVLine1.setEndY(335);

				C4thickHLine1.setStartX(575);
				C4thickHLine1.setStartY(335);
				C4thickHLine1.setEndX(605);
				C4thickHLine1.setEndY(335);

				C4thickVLine1.setStartX(590);
				C4thickVLine1.setStartY(335);
				C4thickVLine1.setEndX(590);
				C4thickVLine1.setEndY(215);

				C4Arc1.setCenterX(590);
				C4Arc1.setCenterY(175);

				C4thickVLine2.setStartX(590);
				C4thickVLine2.setStartY(135);
				C4thickVLine2.setEndX(590);
				C4thickVLine2.setEndY(105);

				pane.getChildren().addAll(C1thinVLine1, C1thinHLine1, C1thinArc1, C1thickVLine1, C1thickHLine1, circle1,
						circle2, C2thinVLine1, C2thinVLine2, C2thinVLine3, C2thinArc1, C2thinHLine2, C2thickVLine1,
						C2thickHLine1, C2Arc1, C2thickHLine2, circle3, C3thinHLine1, C3thinHLine2, C3thinArc1,
						C3thinVLine1, C3thickHLine1, C3thickVLine1, C3Arc1, C3thickVLine2, circle4, C4thinHLine1,
						C4thinHLine2, C4thinArc1, C4thinVLine1, C4thickHLine1, C4thickVLine1, C4Arc1, C4thickVLine2,
						DiscLine1, DiscArc1, connectorCircle1, DiscLine2, DiscArc2, connectorCircle2, group1, group2,
						group3, group4);
			}

		}));
		C2CollisionCheck.setCycleCount(Timeline.INDEFINITE);
		C2CollisionCheck.play();

		/////////////////////////////

		// Pane & Stage

		pane = new Pane();

		pane.setStyle("-fx-background-color: darkgrey");

		pane.getChildren().addAll(C1thinVLine1, C1thinHLine1, C1thinArc1, C1thickVLine1, C1thickHLine1, circle1,
				C2thinVLine1, C2thinVLine2, C2thinVLine3, C2thinHLine2, C2thinArc1, C2thickVLine1, C2thickHLine1,
				C2thickHLine2, C2Arc1, circle2, C3thinHLine1, C3thinHLine2, C3thinArc1, C3thinVLine1, C3thickHLine1,
				C3thickVLine1, C3thickVLine2, C3Arc1, circle3, C4thinHLine1, C4thinHLine2, C4thinVLine1, C4thinArc1,
				C4thickHLine1, C4thickVLine1, C4thickVLine2, C4Arc1, circle4, DiscLine1, DiscArc1, connectorCircle1,
				DiscLine2, DiscArc2, connectorCircle2, group1, group2, group3, group4);

		Scene scene = new Scene(pane, 1000, 700);

		primaryStage.setTitle("Level 5"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

	}
	//////////////////////

	public static void main(String[] args) {
		launch(args);

	}

	// Collison checking method

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
