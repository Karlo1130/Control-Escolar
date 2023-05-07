import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;

import java.util.ArrayList;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class MiVentana extends JFrame {
	JPanel actual=null;
	JPanel anterior=null;
	JPanel bienvenida;
	JPanel login;
	JPanel miCuentaPanel;
	JPanel registro;
	JPanel perfil;
	JPanel accesoPermitido;
    JPanel listaUsuarios;
	String nombre;
	DefaultTableModel tableModel;
	JTable table;
	ArrayList<String[]> usersList;

	private DefaultTableModel modelo;


	
	public MiVentana() {
		this.setVisible(true);
		this.setSize(525, 700);
		this.setLocationRelativeTo(null);
		this.setTitle("Menu Principal");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.decode("#dce7ec"));
		
		
	
		Bienvenida();
		timer();
		repaint();
		revalidate();
		
		
	}
	
	
	public void timer() {
		
		Timer timer = new Timer();
		
		TimerTask tarea = new TimerTask() {
			@Override
			public void run() {
				Login();
			}
		};
		
		timer.schedule(tarea, 3000);
		
	}

	
	public void Bienvenida() {

		bienvenida = new JPanel();
		bienvenida.setSize(300,120);
		bienvenida.setLocation(100,180);
		bienvenida.setLayout(null);
		bienvenida.setBackground(Color.decode("#dce7ec"));
		
		JLabel titleinicio = new JLabel("Bienvenido",JLabel.CENTER);
		titleinicio.setFont(new Font("Comic Sans", Font.BOLD,20));
		titleinicio.setSize(260, 40);
		titleinicio.setLocation(20, 40);
		titleinicio.setOpaque(true);
		titleinicio.setBackground(Color.decode("#1f7690"));
		bienvenida.add(titleinicio);
		
		actual=bienvenida;
		
		add(actual);
		
		repaint();
		revalidate();
	}
	
		
	
	public void menuMiCuenta() {
		miCuentaPanel = new JPanel();
		miCuentaPanel.setSize(525,700);
		miCuentaPanel.setLocation(0,0);
		miCuentaPanel.setLayout(null);
		miCuentaPanel.setBackground(Color.decode("#dce7ec"));
		
		JLabel editarPerfil = new JLabel();
		editarPerfil.setText("Bienvenido "+ nombre);
		editarPerfil.setFont(new Font("Comic Sans", Font.BOLD,20));
		editarPerfil.setBounds(100, 10, 300, 80);
		editarPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		editarPerfil.setForeground(Color.decode("#1f7690"));
		editarPerfil.setFont(new Font("cooper black",0,25));
		
		miCuentaPanel.add(editarPerfil);
		
		JLabel iconoLista = new JLabel(new ImageIcon("material-escolar (1).PNG"));
		iconoLista.setBounds(165, 80, 170, 170);
		miCuentaPanel.add(iconoLista);

        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setBounds(70, 270, 100, 20);
        JTextField nameField = new JTextField();
        nameField.setBounds(200, 270, 200, 20);

        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(70, 300, 100, 20);
        JTextField apellidoField = new JTextField();
        apellidoField.setBounds(200, 300, 200, 20);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(70, 330, 100, 20);
        JTextField emailField = new JTextField();
        emailField.setBounds(200, 330, 200, 20);
        

        JLabel NumTelefono = new JLabel("Numero telefonico:");
        NumTelefono.setBounds(70, 360, 110, 20);
        JTextField NumeroTelefono = new JTextField();
        NumeroTelefono.setBounds(200, 360, 200, 20);
        NumeroTelefono.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char character = e.getKeyChar();
                if (!(Character.isDigit(character) ||
                      (character == KeyEvent.VK_BACK_SPACE) ||
                      (character == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
        
        

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(70, 390, 100, 20);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(200, 390, 200, 20);


        JLabel confirmPasswordLabel = new JLabel("Confirmar contraseña:");
        confirmPasswordLabel.setBounds(70, 420, 150, 20);
        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(200, 420, 150, 20);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setBounds(150, 450, 100, 30);

        JButton updateButton = new JButton("Actualizar datos");
        updateButton.setBounds(260, 450, 130, 30);

        miCuentaPanel.add(nameLabel);
        miCuentaPanel.add(nameField);
        miCuentaPanel.add(apellidoLabel);
        miCuentaPanel.add(apellidoField);
        miCuentaPanel.add(emailLabel);
        miCuentaPanel.add(emailField);
        miCuentaPanel.add(NumTelefono);
        miCuentaPanel.add(NumeroTelefono);
        miCuentaPanel.add(passwordLabel);
        miCuentaPanel.add(passwordField);
        miCuentaPanel.add(confirmPasswordLabel);
        miCuentaPanel.add(confirmPasswordField);
        miCuentaPanel.add(cancelButton);
        miCuentaPanel.add(updateButton);

        cancelButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        
		       Perfil();
		    }
		});
        
        
        anterior = actual;
        actual = miCuentaPanel;
        
        remove(anterior);
        add(actual);
        
        revalidate();
        repaint();
	}
	
	
	public void Login () {
		login = new JPanel();
		login.setSize(400,290);
		login.setLocation(50,50);
		login.setLayout(null);
		login.setBackground(Color.decode("#dce7ec"));
		
		JLabel titleinicio = new JLabel("Iniciar Sesion",JLabel.CENTER);
		titleinicio.setFont(new Font("Comic Sans", Font.BOLD,20));
		titleinicio.setSize(280, 40);
		titleinicio.setLocation(60, 40);
		titleinicio.setOpaque(true);
		titleinicio.setForeground(Color.decode("#ffffff"));
		titleinicio.setBackground(Color.decode("#1f7690"));
		login.add(titleinicio);
		
		JLabel usuario = new JLabel("Ingrese su usuario",JLabel.CENTER);
		usuario.setFont(new Font("Comic Sans", Font.BOLD,16));
		usuario.setSize(250, 20);
		usuario.setLocation(75, 95);
		usuario.setOpaque(true);
		usuario.setForeground(Color.decode("#ffffff"));
		usuario.setBackground(Color.decode("#1f7690"));
		login.add(usuario);
		


		JLabel iniciarcontraseña = new JLabel("Contraseña",JLabel.CENTER);
		iniciarcontraseña.setFont(new Font("Comic Sans", Font.BOLD,16));
		iniciarcontraseña.setSize(250, 30);
		iniciarcontraseña.setLocation(75, 155);
		iniciarcontraseña.setOpaque(true);
		iniciarcontraseña.setForeground(Color.decode("#ffffff"));
		iniciarcontraseña.setBackground(Color.decode("#1f7690"));
		login.add(iniciarcontraseña);

		


		JButton entrarcuentar = new JButton();
		entrarcuentar.setText("Aceptar");
		entrarcuentar.setSize(100, 40);
		entrarcuentar.setLocation(150, 225);
		entrarcuentar.setOpaque(true);
		entrarcuentar.setBackground(Color.white);
		login.add(entrarcuentar);
		
		JTextField username = new JTextField("Ricardo");
		username.setSize(250, 30);
		username.setLocation(75, 120);
		login.add(username);
		
		JPasswordField password = new JPasswordField("12345");
		password.setSize(250, 30);
		password.setLocation(75, 190);
		login.add(password);
	
		entrarcuentar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String user = username.getText();
				String pwd = new String (password.getPassword());
				
				BufferedReader reader;
				
				Boolean flag = false;
				
				try{
					
					FileReader file = new FileReader("users.txt");
					reader = new BufferedReader(file);
					
					String line = reader.readLine();
					
					while(line != null) {
						
						String data [] = line.split(",");
						 
						if( user.equals(data[0]) ) {
							if( pwd.equals(data[3]) ) {
								flag = true;
							}
						} 
						
						line = reader.readLine();
						
					}
					
					if(flag==true) {
						nombre = username.getText();
						JOptionPane.showMessageDialog(null,"Bienvenido "+nombre,"Acceso Permitido", JOptionPane.CLOSED_OPTION );
						Perfil();
						
					}else {
						JOptionPane.showMessageDialog(null,"Error","Datos incorrectos, intentar denuevo.", JOptionPane.ERROR_MESSAGE );
					}
					
					
				} catch(IOException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		anterior=actual;
		actual=login;
		remove(anterior);
		add(actual);
		
		repaint();
		revalidate();
}	
	

	public void menuCrearUsuario() {
		
		registro = new JPanel();
		registro.setSize(400,550);
		registro.setLocation(50,50);
		registro.setLayout(null);
		registro.setBackground(Color.decode("#dce7ec"));
		
		JLabel editarPerfil = new JLabel();
		editarPerfil.setText("Editar Perfil");
		editarPerfil.setBounds(100, 10, 300, 80);
		editarPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		editarPerfil.setForeground(Color.BLACK);
		editarPerfil.setFont(new Font("cooper black",0,25));
		
		perfil.add(editarPerfil);
		
		JLabel iconoLista = new JLabel(new ImageIcon("material-escolar(1).PNG"));
		iconoLista.setBounds(165, 80, 170, 170);
		perfil.add(iconoLista);
		
		JLabel titleregistro = new JLabel("Registro de Nuevo Usuario",JLabel.CENTER);
		titleregistro.setFont(new Font("Comic Sans", Font.BOLD,20));
		titleregistro.setForeground(Color.decode("#ffffff"));
		titleregistro.setSize(280, 40);
		titleregistro.setLocation(60,00);
		titleregistro.setOpaque(true);
		titleregistro.setBackground(Color.decode("#1f7690"));
		registro.add(titleregistro);
	
		//Etiquetas
		JLabel nombre = new JLabel("Ingrese sus nombres",JLabel.CENTER);
		nombre.setFont(new Font("Comic Sans", Font.BOLD,16));
		nombre.setForeground(Color.decode("#ffffff"));
		nombre.setSize(250, 20);
		nombre.setLocation(75, 50);
		nombre.setOpaque(true);
		nombre.setBackground(Color.decode("#1f7690"));
		registro.add(nombre);
				
		JLabel apellido = new JLabel("Apellidos",JLabel.CENTER);
		apellido.setFont(new Font("Comic Sans", Font.BOLD,16));
		apellido.setForeground(Color.decode("#ffffff"));
		apellido.setSize(250, 20);
		apellido.setLocation(75, 105);
		apellido.setOpaque(true);
		apellido.setBackground(Color.decode("#1f7690"));
		registro.add(apellido);
				
		JLabel fechaN = new JLabel("Fecha de nacimiento",JLabel.CENTER);
		fechaN.setFont(new Font("Comic Sans", Font.BOLD,16));
		fechaN.setForeground(Color.decode("#ffffff"));
		fechaN.setSize(250, 20);
		fechaN.setLocation(75, 160);
		fechaN.setOpaque(true);
		fechaN.setBackground(Color.decode("#1f7690"));
		registro.add(fechaN);
		
		
		JLabel correoregistro = new JLabel("Ingrese su correo electronico",JLabel.CENTER);
		correoregistro.setFont(new Font("Comic Sans", Font.BOLD,16));
		correoregistro.setForeground(Color.decode("#ffffff"));
		correoregistro.setSize(250, 30);
		correoregistro.setLocation(75, 215);
		correoregistro.setOpaque(true);
		correoregistro.setBackground(Color.decode("#1f7690"));
		registro.add(correoregistro);
		

		JLabel NumeroDeTelefono = new JLabel("Numero Telefonico",JLabel.CENTER);
		NumeroDeTelefono.setFont(new Font("Comic Sans", Font.BOLD,16));
		NumeroDeTelefono.setForeground(Color.decode("#ffffff"));
		NumeroDeTelefono.setSize(250, 30);
		NumeroDeTelefono.setLocation(75, 280);
		NumeroDeTelefono.setOpaque(true);
		NumeroDeTelefono.setBackground(Color.decode("#1f7690"));
		registro.add(NumeroDeTelefono);
		
		JLabel contrasena = new JLabel("Ingrese una contraseña",JLabel.CENTER);
		contrasena.setFont(new Font("Comic Sans", Font.BOLD,16));
		contrasena.setForeground(Color.decode("#ffffff"));
		contrasena.setSize(250, 30);
		contrasena.setLocation(75, 345);
		contrasena.setOpaque(true);
		contrasena.setBackground(Color.decode("#1f7690"));
		registro.add(contrasena);


		//Campo Para Escribir
		//Nombres
		JTextField username = new JTextField();
		username.setSize(250, 30);
		username.setLocation(75, 70);
		registro.add(username);
		
		//Apellido
		JTextField apellidos = new JTextField();
		apellidos.setSize(250, 30);
		apellidos.setLocation(75, 125);
		registro.add(apellidos);
		
		
		//Fecha de nacimiento
		JTextField FechaN = new JTextField();
		FechaN.setSize(250, 30);
		FechaN.setLocation(75, 180);
		registro.add(FechaN);
		
		
		//Correo electronico
		JTextField Correo= new JTextField();
		Correo.setSize(250, 30);
		Correo.setLocation(75, 245);
		registro.add(Correo);
		
		
		//Numero de telefono
		JTextField Num = new JTextField();
		Num.setSize(250, 30);
		Num.setLocation(75, 310);
		registro.add(Num);
		 Num.addKeyListener(new KeyAdapter() {
	            public void keyTyped(KeyEvent e) {
	                char character = e.getKeyChar();
	                if (!(Character.isDigit(character) ||
	                      (character == KeyEvent.VK_BACK_SPACE) ||
	                      (character == KeyEvent.VK_DELETE))) {
	                    e.consume();
	                }
	            }
	        });
		
		
		//contraseña
		JPasswordField password = new JPasswordField();
		password.setSize(250, 30);
		password.setLocation(75, 370);
		registro.add(password);
		
		JLabel tyc = new JLabel("Terminos y condiciones",JLabel.CENTER);
		tyc.setFont(new Font("Comic Sans", Font.BOLD,16));
		tyc.setForeground(Color.decode("#ffffff"));
		tyc.setSize(250, 40);
		tyc.setLocation(75, 410);
		tyc.setOpaque(true);
		tyc.setBackground(Color.decode("#1f7690"));
		registro.add(tyc);
		
		JRadioButton acepto = new JRadioButton("Acepto");
		acepto.setSize(100, 20);
		acepto.setLocation(75, 460);
		acepto.setOpaque(true);
		acepto.setBackground(Color.decode("#1f7690"));
		registro.add(acepto);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(acepto);
		
		JButton finalregistro = new JButton();
		finalregistro.setText("Aceptar");
		finalregistro.setSize(100, 40);
		finalregistro.setLocation(150, 495);
		finalregistro.setOpaque(true);
		finalregistro.setBackground(Color.white);
		registro.add(finalregistro);
		
		JButton cancelar = new JButton();
		cancelar.setText("Cancelar");
		cancelar.setSize(90, 20);
		cancelar.setLocation(300, 510);
		cancelar.setOpaque(true);
		cancelar.setBackground(Color.white);
		registro.add(cancelar);
		
	
		anterior=actual;
		actual=registro;
		remove(anterior);
		add(actual);
		repaint();
		revalidate();
	}
	
	
	public void ListaUsuario() {
		
	        listaUsuarios = new JPanel(new BorderLayout());
	        listaUsuarios.setSize(525,700);
	        listaUsuarios.setLocation(0,0);
	        listaUsuarios.setLayout(null);
	        listaUsuarios.setBackground(Color.decode("#dce7ec"));
	        

	        JLabel listausuario= new JLabel("Lista de usuarios");
   		 listausuario.setFont(new Font("Comic Sans", Font.BOLD,30));
   		 listausuario.setForeground(Color.decode("#1f7690"));
   		 listausuario.setBounds(130, 10, 300, 50);
   		 listaUsuarios.add(listausuario);
   		
   		 JLabel editar = new JLabel("Editar");
   	     editar.setFont(new Font("Comic Sans", Font.BOLD,20));
   	     editar.setForeground(Color.decode("#1f7690"));
   	     editar.setBounds(10, 30, 450, 100);
   	     listaUsuarios.add(editar);
   	     
   	 

	        anterior=actual;
			actual=listaUsuarios;
			remove(anterior);
			add(actual);
			
			repaint();
			revalidate();
	        
	    }
	
	
	

	
	public void Perfil() {
		perfil = new JPanel();
		perfil.setSize(525,700);
		perfil.setLocation(0,0);
		perfil.setLayout(null);
		perfil.setBackground(Color.decode("#dce7ec"));
		
		JMenu cuentaMenu = new JMenu("Cuenta");
		JMenuItem miCuentaMenuItem = new JMenuItem("Editar");
		cuentaMenu.add(miCuentaMenuItem);
		JMenuItem cerrarSesionMenuItem = new JMenuItem("Cerrar sesion");
		cuentaMenu.add(cerrarSesionMenuItem);
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(cuentaMenu);
		this.setJMenuBar(menuBar);
		
		

		JMenu usuariosMenu = new JMenu("Usuarios");
		JMenuItem ConsultaMenuItem = new JMenuItem("Consultar Cuenta");
		JMenuItem crearUsuarioMenuItem = new JMenuItem("Crear usuarios");
		JMenuItem downloadInfo= new JMenuItem("Descargar Info");
		usuariosMenu.add(ConsultaMenuItem);
		usuariosMenu.add(crearUsuarioMenuItem);
		menuBar.add(usuariosMenu);
		usuariosMenu.add(downloadInfo);

		
		
		JLabel bienvenidaPerfil = new JLabel();
		bienvenidaPerfil.setText("Bienvenido "+ nombre);
		bienvenidaPerfil.setBounds(100, 10, 300, 80);
		bienvenidaPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		bienvenidaPerfil.setForeground(Color.BLACK);
		bienvenidaPerfil.setFont(new Font("cooper black",0,25));
		
		perfil.add(bienvenidaPerfil);
		
		JLabel iconoLista = new JLabel(new ImageIcon("material-escolar(1).PNG"));
		iconoLista.setBounds(165, 80, 170, 170);
		perfil.add(iconoLista);
		
		
		miCuentaMenuItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        
		        menuMiCuenta();
		    }
		});
		
		crearUsuarioMenuItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        
		        menuCrearUsuario();
		    }
		});
		
		crearUsuarioMenuItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        
		        menuCrearUsuario();
		    }
		});
		
		cerrarSesionMenuItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        
		    	JOptionPane.showMessageDialog(null,"Adios "+nombre,"Bye bye", JOptionPane.CLOSED_OPTION );
		    	menuBar.remove(cuentaMenu);
		    	menuBar.remove(usuariosMenu);
		    	
		    	Login();
		    	
		    }
		});
		
		
		

		ConsultaMenuItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        
		        ListaUsuario();
		    }
		});
		
		
		downloadInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				descargar(1);
			}
			
		});
		

		anterior=actual;
		actual=perfil;
		remove(anterior);
		add(actual);
		
		repaint();
		revalidate();
		
		

	}

	public void descargar(int numUsuarioDescargar) {
		  
		  String infoUsuario = "";
		  
		  try {
				BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
				String line = reader.readLine();

				for (int i = 1; i < numUsuarioDescargar; i++) {
					line = reader.readLine();				
				}

				infoUsuario = line;
				
				reader.close();

			} catch (IOException e1) {
				e1.printStackTrace();
			} 
		  
		  //crea cuadro de seleccion de direccion
		  JFileChooser fileChooser = new JFileChooser();
		  fileChooser.setDialogTitle("Seleccionar destino de descarga");

		  // muestra el cuadro y espera a que seleccione una direccion
		  int userSelection = fileChooser.showSaveDialog(this);
		  if (userSelection == JFileChooser.APPROVE_OPTION) {
			  // guarda la direccion seleccionada
			  String filePath = fileChooser.getSelectedFile().getAbsolutePath();

			  try {
				  //crea el archivo de texto
				  File file = new File(filePath+".txt");
				  BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				  writer.write(infoUsuario);
				  writer.close();
			  } catch (IOException e1) {
				  e1.printStackTrace();
			  }

		  }
	  }
	
}