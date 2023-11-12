import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


class ViewFrame extends JFrame
{
Container c;
TextArea taData;
JButton btnBack;

ViewFrame()
{
c = getContentPane();
c.setLayout( new FlowLayout());

taData = new TextArea(50, 60);
btnBack = new JButton("Back");

Font f = new Font("Courier", Font.BOLD , 22);
Font z = new Font("Courier", Font.BOLD , 12);
btnBack.setFont(f);
taData.setFont(z);

StringBuffer data = new StringBuffer();
	try 
	{	
	    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
	    String url =  "jdbc:mysql://localhost:3306/kcs10july22";
	    String un = "root";
	    String pw = "8032";
	    Connection con = DriverManager.getConnection(url, un , pw);

	    String sql = "select * from student";
	    Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(sql);
    	    while(rs.next()) {
		int rno = rs.getInt(1);
		String name = rs.getString(2);
	    	int Subject1 = rs.getInt(3);
	    	int Subject2 = rs.getInt(4);
	    	int Subject3 = rs.getInt(5);
		data.append("rno : " + rno + "    name : " + name + "    Subject1 : " +   Subject1 + "  Subject2 " + 
		Subject2 + "    Subject3 : " +  Subject3 +"\n");
		 }
		taData.setText(data.toString());	
		rs.close();
		con.close();
	}
	catch(SQLException e)
	{
		JOptionPane.showMessageDialog(c,"issue" + e);
	}


c.add(taData);
c.add(btnBack);


ActionListener a1 = (ae) -> {
MainFrame a = new MainFrame();
dispose();
};
btnBack.addActionListener(a1);

setTitle("View Student");
setSize(800,800);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}
