


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
import javax.swing.JTextArea;
import javax.swing.UIManager;
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
	
	//BeerAttributes Display
	private JPanel beerAtts = new JPanel();
	private JPanel bitterness = new JPanel();
	private JLabel bitterScale = new JLabel();
	private JLabel bitterLabel = new JLabel("Bitterness");
	private JLabel beerPic = new JLabel();
	private JPanel abv = new JPanel();
	private JLabel percentage = new JLabel();
	private JLabel abvLabel = new JLabel("ABV");
	
	// *****************************************************************
	// *  Constructor
	// *****************************************************************	
	public BeerDisplay(){
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(200,140));
		setBorder(new LineBorder(Color.gray, 1, true));
		setBackground(Color.white);
		
		//Attributes Display
		beerAtts.setLayout(new BorderLayout());
		beerAtts.setOpaque(false);
		
		abv.setLayout(new BorderLayout());
		abv.setBorder(new EmptyBorder(0,0,0,10));
		abv.setOpaque(false);		
		Font percentageFont = percentage.getFont();
		percentage.setFont(new Font(percentageFont.getName(), Font.PLAIN, 16));
		percentage.setHorizontalAlignment(JLabel.CENTER);
		abv.add(percentage,BorderLayout.CENTER);		
		Font abvLabelFont = abvLabel.getFont();
		abvLabel.setFont(new Font(abvLabelFont.getName(), Font.PLAIN, 8));
		abvLabel.setHorizontalAlignment(JLabel.CENTER);
		abv.add(abvLabel,BorderLayout.SOUTH);	
		beerAtts.add(abv,BorderLayout.EAST);
		
		beerPic.setIcon(new ImageIcon("bin/1.png"));
		beerPic.setHorizontalAlignment(JLabel.CENTER);
		beerPic.setBorder(new EmptyBorder(5,0,0,0));
		beerAtts.add(beerPic,BorderLayout.CENTER);
		
		bitterness.setLayout(new BorderLayout());
		bitterness.setBorder(new EmptyBorder(0,10,0,0));
		bitterness.setOpaque(false);
		Font bitterScaleFont = bitterScale.getFont();
		bitterScale.setFont(new Font(bitterScaleFont.getName(), Font.PLAIN, 16));
		bitterScale.setHorizontalAlignment(JLabel.CENTER);
		bitterness.add(bitterScale, BorderLayout.CENTER);	
		Font bitterLabelFont = bitterLabel.getFont();
		bitterLabel.setFont(new Font(bitterLabelFont.getName(), Font.PLAIN, 8));
		bitterLabel.setHorizontalAlignment(JLabel.CENTER);
		bitterness.add(bitterLabel,BorderLayout.SOUTH);
		beerAtts.add(bitterness,BorderLayout.WEST);	
		
		add(beerAtts,BorderLayout.NORTH);

		Font labelFont = beerName.getFont();
		beerName.setFont(new Font(labelFont.getName(), Font.PLAIN, 12));
		beerName.setHorizontalAlignment(JLabel.CENTER);		
		add(beerName,BorderLayout.CENTER);	
		
		feedBackPanel.setLayout(new FlowLayout());
		feedBackPanel.setOpaque(false);
		
		like.setIcon(new ImageIcon("bin/likeOption.png"));
		like.setBorder(new EmptyBorder(0,15,0,15));
		like.setContentAreaFilled(false);
		like.setCursor(new Cursor(Cursor.HAND_CURSOR));
		like.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(Profile.searchLike(beer)){
        			Profile.removeLike(beer);
        			like.setIcon(new ImageIcon("bin/likeOption.png"));
        			BeerMeGUI.loadProfilePrefs();
        			BeerMeGUI.loadLikesList();
        		}
        		else{
        			Profile.addLike(beer);
        			like.setIcon(new ImageIcon("bin/likeOptionSelected.png"));
        			BeerMeGUI.loadProfilePrefs();
        			BeerMeGUI.loadLikesList();
        		}
        	}
        });
		feedBackPanel.add(like);
		
		
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
		
		
		
		dislike.setIcon(new ImageIcon("bin/disLikeOption.png"));
		dislike.setBorder(new EmptyBorder(0,15,0,15));
		dislike.setContentAreaFilled(false);
		dislike.setCursor(new Cursor(Cursor.HAND_CURSOR));
		dislike.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(Profile.searchDislike(beer)){
        			Profile.removeDislike(beer);
        			dislike.setIcon(new ImageIcon("bin/disLikeOption.png"));
        			BeerMeGUI.loadDislikesList();
        		}
        		else{
        			Profile.addDislike(beer);
        			dislike.setIcon(new ImageIcon("bin/disLikeOptionSelected.png"));
        			BeerMeGUI.loadDislikesList();
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
		
		//Beer Attributes Display
		String bitterness = beer.getAttributes()[0] + "/10";
		bitterScale.setText(bitterness);
		
		String beerPicPath = "bin/" + beer.getAttributes()[3] + ".png";
		beerPic.setIcon(new ImageIcon(beerPicPath)); //Beer Color
		
		String abvPercentage = "~" + beer.getAttributes()[4] + "%";
		percentage.setText(abvPercentage);
		
		
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
