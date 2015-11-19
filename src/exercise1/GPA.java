//Shakir Abdullah
//300759758

package exercise1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GPA extends JFrame{
	JButton calculateButton;
	JComboBox <String>numberOfCoursesCombo;
	JComboBox <String>creditHoursCombo [] = new JComboBox [7];
	JComboBox <String>gradeCombo [] = new JComboBox [7];

	JLabel creditsL, currentGPA_L, 
			numberOfCoursesL, 
			itemNrL, courseCodeL, creditHoursL, gradeL,
			finalGPA_L;
	JLabel itemNr2L [] = new JLabel [7];
	
	JPanel topPanel, coursePanel, bottomPanel,
			topPanelUpper, topPanelLower;
	JTextField creditsF, currentGPA_F;
	JTextField courseCodeTF [] = new JTextField[7];

	String grade[] = {"A+ 90-100%","A 80-89%","B+ 75-79%","B 70-74%",
            "C+ 65-69%","C 60-64%","D+ 55-59%","D 50-54%","F 0-49%"};
	String hours[] = {"1","2","3","4"};
	
	double GPA = 0.0;
	public GPA()
	{
		setLayout( new BorderLayout()); 
		bottomPanel = new JPanel();
		topPanel = new JPanel();
		coursePanel = new JPanel();
		topPanelUpper = new JPanel();
		topPanelLower = new JPanel();
		
		creditsL = new JLabel ("Credit hours earned: ");
		creditsF = new JTextField (4);
		currentGPA_L = new JLabel ("Current GPA");
		currentGPA_F= new JTextField (4);
		numberOfCoursesL = new JLabel("Number of Courses");
		String [] courses = {"0", "1", "2", "3", "4", "5", "6", "7"};
		numberOfCoursesCombo = new JComboBox <String>(courses);
		
		topPanelUpper.add(creditsL);
		topPanelUpper.add(creditsF);
		topPanelUpper.add(currentGPA_L);
		topPanelUpper.add(currentGPA_F);
		topPanel.setLayout(new BorderLayout());
		topPanel.add("North", topPanelUpper);
		topPanelLower.add(numberOfCoursesL);
		topPanelLower.add(numberOfCoursesCombo);
		numberOfCoursesCombo.addActionListener(new comboHandler());
		topPanel.add("South", topPanelLower);
		
		itemNrL = new JLabel("Nr");
		courseCodeL = new JLabel("Course Code");
		creditHoursL = new JLabel("Credit hours");
		gradeL = new JLabel("Grade");
		coursePanel.setLayout(new GridLayout(0, 4));
		coursePanel.add(itemNrL);
		coursePanel.add(courseCodeL);
		coursePanel.add(creditHoursL);
		coursePanel.add(gradeL);
		for (int i=0; i<24; i++){
			coursePanel.add(new JLabel());
		}
		
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		finalGPA_L = new JLabel ("");
		calculateButton = new JButton ("Calculate");
		
		
		bottomPanel.add(finalGPA_L);
		bottomPanel.add(calculateButton);
		add("North", topPanel);
		add("Center", coursePanel);
		add("South", bottomPanel);
		
		calculateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
		      {
				double qualityPoints=0;
				double creditHours = 0;
				double totalCreditHours = 0;
				double grade = 0;
		        for(int i =0;i<numberOfCoursesCombo.getSelectedIndex();i++)
		        {
		        	creditHours = Double.parseDouble(creditHoursCombo[i].getSelectedItem().toString());
		        	if(gradeCombo[i].getSelectedIndex()==0)
		        	{
		        		grade = 4.5;
		        	}
		        	else if(gradeCombo[i].getSelectedIndex()==1)
		        	{
		        		grade = 4.0;
		        	}
		        	else if(gradeCombo[i].getSelectedIndex()==2)
		        	{
		        		grade = 3.5;
		        	}
		        	else if(gradeCombo[i].getSelectedIndex()==3)
		        	{
		        		grade = 3.0;
		        	}
		        	else if(gradeCombo[i].getSelectedIndex()==4)
		        	{
		        		grade = 2.5;
		        	}
		        	else if(gradeCombo[i].getSelectedIndex()==5)
		        	{
		        		grade = 2.0;
		        	}
		        	else if(gradeCombo[i].getSelectedIndex()==6)
		        	{
		        		grade = 1.5;
		        	}
		        	else if(gradeCombo[i].getSelectedIndex()==7)
		        	{
		        		grade = 1.0;
		        	}
		        	else
		        	{
		        		grade=0;
		        	}
		        	
		        	totalCreditHours+= creditHours;
		        	
		        	
		        	qualityPoints+=creditHours*grade;
		        	
		        	
		        }
		        GPA=qualityPoints/totalCreditHours;
				finalGPA_L.setText("Your GPA is: "+Double.toString(GPA));
		      }
		});
	}

	public static void main(String[] args) {
		GPA frame = new GPA();
		frame.setTitle("GPA Calculator");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setSize(300,300);
		frame.setVisible(true);
	}
	
	private class comboHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == numberOfCoursesCombo){
				coursePanel.removeAll();
				coursePanel.add(itemNrL);
				coursePanel.add(courseCodeL);
				coursePanel.add(creditHoursL);
				coursePanel.add(gradeL);
				for(int i = 0; i <  numberOfCoursesCombo.getSelectedIndex(); i++){
					coursePanel.add(new JLabel(Integer.toString(i+1)));
					courseCodeTF[i] = new JTextField();
					coursePanel.add(courseCodeTF[i]);
					creditHoursCombo[i]=new JComboBox<String>(hours);
					coursePanel.add(creditHoursCombo[i]);
					gradeCombo[i] = new JComboBox<String>(grade);
					coursePanel.add(gradeCombo[i]);
				}
				coursePanel.revalidate();
			}
		}
	}
}
