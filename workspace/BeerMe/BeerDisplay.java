


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

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
		//feedBackPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		
		onTap.setIcon(new ImageIcon("bin/onTapOption.png"));
		onTap.setBorder(new EmptyBorder(0,15,0,15));
		onTap.setContentAreaFilled(false);
		onTap.setCursor(new Cursor(Cursor.HAND_CURSOR));
		feedBackPanel.add(onTap);
		
		like.setIcon(new ImageIcon("bin/likeOption.png"));
		like.setBorder(new EmptyBorder(0,15,0,15));
		like.setContentAreaFilled(false);
		like.setCursor(new Cursor(Cursor.HAND_CURSOR));
		feedBackPanel.add(like);
		
		dislike.setIcon(new ImageIcon("bin/disLikeOption.png"));
		dislike.setBorder(new EmptyBorder(0,15,0,15));
		dislike.setContentAreaFilled(false);
		dislike.setCursor(new Cursor(Cursor.HAND_CURSOR));
		feedBackPanel.add(dislike);
		
		add(feedBackPanel,BorderLayout.SOUTH);
		
		
	}
	
	

}
