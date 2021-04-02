import java.awt.Desktop;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;




public class RGSMain {
	

	public static void main(String[] args) throws IOException {
		
		Scanner scn = new Scanner(System.in);
		
		
		// random game selector todos
		// use folder names as categories
		//use game names for selections
		
		//so - load list ini of hierarchy according to target folder
		// then - would you like to select a specific category - list opts
		// then - random game roll
		// then - would you like to load this game? y/n
		// n reroll
		// y load game and end program
		
		//using default filepath for now, option to change with setters if there for later
		File[] categories = getCategoriesFromFilePath(new MainSetup().getFilepath());
		List<File> categoriesMutable = Arrays.asList(categories);
		int selection;
		ArrayList<File> allGamesMutable = new ArrayList<>();
		File selectedGame = null;
		
		
		while(true) {
			
			System.out.println("Random Game Selector");
			System.out.println("Please select a category number below to select or remove a category.  Or type '0' to randomize games from all categories.");
			
			
			for(int i = 1; i < categoriesMutable.size() + 1; i++) {
				
				System.out.println(i + ". " + categoriesMutable.get(i-1).getName());
				
			}
			
			selection = scn.nextInt();
			scn.nextLine();
			
			if(selection == 0) {
				
				Boolean rollgame = true;
				
				do{
					allGamesMutable = getGameList(categoriesMutable);
					selectedGame = randomGameSelector(allGamesMutable);
					System.out.println("Run this game? (y/n) " + selectedGame.getName());
					String input = scn.nextLine();
					rollgame = true;
					
					if(input.equalsIgnoreCase("y")) { 
						rollgame = false;
						break;
					} else {
					}
					
				} while(rollgame);
				
				break;
				
				
			} else if(selection > categoriesMutable.size()) {
				System.out.println("No such category, please enter a valid selection.");
			} else {
				
				System.out.println("To roll from only this category enter '1'.");
				
				int remRoll = scn.nextInt();
				scn.nextLine();
				if(remRoll == 1) {
					//roll from category
					File sCatg = categories[selection-1];
					List<File> asList = new ArrayList<>();
					asList.add(sCatg);
					
					Boolean rollgame = true;
					
					while(rollgame){
						allGamesMutable = getGameList(asList);
						selectedGame = randomGameSelector(allGamesMutable);
						System.out.println("Run this game? (y/n) " + selectedGame.getName());
						String input = scn.nextLine();
						
						
						if(input.equalsIgnoreCase("y")) { 
							rollgame = false;
							break;
						} else {
							rollgame = true;
						}
						
					};
					
					break;
					
					
				} else {
					System.out.println("invalid selection, please try again");
				}
				
				
				break;
			}
		} 
		runGame(selectedGame);
	}
	
	public static File[] getCategoriesFromFilePath(String filepath){
		
		File[] categories = new File(filepath).listFiles(File::isDirectory);		
		
		return categories;
		
	}
	
	public static ArrayList<File> getGameList(List<File> categoriesMutable){
		ArrayList<File> allGames = new ArrayList<>();
		
		for(File folder : categoriesMutable) {
			
			/*
			 * System.out.println(folder.getAbsolutePath()); try {
			 * System.out.println(folder.getCanonicalPath()); } catch (IOException e) { e.printStackTrace(); }
			 * System.out.println(folder.getPath());
			 */
	        // try-catch block to handle exceptions
	        try {
	            File f = folder;

	            FilenameFilter filter = new FilenameFilter() {
	                @Override
	                public boolean accept(File f, String name) {
	                    // We want to find only .url files
	                    return name.endsWith(".url");
	                }
	            };

	        
	            File[] games = f.listFiles(filter);
	            
	            for(File game : games) {
					System.out.println(game);
					allGames.add(game);
				}

	        } catch (Exception e) {
	            System.err.println(e.getMessage());
	        }
			
			
		}
		//System.out.println(allGames.toString());
		
		return allGames;
		
	}
	
	public static File randomGameSelector(ArrayList<File> allGames) {

		Random random = new Random();
		
		File randomGame = allGames.get(random.nextInt(allGames.size()));
		
		return randomGame;
		
	}
	
	public static void runGame(File selectedGame) throws IOException {
	
		if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
        
        Desktop desktop = Desktop.getDesktop();
        if(selectedGame.exists()) desktop.open(selectedGame);
        
    }
		
}
	
	 


