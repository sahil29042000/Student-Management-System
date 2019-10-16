import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.*;
import java.sql.*;
import java.util.*;

class SimpleAudioPlayer
{
	public SimpleAudioPlayer(String filepath) throws UnsupportedAudioFileException, 
        IOException, LineUnavailableException  
    { 
		
        // create AudioInputStream object
        AudioInputStream audioInputStream =  
                AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
          
        // create clip reference 
        Clip clip = AudioSystem.getClip(); 
          
        // open audioInputStream to the clip 
        clip.open(audioInputStream); 
          
        
		clip.start();	
    } 
}
class LoginFrame extends JFrame
{
	Container c;
	JButton btnLogin;
	JLabel lblUserName, lblPassword;
	JTextField txtName;
	JPasswordField pass;
	String user="sahil";
	String password="abc123";
	LoginFrame()
	{
		c=getContentPane();
		setLayout(new FlowLayout());
		lblUserName=new JLabel("Username");
		lblPassword=new JLabel("Password");
		txtName=new JTextField(15);
		pass =new JPasswordField(15);
		pass.setEchoChar('*');
		btnLogin=new JButton("Login");	
				
		c.add(lblUserName);
		c.add(txtName);
		c.add(lblPassword);
		c.add(pass);
		c.add(btnLogin);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250,300);
		setTitle("Login");
		setLocationRelativeTo(null);
		setVisible(true);
		
		ActionListener a1 = (ae) ->{
			String s1=txtName.getText();
			String s2=pass.getText();
			if(s1.isEmpty())
			{
				try{
						SimpleAudioPlayer s=new SimpleAudioPlayer("alert.wav");
					}
				catch(Exception e)
				{
					System.out.println("Issue with playing sound");
					e.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(new JDialog(),"USERNAME cannot be empty");
				txtName.requestFocus();
			}	
			else if(s2.isEmpty())
			{
				
				try{
						SimpleAudioPlayer s=new SimpleAudioPlayer("alert.wav");
					}
				catch(Exception e)
				{
					System.out.println("Issue with playing sound");
					e.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(new JDialog(),"PAASSWORD cannot be empty");
				pass.requestFocus();
				
				
			}	
			else if(s1.equals(user))
			{	if(s2.equals(password))
				{
					try{
						SimpleAudioPlayer s=new SimpleAudioPlayer("alert3.wav");
					}
					catch(Exception e)
					{
						System.out.println("Issue with playing sound");
						e.printStackTrace();
					}
	
					JOptionPane.showMessageDialog(new JDialog(),"login SUCCESSFUL");								
					CrudFrame cf=new CrudFrame();
					dispose();
				}
				else 
				{
					try{
						SimpleAudioPlayer s=new SimpleAudioPlayer("alert.wav");
					}
					catch(Exception e)
					{
						System.out.println("Issue with playing sound");
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(new JDialog(),"login UNSUCCESSFUL Enter password again");
					pass.requestFocus();
					pass.setText("");
				}	
				
			}
			else
			{
				
				try{
						SimpleAudioPlayer s=new SimpleAudioPlayer("alert.wav");
					}
				catch(Exception e)
				{
					System.out.println("Issue with playing sound");
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(new JDialog(),"login UNSUCCESSFUL Enter Username again");
				txtName.requestFocus();
				txtName.setText("");
				pass.setText("");
	
				
			}
		};
		btnLogin.addActionListener(a1);
		
	}



}

class LoginFrameTest
{
	public static void main(String args[])
	{
		LoginFrame l=new LoginFrame();
		
	}
	public boolean isInteger(String n)
	{
		try{
			int m=Integer.parseInt(n);
			
		}
		catch(NumberFormatException nfe){
			return false;
			
		}
		return true;
	}
	public boolean isName(String s)
	{
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)=='0'||s.charAt(i)=='1'||s.charAt(i)=='2'||s.charAt(i)=='3'||
			s.charAt(i)=='4'||s.charAt(i)=='5'||s.charAt(i)=='6'||s.charAt(i)=='7'
			||s.charAt(i)=='8'||s.charAt(i)=='9'
			)
			{
				return false;
			}	
			
		}	
		return true;
		
	}
	
}

