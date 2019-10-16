import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



class CrudFrame extends JFrame
{
	Container c;
	JButton btnAdd,btnView,btnUpdate,btnDelete,btnExit;
	
	
	
	CrudFrame()
	{
		c=getContentPane();
		setLayout(new FlowLayout());
		setSize(300,300);
		
		btnAdd=new JButton("Add");
		btnView=new JButton("View");
		btnUpdate=new JButton("Update");
		btnDelete=new JButton("Delete");
		btnExit=new JButton("LOG OUT");
		
		
		c.add(btnAdd);
		c.add(btnView);
		c.add(btnUpdate);
		c.add(btnDelete);
		c.add(btnExit);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("CRUD");
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		
		ActionListener a1 = (ae) -> {
			AddFrame af =new AddFrame();
			dispose();
			
		};
		btnAdd.addActionListener(a1);
		
		
		ActionListener a2 = (ae) -> {
			ViewFrame vf =new ViewFrame();
			dispose();
			
		};
		btnView.addActionListener(a2);
		
		
		ActionListener a3 = (ae) -> {
			UpdateFrame uf =new UpdateFrame();
			dispose();
			
		};
		btnUpdate.addActionListener(a3);
		
		
		
		ActionListener a4 = (ae) -> {
			DeleteFrame af =new DeleteFrame();
			dispose();
			
		};
		btnDelete.addActionListener(a4);
	
		ActionListener a5 = (ae) -> {
			int dialogbtn=JOptionPane.showConfirmDialog(new JDialog(),"Are you Sure you want to log out","LOG OUT",JOptionPane.YES_NO_OPTION);
			if(dialogbtn==JOptionPane.YES_OPTION)
			{
				dispose();
				LoginFrame lf =new LoginFrame();
			}	
			
			
		};
		btnExit.addActionListener(a5);
	}



}
