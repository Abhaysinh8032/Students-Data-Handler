import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class UpdateFrameRoll extends JFrame
{
Container c;
JLabel labRno, labNRno;
JTextField txtRno, txtNRno;
JButton btnUpdate, btnBack;

UpdateFrameRoll()
{
c = getContentPane();
c.setLayout(null);

labRno = new JLabel("Roll no.");
txtRno = new JTextField(35);
labNRno = new JLabel("New Roll no.");
txtNRno = new JTextField(35);
btnUpdate = new JButton("Update");
btnBack = new JButton("Back");

Font f = new Font("Courier", Font.BOLD , 24);
labRno.setFont(f);
txtRno.setFont(f);
labNRno.setFont(f);
txtNRno.setFont(f);
btnUpdate.setFont(f);
btnBack.setFont(f);

labRno.setBounds(50 , 20 , 300, 40);
txtRno.setBounds(300, 20, 300, 40);
labNRno.setBounds(50, 80 , 300, 40);
txtNRno.setBounds(300, 80, 300, 40);
btnUpdate.setBounds(200 , 200 , 300, 40);
btnBack.setBounds(200, 300, 300, 40);


c.add(labRno);
c.add(txtRno);
c.add(labNRno);
c.add(txtNRno);
c.add(btnUpdate);
c.add(btnBack);

ActionListener a1 = (ae) -> {
UpdateFrame a = new UpdateFrame();
dispose();
};
btnBack.addActionListener(a1);

ActionListener a2 = (ae) -> {
	try
	{
	int rno = Integer.parseInt(txtRno.getText());
	int nrno = Integer.parseInt(txtNRno.getText());
	if((rno>0)&(nrno>0)){
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

	    String sql = " update student set rno = ?  where rno = ?";
	    PreparedStatement pst = con.prepareStatement(sql);
	    pst.setInt(1, nrno);
	    pst.setInt(2, rno);
	    pst.executeUpdate();
	    JOptionPane.showMessageDialog(c,"record updated");
	    con.close();
	    }else{JOptionPane.showMessageDialog(c,"this rno is not exist ");}
	}
	catch(SQLException e)
	{
		JOptionPane.showMessageDialog(c,"Roll no. not exists" + e);
	}
	} else{ JOptionPane.showMessageDialog(c,"invalid  roll no.");}
	}catch(NumberFormatException  e)
	{
		JOptionPane.showMessageDialog(c, " Please check inputs empty or invalid rno./marks " );

	}
};
btnUpdate.addActionListener(a2);


setTitle("Update Roll no. of Student");
setSize(800,500);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}
