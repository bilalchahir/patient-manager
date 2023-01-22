package com.india;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Iterator;
import java.util.List;


import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;




public class Gestionn extends JFrame {
	private static SessionFactory factory;
	DefaultTableModel model=new DefaultTableModel();
	
	
	public Gestionn() {
		
		  tble = new JTable();
		  tble.setModel(new DefaultTableModel(
		            new Object [][] {
		                {null, null, null, null, null},
		                {null, null, null, null, null},
		                {null, null, null, null, null},
		                {null, null, null, null, null},
		                {null, null, null, null, null}
		                
		            },
		            new String [] {
		                "Title 1", "Title 2", "Title 3", "Title 4"
		            }
		        ));
		 try {
			 
			 //Il active le framework Hibernate
			 //L'objet de session est créé sur la base de l'objet SessionFactory
			 
	         factory = new Configuration().
	                   configure().
	                   addPackage("com.india"). //add package if used.
	                   addAnnotatedClass(Patient.class).
	                   buildSessionFactory();//L'objet de session est utilisé pour effectuer des opérations CRUD
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		  Session session = factory.openSession();
	      Transaction tx = null;
	      model.addColumn("id");
	      model.addColumn("Nom");
	      model.addColumn("Prenom");
	      model.addColumn("maladie");
	      model.addColumn("age");
	      
	     
	      
	      try {
	         tx = session.beginTransaction();//L'objet de transaction est utilisé chaque fois que nous effectuons une opération
	         List employees = session.createQuery("FROM Patient",Patient.class).list(); //d'apporter les modifications++
	         for (Iterator iterator = employees.iterator(); iterator.hasNext();){
	            Patient patient = (Patient) iterator.next(); 
	           // System.out.print("First Name: " + employee.getFirstName()); 
	           // System.out.print("  Last Name: " + employee.getLastName()); 
	            model.addRow(new Object[]{patient.getId(),patient.getFirstName(),patient.getLastName(),patient.getMaladie(),
	            	    patient.getAge()});
	            
	            
	         }
	         tble.setModel(model);
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   
		jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jButton4 = new JButton();
        jButton5 = new JButton();
        jButton6 = new JButton();
        txtre = new JTextField();
        jLabel6 = new JLabel();
        jScrollPane1 = new JScrollPane();
      
        jLabel2 = new JLabel();
        jLabel1 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        txtnot = new JTextField();
        txtbr = new JComboBox();
        txtpr = new JTextField();
        txtno = new JTextField();
        txtid = new JTextField();
        jLabel7 = new JLabel();
        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        jMenuItem1 = new JMenuItem();
        jMenuItem2 = new JMenuItem();
        jMenuItem3 = new JMenuItem();
        jMenuItem4 = new JMenuItem();
        jMenuItem5 = new JMenuItem();
        jMenu2 = new JMenu();
        jMenuItem6 = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(770, 530));
        getContentPane().setLayout(null);
       
        jButton1.setFont(new Font("Tahoma", 1, 14)); // NOI18N
         jButton1.setIcon(new ImageIcon("icone/supprimer.png")); // NOI18N
        jButton1.setText("Supprimer");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            if(JOptionPane.showConfirmDialog(null,"attention vous avez supprimer un patient,est ce que tu et sure?"
                         ,"supprimer un patient",JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
            	Session session = factory.openSession();
      	      Transaction tx = null;
      	    if(txtid.getText().length() != 0){
      	      try {
      	         tx = session.beginTransaction();
      	        String EmployeeID =txtid.getText();
      	         Patient employee = (Patient)session.get(Patient.class, Integer.parseInt(EmployeeID)); 
      	         session.delete(employee); 
      	         tx.commit();
      	      } catch (HibernateException e) {
      	         if (tx!=null) tx.rollback();
      	         e.printStackTrace(); 
      	      } finally {
      	         session.close(); 
      	         
            }}
      	    
      	   
      	    
        }else { JOptionPane.showMessageDialog(null,"veuillez SVP remplire le champ id !");}}});
        getContentPane().add(jButton1);
        jButton1.setBounds(180, 400, 143, 40);