class DbHandler
{
	public void addStudent(Student s)
	{
		Connection con=null;
		
		try
		{	
			System.out.println("Loading the driver");
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			System.out.println("Driver loaded");
					
					
			System.out.println("Trying to connect");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			System.out.println("connected");
					
			
					
			String sql="insert into student1 values(?,?,?)";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1,s.getRno());
			pst.setString(2,s.getName());
			pst.setInt(3,s.getMarks());
			int dialogbtn=JOptionPane.showConfirmDialog(new JDialog(),"Are you Sure you want to insert record","INSERT RECORD",JOptionPane.YES_NO_OPTION);
			if(dialogbtn==JOptionPane.YES_OPTION)
			{
				AddFrame af =new AddFrame();
			}	
			int result=pst.executeUpdate();
			System.out.println(result+ " records inserted ");
			
			JOptionPane.showMessageDialog(new JDialog(),result+ " records inserted ");
			pst.close();
					
					
		}
		catch(SQLException e)
		{
			System.out.println("ISSUE "+e);
			JOptionPane.showMessageDialog(new JDialog(),"ISSUE "+e);
		}
		finally
		{
			try
			{
				System.out.println("Trying to close the connection");
				if(con!=null)
				{
					con.close();
		
				}	
				System.out.println("connection closed");
				
					
			}
			catch(SQLException e)
			{
				System.out.println("ISSUE "+e);
				JOptionPane.showMessageDialog(new JDialog(),"ISSUE "+e);	
			}
		}
		
		
	
		
	}
	
	
	public String viewStudent()
	{
		Connection con=null;
		StringBuffer sb=new StringBuffer();
		try
		{
			System.out.println("Loading the driver");
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			System.out.println("Driver loaded");
					
					
			System.out.println("Trying to connect");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			System.out.println("connected");
					
			Statement stmt=con.createStatement();
					
					
			String sql="select * from student1";
			ResultSet rs= stmt.executeQuery(sql);
			while(rs.next())
			{
				int rno=rs.getInt(1);
				String name=rs.getString(2);
				int marks=rs.getInt(3);
				System.out.println("Rno: "+rno+" NAME: "+name+" MARKS= "+marks);
				sb.append("Rno: "+rno+" NAME: "+name+" MARKS= "+marks+"\n");
			}	
			
		
			rs.close();
			stmt.close();
			
		}
		catch(SQLException e)
		{
			System.out.println("ISSUE "+e);
			JOptionPane.showMessageDialog(new JDialog(),"ISSUE "+e);
			
		}
		finally
		{
			try
			{
				System.out.println("Trying to close the connection");
				if(con!=null)
				{
					con.close();
						
				}	
				System.out.println("connection closed");
				
			
			}
			catch(SQLException e)
			{
				System.out.println("ISSUE "+e);
				JOptionPane.showMessageDialog(new JDialog(),"ISSUE "+e);
			}
					
		}
				
		return sb.toString();
	}

	public void updateStudent(Student s)
	{
		Connection con=null;
		
		try
		{	
			System.out.println("Loading the driver");
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			System.out.println("Driver loaded");
					
					
			System.out.println("Trying to connect");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			System.out.println("connected");
					
			
					
			String sql="update student1  set name=?, marks=? where rno=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(3,s.getRno());
			pst.setString(1,s.getName());
			pst.setInt(2,s.getMarks());
			int result=pst.executeUpdate();
			int dialogbtn=JOptionPane.showConfirmDialog(new JDialog(),"Are you Sure you want to update record","UPDATE RECORD",JOptionPane.YES_NO_OPTION);
			if(dialogbtn==JOptionPane.YES_OPTION)
			{
				UpdateFrame uf =new UpdateFrame();
			}	
			System.out.println(result+ " records inserted ");
			JOptionPane.showMessageDialog(new JDialog(),result+ " records updated ");
			pst.close();
					
					
		}
		catch(SQLException e)
		{
			System.out.println("ISSUE "+e);
			JOptionPane.showMessageDialog(new JDialog(),"ISSUE "+e);
		}
		finally
		{
			try
			{
				System.out.println("Trying to close the connection");
				if(con!=null)
				{
					con.close();
		
				}	
				System.out.println("connection closed");
				
					
			}
			catch(SQLException e)
			{
				System.out.println("ISSUE "+e);
				JOptionPane.showMessageDialog(new JDialog(),"ISSUE "+e);	
			}
		}
	}
	public void deleteStudent(Student s)
	{
		Connection con=null;
		
		try
		{	
			System.out.println("Loading the driver");
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			System.out.println("Driver loaded");
					
					
			System.out.println("Trying to connect");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			System.out.println("connected");
					
			
					
			String sql="delete from student1 where rno=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1,s.getRno());
			
			int result=pst.executeUpdate();
			int dialogbtn=JOptionPane.showConfirmDialog(new JDialog(),"Are you Sure you want to delete record","DELETE RECORD",JOptionPane.YES_NO_OPTION);
			if(dialogbtn==JOptionPane.YES_OPTION)
			{
				DeleteFrame df =new DeleteFrame();
			}	
			System.out.println(result+ " records deleted");
			JOptionPane.showMessageDialog(new JDialog(),result+ " records deleted ");
			pst.close();
					
					
		}
		catch(SQLException e)
		{
			System.out.println("ISSUE "+e);
			JOptionPane.showMessageDialog(new JDialog(),"ISSUE "+e);
		}
		finally
		{
			try
			{
				System.out.println("Trying to close the connection");
				if(con!=null)
				{
					con.close();
		
				}	
				System.out.println("connection closed");
				
					
			}
			catch(SQLException e)
			{
				System.out.println("ISSUE "+e);
				JOptionPane.showMessageDialog(new JDialog(),"ISSUE "+e);	
			}
		}
	}
	
}