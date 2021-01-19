package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class QuizCardBuilderBuilder {

    private JTextArea question;
    private JTextArea answer;
    private ArrayList<Quizcard> quizcardlist;
    private JFrame frame;
    public static void main(String[] args) {
	QuizCardBuilderBuilder builder=new QuizCardBuilderBuilder();
     builder.go();
    }

    public void go()
    {
        frame=new JFrame("QuizCardBuilder");
        JPanel mainpanel=new JPanel();
        quizcardlist =new ArrayList<Quizcard>();
        Font bigfont=new Font("sanserif",Font.BOLD,24);
        question=new JTextArea(6,20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(bigfont);
        JScrollPane qscroller=new JScrollPane(question);
        qscroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qscroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JLabel qlabel=new JLabel("Question");
        mainpanel.add(qlabel);
        mainpanel.add(qscroller);
        answer=new JTextArea(6,20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(bigfont);
        JScrollPane ascroller=new JScrollPane(answer);
        ascroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        ascroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JLabel alabel=new JLabel("Answer");
        mainpanel.add(alabel);
        mainpanel.add(ascroller);
        JButton nextbutton=new JButton("Next Card");
        nextbutton.addActionListener(new nextcardListner());
        mainpanel.add(nextbutton);
        JMenuBar menuBar=new JMenuBar();
        JMenu filemenu= new JMenu("File");
        JMenuItem newMenuitem=new JMenuItem("New");
        JMenuItem saveMenuitem=new JMenuItem("Save");
        filemenu.add(newMenuitem);
        newMenuitem.addActionListener(new newMenuListener());
        filemenu.add(saveMenuitem);
        saveMenuitem.addActionListener(new saveMenuListener());
        menuBar.add(filemenu);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER,mainpanel);
        frame.setSize(500,600);
        frame.setVisible(true);
    }

    public  class nextcardListner implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Quizcard card=new Quizcard(question.getText(),answer.getText());
            quizcardlist.add(card);
//            Quizcard c=quizcardlist.get(0);
//            System.out.println(c.getAnswer());
            clearcard();
        }
    }
    public  class newMenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            quizcardlist.clear();
            clearcard();
        }
    }

    public class saveMenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Quizcard card=new Quizcard(question.getText(),answer.getText());
            quizcardlist.add(card);
            JFileChooser filesave=new JFileChooser();
            filesave.showSaveDialog(frame);
            savefile(filesave.getSelectedFile());
        }
    }
    private void clearcard(){
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }

    private void savefile(File file){
        try {
            BufferedWriter writer=new BufferedWriter(new FileWriter(file));
            for (Quizcard card:quizcardlist)
            {
                writer.write(card.getQuestion()+"/");
                writer.write(card.getAnswer()+"\n");
            }
            writer.close();
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
