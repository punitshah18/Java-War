import javax.swing. *;
import java.awt. *;
import java.awt.event. *;

public class gui extends JPanel implements ActionListener
{
    /*
    JPanel main = new JPanel (); 
    JPanel war = new JPanel ();
    JPanel rules = new JPanel ();
    JPanel options = new JPanel ();
     */

    int user = 26;
    int cpu = 26;
    int z = 0;
    
    int pCurrentCard = -1;
    int cCurrentCard =-1;

    static JFrame panel; 
    
    JLabel cardscpu = new JLabel ();
    JLabel cards = new JLabel ();
    
    JLabel cardback1 = new JLabel ();
    JLabel cardback2 = new JLabel ();

    GridBagConstraints con = new GridBagConstraints ();

    CardLayout frames = new CardLayout ();

    public static void main (String args[])
    {
        gui content = new gui (); 
        panel = new JFrame ("War game");
        panel.setContentPane (content); 
        content.setBackground (new Color (60,186,63));
        panel.setSize (700,400); 
        panel.setLocation (280,175);
        panel.setVisible (true);
        panel.setResizable (true);
    }

    public gui () 
    {
        setLayout(frames);
        main ();
        playscreen ();
        instructionsscreen ();
        optionsscreen ();
    }

    public void main ()
    {
        JPanel mainmenu = new JPanel (new BorderLayout (8,8));
        mainmenu.setBackground (new Color (60,186,63));
        //-----------------------------------------------------------
        JPanel submainpanel = new JPanel ();
        submainpanel.setBackground (new Color (60,186,63));
        //make transparent

        JLabel title = new JLabel ("WAR");
        title.setFont (new Font ("Goudy Stout", Font.BOLD, 48));
        title.setForeground (Color.red);
        submainpanel.add (title);

        //------------------------------------------------------------
        JPanel picture1 = new JPanel ();
        picture1.setBackground (new Color (60,186,63));

        JLabel mainmenuimage1 = new JLabel (createImageIcon ("mainmenuimage1.jpg"));

        picture1.add (mainmenuimage1);

        //------------------------------------------------------------
        JPanel picture2 = new JPanel ();
        picture2.setBackground (new Color (60,186,63));

        JLabel mainmenuimage2 = new JLabel (createImageIcon ("mainmenuimage2.jpg"));

        picture2.add (mainmenuimage2);
        //------------------------------------------------------------
        JPanel buttons = new JPanel (new GridBagLayout ());
        buttons.setBackground (new Color (60,186,63));

        JButton playbutton = new JButton ("PLAY");
        playbutton.setFont (new Font ("Cooper Black", Font.PLAIN, 24));
        playbutton.setActionCommand ("PLAY");
        playbutton.addActionListener (this);
        con.weightx = 0.5;
        con.anchor = GridBagConstraints.NORTH;
        buttons.add (playbutton, con);

        JButton rulesbutton = new JButton ("INSTRUCTIONS");
        rulesbutton.setFont (new Font ("Cooper Black", Font.PLAIN, 24));
        rulesbutton.setActionCommand ("INSTRUCTIONS");
        rulesbutton.addActionListener (this);
        con.anchor = GridBagConstraints.CENTER;
        con.insets = new Insets (20,0,20,0);
        con.gridx = 0;
        con.gridy = 1;
        buttons.add (rulesbutton, con);

        JButton optionsbutton = new JButton ("OPTIONS");
        optionsbutton.setFont (new Font ("Cooper Black", Font.PLAIN, 24));
        optionsbutton.setActionCommand ("OPTIONS");
        optionsbutton.addActionListener (this);
        con.insets = new Insets (0,0,0,0);
        con.gridx = 0;
        con.gridy = 2;
        buttons.add (optionsbutton, con);

        JButton quitbutton = new JButton ("QUIT");
        quitbutton.setFont (new Font ("Cooper Black", Font.PLAIN, 24));
        quitbutton.setActionCommand ("QUIT");
        quitbutton.addActionListener (this);
        con.insets = new Insets (20,70,20,70);
        con.gridx = 0;
        con.gridy = 3;
        buttons.add (quitbutton, con);
        //------------------------------------------------------------------
        JPanel message = new JPanel ();
        message.setBackground (new Color (60,186,63));

        JLabel messagetext = new JLabel ("   NEW GAME MODE COMING SOON!   ");
        messagetext.setFont (new Font ("Cooper Black", Font.ITALIC, 24));
        messagetext.setForeground (Color.yellow);

        message.add (messagetext);
        //------------------------------------------------------------------
        mainmenu.add (submainpanel, BorderLayout.NORTH);
        mainmenu.add (buttons, BorderLayout.CENTER);
        mainmenu.add (picture1, BorderLayout.WEST);
        mainmenu.add (picture2, BorderLayout.EAST);
        mainmenu.add (message, BorderLayout.SOUTH);

        add ("gotomainmenu", mainmenu);
    }

