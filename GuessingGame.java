

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * 
 * Chelle Cruz
 * 
 * Homework 03
 * 
 */

public class GuessingGame extends Application {
	
	BorderPane pane = new BorderPane();
	FlowPane menu = new FlowPane();
	Text title = new Text("The Guessing Game!");
	Text name = new Text("By: Chelle Cruz");
	String[] hpWords = {
			"Harry Potter", "Albus Dumbledore", "Lord Voldemort", "Quidditch", "Avada Kedavra",
			"Hermoine Granger", "Hogwarts", "Butter Beer", "Dementor", "Griffindor", "Muggle", "Azkaban",
			"Neville Longbottom", "Severus Snape", "Sirius Black", "Bellatrix Lestrange", "Tom Riddle",
			"Horcrux", "Deathly Hallows", "Hufflepuff", "Ravenclaw", "Slytherin", "Draco Malfoy",
			"Death Eaters", "Hagrid", "Chamber of Secrets", "Diagon Alley", "Expecto Patronum",
			"Ministry of Magic", "Ron Weasley", "Luna Lovegood", "Expelliarmus", "Golden Snitch",
			"Hogsmeade", "Invisibility Cloak", "Elder Wand", "Ressurection Stone", "Mudblood",
			"Polyjuice Potion", "Platform Nine and Three Quarters"
	};
	String[] halloweenWords = {
			"Frankenstein", "Candy", "Witchcraft", "Dracula", "Costume",
			"Monster Mash", "Trick or Treat", "Hocus Pocus", "Pumpkin", "Haunted House",
			"Ghoul", "Broomstick", "Skeleton", "Corpse", "Werewolf", "Boogeyman", "Blood",
			"Wicked", "Zombie", "Goblin", "Spirit", "Demon", "Spider", "Graveyard", "Day of the Dead"
	};
	String[] movieWords = {
			"Star Wars", "Sound of Music", "Transformers", "Hunger Games",
			"Godfather", "Wizard of Oz", "Psycho", "Forrest Gump", "Lord of the Rings",
			"Titanic", "Interstellar", "Jaws", "Jurrasic Park", "Kill Bill", "The Dark Knight",
			"Batman vs Superman", "Frozen", "Inception", "Ghostbusters", "Avatar", "Gone Girl",
			"Dumb and Dumber", "Austin Powers", "Back to the Future", "Notebook", "King Kong",
			"Carrie", "Exorcist", "Nightmare on Elm Street"
	};
	String[] javaWords = {
			"Abstract Class", "Constructor", "Object Oriented Programming", "Methods",
			"Superclass", "Initialize", "Parameters", "For loop", "ArrayList", "TreeSet",
			"HashMap", "If Else Statement", "Data Field", "Inheritance", "Polymorphism",
			"Generics", "Exception Handling", "JavaFX", "Runtime Exception", "Interface",
			"Comparable", "Visibility Modifiers", "Syntax", "Algorithms", "Overloading",
			"Last In First Out", "Reference Variable", "Recursion", "Binary Search",
			"Selection Sort", "Queues", "Dynamic Programming"	
	};
	TreeMap<String, String[]> themes = new TreeMap<>();
	//Linked hash map: integer is index, and letters of the word
	LinkedHashMap<Integer, Character> letters = new LinkedHashMap<>();	//Array of characters of random word
	Text enter = new Text("Enter a letter: ");
	Text enteredLetter = new Text("");
	Scene scene = new Scene(pane, 700, 700);
	//Current word during the game
	StringBuilder currentWord = new StringBuilder();
	Text word = new Text();
	ArrayList<Character> entered = new ArrayList<>();
	Text alreadyEntered = new Text("Letter is already entered.");
	boolean thereIsAMatch = false;
	FlowPane topPane = new FlowPane();
	Text missed = new Text("Missed letters: ");
	Text displayMissedLetters = new Text("");
	StringBuilder mLetters = new StringBuilder();
	int numberMissed;
	String mLetter;
	Text youLost = new Text("You lost! You missed 7 times!");
	Text youWon = new Text("You won! You solved the word!");
	boolean isSolved;
	Button pickTheme = new Button("Pick a theme!");
	Text newWord = new Text("Wanna guess another word? (Press ENTER)");
	String currentTheme;
	Text theme = new Text("Pick a Theme!");
	FlowPane centerPane = new FlowPane();
	
