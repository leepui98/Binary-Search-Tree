import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainBinaryTree extends Application {

	Tree bST = new Tree();
	Tree bSTLastName = new Tree();
	final TextField nameSearchFirst = new TextField();
	final TextField nameSearchLast = new TextField();
	final TextField identifyFirstNameLength= new TextField();
	final TextField identifyLastNameLength= new TextField();
	final GridPane grid = new GridPane();
//	final TextField compareFirstNameLength= new TextField();
	

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane borderpane = new BorderPane();
			final HBox hBox = new HBox();
			hBox.setPadding(new Insets (14, 14, 14, 14));
			hBox.setSpacing(12);
			hBox.setStyle("-fx-background-color: #87CEFA;");
			borderpane.setTop(hBox);
			
			final HBox hBox2= new HBox();
			hBox2.setPadding(new Insets (14, 14, 14, 14));
			hBox2.setSpacing(12);
			hBox2.setStyle("-fx-background-color: #87CEFA;");
			borderpane.setBottom(hBox2);
			
			
			grid.setStyle("-fx-background-color: #B0C4DE;");
		    grid.setHgap(10);
		    grid.setVgap(10);
		    grid.setPadding(new Insets(0, 10, 0, 10));
			borderpane.setCenter(grid);	
			bST.setGridpane(grid);
			bSTLastName.setGridpane(grid);
			
			
			final Button btnLoad = new Button("Load File");
			btnLoad.setPrefSize(300, 50);
			btnLoad.setOnMouseClicked(e -> {
				load(primaryStage);
			});

			final Button btnInOrder = new Button("In-order by first name");
			btnInOrder.setPrefSize(400, 50);
			btnInOrder.setOnAction(e -> {
				printInOrder();
			});

			final Button btnPre = new Button("Pre-order by first name");
			btnPre.setPrefSize(400, 50);
			btnPre.setOnAction(e -> {
				printPreOrder();
			});

			final Button btnPost = new Button("Post-order by first name");
			btnPost.setPrefSize(400, 50);
			btnPost.setOnAction(e -> {
				printPostOrder();
			});
			
			final Button btnBreadthFirst= new Button("Breadth-first by first name");
			btnBreadthFirst.setPrefSize(400, 50);
			btnBreadthFirst.setOnAction(e -> {
				printBreadthFirstName();
			});

			final Button btnInOrderLastName = new Button("In-order by last name");
			btnInOrderLastName.setPrefSize(400, 50);
			btnInOrderLastName.setOnAction(e -> {
				printInOrderLastName();
			});

			final Button btnPreLastName = new Button("Pre-order by last name");
			btnPreLastName.setPrefSize(400, 50);
			btnPreLastName.setOnAction(e -> {
				printPreOrderLastName();
			});

			final Button btnPostLastName = new Button("Post-order by last name");
			btnPostLastName.setPrefSize(400, 50);
			btnPostLastName.setOnAction(e -> {
				printPostOrderLastName();
			});
			
			final Button btnBreadthLast= new Button("Breadth-first by last name");
			btnBreadthLast.setPrefSize(400, 50);
			btnBreadthLast.setOnAction(e -> {
				printBreadthLastName();
			});
			
			final Button btnFirstNameLonger= new Button("First name longer");
			btnFirstNameLonger.setPrefSize(200, 30);
			btnFirstNameLonger.setOnAction(e -> {
				printFirstNameLonger();
			});
			
			final Button btnLastNameLonger= new Button("Last name longer");
			btnLastNameLonger.setPrefSize(200, 30);
			btnLastNameLonger.setOnAction(e -> {
				printLastNameLonger();
			});
			
			final Button btnClear= new Button("Clear text");
			btnClear.setPrefSize(150, 50);
			btnClear.setOnAction(e -> {
				grid.getChildren().clear();
			});
			
			nameSearchFirst.setOnKeyPressed((event) -> {
				if (event.getCode() == KeyCode.ENTER) {
					searchFirstName();
				}
			});
//			nameSearchLast.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> searchLastName());
			nameSearchLast.setOnKeyPressed((event) -> {
				if (event.getCode() == KeyCode.ENTER) {
					searchLastName();
				}
			});
//			identifyFirstNameLength.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> identifyLengthofSearchFirstName ());
			identifyFirstNameLength.setOnKeyPressed((event) -> {
				if (event.getCode() == KeyCode.ENTER) {
					int l = Integer.parseInt(identifyFirstNameLength.getText());				
					List<Person> results = new ArrayList<>();
					ArrayList<Text> fNL= new ArrayList<Text>();
					bST.searchFirstNameWithLength(bST.root, l, results);
					
					for(Person p: results) {
//						System.out.println(p.getFirstName());
						Text a= new Text (p.getFirstName()+ " " + p.getLastName());
						fNL.add(a);
					}
					int x = 0;
					int y = 0;
					for(int i=0; i<fNL.size(); i++)
					{
						if(i %10 == 0)
						{
							x = 0;
							y++;
						}
						grid.add(fNL.get(i), x + (i%10), y);
					}
					}
			});
			
			identifyLastNameLength.setOnKeyPressed((event) -> {
				if (event.getCode() == KeyCode.ENTER) {
					int l = Integer.parseInt(identifyLastNameLength.getText());				
					List<Person> resultsLast = new ArrayList<>();
					ArrayList<Text> fNL= new ArrayList<Text>();
					bSTLastName.searchLastNameWithLength(bSTLastName.root, l, resultsLast);
					
					for(Person p: resultsLast) {
//						System.out.println(p.getLastName());
						Text a= new Text (p.getFirstName()+ " " + p.getLastName());
						fNL.add(a);
					}
					int x = 0;
					int y = 0;
					for(int i=0; i<fNL.size(); i++)
					{
						if(i %10 == 0)
						{
							x = 0;
							y++;
						}
						grid.add(fNL.get(i), x + (i%10), y);
					}
					}
			});

			final Text t1= new Text("Search by first name");
			final Text t2= new Text("Search by last name");
			final Text t3= new Text("First names have length of : ");
			final Text t4= new Text("Last names have length of : ");
			
			

			VBox vBox = new VBox();
			vBox.setPadding(new Insets(10));
			vBox.setSpacing(15);
			vBox.setStyle("-fx-background-color: #FFC0CB;");
			vBox.getChildren().addAll(t1,nameSearchFirst, t2,nameSearchLast,t3,identifyFirstNameLength,t4, identifyLastNameLength, btnFirstNameLonger, btnLastNameLonger, btnClear);
			borderpane.setLeft(vBox);
			
			
			hBox.getChildren().addAll(btnLoad, btnInOrder, btnPre, btnPost, btnBreadthFirst );
			hBox2.getChildren().addAll(btnInOrderLastName, btnPreLastName, btnPostLastName, btnBreadthLast);
			Scene scene = new Scene(borderpane, 700, 700);
			scene.getStylesheets().add(getClass().getResource("styling.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printInOrder() {
		bST.inOrderPrintAll();
	}

	public void printPreOrder() {
		bST.prePrintNames();
	}

	public void printPostOrder() {
		bST.postPrintNames();
	}
	
	public void printBreadthFirstName() {
//		List<Person> breadthFN= new ArrayList<>();
		ArrayList<Text> fNL= new ArrayList<Text>();
		bST.breadthPrintName(bST.root,fNL);
//		for(Person p: breadthFN) {
////			if (p != null)
////			System.out.println(p.getFirstName()+ p.getLastName());
////			
////			if(p== null)
////			System.out.println("Null");
////		}
//			if(p != null) {
//			Text a= new Text (p.getFirstName()+ " "+ p.getLastName());
//			fNL.add(a);
//			}
//			else if (p==null) {
//			Text nl= new Text (" no text ");
//			fNL.add(nl);
//			}
//		}
		
		int x = 0;
		int y = 0;
		for(int i=0; i<fNL.size(); i++)
		{
			if(i %10 == 0)
			{
				x = 0;
				y++;
			}
			grid.add(fNL.get(i), x + (i%10), y);
		}
	
	}
	
	public void printInOrderLastName() {
		bSTLastName.inOrderPrintAll();
	}

	public void printPreOrderLastName() {
		bSTLastName.prePrintNames();
	}

	public void printPostOrderLastName() {
		bSTLastName.postPrintNames();
	}
	
	public void printBreadthLastName() {
//		List<Person> breadthFL= new ArrayList<>();
		ArrayList<Text> fNL= new ArrayList<Text>();
		bSTLastName.breadthPrintName(bSTLastName.root,fNL);
//		for(Person p: breadthFL) {
//			if(p != null) {
//			Text a= new Text (p.getFirstName()+ " " + p.getLastName());
//			fNL.add(a);
//		}
//		
//		else if (p==null) {
//			Text nl= new Text (" no text ");
//			fNL.add(nl);
//			}
//		
		int x = 0;
		int y = 0;
		for(int i=0; i<fNL.size(); i++)
		{
			if(i %10 == 0)
			{
				x = 0;
				y++;
			}
			grid.add(fNL.get(i), x + (i%10), y);
		}
		}
		
	
	
	public void printFirstNameLonger() {
		List<Person> firstNameLonger = new ArrayList<>();
		ArrayList<Text> fNL= new ArrayList<Text>();
		bST.firstNameLongerThanLastName(bST.root, firstNameLonger);
		for(Person p: firstNameLonger) {
			Text a= new Text (p.getFirstName()+ " " + p.getLastName());
			fNL.add(a);
		}
		
		int x = 0;
		int y = 0;
		for(int i=0; i<fNL.size(); i++)
		{
			if(i %10 == 0)
			{
				x = 0;
				y++;
			}
			grid.add(fNL.get(i), x + (i%10), y);
		}
	}
	
	public void printLastNameLonger() {
		List<Person> LastNameLonger = new ArrayList<>();
		ArrayList<Text> fNL= new ArrayList<Text>();
		bSTLastName.LastNameLongerThanFirstName(bSTLastName.root, LastNameLonger);
		for(Person p: LastNameLonger) {
			Text a= new Text (p.getFirstName()+ " " + p.getLastName());
			fNL.add(a);
		}
		int x = 0;
		int y = 0;
		for(int i=0; i<fNL.size(); i++)
		{
			if(i %10 == 0)
			{
				x = 0;
				y++;
			}
			grid.add(fNL.get(i), x + (i%10), y);
		}
	}
	
	public void searchFirstName() {
		String searchName = nameSearchFirst.getText();
		Person pp = bST.searchByFirstName(bST.getRoot(), searchName);
		if (pp != null) {
//			System.out.println("Found: " + pp);
			Text foundF= new Text("Found that person");
			grid.add(foundF, 0, 0);
		
		}
		else {
			Text notFoundF= new Text ("No such person");
			grid.add(notFoundF, 0, 0);
		}
			
	}

	public void searchLastName() {
		String searchName = nameSearchLast.getText();
		Person pp = bSTLastName.searchByLastName(bSTLastName.getRoot(), searchName);
		if (pp != null) {
			Text foundL= new Text ("Found that person");
			grid.add(foundL, 0, 0);
		}
		else {
			Text notFoundL= new Text("No such person");
			grid.add(notFoundL, 0, 0);
		}
	}

//	public void identifyLengthofSearchFirstName() {
//		String searchName= identifyFirstNameLength.getText();
//		Person p1= bST.searchByFirstName(bST.getRoot(), searchName);
//		String a= p1.getFirstName();
//		if(p1 != null) 
//			 System.out.println(a + "'s length name is "+a.length());
//		
//		else 
//			System.out.println("Not found");
//	
//	}
//	
//	public void identifyLengthofSearchLastName() {
//		String searchName= identifyLastNameLength.getText();
//		Person p1= bST.searchByLastName(bST.getRoot(), searchName);
//		String a= p1.getLastName();
//		if(p1 != null) 
//			 System.out.println(a + "'s length name is "+a.length()); //when have same last name program crash
//		
//		else 
//			System.out.println("Not found"); //no such name cause program crash
//	}

	public void load(Stage primaryStage) {

		try {

			FileChooser fileChooser = new FileChooser();

			File inFile = fileChooser.showOpenDialog(primaryStage);
			Scanner scan = new Scanner(inFile);

			while (scan.hasNext()) {
				String name1 = scan.next();
				String name2 = scan.next();
				Person p = new Person(name1, name2);
				Person p1 = new Person(name1, name2);
				bST.addPerson(p);// This tree holds names sorted based on the first name
				bSTLastName.addPersonLastName(p1);// This tree holds names sorted based on the last name
			}
			scan.close();

		} catch (IOException e) {
			System.out.println("File Error.");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
