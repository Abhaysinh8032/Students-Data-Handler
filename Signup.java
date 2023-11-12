import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Signup extends JFrame
{
Container c;
JLabel labUserName , labPassword , labConfirmPassword;
JTextField txtUserName;
JPasswordField txtPassword , txtConfirmPassword;
JButton btnSignup ,btnLoginPage;

Signup()
{
c = getContentPane();
c.setLayout(null);

labUserName = new JLabel("UserName");
txtUserName = new JTextField(30);
labPassword = new JLabel("Password");
txtPassword = new JPasswordField(30);
txtPassword.setEchoChar('*');
labConfirmPassword = new JLabel("Confirm Password");
txtConfirmPassword = new JPasswordField(30);
txtConfirmPassword.setEchoChar('*');
btnSignup = new JButton("Signup");
btnLoginPage = new JButton("Go to Login Page");


Font f = new Font("Courier", Font.BOLD , 24);
labUserName.setFont(f);
txtUserName.setFont(f);
labPassword.setFont(f);
txtPassword.setFont(f);
labConfirmPassword.setFont(f);
txtConfirmPassword.setFont(f);
btnSignup.setFont(f);
btnLoginPage.setFont(f);

labUserName.setBounds(50 , 20 , 300, 40);
txtUserName.setBounds(300, 20, 300, 40);
labPassword.setBounds(50, 80 , 300, 40);
txtPassword.setBounds(300, 80, 300, 40);
labConfirmPassword.setBounds(50, 140 , 300, 40);
txtConfirmPassword.setBounds(300, 140, 300, 40);
btnSignup.setBounds(200 , 200 , 300, 40);
btnLoginPage.setBounds(200 , 260 , 300, 40);



c.add(labUserName);
c.add(txtUserName);
c.add(labPassword);
c.add(txtPassword);
c.add(labConfirmPassword);
c.add(txtConfirmPassword);
c.add(btnSignup);
c.add(btnLoginPage);




ActionListener a1 = (ae) -> {
Login a = new Login();
dispose();
};
btnLoginPage.addActionListener(a1);

ActionListener a2 = (ae) -> {
	try
	{
		String username = txtUserName.getText();
		String password = txtPassword.getText();
		String confirmpassword = txtConfirmPassword.getText();
		boolean result = username.matches("[a-zA-Z]+");


		if((password.equals(confirmpassword))&(result)&(username.length()>1)&(password.length()>5))
		{
		
			try
            {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                String url = "jdbc:mysql://localhost:3306/au4july22";
                String un = "root";
                String pw = "8032";
                Connection con = DriverManager.getConnection(url,un,pw);
                
                String sql1 = "select*from users where name=?";
                PreparedStatement pst1 = con.prepareStatement(sql1);
                pst1.setString(1,username);
            
                ResultSet rs1 = pst1.executeQuery();
                if (rs1.next())
                    {
                       JOptionPane.showMessageDialog(c,"Already signup");
                
                    }
                else{
                        String sql2 = "insert into users values(?,?)";
                        PreparedStatement pst2 = con.prepareStatement(sql2);
                        pst2.setString(1,username);
                        pst2.setString(2,password);
                        pst2.executeUpdate();
						Login a = new Login();
						dispose();
                        
                    }
                con.close();
            }    
            catch(SQLException e)
            {
                 JOptionPane.showMessageDialog(c,"issue" + e);
            
            }


		


			
		} 
		else if(username.equals(""))	{ JOptionPane.showMessageDialog(null,"please enter Name");}
		else if(password.equals(""))	{ JOptionPane.showMessageDialog(null,"please enter Password");}
		else if(!(username.length()>1))	{ JOptionPane.showMessageDialog(c,"invalid Name");}
		else if(!(password.length()>5))	{ JOptionPane.showMessageDialog(c,"Password should atleast 6 characters");}
		else if(!(password.equals(confirmpassword)))	{ JOptionPane.showMessageDialog(c,"Password doesnot matches");}
		else							{ JOptionPane.showMessageDialog(c,"name can accept only aplphabets");}
	}
	catch(Exception  e)
	{
		JOptionPane.showMessageDialog(c, " Issue" + e );

	}


};
btnSignup.addActionListener(a2);


setTitle("Login page for facaulty");
setSize(600,600);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}