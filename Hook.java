
// Murat Şenol 
// Ahmet Faruk Çolak 

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
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Hook extends Application {

	//Defining in global

	Stage window;
	Scene scene1, scene2, scene3, scene4, scene5, scene6;

	boolean lv1CollisionDetected = false;
	ArrayList<Timeline> lv1AllAnims;
	Pane level1Pane;

	boolean lv2CollisionDetected = false;
	ArrayList<Timeline> lv2AllAnims;
	Pane level2Pane;

	boolean lv3CollisionDetected = false;
	ArrayList<Timeline> lv3AllAnims;
	Pane level3Pane;

	boolean lv4CollisionDetected = false;
	ArrayList<Timeline> lv4AllAnims;
	Pane level4Pane;

	boolean lv5CollisionDetected = false;
	ArrayList<Timeline> lv5AllAnims;
	Pane level5Pane;

	public void start(Stage primaryStage) {

		/////////////// MENU////////////////

		window = primaryStage;

		// Creates a pane
		Pane menuPane = new Pane();

		// Creates a circle
		Circle menuCircle1 = new Circle(300, 450, 50);
		menuCircle1.setStroke(Color.RED);
		menuCircle1.setFill(Color.RED);

		// To make it setOnMousePressed method available on the whole circle
		// Because there will be a text on the circle
		Circle startCircle = new Circle();
		startCircle.setCenterX(300);
		startCircle.setCenterY(450);
		startCircle.setRadius(50);
		startCircle.setOpacity(0.0);

		startCircle.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				// To enlarge and shrink circle when pressed
				ScaleTransition st = new ScaleTransition(Duration.millis(150), menuCircle1);
				st.setByX(0.15);
				st.setByY(0.15);
				st.setCycleCount(2);
				st.setAutoReverse(true);
				st.play();
			}
		});

		//When the startCircle is clicked, the game starts, it passes to Level 1.
		startCircle.setOnMouseClicked(e -> window.setScene(scene2));

		// Creates a circle
		Circle menuCircle2 = new Circle(700, 450, 50);
		menuCircle2.setStroke(Color.RED);
		menuCircle2.setFill(Color.RED);

		// To make it setOnMousePressed method available on the whole circle
		Circle quitCircle = new Circle();
		quitCircle.setCenterX(700);
		quitCircle.setCenterY(450);
		quitCircle.setRadius(50);
		quitCircle.setOpacity(0.0);

		quitCircle.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				// To enlarge and shrink circle when pressed
				ScaleTransition st = new ScaleTransition(Duration.millis(150), menuCircle2);
				st.setByX(0.15);
				st.setByY(0.15);
				st.setCycleCount(2);
				st.setAutoReverse(true);
				st.play();

				// When the user clicks on the quitCircle, program will shut down.
				if (quitCircle.isPressed()) {
					window.close();
				}
			}
		});

		// Creates a text, and locates it approximately on the startCircle.
		Text menuText1 = new Text(20, 20, "START");
		menuText1.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 25));
		menuText1.setFill(Color.LIGHTYELLOW);
		menuText1.setX(260);
		menuText1.setY(455);

		// Creates a text, and locates it approximately on the quitCircle.
		Text menuText2 = new Text(20, 20, "QUIT");
		menuText2.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 25));
		menuText2.setFill(Color.LIGHTYELLOW);
		menuText2.setX(668);
		menuText2.setY(455);

		// Connection lines
		Line menuHLine1 = new Line(350, 450, 650, 450);

		Line menuVLine1 = new Line(500, 450, 500, 275);

		Line menuHLine2 = new Line(300, 275, 700, 275);
		menuHLine2.setStrokeWidth(7);
		menuHLine2.setStroke(Color.DARKBLUE);

		Text menuText3 = new Text(20, 20, "HOOK");
		menuText3.setFont(Font.font("Comic San MS", FontWeight.BOLD, 140));
		menuText3.setFill(Color.DARKBLUE);
		menuText3.setX(290);
		menuText3.setY(275);

		//Sets the background color to darkgrey
		menuPane.setStyle("-fx-background-color: darkgrey");

		menuPane.getChildren().addAll(menuCircle1, menuCircle2, menuText1, menuText2, menuHLine1, menuVLine1,
				menuHLine2, menuText3, startCircle, quitCircle);

		Scene scene1 = new Scene(menuPane, 1000, 700);

		//////////////////////////////////// LEVEL1/////////////////////////////////////
		//Creating groups not to write same FadeTransition part for every line.
		Group lv1Group1 = new Group();
		Group lv1Group2 = new Group();

		//Contains animations. In order to pause all the animations when a collision pops up.
		lv1AllAnims = new ArrayList<>();

		// For Circle1
		Circle lv1Circle1 = new Circle();
		lv1Circle1.setCenterX(600);
		lv1Circle1.setCenterY(600);
		lv1Circle1.setRadius(35);
		lv1Circle1.setStroke(Color.DARKRED);
		lv1Circle1.setFill(Color.BROWN);

		Line lv1C1thinVLine1 = new Line(600, 565, 600, 465);

		Line lv1C1thickHLine1 = new Line(585, 465, 615, 465);
		lv1C1thickHLine1.setStrokeWidth(5);
		lv1C1thickHLine1.setStrokeLineCap(null); //Makes the line's corners smooth.

		Line lv1C1thickVLine1 = new Line(600, 465, 600, 185);
		lv1C1thickVLine1.setStrokeWidth(6);

		//Fires an event when the circle is pressed(mouse)
		lv1Circle1.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) { //Handles the event

				//Makes the circle to large and shrink, to notify the user that circle is pressed.
				ScaleTransition lv1st = new ScaleTransition(Duration.millis(150), lv1Circle1);
				lv1st.setByX(0.15);
				lv1st.setByY(0.15);
				lv1st.setCycleCount(2);
				lv1st.setAutoReverse(true);
				lv1st.play();

				//Animations

				//With 200 pixel speed, changes endY of the corresponding line to the 465 in the Y axis
				Timeline lv1C1AnimationVertical = new Timeline(new KeyFrame(Duration.seconds((465 - 185) / 200.0),
						new KeyValue(lv1C1thickVLine1.endYProperty(), 465)));
				lv1C1AnimationVertical.play();

				Timeline lv1C1AnimationHorizontalRight = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(lv1C1thickHLine1.startXProperty(), 560)));
				lv1C1AnimationHorizontalRight.play();

				Timeline lv1C1AnimationHorizontalLeft = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(lv1C1thickHLine1.endXProperty(), 640)));
				lv1C1AnimationHorizontalLeft.play();

				//Animation array
				Timeline[] lv1Animations = { lv1C1AnimationVertical, lv1C1AnimationHorizontalRight,
						lv1C1AnimationHorizontalLeft };
				Collections.addAll(lv1AllAnims, lv1Animations);

				//If the sliding of the line is over, fires another event setOnFinished
				lv1C1AnimationVertical.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						lv1Group1.getChildren().addAll(lv1C1thickVLine1, lv1C1thickHLine1, lv1C1thinVLine1, lv1Circle1);

						//Erases the group slowly(discoloration)
						FadeTransition lv1ft = new FadeTransition(Duration.millis(500), lv1Group1);
						lv1ft.setFromValue(1.0);
						lv1ft.setToValue(0.0);
						lv1ft.play();

					}
				});
			}
		});

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// For Circle2
		Circle lv1Circle2 = new Circle();
		lv1Circle2.setCenterX(850);
		lv1Circle2.setCenterY(500);
		lv1Circle2.setRadius(35);
		lv1Circle2.setStroke(Color.DARKRED);
		lv1Circle2.setFill(Color.BROWN);

		Line lv1C2thinHLine1 = new Line(815, 500, 155, 500);

		Arc lv1C2thinArc1 = new Arc(155, 495, 5, 5, 270, -90);
		lv1C2thinArc1.setType(ArcType.OPEN);
		lv1C2thinArc1.setFill(null);
		lv1C2thinArc1.setStroke(Color.BLACK);

		Line lv1C2thinVLine1 = new Line(150, 495, 150, 205);

		Line lv1C2thinHLine2 = new Line(155, 200, 170, 200);

		Arc lv1C2thinArc2 = new Arc(155, 205, 5, 5, 180, -90);
		lv1C2thinArc2.setType(ArcType.OPEN);
		lv1C2thinArc2.setFill(null);
		lv1C2thinArc2.setStroke(Color.BLACK);

		Line lv1C2thickVLine1 = new Line(170, 185, 170, 215);
		lv1C2thickVLine1.setStrokeWidth(5);
		lv1C2thickVLine1.setStrokeLineCap(null);

		Line lv1C2thickHLine1 = new Line(170, 200, 555, 200);
		lv1C2thickHLine1.setStrokeWidth(6);

		Arc lv1C2Arc3 = new Arc(600, 200, 40, 40, 0, 180);
		lv1C2Arc3.setType(ArcType.OPEN);
		lv1C2Arc3.setFill(null);
		lv1C2Arc3.setStroke(Color.BLACK);
		lv1C2Arc3.setStrokeWidth(6);

		Line lv1C2thickHLine2 = new Line(645, 200, 800, 200);
		lv1C2thickHLine2.setStrokeWidth(6);

		//Fires an event
		lv1Circle2.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				//Makes the circle to large and shrink, to notify the user that circle is pressed.
				ScaleTransition lv1st = new ScaleTransition(Duration.millis(150), lv1Circle2);
				lv1st.setByX(0.15);
				lv1st.setByY(0.15);
				lv1st.setCycleCount(2);
				lv1st.setAutoReverse(true);
				lv1st.play();

				Timeline lv1C2AnimationHorizontal = new Timeline(new KeyFrame(Duration.seconds((555 - 170) / 200.0),
						new KeyValue(lv1C2thickHLine1.endXProperty(), 170)));
				lv1C2AnimationHorizontal.play();

				Timeline lv1C2AnimationVerticalUp = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(lv1C2thickVLine1.startYProperty(), 160)));
				lv1C2AnimationVerticalUp.play();

				Timeline lv1C2AnimationVerticalDown = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(lv1C2thickVLine1.endYProperty(), 240)));
				lv1C2AnimationVerticalDown.play();

				Timeline lv1C2AnimationHorizontalEnd = new Timeline(new KeyFrame(Duration.seconds((800 - 170) / 200.0),
						new KeyValue(lv1C2thickHLine2.endXProperty(), 170)));
				lv1C2AnimationHorizontalEnd.play();

				Timeline lv1C2AnimationHorizontalStart = new Timeline(new KeyFrame(
						Duration.seconds((645 - 170) / 200.0), new KeyValue(lv1C2thickHLine2.startXProperty(), 170)));
				lv1C2AnimationHorizontalStart.play();

				Timeline lv1C2AnimationArc = new Timeline(new KeyFrame(Duration.seconds((600 - 130) / 200.0),
						new KeyValue(lv1C2Arc3.centerXProperty(), 130)));
				lv1C2AnimationArc.play();

				Timeline lv1C2ArcDisappear = new Timeline(
						new KeyFrame(Duration.millis(500), new KeyValue(lv1C2Arc3.lengthProperty(), 0)));
				lv1C2ArcDisappear.setDelay(Duration.seconds((547 - 170) / 200.0));
				lv1C2ArcDisappear.play();

				//Animations array
				Timeline[] lv1animations = { lv1C2AnimationHorizontal, lv1C2AnimationVerticalUp,
						lv1C2AnimationVerticalDown, lv1C2AnimationHorizontalEnd, lv1C2AnimationHorizontalStart,
						lv1C2AnimationArc, lv1C2ArcDisappear };

				//In overall, adds all the animations of the level to the arrayList.
				Collections.addAll(lv1AllAnims, lv1animations);

				//If the animation is over, fires an event
				lv1C2AnimationHorizontalEnd.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						lv1Group2.getChildren().addAll(lv1C2thinVLine1, lv1C2thinArc1, lv1C2thinArc2, lv1C2thinHLine1,
								lv1C2thinHLine2, lv1C2thickVLine1, lv1C2thickHLine1, lv1C2thickHLine2, lv1C2Arc3,
								lv1Circle2);

						//Erases the group slowly(discoloration)
						FadeTransition lv1ft = new FadeTransition(Duration.millis(500), lv1Group2);
						lv1ft.setFromValue(1.0);
						lv1ft.setToValue(0.0);
						lv1ft.play();
						lv1ft.setOnFinished(e -> window.setScene(scene3));
						/*Group 2 is the last part of the level, I mean circle2 must be clicked at last.
						 Therefore when fadetransition of group2 is over, it passes to next level*/

					}
				});
			}
		});

		//For collisions
		Timeline lv1CollisionCheck = new Timeline(new KeyFrame(Duration.millis(50), event -> {

			lv1CheckShapeIntersection(lv1C2Arc3, lv1C1thickVLine1); //Calls the method

			if (lv1CollisionDetected) { //If there is a collision

				for (Timeline anim : lv1AllAnims) { //Stops all the animations
					anim.pause();
				}

				level1Pane.getChildren().clear(); //Clears the pane

				//Then to reset the level, it sets all the elements in the pane
				lv1Circle2.setCenterX(850);
				lv1Circle2.setCenterY(500);

				lv1C2thinHLine1.setStartX(815);
				lv1C2thinHLine1.setStartY(500);
				lv1C2thinHLine1.setEndX(155);
				lv1C2thinHLine1.setEndY(500);

				lv1C2thinVLine1.setStartX(150);
				lv1C2thinVLine1.setStartY(495);
				lv1C2thinVLine1.setEndX(150);
				lv1C2thinVLine1.setEndY(205);

				lv1C2thinHLine2.setStartX(155);
				lv1C2thinHLine2.setStartY(200);
				lv1C2thinHLine2.setEndX(170);
				lv1C2thinHLine2.setEndY(200);

				lv1C2thickVLine1.setStartX(170);
				lv1C2thickVLine1.setStartY(185);
				lv1C2thickVLine1.setEndX(170);
				lv1C2thickVLine1.setEndY(215);

				lv1C2thickHLine1.setStartX(170);
				lv1C2thickHLine1.setStartY(200);
				lv1C2thickHLine1.setEndX(555);
				lv1C2thickHLine1.setEndY(200);

				lv1C2thickHLine2.setStartX(645);
				lv1C2thickHLine2.setStartY(200);
				lv1C2thickHLine2.setEndX(800);
				lv1C2thickHLine2.setEndY(200);

				lv1C2thinArc1.setCenterX(155);
				lv1C2thinArc1.setCenterY(495);

				lv1C2thinArc2.setCenterX(155);
				lv1C2thinArc2.setCenterY(205);

				lv1C2Arc3.setCenterX(600);
				lv1C2Arc3.setCenterY(200);

				//Then adds them to pane again.
				level1Pane.getChildren().addAll(lv1Circle1, lv1C1thinVLine1, lv1C1thickHLine1, lv1C1thickVLine1,
						lv1Circle2, lv1C2thinHLine1, lv1C2thinVLine1, lv1C2thinHLine2, lv1C2thickVLine1,
						lv1C2thickHLine1, lv1C2thickHLine2, lv1C2thinArc1, lv1C2thinArc2, lv1C2Arc3, lv1Group1,
						lv1Group2);
			}

		}));
		lv1CollisionCheck.setCycleCount(Timeline.INDEFINITE); //To always checks whether there is a collision or not
		lv1CollisionCheck.play();

		//////////////////////////
		// Create a pane to hold the elements
		level1Pane = new Pane();
		level1Pane.setStyle("-fx-background-color: darkgrey");

		level1Pane.getChildren().addAll(lv1Circle1, lv1C1thinVLine1, lv1C1thickHLine1, lv1C1thickVLine1, lv1Circle2,
				lv1C2thinHLine1, lv1C2thinVLine1, lv1C2thinHLine2, lv1C2thickVLine1, lv1C2thickHLine1, lv1C2thinArc1,
				lv1C2thickHLine2, lv1C2thinArc2, lv1C2Arc3, lv1Group1, lv1Group2);
		scene2 = new Scene(level1Pane, 1000, 700);

		///////////////////////////////////////// LEVEL2/////////////////////////////////////

		Group lv2Group1 = new Group();
		Group lv2Group2 = new Group();
		Group lv2Group3 = new Group();
		Group lv2Group4 = new Group();
		lv2AllAnims = new ArrayList<>();

		// For Circle 1
		Circle lv2Circle1 = new Circle();
		lv2Circle1.setCenterX(150);
		lv2Circle1.setCenterY(550);
		lv2Circle1.setRadius(35);
		lv2Circle1.setStroke(Color.DARKRED);
		lv2Circle1.setFill(Color.BROWN);

		Line lv2C1thinVLine1 = new Line(150, 515, 150, 155);

		Arc lv2C1thinArc1 = new Arc(155, 155, 5, 5, 180, -90);
		lv2C1thinArc1.setType(ArcType.OPEN);
		lv2C1thinArc1.setFill(null);
		lv2C1thinArc1.setStroke(Color.BLACK);

		Line lv2C1thinHLine1 = new Line(155, 150, 170, 150);

		Line lv2C1thickVLine1 = new Line(170, 135, 170, 165);
		lv2C1thickVLine1.setStrokeWidth(5);
		lv2C1thickVLine1.setStrokeLineCap(null);

		Line lv2C1thickHLine1 = new Line(170, 150, 327, 150);
		lv2C1thickHLine1.setStrokeWidth(6);

		lv2Circle1.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition lv2st = new ScaleTransition(Duration.millis(150), lv2Circle1);
				lv2st.setByX(0.15);
				lv2st.setByY(0.15);
				lv2st.setCycleCount(2);
				lv2st.setAutoReverse(true);
				lv2st.play();

				Timeline lv2C1AnimationHorizontal = new Timeline(new KeyFrame(Duration.seconds((327 - 170) / 200.0),
						new KeyValue(lv2C1thickHLine1.endXProperty(), 170)));
				lv2C1AnimationHorizontal.play();

				Timeline lv2C1AnimationVerticalUp = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(lv2C1thickVLine1.startYProperty(), 110)));
				lv2C1AnimationVerticalUp.play();

				Timeline lv2C1AnimationVerticalDown = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(lv2C1thickVLine1.endYProperty(), 190)));
				lv2C1AnimationVerticalDown.play();

				Timeline[] lv2animations = { lv2C1AnimationHorizontal, lv2C1AnimationVerticalUp,
						lv2C1AnimationVerticalDown };
				Collections.addAll(lv2AllAnims, lv2animations);

				lv2C1AnimationHorizontal.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						lv2Group1.getChildren().addAll(lv2C1thinVLine1, lv2C1thinArc1, lv2C1thinHLine1,
								lv2C1thickVLine1, lv2C1thickHLine1, lv2Circle1);

						FadeTransition lv2ft = new FadeTransition(Duration.millis(500), lv2Group1);
						lv2ft.setFromValue(1.0);
						lv2ft.setToValue(0.0);
						lv2ft.play();

					}
				});
			}
		});

		//////////////////////////////////////////////////////////////////////////////////////////////

		// For Circle2

		Circle lv2Circle2 = new Circle();
		lv2Circle2.setCenterX(322);
		lv2Circle2.setCenterY(550);
		lv2Circle2.setRadius(35);
		lv2Circle2.setStroke(Color.DARKRED);
		lv2Circle2.setFill(Color.BROWN);

		Line lv2C2thinVLine1 = new Line(322, 515, 322, 445);

		Line lv2C2thickHLine1 = new Line(307, 445, 337, 445);
		lv2C2thickHLine1.setStrokeWidth(5);
		lv2C2thickHLine1.setStrokeLineCap(null);

		Line lv2C2thickVLine1 = new Line(322, 445, 322, 360);
		lv2C2thickVLine1.setStrokeWidth(6);
		lv2C2thickVLine1.setStrokeLineCap(null);

		Arc lv2C2Arc1 = new Arc(322, 320, 40, 40, 90, 180);
		lv2C2Arc1.setType(ArcType.OPEN);
		lv2C2Arc1.setFill(null);
		lv2C2Arc1.setStroke(Color.BLACK);
		lv2C2Arc1.setStrokeWidth(6);

		Line lv2C2thickVLine2 = new Line(322, 275, 322, 195);
		lv2C2thickVLine2.setStrokeWidth(6);
		lv2C2thickVLine2.setStrokeLineCap(null);

		Arc lv2C2Arc2 = new Arc(322, 150, 40, 40, 90, -180);
		lv2C2Arc2.setType(ArcType.OPEN);
		lv2C2Arc2.setFill(null);
		lv2C2Arc2.setStroke(Color.BLACK);
		lv2C2Arc2.setStrokeWidth(6);

		Line lv2C2thickVLine3 = new Line(322, 108, 322, 60);
		lv2C2thickVLine3.setStrokeWidth(6);

		lv2Circle2.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition lv2st = new ScaleTransition(Duration.millis(150), lv2Circle2);
				lv2st.setByX(0.15);
				lv2st.setByY(0.15);
				lv2st.setCycleCount(2);
				lv2st.setAutoReverse(true);
				lv2st.play();

				Timeline lv2C2AnimationVertical1 = new Timeline(new KeyFrame(Duration.seconds((445 - 360) / 200.0),
						new KeyValue(lv2C2thickVLine1.endYProperty(), 445)));
				lv2C2AnimationVertical1.play();

				Timeline lv2C2AnimationVertical2End = new Timeline(new KeyFrame(Duration.seconds((445 - 195) / 200.0),
						new KeyValue(lv2C2thickVLine2.endYProperty(), 445)));
				lv2C2AnimationVertical2End.play();

				Timeline lv2C2AnimationVertical2Start = new Timeline(new KeyFrame(Duration.seconds((445 - 275) / 200.0),
						new KeyValue(lv2C2thickVLine2.startYProperty(), 445)));
				lv2C2AnimationVertical2Start.play();

				Timeline lv2C2AnimationHorizontalRight = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(lv2C2thickHLine1.startXProperty(), 282)));
				lv2C2AnimationHorizontalRight.play();

				Timeline lv2C2AnimationHorizontalLeft = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(lv2C2thickHLine1.endXProperty(), 362)));
				lv2C2AnimationHorizontalLeft.play();

				Timeline lv2C2AnimationVertical3End = new Timeline(new KeyFrame(Duration.seconds((445 - 60) / 200.0),
						new KeyValue(lv2C2thickVLine3.endYProperty(), 445)));
				lv2C2AnimationVertical3End.play();

				Timeline lv2C2AnimationVertical3Start = new Timeline(new KeyFrame(Duration.seconds((445 - 108) / 200.0),
						new KeyValue(lv2C2thickVLine3.startYProperty(), 445)));
				lv2C2AnimationVertical3Start.play();

				Timeline lv2C2AnimationArc1 = new Timeline(new KeyFrame(Duration.seconds((485 - 320) / 200.0),
						new KeyValue(lv2C2Arc1.centerYProperty(), 485)));
				lv2C2AnimationArc1.play();

				Timeline lv2C2AnimationArc2 = new Timeline(new KeyFrame(Duration.seconds((485 - 150) / 200.0),
						new KeyValue(lv2C2Arc2.centerYProperty(), 485)));
				lv2C2AnimationArc2.play();

				Timeline lv2C2Arc1Disappear = new Timeline(
						new KeyFrame(Duration.millis(500), new KeyValue(lv2C2Arc1.lengthProperty(), 0)));
				lv2C2Arc1Disappear.setDelay(Duration.seconds((430 - 360) / 200.0));
				lv2C2Arc1Disappear.play();

				Timeline lv2C2Arc2Disappear = new Timeline(
						new KeyFrame(Duration.millis(500), new KeyValue(lv2C2Arc2.lengthProperty(), 0)));
				lv2C2Arc2Disappear.setDelay(Duration.seconds((430 - 190) / 200.0));
				lv2C2Arc2Disappear.play();

				Timeline[] lv2animations = { lv2C2AnimationVertical1, lv2C2AnimationVertical2End,
						lv2C2AnimationVertical2Start, lv2C2AnimationVertical3End, lv2C2AnimationVertical3Start,
						lv2C2AnimationArc1, lv2C2AnimationArc2, lv2C2Arc1Disappear, lv2C2Arc2Disappear };
				Collections.addAll(lv2AllAnims, lv2animations);

				lv2C2AnimationVertical3End.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						lv2Group2.getChildren().addAll(lv2C2thinVLine1, lv2C2Arc1, lv2C2Arc2, lv2C2thickHLine1,
								lv2C2thickVLine1, lv2C2thickVLine2, lv2C2thickVLine3, lv2Circle2);

						FadeTransition lv2ft = new FadeTransition(Duration.millis(500), lv2Group2);
						lv2ft.setFromValue(1.0);
						lv2ft.setToValue(0.0);
						lv2ft.play();
						lv2ft.setOnFinished(e -> window.setScene(scene4));
						//Circle2 must be clicked at last. Therefore, when the fadetransition of group2 is over
						//it passes to next level

					}
				});
			}
		});

		//////////////////////////////////////////////////////////////////////////////////////////

		// For Circle3

		Circle lv2Circle3 = new Circle();
		lv2Circle3.setCenterX(450);
		lv2Circle3.setCenterY(550);
		lv2Circle3.setRadius(35);
		lv2Circle3.setStroke(Color.DARKRED);
		lv2Circle3.setFill(Color.BROWN);

		Line lv2C3thinVLine1 = new Line(450, 515, 450, 445);

		Line lv2C3thickHLine1 = new Line(435, 445, 465, 445);
		lv2C3thickHLine1.setStrokeWidth(5);
		lv2C3thickHLine1.setStrokeLineCap(null);

		Line lv2C3thickVLine1 = new Line(450, 445, 450, 315);
		lv2C3thickVLine1.setStrokeWidth(6);

		lv2Circle3.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition lv2st = new ScaleTransition(Duration.millis(150), lv2Circle3);
				lv2st.setByX(0.15);
				lv2st.setByY(0.15);
				lv2st.setCycleCount(2);
				lv2st.setAutoReverse(true);
				lv2st.play();

				Timeline lv2C3AnimationVertical = new Timeline(new KeyFrame(Duration.seconds((445 - 360) / 200.0),
						new KeyValue(lv2C3thickVLine1.endYProperty(), 445)));
				lv2C3AnimationVertical.play();

				Timeline lv2C3AnimationHorizontalRight = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(lv2C3thickHLine1.startXProperty(), 410)));
				lv2C3AnimationHorizontalRight.play();

				Timeline lv2C3AnimationHorizontalLeft = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(lv2C3thickHLine1.endXProperty(), 490)));
				lv2C3AnimationHorizontalLeft.play();

				Timeline[] lv2animations = { lv2C3AnimationVertical, lv2C3AnimationHorizontalRight,
						lv2C3AnimationHorizontalLeft };
				Collections.addAll(lv2AllAnims, lv2animations);

				lv2C3AnimationVertical.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						lv2Group3.getChildren().addAll(lv2C3thickVLine1, lv2C3thickHLine1, lv2C3thinVLine1, lv2Circle3);
						FadeTransition lv2ft = new FadeTransition(Duration.millis(500), lv2Group3);
						lv2ft.setFromValue(1.0);
						lv2ft.setToValue(0.0);
						lv2ft.play();

					}
				});
			}
		});

		///////////////////////////////////////////////////////////////////////////////////////////////

		// For Circle4

		Circle lv2Circle4 = new Circle();
		lv2Circle4.setCenterX(850);
		lv2Circle4.setCenterY(550);
		lv2Circle4.setRadius(35);
		lv2Circle4.setStroke(Color.DARKRED);
		lv2Circle4.setFill(Color.BROWN);

		Line lv2C4thinVLine1 = new Line(850, 515, 850, 325);

		Arc lv2C4thinArc1 = new Arc(845, 325, 5, 5, 0, 90);
		lv2C4thinArc1.setType(ArcType.OPEN);
		lv2C4thinArc1.setFill(null);
		lv2C4thinArc1.setStroke(Color.BLACK);

		Line lv2C4thinHLine1 = new Line(845, 320, 830, 320);

		Line lv2C4thickVLine1 = new Line(830, 305, 830, 335);
		lv2C4thickVLine1.setStrokeWidth(5);
		lv2C4thickVLine1.setStrokeLineCap(null);

		Line lv2C4thickHLine1 = new Line(830, 320, 494, 320);
		lv2C4thickHLine1.setStrokeWidth(6);

		Arc lv2C4Arc2 = new Arc(450, 320, 40, 40, 180, -180);
		lv2C4Arc2.setType(ArcType.OPEN);
		lv2C4Arc2.setFill(null);
		lv2C4Arc2.setStroke(Color.BLACK);
		lv2C4Arc2.setStrokeWidth(6);

		Line lv2C4thickHLine2 = new Line(404, 320, 314, 320);
		lv2C4thickHLine2.setStrokeWidth(6);

		lv2Circle4.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition lv2st = new ScaleTransition(Duration.millis(150), lv2Circle4);
				lv2st.setByX(0.15);
				lv2st.setByY(0.15);
				lv2st.setCycleCount(2);
				lv2st.setAutoReverse(true);
				lv2st.play();

				Timeline lv2C4AnimationHorizontal = new Timeline(new KeyFrame(Duration.seconds((830 - 494) / 200.0),
						new KeyValue(lv2C4thickHLine1.endXProperty(), 830)));
				lv2C4AnimationHorizontal.play();

				Timeline lv2C4AnimationVerticalUp = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(lv2C4thickVLine1.startYProperty(), 280)));
				lv2C4AnimationVerticalUp.play();

				Timeline lv2C4AnimationVerticalDown = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(lv2C4thickVLine1.endYProperty(), 360)));
				lv2C4AnimationVerticalDown.play();

				Timeline lv2C4AnimationArc = new Timeline(new KeyFrame(Duration.seconds((870 - 450) / 200.0),
						new KeyValue(lv2C4Arc2.centerXProperty(), 870)));
				lv2C4AnimationArc.play();

				Timeline lv2C4AnimationHorizontal2End = new Timeline(new KeyFrame(Duration.seconds((830 - 314) / 200.0),
						new KeyValue(lv2C4thickHLine2.endXProperty(), 830)));
				lv2C4AnimationHorizontal2End.play();

				Timeline lv2C4AnimationHorizontal2Start = new Timeline(new KeyFrame(
						Duration.seconds((830 - 404) / 200.0), new KeyValue(lv2C4thickHLine2.startXProperty(), 830)));
				lv2C4AnimationHorizontal2Start.play();

				Timeline[] lv2animations = { lv2C4AnimationHorizontal, lv2C4AnimationVerticalUp,
						lv2C4AnimationVerticalDown, lv2C4AnimationArc, lv2C4AnimationHorizontal2End,
						lv2C4AnimationHorizontal2Start};
				Collections.addAll(lv2AllAnims, lv2animations);

				Timeline lv2C4ArcDisappear = new Timeline(
						new KeyFrame(Duration.millis(500), new KeyValue(lv2C4Arc2.lengthProperty(), 0)));
				lv2C4ArcDisappear.setDelay(Duration.seconds((778 - 450) / 200.0));
				lv2C4ArcDisappear.play();

				Collections.addAll(lv2AllAnims, lv2C4ArcDisappear);

				lv2C4AnimationHorizontal2End.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						lv2Group4.getChildren().addAll(lv2C4thinVLine1, lv2C4thinArc1, lv2C4thinHLine1,
								lv2C4thickVLine1, lv2C4thickHLine1, lv2C4thickHLine2, lv2Circle4);

						FadeTransition lv2ft = new FadeTransition(Duration.millis(500), lv2Group4);
						lv2ft.setFromValue(1.0);
						lv2ft.setToValue(0.0);
						lv2ft.play();

					}
				});
			}
		});

		Timeline lv2C2CollisionCheck = new Timeline(new KeyFrame(Duration.millis(50), event -> {

			//There are multiple collision possibilities in this level.

			lv2CheckShapeIntersection(lv2C2Arc2, lv2C1thickHLine1);
			if (lv2CollisionDetected) {

				for (Timeline anim : lv2AllAnims) {
					anim.pause();
				}

				level2Pane.getChildren().clear();

				lv2C1thinVLine1.setStartX(150);
				lv2C1thinVLine1.setStartY(515);
				lv2C1thinVLine1.setEndX(150);
				lv2C1thinVLine1.setEndY(155);

				lv2C1thinHLine1.setStartX(155);
				lv2C1thinHLine1.setStartY(150);
				lv2C1thinHLine1.setEndX(170);
				lv2C1thinHLine1.setEndY(150);

				lv2C1thickVLine1.setStartX(170);
				lv2C1thickVLine1.setStartY(135);
				lv2C1thickVLine1.setEndX(170);
				lv2C1thickVLine1.setEndY(165);

				lv2C1thickHLine1.setStartX(170);
				lv2C1thickHLine1.setStartY(150);
				lv2C1thickHLine1.setEndX(327);
				lv2C1thickHLine1.setEndY(150);

				lv2C1thinArc1.setCenterX(155);
				lv2C1thinArc1.setCenterY(155);

				////

				lv2Circle2.setCenterX(322);
				lv2Circle2.setCenterY(550);

				lv2C2thinVLine1.setStartX(322);
				lv2C2thinVLine1.setStartY(515);
				lv2C2thinVLine1.setEndX(322);
				lv2C2thinVLine1.setEndY(445);

				lv2C2thickHLine1.setStartX(307);
				lv2C2thickHLine1.setStartY(445);
				lv2C2thickHLine1.setEndX(337);
				lv2C2thickHLine1.setEndY(445);

				lv2C2thickVLine1.setStartX(322);
				lv2C2thickVLine1.setStartY(445);
				lv2C2thickVLine1.setEndX(322);
				lv2C2thickVLine1.setEndY(360);

				lv2C2Arc1.setCenterX(322);
				lv2C2Arc1.setCenterY(320);

				lv2C2thickVLine2.setStartX(322);
				lv2C2thickVLine2.setStartY(275);
				lv2C2thickVLine2.setEndX(322);
				lv2C2thickVLine2.setEndY(195);

				lv2C2Arc2.setCenterX(322);
				lv2C2Arc2.setCenterY(150);

				lv2C2thickVLine3.setStartX(322);
				lv2C2thickVLine3.setStartY(108);
				lv2C2thickVLine3.setEndX(322);
				lv2C2thickVLine3.setEndY(60);

				///

				lv2Circle3.setCenterX(450);
				lv2Circle3.setCenterY(550);

				lv2C3thinVLine1.setStartX(450);
				lv2C3thinVLine1.setStartY(515);
				lv2C3thinVLine1.setEndX(450);
				lv2C3thinVLine1.setEndY(445);

				lv2C3thickHLine1.setStartX(435);
				lv2C3thickHLine1.setStartY(445);
				lv2C3thickHLine1.setEndX(465);
				lv2C3thickHLine1.setEndY(445);

				lv2C3thickVLine1.setStartX(450);
				lv2C3thickVLine1.setStartY(445);
				lv2C3thickVLine1.setEndX(450);
				lv2C3thickVLine1.setEndY(315);

				////

				lv2Circle4.setCenterX(850);
				lv2Circle4.setCenterY(550);

				lv2C4thinVLine1.setStartX(850);
				lv2C4thinVLine1.setStartY(515);
				lv2C4thinVLine1.setEndX(850);
				lv2C4thinVLine1.setEndY(325);

				lv2C4thinArc1.setCenterX(845);
				lv2C4thinArc1.setCenterY(325);

				lv2C4thinHLine1.setStartX(845);
				lv2C4thinHLine1.setStartY(320);
				lv2C4thinHLine1.setEndX(830);
				lv2C4thinHLine1.setEndY(320);

				lv2C4thickVLine1.setStartX(830);
				lv2C4thickVLine1.setStartY(305);
				lv2C4thickVLine1.setEndX(830);
				lv2C4thickVLine1.setEndY(335);

				lv2C4thickHLine1.setStartX(830);
				lv2C4thickHLine1.setStartY(320);
				lv2C4thickHLine1.setEndX(494);
				lv2C4thickHLine1.setEndY(320);

				lv2C4Arc2.setCenterX(450);
				lv2C4Arc2.setCenterY(320);

				lv2C4thickHLine2.setStartX(404);
				lv2C4thickHLine2.setStartY(320);
				lv2C4thickHLine2.setEndX(314);
				lv2C4thickHLine2.setEndY(320);

				level2Pane.getChildren().addAll(lv2Circle1, lv2Circle2, lv2Circle3, lv2C1thinVLine1, lv2C1thinHLine1,
						lv2C1thickVLine1, lv2C1thickHLine1, lv2C2thinVLine1, lv2C2thickHLine1, lv2C2thickVLine1,
						lv2C2Arc1, lv2C2thickVLine2, lv2C2Arc2, lv2C2thickVLine3, lv2C3thinVLine1, lv2C3thickHLine1,
						lv2C3thickVLine1, lv2Circle4, lv2C4thinVLine1, lv2C4thinArc1, lv2C4thinHLine1, lv2C4thickVLine1,
						lv2C4thickHLine1, lv2C4Arc2, lv2C4thickHLine2, lv2C1thinArc1, lv2Group1, lv2Group2, lv2Group3,
						lv2Group4);
			}

			lv2CheckShapeIntersection(lv2C2Arc1, lv2C4thickHLine2);
			if (lv2CollisionDetected) {

				for (Timeline anim : lv2AllAnims) {
					anim.pause();
				}

				level2Pane.getChildren().clear();

				lv2C1thinVLine1.setStartX(150);
				lv2C1thinVLine1.setStartY(515);
				lv2C1thinVLine1.setEndX(150);
				lv2C1thinVLine1.setEndY(155);

				lv2C1thinHLine1.setStartX(155);
				lv2C1thinHLine1.setStartY(150);
				lv2C1thinHLine1.setEndX(170);
				lv2C1thinHLine1.setEndY(150);

				lv2C1thickVLine1.setStartX(170);
				lv2C1thickVLine1.setStartY(135);
				lv2C1thickVLine1.setEndX(170);
				lv2C1thickVLine1.setEndY(165);

				lv2C1thickHLine1.setStartX(170);
				lv2C1thickHLine1.setStartY(150);
				lv2C1thickHLine1.setEndX(327);
				lv2C1thickHLine1.setEndY(150);

				lv2C1thinArc1.setCenterX(155);
				lv2C1thinArc1.setCenterY(155);

				////

				lv2Circle2.setCenterX(322);
				lv2Circle2.setCenterY(550);

				lv2C2thinVLine1.setStartX(322);
				lv2C2thinVLine1.setStartY(515);
				lv2C2thinVLine1.setEndX(322);
				lv2C2thinVLine1.setEndY(445);

				lv2C2thickHLine1.setStartX(307);
				lv2C2thickHLine1.setStartY(445);
				lv2C2thickHLine1.setEndX(337);
				lv2C2thickHLine1.setEndY(445);

				lv2C2thickVLine1.setStartX(322);
				lv2C2thickVLine1.setStartY(445);
				lv2C2thickVLine1.setEndX(322);
				lv2C2thickVLine1.setEndY(360);

				lv2C2Arc1.setCenterX(322);
				lv2C2Arc1.setCenterY(320);

				lv2C2thickVLine2.setStartX(322);
				lv2C2thickVLine2.setStartY(275);
				lv2C2thickVLine2.setEndX(322);
				lv2C2thickVLine2.setEndY(195);

				lv2C2Arc2.setCenterX(322);
				lv2C2Arc2.setCenterY(150);

				lv2C2thickVLine3.setStartX(322);
				lv2C2thickVLine3.setStartY(108);
				lv2C2thickVLine3.setEndX(322);
				lv2C2thickVLine3.setEndY(60);

				///

				lv2Circle3.setCenterX(450);
				lv2Circle3.setCenterY(550);

				lv2C3thinVLine1.setStartX(450);
				lv2C3thinVLine1.setStartY(515);
				lv2C3thinVLine1.setEndX(450);
				lv2C3thinVLine1.setEndY(445);

				lv2C3thickHLine1.setStartX(435);
				lv2C3thickHLine1.setStartY(445);
				lv2C3thickHLine1.setEndX(465);
				lv2C3thickHLine1.setEndY(445);

				lv2C3thickVLine1.setStartX(450);
				lv2C3thickVLine1.setStartY(445);
				lv2C3thickVLine1.setEndX(450);
				lv2C3thickVLine1.setEndY(315);

				////

				lv2Circle4.setCenterX(850);
				lv2Circle4.setCenterY(550);

				lv2C4thinVLine1.setStartX(850);
				lv2C4thinVLine1.setStartY(515);
				lv2C4thinVLine1.setEndX(850);
				lv2C4thinVLine1.setEndY(325);

				lv2C4thinArc1.setCenterX(845);
				lv2C4thinArc1.setCenterY(325);

				lv2C4thinHLine1.setStartX(845);
				lv2C4thinHLine1.setStartY(320);
				lv2C4thinHLine1.setEndX(830);
				lv2C4thinHLine1.setEndY(320);

				lv2C4thickVLine1.setStartX(830);
				lv2C4thickVLine1.setStartY(305);
				lv2C4thickVLine1.setEndX(830);
				lv2C4thickVLine1.setEndY(335);

				lv2C4thickHLine1.setStartX(830);
				lv2C4thickHLine1.setStartY(320);
				lv2C4thickHLine1.setEndX(494);
				lv2C4thickHLine1.setEndY(320);

				lv2C4Arc2.setCenterX(450);
				lv2C4Arc2.setCenterY(320);

				lv2C4thickHLine2.setStartX(404);
				lv2C4thickHLine2.setStartY(320);
				lv2C4thickHLine2.setEndX(314);
				lv2C4thickHLine2.setEndY(320);

				level2Pane.getChildren().addAll(lv2Circle1, lv2Circle2, lv2Circle3, lv2C1thinVLine1, lv2C1thinHLine1,
						lv2C1thickVLine1, lv2C1thickHLine1, lv2C2thinVLine1, lv2C2thickHLine1, lv2C2thickVLine1,
						lv2C2Arc1, lv2C2thickVLine2, lv2C2Arc2, lv2C2thickVLine3, lv2C3thinVLine1, lv2C3thickHLine1,
						lv2C3thickVLine1, lv2Circle4, lv2C4thinVLine1, lv2C4thinArc1, lv2C4thinHLine1, lv2C4thickVLine1,
						lv2C4thickHLine1, lv2C4Arc2, lv2C4thickHLine2, lv2C1thinArc1, lv2Group1, lv2Group2, lv2Group3,
						lv2Group4);
			}

			lv2CheckShapeIntersection(lv2C4Arc2, lv2C3thickVLine1);
			if (lv2CollisionDetected) {

				for (Timeline anim : lv2AllAnims) {
					anim.pause();
				}

				level2Pane.getChildren().clear();

				lv2C1thinVLine1.setStartX(150);
				lv2C1thinVLine1.setStartY(515);
				lv2C1thinVLine1.setEndX(150);
				lv2C1thinVLine1.setEndY(155);

				lv2C1thinHLine1.setStartX(155);
				lv2C1thinHLine1.setStartY(150);
				lv2C1thinHLine1.setEndX(170);
				lv2C1thinHLine1.setEndY(150);

				lv2C1thickVLine1.setStartX(170);
				lv2C1thickVLine1.setStartY(135);
				lv2C1thickVLine1.setEndX(170);
				lv2C1thickVLine1.setEndY(165);

				lv2C1thickHLine1.setStartX(170);
				lv2C1thickHLine1.setStartY(150);
				lv2C1thickHLine1.setEndX(327);
				lv2C1thickHLine1.setEndY(150);

				lv2C1thinArc1.setCenterX(155);
				lv2C1thinArc1.setCenterY(155);

				////

				lv2Circle2.setCenterX(322);
				lv2Circle2.setCenterY(550);

				lv2C2thinVLine1.setStartX(322);
				lv2C2thinVLine1.setStartY(515);
				lv2C2thinVLine1.setEndX(322);
				lv2C2thinVLine1.setEndY(445);

				lv2C2thickHLine1.setStartX(307);
				lv2C2thickHLine1.setStartY(445);
				lv2C2thickHLine1.setEndX(337);
				lv2C2thickHLine1.setEndY(445);

				lv2C2thickVLine1.setStartX(322);
				lv2C2thickVLine1.setStartY(445);
				lv2C2thickVLine1.setEndX(322);
				lv2C2thickVLine1.setEndY(360);

				lv2C2Arc1.setCenterX(322);
				lv2C2Arc1.setCenterY(320);

				lv2C2thickVLine2.setStartX(322);
				lv2C2thickVLine2.setStartY(275);
				lv2C2thickVLine2.setEndX(322);
				lv2C2thickVLine2.setEndY(195);

				lv2C2Arc2.setCenterX(322);
				lv2C2Arc2.setCenterY(150);

				lv2C2thickVLine3.setStartX(322);
				lv2C2thickVLine3.setStartY(108);
				lv2C2thickVLine3.setEndX(322);
				lv2C2thickVLine3.setEndY(60);

				///

				lv2Circle3.setCenterX(450);
				lv2Circle3.setCenterY(550);

				lv2C3thinVLine1.setStartX(450);
				lv2C3thinVLine1.setStartY(515);
				lv2C3thinVLine1.setEndX(450);
				lv2C3thinVLine1.setEndY(445);

				lv2C3thickHLine1.setStartX(435);
				lv2C3thickHLine1.setStartY(445);
				lv2C3thickHLine1.setEndX(465);
				lv2C3thickHLine1.setEndY(445);

				lv2C3thickVLine1.setStartX(450);
				lv2C3thickVLine1.setStartY(445);
				lv2C3thickVLine1.setEndX(450);
				lv2C3thickVLine1.setEndY(315);

				////

				lv2Circle4.setCenterX(850);
				lv2Circle4.setCenterY(550);

				lv2C4thinVLine1.setStartX(850);
				lv2C4thinVLine1.setStartY(515);
				lv2C4thinVLine1.setEndX(850);
				lv2C4thinVLine1.setEndY(325);

				lv2C4thinArc1.setCenterX(845);
				lv2C4thinArc1.setCenterY(325);

				lv2C4thinHLine1.setStartX(845);
				lv2C4thinHLine1.setStartY(320);
				lv2C4thinHLine1.setEndX(830);
				lv2C4thinHLine1.setEndY(320);

				lv2C4thickVLine1.setStartX(830);
				lv2C4thickVLine1.setStartY(305);
				lv2C4thickVLine1.setEndX(830);
				lv2C4thickVLine1.setEndY(335);

				lv2C4thickHLine1.setStartX(830);
				lv2C4thickHLine1.setStartY(320);
				lv2C4thickHLine1.setEndX(494);
				lv2C4thickHLine1.setEndY(320);

				lv2C4Arc2.setCenterX(450);
				lv2C4Arc2.setCenterY(320);

				lv2C4thickHLine2.setStartX(404);
				lv2C4thickHLine2.setStartY(320);
				lv2C4thickHLine2.setEndX(314);
				lv2C4thickHLine2.setEndY(320);

				level2Pane.getChildren().addAll(lv2Circle1, lv2Circle2, lv2Circle3, lv2C1thinVLine1, lv2C1thinHLine1,
						lv2C1thickVLine1, lv2C1thickHLine1, lv2C2thinVLine1, lv2C2thickHLine1, lv2C2thickVLine1,
						lv2C2Arc1, lv2C2thickVLine2, lv2C2Arc2, lv2C2thickVLine3, lv2C3thinVLine1, lv2C3thickHLine1,
						lv2C3thickVLine1, lv2Circle4, lv2C4thinVLine1, lv2C4thinArc1, lv2C4thinHLine1, lv2C4thickVLine1,
						lv2C4thickHLine1, lv2C4Arc2, lv2C4thickHLine2, lv2C1thinArc1, lv2Group1, lv2Group2, lv2Group3,
						lv2Group4);
			}

		}));
		lv2C2CollisionCheck.setCycleCount(Timeline.INDEFINITE);
		lv2C2CollisionCheck.play();

		//////////////////////
		level2Pane = new Pane();
		level2Pane.setStyle("-fx-background-color: darkgrey");

		level2Pane.getChildren().addAll(lv2Circle1, lv2Circle2, lv2Circle3, lv2C1thinVLine1, lv2C1thinHLine1,
				lv2C1thickVLine1, lv2C1thickHLine1, lv2C2thinVLine1, lv2C2thickHLine1, lv2C2thickVLine1, lv2C2Arc1,
				lv2C2thickVLine2, lv2C2Arc2, lv2C2thickVLine3, lv2C3thinVLine1, lv2C3thickHLine1, lv2C3thickVLine1,
				lv2Circle4, lv2C4thinVLine1, lv2C4thinArc1, lv2C4thinHLine1, lv2C4thickVLine1, lv2C4thickHLine1,
				lv2C4Arc2, lv2C4thickHLine2, lv2C1thinArc1, lv2Group1, lv2Group2, lv2Group3, lv2Group4);

		scene3 = new Scene(level2Pane, 1000, 700);

		////////////////////////////////////////// LEVEL3///////////////////////////////////

		Group lv3Group1 = new Group();
		Group lv3Group2 = new Group();
		Group lv3Group3 = new Group();
		lv3AllAnims = new ArrayList<>();

		// For Circle1

		Circle lv3Circle1 = new Circle();
		lv3Circle1.setCenterX(150);
		lv3Circle1.setCenterY(425);
		lv3Circle1.setRadius(35);
		lv3Circle1.setStroke(Color.DARKRED);
		lv3Circle1.setFill(Color.BROWN);

		Line lv3C1thinHLine1 = new Line(185, 425, 727, 425);

		Arc lv3C1thinArc1 = new Arc(727, 420, 5, 5, 270, 90);
		lv3C1thinArc1.setType(ArcType.OPEN);
		lv3C1thinArc1.setFill(null);
		lv3C1thinArc1.setStroke(Color.BLACK);

		Line lv3C1thinVLine1 = new Line(732, 420, 732, 405);

		Line lv3C1thickHLine1 = new Line(717, 405, 747, 405);
		lv3C1thickHLine1.setStrokeWidth(6);
		lv3C1thickHLine1.setStrokeLineCap(null);

		Line lv3C1thickVLine1 = new Line(732, 405, 732, 305);
		lv3C1thickVLine1.setStrokeWidth(6);

		Arc lv3C1Arc2 = new Arc(732, 260, 40, 40, 90, -180);
		lv3C1Arc2.setType(ArcType.OPEN);
		lv3C1Arc2.setFill(null);
		lv3C1Arc2.setStroke(Color.BLACK);
		lv3C1Arc2.setStrokeWidth(6);

		Line lv3C1thickVLine2 = new Line(732, 220, 732, 122);
		lv3C1thickVLine2.setStrokeWidth(6);

		lv3Circle1.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition lv3st = new ScaleTransition(Duration.millis(150), lv3Circle1);
				lv3st.setByX(0.15);
				lv3st.setByY(0.15);
				lv3st.setCycleCount(2);
				lv3st.setAutoReverse(true);
				lv3st.play();

				Timeline lv3C1AnimationVertical = new Timeline(new KeyFrame(Duration.seconds((405 - 305) / 200.0),
						new KeyValue(lv3C1thickVLine1.endYProperty(), 405)));
				lv3C1AnimationVertical.play();

				Timeline lv3C1AnimationHorizontalRight = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(lv3C1thickHLine1.startXProperty(), 692)));
				lv3C1AnimationHorizontalRight.play();

				Timeline lv3C1AnimationHorizontalLeft = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(lv3C1thickHLine1.endXProperty(), 772)));
				lv3C1AnimationHorizontalLeft.play();

				Timeline lv3C1AnimationVerticalEnd = new Timeline(new KeyFrame(Duration.seconds((405 - 122) / 200.0),
						new KeyValue(lv3C1thickVLine2.endYProperty(), 405)));
				lv3C1AnimationVerticalEnd.play();

				Timeline lv3C1AnimationVerticalStart = new Timeline(new KeyFrame(Duration.seconds((405 - 220) / 200.0),
						new KeyValue(lv3C1thickVLine2.startYProperty(), 405)));
				lv3C1AnimationVerticalStart.play();

				Timeline lv3C1AnimationArc = new Timeline(new KeyFrame(Duration.seconds((445 - 260) / 200.0),
						new KeyValue(lv3C1Arc2.centerYProperty(), 445)));
				lv3C1AnimationArc.play();

				Timeline lv3C1ArcDisappear = new Timeline(
						new KeyFrame(Duration.millis(500), new KeyValue(lv3C1Arc2.lengthProperty(), 0)));
				lv3C1ArcDisappear.setDelay(Duration.seconds((392 - 300) / 200.0));
				lv3C1ArcDisappear.play();

				Timeline[] lv3animations = { lv3C1AnimationVertical, lv3C1AnimationHorizontalRight,
						lv3C1AnimationHorizontalLeft, lv3C1AnimationVerticalEnd, lv3C1AnimationVerticalStart,
						lv3C1AnimationArc, lv3C1ArcDisappear };
				Collections.addAll(lv3AllAnims, lv3animations);

				lv3C1AnimationVerticalEnd.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						lv3Group1.getChildren().addAll(lv3C1thinHLine1, lv3C1thinArc1, lv3C1thinVLine1,
								lv3C1thickHLine1, lv3C1thickVLine1, lv3C1Arc2, lv3C1thickVLine2, lv3Circle1);

						FadeTransition lv3ft = new FadeTransition(Duration.millis(500), lv3Group1);
						lv3ft.setFromValue(1.0);
						lv3ft.setToValue(0.0);
						lv3ft.play();

					}
				});
			}
		});

		///////////////////////////////////////////////////////////////////////////////////////////////

		// For Circle2

		Circle lv3Circle2 = new Circle();
		lv3Circle2.setCenterX(265);
		lv3Circle2.setCenterY(490);
		lv3Circle2.setRadius(35);
		lv3Circle2.setStroke(Color.DARKRED);
		lv3Circle2.setFill(Color.BROWN);

		Line lv3C2thinHLine1 = new Line(300, 490, 510, 490);

		Arc lv3C2thinArc1 = new Arc(510, 485, 5, 5, 270, 90);
		lv3C2thinArc1.setType(ArcType.OPEN);
		lv3C2thinArc1.setFill(null);
		lv3C2thinArc1.setStroke(Color.BLACK);

		Line lv3C2thinVLine1 = new Line(515, 485, 515, 265);

		Arc lv3C2thinArc2 = new Arc(520, 265, 5, 5, 180, -90);
		lv3C2thinArc2.setType(ArcType.OPEN);
		lv3C2thinArc2.setFill(null);
		lv3C2thinArc2.setStroke(Color.BLACK);

		Line lv3C2thinHLine2 = new Line(520, 260, 535, 260);

		Line lv3C2thickVLine1 = new Line(535, 245, 535, 275);
		lv3C2thickVLine1.setStrokeWidth(6);
		lv3C2thickVLine1.setStrokeLineCap(null);

		Line lv3C2thickHLine1 = new Line(535, 260, 737, 260);
		lv3C2thickHLine1.setStrokeWidth(6);

		lv3Circle2.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition lv3st = new ScaleTransition(Duration.millis(150), lv3Circle2);
				lv3st.setByX(0.15);
				lv3st.setByY(0.15);
				lv3st.setCycleCount(2);
				lv3st.setAutoReverse(true);
				lv3st.play();

				Timeline lv3C2AnimationHorizontal = new Timeline(new KeyFrame(Duration.seconds((737 - 535) / 200.0),
						new KeyValue(lv3C2thickHLine1.endXProperty(), 535)));
				lv3C2AnimationHorizontal.play();

				Timeline lv3C2AnimationVerticalUp = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(lv3C2thickVLine1.startYProperty(), 220)));
				lv3C2AnimationVerticalUp.play();

				Timeline lv3C2AnimationVerticalDown = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(lv3C2thickVLine1.endYProperty(), 300)));
				lv3C2AnimationVerticalDown.play();

				Timeline[] lv3animations = { lv3C2AnimationHorizontal, lv3C2AnimationVerticalUp,
						lv3C2AnimationVerticalDown, };
				Collections.addAll(lv3AllAnims, lv3animations);

				lv3C2AnimationHorizontal.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						lv3Group2.getChildren().addAll(lv3C2thinVLine1, lv3C2thinArc1, lv3C2thinArc2, lv3C2thinHLine1,
								lv3C2thinHLine2, lv3C2thickVLine1, lv3C2thickHLine1, lv3Circle2);

						FadeTransition lv3ft = new FadeTransition(Duration.millis(500), lv3Group2);
						lv3ft.setFromValue(1.0);
						lv3ft.setToValue(0.0);
						lv3ft.play();

					}
				});
			}
		});

		////////////////////////////////////////////////////////////////////////////////////////////////

		// For Circle3

		Circle lv3Circle3 = new Circle();
		lv3Circle3.setCenterX(400);
		lv3Circle3.setCenterY(575);
		lv3Circle3.setRadius(35);
		lv3Circle3.setStroke(Color.DARKRED);
		lv3Circle3.setFill(Color.BROWN);

		Line lv3C3thinVLine1 = new Line(400, 540, 400, 132);

		Arc lv3C3thinArc1 = new Arc(405, 132, 5, 5, 180, -90);
		lv3C3thinArc1.setType(ArcType.OPEN);
		lv3C3thinArc1.setFill(null);
		lv3C3thinArc1.setStroke(Color.BLACK);

		Line lv3C3thinHLine1 = new Line(405, 127, 420, 127);

		Line lv3C3thickVLine1 = new Line(420, 112, 420, 142);
		lv3C3thickVLine1.setStrokeWidth(6);
		lv3C3thickVLine1.setStrokeLineCap(null);

		Line lv3C3thickHLine1 = new Line(420, 127, 690, 127);
		lv3C3thickHLine1.setStrokeWidth(6);

		Arc lv3C3Arc2 = new Arc(732, 127, 40, 40, 0, 180);
		lv3C3Arc2.setType(ArcType.OPEN);
		lv3C3Arc2.setFill(null);
		lv3C3Arc2.setStroke(Color.BLACK);
		lv3C3Arc2.setStrokeWidth(6);

		Line lv3C3thickHLine2 = new Line(772, 127, 850, 127);
		lv3C3thickHLine2.setStrokeWidth(6);

		lv3Circle3.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition lv3st = new ScaleTransition(Duration.millis(150), lv3Circle3);
				lv3st.setByX(0.15);
				lv3st.setByY(0.15);
				lv3st.setCycleCount(2);
				lv3st.setAutoReverse(true);
				lv3st.play();

				Timeline lv3C3AnimationHorizontal = new Timeline(new KeyFrame(Duration.seconds((690 - 420) / 200.0),
						new KeyValue(lv3C3thickHLine1.endXProperty(), 420)));
				lv3C3AnimationHorizontal.play();

				Timeline lv3C3AnimationVerticalUp = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(lv3C3thickVLine1.startYProperty(), 87)));
				lv3C3AnimationVerticalUp.play();

				Timeline lv3C3AnimationVerticalDown = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(lv3C3thickVLine1.endYProperty(), 167)));
				lv3C3AnimationVerticalDown.play();

				Timeline lv3C3AnimationHorizontalEnd = new Timeline(new KeyFrame(Duration.seconds((850 - 420) / 200.0),
						new KeyValue(lv3C3thickHLine2.endXProperty(), 420)));
				lv3C3AnimationHorizontalEnd.play();

				Timeline lv3C3AnimationHorizontalStart = new Timeline(new KeyFrame(
						Duration.seconds((772 - 420) / 200.0), new KeyValue(lv3C3thickHLine2.startXProperty(), 420)));
				lv3C3AnimationHorizontalStart.play();

				Timeline lv3C3AnimationArc = new Timeline(new KeyFrame(Duration.seconds((732 - 380) / 200.0),
						new KeyValue(lv3C3Arc2.centerXProperty(), 380)));
				lv3C3AnimationArc.play();

				Timeline lv3C3ArcDisappear = new Timeline(
						new KeyFrame(Duration.millis(500), new KeyValue(lv3C3Arc2.lengthProperty(), 0)));
				lv3C3ArcDisappear.setDelay(Duration.seconds((679 - 420) / 200.0));
				lv3C3ArcDisappear.play();

				Timeline[] lv3animations = { lv3C3AnimationHorizontal, lv3C3AnimationVerticalUp,
						lv3C3AnimationVerticalDown, lv3C3AnimationHorizontalEnd, lv3C3AnimationHorizontalStart,
						lv3C3AnimationArc, lv3C3ArcDisappear };
				Collections.addAll(lv3AllAnims, lv3animations);

				lv3C3AnimationHorizontalEnd.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						lv3Group3.getChildren().addAll(lv3C3thinVLine1, lv3C3thinArc1, lv3C3thinHLine1,
								lv3C3thickVLine1, lv3C3thickHLine1, lv3C3thickHLine2, lv3C3Arc2, lv3Circle3);

						FadeTransition lv3ft = new FadeTransition(Duration.millis(500), lv3Group3);
						lv3ft.setFromValue(1.0);
						lv3ft.setToValue(0.0);
						lv3ft.play();
						lv3ft.setOnFinished(e -> window.setScene(scene5));
						//Circle3 must be clicked at last, so when the fadetransition of the group3 is over
						//it passes to next level

					}
				});
			}
		});

		Timeline lv3CollisionCheck = new Timeline(new KeyFrame(Duration.millis(50), event -> {

			//Multiple collision possibilites

			lv3CheckShapeIntersection(lv3C1Arc2, lv3C2thickHLine1);
			if (lv3CollisionDetected) {

				for (Timeline anim : lv3AllAnims) {
					anim.pause();
				}

				lv3Circle1.setCenterX(150);
				lv3Circle1.setCenterY(425);

				lv3C1thinHLine1.setStartX(185);
				lv3C1thinHLine1.setStartY(425);
				lv3C1thinHLine1.setEndX(727);
				lv3C1thinHLine1.setEndY(425);

				lv3C1thinArc1.setCenterX(727);
				lv3C1thinArc1.setCenterY(420);

				lv3C1thinVLine1.setStartX(732);
				lv3C1thinVLine1.setStartY(420);
				lv3C1thinVLine1.setEndX(732);
				lv3C1thinVLine1.setEndY(405);

				lv3C1thickHLine1.setStartX(717);
				lv3C1thickHLine1.setStartY(405);
				lv3C1thickHLine1.setEndX(747);
				lv3C1thickHLine1.setEndY(405);

				lv3C1thickVLine1.setStartX(732);
				lv3C1thickVLine1.setStartY(405);
				lv3C1thickVLine1.setEndX(732);
				lv3C1thickVLine1.setEndY(305);

				lv3C1thickVLine2.setStartX(732);
				lv3C1thickVLine2.setStartY(220);
				lv3C1thickVLine2.setEndX(732);
				lv3C1thickVLine2.setEndY(122);

				lv3C1Arc2.setCenterX(732);
				lv3C1Arc2.setCenterY(260);

				//////

				lv3Circle2.setCenterX(265);
				lv3Circle2.setCenterY(490);

				lv3C2thinHLine1.setStartX(300);
				lv3C2thinHLine1.setStartY(490);
				lv3C2thinHLine1.setEndX(510);
				lv3C2thinHLine1.setEndY(490);

				lv3C2thinArc1.setCenterX(510);
				lv3C2thinArc1.setCenterY(485);

				lv3C2thinVLine1.setStartX(515);
				lv3C2thinVLine1.setStartY(485);
				lv3C2thinVLine1.setEndX(515);
				lv3C2thinVLine1.setEndY(265);

				lv3C2thinArc2.setCenterX(520);
				lv3C2thinArc2.setCenterY(265);

				lv3C2thinHLine2.setStartX(520);
				lv3C2thinHLine2.setStartY(260);
				lv3C2thinHLine2.setEndX(535);
				lv3C2thinHLine2.setEndY(260);

				lv3C2thickVLine1.setStartX(535);
				lv3C2thickVLine1.setStartY(245);
				lv3C2thickVLine1.setEndX(535);
				lv3C2thickVLine1.setEndY(275);

				lv3C2thickHLine1.setStartX(535);
				lv3C2thickHLine1.setStartY(260);
				lv3C2thickHLine1.setEndX(737);
				lv3C2thickHLine1.setEndY(260);

				////

				lv3Circle3.setCenterX(400);
				lv3Circle3.setCenterY(575);

				lv3C3thinVLine1.setStartX(400);
				lv3C3thinVLine1.setStartY(540);
				lv3C3thinVLine1.setEndX(400);
				lv3C3thinVLine1.setEndY(132);

				lv3C3thinArc1.setCenterX(405);
				lv3C3thinArc1.setCenterY(132);

				lv3C3thinHLine1.setStartX(405);
				lv3C3thinHLine1.setStartY(127);
				lv3C3thinHLine1.setEndX(420);
				lv3C3thinHLine1.setEndY(127);

				lv3C3thickVLine1.setStartX(420);
				lv3C3thickVLine1.setStartY(112);
				lv3C3thickVLine1.setEndX(420);
				lv3C3thickVLine1.setEndY(142);

				lv3C3thickHLine1.setStartX(420);
				lv3C3thickHLine1.setStartY(127);
				lv3C3thickHLine1.setEndX(690);
				lv3C3thickHLine1.setEndY(127);

				lv3C3Arc2.setCenterX(732);
				lv3C3Arc2.setCenterY(127);

				lv3C3thickHLine2.setStartX(772);
				lv3C3thickHLine2.setStartY(127);
				lv3C3thickHLine2.setEndX(850);
				lv3C3thickHLine2.setEndY(127);

				level3Pane.getChildren().clear();
				level3Pane.getChildren().addAll(lv3Circle1, lv3C1thinHLine1, lv3C1thinArc1, lv3C1thinVLine1,
						lv3C1thickHLine1, lv3C1thickVLine1, lv3C1Arc2, lv3C1thickVLine2, lv3Circle2, lv3C2thinHLine1,
						lv3C2thinArc1, lv3C2thinVLine1, lv3C2thinArc2, lv3C2thinHLine2, lv3C2thickVLine1,
						lv3C2thickHLine1, lv3Circle3, lv3C3thinVLine1, lv3C3thinArc1, lv3C3thinHLine1, lv3C3thickVLine1,
						lv3C3thickHLine1, lv3C3Arc2, lv3C3thickHLine2, lv3Group1, lv3Group2, lv3Group3);
			}

			lv3CheckShapeIntersection(lv3C3Arc2, lv3C1thickVLine2);
			if (lv3CollisionDetected) {

				for (Timeline anim : lv3AllAnims) {
					anim.pause();
				}

				lv3Circle1.setCenterX(150);
				lv3Circle1.setCenterY(425);

				lv3C1thinHLine1.setStartX(185);
				lv3C1thinHLine1.setStartY(425);
				lv3C1thinHLine1.setEndX(727);
				lv3C1thinHLine1.setEndY(425);

				lv3C1thinArc1.setCenterX(727);
				lv3C1thinArc1.setCenterY(420);

				lv3C1thinVLine1.setStartX(732);
				lv3C1thinVLine1.setStartY(420);
				lv3C1thinVLine1.setEndX(732);
				lv3C1thinVLine1.setEndY(405);

				lv3C1thickHLine1.setStartX(717);
				lv3C1thickHLine1.setStartY(405);
				lv3C1thickHLine1.setEndX(747);
				lv3C1thickHLine1.setEndY(405);

				lv3C1thickVLine1.setStartX(732);
				lv3C1thickVLine1.setStartY(405);
				lv3C1thickVLine1.setEndX(732);
				lv3C1thickVLine1.setEndY(305);

				lv3C1thickVLine2.setStartX(732);
				lv3C1thickVLine2.setStartY(220);
				lv3C1thickVLine2.setEndX(732);
				lv3C1thickVLine2.setEndY(122);

				lv3C1Arc2.setCenterX(732);
				lv3C1Arc2.setCenterY(260);

				//////

				lv3Circle2.setCenterX(265);
				lv3Circle2.setCenterY(490);

				lv3C2thinHLine1.setStartX(300);
				lv3C2thinHLine1.setStartY(490);
				lv3C2thinHLine1.setEndX(510);
				lv3C2thinHLine1.setEndY(490);

				lv3C2thinArc1.setCenterX(510);
				lv3C2thinArc1.setCenterY(485);

				lv3C2thinVLine1.setStartX(515);
				lv3C2thinVLine1.setStartY(485);
				lv3C2thinVLine1.setEndX(515);
				lv3C2thinVLine1.setEndY(265);

				lv3C2thinArc2.setCenterX(520);
				lv3C2thinArc2.setCenterY(265);

				lv3C2thinHLine2.setStartX(520);
				lv3C2thinHLine2.setStartY(260);
				lv3C2thinHLine2.setEndX(535);
				lv3C2thinHLine2.setEndY(260);

				lv3C2thickVLine1.setStartX(535);
				lv3C2thickVLine1.setStartY(245);
				lv3C2thickVLine1.setEndX(535);
				lv3C2thickVLine1.setEndY(275);

				lv3C2thickHLine1.setStartX(535);
				lv3C2thickHLine1.setStartY(260);
				lv3C2thickHLine1.setEndX(737);
				lv3C2thickHLine1.setEndY(260);

				////

				lv3Circle3.setCenterX(400);
				lv3Circle3.setCenterY(575);

				lv3C3thinVLine1.setStartX(400);
				lv3C3thinVLine1.setStartY(540);
				lv3C3thinVLine1.setEndX(400);
				lv3C3thinVLine1.setEndY(132);

				lv3C3thinArc1.setCenterX(405);
				lv3C3thinArc1.setCenterY(132);

				lv3C3thinHLine1.setStartX(405);
				lv3C3thinHLine1.setStartY(127);
				lv3C3thinHLine1.setEndX(420);
				lv3C3thinHLine1.setEndY(127);

				lv3C3thickVLine1.setStartX(420);
				lv3C3thickVLine1.setStartY(112);
				lv3C3thickVLine1.setEndX(420);
				lv3C3thickVLine1.setEndY(142);

				lv3C3thickHLine1.setStartX(420);
				lv3C3thickHLine1.setStartY(127);
				lv3C3thickHLine1.setEndX(690);
				lv3C3thickHLine1.setEndY(127);

				lv3C3Arc2.setCenterX(732);
				lv3C3Arc2.setCenterY(127);

				lv3C3thickHLine2.setStartX(772);
				lv3C3thickHLine2.setStartY(127);
				lv3C3thickHLine2.setEndX(850);
				lv3C3thickHLine2.setEndY(127);

				level3Pane.getChildren().clear();
				level3Pane.getChildren().addAll(lv3Circle1, lv3C1thinHLine1, lv3C1thinArc1, lv3C1thinVLine1,
						lv3C1thickHLine1, lv3C1thickVLine1, lv3C1Arc2, lv3C1thickVLine2, lv3Circle2, lv3C2thinHLine1,
						lv3C2thinArc1, lv3C2thinVLine1, lv3C2thinArc2, lv3C2thinHLine2, lv3C2thickVLine1,
						lv3C2thickHLine1, lv3Circle3, lv3C3thinVLine1, lv3C3thinArc1, lv3C3thinHLine1, lv3C3thickVLine1,
						lv3C3thickHLine1, lv3C3Arc2, lv3C3thickHLine2, lv3Group1, lv3Group2, lv3Group3);
			}

		}));
		lv3CollisionCheck.setCycleCount(Timeline.INDEFINITE);
		lv3CollisionCheck.play();

		//////////////////////
		level3Pane = new Pane();
		level3Pane.setStyle("-fx-background-color: darkgrey");
		level3Pane.getChildren().addAll(lv3Circle1, lv3C1thinHLine1, lv3C1thinArc1, lv3C1thinVLine1, lv3C1thickHLine1,
				lv3C1thickVLine1, lv3C1Arc2, lv3C1thickVLine2, lv3Circle2, lv3C2thinHLine1, lv3C2thinArc1,
				lv3C2thinVLine1, lv3C2thinArc2, lv3C2thinHLine2, lv3C2thickVLine1, lv3C2thickHLine1, lv3Circle3,
				lv3C3thinVLine1, lv3C3thinArc1, lv3C3thinHLine1, lv3C3thickVLine1, lv3C3thickHLine1, lv3C3Arc2,
				lv3C3thickHLine2, lv3Group1, lv3Group2, lv3Group3);

		scene4 = new Scene(level3Pane, 1000, 700);

		///////////////////////////////////// LEVEL4/////////////////////////////////////////

		Group lv4Group1 = new Group();
		Group lv4Group2 = new Group();
		lv4AllAnims = new ArrayList<>();

		Arc lv4DiscArc1 = new Arc(350, 400, 40, 40, 0, 360);
		lv4DiscArc1.setType(ArcType.OPEN);
		lv4DiscArc1.setFill(null);
		lv4DiscArc1.setStroke(Color.BLACK);
		lv4DiscArc1.setStrokeWidth(4);

		Line lv4DiscLine1 = new Line(350, 440, 350, 360);
		lv4DiscLine1.setStrokeWidth(3);

		Circle lv4ConnectorCircle1 = new Circle();
		lv4ConnectorCircle1.setCenterX(350);
		lv4ConnectorCircle1.setCenterY(400);
		lv4ConnectorCircle1.setRadius(40);
		lv4ConnectorCircle1.setOpacity(0.0);

		//Event for turning the line the disconnector
		lv4ConnectorCircle1.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				int i = (int) (lv4DiscLine1.getRotate() / 90);

				Timeline lv4DiscLine1Animation = new Timeline(
						new KeyFrame(Duration.millis(500), new KeyValue(lv4DiscLine1.rotateProperty(), (i + 1) * 90)));
				lv4DiscLine1Animation.play();

			}

		});

		////////////////////////////////////////////////////////////////////////////////

		// For Circle1

		Circle lv4Circle1 = new Circle();
		lv4Circle1.setCenterX(175);
		lv4Circle1.setCenterY(400);
		lv4Circle1.setRadius(35);
		lv4Circle1.setStroke(Color.DARKRED);
		lv4Circle1.setFill(Color.BROWN);

		Line lv4C1thinHLine1 = new Line(210, 400, 310, 400);

		Line lv4C1thinHLine2 = new Line(390, 400, 745, 400);

		Arc lv4C1thinArc1 = new Arc(745, 395, 5, 5, 270, 90);
		lv4C1thinArc1.setType(ArcType.OPEN);
		lv4C1thinArc1.setFill(null);
		lv4C1thinArc1.setStroke(Color.BLACK);

		Line lv4C1thinVLine1 = new Line(750, 395, 750, 380);

		Line lv4C1thickHLine1 = new Line(735, 380, 765, 380);
		lv4C1thickHLine1.setStrokeWidth(6);
		lv4C1thickHLine1.setStrokeLineCap(null);

		Line lv4C1thickVLine1 = new Line(750, 380, 750, 170);
		lv4C1thickVLine1.setStrokeWidth(6);

		lv4Circle1.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition lv4st = new ScaleTransition(Duration.millis(150), lv4Circle1);
				lv4st.setByX(0.15);
				lv4st.setByY(0.15);
				lv4st.setCycleCount(2);
				lv4st.setAutoReverse(true);

				if (lv4DiscLine1.getRotate() % 180 == 90) {
					lv4st.play();

					Timeline lv4C1AnimationVertical = new Timeline(new KeyFrame(Duration.seconds((380 - 170) / 200.0),
							new KeyValue(lv4C1thickVLine1.endYProperty(), 380)));
					lv4C1AnimationVertical.play();

					Timeline lv4C1AnimationHorizontalRight = new Timeline(
							new KeyFrame(Duration.millis(150), new KeyValue(lv4C1thickHLine1.startXProperty(), 710)));
					lv4C1AnimationHorizontalRight.play();

					Timeline lv4C1AnimationHorizontalLeft = new Timeline(
							new KeyFrame(Duration.millis(150), new KeyValue(lv4C1thickHLine1.endXProperty(), 790)));
					lv4C1AnimationHorizontalLeft.play();

					Timeline[] animations = { lv4C1AnimationVertical, lv4C1AnimationHorizontalRight,
							lv4C1AnimationHorizontalLeft };
					Collections.addAll(lv4AllAnims, animations);

					lv4C1AnimationVertical.setOnFinished(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {

							lv4Group1.getChildren().addAll(lv4C1thinVLine1, lv4C1thinArc1, lv4C1thinHLine1,
									lv4C1thinHLine2, lv4C1thickVLine1, lv4C1thickHLine1, lv4Circle1);

							FadeTransition lv4ft = new FadeTransition(Duration.millis(500), lv4Group1);
							lv4ft.setFromValue(1.0);
							lv4ft.setToValue(0.0);
							lv4ft.play();

						}
					});
				}

			}
		});

		////////////////////////////////////////////////////////////////////////////////

		// For Circle2

		Circle lv4Circle2 = new Circle();
		lv4Circle2.setCenterX(350);
		lv4Circle2.setCenterY(575);
		lv4Circle2.setRadius(35);
		lv4Circle2.setStroke(Color.DARKRED);
		lv4Circle2.setFill(Color.BROWN);

		Line lv4C2thinVLine1 = new Line(350, 540, 350, 440);

		Line lv4C2thinVLine2 = new Line(350, 360, 350, 180);

		Arc lv4C2thinArc1 = new Arc(355, 180, 5, 5, 180, -90);
		lv4C2thinArc1.setType(ArcType.OPEN);
		lv4C2thinArc1.setFill(null);
		lv4C2thinArc1.setStroke(Color.BLACK);

		Line lv4C2thinHLine1 = new Line(355, 175, 370, 175);

		Line lv4C2thickVLine1 = new Line(370, 160, 370, 190);
		lv4C2thickVLine1.setStrokeWidth(6);
		lv4C2thickVLine1.setStrokeLineCap(null);

		Line lv4C2thickHLine1 = new Line(370, 175, 707, 175);
		lv4C2thickHLine1.setStrokeWidth(6);

		Arc lv4C2Arc2 = new Arc(750, 175, 40, 40, 0, 180);
		lv4C2Arc2.setType(ArcType.OPEN);
		lv4C2Arc2.setFill(null);
		lv4C2Arc2.setStroke(Color.BLACK);
		lv4C2Arc2.setStrokeWidth(6);

		Line lv4C2thickHLine2 = new Line(792, 175, 820, 175);
		lv4C2thickHLine2.setStrokeWidth(6);

		lv4Circle2.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition lv4st = new ScaleTransition(Duration.millis(150), lv4Circle2);
				lv4st.setByX(0.15);
				lv4st.setByY(0.15);
				lv4st.setCycleCount(2);
				lv4st.setAutoReverse(true);

				//In order to move lines, the lines in the disconnector and the connection lines must be in th same direction.
				if (lv4DiscLine1.getRotate() % 180 == 0) {
					lv4st.play();

					Timeline lv4C2AnimationHorizontal = new Timeline(new KeyFrame(Duration.seconds((707 - 370) / 200.0),
							new KeyValue(lv4C2thickHLine1.endXProperty(), 370)));
					lv4C2AnimationHorizontal.play();

					Timeline lv4C2AnimationVerticalUp = new Timeline(
							new KeyFrame(Duration.millis(150), new KeyValue(lv4C2thickVLine1.startYProperty(), 135)));
					lv4C2AnimationVerticalUp.play();

					Timeline lv4C2AnimationVerticalDown = new Timeline(
							new KeyFrame(Duration.millis(150), new KeyValue(lv4C2thickVLine1.endYProperty(), 215)));
					lv4C2AnimationVerticalDown.play();

					Timeline lv4C2AnimationHorizontalEnd = new Timeline(new KeyFrame(
							Duration.seconds((820 - 370) / 200.0), new KeyValue(lv4C2thickHLine2.endXProperty(), 370)));
					lv4C2AnimationHorizontalEnd.play();

					Timeline lv4C2AnimationHorizontalStart = new Timeline(
							new KeyFrame(Duration.seconds((792 - 370) / 200.0),
									new KeyValue(lv4C2thickHLine2.startXProperty(), 370)));
					lv4C2AnimationHorizontalStart.play();

					Timeline lv4C2AnimationArc = new Timeline(new KeyFrame(Duration.seconds((750 - 330) / 200.0),
							new KeyValue(lv4C2Arc2.centerXProperty(), 330)));
					lv4C2AnimationArc.play();

					Timeline lv4C2ArcDisappear = new Timeline(
							new KeyFrame(Duration.millis(500), new KeyValue(lv4C2Arc2.lengthProperty(), 0)));
					lv4C2ArcDisappear.setDelay(Duration.seconds((695 - 370) / 200.0));
					lv4C2ArcDisappear.play();

					Timeline[] lv4animations = { lv4C2AnimationHorizontal, lv4C2AnimationVerticalUp,
							lv4C2AnimationVerticalDown, lv4C2AnimationHorizontalEnd, lv4C2AnimationHorizontalStart,
							lv4C2AnimationArc, lv4C2ArcDisappear };
					Collections.addAll(lv4AllAnims, lv4animations);

					lv4C2AnimationHorizontalEnd.setOnFinished(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {

							lv4Group2.getChildren().addAll(lv4C2thinVLine1, lv4C2thinVLine2, lv4C2thinArc1,
									lv4C2thinHLine1, lv4C2thickVLine1, lv4C2thickHLine1, lv4C2thickHLine2, lv4C2Arc2,
									lv4Circle2, lv4DiscArc1, lv4DiscLine1);

							FadeTransition lv4ft = new FadeTransition(Duration.millis(500), lv4Group2);
							lv4ft.setFromValue(1.0);
							lv4ft.setToValue(0.0);
							lv4ft.play();
							lv4ft.setOnFinished(e -> window.setScene(scene6));
							//Circle2 must be clicked at last, so when the fadetransition of the group2 is over
							//it passes to next level

						}
					});
				}

			}
		});

		Timeline lv4C2CollisionCheck = new Timeline(new KeyFrame(Duration.millis(50), event -> {

			lv4CheckShapeIntersection(lv4C2Arc2, lv4C1thickVLine1);
			if (lv4CollisionDetected) {

				for (Timeline anim : lv4AllAnims) {
					anim.pause();
				}

				level4Pane.getChildren().clear();

				lv4Circle2.setCenterX(350);
				lv4Circle2.setCenterY(575);

				lv4C2thinVLine1.setStartX(350);
				lv4C2thinVLine1.setStartY(540);
				lv4C2thinVLine1.setEndX(350);
				lv4C2thinVLine1.setEndY(440);

				lv4C2thinVLine2.setStartX(350);
				lv4C2thinVLine2.setStartY(360);
				lv4C2thinVLine2.setEndX(350);
				lv4C2thinVLine2.setEndY(180);

				lv4C2thinArc1.setCenterX(355);
				lv4C2thinArc1.setCenterY(180);

				lv4C2thinHLine1.setStartX(355);
				lv4C2thinHLine1.setStartY(175);
				lv4C2thinHLine1.setEndX(370);
				lv4C2thinHLine1.setEndY(175);

				lv4C2thickVLine1.setStartX(370);
				lv4C2thickVLine1.setStartY(160);
				lv4C2thickVLine1.setEndX(370);
				lv4C2thickVLine1.setEndY(190);

				lv4C2thickHLine1.setStartX(370);
				lv4C2thickHLine1.setStartY(175);
				lv4C2thickHLine1.setEndX(707);
				lv4C2thickHLine1.setEndY(175);

				lv4C2Arc2.setCenterX(750);
				lv4C2Arc2.setCenterY(175);

				lv4C2thickHLine2.setStartX(792);
				lv4C2thickHLine2.setStartY(175);
				lv4C2thickHLine2.setEndX(820);
				lv4C2thickHLine2.setEndY(175);
				level4Pane.getChildren().addAll(lv4Circle1, lv4C1thinHLine1, lv4C1thinArc1, lv4C1thinHLine2,
						lv4C1thinVLine1, lv4C1thickHLine1, lv4C1thickVLine1, lv4DiscArc1, lv4DiscLine1,
						lv4ConnectorCircle1, lv4Circle2, lv4C2thinVLine1, lv4C2thinVLine2, lv4C2thinArc1,
						lv4C2thinHLine1, lv4C2thickVLine1, lv4C2thickHLine1, lv4C2Arc2, lv4C2thickHLine2, lv4Group1,
						lv4Group2);
			}

		}));
		lv4C2CollisionCheck.setCycleCount(Timeline.INDEFINITE);
		lv4C2CollisionCheck.play();

		//////////////////////
		level4Pane = new Pane();

		level4Pane.setStyle("-fx-background-color: darkgrey");
		level4Pane.getChildren().addAll(lv4Circle1, lv4C1thinHLine1, lv4C1thinArc1, lv4C1thinHLine2, lv4C1thinVLine1,
				lv4C1thickHLine1, lv4C1thickVLine1, lv4DiscArc1, lv4DiscLine1, lv4ConnectorCircle1, lv4Circle2,
				lv4C2thinVLine1, lv4C2thinVLine2, lv4C2thinArc1, lv4C2thinHLine1, lv4C2thickVLine1, lv4C2thickHLine1,
				lv4C2Arc2, lv4C2thickHLine2, lv4Group1, lv4Group2);

		scene5 = new Scene(level4Pane, 1000, 700);

		/////////////////////////////////////// LEVEL5///////////////////////////////////////

		Group lv5Group1 = new Group();
		Group lv5Group2 = new Group();
		Group lv5Group3 = new Group();
		Group lv5Group4 = new Group();

		lv5AllAnims = new ArrayList<>();

		Arc lv5DiscArc1 = new Arc(350, 465, 40, 40, 0, 360);
		lv5DiscArc1.setType(ArcType.OPEN);
		lv5DiscArc1.setFill(null);
		lv5DiscArc1.setStroke(Color.BLACK);
		lv5DiscArc1.setStrokeWidth(4);

		Line lv5DiscLine1 = new Line(310, 465, 390, 465);
		lv5DiscLine1.setStrokeWidth(3);

		Circle lv5ConnectorCircle1 = new Circle();
		lv5ConnectorCircle1.setCenterX(350);
		lv5ConnectorCircle1.setCenterY(465);
		lv5ConnectorCircle1.setRadius(40);
		lv5ConnectorCircle1.setOpacity(0.0);

		//Event for turning the line in the disconnector
		lv5ConnectorCircle1.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				int i = (int) (lv5DiscLine1.getRotate() / 90);

				Timeline lv5DiscLine1Animation = new Timeline(
						new KeyFrame(Duration.millis(500), new KeyValue(lv5DiscLine1.rotateProperty(), (i + 1) * 90)));
				lv5DiscLine1Animation.play();

			}

		});

		Arc lv5DiscArc2 = new Arc(350, 350, 40, 40, 0, 360);
		lv5DiscArc2.setType(ArcType.OPEN);
		lv5DiscArc2.setFill(null);
		lv5DiscArc2.setStroke(Color.BLACK);
		lv5DiscArc2.setStrokeWidth(4);

		Line lv5DiscLine2 = new Line(350, 310, 350, 390);
		lv5DiscLine2.setStrokeWidth(3);

		Circle lv5ConnectorCircle2 = new Circle();
		lv5ConnectorCircle2.setCenterX(350);
		lv5ConnectorCircle2.setCenterY(350);
		lv5ConnectorCircle2.setRadius(40);
		lv5ConnectorCircle2.setOpacity(0.0);

		//For other disconnector
		lv5ConnectorCircle2.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				int i = (int) (lv5DiscLine2.getRotate() / 90);

				Timeline lv5DiscLine2Animation = new Timeline(
						new KeyFrame(Duration.millis(500), new KeyValue(lv5DiscLine2.rotateProperty(), (i + 1) * 90)));
				lv5DiscLine2Animation.play();
			}

		});

		// For Circle1

		Circle lv5Circle1 = new Circle();
		lv5Circle1.setCenterX(700);
		lv5Circle1.setCenterY(620);
		lv5Circle1.setRadius(35);
		lv5Circle1.setStroke(Color.DARKRED);
		lv5Circle1.setFill(Color.BROWN);

		Line lv5C1thinVLine1 = new Line(700, 620, 700, 180);

		Arc lv5C1thinArc1 = new Arc(695, 180, 5, 5, 0, 90);
		lv5C1thinArc1.setType(ArcType.OPEN);
		lv5C1thinArc1.setFill(null);
		lv5C1thinArc1.setStroke(Color.BLACK);

		Line lv5C1thinHLine1 = new Line(695, 175, 685, 175);

		Line lv5C1thickVLine1 = new Line(685, 160, 685, 190);
		lv5C1thickVLine1.setStrokeWidth(6);
		lv5C1thickVLine1.setStrokeLineCap(null);

		Line lv5C1thickHLine1 = new Line(685, 175, 590, 175);
		lv5C1thickHLine1.setStrokeWidth(6);

		// Animations for Group1

		lv5Circle1.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				// To enlarge and shrink circle when pressed
				ScaleTransition lv5st = new ScaleTransition(Duration.millis(150), lv5Circle1);
				lv5st.setByX(0.15);
				lv5st.setByY(0.15);
				lv5st.setCycleCount(2);
				lv5st.setAutoReverse(true);
				lv5st.play();

				// To shorten line over time
				Timeline lv5C1AnimationHorizontal = new Timeline(new KeyFrame(Duration.seconds((685 - 590) / 200.0),
						new KeyValue(lv5C1thickHLine1.endXProperty(), 685)));
				lv5C1AnimationHorizontal.play();

				Timeline lv5C1AnimationVerticalUp = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(lv5C1thickVLine1.startYProperty(), 135)));
				lv5C1AnimationVerticalUp.play();

				Timeline lv5C1AnimationVerticalDown = new Timeline(
						new KeyFrame(Duration.millis(150), new KeyValue(lv5C1thickVLine1.endYProperty(), 215)));
				lv5C1AnimationVerticalDown.play();

				// Registering animations to allAnims ArrayList to use it in
				// ChackShapeInersection method
				Timeline[] lv5animations = { lv5C1AnimationHorizontal, lv5C1AnimationVerticalUp,
						lv5C1AnimationVerticalDown };
				Collections.addAll(lv5AllAnims, lv5animations);

				lv5C1AnimationHorizontal.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						lv5Group1.getChildren().addAll(lv5C1thinVLine1, lv5C1thinArc1, lv5C1thinHLine1,
								lv5C1thickVLine1, lv5C1thickHLine1, lv5Circle1);

						FadeTransition lv5ft = new FadeTransition(Duration.millis(500), lv5Group1);
						lv5ft.setFromValue(1.0);
						lv5ft.setToValue(0.0);
						lv5ft.play();

					}
				});

			}
		});

		///////////////////////////////////

		// For Circle2

		Circle lv5Circle2 = new Circle();
		lv5Circle2.setCenterX(350);
		lv5Circle2.setCenterY(620);
		lv5Circle2.setRadius(35);
		lv5Circle2.setStroke(Color.DARKRED);
		lv5Circle2.setFill(Color.BROWN);

		Line lv5C2thinVLine1 = new Line(350, 585, 350, 505);
		Line lv5C2thinVLine2 = new Line(350, 425, 350, 390);
		Line lv5C2thinVLine3 = new Line(350, 310, 350, 110);

		Arc lv5C2thinArc1 = new Arc(355, 110, 5, 5, 180, -90);
		lv5C2thinArc1.setType(ArcType.OPEN);
		lv5C2thinArc1.setFill(null);
		lv5C2thinArc1.setStroke(Color.BLACK);

		Line lv5C2thinHLine2 = new Line(355, 105, 365, 105);

		Line lv5C2thickVLine1 = new Line(365, 90, 365, 120);
		lv5C2thickVLine1.setStrokeWidth(6);
		lv5C2thickVLine1.setStrokeLineCap(null);

		Line lv5C2thickHLine1 = new Line(365, 105, 550, 105);
		lv5C2thickHLine1.setStrokeWidth(6);

		Arc lv5C2Arc1 = new Arc(590, 105, 40, 40, 0, 180);
		lv5C2Arc1.setType(ArcType.OPEN);
		lv5C2Arc1.setFill(null);
		lv5C2Arc1.setStroke(Color.BLACK);
		lv5C2Arc1.setStrokeWidth(6);

		Line lv5C2thickHLine2 = new Line(630, 105, 750, 105);
		lv5C2thickHLine2.setStrokeWidth(6);

		// Animations for Group2

		lv5Circle2.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition lv5st = new ScaleTransition(Duration.millis(150), lv5Circle2);
				lv5st.setByX(0.15);
				lv5st.setByY(0.15);
				lv5st.setCycleCount(2);
				lv5st.setAutoReverse(true);

				if (lv5DiscLine1.getRotate() % 180 == 90) {
					if (lv5DiscLine2.getRotate() % 180 == 0) {
						lv5st.play();

						Timeline lv5C2AnimationHorizontal1 = new Timeline(
								new KeyFrame(Duration.seconds((550 - 365) / 200.0),
										new KeyValue(lv5C2thickHLine1.endXProperty(), 365)));
						lv5C2AnimationHorizontal1.play();

						Timeline lv5C2AnimationHorizontal2End = new Timeline(
								new KeyFrame(Duration.seconds((750 - 365) / 200.0),
										new KeyValue(lv5C2thickHLine2.endXProperty(), 365)));
						lv5C2AnimationHorizontal2End.play();

						Timeline lv5C2AnimationHorizontal2Start = new Timeline(
								new KeyFrame(Duration.seconds((630 - 365) / 200.0),
										new KeyValue(lv5C2thickHLine2.startXProperty(), 365)));
						lv5C2AnimationHorizontal2Start.play();

						Timeline lv5C2AnimationVerticalUp = new Timeline(new KeyFrame(Duration.millis(150),
								new KeyValue(lv5C2thickVLine1.startYProperty(), 65)));
						lv5C2AnimationVerticalUp.play();

						Timeline lv5C2AnimationVerticalDown = new Timeline(
								new KeyFrame(Duration.millis(150), new KeyValue(lv5C2thickVLine1.endYProperty(), 145)));
						lv5C2AnimationVerticalDown.play();

						Timeline lv5C2AnimationArc = new Timeline(new KeyFrame(Duration.seconds((590 - 325) / 200.0),
								new KeyValue(lv5C2Arc1.centerXProperty(), 325)));
						lv5C2AnimationArc.play();

						Timeline lv5C2ArcDisappear = new Timeline(
								new KeyFrame(Duration.millis(500), new KeyValue(lv5C2Arc1.lengthProperty(), 0)));
						lv5C2ArcDisappear.setDelay(Duration.seconds((535 - 365) / 200.0));
						lv5C2ArcDisappear.play();

						Timeline[] lv5animations = { lv5C2AnimationHorizontal1, lv5C2AnimationHorizontal2End,
								lv5C2AnimationHorizontal2Start, lv5C2AnimationVerticalUp, lv5C2AnimationVerticalDown,
								lv5C2AnimationArc, lv5C2ArcDisappear };
						Collections.addAll(lv5AllAnims, lv5animations);

						lv5C2AnimationHorizontal2End.setOnFinished(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {

								lv5Group2.getChildren().addAll(lv5C2thinVLine1, lv5C2thinVLine2, lv5C2thinVLine3,
										lv5C2thinArc1, lv5C2thinHLine2, lv5C2thickVLine1, lv5C2thickHLine1, lv5C2Arc1,
										lv5C2thickHLine2, lv5Circle2, lv5DiscArc2, lv5DiscLine2);

								FadeTransition lv5ft = new FadeTransition(Duration.millis(500), lv5Group2);
								lv5ft.setFromValue(1.0);
								lv5ft.setToValue(0.0);
								lv5ft.play();
							}
						});
					}
				}
			}
		});

		// For Circle3

		Circle lv5Circle3 = new Circle();
		lv5Circle3.setCenterX(100);
		lv5Circle3.setCenterY(465);
		lv5Circle3.setRadius(35);
		lv5Circle3.setStroke(Color.DARKRED);
		lv5Circle3.setFill(Color.BROWN);

		Line lv5C3thinHLine1 = new Line(135, 465, 310, 465);
		Line lv5C3thinHLine2 = new Line(390, 465, 740, 465);

		Arc lv5C3thinArc1 = new Arc(740, 460, 5, 5, -90, 90);
		lv5C3thinArc1.setType(ArcType.OPEN);
		lv5C3thinArc1.setFill(null);
		lv5C3thinArc1.setStroke(Color.BLACK);

		Line lv5C3thinVLine1 = new Line(745, 460, 745, 450);

		Line lv5C3thickHLine1 = new Line(730, 450, 760, 450);
		lv5C3thickHLine1.setStrokeWidth(6);
		lv5C3thickHLine1.setStrokeLineCap(null);

		Line lv5C3thickVLine1 = new Line(745, 450, 745, 145);
		lv5C3thickVLine1.setStrokeWidth(6);

		Arc lv5C3Arc1 = new Arc(745, 105, 40, 40, 90, -180);
		lv5C3Arc1.setType(ArcType.OPEN);
		lv5C3Arc1.setFill(null);
		lv5C3Arc1.setStroke(Color.BLACK);
		lv5C3Arc1.setStrokeWidth(6);

		Line lv5C3thickVLine2 = new Line(745, 65, 745, 35);
		lv5C3thickVLine2.setStrokeWidth(6);

		// Animations for Group3

		lv5Circle3.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition lv5st = new ScaleTransition(Duration.millis(150), lv5Circle3);
				lv5st.setByX(0.15);
				lv5st.setByY(0.15);
				lv5st.setCycleCount(2);
				lv5st.setAutoReverse(true);

				if (lv5DiscLine1.getRotate() % 180 == 0) {
					lv5st.play();

					Timeline lv5C3AnimationVertical1 = new Timeline(new KeyFrame(Duration.seconds((450 - 145) / 200.0),
							new KeyValue(lv5C3thickVLine1.endYProperty(), 450)));
					lv5C3AnimationVertical1.play();

					Timeline lv5C3AnimationVertical2End = new Timeline(new KeyFrame(
							Duration.seconds((450 - 35) / 200.0), new KeyValue(lv5C3thickVLine2.endYProperty(), 450)));
					lv5C3AnimationVertical2End.play();

					Timeline lv5C3AnimationVertical2Start = new Timeline(
							new KeyFrame(Duration.seconds((450 - 65) / 200.0),
									new KeyValue(lv5C3thickVLine2.startYProperty(), 450)));
					lv5C3AnimationVertical2Start.play();

					Timeline lv5C3AnimationHorizontalLeft = new Timeline(
							new KeyFrame(Duration.millis(150), new KeyValue(lv5C3thickHLine1.startXProperty(), 705)));
					lv5C3AnimationHorizontalLeft.play();

					Timeline lv5C3AnimationHorizontalRight = new Timeline(
							new KeyFrame(Duration.millis(150), new KeyValue(lv5C3thickHLine1.endXProperty(), 785)));
					lv5C3AnimationHorizontalRight.play();

					Timeline lv5C3AnimationArc = new Timeline(new KeyFrame(Duration.seconds((490 - 105) / 200.0),
							new KeyValue(lv5C3Arc1.centerYProperty(), 490)));
					lv5C3AnimationArc.play();

					Timeline lv5C3ArcDisappear = new Timeline(
							new KeyFrame(Duration.millis(500), new KeyValue(lv5C3Arc1.lengthProperty(), 0)));
					lv5C3ArcDisappear.setDelay(Duration.seconds((435 - 145) / 200.0));
					lv5C3ArcDisappear.play();

					Timeline[] lv5animations = { lv5C3AnimationVertical1, lv5C3AnimationVertical2End,
							lv5C3AnimationVertical2Start, lv5C3AnimationHorizontalLeft, lv5C3AnimationHorizontalRight,
							lv5C3AnimationArc, lv5C3ArcDisappear };
					Collections.addAll(lv5AllAnims, lv5animations);

					lv5C3AnimationVertical2End.setOnFinished(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {

							lv5Group3.getChildren().addAll(lv5C3thinHLine1, lv5C3thinHLine2, lv5C3thinArc1,
									lv5C3thinVLine1, lv5C3thickHLine1, lv5C3thickVLine1, lv5C3Arc1, lv5C3thickVLine2,
									lv5Circle3, lv5DiscArc1, lv5DiscLine1);

							FadeTransition lv5ft = new FadeTransition(Duration.millis(500), lv5Group3);
							lv5ft.setFromValue(1.0);
							lv5ft.setToValue(0.0);
							lv5ft.play();
							lv5ft.setOnFinished(e -> window.close());
							//Circle3 must be clicked at last, so when the fadetransition of the group3 is over
							//the game is completed successfully. Then the stage is closed.
						}
					});
				}
			}
		});

		// For Circle4

		Circle lv5Circle4 = new Circle();
		lv5Circle4.setCenterX(100);
		lv5Circle4.setCenterY(350);
		lv5Circle4.setRadius(35);
		lv5Circle4.setStroke(Color.DARKRED);
		lv5Circle4.setFill(Color.BROWN);

		Line lv5C4thinHLine1 = new Line(135, 350, 310, 350);
		Line lv5C4thinHLine2 = new Line(390, 350, 585, 350);

		Arc lv5C4thinArc1 = new Arc(585, 345, 5, 5, -90, 90);
		lv5C4thinArc1.setType(ArcType.OPEN);
		lv5C4thinArc1.setFill(null);
		lv5C4thinArc1.setStroke(Color.BLACK);

		Line lv5C4thinVLine1 = new Line(590, 345, 590, 335);

		Line lv5C4thickHLine1 = new Line(575, 335, 605, 335);
		lv5C4thickHLine1.setStrokeWidth(6);
		lv5C4thickHLine1.setStrokeLineCap(null);

		Line lv5C4thickVLine1 = new Line(590, 335, 590, 215);
		lv5C4thickVLine1.setStrokeWidth(6);

		Arc lv5C4Arc1 = new Arc(590, 175, 40, 40, 90, 180);
		lv5C4Arc1.setType(ArcType.OPEN);
		lv5C4Arc1.setFill(null);
		lv5C4Arc1.setStroke(Color.BLACK);
		lv5C4Arc1.setStrokeWidth(6);

		Line lv5C4thickVLine2 = new Line(590, 135, 590, 105);
		lv5C4thickVLine2.setStrokeWidth(6);

		// Animations for Group4

		lv5Circle4.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				ScaleTransition lv5st = new ScaleTransition(Duration.millis(150), lv5Circle4);
				lv5st.setByX(0.15);
				lv5st.setByY(0.15);
				lv5st.setCycleCount(2);
				lv5st.setAutoReverse(true);

				if (lv5DiscLine2.getRotate() % 180 == 90) {
					lv5st.play();

					Timeline lv5C4AnimationVertical1 = new Timeline(new KeyFrame(Duration.seconds((335 - 215) / 200.0),
							new KeyValue(lv5C4thickVLine1.endYProperty(), 335)));
					lv5C4AnimationVertical1.play();

					Timeline lv5C4AnimationVertical2End = new Timeline(new KeyFrame(
							Duration.seconds((335 - 105) / 200.0), new KeyValue(lv5C4thickVLine2.endYProperty(), 335)));
					lv5C4AnimationVertical2End.play();

					Timeline lv5C4AnimationVertical2Start = new Timeline(
							new KeyFrame(Duration.seconds((335 - 135) / 200.0),
									new KeyValue(lv5C4thickVLine2.startYProperty(), 335)));
					lv5C4AnimationVertical2Start.play();

					Timeline lv5C4AnimationHorizontalLeft = new Timeline(
							new KeyFrame(Duration.millis(150), new KeyValue(lv5C4thickHLine1.startXProperty(), 550)));
					lv5C4AnimationHorizontalLeft.play();

					Timeline lv5C4AnimationHorizontalRight = new Timeline(
							new KeyFrame(Duration.millis(150), new KeyValue(lv5C4thickHLine1.endXProperty(), 630)));
					lv5C4AnimationHorizontalRight.play();

					Timeline lv5C4AnimationArc = new Timeline(new KeyFrame(Duration.seconds((375 - 175) / 200.0),
							new KeyValue(lv5C4Arc1.centerYProperty(), 375)));
					lv5C4AnimationArc.play();

					Timeline lv5C4ArcDisappear = new Timeline(
							new KeyFrame(Duration.millis(500), new KeyValue(lv5C4Arc1.lengthProperty(), 0)));
					lv5C4ArcDisappear.setDelay(Duration.seconds((280 - 175) / 200.0));
					lv5C4ArcDisappear.play();

					Timeline[] lv5animations = { lv5C4AnimationVertical1, lv5C4AnimationVertical2End,
							lv5C4AnimationVertical2Start, lv5C4AnimationHorizontalLeft, lv5C4AnimationHorizontalRight,
							lv5C4AnimationArc, lv5C4ArcDisappear };
					Collections.addAll(lv5AllAnims, lv5animations);

					lv5C4AnimationVertical2End.setOnFinished(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {

							lv5Group4.getChildren().addAll(lv5C4thinHLine1, lv5C4thinHLine2, lv5C4thinArc1,
									lv5C4thinVLine1, lv5C4thickHLine1, lv5C4thickVLine1, lv5C4Arc1, lv5C4thickVLine2,
									lv5Circle4);

							FadeTransition lv5ft = new FadeTransition(Duration.millis(500), lv5Group4);
							lv5ft.setFromValue(1.0);
							lv5ft.setToValue(0.0);
							lv5ft.play();

						}
					});
				}
			}
		});

		Timeline lv5C2CollisionCheck = new Timeline(new KeyFrame(Duration.millis(50), event -> {

			//Multiple collision possibilities

			lv5CheckShapeIntersection(lv5C2Arc1, lv5C4thickVLine2);
			if (lv5CollisionDetected) {

				for (Timeline anim : lv5AllAnims) {
					anim.pause();
				}

				level5Pane.getChildren().clear();

				lv5C1thickHLine1.setStartX(685);
				lv5C1thickHLine1.setStartY(175);
				lv5C1thickHLine1.setEndX(590);
				lv5C1thickHLine1.setEndY(175);

				lv5Circle2.setCenterX(350);
				lv5Circle2.setCenterY(620);

				lv5C2thinVLine1.setStartX(350);
				lv5C2thinVLine1.setStartY(585);
				lv5C2thinVLine1.setEndX(350);
				lv5C2thinVLine1.setEndY(505);

				lv5C2thinVLine2.setStartX(350);
				lv5C2thinVLine2.setStartY(425);
				lv5C2thinVLine2.setEndX(350);
				lv5C2thinVLine2.setEndY(390);

				lv5C2thinVLine3.setStartX(350);
				lv5C2thinVLine3.setStartY(310);
				lv5C2thinVLine3.setEndX(350);
				lv5C2thinVLine3.setEndY(110);

				lv5C2thinArc1.setCenterX(355);
				lv5C2thinArc1.setCenterY(110);

				lv5C2thinHLine2.setStartX(355);
				lv5C2thinHLine2.setStartY(105);
				lv5C2thinHLine2.setEndX(365);
				lv5C2thinHLine2.setEndY(105);

				lv5C2thickVLine1.setStartX(365);
				lv5C2thickVLine1.setStartY(90);
				lv5C2thickVLine1.setEndX(365);
				lv5C2thickVLine1.setEndY(120);

				lv5C2thickHLine1.setStartX(365);
				lv5C2thickHLine1.setStartY(105);
				lv5C2thickHLine1.setEndX(550);
				lv5C2thickHLine1.setEndY(105);

				lv5C2Arc1.setCenterX(590);
				lv5C2Arc1.setCenterY(105);

				lv5C2thickHLine2.setStartX(630);
				lv5C2thickHLine2.setStartY(105);
				lv5C2thickHLine2.setEndX(750);
				lv5C2thickHLine2.setEndY(105);

				/////

				lv5Circle3.setCenterX(100);
				lv5Circle3.setCenterY(465);

				lv5C3thinHLine1.setStartX(135);
				lv5C3thinHLine1.setStartY(465);
				lv5C3thinHLine1.setEndX(310);
				lv5C3thinHLine1.setEndY(465);

				lv5C3thinHLine2.setStartX(390);
				lv5C3thinHLine2.setStartY(465);
				lv5C3thinHLine2.setEndX(740);
				lv5C3thinHLine2.setEndY(465);

				lv5C3thinArc1.setCenterX(740);
				lv5C3thinArc1.setCenterY(460);

				lv5C3thinVLine1.setStartX(745);
				lv5C3thinVLine1.setStartY(460);
				lv5C3thinVLine1.setEndX(745);
				lv5C3thinVLine1.setEndY(450);

				lv5C3thickHLine1.setStartX(730);
				lv5C3thickHLine1.setStartY(450);
				lv5C3thickHLine1.setEndX(760);
				lv5C3thickHLine1.setEndY(450);

				lv5C3thickVLine1.setStartX(745);
				lv5C3thickVLine1.setStartY(450);
				lv5C3thickVLine1.setEndX(745);
				lv5C3thickVLine1.setEndY(145);

				lv5C3Arc1.setCenterX(745);
				lv5C3Arc1.setCenterY(105);

				lv5C3thickVLine2.setStartX(745);
				lv5C3thickVLine2.setStartY(65);
				lv5C3thickVLine2.setEndX(745);
				lv5C3thickVLine2.setEndY(35);

				////////

				lv5Circle4.setCenterX(100);
				lv5Circle4.setCenterY(350);

				lv5C4thinHLine1.setStartX(135);
				lv5C4thinHLine1.setStartX(350);
				lv5C4thinHLine1.setEndX(310);
				lv5C4thinHLine1.setEndY(350);

				lv5C4thinHLine1.setStartX(390);
				lv5C4thinHLine1.setStartY(350);
				lv5C4thinHLine1.setEndX(585);
				lv5C4thinHLine1.setEndY(350);

				lv5C4thinArc1.setCenterX(585);
				lv5C4thinArc1.setCenterY(345);

				lv5C4thinVLine1.setStartX(590);
				lv5C4thinVLine1.setStartY(345);
				lv5C4thinVLine1.setEndX(590);
				lv5C4thinVLine1.setEndY(335);

				lv5C4thickHLine1.setStartX(575);
				lv5C4thickHLine1.setStartY(335);
				lv5C4thickHLine1.setEndX(605);
				lv5C4thickHLine1.setEndY(335);

				lv5C4thickVLine1.setStartX(590);
				lv5C4thickVLine1.setStartY(335);
				lv5C4thickVLine1.setEndX(590);
				lv5C4thickVLine1.setEndY(215);

				lv5C4Arc1.setCenterX(590);
				lv5C4Arc1.setCenterY(175);

				lv5C4thickVLine2.setStartX(590);
				lv5C4thickVLine2.setStartY(135);
				lv5C4thickVLine2.setEndX(590);
				lv5C4thickVLine2.setEndY(105);

				level5Pane.getChildren().addAll(lv5C1thinVLine1, lv5C1thinHLine1, lv5C1thinArc1, lv5C1thickVLine1,
						lv5C1thickHLine1, lv5Circle1, lv5Circle2, lv5C2thinVLine1, lv5C2thinVLine2, lv5C2thinVLine3,
						lv5C2thinArc1, lv5C2thinHLine2, lv5C2thickVLine1, lv5C2thickHLine1, lv5C2Arc1, lv5C2thickHLine2,
						lv5Circle3, lv5C3thinHLine1, lv5C3thinHLine2, lv5C3thinArc1, lv5C3thinVLine1, lv5C3thickHLine1,
						lv5C3thickVLine1, lv5C3Arc1, lv5C3thickVLine2, lv5Circle4, lv5C4thinHLine1, lv5C4thinHLine2,
						lv5C4thinArc1, lv5C4thinVLine1, lv5C4thickHLine1, lv5C4thickVLine1, lv5C4Arc1, lv5C4thickVLine2,
						lv5DiscLine1, lv5DiscArc1, lv5ConnectorCircle1, lv5DiscLine2, lv5DiscArc2, lv5ConnectorCircle2,
						lv5Group1, lv5Group2, lv5Group3, lv5Group4);
			}

			lv5CheckShapeIntersection(lv5C3Arc1, lv5C2thickHLine2);
			if (lv5CollisionDetected) {

				for (Timeline anim : lv5AllAnims) {
					anim.pause();
				}

				level5Pane.getChildren().clear();

				lv5C1thickHLine1.setStartX(685);
				lv5C1thickHLine1.setStartY(175);
				lv5C1thickHLine1.setEndX(590);
				lv5C1thickHLine1.setEndY(175);

				lv5Circle2.setCenterX(350);
				lv5Circle2.setCenterY(620);

				lv5C2thinVLine1.setStartX(350);
				lv5C2thinVLine1.setStartY(585);
				lv5C2thinVLine1.setEndX(350);
				lv5C2thinVLine1.setEndY(505);

				lv5C2thinVLine2.setStartX(350);
				lv5C2thinVLine2.setStartY(425);
				lv5C2thinVLine2.setEndX(350);
				lv5C2thinVLine2.setEndY(390);

				lv5C2thinVLine3.setStartX(350);
				lv5C2thinVLine3.setStartY(310);
				lv5C2thinVLine3.setEndX(350);
				lv5C2thinVLine3.setEndY(110);

				lv5C2thinArc1.setCenterX(355);
				lv5C2thinArc1.setCenterY(110);

				lv5C2thinHLine2.setStartX(355);
				lv5C2thinHLine2.setStartY(105);
				lv5C2thinHLine2.setEndX(365);
				lv5C2thinHLine2.setEndY(105);

				lv5C2thickVLine1.setStartX(365);
				lv5C2thickVLine1.setStartY(90);
				lv5C2thickVLine1.setEndX(365);
				lv5C2thickVLine1.setEndY(120);

				lv5C2thickHLine1.setStartX(365);
				lv5C2thickHLine1.setStartY(105);
				lv5C2thickHLine1.setEndX(550);
				lv5C2thickHLine1.setEndY(105);

				lv5C2Arc1.setCenterX(590);
				lv5C2Arc1.setCenterY(105);

				lv5C2thickHLine2.setStartX(630);
				lv5C2thickHLine2.setStartY(105);
				lv5C2thickHLine2.setEndX(750);
				lv5C2thickHLine2.setEndY(105);

				/////

				lv5Circle3.setCenterX(100);
				lv5Circle3.setCenterY(465);

				lv5C3thinHLine1.setStartX(135);
				lv5C3thinHLine1.setStartY(465);
				lv5C3thinHLine1.setEndX(310);
				lv5C3thinHLine1.setEndY(465);

				lv5C3thinHLine2.setStartX(390);
				lv5C3thinHLine2.setStartY(465);
				lv5C3thinHLine2.setEndX(740);
				lv5C3thinHLine2.setEndY(465);

				lv5C3thinArc1.setCenterX(740);
				lv5C3thinArc1.setCenterY(460);

				lv5C3thinVLine1.setStartX(745);
				lv5C3thinVLine1.setStartY(460);
				lv5C3thinVLine1.setEndX(745);
				lv5C3thinVLine1.setEndY(450);

				lv5C3thickHLine1.setStartX(730);
				lv5C3thickHLine1.setStartY(450);
				lv5C3thickHLine1.setEndX(760);
				lv5C3thickHLine1.setEndY(450);

				lv5C3thickVLine1.setStartX(745);
				lv5C3thickVLine1.setStartY(450);
				lv5C3thickVLine1.setEndX(745);
				lv5C3thickVLine1.setEndY(145);

				lv5C3Arc1.setCenterX(745);
				lv5C3Arc1.setCenterY(105);

				lv5C3thickVLine2.setStartX(745);
				lv5C3thickVLine2.setStartY(65);
				lv5C3thickVLine2.setEndX(745);
				lv5C3thickVLine2.setEndY(35);

				////////

				lv5Circle4.setCenterX(100);
				lv5Circle4.setCenterY(350);

				lv5C4thinHLine1.setStartX(135);
				lv5C4thinHLine1.setStartX(350);
				lv5C4thinHLine1.setEndX(310);
				lv5C4thinHLine1.setEndY(350);

				lv5C4thinHLine1.setStartX(390);
				lv5C4thinHLine1.setStartY(350);
				lv5C4thinHLine1.setEndX(585);
				lv5C4thinHLine1.setEndY(350);

				lv5C4thinArc1.setCenterX(585);
				lv5C4thinArc1.setCenterY(345);

				lv5C4thinVLine1.setStartX(590);
				lv5C4thinVLine1.setStartY(345);
				lv5C4thinVLine1.setEndX(590);
				lv5C4thinVLine1.setEndY(335);

				lv5C4thickHLine1.setStartX(575);
				lv5C4thickHLine1.setStartY(335);
				lv5C4thickHLine1.setEndX(605);
				lv5C4thickHLine1.setEndY(335);

				lv5C4thickVLine1.setStartX(590);
				lv5C4thickVLine1.setStartY(335);
				lv5C4thickVLine1.setEndX(590);
				lv5C4thickVLine1.setEndY(215);

				lv5C4Arc1.setCenterX(590);
				lv5C4Arc1.setCenterY(175);

				lv5C4thickVLine2.setStartX(590);
				lv5C4thickVLine2.setStartY(135);
				lv5C4thickVLine2.setEndX(590);
				lv5C4thickVLine2.setEndY(105);

				level5Pane.getChildren().addAll(lv5C1thinVLine1, lv5C1thinHLine1, lv5C1thinArc1, lv5C1thickVLine1,
						lv5C1thickHLine1, lv5Circle1, lv5Circle2, lv5C2thinVLine1, lv5C2thinVLine2, lv5C2thinVLine3,
						lv5C2thinArc1, lv5C2thinHLine2, lv5C2thickVLine1, lv5C2thickHLine1, lv5C2Arc1, lv5C2thickHLine2,
						lv5Circle3, lv5C3thinHLine1, lv5C3thinHLine2, lv5C3thinArc1, lv5C3thinVLine1, lv5C3thickHLine1,
						lv5C3thickVLine1, lv5C3Arc1, lv5C3thickVLine2, lv5Circle4, lv5C4thinHLine1, lv5C4thinHLine2,
						lv5C4thinArc1, lv5C4thinVLine1, lv5C4thickHLine1, lv5C4thickVLine1, lv5C4Arc1, lv5C4thickVLine2,
						lv5DiscLine1, lv5DiscArc1, lv5ConnectorCircle1, lv5DiscLine2, lv5DiscArc2, lv5ConnectorCircle2,
						lv5Group1, lv5Group2, lv5Group3, lv5Group4);
			}

			lv5CheckShapeIntersection(lv5C4Arc1, lv5C1thickHLine1);
			if (lv5CollisionDetected) {

				for (Timeline anim : lv5AllAnims) {
					anim.pause();
				}

				level5Pane.getChildren().clear();

				lv5C1thickHLine1.setStartX(685);
				lv5C1thickHLine1.setStartY(175);
				lv5C1thickHLine1.setEndX(590);
				lv5C1thickHLine1.setEndY(175);

				lv5Circle2.setCenterX(350);
				lv5Circle2.setCenterY(620);

				lv5C2thinVLine1.setStartX(350);
				lv5C2thinVLine1.setStartY(585);
				lv5C2thinVLine1.setEndX(350);
				lv5C2thinVLine1.setEndY(505);

				lv5C2thinVLine2.setStartX(350);
				lv5C2thinVLine2.setStartY(425);
				lv5C2thinVLine2.setEndX(350);
				lv5C2thinVLine2.setEndY(390);

				lv5C2thinVLine3.setStartX(350);
				lv5C2thinVLine3.setStartY(310);
				lv5C2thinVLine3.setEndX(350);
				lv5C2thinVLine3.setEndY(110);

				lv5C2thinArc1.setCenterX(355);
				lv5C2thinArc1.setCenterY(110);

				lv5C2thinHLine2.setStartX(355);
				lv5C2thinHLine2.setStartY(105);
				lv5C2thinHLine2.setEndX(365);
				lv5C2thinHLine2.setEndY(105);

				lv5C2thickVLine1.setStartX(365);
				lv5C2thickVLine1.setStartY(90);
				lv5C2thickVLine1.setEndX(365);
				lv5C2thickVLine1.setEndY(120);

				lv5C2thickHLine1.setStartX(365);
				lv5C2thickHLine1.setStartY(105);
				lv5C2thickHLine1.setEndX(550);
				lv5C2thickHLine1.setEndY(105);

				lv5C2Arc1.setCenterX(590);
				lv5C2Arc1.setCenterY(105);

				lv5C2thickHLine2.setStartX(630);
				lv5C2thickHLine2.setStartY(105);
				lv5C2thickHLine2.setEndX(750);
				lv5C2thickHLine2.setEndY(105);

				/////

				lv5Circle3.setCenterX(100);
				lv5Circle3.setCenterY(465);

				lv5C3thinHLine1.setStartX(135);
				lv5C3thinHLine1.setStartY(465);
				lv5C3thinHLine1.setEndX(310);
				lv5C3thinHLine1.setEndY(465);

				lv5C3thinHLine2.setStartX(390);
				lv5C3thinHLine2.setStartY(465);
				lv5C3thinHLine2.setEndX(740);
				lv5C3thinHLine2.setEndY(465);

				lv5C3thinArc1.setCenterX(740);
				lv5C3thinArc1.setCenterY(460);

				lv5C3thinVLine1.setStartX(745);
				lv5C3thinVLine1.setStartY(460);
				lv5C3thinVLine1.setEndX(745);
				lv5C3thinVLine1.setEndY(450);

				lv5C3thickHLine1.setStartX(730);
				lv5C3thickHLine1.setStartY(450);
				lv5C3thickHLine1.setEndX(760);
				lv5C3thickHLine1.setEndY(450);

				lv5C3thickVLine1.setStartX(745);
				lv5C3thickVLine1.setStartY(450);
				lv5C3thickVLine1.setEndX(745);
				lv5C3thickVLine1.setEndY(145);

				lv5C3Arc1.setCenterX(745);
				lv5C3Arc1.setCenterY(105);

				lv5C3thickVLine2.setStartX(745);
				lv5C3thickVLine2.setStartY(65);
				lv5C3thickVLine2.setEndX(745);
				lv5C3thickVLine2.setEndY(35);

				////////

				lv5Circle4.setCenterX(100);
				lv5Circle4.setCenterY(350);

				lv5C4thinHLine1.setStartX(135);
				lv5C4thinHLine1.setStartX(350);
				lv5C4thinHLine1.setEndX(310);
				lv5C4thinHLine1.setEndY(350);

				lv5C4thinHLine1.setStartX(390);
				lv5C4thinHLine1.setStartY(350);
				lv5C4thinHLine1.setEndX(585);
				lv5C4thinHLine1.setEndY(350);

				lv5C4thinArc1.setCenterX(585);
				lv5C4thinArc1.setCenterY(345);

				lv5C4thinVLine1.setStartX(590);
				lv5C4thinVLine1.setStartY(345);
				lv5C4thinVLine1.setEndX(590);
				lv5C4thinVLine1.setEndY(335);

				lv5C4thickHLine1.setStartX(575);
				lv5C4thickHLine1.setStartY(335);
				lv5C4thickHLine1.setEndX(605);
				lv5C4thickHLine1.setEndY(335);

				lv5C4thickVLine1.setStartX(590);
				lv5C4thickVLine1.setStartY(335);
				lv5C4thickVLine1.setEndX(590);
				lv5C4thickVLine1.setEndY(215);

				lv5C4Arc1.setCenterX(590);
				lv5C4Arc1.setCenterY(175);

				lv5C4thickVLine2.setStartX(590);
				lv5C4thickVLine2.setStartY(135);
				lv5C4thickVLine2.setEndX(590);
				lv5C4thickVLine2.setEndY(105);

				level5Pane.getChildren().addAll(lv5C1thinVLine1, lv5C1thinHLine1, lv5C1thinArc1, lv5C1thickVLine1,
						lv5C1thickHLine1, lv5Circle1, lv5Circle2, lv5C2thinVLine1, lv5C2thinVLine2, lv5C2thinVLine3,
						lv5C2thinArc1, lv5C2thinHLine2, lv5C2thickVLine1, lv5C2thickHLine1, lv5C2Arc1, lv5C2thickHLine2,
						lv5Circle3, lv5C3thinHLine1, lv5C3thinHLine2, lv5C3thinArc1, lv5C3thinVLine1, lv5C3thickHLine1,
						lv5C3thickVLine1, lv5C3Arc1, lv5C3thickVLine2, lv5Circle4, lv5C4thinHLine1, lv5C4thinHLine2,
						lv5C4thinArc1, lv5C4thinVLine1, lv5C4thickHLine1, lv5C4thickVLine1, lv5C4Arc1, lv5C4thickVLine2,
						lv5DiscLine1, lv5DiscArc1, lv5ConnectorCircle1, lv5DiscLine2, lv5DiscArc2, lv5ConnectorCircle2,
						lv5Group1, lv5Group2, lv5Group3, lv5Group4);
			}

		}));
		lv5C2CollisionCheck.setCycleCount(Timeline.INDEFINITE);
		lv5C2CollisionCheck.play();

		/////////////////////////////

		// Pane & Stage

		level5Pane = new Pane();

		level5Pane.setStyle("-fx-background-color: darkgrey");

		level5Pane.getChildren().addAll(lv5C1thinVLine1, lv5C1thinHLine1, lv5C1thinArc1, lv5C1thickVLine1,
				lv5C1thickHLine1, lv5Circle1, lv5Circle2, lv5C2thinVLine1, lv5C2thinVLine2, lv5C2thinVLine3,
				lv5C2thinArc1, lv5C2thinHLine2, lv5C2thickVLine1, lv5C2thickHLine1, lv5C2Arc1, lv5C2thickHLine2,
				lv5Circle3, lv5C3thinHLine1, lv5C3thinHLine2, lv5C3thinArc1, lv5C3thinVLine1, lv5C3thickHLine1,
				lv5C3thickVLine1, lv5C3Arc1, lv5C3thickVLine2, lv5Circle4, lv5C4thinHLine1, lv5C4thinHLine2,
				lv5C4thinArc1, lv5C4thinVLine1, lv5C4thickHLine1, lv5C4thickVLine1, lv5C4Arc1, lv5C4thickVLine2,
				lv5DiscLine1, lv5DiscArc1, lv5ConnectorCircle1, lv5DiscLine2, lv5DiscArc2, lv5ConnectorCircle2,
				lv5Group1, lv5Group2, lv5Group3, lv5Group4);

		scene6 = new Scene(level5Pane, 1000, 700);

		////////////////////////////////////////////////////////////////////////////////////

		window.setTitle("Hook"); // Set the stage title
		window.setScene(scene1); // Place the scene in the stage
		window.show(); // Display the stage
	}

	public static void main(String[] args) {
		launch(args);

	}

	//Methods for checking whether there is a collision or not for each level

	private void lv1CheckShapeIntersection(Shape block, Shape shape) {

		lv1CollisionDetected = false;
		// If the reference shape is not itself
		if (shape != block) { //The line or arc cannot be collide by itself.

			// Check whether there is intersection area between shapes.
			Shape intersect = Shape.intersect(block, shape); //Assigns the intersection area to the intersect variable.
			// If there is no such area method returns a negative number
			if (intersect.getBoundsInLocal().getHeight() > -1) {
				lv1CollisionDetected = true;  //If there is, makes the lv1CollisionDetected variable true.
			}

		}

	}

	private void lv2CheckShapeIntersection(Shape block, Shape shape) {

		lv2CollisionDetected = false;
		// If the reference shape is not itself
		if (shape != block) {

			// Check whether there is intersection area between shapes.
			Shape intersect = Shape.intersect(block, shape);
			// If there is no such area method returns a negative number
			if (intersect.getBoundsInLocal().getHeight() > -1) {
				lv2CollisionDetected = true;
			}

		}

	}

	private void lv3CheckShapeIntersection(Shape block, Shape shape) {

		lv3CollisionDetected = false;
		// If the reference shape is not itself
		if (shape != block) {

			// Check whether there is intersection area between shapes.
			Shape intersect = Shape.intersect(block, shape);
			// If there is no such area method returns a negative number
			if (intersect.getBoundsInLocal().getHeight() > -1) {
				lv3CollisionDetected = true;
			}

		}

	}

	private void lv4CheckShapeIntersection(Shape block, Shape shape) {

		lv4CollisionDetected = false;
		// If the reference shape is not itself
		if (shape != block) {

			// Check whether there is intersection area between shapes.
			Shape intersect = Shape.intersect(block, shape);
			// If there is no such area method returns a negative number
			if (intersect.getBoundsInLocal().getHeight() > -1) {
				lv4CollisionDetected = true;
			}

		}

	}

	private void lv5CheckShapeIntersection(Shape block, Shape shape) {

		lv5CollisionDetected = false;
		// If the reference shape is not itself
		if (shape != block) {

			// Check whether there is intersection area between shapes.
			Shape intersect = Shape.intersect(block, shape);
			// If there is no such area method returns a negative number
			if (intersect.getBoundsInLocal().getHeight() > -1) {
				lv5CollisionDetected = true;
			}
		}

	}
}
