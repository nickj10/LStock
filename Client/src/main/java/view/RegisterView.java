package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class RegisterView extends MainView{

    private JPanel jpCenter;
    private JPanel jpCampos;
    private JPanel jpBotones;
    private JTextField[] campos;
    private JButton jbLogin;
    private JButton jbRegister;


    public RegisterView () {
        super();
    }

    public void initUI () {
        super.initUI();
        //Creamos un panel para los campos y otro para los botones
        jpCenter = new JPanel();
        jpCenter = new JPanel(new BorderLayout());
        jpCenter.setBackground(Color.WHITE);
        jpCampos = new JPanel();
        //El layout de los campos será BoxLayout
        jpCampos.setLayout(new BoxLayout(jpCampos, BoxLayout.PAGE_AXIS));
        jpCampos.setBackground(Color.WHITE);
        jpCampos.setBorder(BorderFactory.createEmptyBorder(50,0,60,0));
        int anchuraCampo = 150;
        int alturaCampo = 20;
        campos = new JTextField[4];
        //Color colorTextField = new Color(242, 239, 236);
        campos[0] = new JTextField("Nickname");
        campos[0].setPreferredSize(new Dimension(anchuraCampo,alturaCampo));
        campos[0].setBorder(null);
        campos[0].setMargin(new Insets(0,70,0,0));
        campos[0].setBackground(color.getTEXTFIELD());
        campos[0].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campos[0].getText().equals("Nickname")) {
                    campos[0].setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campos[0].getText().equals("")) {
                    campos[0].setText("Nickname");
                }
            }
        });
        jpCampos.add(campos[0]);
        //Ponemos un espacio de separacion entre los campos
        jpCampos.add(Box.createRigidArea(new Dimension(anchuraCampo, 20)));
        campos[1] = new JTextField("Email");
        campos[1].setPreferredSize(new Dimension(anchuraCampo,alturaCampo));
        campos[1].setBorder(null);
        campos[1].setBackground(color.getTEXTFIELD());
        campos[1].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campos[1].getText().equals("Email")) {
                    campos[1].setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (campos[1].getText().equals("")) {
                    campos[1].setText("Email");
                }
            }
        });
        jpCampos.add(campos[1]);
        //Ponemos un espacio de separacion entre los campos
        jpCampos.add(Box.createRigidArea(new Dimension(anchuraCampo, 20)));
        campos[2] = new JTextField("Password");
        campos[2].setPreferredSize(new Dimension(anchuraCampo,alturaCampo));
        campos[2].setBorder(null);
        campos[2].setBackground(color.getTEXTFIELD());
        campos[2].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campos[2].getText().equals("Password")) {
                    campos[2].setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (campos[2].getText().equals("")) {
                    campos[2].setText("Password");
                }
            }
        });
        jpCampos.add(campos[2]);
        //Ponemos un espacio de separacion entre los campos
        jpCampos.add(Box.createRigidArea(new Dimension(anchuraCampo, 20)));
        campos[3] = new JTextField("Verify Password");
        campos[3].setPreferredSize(new Dimension(anchuraCampo,alturaCampo));
        campos[3].setBorder(null);
        campos[3].setBackground(color.getTEXTFIELD());
        campos[3].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campos[3].getText().equals("Verify Password")) {
                    campos[3].setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (campos[3].getText().equals("")) {
                    campos[3].setText("Verify Password");
                }
            }
        });
        jpCampos.add(campos[3]);

        jpCenter.add(jpCampos, BorderLayout.CENTER);

        jpBotones = new JPanel(new GridLayout(1,2, 30 ,0));
        jpBotones.setBackground(Color.WHITE);
        int anchuraBoton = 200;
        int alturaBoton = 40;
        //Boton de login
        //Color colorLogin = new Color(232, 185, 108);
        jbLogin = new JButton("Login");
        jbLogin.setBorder(null);
        jbLogin.setBackground(color.getYELLOW());
        jbLogin.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));
        jpBotones.add(jbLogin);
        //Boton de register
        //Color colorRegister = new Color(160, 160, 160);
        jbRegister = new JButton("Register");
        jbRegister.setBorder(null);
        jbRegister.setBackground(color.getLightGrey());
        jbRegister.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));
        jpBotones.add(jbRegister);
        jpCenter.add(jpBotones, BorderLayout.SOUTH);
        jpCenter.setBorder(BorderFactory.createEmptyBorder(0,250,150,250));
        jpCard.add(jpCenter);
    }

    public void registerController (ActionListener actionListener) {
        campos[0].addActionListener(actionListener);
        campos[0].setActionCommand("nickname");
        campos[1].addActionListener(actionListener);
        campos[1].setActionCommand("email");
        campos[2].addActionListener(actionListener);
        campos[2].setActionCommand("pass1");
        campos[3].addActionListener(actionListener);
        campos[3].setActionCommand("pass2");
        jbLogin.addActionListener(actionListener);
        jbLogin.setActionCommand("login");
        jbRegister.addActionListener(actionListener);
        jbRegister.setActionCommand("register");
    }

    public static void main( String[] args )
    {
        JFrame frame = new JFrame();
        RegisterView registerView = new RegisterView();
        frame.getContentPane().add(registerView);
        frame.setSize(1024, 768);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
