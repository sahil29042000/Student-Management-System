import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



class UpdateFrame extends JFrame
{
	Container c;
	JButton btnSave,btnBack;
	JLabel lblRno, lblName,lblMarks;
	JTextField txtName,txtRno,txtMarks;
	
	
	UpdateFrame()
	{
		c=getContentPane();
		setLayout(new FlowLayout());
		lblName=new JLabel("Enter Name");
		txtName=new JTextField(15);
		lblRno=new JLabel("Enter roll number");
		lblMarks=new JLabel("Enter marks");
		txtRno=new JTextField(15);
		txtMarks=new JTextField(15);
		btnSave=new JButton("SAVE");
		btnBack=new JButton("BACK");
		c.add(lblRno);
		c.add(txtRno);
		c.add(lblName);
		c.add(txtName);
		c.add(lblMarks);
		c.add(txtMarks);
		c.add(btnSave);
		c.add(btnBack);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,300);
		setTitle("UPDATE");
		setLocationRelativeTo(null);
		setVisible(true);
		LoginFrameTest lft =new LoginFrameTest();
		
		ActionListener a1=(ae) ->{
			CrudFrame cf=new CrudFrame();
			dispose();
		
		};
		btnBack.addActionListener(a1);
		
		ActionListener a2=(ae) ->{
			String r=txtRno.getText();
			String name=txtName.getText();
			String m=txtMarks.getText();
			if(lft.isInteger(r))
			{
				if(lft.isInteger(m) )
				{
					int rno=Integer.parseInt(r);
					int marks=Integer.parseInt(m);
					if(rno>0 )
						
					{
						if(lft.isName(name) && !(name.equals("")))
						{
							if(marks<=100 && marks>=0)
							{
								Student s=new Student(rno,name,marks);
								DbHandler db=new DbHandler();
								db.updateStudent(s);
								txtMarks.setText("");
								txtRno.setText("");
								txtName.setText("");
							}
							else
							{
								JOptionPane.showMessageDialog(new JDialog(),"Marks cannot be more than 100 and less than 0");
								txtMarks.setText("");
								txtMarks.requestFocus();
	
							}
							
						}	
						else
						{
							JOptionPane.showMessageDialog(new JDialog(),"Name cannot be empty or contain integers");
							txtName.setText("");
							txtName.requestFocus();
	
						}	
						
					}	
					else{
						JOptionPane.showMessageDialog(new JDialog(),"Roll Number cannot be negative");
						txtRno.setText("");
						txtRno.requestFocus();
	
					}
					
					
				}
				else
				{
					JOptionPane.showMessageDialog(new JDialog(),"Marks have to be an integer");
					txtMarks.setText("");
					txtMarks.requestFocus();
				}
			
				
			}
			else{
				JOptionPane.showMessageDialog(new JDialog(),"Roll number has to be an integer");
				txtRno.setText("");
				txtRno.requestFocus();
			}
			
			
			
			
		};
		btnSave.addActionListener(a2);
	 
	
	
	}
}