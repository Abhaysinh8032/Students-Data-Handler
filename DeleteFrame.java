import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class DeleteFrame extends JFrame
{
Container c;
JLabel labRno;
JTextField txtRno;
JButton btnDelete, btnBack;

DeleteFrame()
{
c = getContentPane();
c.setLayout(null);

labRno = new JLabel("Roll no.");
txtRno = new JTextField(20);
btnDelete = new JButton("delete");
btnBack = new JButton("Back");

Font f = new Font("Courier", Font.BOLD , 24);
labRno.setFont(f);
txtRno.setFont(f);
btnDelete.setFont(f);
btnBack.setFont(f);

labRno.setBounds(150 , 20 , 300, 40);
txtRno.setBounds(50, 80, 300, 40);
btnDelete.setBounds(50 , 200 , 300, 40);
btnBack.setBounds(50, 280, 300, 40);

c.add(labRno);
c.add(txtRno);
c.add(btnDelete);
c.add(btnBack);

ActionListener a4 = (ae) -> {
MainFrame a = new MainFrame();
dispose();
};
btnBack.addActionListener(a4);

ActionListener a5 = (ae) -> {
	try
	{
		int rno = Integer.parseInt(txtRno.getText());
		if(rno>0){
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

			String sql = " delete from student where rno = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, rno);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(c,"record removed");
			con.close();
			}else{JOptionPane.showMessageDialog(c,"this rno is not exist ");}
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(c,"issue" + e);
		}
		}
		else{ JOptionPane.showMessageDialog(c,"invalid  roll no.");}
	}catch(NumberFormatException  e)
		{
			JOptionPane.showMessageDialog(c, " Please check inputs empty or invalid rno " );

		}
};
btnDelete.addActionListener(a5);


setTitle("Delete Student");
setSize(400,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}
