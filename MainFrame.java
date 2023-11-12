import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainFrame extends JFrame
{
Container c;
JButton btnAdd, btnView ,btnUpdate , btnDelete, btnLogout;

MainFrame()
{
c = getContentPane();
c.setLayout(null);

btnAdd = new JButton("Add");
btnView = new JButton("View");
btnUpdate = new JButton("Update");
btnDelete = new JButton("Delete");
btnLogout = new JButton("Logout");

Font f = new Font("Courier", Font.BOLD , 24);
btnAdd.setFont(f);
btnView.setFont(f);
btnUpdate.setFont(f);
btnDelete.setFont(f);
btnLogout.setFont(f);

btnAdd.setBounds(50 , 50 , 100, 40);
btnView.setBounds(320 , 50 , 100, 40);
btnUpdate.setBounds(30 , 180 , 150, 40);
btnDelete.setBounds(300 , 180 , 150, 40);
btnLogout.setBounds(200 , 320 , 150, 40);


c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnDelete);
c.add(btnLogout);

ActionListener a1 = (ae) -> {
AddFrame a = new AddFrame();
dispose();
};
btnAdd.addActionListener(a1);

ActionListener a2 = (ae)-> {
ViewFrame a = new ViewFrame();
dispose();
};
btnView.addActionListener(a2);

ActionListener a3 = (ae)-> {
UpdateFrame a = new UpdateFrame();
dispose();
};
btnUpdate.addActionListener(a3);

ActionListener a4 = (ae)-> {
DeleteFrame a = new DeleteFrame();
dispose();
};
btnDelete.addActionListener(a4);

ActionListener a5 = (ae)-> {
Login a = new Login();
dispose();
};
btnLogout.addActionListener(a5);


setTitle("Student Management System");
setSize(500,500);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}