    public void playscreen ()
    {
        JPanel playpanel = new JPanel (new BorderLayout(8,8));
        playpanel.setBackground (new Color (60,186,63));

        //---------------------------------------------------------------
        JPanel playpaneltitlepanel = new JPanel ();
        playpaneltitlepanel.setBackground (new Color (60,186,63));

        JLabel playpaneltitle = new JLabel ("THE BATTLEFIELD");
        playpaneltitle.setFont (new Font ("Goudy Stout", Font.BOLD, 36));
        playpaneltitle.setForeground (Color.red);
        playpaneltitlepanel.add (playpaneltitle);

        //---------------------------------------------------------------

        JPanel stats = new JPanel (new GridBagLayout ());
        stats.setBackground (new Color (60,186,63));

        cards = new JLabel ();
        cards.setText ("YOU - " + user);
        cards.setFont (new Font ("Papyrus", Font.BOLD, 18));
        con.weightx = 0.5;
        con.anchor = GridBagConstraints.NORTH;
        stats.add (cards, con);

        cardscpu = new JLabel ();
        cardscpu.setText ("CPU - " + cpu);
        cardscpu.setFont (new Font ("Papyrus", Font.BOLD, 18));
        con.anchor = GridBagConstraints.CENTER;
        con.insets = new Insets (20,0,20,0);
        con.gridx = 0;
        con.gridy = 1;
        stats.add (cardscpu, con);
        //---------------------------------------------------------------

        JPanel playbuttons = new JPanel ();
        playbuttons.setBackground (new Color (60,186,63));

        JButton drawbutton = new JButton ("DRAW");
        drawbutton.setFont (new Font ("Cooper Black", Font.PLAIN, 24));
        drawbutton.setActionCommand ("DRAW");
        drawbutton.addActionListener (this);
        playbuttons.add (drawbutton);

        JLabel space = new JLabel ("       ");
        playbuttons.add (space);

        JButton reset = new JButton ("RESET CARDS");
        reset.setFont (new Font ("Cooper Black", Font.PLAIN, 24));
        reset.setActionCommand ("RESET");
        reset.addActionListener (this);
        playbuttons.add (reset);

        JLabel space2 = new JLabel ("       ");
        playbuttons.add (space2);

        JButton backbutton = new JButton ("BACK");
        backbutton.setFont (new Font ("Cooper Black", Font.PLAIN, 24));
        backbutton.setActionCommand ("BACK");
        backbutton.addActionListener (this);
        playbuttons.add (backbutton);

        //---------------------------------------------------------------

        JPanel image1 = new JPanel ();
        image1.setBackground (new Color (60,186,63));

        JLabel cardback1 = new JLabel (createImageIcon ("cardback.jpg"));
        image1.add (cardback1);

        //---------------------------------------------------------------

        JPanel image2 = new JPanel ();
        image2.setBackground (new Color (60,186,63));

        JLabel cardback2 = new JLabel (createImageIcon ("cardback2.jpg"));
        image2.add (cardback2);

        playpanel.add (playpaneltitlepanel, BorderLayout.NORTH);
        playpanel.add (stats, BorderLayout.CENTER);
        playpanel.add (playbuttons, BorderLayout.SOUTH);
        playpanel.add (image2, BorderLayout.EAST);
        playpanel.add (image1, BorderLayout.WEST);

        add ("gotowarscreen", playpanel);
    }

