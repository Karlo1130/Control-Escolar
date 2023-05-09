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
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;
import java.util.*;

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
import javax.swing.JFileChooser;

public class MiVentana extends JFrame {
	JPanel actual=null;
	JPanel anterior=null;
	JPanel bienvenida;
	JPanel login;
	JPanel miCuentaPanel;
	JPanel registro;
	JPanel perfil;
	JPanel consultar;
	JPanel accesoPermitido;
    JPanel listaUsuarios;
    
	String nombre;
	
	DefaultTableModel tableModel;
	
	JTable table;
	
	private String[] alumnosColumns  = {"Nombre", "Apellidos", "Fechad de nacimiento", "Correo", "Telefono", "Foto"};
	private String[] maestrosColumns = {"Nombre", "Apellidos", "Fechad de nacimiento", "Correo", "Telefono", "Grado de estudio", "Foto"};
	
	ArrayList<String[]> usersList;
	
	private int posicionUsuario;
	
    private JPanel filtroUsuarios;
    private boolean estudiante = false;
    private boolean docente = false;

	private DefaultTableModel modelo;


	
	public MiVentana() {
		this.setVisible(true);
		this.setSize(525, 700);
		this.setLocationRelativeTo(null);
		this.setTitle("Menu Principal");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.decode("#dce7ec"));
		
		getMaestros();
	
