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

public class Level2 extends Application {

	boolean collisionDetected = false;
	ArrayList<Timeline> allAnims;
	Pane pane;

	public void start(Stage primaryStage) {

		Group group1 = new Group();
		Group group2 = new Group();
		Group group3 = new Group();
		Group group4 = new Group();
		allAnims = new ArrayList<>();

		// For Circle 1
		Circle circle1 = new Circle();
		circle1.setCenterX(150);
		circle1.setCenterY(550);
		circle1.setRadius(35);
		circle1.setStroke(Color.DARKRED);
		circle1.setFill(Color.BROWN);

		Line C1thinVLine1 = new Line(150, 515, 150, 155);

		Arc C1thinArc1 = new Arc(155, 155, 5, 5, 180, -90);
		C1thinArc1.setType(ArcType.OPEN);
		C1thinArc1.setFill(null);
		C1thinArc1.setStroke(Color.BLACK);

		Line C1thinHLine1 = new Line(155, 150, 170, 150);

		Line C1thickVLine1 = new Line(170, 135, 170, 165);
		C1thickVLine1.setStrokeWidth(5);
		C1thickVLine1.setStrokeLineCap(null);

		Line C1thickHLine1 = new Line(170, 150, 327, 150);
		C1thickHLine1.setStrokeWidth(6);

		circle1.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition st = new ScaleTransition(Duration.millis(150), circle1);
				st.setByX(0.15);
				st.setByY(0.15);
				st.setCycleCount(2);
				st.setAutoReverse(true);
				st.play();

				Timeline C1AnimationHorizontal = new Timeline(new KeyFrame(Duration.seconds((327 - 170) / 200.0),
						new KeyValue(C1thickHLine1.endXProperty(), 170)));
				C1AnimationHorizontal.play();

				Timeline C1AnimationVerticalUp = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(C1thickVLine1.startYProperty(), 110)));
				C1AnimationVerticalUp.play();

				Timeline C1AnimationVerticalDown = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(C1thickVLine1.endYProperty(), 190)));
				C1AnimationVerticalDown.play();

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

		//////////////////////////////////////////////////////////////////////////////////////////////

		// For Circle2

		Circle circle2 = new Circle();
		circle2.setCenterX(322);
		circle2.setCenterY(550);
		circle2.setRadius(35);
		circle2.setStroke(Color.DARKRED);
		circle2.setFill(Color.BROWN);

		Line C2thinVLine1 = new Line(322, 515, 322, 445);

		Line C2thickHLine1 = new Line(307, 445, 337, 445);
		C2thickHLine1.setStrokeWidth(5);
		C2thickHLine1.setStrokeLineCap(null);

		Line C2thickVLine1 = new Line(322, 445, 322, 360);
		C2thickVLine1.setStrokeWidth(6);
		C2thickVLine1.setStrokeLineCap(null);

		Arc C2Arc1 = new Arc(322, 320, 40, 40, 90, 180);
		C2Arc1.setType(ArcType.OPEN);
		C2Arc1.setFill(null);
		C2Arc1.setStroke(Color.BLACK);
		C2Arc1.setStrokeWidth(6);

		Line C2thickVLine2 = new Line(322, 275, 322, 195);
		C2thickVLine2.setStrokeWidth(6);
		C2thickVLine2.setStrokeLineCap(null);

		Arc C2Arc2 = new Arc(322, 150, 40, 40, 90, -180);
		C2Arc2.setType(ArcType.OPEN);
		C2Arc2.setFill(null);
		C2Arc2.setStroke(Color.BLACK);
		C2Arc2.setStrokeWidth(6);

		Line C2thickVLine3 = new Line(322, 108, 322, 60);
		C2thickVLine3.setStrokeWidth(6);

		circle2.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition st = new ScaleTransition(Duration.millis(150), circle2);
				st.setByX(0.15);
				st.setByY(0.15);
				st.setCycleCount(2);
				st.setAutoReverse(true);
				st.play();

				Timeline C2AnimationVertical1 = new Timeline(new KeyFrame(Duration.seconds((445 - 360) / 200.0),
						new KeyValue(C2thickVLine1.endYProperty(), 445)));
				C2AnimationVertical1.play();

				Timeline C2AnimationVertical2End = new Timeline(new KeyFrame(Duration.seconds((445 - 195) / 200.0),
						new KeyValue(C2thickVLine2.endYProperty(), 445)));
				C2AnimationVertical2End.play();

				Timeline C2AnimationVertical2Start = new Timeline(new KeyFrame(Duration.seconds((445 - 275) / 200.0),
						new KeyValue(C2thickVLine2.startYProperty(), 445)));
				C2AnimationVertical2Start.play();

				Timeline C2AnimationHorizontalRight = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(C2thickHLine1.startXProperty(), 282)));
				C2AnimationHorizontalRight.play();

				Timeline C2AnimationHorizontalLeft = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(C2thickHLine1.endXProperty(), 362)));
				C2AnimationHorizontalLeft.play();

				Timeline C2AnimationVertical3End = new Timeline(new KeyFrame(Duration.seconds((445 - 60) / 200.0),
						new KeyValue(C2thickVLine3.endYProperty(), 445)));
				C2AnimationVertical3End.play();

				Timeline C2AnimationVertical3Start = new Timeline(new KeyFrame(Duration.seconds((445 - 108) / 200.0),
						new KeyValue(C2thickVLine3.startYProperty(), 445)));
				C2AnimationVertical3Start.play();

				Timeline C2AnimationArc1 = new Timeline(new KeyFrame(Duration.seconds((485 - 320) / 200.0),
						new KeyValue(C2Arc1.centerYProperty(), 485)));
				C2AnimationArc1.play();

				Timeline C2AnimationArc2 = new Timeline(new KeyFrame(Duration.seconds((485 - 150) / 200.0),
						new KeyValue(C2Arc2.centerYProperty(), 485)));
				C2AnimationArc2.play();

				Timeline C2Arc1Disappear = new Timeline(
						new KeyFrame(Duration.millis(500), new KeyValue(C2Arc1.lengthProperty(), 0)));
				C2Arc1Disappear.setDelay(Duration.seconds((430 - 360) / 200.0));
				C2Arc1Disappear.play();

				Timeline C2Arc2Disappear = new Timeline(
						new KeyFrame(Duration.millis(500), new KeyValue(C2Arc2.lengthProperty(), 0)));
				C2Arc2Disappear.setDelay(Duration.seconds((430 - 190) / 200.0));
				C2Arc2Disappear.play();

				Timeline[] animations = { C2AnimationVertical1, C2AnimationVertical2End, C2AnimationVertical2Start,
						C2AnimationVertical3End, C2AnimationVertical3Start, C2AnimationArc1, C2AnimationArc2,
						C2Arc1Disappear, C2Arc2Disappear };
				Collections.addAll(allAnims, animations);

				C2AnimationVertical3End.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						group2.getChildren().addAll(C2thinVLine1, C2Arc1, C2Arc2, C2thickHLine1, C2thickVLine1,
								C2thickVLine2, C2thickVLine3, circle2);

						FadeTransition ft = new FadeTransition(Duration.millis(500), group2);
						ft.setFromValue(1.0);
						ft.setToValue(0.0);
						ft.play();

					}
				});
			}
		});

		//////////////////////////////////////////////////////////////////////////////////////////

		// For Circle3

		Circle circle3 = new Circle();
		circle3.setCenterX(450);
		circle3.setCenterY(550);
		circle3.setRadius(35);
		circle3.setStroke(Color.DARKRED);
		circle3.setFill(Color.BROWN);

		Line C3thinVLine1 = new Line(450, 515, 450, 445);

		Line C3thickHLine1 = new Line(435, 445, 465, 445);
		C3thickHLine1.setStrokeWidth(5);
		C3thickHLine1.setStrokeLineCap(null);

		Line C3thickVLine1 = new Line(450, 445, 450, 315);
		C3thickVLine1.setStrokeWidth(6);

		circle3.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition st = new ScaleTransition(Duration.millis(150), circle3);
				st.setByX(0.15);
				st.setByY(0.15);
				st.setCycleCount(2);
				st.setAutoReverse(true);
				st.play();

				Timeline C3AnimationVertical = new Timeline(new KeyFrame(Duration.seconds((445 - 360) / 200.0),
						new KeyValue(C3thickVLine1.endYProperty(), 445)));
				C3AnimationVertical.play();

				Timeline C3AnimationHorizontalRight = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(C3thickHLine1.startXProperty(), 410)));
				C3AnimationHorizontalRight.play();

				Timeline C3AnimationHorizontalLeft = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(C3thickHLine1.endXProperty(), 490)));
				C3AnimationHorizontalLeft.play();

				Timeline[] animations = { C3AnimationVertical, C3AnimationHorizontalRight, C3AnimationHorizontalLeft };
				Collections.addAll(allAnims, animations);

				C3AnimationVertical.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						group3.getChildren().addAll(C3thickVLine1, C3thickHLine1, C3thinVLine1, circle3);
						FadeTransition ft = new FadeTransition(Duration.millis(500), group3);
						ft.setFromValue(1.0);
						ft.setToValue(0.0);
						ft.play();

					}
				});
			}
		});

		///////////////////////////////////////////////////////////////////////////////////////////////

		// For Circle4

		Circle circle4 = new Circle();
		circle4.setCenterX(850);
		circle4.setCenterY(550);
		circle4.setRadius(35);
		circle4.setStroke(Color.DARKRED);
		circle4.setFill(Color.BROWN);

		Line C4thinVLine1 = new Line(850, 515, 850, 325);

		Arc C4thinArc1 = new Arc(845, 325, 5, 5, 0, 90);
		C4thinArc1.setType(ArcType.OPEN);
		C4thinArc1.setFill(null);
		C4thinArc1.setStroke(Color.BLACK);

		Line C4thinHLine1 = new Line(845, 320, 830, 320);

		Line C4thickVLine1 = new Line(830, 305, 830, 335);
		C4thickVLine1.setStrokeWidth(5);
		C4thickVLine1.setStrokeLineCap(null);

		Line C4thickHLine1 = new Line(830, 320, 494, 320);
		C4thickHLine1.setStrokeWidth(6);

		Arc C4Arc2 = new Arc(450, 320, 40, 40, 180, -180);
		C4Arc2.setType(ArcType.OPEN);
		C4Arc2.setFill(null);
		C4Arc2.setStroke(Color.BLACK);
		C4Arc2.setStrokeWidth(6);

		Line C4thickHLine2 = new Line(404, 320, 314, 320);
		C4thickHLine2.setStrokeWidth(6);

		circle4.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition st = new ScaleTransition(Duration.millis(150), circle4);
				st.setByX(0.15);
				st.setByY(0.15);
				st.setCycleCount(2);
				st.setAutoReverse(true);
				st.play();

				Timeline C4AnimationHorizontal = new Timeline(new KeyFrame(Duration.seconds((830 - 494) / 200.0),
						new KeyValue(C4thickHLine1.endXProperty(), 830)));
				C4AnimationHorizontal.play();

				Timeline C4AnimationVerticalUp = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(C4thickVLine1.startYProperty(), 280)));
				C4AnimationVerticalUp.play();

				Timeline C4AnimationVerticalDown = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(C4thickVLine1.endYProperty(), 360)));
				C4AnimationVerticalDown.play();

				Timeline C4AnimationArc = new Timeline(new KeyFrame(Duration.seconds((870 - 450) / 200.0),
						new KeyValue(C4Arc2.centerXProperty(), 870)));
				C4AnimationArc.play();

				Timeline C4AnimationHorizontal2End = new Timeline(new KeyFrame(Duration.seconds((830 - 314) / 200.0),
						new KeyValue(C4thickHLine2.endXProperty(), 830)));
				C4AnimationHorizontal2End.play();

				Timeline C4AnimationHorizontal2Start = new Timeline(new KeyFrame(Duration.seconds((830 - 404) / 200.0),
						new KeyValue(C4thickHLine2.startXProperty(), 830)));
				C4AnimationHorizontal2Start.play();

				Timeline[] animations = { C4AnimationHorizontal, C4AnimationVerticalUp, C4AnimationVerticalDown,
						C4AnimationArc, C4AnimationHorizontal2End, C4AnimationHorizontal2End };
				Collections.addAll(allAnims, animations);

				Timeline C4ArcDisappear = new Timeline(
						new KeyFrame(Duration.millis(500), new KeyValue(C4Arc2.lengthProperty(), 0)));
				C4ArcDisappear.setDelay(Duration.seconds((778 - 450) / 200.0));
				C4ArcDisappear.play();

				C4AnimationHorizontal2End.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						group4.getChildren().addAll(C4thinVLine1, C4thinArc1, C4thinHLine1, C4thickVLine1,
								C4thickHLine1, C4thickHLine2, circle4);

						FadeTransition ft = new FadeTransition(Duration.millis(500), group4);
						ft.setFromValue(1.0);
						ft.setToValue(0.0);
						ft.play();

					}
				});
			}
		});

		Timeline C2CollisionCheck = new Timeline(new KeyFrame(Duration.millis(50), event -> {

			checkShapeIntersection(C2Arc2, C1thickHLine1);
			if (collisionDetected) {

				for (Timeline anim : allAnims) {
					anim.pause();
				}

				pane.getChildren().clear();

				C1thinVLine1.setStartX(150);
				C1thinVLine1.setStartY(515);
				C1thinVLine1.setEndX(150);
				C1thinVLine1.setEndY(155);

				C1thinHLine1.setStartX(155);
				C1thinHLine1.setStartY(150);
				C1thinHLine1.setEndX(170);
				C1thinHLine1.setEndY(150);

				C1thickVLine1.setStartX(170);
				C1thickVLine1.setStartY(135);
				C1thickVLine1.setEndX(170);
				C1thickVLine1.setEndY(165);

				C1thickHLine1.setStartX(170);
				C1thickHLine1.setStartY(150);
				C1thickHLine1.setEndX(327);
				C1thickHLine1.setEndY(150);

				C1thinArc1.setCenterX(155);
				C1thinArc1.setCenterY(155);

				////

				circle2.setCenterX(322);
				circle2.setCenterY(550);

				C2thinVLine1.setStartX(322);
				C2thinVLine1.setStartY(515);
				C2thinVLine1.setEndX(322);
				C2thinVLine1.setEndY(445);

				C2thickHLine1.setStartX(307);
				C2thickHLine1.setStartY(445);
				C2thickHLine1.setEndX(337);
				C2thickHLine1.setEndY(445);

				C2thickVLine1.setStartX(322);
				C2thickVLine1.setStartY(445);
				C2thickVLine1.setEndX(322);
				C2thickVLine1.setEndY(360);

				C2Arc1.setCenterX(322);
				C2Arc1.setCenterY(320);

				C2thickVLine2.setStartX(322);
				C2thickVLine2.setStartY(275);
				C2thickVLine2.setEndX(322);
				C2thickVLine2.setEndY(195);

				C2Arc2.setCenterX(322);
				C2Arc2.setCenterY(150);

				C2thickVLine3.setStartX(322);
				C2thickVLine3.setStartY(108);
				C2thickVLine3.setEndX(322);
				C2thickVLine3.setEndY(60);

				///

				circle3.setCenterX(450);
				circle3.setCenterY(550);

				C3thinVLine1.setStartX(450);
				C3thinVLine1.setStartY(515);
				C3thinVLine1.setEndX(450);
				C3thinVLine1.setEndY(445);

				C3thickHLine1.setStartX(435);
				C3thickHLine1.setStartY(445);
				C3thickHLine1.setEndX(465);
				C3thickHLine1.setEndY(445);

				C3thickVLine1.setStartX(450);
				C3thickVLine1.setStartY(445);
				C3thickVLine1.setEndX(450);
				C3thickVLine1.setEndY(315);

				////

				circle4.setCenterX(850);
				circle4.setCenterY(550);

				C4thinVLine1.setStartX(850);
				C4thinVLine1.setStartY(515);
				C4thinVLine1.setEndX(850);
				C4thinVLine1.setEndY(325);

				C4thinArc1.setCenterX(845);
				C4thinArc1.setCenterY(325);

				C4thinHLine1.setStartX(845);
				C4thinHLine1.setStartY(320);
				C4thinHLine1.setEndX(830);
				C4thinHLine1.setEndY(320);

				C4thickVLine1.setStartX(830);
				C4thickVLine1.setStartY(305);
				C4thickVLine1.setEndX(830);
				C4thickVLine1.setEndY(335);

				C4thickHLine1.setStartX(830);
				C4thickHLine1.setStartY(320);
				C4thickHLine1.setEndX(494);
				C4thickHLine1.setEndY(320);

				C4Arc2.setCenterX(450);
				C4Arc2.setCenterY(320);

				C4thickHLine2.setStartX(404);
				C4thickHLine2.setStartY(320);
				C4thickHLine2.setEndX(314);
				C4thickHLine2.setEndY(320);

				pane.getChildren().addAll(circle1, circle2, circle3, C1thinVLine1, C1thinHLine1, C1thickVLine1,
						C1thickHLine1, C2thinVLine1, C2thickHLine1, C2thickVLine1, C2Arc1, C2thickVLine2, C2Arc2,
						C2thickVLine3, C3thinVLine1, C3thickHLine1, C3thickVLine1, circle4, C4thinVLine1, C4thinArc1,
						C4thinHLine1, C4thickVLine1, C4thickHLine1, C4Arc2, C4thickHLine2, C1thinArc1, group1, group2,
						group3, group4);
			}

			checkShapeIntersection(C2Arc1, C4thickHLine2);
			if (collisionDetected) {

				for (Timeline anim : allAnims) {
					anim.pause();
				}

				pane.getChildren().clear();

				C1thinVLine1.setStartX(150);
				C1thinVLine1.setStartY(515);
				C1thinVLine1.setEndX(150);
				C1thinVLine1.setEndY(155);

				C1thinHLine1.setStartX(155);
				C1thinHLine1.setStartY(150);
				C1thinHLine1.setEndX(170);
				C1thinHLine1.setEndY(150);

				C1thickVLine1.setStartX(170);
				C1thickVLine1.setStartY(135);
				C1thickVLine1.setEndX(170);
				C1thickVLine1.setEndY(165);

				C1thickHLine1.setStartX(170);
				C1thickHLine1.setStartY(150);
				C1thickHLine1.setEndX(327);
				C1thickHLine1.setEndY(150);

				C1thinArc1.setCenterX(155);
				C1thinArc1.setCenterY(155);

				////

				circle2.setCenterX(322);
				circle2.setCenterY(550);

				C2thinVLine1.setStartX(322);
				C2thinVLine1.setStartY(515);
				C2thinVLine1.setEndX(322);
				C2thinVLine1.setEndY(445);

				C2thickHLine1.setStartX(307);
				C2thickHLine1.setStartY(445);
				C2thickHLine1.setEndX(337);
				C2thickHLine1.setEndY(445);

				C2thickVLine1.setStartX(322);
				C2thickVLine1.setStartY(445);
				C2thickVLine1.setEndX(322);
				C2thickVLine1.setEndY(360);

				C2Arc1.setCenterX(322);
				C2Arc1.setCenterY(320);

				C2thickVLine2.setStartX(322);
				C2thickVLine2.setStartY(275);
				C2thickVLine2.setEndX(322);
				C2thickVLine2.setEndY(195);

				C2Arc2.setCenterX(322);
				C2Arc2.setCenterY(150);

				C2thickVLine3.setStartX(322);
				C2thickVLine3.setStartY(108);
				C2thickVLine3.setEndX(322);
				C2thickVLine3.setEndY(60);

				///

				circle3.setCenterX(450);
				circle3.setCenterY(550);

				C3thinVLine1.setStartX(450);
				C3thinVLine1.setStartY(515);
				C3thinVLine1.setEndX(450);
				C3thinVLine1.setEndY(445);

				C3thickHLine1.setStartX(435);
				C3thickHLine1.setStartY(445);
				C3thickHLine1.setEndX(465);
				C3thickHLine1.setEndY(445);

				C3thickVLine1.setStartX(450);
				C3thickVLine1.setStartY(445);
				C3thickVLine1.setEndX(450);
				C3thickVLine1.setEndY(315);

				////

				circle4.setCenterX(850);
				circle4.setCenterY(550);

				C4thinVLine1.setStartX(850);
				C4thinVLine1.setStartY(515);
				C4thinVLine1.setEndX(850);
				C4thinVLine1.setEndY(325);

				C4thinArc1.setCenterX(845);
				C4thinArc1.setCenterY(325);

				C4thinHLine1.setStartX(845);
				C4thinHLine1.setStartY(320);
				C4thinHLine1.setEndX(830);
				C4thinHLine1.setEndY(320);

				C4thickVLine1.setStartX(830);
				C4thickVLine1.setStartY(305);
				C4thickVLine1.setEndX(830);
				C4thickVLine1.setEndY(335);

				C4thickHLine1.setStartX(830);
				C4thickHLine1.setStartY(320);
				C4thickHLine1.setEndX(494);
				C4thickHLine1.setEndY(320);

				C4Arc2.setCenterX(450);
				C4Arc2.setCenterY(320);

				C4thickHLine2.setStartX(404);
				C4thickHLine2.setStartY(320);
				C4thickHLine2.setEndX(314);
				C4thickHLine2.setEndY(320);

				pane.getChildren().addAll(circle1, circle2, circle3, C1thinVLine1, C1thinHLine1, C1thickVLine1,
						C1thickHLine1, C2thinVLine1, C2thickHLine1, C2thickVLine1, C2Arc1, C2thickVLine2, C2Arc2,
						C2thickVLine3, C3thinVLine1, C3thickHLine1, C3thickVLine1, circle4, C4thinVLine1, C4thinArc1,
						C4thinHLine1, C4thickVLine1, C4thickHLine1, C4Arc2, C4thickHLine2, C1thinArc1, group1, group2,
						group3, group4);
			}

			checkShapeIntersection(C4Arc2, C3thickVLine1);
			if (collisionDetected) {

				for (Timeline anim : allAnims) {
					anim.pause();
				}

				pane.getChildren().clear();

				C1thinVLine1.setStartX(150);
				C1thinVLine1.setStartY(515);
				C1thinVLine1.setEndX(150);
				C1thinVLine1.setEndY(155);

				C1thinHLine1.setStartX(155);
				C1thinHLine1.setStartY(150);
				C1thinHLine1.setEndX(170);
				C1thinHLine1.setEndY(150);

				C1thickVLine1.setStartX(170);
				C1thickVLine1.setStartY(135);
				C1thickVLine1.setEndX(170);
				C1thickVLine1.setEndY(165);

				C1thickHLine1.setStartX(170);
				C1thickHLine1.setStartY(150);
				C1thickHLine1.setEndX(327);
				C1thickHLine1.setEndY(150);

				C1thinArc1.setCenterX(155);
				C1thinArc1.setCenterY(155);

				////

				circle2.setCenterX(322);
				circle2.setCenterY(550);

				C2thinVLine1.setStartX(322);
				C2thinVLine1.setStartY(515);
				C2thinVLine1.setEndX(322);
				C2thinVLine1.setEndY(445);

				C2thickHLine1.setStartX(307);
				C2thickHLine1.setStartY(445);
				C2thickHLine1.setEndX(337);
				C2thickHLine1.setEndY(445);

				C2thickVLine1.setStartX(322);
				C2thickVLine1.setStartY(445);
				C2thickVLine1.setEndX(322);
				C2thickVLine1.setEndY(360);

				C2Arc1.setCenterX(322);
				C2Arc1.setCenterY(320);

				C2thickVLine2.setStartX(322);
				C2thickVLine2.setStartY(275);
				C2thickVLine2.setEndX(322);
				C2thickVLine2.setEndY(195);

				C2Arc2.setCenterX(322);
				C2Arc2.setCenterY(150);

				C2thickVLine3.setStartX(322);
				C2thickVLine3.setStartY(108);
				C2thickVLine3.setEndX(322);
				C2thickVLine3.setEndY(60);

				///

				circle3.setCenterX(450);
				circle3.setCenterY(550);

				C3thinVLine1.setStartX(450);
				C3thinVLine1.setStartY(515);
				C3thinVLine1.setEndX(450);
				C3thinVLine1.setEndY(445);

				C3thickHLine1.setStartX(435);
				C3thickHLine1.setStartY(445);
				C3thickHLine1.setEndX(465);
				C3thickHLine1.setEndY(445);

				C3thickVLine1.setStartX(450);
				C3thickVLine1.setStartY(445);
				C3thickVLine1.setEndX(450);
				C3thickVLine1.setEndY(315);

				////

				circle4.setCenterX(850);
				circle4.setCenterY(550);

				C4thinVLine1.setStartX(850);
				C4thinVLine1.setStartY(515);
				C4thinVLine1.setEndX(850);
				C4thinVLine1.setEndY(325);

				C4thinArc1.setCenterX(845);
				C4thinArc1.setCenterY(325);

				C4thinHLine1.setStartX(845);
				C4thinHLine1.setStartY(320);
				C4thinHLine1.setEndX(830);
				C4thinHLine1.setEndY(320);

				C4thickVLine1.setStartX(830);
				C4thickVLine1.setStartY(305);
				C4thickVLine1.setEndX(830);
				C4thickVLine1.setEndY(335);

				C4thickHLine1.setStartX(830);
				C4thickHLine1.setStartY(320);
				C4thickHLine1.setEndX(494);
				C4thickHLine1.setEndY(320);

				C4Arc2.setCenterX(450);
				C4Arc2.setCenterY(320);

				C4thickHLine2.setStartX(404);
				C4thickHLine2.setStartY(320);
				C4thickHLine2.setEndX(314);
				C4thickHLine2.setEndY(320);

				pane.getChildren().addAll(circle1, circle2, circle3, C1thinVLine1, C1thinHLine1, C1thickVLine1,
						C1thickHLine1, C2thinVLine1, C2thickHLine1, C2thickVLine1, C2Arc1, C2thickVLine2, C2Arc2,
						C2thickVLine3, C3thinVLine1, C3thickHLine1, C3thickVLine1, circle4, C4thinVLine1, C4thinArc1,
						C4thinHLine1, C4thickVLine1, C4thickHLine1, C4Arc2, C4thickHLine2, C1thinArc1, group1, group2,
						group3, group4);
			}

		}));
		C2CollisionCheck.setCycleCount(Timeline.INDEFINITE);
		C2CollisionCheck.play();

		//////////////////////
		pane = new Pane();
		pane.setStyle("-fx-background-color: darkgrey");

		pane.getChildren().addAll(circle1, circle2, C1thinVLine1, C1thinHLine1, C1thickVLine1, C1thickHLine1,
				C2thinVLine1, C2thickHLine1, C2thickVLine1, C2Arc1, C2thickVLine2, C2Arc2, C2thickVLine3, circle3,
				C3thinVLine1, C3thickHLine1, C3thickVLine1, circle4, C4thinVLine1, C4thinHLine1, C4thickVLine1,
				C4thickHLine1, C4thinArc1, C4thickHLine2, C1thinArc1, C4Arc2, group1, group2, group3, group4);

		Scene scene = new Scene(pane, 1000, 700);

		primaryStage.setTitle("Level 2"); // Set the stage title
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