    public void instructionsscreen ()
    {

        //-----------------------------------------------------------------
        JPanel rulespanel = new JPanel ();
        rulespanel.setBackground (new Color (60, 186, 63));

        JLabel instructionstitle = new JLabel ("RULES OF WAR");
        instructionstitle.setFont (new Font ("Goudy Stout", Font.BOLD, 36));
        instructionstitle.setForeground (Color.red);
        instructionstitle.setBounds (150,-10,300,90);
        rulespanel.add (instructionstitle);
        //-----------------------------------------------------------------

        JLabel instructionspart1 = new JLabel ("Are you ready for war soldier?");
        instructionspart1.setFont (new Font ("Papyrus", Font.BOLD, 24));
        instructionspart1.setBounds (70,40,400,90);
        rulespanel.add (instructionspart1);

        //-----------------------------------------------------------------

        JLabel instructionspart2 = new JLabel ("26 cards are distributed to each side randomly");
        instructionspart2.setFont (new Font ("Papyrus", Font.PLAIN, 18));
        instructionspart2.setBounds (70,80,500,90);
        rulespanel.add (instructionspart2);

        //------------------------------------------------------------------

        JLabel instructionspart3 = new JLabel ("The 'Play' button is clicked and each side draws a card");
        instructionspart3.setFont (new Font ("Papyrus", Font.PLAIN, 18));
        instructionspart3.setBounds (50,160,600,90);
        rulespanel.add (instructionspart3);

        //------------------------------------------------------------------

        JLabel instructionspart4 = new JLabel ("The side with the greater number wins both cards and they are added to their pile");
        instructionspart4.setFont (new Font ("Papyrus", Font.PLAIN, 18));
        instructionspart4.setBounds (50,160,600,90);
        rulespanel.add (instructionspart4);

        //------------------------------------------------------------------

        JLabel instructionspart5 = new JLabel ("The player who gets all the cards win");
        instructionspart5.setFont (new Font ("Papyrus", Font.PLAIN, 18));
        instructionspart5.setBounds (70,80,500,90);
        rulespanel.add (instructionspart5);

        //------------------------------------------------------------------

        JLabel instructionspart6 = new JLabel ("If the same card is drawn, 3 more are drawn and player with the higher sum wins all of them");
        instructionspart6.setFont (new Font ("Papyrus", Font.PLAIN, 18));
        instructionspart6.setBounds (70,80,500,90);
        rulespanel.add (instructionspart6);

        //---------------------------------------------------------------------

        JLabel instructionspart7 = new JLabel ("Now get out there and fight!");
        instructionspart7.setFont (new Font ("Papyrus", Font.BOLD, 18));
        instructionspart7.setBounds (70,80,500,90);
        rulespanel.add (instructionspart7);

        //----------------------------------------------------------------------

        JButton back = new JButton ("BACK");
        back.setFont (new Font ("Cooper Black", Font.PLAIN, 24));
        back.setActionCommand ("BACK");
        back.addActionListener (this);
        back.setBounds (50,160,900,90);
        rulespanel.add (back);

        add ("gotoinstructionsscreen", rulespanel);
    }

    public void optionsscreen ()
    {
        JPanel optionspanel = new JPanel ();
        optionspanel.setBackground (new Color (60,183, 63));

        JLabel optionstitle = new JLabel ("OPTIONS");
        optionstitle.setFont (new Font ("Goudy Stout", Font.BOLD, 36));
        optionstitle.setForeground (Color.red);
        optionstitle.setBounds (150,-10,300,90);
        optionspanel.add (optionstitle);

        JButton volume = new JButton (createImageIcon ("volume.png"));
        volume.setActionCommand ("VOLUME");
        volume.addActionListener (this);
        optionspanel.add (volume);

        JButton back2 = new JButton ("BACK");
        back2.setFont (new Font ("Cooper Black", Font.PLAIN, 24));
        back2.setActionCommand ("BACK");
        back2.addActionListener (this);
        optionspanel.add (back2);

        add ("gotooptionsscreen", optionspanel);
    }

    public void actionPerformed (ActionEvent e)
    {
        if (e.getActionCommand().equals ("PLAY"))
        {
            frames.show (this, "gotowarscreen");
        }

        else if (e.getActionCommand().equals ("INSTRUCTIONS"))
        {
            frames.show (this, "gotoinstructionsscreen");
        }

        else if (e.getActionCommand().equals ("OPTIONS"))
        {
            frames.show (this, "gotooptionsscreen");
        }

        else if (e.getActionCommand().equals ("BACK"))
        {
            frames.show (this, "gotomainmenu");
        }
        else if (e.getActionCommand().equals ("QUIT"))
        {
            System.exit (0);
        }
        else if (e.getActionCommand().equals ("DRAW"))
        {
            Card object = new Card ();
            int[] p1Num = object.getP1NumDeck(); 
            char[] p1Suit = object.getP1SuitDeck ();
            int[] cpuNum = object.getCpuNumDeck ();
            char[] cpuSuit = object.getcpuSuitDeck ();
            
            if(z<26)
            {
                //return p1num[z]
                pCurrentCard = p1Num[z];
                //return cpunum[z]
                cCurrentCard = cpuNum[z];
                System.out.println(pCurrentCard);
                System.out.println(cCurrentCard);
                z++;
                if (pCurrentCard < cCurrentCard)
                {
                    user++;
                    cpu--;
                    cardscpu.setText ("CPU - " + cpu);
                    cards.setText ("YOU - " + user);
                }
                else if (pCurrentCard > cCurrentCard)
                {
                    user--;
                    cpu++;
                    cardscpu.setText ("CPU - " + cpu);
                    cards.setText ("YOU - " + user);
                }
            }
        }
        else if (e.getActionCommand().equals ("RESET"))
        {
            user = 26;
            cpu = 26;
        }
    }

    protected static ImageIcon createImageIcon (String path)
    {
        java.net.URL 
        imgURL = gui.class.getResource (path);
        if (imgURL !=null)
        {
            return new ImageIcon (imgURL);
        }
        else
        {
            System.err.println ("Couldn't find file: "+path);
            return null;
        }
    }
}