	//Files: put on src folder
	//Mp3 files: put on project folder
	ImageView hpButton = new ImageView("/harry-potter-button.jpg");
	File hpMusic = new File("hp-track.mp3");
	Media hpTrack = new Media(hpMusic.toURI().toString());
	MediaPlayer mediaPlayerHP = new MediaPlayer(hpTrack);
	ImageView halloweenButton = new ImageView("/halloween-button.jpg");
	File hallowMusic = new File("halloween-track.mp3");
	Media hallowTrack = new Media(hallowMusic.toURI().toString());
	MediaPlayer mediaPlayerHallow = new MediaPlayer(hallowTrack);
	ImageView moviesButton = new ImageView("/movies-button.jpg");
	File moviesMusic = new File("movies-track.mp3");
	Media moviesTrack = new Media(moviesMusic.toURI().toString());
	MediaPlayer mediaPlayerMovies = new MediaPlayer(moviesTrack);
	ImageView javaButton = new ImageView("/java-button.jpg");
	File javaMusic = new File("java-track.mp3");
	Media javaTrack = new Media(javaMusic.toURI().toString());
	MediaPlayer mediaPlayerJava = new MediaPlayer(javaTrack);
	
	//GIFs
	ImageView hpGif = new ImageView("/hp.gif");
	ImageView hpLose = new ImageView("/hp-lose.gif");
	ImageView halloweenWon = new ImageView("/halloween-win.gif");
	ImageView halloweenLose = new ImageView("/halloween-lose.gif");
	ImageView movieWin = new ImageView("/movie-win.gif");
	ImageView movieLose = new ImageView("/movie-lose.gif");
	ImageView javaWin = new ImageView("/java-win.gif");
	ImageView javaLose = new ImageView("/java-lose.gif");
	
	
	@Override
	public void start(Stage primaryStage) {
		
		//Set ids for css
		setIDsAndStyles();
		//Set up words for game
		setUpWords();
		//create starting menu
		mainMenu();
		
		//Get the css for the styles
		scene.getStylesheets().add("myStyle.css");
		scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Abril+Fatface");
		scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Old+Standard+TT");
		primaryStage.setTitle("Chelle's Guessing Game"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void setIDsAndStyles() {
		
		//Resize gif
		javaWin.setFitHeight(210);
		movieWin.setFitHeight(210);
		
		pane.setId("pane");
		title.setStyle("-fx-font-family: 'Abril Fatface', cursive; -fx-fill: #962d3e;" +
		"-fx-font-size: 40");
		name.setStyle("-fx-font-family: 'Old Standard TT', serif; -fx-fill: #343642;" +
		"-fx-font-size: 20");
		pickTheme.setStyle("-fx-background-color: #348899; -fx-border-radius: 5;"
				+ "-fx-padding: 20 20 20 20; -fx-text-fill: #f2ebc7");
		pickTheme.setOnMousePressed(e -> {
			pickTheme.setOpacity(0.5);
		});
		pickTheme.setOnMouseReleased(e -> {
			pickTheme.setOpacity(1);
		});
		theme.setStyle("-fx-font-family: 'Abril Fatface', cursive; -fx-fill: #962d3e;" +
		"-fx-font-size: 30");
		
		missed.setStyle("-fx-fill: #343642; -fx-font-size: 15; -fx-family: Helvetica");
		enter.setStyle("-fx-fill: #343642; -fx-font-size: 15; -fx-family: Helvetica");
		enteredLetter.setStyle("-fx-font-size: 20; -fx-family: Helvetica; -fx-fill: #962d3e;"
				+ "-fx-font-weight: bolder"); 
		displayMissedLetters.setStyle("-fx-font-size: 20; -fx-family: Helvetica; -fx-fill: #962d3e;"
				+ "-fx-font-weight: bolder");
		alreadyEntered.setStyle("-fx-family: Helvetica; -fx-fill: #962d3e; -fx-font-size: 15");
		youWon.setStyle("-fx-font-size: 15; -fx-fill: #962d3e; -fx-font-family: 'Abril Fatface', cursive");
		youLost.setStyle("-fx-font-size: 15; -fx-fill: #962d3e; -fx-font-family: 'Abril Fatface', cursive");
		newWord.setStyle("-fx-font-family: 'Old Standard TT', serif; -fx-fill: #343642;" +
		"-fx-font-size: 15");
		word.setStyle("-fx-fill: #343642; -fx-family: Helvetica; -fx-font-size: 30");
		
	}
	
	public void setUpWords() {
	
		//Set up themes and words in hash map
		themes.put("Harry Potter", hpWords);
		themes.put("Halloween", halloweenWords);
		themes.put("Movies", movieWords);
		themes.put("Java", javaWords);
		
	}
	
	public void mainMenu() {
		
		menu.setAlignment(Pos.CENTER);
		menu.setOrientation(Orientation.VERTICAL);
		menu.setVgap(15);
		pane.setCenter(menu);
		menu.getChildren().addAll(title, name, pickTheme);
		
		pickTheme.setOnAction(e -> {
			pickTheme();
		});
		
	}
	
	public void pickTheme() {
		
		//Make enter key unclickable
		scene.setOnKeyPressed(null);
		
		//Clears the previous panes from previous game
		//Default setting for menu
		pane.setTop(null);
		pane.setBottom(null);
		pane.setCenter(menu);
		menu.setOrientation(Orientation.VERTICAL);
		menu.setPadding(new Insets(60, 0, 0, 100));
		menu.setAlignment(Pos.BASELINE_LEFT);
		menu.getChildren().clear();
		menu.setVgap(30);
		menu.getChildren().addAll(theme, hpButton, halloweenButton, moviesButton, javaButton);
		
		//When mouse hovers over
		hpButton.setOnMouseEntered(e -> {
			mediaPlayerHP.play();
		});
		//When mouse exits the button
		hpButton.setOnMouseExited(e -> {
			mediaPlayerHP.stop();
		});
		hpButton.setOnMouseClicked(e -> {
			currentTheme = "Harry Potter";
			newGame(currentTheme);
		});
		hpButton.setOnMousePressed(e -> {
			hpButton.setOpacity(0.5);
		});
		hpButton.setOnMouseReleased(e -> {
			hpButton.setOpacity(1);
		});
		
		halloweenButton.setOnMouseEntered(e -> {
			mediaPlayerHallow.play();
		});
		halloweenButton.setOnMouseExited(e -> {
			mediaPlayerHallow.stop();
		});
		halloweenButton.setOnMouseClicked(e -> {
			currentTheme = "Halloween";
			newGame(currentTheme);
		});
		halloweenButton.setOnMousePressed(e -> {
			halloweenButton.setOpacity(0.5);
		});
		halloweenButton.setOnMouseReleased(e -> {
			halloweenButton.setOpacity(1);
		});
		
		moviesButton.setOnMouseEntered(e -> {
			mediaPlayerMovies.play();
		});
		moviesButton.setOnMouseExited(e -> {
			mediaPlayerMovies.stop();
		});
		moviesButton.setOnMouseClicked(e -> {
			currentTheme = "Movies";
			newGame(currentTheme);
		});
		moviesButton.setOnMousePressed(e -> {
			moviesButton.setOpacity(0.5);
		});
		moviesButton.setOnMouseReleased(e -> {
			moviesButton.setOpacity(1);
		});
		
		javaButton.setOnMouseEntered(e -> {
			mediaPlayerJava.play();
		});
		javaButton.setOnMouseExited(e -> {
			mediaPlayerJava.stop();
		});
		javaButton.setOnMouseClicked(e -> {
			currentTheme = "Java";
			newGame(currentTheme);
		});
		javaButton.setOnMousePressed(e -> {
			javaButton.setOpacity(0.5);
		});
		javaButton.setOnMouseReleased(e -> {
			javaButton.setOpacity(1);
		});
		
	}
	
	public void newGame(String theme) {
		
		//Clear the content in enteredLetter
		enteredLetter.setText("");
		
		//Make enter key unclickable
		scene.setOnKeyPressed(null);
		
		//Set number missed to 0
		numberMissed = 0;
		
		//Get the string array from theme
		String[] words = themes.get(theme);
		
		//Pick a random word: index between 0 and words.length - 1
		int randomIndex = (int)(Math.random() * words.length);
		
		//Get random word from random index
		String word = words[randomIndex];
		
		//Get each letter of word and store in array list
		//Clear array lists
		entered.clear();
		letters.clear();
		
		//Add letters of the word to map
		for (int i = 0; i < word.length(); i++) {
			letters.put(i, word.charAt(i));
			System.out.println(word.charAt(i));
		}
		
		//Add missed and missed letters to flow pane 
		//Set flow pane to top of border pane
		topPane.setOrientation(Orientation.VERTICAL);
		topPane.setAlignment(Pos.BASELINE_LEFT);
		topPane.setPadding(new Insets(60, 0, 0, 60));
		topPane.setVgap(10);
		
		//if top pane does not contain usual elements
		if ((!topPane.getChildren().contains(pickTheme)) &&
			(!topPane.getChildren().contains(missed)) &&
			(!topPane.getChildren().contains(displayMissedLetters)) &&
			(!topPane.getChildren().contains(title)) &&
			(!topPane.getChildren().contains(name))) {
			
			topPane.getChildren().addAll(title, name, pickTheme, missed, displayMissedLetters);
			//Make size of title and name smaller
			title.setStyle("-fx-font-family: 'Abril Fatface', cursive; -fx-fill: #962d3e;" +
		"-fx-font-size: 15");
			name.setStyle("-fx-font-family: 'Old Standard TT', serif; -fx-fill: #343642;" +
					"-fx-font-size: 10");
			
		}	
		
		pane.setTop(topPane);
		
		//Get rid of gifs
		for (int i = 0; i < topPane.getChildren().size(); i++) {
			
			Node n = topPane.getChildren().get(i);
			
			if (n instanceof ImageView && n != null && topPane.getChildren().contains(n)) {
				topPane.getChildren().remove(n);
			}
			
		}
		
		startGame();
	
	}
	
	public void startGame() {
		
		//Clear
		menu.getChildren().clear();
		//Clear the current word and mLetters
		currentWord.setLength(0);
		mLetters.setLength(0);
		displayMissedLetters.setText("");
		
		//Add the censored word onto pane
		for (Map.Entry<Integer, Character> pair: letters.entrySet()) {
			
			//If the character is a space, don't censor it
			if (pair.getValue() == 32) {
				currentWord.append(' ');
			}
			else {
				currentWord.append('*');
			}
			
		}
		//Put word onto text
		word.setText(currentWord.toString());
		
		pane.setCenter(centerPane);
		if (!centerPane.getChildren().contains(word)) {
			centerPane.getChildren().add(word);
		}
		centerPane.setAlignment(Pos.TOP_CENTER);
		
		pane.setBottom(menu);
		menu.setOrientation(Orientation.HORIZONTAL);
		menu.setAlignment(Pos.BASELINE_CENTER);
		menu.setPadding(new Insets(0, 0, 45, 0));
		menu.getChildren().addAll(enter, enteredLetter);
		
		//Allow to get character when typing key
		scene.setOnKeyTyped(e -> {
			
			String c = e.getCharacter();
			
			//Make sure key typed is a letter
			if (Character.isLetter(c.charAt(0))) {
				enteredLetter.setText(c);
				checkWord(c);
			}
			
		});
		
	}
	
	public void checkWord(String letter) {
		
		//set there is a match to false
		thereIsAMatch = false;
		
		//Check entered letters array list if letter selected is already an entered letter
		for (Character e: entered) {
			
			if (letter.compareToIgnoreCase(e + "") == 0) {
				
				//if the top pane doesn't already have alreadyEntered text
				if (!topPane.getChildren().contains(alreadyEntered)) {
					//Notify user that letter typed is already missed
					topPane.getChildren().add(alreadyEntered);
				}	
				return;
				
			}
 			
		}
		
		//Add letter to entered
		entered.add(letter.charAt(0));
		//Take out alreadyEntered Text
		if (topPane.getChildren().contains(alreadyEntered)) {
			topPane.getChildren().remove(alreadyEntered);		
		}
		
		//Check array list if the letter matches any elements
		for (Map.Entry<Integer, Character> pair: letters.entrySet()) {
			
			if (letter.compareToIgnoreCase(pair.getValue() + "") == 0) {
				
				thereIsAMatch = true;
				
				currentWord.replace(pair.getKey(), pair.getKey() + 1, pair.getValue() + "");
				word.setText(currentWord.toString());
				
			}
			
		}
		
		//If not, then number of misses increases
		if (!thereIsAMatch) {
			numberMissed++;
			mLetter = letter;
			mLetters.append(mLetter + " ");
			displayMissedLetters.setText(mLetters.toString());
		}
		
		
		
		//Check if there are still censored letters in the current word
		isSolved = checkforCensors();
		
		//Check if there are 7 missed letters
		if (numberMissed == 7) {
			
			//Clear menu
			menu.getChildren().clear();
			menu.setOrientation(Orientation.VERTICAL);
			menu.getChildren().addAll(youLost, newWord);
			
			//Add gif
			if (currentTheme.equals("Harry Potter")) {
				topPane.getChildren().add(hpLose);
			}
			else if (currentTheme.equals("Halloween")) {
				topPane.getChildren().add(halloweenLose);
			}
			else if (currentTheme.equals("Movies")) {
				topPane.getChildren().add(movieLose);
			}
			else if (currentTheme.equals("Java")) {
				topPane.getChildren().add(javaLose);
			}
			
			//If enter is pressed (not typed)
			scene.setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.ENTER) {
					newGame(currentTheme);
				}
			});
			//Make keys unclickable
			scene.setOnKeyTyped(null);
			
		}
		//Check if user solved the word
		else if (isSolved) {
			
			//Clear menu
			menu.getChildren().clear();
			menu.setOrientation(Orientation.VERTICAL);
			menu.setVgap(5);
			menu.getChildren().addAll(youWon, newWord);
			
			//Add gif to center pane
			if (currentTheme.equals("Harry Potter")) {
				topPane.getChildren().add(hpGif);
			}	
			else if (currentTheme.equals("Halloween")) {
				topPane.getChildren().add(halloweenWon);
			}
			else if (currentTheme.equals("Movies")) {
				topPane.getChildren().add(movieWin);
			}
			else if (currentTheme.equals("Java")) {
				topPane.getChildren().add(javaWin);
			}
			
			//If enter is pressed (not typed)
			scene.setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.ENTER) {
					newGame(currentTheme);
				}
			});
			//Make keys unclickable
			scene.setOnKeyTyped(null);
			
		}

	}
	
	public boolean checkforCensors() {
		
		for (int i = 0; i < currentWord.length(); i++) {
			
			if (currentWord.charAt(i) == '*') {
				return false;
			}
			
		}
		
		//Otherwise
		return true;
		
	}

}
