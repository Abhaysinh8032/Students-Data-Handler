import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Login extends JFrame
{
Container c;
JLabel labUserName , labPassword;
JTextField txtUserName ;
JPasswordField txtPassword;
JButton btnLogin , btnSignup;

Login()
{
c = getContentPane();
c.setLayout(null);

labUserName = new JLabel("UserName");
txtUserName = new JTextField(30);
labPassword = new JLabel("Password");
txtPassword = new JPasswordField(30);
txtPassword.setEchoChar('*');
btnLogin = new JButton("Login");
btnSignup = new JButton("Signup");

Font f = new Font("Courier", Font.BOLD , 24);
labUserName.setFont(f);
txtUserName.setFont(f);
labPassword.setFont(f);
txtPassword.setFont(f);
btnLogin.setFont(f);
btnSignup.setFont(f);

labUserName.setBounds(50 , 20 , 300, 40);
txtUserName.setBounds(300, 20, 300, 40);
labPassword.setBounds(50, 80 , 300, 40);
txtPassword.setBounds(300, 80, 300, 40);
btnLogin.setBounds(200 , 140 , 300, 40);
btnSignup.setBounds(200 , 200 , 300, 40);



c.add(labUserName);
c.add(txtUserName);
c.add(labPassword);
c.add(txtPassword);
c.add(btnLogin);
c.add(btnSignup);





ActionListener a1 = (ae) -> {
Signup a = new Signup();
dispose();
};
btnSignup.addActionListener(a1);

ActionListener a2 = (ae) -> {
	try
	{
		String username = txtUserName.getText();
		String password = txtPassword.getText();
		boolean result = username.matches("[a-zA-Z]+");


		if((result)&(username.length()>1)&(password.length()>5))
		{
		
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String url =  "jdbc:mysql://localhost:3306/au4july22";
			String un = "root";
			String pw = "8032";
			Connection con = DriverManager.getConnection(url, un , pw);

			String sql= "select * from users where name=? and password=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				MainFrame a = new MainFrame();
				dispose();
				
			}
			else{
					JOptionPane.showMessageDialog(c,"invalid login");
				}
			con.close();


		


			
		} 
		else if(username.equals(""))	{ JOptionPane.showMessageDialog(null,"please enter Name");}
		else if(password.equals(""))	{ JOptionPane.showMessageDialog(null,"please enter Password");}
		else if(!(username.length()>1))	{ JOptionPane.showMessageDialog(c,"invalid Name");}
		else if(!(password.length()>5))	{ JOptionPane.showMessageDialog(c,"invalid Password");}
		else							{ JOptionPane.showMessageDialog(c,"name can accept only aplphabets");}
	}
	catch(Exception  e)
	{
		JOptionPane.showMessageDialog(c, " Issue" + e );

	}


};
btnLogin.addActionListener(a2);


setTitle("Login page for facaulty");
setSize(700,600);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}