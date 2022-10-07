package Compressd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipOutputStream;

public class MainGUI extends JFrame {
    public static void main(String[] args) {
        new MainGUI();
    }



    private Container container = getContentPane();
    private JLabel fileurlLable = new JLabel("请选择文件或者手动输入文件路径:");
    private JTextField fileurl = new JTextField();
    private JLabel result = new JLabel("请选择文件，可点击-说明-按钮查看详细步骤");
    private  JButton selectBtn = new JButton("选择文件");
    private JButton zipBtn = new JButton("压缩");
    private JButton unzipBtn = new JButton("解压缩");
    private JButton cancelBtn = new JButton("清空");
    private JButton infoBtn = new JButton("说明");


    public MainGUI(){
        setTitle("解压缩文件");
        setBounds(600,200,700,300);
        container.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        setVisible(true);
    }

    private void init(){
        /*输入部分--Center*/
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        fileurlLable.setBounds(10, 20, 300, 25);
        result.setBounds(10,100,690,100);
        fieldPanel.add(fileurlLable);
        fieldPanel.add(result);
        fileurl.setBounds(320, 20, 360, 25);
        selectBtn.setBounds(220,20,100,25);
        fieldPanel.add(selectBtn);
        fieldPanel.add(fileurl);
        container.add(fieldPanel, "Center");

        /*按钮部分--South*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(zipBtn);
        buttonPanel.add(unzipBtn);
        buttonPanel.add(cancelBtn);
        buttonPanel.add(infoBtn);
        container.add(buttonPanel, "South");
        listerner();
    }

    private void listerner() {
        selectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jfc.showDialog(new JLabel(),"选择");
                File file = jfc.getSelectedFile();
                try {
                        fileurl.setText(file.getAbsolutePath());
                        result.setText("已选择：" + jfc.getSelectedFile().getName());
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(null,"未选择文件");
                }
            }
        });


        zipBtn.addActionListener(new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {
                                             String input = fileurl.getText();
                                             if (null == input || input.trim().length() == 0) {
                                                 JOptionPane.showMessageDialog(null, "路径不能为空");
                                             } else {
                                                 try {
                                                     File file = new File(input);
                                                     int index = input.lastIndexOf("\\");
                                                     String des = input.substring(0,index)+"\\"+file.getName()+".zip";
                                                     FileOutputStream fos = new FileOutputStream(des);
                                                     ZipOutputStream zos = new ZipOutputStream(fos);

                                                     zipCompress.zipFile(file,file.getName(),zos);
                                                     zos.close();
                                                     fos.close();
                                                     result.setText("已经压缩至："+des);

                                                     //  Block of code to try
                                                 } catch (Exception exception) {
                                                     //  Block of code to handle errors
                                                     JOptionPane.showMessageDialog(null, "所选择文件不存在或者打开失败！");
                                                     exception.printStackTrace();
                                                 }
                                             }
                                         }
                                     }
        );
        unzipBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = fileurl.getText();
                if (null == input || input.trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "路径不能为空");
                } else {
                    JOptionPane.showMessageDialog(null,"因为作业没要求写解压，且时间关系故暂且没实现(ノ｀Д)ノ");
                }
            }
        });


        /** 清空输入信息 */
        cancelBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fileurl.setText("");
                        result.setText("请选择文件，可点击-说明-按钮查看详细步骤");
                    }
                });

        //关于本项目的介绍页面：
        infoBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null, "网络编程作业三\n可以对文件以及文件夹进行压缩，但因为各种原因，没有解压缩功能；采用了Java的swing来创建交互界面;\n关于：\n网络201张进华\nemail:2006200014@e.gzhu.edu.cn\nGithub:\nhttps://github.com/myli0724/homework03-14");
                    }
                }
        );
    }
}

