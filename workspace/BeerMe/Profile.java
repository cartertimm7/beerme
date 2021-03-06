
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Math;

public class Profile{
	
	public Profile() throws FileNotFoundException{
		fileAccess = new FileManager();
		likes = new ArrayList<Beer>();
		dislikes = new ArrayList<Beer>();
		onTap = new ArrayList<Beer>();
		
		Beer[] beerList = Discover.getBeerList();
		
		//add to likes ArrayList
		String[] fromFiles = fileAccess.getNames("likes.txt");
		for (int i = 0; i < fromFiles.length; i++){
			for (int j = 0; j < beerList.length; j++){
				if (beerList[j].getName().equals(fromFiles[i])){
					likes.add(beerList[j]);
					break;
				}
			}
		}
		likes.trimToSize();
		
		//add to dislikes ArrayList
		fromFiles = fileAccess.getNames("dislikes.txt");
		for (int i = 0; i < fromFiles.length; i++){
			for (int j = 0; j < beerList.length; j++){
				if (beerList[j].getName().equals(fromFiles[i])){
					dislikes.add(beerList[j]);
					break;
				}
			}
		}
		dislikes.trimToSize();
		
		//add to onTap ArrayList
		fromFiles = fileAccess.getNames("onTap.txt");
		System.out.println(Arrays.toString(fromFiles));
		System.out.println(fromFiles.length);
		System.out.println(beerList.length);
		for (int i = 0; i < fromFiles.length; i++){
			for (int j = 0; j < beerList.length; j++){
				if (beerList[j].getName().equals(fromFiles[i])){
					onTap.add(beerList[j]);
					break;
				}
			}
		}
		onTap.trimToSize();
	}
	
	public static Beer[] getLikes(){
		Beer[] returnArr = new Beer[likes.size()];
		returnArr = likes.toArray(returnArr);
		return returnArr;
	}
	
	public static Beer[] getDislikes(){
		Beer[] returnArr = new Beer[dislikes.size()];
		returnArr = dislikes.toArray(returnArr);
		return returnArr;
	}
	
	public static Beer[] getOnTap(){
		Beer[] returnArr = new Beer[onTap.size()];
		returnArr = onTap.toArray(returnArr);
		return returnArr;
	}
	
	public static void addLike(Beer beer){
		likes.add(beer);
		likes.trimToSize();
	}
	
	public static void addDislike(Beer beer){
		dislikes.add(beer);
		dislikes.trimToSize();
	}
	
	public static void addOnTap(Beer beer){
		onTap.add(beer);
		onTap.trimToSize();
	}
	
	public static void removeLike(Beer beer){
		likes.remove(beer);
		likes.trimToSize();
	}
	
	public static void removeDislike(Beer beer){
		dislikes.remove(beer);
		dislikes.trimToSize();
	}
	
	public static void removeOnTap(Beer beer){
		onTap.remove(beer);
		onTap.trimToSize();
	}
	
	public static Boolean searchLike(Beer beer){
		return likes.contains(beer);
	}
	
	public static Boolean searchDislike(Beer beer){
		return dislikes.contains(beer);
	}
	
	public static Boolean searchOnTap(Beer beer){
		return onTap.contains(beer);
	}
	
	public static int[] getPositiveAttributes(){
		int[] posAtts = new int[5];
		if (likes.size() == 0){
			for (int i = 0; i < 5; i++)
				posAtts[i] = 5;
			return posAtts;
		}
		
		for (int i = 0; i < likes.size(); i++){
			int[] attributes = likes.get(i).getAttributes();
			for (int j = 0; j < 5; j++){
				posAtts[j] += attributes[j];
			}
		}
		
		for (int i = 0; i < 5; i++){
			posAtts[i] = posAtts[i] / likes.size();
			posAtts[i] = (int) Math.round(posAtts[i]);
		}
		
		return posAtts;
	}
	
	public static int[] getNegativeAttributes(){
		int[] negAtts = new int[5];
		if (dislikes.size() == 0){
			for (int i = 0; i < 5; i++)
				negAtts[i] = 64;
			return negAtts;
		}
		
		for (int i = 0; i < dislikes.size(); i++){
			int[] attributes = dislikes.get(i).getAttributes();
			for (int j = 0; j < 5; j++){
				negAtts[j] += attributes[j];
			}
		}
		
		for (int i = 0; i < 5; i++){
			negAtts[i] = negAtts[i] / dislikes.size();
			negAtts[i] = (int) Math.round(negAtts[i]);
		}
		
		return negAtts;
	}
	
	public static Beer[] getOmits(){
		ArrayList<Beer> omits = new ArrayList<Beer>();
		omits.addAll(likes);
		omits.addAll(dislikes);
		omits.addAll(onTap);
		omits.trimToSize();
		
		Beer[] returnArr = new Beer[omits.size()];
		returnArr = omits.toArray(returnArr);
		return returnArr;
		
	}
	
	public static void saveAndQuit() throws IOException{
		
		Beer[] likedBeers = new Beer[likes.size()];
		Beer[] dislikedBeers = new Beer[dislikes.size()];
		Beer[] onTapBeers = new Beer[onTap.size()];
		likedBeers = likes.toArray(likedBeers);
		dislikedBeers = dislikes.toArray(dislikedBeers);
		onTapBeers = onTap.toArray(onTapBeers);
		fileAccess.quitAndSave(likedBeers, dislikedBeers, onTapBeers);
	}

	public static int[] getPreferences(){
		int[] attributes = new int[5];
		for (int i = 0; i < likes.size(); i++){
			for (int j = 0; j < 5; j++)
				attributes[j] += likes.get(i).getAttributes()[j];
		}

		for (int i = 0; i < likes.size(); i++)
			attributes[i] = attributes[i] / likes.size();

		return attributes;
	}	
	
	private static ArrayList<Beer> likes;
	private static ArrayList<Beer> dislikes;
	private static ArrayList<Beer> onTap;
	private static FileManager fileAccess;
}
