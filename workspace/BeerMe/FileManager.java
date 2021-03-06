import java.util.*;
import java.io.*;

public class FileManager {
	
	//used only to get initial attributes to load beers into beerList
	public int[][] getAttributes() throws FileNotFoundException {
		int numOfBeers = this.getNames("beerList.csv").length;
		int[][] toReturn = new int[numOfBeers][5];		//5 attributes per beer
		File inFile = new File ("beerList.csv");		//master beer list
	    Scanner scan = new Scanner (inFile);
	    String[] info;
	    String csvSplitBy = ",";
	    scan.nextLine();                        //skip headers
	    for(int i = 0; i < numOfBeers; i++) {
	    	info = scan.nextLine().split(csvSplitBy);
	    	for(int j=1; j < 6; j++) {			//6 is num of attributes (including name)
	    		toReturn[i][j-1] = Integer.parseInt(info[j]);
	    	}
	    }
	    scan.close();
	    return toReturn;
	}
	
	//used to load beers into beerList in class Discover, also to create omitList for findBeers
	public String[] getNames(String filename) throws FileNotFoundException {
		String[] toReturn;
		File inFile = new File (filename);		//fileName is the master beer csv
	    Scanner scan = new Scanner (inFile);
	    
	    //get length
	    int len = 0;
	    while (scan.hasNextLine()){
	    	len++;
	    	scan.nextLine();
	    }
	    //reset scanner to top
	    Scanner scan2 = new Scanner (inFile);
    	//read into toReturn
    	String[] parts;
    	parts = filename.split("\\.");
    	//len--;	//NEW
		if (parts[1].equals("csv")) {
			//CSV FILE
			len--;
	    	toReturn = new String[len];
			scan2.nextLine(); 					//skip header
			for (int i = 0; i < len; i++) {	
				String[] temp = scan2.nextLine().split(",");
				toReturn[i]	= temp[0]; 
			}
		} else {
	    	toReturn = new String[len];
			//TXT FILE
			for (int i = 0; i < len; i++) {
				toReturn[i] = scan2.nextLine();
			}
		}
		scan.close();
		scan2.close();
		return toReturn;
	}
	
	
	public void quitAndSave (Beer[] likes, Beer[] dislikes, Beer[] onTap) throws IOException {
		//writer for likes
		File l = new File("likes.txt");
	    FileWriter lWriter = new FileWriter (l,false);
	    PrintWriter lPrinter = new PrintWriter (lWriter);
	    
	    //writer for dislikes
		File d = new File("dislike.txt");
	    FileWriter dWriter = new FileWriter (d,false);
	    PrintWriter dPrinter = new PrintWriter (dWriter);
		
	    //writer for onTap
		File o = new File("onTap.txt");
	    FileWriter oWriter = new FileWriter (o,false);
	    PrintWriter oPrinter = new PrintWriter (oWriter);
		
	    for (int i = 0; i < likes.length; i++) {
	    	lPrinter.println(likes[i].getName());
	    }
	    for (int j = 0; j < dislikes.length; j++) {
	    	dPrinter.println(dislikes[j].getName());
	    }
	    for (int k = 0; k < onTap.length; k++) {
	    	oPrinter.println(onTap[k].getName());
	    }
	    
	    lPrinter.close();
	    dPrinter.close();
	    oPrinter.close();
	}
	
}
	
	
