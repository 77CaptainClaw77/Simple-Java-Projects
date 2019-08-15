package textedit;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;

//import sun.jvm.hotspot.ui.Editor;

public class TextEditor
{
    JFrame f;
    JTextArea txt;
    TextEditor() {
        //Main Frame
        f=new JFrame("Editor");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set the theme and  look and feel for the frame
        try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");//Each llok and feel has a theme that can be set
            OceanTheme th=new OceanTheme();//Theme
            MetalLookAndFeel.setCurrentTheme(th);//theme of the look and feel
        }
        catch(Exception e)
        {
            e.printStackTrace();//Print exception details
        }
       //Create Text Area     
        txt=new JTextArea();
        //Creating a menu bar for the application
        JMenuBar mbar=new JMenuBar();
        //Creating File Menu 
        JMenu fileMenu=new JMenu("File");
        JMenuItem fmenu_item_1=new JMenuItem("Create New File");
        JMenuItem fmenu_item_2=new JMenuItem("Open Existing File");
        JMenuItem fmenu_item_3=new JMenuItem("Save Current File");
        //Adding action handlers
        PerformAction PA=new PerformAction();//Action handler class
        fmenu_item_1.addActionListener(PA);
        fmenu_item_2.addActionListener(PA);
        fmenu_item_3.addActionListener(PA);       
        //Creating Edit Menu
        JMenu editMenu=new JMenu("Edit");
        JMenuItem emenu_item_1=new JMenuItem("Cut");
        JMenuItem emenu_item_2=new JMenuItem("Copy");
        JMenuItem emenu_item_3=new JMenuItem("Paste");
        //Adding action handlers
        emenu_item_1.addActionListener(PA);
        emenu_item_2.addActionListener(PA);
        emenu_item_3.addActionListener(PA);   
        //Creating plugins menu    
        JMenu pluginsMenu=new JMenu("Plugins");
        //Adding menu items to the menus
        fileMenu.add(fmenu_item_1);
        fileMenu.add(fmenu_item_2);
        fileMenu.add(fmenu_item_3);
        editMenu.add(emenu_item_1);
        editMenu.add(emenu_item_2);
        editMenu.add(emenu_item_3);       
        //Adding menus to the menu bar
        mbar.add(fileMenu);
        mbar.add(editMenu);
        mbar.add(pluginsMenu);
        //Adding menu bar to frame
        f.setJMenuBar(mbar);
        f.add(txt);
        f.setSize(720, 500);
        f.setVisible(true);
    }
    class PerformAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String menu_clicked=e.getActionCommand();
            if(menu_clicked.equals("Cut"))
                txt.cut();
            else if(menu_clicked.equals("Copy"))
                txt.copy();
            else if(menu_clicked.equals("Paste"))
                txt.paste();
            else if(menu_clicked.equals("Create New File"))
            {
                txt.setText("");                
            }
            else if(menu_clicked.equals("Open Existing File"))
            {
                //Creating a new file chooser to allow the user to choose the file to open
               try
               {
                    JFileChooser jf=new JFileChooser("~/Documents");
                    int r=jf.showOpenDialog(null);
                    if(r==JFileChooser.APPROVE_OPTION)//User has opened the file
                    {
                        File fi=new File(jf.getSelectedFile().getAbsolutePath());
                        FileReader fr=new FileReader(fi);
                        BufferedReader br =new BufferedReader(fr);
                        String sl=br.readLine(),s1="";
                        while((s1=br.readLine())!=null)
                            sl+="\n"+s1;
                        txt.setText(sl);
                    }
                }
                catch(Exception exc){
                    JOptionPane.showMessageDialog(f,exc.getStackTrace());
                }
            }
            else
            {
                //Creating a new file chooser to allow the user to save
                try
                {
                    JFileChooser jf=new JFileChooser("~/Documents");
                    int r=jf.showSaveDialog(null);
                    if(r==JFileChooser.APPROVE_OPTION)
                    {
                        File fi=new File(jf.getSelectedFile().getAbsolutePath());
                        FileWriter fw=new FileWriter(fi);
                        BufferedWriter bw=new BufferedWriter(fw);
                        bw.write(txt.getText());
                        bw.flush();
                        bw.close();
                    }
                }
                catch(Exception exp)
                {
                    JOptionPane.showMessageDialog(f,exp.getStackTrace());
                }
            }
            
        } 
    }
}
class ExecEditor
{
    public static void main(String[] args) 
    {
        TextEditor ed=new TextEditor();    
    }    
}
