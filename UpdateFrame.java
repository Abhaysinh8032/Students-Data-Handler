import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class UpdateFrame extends JFrame
{
Container c;
JLabel labRno, labName, labSubject1, labSubject2 , labSubject3,labOnly;
JTextField txtRno, txtName, txtSubject1, txtSubject2, txtSubject3;
JButton btnUpdate, btnBack, btnOnly;

UpdateFrame()
{
c = getContentPane();
c.setLayout(null);

labRno = new JLabel("Roll no.");
txtRno = new JTextField(35);
labName = new JLabel("Name");
txtName = new JTextField(35);
labSubject1 = new JLabel("Subject1");
txtSubject1 = new JTextField(35);
labSubject2 = new JLabel("Subject2");
txtSubject2 = new JTextField(35);
labSubject3 = new JLabel("Subject3");
txtSubject3 = new JTextField(35);
labOnly = new JLabel("For only Roll no.");
btnOnly = new JButton(" Only Roll no.");
btnUpdate = new JButton("Update");
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
labOnly.setFont(f);
btnOnly.setFont(f);
btnUpdate.setFont(f);
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
labOnly.setBounds(50 , 320 , 300, 40);
btnOnly.setBounds(300, 320, 300, 40);
btnUpdate.setBounds(200 , 400 , 300, 40);
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
c.add(labOnly);
c.add(btnOnly);
c.add(btnUpdate);
c.add(btnBack);

ActionListener a1 = (ae) -> {
MainFrame a = new MainFrame();
dispose();
};
btnBack.addActionListener(a1);

ActionListener a3 = (ae) -> {
UpdateFrameRoll b = new UpdateFrameRoll();
dispose();
};
btnOnly.addActionListener(a3);


ActionListener a2 = (ae) -> {
	try
	{
	int rno = Integer.parseInt(txtRno.getText());
	String name = txtName.getText();
	boolean result = name.matches("[a-zA-Z]+");
	int Subject1 = Integer.parseInt(txtSubject1.getText());
	int Subject2 = Integer.parseInt(txtSubject2.getText());
	int Subject3 = Integer.parseInt(txtSubject3.getText());
	if((result)&(name.length()>1)&(rno>0)&(Subject1>=0)&(Subject1<101)&(Subject2>=0)&(Subject2<101)&(Subject3>=0)&(Subject3<101)){
	try 
	{	
	    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
	    String url =  "jdbc:mysql://localhost:3306/kcs10july22";
	    String un = "root";
	    String pw = "8032";
	    Connection con = DriverManager.getConnection(url, un , pw);
	
	    String s1 ="select * from student where rno=?";
	    PreparedStatement p1=con.prepareStatement(s1);
	    p1.setInt(1,rno);
	    ResultSet rs=p1.executeQuery();
	    if(rs.next()){

	    String sql = " update student set rno = ? , name =? , Subject1 = ? , Subject2 = ? , Subject3 = ? where rno = ?";
	    PreparedStatement pst = con.prepareStatement(sql);
	    pst.setInt(1, rno);
	    pst.setString(2, name);
	    pst.setInt(3, Subject1);
	    pst.setInt(4, Subject2);
	    pst.setInt(5, Subject3);
	    pst.setInt(6, rno);
	    pst.executeUpdate();
	    JOptionPane.showMessageDialog(c,"record updated");
	    con.close();
	    }else{JOptionPane.showMessageDialog(c,"this rno is not exist ");}
	}
	catch(SQLException e)
	{
		JOptionPane.showMessageDialog(c,"issue" + e);
	}
	}
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
	}catch(NumberFormatException  e)
	{
		JOptionPane.showMessageDialog(c, " Please check inputs empty or invalid rno./marks " );

	}
};
btnUpdate.addActionListener(a2);


setTitle("Update Student");
setSize(800,600);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}
