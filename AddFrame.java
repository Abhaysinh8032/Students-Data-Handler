import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.io.*;

class AddFrame extends JFrame
{
Container c;
JLabel labRno, labName, labSubject1, labSubject2 , labSubject3;
JTextField txtRno, txtName, txtSubject1, txtSubject2, txtSubject3;
JButton btnSave, btnBack;

AddFrame()
{
c = getContentPane();
c.setLayout(null);

labRno = new JLabel("Roll no.");
txtRno = new JTextField(30);
labName = new JLabel("Name");
txtName = new JTextField(30);
labSubject1 = new JLabel("enter Subject1 marks");
txtSubject1 = new JTextField(30);
labSubject2 = new JLabel("enter Subject2 marks");
txtSubject2 = new JTextField(30);
labSubject3 = new JLabel("enter Subject3 marks");
txtSubject3 = new JTextField(30);
btnSave = new JButton("Save");
btnBack = new JButton("Back");

Font f = new Font("Courier", Font.BOLD , 24);
labRno.setFont(f);
txtRno.setFont(f);
labName.setFont(f);
txtName.setFont(f);
labSubject1.setFont(f);
txtSubject1.setFont(f);
labSubject2.setFont(f);
txtSubject2.setFont(f);
labSubject3.setFont(f);
txtSubject3.setFont(f);
btnSave.setFont(f);
btnBack.setFont(f);

labRno.setBounds(50 , 20 , 300, 40);
txtRno.setBounds(300, 20, 300, 40);
labName.setBounds(50, 80 , 300, 40);
txtName.setBounds(300, 80, 300, 40);
labSubject1.setBounds(50 , 140 , 300, 40);
txtSubject1.setBounds(300, 140, 300, 40);
labSubject2.setBounds(50 , 200 , 300, 40);
txtSubject2.setBounds(300, 200, 300, 40);
labSubject3.setBounds(50 , 260 , 300, 40);
txtSubject3.setBounds(300, 260, 300, 40);
btnSave.setBounds(200 , 400 , 300, 40);
btnBack.setBounds(200, 460, 300, 40);



c.add(labRno);
c.add(txtRno);
c.add(labName);
c.add(txtName);
c.add(labSubject1);
c.add(txtSubject1);
c.add(labSubject2);
c.add(txtSubject2);
c.add(labSubject3);
c.add(txtSubject3);
c.add(btnSave);
c.add(btnBack);

ActionListener a1 = (ae) -> {
MainFrame a = new MainFrame();
dispose();
};
btnBack.addActionListener(a1);

ActionListener a2 = (ae) -> {
	try
	{
	int rno = Integer.parseInt(txtRno.getText());

	String name = txtName.getText();
	boolean result = name.matches("[a-zA-Z]+");
	int Subject1 = Integer.parseInt(txtSubject1.getText());
	int Subject2 = Integer.parseInt(txtSubject2.getText());
	int Subject3 = Integer.parseInt(txtSubject3.getText());
	Map<String,Integer> m;
	m = new TreeMap<String,Integer>();
	

	if((result)&(name.length()>1)&( 0<= Subject1)&(Subject1<101)&( 0<= Subject2)&(Subject2<101)&( 0<= Subject3)&( Subject3<101)&(rno>0)){
	try 
	{	
	    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
	    String url =  "jdbc:mysql://localhost:3306/kcs10july22";
	    String un = "root";
	    String pw = "8032";
	    Connection con = DriverManager.getConnection(url, un , pw);
	    String sql = "insert into student values(?,?,?,?,?)";
	    PreparedStatement pst = con.prepareStatement(sql);
	    pst.setInt(1, rno);
	    pst.setString(2, name);
	    pst.setInt(3, Subject1);
	    pst.setInt(4, Subject2);
	    pst.setInt(5, Subject3);
	    pst.executeUpdate();
	    JOptionPane.showMessageDialog(c,"record added");
	    con.close();
	}
	catch(SQLException e)
	{
		JOptionPane.showMessageDialog(c,"this rno saved  if you want to update then go to Update" );
	}

	


		   
	  } 
	//else if(){JOptionPane.showMessageDialog(null,"special symbols and numbers are not allowed in Name");}
	else if(name.equals(""))	{ JOptionPane.showMessageDialog(null,"please enter Name");}
	else if(!(name.length()>1))	{ JOptionPane.showMessageDialog(c,"invalid Name");}
	else if(!(rno>0))		{ JOptionPane.showMessageDialog(c,"invalid Roll no.");}
	else if(!( 0<= Subject1))	{ JOptionPane.showMessageDialog(c,"invalid marks of  Subject1");}
	else if(!(Subject1<101))	{ JOptionPane.showMessageDialog(c,"invalid marks of  Subject1");}
	else if(!(0<= Subject2))	{ JOptionPane.showMessageDialog(c,"invalid marks of  Subject2");}
	else if(!(Subject2<101))	{ JOptionPane.showMessageDialog(c,"invalid marks of  Subject2");}
	else  if(!(0<= Subject3))	{ JOptionPane.showMessageDialog(c,"invalid marks of  Subject3");}
	else if(!(Subject3<101))	{ JOptionPane.showMessageDialog(c,"invalid marks of  Subject3");}
	else			{ JOptionPane.showMessageDialog(c,"name can accept only aplphabets");}
		}
	catch(NumberFormatException  e)
	{
		JOptionPane.showMessageDialog(c, " Please check inputs empty or invalid rno1 " );

	}


};
btnSave.addActionListener(a2);


setTitle("Add Student");
setSize(800,600);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}