		Bienvenida();
		timer();
		repaint();
		revalidate();
		
		
	}
	
	
	public void timer() {//Funcion timer realizada por Garayzar Ricardo
		
		Timer timer = new Timer();
		
		TimerTask tarea = new TimerTask() {
			@Override
			public void run() {
				filtroUsuarios();
			}
		};
		
		timer.schedule(tarea, 2000);
		
	}

	
	public void Bienvenida() {//Panel bienvenida realizado por Garayzar Ricardo

		bienvenida = new JPanel();
		bienvenida.setSize(525, 700);//COMENTARIO DE CAMPA: MODIFIQUE EL TAMAÑO Y LA UBICACION 
		bienvenida.setLocation(0,0);
		bienvenida.setLayout(null);
        bienvenida.setBackground(Color.decode("#dce7ec"));
		
		JLabel titleinicio = new JLabel("Bienvenido",JLabel.CENTER);
		titleinicio.setFont(new Font("Comic Sans", Font.BOLD,30));
		titleinicio.setSize(260, 40);
		titleinicio.setLocation(130, 200);
		titleinicio.setOpaque(false);
		titleinicio.setBackground(Color.decode("#1f7690"));
		bienvenida.add(titleinicio);
		
		JLabel fondo = new JLabel(new ImageIcon("fondo.PNG"));//COMENTARIO DE CAMPA: AÑADI UNA IMAGEN DE FONDO
		fondo.setBounds(-2, 1, 525, 700);//ANCHO X, Y,TAMAÑO EC0307 008299
		bienvenida.add(fondo);
	
		
		
		actual=bienvenida;
		
		add(actual);
		
		repaint();
		revalidate();
	}
	
		
	
	public void menuMiCuenta() {//Panel menuMicuenta realizado por Garayzar Ricardo
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

		JLabel fondo = new JLabel(new ImageIcon("fondo2.PNG"));//COMENTARIO DE CAMPA: AÑADI UNA IMAGEN DE FONDO
		fondo.setBounds(-2, 1, 525, 700);//ANCHO X, Y,TAMAÑO EC0307 008299
		miCuentaPanel.add(fondo);
        
        cancelButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        
		       Perfil();
		    }
		});
        
        updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			    actualizarUsuario(nameField, apellidoField, emailField, NumeroTelefono, passwordField, confirmPasswordField, posicionUsuario);
			    }
        	
        });
        
        
        anterior = actual;
        actual = miCuentaPanel;
        
        remove(anterior);
        add(actual);
        
        revalidate();
        repaint();
	}
	
	
	public void Login () {//panel Login realizado por Garayzar Ricardo
		login = new JPanel();
		login.setSize(525, 700);//COMENTARIO DE CAMPA: MODIFIQUE EL TAMAÑO Y LA UBICACION DEL PANEL
		login.setLocation(0,0);
		login.setLayout(null);
        login.setBackground(Color.decode("#dce7ec"));
		
		JLabel titleinicio = new JLabel("Iniciar Sesion",JLabel.CENTER);//COMENTARIO DE CAMPA: MODIFIQUE LAS POSICIONES
		titleinicio.setFont(new Font("Comic Sans", Font.BOLD,20));
		titleinicio.setSize(280, 40);
		titleinicio.setLocation(125, 40);//60
		titleinicio.setOpaque(false);
		titleinicio.setForeground(Color.decode("#27374F"));
		titleinicio.setBackground(Color.decode("#608AC5"));
		login.add(titleinicio);
		
		JLabel usuario = new JLabel("Ingrese su usuario",JLabel.CENTER);
		usuario.setFont(new Font("Comic Sans", Font.BOLD,16));
		usuario.setSize(250, 20);
		usuario.setLocation(140, 95);//75
		usuario.setOpaque(true);
		usuario.setForeground(Color.decode("#27374F"));
		usuario.setBackground(Color.decode("#5C87C0"));
		login.add(usuario);
		


		JLabel iniciarcontraseña = new JLabel("Contraseña",JLabel.CENTER);
		iniciarcontraseña.setFont(new Font("Comic Sans", Font.BOLD,16));
		iniciarcontraseña.setSize(250, 30);
		iniciarcontraseña.setLocation(140, 155);
		iniciarcontraseña.setOpaque(true);
		iniciarcontraseña.setForeground(Color.decode("#27374F"));
		iniciarcontraseña.setBackground(Color.decode("#5C87C0"));
		login.add(iniciarcontraseña);

		


		JButton entrarcuentar = new JButton();
		entrarcuentar.setText("Aceptar");
		entrarcuentar.setSize(100, 40);
		entrarcuentar.setLocation(140, 225);
		entrarcuentar.setOpaque(false);
		entrarcuentar.setBackground(Color.white);
		login.add(entrarcuentar);
		
		JButton salir = new JButton();
		salir.setText("Salir");
		salir.setSize(100, 40);
		salir.setLocation(290, 225);//225
		salir.setOpaque(false);
		salir.setBackground(Color.white);
		login.add(salir);
		
		JTextField username = new JTextField("Rick_21@alu.uabcs.mx");
		username.setSize(250, 30);
		username.setLocation(140, 120);
		login.add(username);
		
		JPasswordField password = new JPasswordField("12345");
		password.setSize(250, 30);
		password.setLocation(140, 190);
		login.add(password);

		salir.addActionListener(new ActionListener() {
			@Override
			
			public void actionPerformed(ActionEvent e) {

				remove(actual);
				filtroUsuarios();
				repaint();
				revalidate();
			}
			
		});
		
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
					
					int aux=0;
					
					if(estudiante==true)//CAMPA
					{
						if(user.contains("@alu.uabcs.mx") && !user.contains("@uabcs.mx") ) {//COMENTARIO DE CAMPA: AÑADÍ FILTROS PARA LOS CORREOS DE ESTUDIANTES O DOCENTES
							while(line != null) {//CAMPA Y KARLO  
								
									
								String data [] = line.split(",");
								
								for (int i = 0; i < data.length; i++) {

									System.out.println(data[i]); 
								}
								 
								if( user.equals(data[3]) ) {
									
									if( pwd.equals(data[5]) ) {
										posicionUsuario = aux;
										flag = true;
									}
								} 
								
								line = reader.readLine();
								aux++;
								
								
							}
						}else {
							JOptionPane.showMessageDialog(null, "Usuario incorrecto","ERROR",JOptionPane.ERROR_MESSAGE );
						}
						
					}//FIN DEL IF ESTUDIANTES
					else {
						if(docente==true) {//CAMPA
							if(!user.contains("@alu.uabcs.mx") && user.contains("@uabcs.mx")) {//CAMPA
								while(line != null) {//CAMPA Y KARLO  
									
										
									String data [] = line.split(",");
									 
									if( user.equals(data[3]) ) {
										
										if( pwd.equals(data[5]) ) {
											posicionUsuario = aux;
											flag = true;
										}
									} 
									
									line = reader.readLine();
									aux++;
									
									
								}
							}else {
								JOptionPane.showMessageDialog(null, "Usuario incorrecto","ERROR",JOptionPane.ERROR_MESSAGE );
							}
						}
					}
					if(flag==true) {
						
						String[] datos = getDatosUsuario(posicionUsuario);
						
						nombre = datos[0];
						JOptionPane.showMessageDialog(null,"Bienvenido "+nombre,"Acceso Permitido", JOptionPane.CLOSED_OPTION );
						Perfil();
						
					}/*else {
						JOptionPane.showMessageDialog(null,"Error","Datos incorrectos, intentar denuevo.", JOptionPane.ERROR_MESSAGE );
					}*/
					
					
				} catch(IOException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		JLabel btn3 = new JLabel(new ImageIcon("boton.PNG"));
		btn3.setBounds(140, 225, 100, 40);
		login.add(btn3);
		
		JLabel btn4 = new JLabel(new ImageIcon("boton.PNG"));
		btn4.setBounds(290, 225, 100, 40);
		login.add(btn4);
		
		JLabel fondo = new JLabel(new ImageIcon("fondo.PNG"));//COMENTARIO DE CAMPA: AÑADI FONDO
		fondo.setBounds(-2, 1, 525, 700);
		login.add(fondo); 
	
		
		anterior=actual;
		actual=login;
		remove(anterior);
		add(actual);
		
		repaint();
		revalidate();
}	
	
	public void filtroUsuarios () {//CAMPA
		filtroUsuarios = new JPanel();
		filtroUsuarios.setSize(525, 700);
		filtroUsuarios.setLocation(0,0);
		filtroUsuarios.setLayout(null);
		filtroUsuarios.setBackground(Color.decode("#008299"));
		
		JLabel titleinicio = new JLabel("Elija el tipo de Usuario",JLabel.CENTER);
		titleinicio.setFont(new Font("Comic Sans", Font.BOLD,20));
		titleinicio.setSize(280, 40);
		titleinicio.setLocation(125,100);
		titleinicio.setOpaque(false);
		titleinicio.setBackground(Color.decode("#4e7485"));
		filtroUsuarios.add(titleinicio);
		
		JButton estudi = new JButton();
		estudi.setText("Estudiante");
		estudi.setSize(100, 40);
		estudi.setLocation(100, 225);
		estudi.setOpaque(false);
		estudi.setBackground(Color.white);
		filtroUsuarios.add(estudi);
		
		JButton docent = new JButton();
		docent.setText("Docente");
		docent.setSize(100, 40);
		docent.setLocation(320, 225);
		docent.setOpaque(false);
		docent.setBackground(Color.white);
		filtroUsuarios.add(docent);
		docent.addActionListener(new ActionListener() {
			@Override
			
			public void actionPerformed(ActionEvent e) {
				docente = true;
		        estudiante = false;
				Login();
			}
			
		});
		
		
		estudi.addActionListener(new ActionListener() {
			@Override
			
			public void actionPerformed(ActionEvent e) {
				estudiante = true;
		        docente = false;
				Login();
			}
			
		});
	
		JLabel btn1 = new JLabel(new ImageIcon("boton.PNG"));
		btn1.setBounds(100, 225, 100, 40);
		filtroUsuarios.add(btn1);
		
		JLabel btn2 = new JLabel(new ImageIcon("boton.PNG"));
		btn2.setBounds(320, 225, 100, 40);
		filtroUsuarios.add(btn2);
		
		JLabel fondo = new JLabel(new ImageIcon("fondo.PNG"));
		fondo.setBounds(-2, 1, 525, 700);
		filtroUsuarios.add(fondo);
		
		anterior=actual;
		actual=filtroUsuarios;
		remove(anterior);
		add(actual);
		
		repaint();
		revalidate();
}	


