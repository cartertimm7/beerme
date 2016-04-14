


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class BeerDisplay extends JPanel{
	
	// *****************************************************************
	// *  Fields
	// *****************************************************************
	
	private Beer beer;
	
	//GUI Fields
	private JLabel beerName = new JLabel("Beer Name");
	private JPanel feedBackPanel = new JPanel();
	private JButton onTap = new JButton();
	private JButton like = new JButton();
	private JButton dislike = new JButton();

	
	// *****************************************************************
	// *  Constructor
	// *****************************************************************	
	public BeerDisplay(){
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(200,140));
		setBorder(new LineBorder(Color.gray, 1, true));
		setBackground(Color.white);
		
		Font labelFont = beerName.getFont();
		beerName.setFont(new Font(labelFont.getName(), Font.PLAIN, 15));
		beerName.setHorizontalAlignment(JLabel.CENTER);
		add(beerName,BorderLayout.CENTER);	
		
		feedBackPanel.setLayout(new FlowLayout());
		feedBackPanel.setOpaque(false);
		
		onTap.setIcon(new ImageIcon("bin/onTapOption.png"));
		onTap.setBorder(new EmptyBorder(0,15,0,15));
		onTap.setContentAreaFilled(false);
		onTap.setCursor(new Cursor(Cursor.HAND_CURSOR));
		onTap.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(Profile.searchOnTap(beer)){
        			Profile.removeOnTap(beer);
        			onTap.setIcon(new ImageIcon("bin/onTapOption.png"));
        			BeerMeGUI.loadOnTapList();
        		}
        		else{
        			Profile.addOnTap(beer);
        			onTap.setIcon(new ImageIcon("bin/onTapOptionSelected.png"));
        			BeerMeGUI.loadOnTapList();
        		}		
        	}
        });
		feedBackPanel.add(onTap);
		
		like.setIcon(new ImageIcon("bin/likeOption.png"));
		like.setBorder(new EmptyBorder(0,15,0,15));
		like.setContentAreaFilled(false);
		like.setCursor(new Cursor(Cursor.HAND_CURSOR));
		like.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(Profile.searchLike(beer)){
        			Profile.removeLike(beer);
        			like.setIcon(new ImageIcon("bin/likeOption.png"));
        		}
        		else{
        			Profile.addLike(beer);
        			like.setIcon(new ImageIcon("bin/likeOptionSelected.png"));
        		}
        	}
        });
		feedBackPanel.add(like);
		
		dislike.setIcon(new ImageIcon("bin/disLikeOption.png"));
		dislike.setBorder(new EmptyBorder(0,15,0,15));
		dislike.setContentAreaFilled(false);
		dislike.setCursor(new Cursor(Cursor.HAND_CURSOR));
		dislike.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(Profile.searchLike(beer)){
        			Profile.removeDislike(beer);
        			dislike.setIcon(new ImageIcon("bin/disLikeOption.png"));
        		}
        		else{
        			Profile.addDislike(beer);
        			dislike.setIcon(new ImageIcon("bin/disLikeOptionSelected.png"));
        		}
        	}
        });
		feedBackPanel.add(dislike);
		
		add(feedBackPanel,BorderLayout.SOUTH);
			
	}
	
	// *****************************************************************
	// *  Methods
	// *****************************************************************
	
	/**
	 * Sets the beer to be tied to the display
	 */
	public void setBeer(Beer newBeer){
		beer = newBeer;
		beerName.setText(beer.getName()); //Sets the beerName in the display
		
		//Resets Icons
		onTap.setIcon(new ImageIcon("bin/onTapOption.png"));
		like.setIcon(new ImageIcon("bin/likeOption.png"));
		dislike.setIcon(new ImageIcon("bin/disLikeOption.png"));
		
		if (Profile.searchLike(newBeer)){
			like.setIcon(new ImageIcon("bin/likeOptionSelected.png"));
		}
		if (Profile.searchDislike(newBeer)){
			dislike.setIcon(new ImageIcon("bin/disLikeOptionSelected.png"));
		}
		if (Profile.searchOnTap(newBeer)){
			onTap.setIcon(new ImageIcon("bin/onTapOptionSelected.png"));
		}
		
	}
	
	
	

}
