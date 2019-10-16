import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DeleteFrame extends JFrame
{
	Container c;
	JButton btnBack,btnDelete;
	JTextField txtRno;
	JLabel lblRno;
	DeleteFrame()
	{
		c=getContentPane();
		setLayout(new FlowLayout());
		btnDelete =new JButton("DELETE");
		btnBack =new JButton("BACK");
		txtRno = new JTextField(15);
		lblRno =new JLabel("Enter roll number");
		c.add(lblRno);
		c.add(txtRno);
		c.add(btnBack);
		c.add(btnDelete);
		setTitle("DELETE");
		setSize(300,300);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoginFrameTest lft =new LoginFrameTest();
		ActionListener a1=(ae) ->{
			CrudFrame cf=new CrudFrame();
			dispose();
		
		};
		btnBack.addActionListener(a1);
		ActionListener a2=(ae) -> {
			String s1=txtRno.getText();
			if(!s1.isEmpty())
			{
				if(lft.isInteger(s1))
				{
				
				int rno=Integer.parseInt(s1);
				if(rno>0)
				{
					Student s=new Student();
					s.setRno(rno);
					DbHandler db=new DbHandler();
					db.deleteStudent(s);
					txtRno.setText("");
				}
				else{
						JOptionPane.showMessageDialog(new JDialog(),"Roll Number cannot be negative");
						txtRno.setText("");
						txtRno.requestFocus();
	
					}
			
			
				
			}
			else
			{
				JOptionPane.showMessageDialog(new JDialog(),"Roll number has to be an integer");
				txtRno.setText("");
				txtRno.requestFocus();
			}
		}	
		else
		{
			JOptionPane.showMessageDialog(new JDialog(),"Roll number cannot be empty");
				txtRno.setText("");
				txtRno.requestFocus();
		}	
		};
		btnDelete.addActionListener(a2);
	}

}