public void menuCrearUsuario() {//panel menuCrearUsuario realizado por Garayzar Ricardo
		
		registro = new JPanel();
		registro.setSize(525, 700);
		registro.setLocation(0,0);
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
		finalregistro.setLocation(100, 550);
		finalregistro.setOpaque(true);
		finalregistro.setBackground(Color.white);
		registro.add(finalregistro);

		JButton cancelar = new JButton();
		cancelar.setText("Cancelar");
		cancelar.setSize(90, 20);
		cancelar.setLocation(300, 570);
		cancelar.setOpaque(true);
		cancelar.setBackground(Color.white);
		registro.add(cancelar);
		//--------------------------------------------------------------------CAMPA
		
		JButton colocarImagen = new JButton();
		colocarImagen.setText("Colocar Imagen");
		colocarImagen.setSize(130, 40);
		colocarImagen.setLocation(160, 495);
		colocarImagen.setOpaque(true);
		colocarImagen.setBackground(Color.white);
		registro.add(colocarImagen);
		
		colocarImagen.addActionListener(new ActionListener() {//MENSAJE CAMPA: LE PUSE FUNCIONALIDAD AL BOTÓN CANCELAR

			@Override
			public void actionPerformed(ActionEvent e) {
			
			}
			
		});
	if(docente) {//CAMPA
		//PANEL
		registro.setSize(400,600);
		registro.setLocation(50,10);//(50,50)MENSAJE DE CAMPA: MODIFIQUE LA ALTURA DEL PANEL PARA AÑADIR UNA OPCION ALTERANATIVA
		//BOTON CANCELAR
		cancelar.setSize(90, 20);
		cancelar.setLocation(300, 570);
		//BOTON ACEPTO
		acepto.setSize(100, 20);
		acepto.setLocation(75, 520);
		//TERMINOS Y CONDICIONES
		tyc.setSize(250, 40);
		tyc.setLocation(75, 470);
		//BOTON ACEPTAR
		finalregistro.setSize(100, 40);
		finalregistro.setLocation(50, 550);
		//ETIQUETA
		JLabel gradoDocente = new JLabel("Ingrese su grado de estudio",JLabel.CENTER);
		gradoDocente.setFont(new Font("Comic Sans", Font.BOLD,16));
		gradoDocente.setForeground(Color.decode("#ffffff"));
		gradoDocente.setSize(250, 20);
		gradoDocente.setLocation(75, 405);
		gradoDocente.setOpaque(true);
		gradoDocente.setBackground(Color.decode("#1f7690"));
		registro.add(gradoDocente);
		//GRADO DE ESTUDIO
		JTextField gradoEstudio = new JTextField();
		gradoEstudio.setSize(250, 30);
		gradoEstudio.setLocation(75, 425);
		registro.add(gradoEstudio);
			
			finalregistro.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					CrearUsuarioDocente(username, apellidos, FechaN, Correo, Num, password, gradoEstudio);
				}
				
			});
			}
		else {
			//PANEL
			registro.setSize(400,550);
			registro.setLocation(50,50);
			//BOTON CANCELAR
			cancelar.setSize(90, 20);
			cancelar.setLocation(300, 510);
			//BOTON ACEPTO
			acepto.setSize(100, 20);
			acepto.setLocation(75, 460);
			//TERMINOS Y CONDICIONES
			tyc.setSize(250, 40);
			tyc.setLocation(75, 410);
			//BOTON ACEPTAR
			finalregistro.setSize(100, 40);
			finalregistro.setLocation(50, 495);
			finalregistro.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					CrearUsuario(username, apellidos, FechaN, Correo, Num, password);
				}
				
			});
			
			}

		
		cancelar.addActionListener(new ActionListener() {//MENSAJE CAMPA: LE PUSE FUNCIONALIDAD AL BOTÓN CANCELAR

			@Override
			public void actionPerformed(ActionEvent e) {
				remove(actual);
				Perfil();
				repaint();
				revalidate();
			}
			
		});
		

		
		//--------------------------------------------------------------------
		
		JLabel fondo = new JLabel(new ImageIcon("fondo.PNG"));//COMENTARIO DE CAMPA: AÑADI UNA IMAGEN DE FONDO
		fondo.setBounds(-2, 1, 525, 700);//ANCHO X, Y,TAMAÑO EC0307 008299
		registro.add(fondo);

		anterior=actual;
		actual=registro;
		remove(anterior);
		add(actual);
		repaint();
		revalidate();
	}
	
	public void ListaUsuario() {//panel ListaUsuario realizado por Garayzar Ricardo
		
		listaUsuarios = new JPanel(new BorderLayout());
		listaUsuarios.setSize(525,700);
		listaUsuarios.setLocation(0,0);
		listaUsuarios.setLayout(null);
		listaUsuarios.setBackground(Color.decode("#dce7ec"));
		
		

		JLabel listausuario= new JLabel("Lista de usuarios");
		listausuario.setFont(new Font("Comic Sans", Font.BOLD,30));
		listausuario.setBounds(130, 10, 300, 50);
		listaUsuarios.add(listausuario);

		JLabel editarAlumnos = new JLabel("Consultar alumnos");
		editarAlumnos.setFont(new Font("Comic Sans", Font.BOLD,20));
		editarAlumnos.setBounds(10, 30, 200, 100);
		listaUsuarios.add(editarAlumnos);

		JLabel editarMaestros = new JLabel("Consultar maestros");
		editarMaestros.setFont(new Font("Comic Sans", Font.BOLD,20));
		editarMaestros.setBounds(300, 30, 200, 100);
		listaUsuarios.add(editarMaestros);
		
		JButton alumnosBtn = new JButton("A");
		alumnosBtn.setBounds(10, 130, 200, 100);
		listaUsuarios.add(alumnosBtn);
		
		JButton maestrosBtn = new JButton("M");
		maestrosBtn.setBounds(300, 130, 200, 100);
		listaUsuarios.add(maestrosBtn);
		

		alumnosBtn.addActionListener(new ActionListener() {
			@Override
			
			public void actionPerformed(ActionEvent e) {
				docente = false;
		        estudiante = true;
				
		        ConsultarAlumnos();
			}
			
		});
		
		
		 maestrosBtn.addActionListener(new ActionListener() {
			@Override
			
			public void actionPerformed(ActionEvent e) {
				docente = true;
		        estudiante = false;
		        ConsultarMaestros();
			}
			
		});
		 
			JLabel fondo = new JLabel(new ImageIcon("fondo.PNG"));//COMENTARIO DE CAMPA: AÑADI UNA IMAGEN DE FONDO
			fondo.setBounds(-2, 1, 525, 700);//ANCHO X, Y,TAMAÑO EC0307 008299
			listaUsuarios.add(fondo);


		anterior=actual;
		actual=listaUsuarios;
		remove(anterior);
		add(actual);

		repaint();
		revalidate();
	        
	    }
	public void ConsultarMaestros() {

		consultar= new JPanel();
		consultar.setSize(525,700);
		consultar.setLocation(0,0);
		consultar.setLayout(null);
        consultar.setBackground(Color.decode("#dce7ec"));
        
        JLabel eliminarI= new JLabel("Consultar Informacion de Maestros");
        eliminarI.setFont(new Font("Comic Sans", Font.BOLD,30));
        eliminarI.setBounds(110, 10, 300, 50);
        consultar.add(eliminarI);
	
        JScrollPane scroll = new JScrollPane(new Tabla(getMaestros(), maestrosColumns));
        scroll.setBounds(0, 330, 510, 200);
        scroll.setVisible(true);
        consultar.add(scroll);
        
        anterior=actual;
		actual=consultar;
		remove(anterior);
		add(actual);

		repaint();
		revalidate();
	}
	
	public void ConsultarAlumnos() {

		consultar= new JPanel();
		consultar.setSize(525,700);
		consultar.setLocation(0,0);
		consultar.setLayout(null);
        consultar.setBackground(Color.decode("#dce7ec"));
        
        JLabel eliminarI= new JLabel("Consultar Informacion de Alumnos");
        eliminarI.setFont(new Font("Comic Sans", Font.BOLD,30));
        eliminarI.setBounds(110, 10, 300, 50);
        consultar.add(eliminarI);
	
        JScrollPane scroll = new JScrollPane(new Tabla(getAlumnos(), alumnosColumns));
        scroll.setBounds(0, 330, 510, 200);
        scroll.setVisible(true);
        consultar.add(scroll);
		
        anterior=actual;
		actual=consultar;
		remove(anterior);
		add(actual);

		repaint();
		revalidate();
	}
	
	public void Perfil() {//panel perfil realizado por Garayzar Ricardo
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
		    	
		    	filtroUsuarios();
		    	
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
				descargar(posicionUsuario);
//				try {
//					eliminarUsuario(posicionUsuario);
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			}
			
		});
		
		JLabel fondo = new JLabel(new ImageIcon("fondo.PNG"));//COMENTARIO DE CAMPA: AÑADI UNA IMAGEN DE FONDO
		fondo.setBounds(-2, 1, 525, 700);//ANCHO X, Y,TAMAÑO EC0307 008299
		perfil.add(fondo);

		anterior=actual;
		actual=perfil;
		remove(anterior);
		add(actual);
		
		repaint();
		revalidate();
		
		

	}
	
	/*
	 * funciones por Espinoza Karlo
	 */
	
	public void descargar(int numUsuarioDescargar) {
		  
		  String infoUsuario = "";
		  
		  try {
				BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
				String line = reader.readLine();

				for (int i = 0; i < numUsuarioDescargar; i++) {
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

	public void eliminarUsuario(int numUsuarioEliminar) throws IOException {
		
	File archivo = new File("users.txt");
	Scanner entrada = new Scanner(archivo);
	List<String> lineas = new ArrayList<String>();

	while (entrada.hasNextLine()) {
		lineas.add(entrada.nextLine());
	}
	entrada.close();

	int numeroLineaAEliminar = numUsuarioEliminar;
	lineas.remove(numeroLineaAEliminar);

	
	BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
	for (String linea : lineas) {
		writer.write(linea + "\n");
	}
	
	writer.close();
	
	}

	public void CrearUsuarioDocente(JTextField nombreText, JTextField apellidosText, //CAMPA
			JTextField FechaN, JTextField correo, JTextField numT, JPasswordField contraseñaText ,JTextField  gradoEstudio) {

//		CrearUsuario(username, apellidos, FechaN, Correo, Num, password);


		//String password = new String(contraseñaText.getPassword());
		String password = new String(contraseñaText.getPassword());
		String datosUsuario = "";

		boolean match = false;

		if(password.length() != 0
				&& nombreText.getText().length() != 0
				&& apellidosText.getText().length() != 0
				&& FechaN.getText().length() != 0
				&& correo.getText().length() != 0
				&& gradoEstudio.getText().length() != 0
				) {

			try {
				FileWriter writer = new FileWriter("users.txt", true);

				BufferedWriter buffWriter = new BufferedWriter(writer);

				datosUsuario = nombreText.getText()+","
						+apellidosText.getText()+","
						+FechaN.getText()+","
						+correo.getText()+","
						+numT.getText()+","
						+password+","
						+gradoEstudio.getText();

				BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
				String line = reader.readLine();

				String[] lineArray = null;

				while (line != null) {
					lineArray = line.split(",");

					if(correo.getText().equals(lineArray[3])) {
						match = true;
					}

					line = reader.readLine();
				}
				reader.close();

				if(!match) {
					buffWriter.write(datosUsuario);
					buffWriter.newLine();
					buffWriter.close();
					JOptionPane.showMessageDialog(this, "Cuenta creada con exito", "Listo!", JOptionPane.INFORMATION_MESSAGE);


				}
				else {
					JOptionPane.showMessageDialog(this, "Error al registrar", "Error", JOptionPane.ERROR_MESSAGE);
				}

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else {

			JOptionPane.showMessageDialog(this, "Error al registrar", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public void CrearUsuario(JTextField nombreText, JTextField apellidosText, 
			JTextField FechaN, JTextField correo, JTextField numT, JPasswordField contraseñaText) {

//		CrearUsuario(username, apellidos, FechaN, Correo, Num, password);


		//String password = new String(contraseñaText.getPassword());
		String password = new String(contraseñaText.getPassword());
		String datosUsuario = "";

		boolean match = false;

		if(password.length() != 0
				&& nombreText.getText().length() != 0
				&& apellidosText.getText().length() != 0
				&& FechaN.getText().length() != 0
				&& correo.getText().length() != 0) {

			try {
				FileWriter writer = new FileWriter("users.txt", true);

				BufferedWriter buffWriter = new BufferedWriter(writer);

				datosUsuario = nombreText.getText()+","
						+apellidosText.getText()+","
						+correo.getText()+","
						+FechaN.getText()+","
						+numT.getText()+","
						+password;

				BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
				String line = reader.readLine();

				String[] lineArray = null;

				while (line != null) {
					lineArray = line.split(",");

					if(correo.getText().equals(lineArray[3])) {
						match = true;
					}

					line = reader.readLine();
				}
				reader.close();

				if(!match) {
					buffWriter.write(datosUsuario);
					buffWriter.newLine();
					buffWriter.close();
					JOptionPane.showMessageDialog(this, "Cuenta creada con exito", "Listo!", JOptionPane.INFORMATION_MESSAGE);


				}
				else {
					JOptionPane.showMessageDialog(this, "Error al registrar", "Error", JOptionPane.ERROR_MESSAGE);
				}

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else {

			JOptionPane.showMessageDialog(this, "Error al registrar", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public void actualizarUsuario(JTextField nombreText, JTextField apellidosText, 
			JTextField correo, JTextField telefono, JPasswordField contraseñaText, JPasswordField contraseñaText2, int numUsuario) {

//		CrearUsuario(username, apellidos, FechaN, Correo, Num, password);


		//String password = new String(contraseñaText.getPassword());
		String password = new String(contraseñaText.getPassword());
		String password2 = new String(contraseñaText2.getPassword());
		String datosUsuario = "";

		boolean match = false;

		if(password.equals(password2) && password.length() != 0
				&& nombreText.getText().length() != 0
				&& apellidosText.getText().length() != 0
				&& correo.getText().length() != 0) {

			try {
				
				BufferedReader readerCounter = new BufferedReader(new FileReader("users.txt"));
				String newLine = readerCounter.readLine();

				for (int i = 0; i < numUsuario; i++) {
					newLine = readerCounter.readLine();
				}
				
				String data[] = newLine.split(",");

				datosUsuario = nombreText.getText()+","
						+apellidosText.getText()+","
						+data[2]+","
						+correo.getText()+","
						+data[4]+","
						+password;

				BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
				String line = reader.readLine();

				String[] lineArray = null;

				while (line != null) {
					lineArray = line.split(",");

					if(correo.getText().equals(lineArray[3])) {
						match = true;
					}

					line = reader.readLine();
				}
				reader.close();

				if(!match) {
					
					eliminarUsuario(numUsuario);
					
					FileWriter writer = new FileWriter("users.txt", true);
					BufferedWriter buffWriter = new BufferedWriter(writer);
					
					buffWriter.write(datosUsuario);
					buffWriter.newLine();
					buffWriter.close();
					JOptionPane.showMessageDialog(this, "Cuenta creada con exito", "Listo!", JOptionPane.INFORMATION_MESSAGE);

					/*
					 * logica de el cambio de ventanas
					 */


				}
				else {
					JOptionPane.showMessageDialog(this, "Error al registrar1", "Error", JOptionPane.ERROR_MESSAGE);
				}

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else {

			JOptionPane.showMessageDialog(this, "Error al registrar", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public String[] getDatosUsuario(int numUsuario) {
		
		String[] datos = null;

		try {
			BufferedReader readerCounter = new BufferedReader(new FileReader("users.txt"));
			String line = readerCounter.readLine();
			
			datos = line.split(",");
			
			for (int i = 0; i < numUsuario; i++) {
				
				line = readerCounter.readLine();
				datos = line.split(",");
			}

			readerCounter.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		
		return datos;
	}
	
	public Object[][] getMaestros() {
		
//		String[] columns = {"Nombre", "Apellidos", "Fechad de nacimiento", "Correo", "Telefono", "Grado de estudio"};
		Object[][] data = new Object[getNumeroDeMaestros()][maestrosColumns.length];
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
			String line = reader.readLine();
			
			String[] lineArray = line.split(",");;

			int aux = 0;
			
			boolean addDato = false;

			//añade los datos de la base de datos a la matriz data
			while (line != null) {
				lineArray = line.split(",");
				
				
				for (int i = 0; i < lineArray.length-1; i++) {

					if(lineArray[3].contains("@uabcs.mx"))
					{

						if(i == lineArray.length-3) {
							data[aux][i] = lineArray[i+1];
							addDato = true;
						}else if(i == lineArray.length-2) {
							data[aux][i] = new JLabel(new ImageIcon(lineArray[i+1]));
							addDato = true;
						}else {
						data[aux][i] = lineArray[i];
						addDato = true;
						}
					}

				}
				line = reader.readLine();
				if(addDato) {
				aux++;
				addDato = false;
				}
			}

			reader.close();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
		return data;
	}
	
	public int getNumeroDeMaestros() {

		int numLines = 0;

		try {
			BufferedReader readerCounter = new BufferedReader(new FileReader("users.txt"));
			String lineCounter = readerCounter.readLine();

			String[] data = lineCounter.split(",");
			
			while(lineCounter!=null) {
				if(data[3].contains("@uabcs.mx")) {
				numLines++;
				}
				lineCounter = readerCounter.readLine();
				
				if(lineCounter!=null) {
				data = lineCounter.split(",");
				}
			}

			readerCounter.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		} 

		return numLines;

	}
	
	public Object[][] getAlumnos() {
		
//		String[] columns = {"Nombre", "Apellidos", "Fechad de nacimiento", "Correo", "Telefono", "Grado de estudio"};
		Object[][] data = new Object[getNumeroDeAlumnos()][alumnosColumns.length];
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
			String line = reader.readLine();
			
			String[] lineArray = line.split(",");;

			int aux = 0;
			
			boolean addDato = false;

			//añade los datos de la base de datos a la matriz data
			while (line != null) {
				lineArray = line.split(",");
				
				
				for (int i = 0; i < lineArray.length-1; i++) {

					if(lineArray[3].contains("@alu.uabcs.mx"))
					{

						if(i == lineArray.length-2) {
							data[aux][i] = new JLabel(new ImageIcon(lineArray[i+1]));
							addDato = true;
						}else {
						data[aux][i] = lineArray[i];
						addDato = true;
						}
					}

				}
				line = reader.readLine();
				if(addDato) {
				aux++;
				addDato = false;
				}
			}

			reader.close();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
		return data;
	}
	
	public int getNumeroDeAlumnos() {

		int numLines = 0;

		try {
			BufferedReader readerCounter = new BufferedReader(new FileReader("users.txt"));
			String lineCounter = readerCounter.readLine();

			String[] data = lineCounter.split(",");
			
			while(lineCounter!=null) {
				if(data[3].contains("@alu.uabcs.mx")) {
				numLines++;
				}
				lineCounter = readerCounter.readLine();
				
				if(lineCounter!=null) {
					data = lineCounter.split(",");
				}
			}

			readerCounter.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		} 

		return numLines;

	}
}