        jButton2.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new ImageIcon("icone/ajouter.png")); // NOI18N
        jButton2.setText("Ajouter");
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	 Session session = factory.openSession();
       	      Transaction tx = null;
       	      Integer employeeID = null;
       	      
       	      try {
       	         tx = session.beginTransaction();
       	         Patient patient= new Patient();
       	        patient.setFirstName(txtpr.getText());
       	         patient.setLastName(txtno.getText());
       	         patient.setAge(Integer.parseInt(txtnot.getText()));
       	         patient.setMaladie(txtbr.getSelectedItem().toString());
       	         employeeID = (Integer) session.save(patient); 
       	         tx.commit();
       	      JOptionPane.showMessageDialog(null,"le patient est bien ajouter");
       	   txtno.setText("");
       	   txtpr.setText("");txtbr.setSelectedItem(2);txtnot.setText("");
       	      } catch (HibernateException e) {
       	    	JOptionPane.showMessageDialog(null,e.getMessage());
       	         if (tx!=null) tx.rollback();
       	         e.printStackTrace(); 
       	      } finally {
       	         session.close(); 
       	      }
             
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(40, 350, 130, 40);

        jButton3.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new ImageIcon("icone/rechercher.png")); // NOI18N
        jButton3.setText("recherche ");
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	DefaultTableModel model=new DefaultTableModel();
            	tble.setModel(new DefaultTableModel(
    		            new Object [][] {
    		                {null, null, null, null, null},
    		                {null, null, null, null, null},
    		                {null, null, null, null, null},
    		                {null, null, null, null, null},
    		                {null, null, null, null, null}
    		                
    		            },
    		            new String [] {
    		                "Title 1", "Title 2", "Title 3", "Title 4"
    		            }
    		        ));
            	  model.addColumn("id");
        	      model.addColumn("Nom");
        	      model.addColumn("Prenom");
        	      model.addColumn("maladie");
        	      model.addColumn("age");
            	 Session session = factory.openSession();
       	      Transaction tx = null;
       	   tx = session.beginTransaction();
       	   String id=txtre.getText();
	         List employees = session.createQuery("FROM Patient P WHERE P.id="+id,Patient.class).list(); 
	         for (Iterator iterator = employees.iterator(); iterator.hasNext();){
	            Patient patient = (Patient) iterator.next(); 
	           // System.out.print("First Name: " + employee.getFirstName()); 
	           // System.out.print("  Last Name: " + employee.getLastName()); 
	            model.addRow(new Object[]{patient.getId(),patient.getFirstName(),patient.getLastName(),patient.getMaladie(),
	            	    patient.getAge()});
	            
	            
	         }
	         tble.setModel(model);
               
            
                
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(380, 380, 150, 40);

        jButton4.setFont(new Font("Tahoma", 1, 14)); // NOI18N
       jButton4.setIcon(new ImageIcon("icone/modifier.png")); // NOI18N
        jButton4.setText("modifier");
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	 Session session = factory.openSession();
       	      Transaction tx = null;
       	      
       	      try {
       	    	 if (JOptionPane.showConfirmDialog (null,"confirmer la modification","modification",
                         JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
       	         tx = session.beginTransaction();
       	         String EmployeeID=txtid.getText();
       	         Patient patient = (Patient)session.get(Patient.class, Integer.parseInt(EmployeeID)); 
       	               patient.setFirstName(txtpr.getText());
       	               patient.setLastName(txtno.getText());
       	            patient.setMaladie(txtbr.getSelectedItem().toString());
    	               patient.setAge(Integer.parseInt(txtnot.getText()));
       	               
       			 session.update(patient); 
       	         tx.commit();}
       	      } catch (HibernateException e) {
       	         if (tx!=null) tx.rollback();
       	         e.printStackTrace(); 
       	      }
       	   catch (Exception e){JOptionPane.showMessageDialog(null,"erreur de modifier !!!!!!!"+e.getMessage());
           System.err.println(e);}finally {
       	         session.close(); 
       	      }
                
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(180, 350, 140, 40);

        jButton5.setFont(new Font("Tahoma", 1, 14)); // NOI18N
       jButton5.setIcon(new ImageIcon("icone/actualiser.png")); // NOI18N
        jButton5.setText("actualiser");
        jButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	DefaultTableModel model=new DefaultTableModel();
            	tble.setModel(new DefaultTableModel(
    		            new Object [][] {
    		                {null, null, null, null, null},
    		                {null, null, null, null, null},
    		                {null, null, null, null, null},
    		                {null, null, null, null, null},
    		                {null, null, null, null, null}
    		                
    		            },
    		            new String [] {
    		                "Title 1", "Title 2", "Title 3", "Title 4"
    		            }
    		        ));
            	  model.addColumn("id");
        	      model.addColumn("Nom");
        	      model.addColumn("Prenom");
        	      model.addColumn("maladie");
        	      model.addColumn("age");
            	 Session session = factory.openSession();
       	      Transaction tx = null;
       	   tx = session.beginTransaction();
	         List employees = session.createQuery("FROM Patient",Patient.class).list(); 
	         for (Iterator iterator = employees.iterator(); iterator.hasNext();){
	            Patient patient = (Patient) iterator.next(); 
	           // System.out.print("First Name: " + employee.getFirstName()); 
	           // System.out.print("  Last Name: " + employee.getLastName()); 
	            model.addRow(new Object[]{patient.getId(),patient.getFirstName(),patient.getLastName(),patient.getMaladie(),
	            	    patient.getAge()});
	            
	            
	         }
	         tble.setModel(model);
               
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(40, 400, 130, 40);

        jButton6.setFont(new Font("Tahoma", 1, 14)); // NOI18N
       jButton6.setIcon(new ImageIcon("icone/nouveau.png")); // NOI18N
        jButton6.setText("réaliser par");
        jButton6.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(90, 450, 160, 40);

        txtre.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        txtre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               
            }
        });
        txtre.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
            }
        });
        getContentPane().add(txtre);
        txtre.setBounds(560, 380, 400, 30);

        jLabel6.setFont(new Font("Times New Roman", 1, 48)); // NOI18N
        jLabel6.setText("gestion des patients");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(160, 30, 2400, 100);

       
        tble.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                
            }
        });
        jScrollPane1.setViewportView(tble);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(340, 140, 374, 90);

        jLabel2.setFont(new Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("id :");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(40, 110, 42, 17);

        jLabel1.setFont(new Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Nom :");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 150, 42, 17);

        jLabel3.setFont(new Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Prenom:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 190, 53, 17);

        jLabel4.setFont(new Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("maladie :");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(40, 240, 60, 17);

        jLabel5.setFont(new Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("age  :");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(50, 290, 40, 17);

        txtnot.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        txtnot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               
            }
        });
        getContentPane().add(txtnot);
        txtnot.setBounds(170, 290, 100, 23);

        txtbr.setModel(new DefaultComboBoxModel(new String[] { "Diabète", "Rhumatisme", "Cardiaque" }));
        getContentPane().add(txtbr);
        txtbr.setBounds(170, 240, 100, 22);

        txtpr.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtpr);
        txtpr.setBounds(170, 190, 100, 23);

        txtno.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtno);
        txtno.setBounds(170, 150, 100, 23);

        txtid.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        txtid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               
            }
        });
        getContentPane().add(txtid);
        txtid.setBounds(170, 110, 100, 23);

        jLabel7.setIcon(new ImageIcon("icone/hopital1.jpg")); // NOI18N
        getContentPane().add(jLabel7);
        jLabel7.setBounds(0, 0, 760, 500);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        jMenuItem1.setText("ajouter");
        jMenuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.ALT_MASK));
        jMenuItem2.setText("modifier");
        jMenuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        jMenuItem3.setText("supprimer");
        jMenuItem3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        jMenuItem4.setText("actualiser");
        jMenuItem4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        jMenuItem5.setText("rechercher");
        jMenuItem5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               
            }
        });
        tble.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
            	try{
            		int i=tble.getSelectedRow();deplace(i);
            		}catch(Exception e){JOptionPane.showMessageDialog(null,"erreur de deplacement "+e.getLocalizedMessage());}
            		    
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        jMenuItem6.setText("realiser par");
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
        setVisible(true);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JButton jButton6;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private  JLabel jLabel7;
    private  JMenu jMenu1;
    private  JMenu jMenu2;
    private  JMenuBar jMenuBar1;
    private  JMenuItem jMenuItem1;
    private  JMenuItem jMenuItem2;
    private  JMenuItem jMenuItem3;
    private  JMenuItem jMenuItem4;
    private JMenuItem jMenuItem5;
    private  JMenuItem jMenuItem6;
    private  JScrollPane jScrollPane1;
    private  JTable tble;
    private  JComboBox txtbr;
    private  JTextField txtid;
    private  JTextField txtno;
    private  JTextField txtnot;
    private  JTextField txtpr;
    private  JTextField txtre;
    // End of variables declaration//GEN-END:variables
	
    private void deplace(int i){
    	try {
    	txtid.setText(model.getValueAt(i,0).toString());
    	txtno.setText(model.getValueAt(i,1).toString());
    	txtpr.setText(model.getValueAt(i,2).toString());
    	txtbr.setSelectedItem(model.getValueAt(i,3).toString());
    	txtnot.setText(model.getValueAt(i,4).toString());
    	}catch (Exception e){System.err.println(e);
    	JOptionPane.showMessageDialog(null,"erreur de deplacement"+e.getLocalizedMessage());}
    	}

	}


