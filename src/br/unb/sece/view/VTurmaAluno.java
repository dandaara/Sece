package br.unb.sece.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;


import br.unb.sece.model.Aluno;
import br.unb.sece.model.Turma;
import br.unb.sece.model.TurmaAluno;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

import br.unb.sece.control.CPadrao;
import br.unb.sece.control.CTurma;
import br.unb.sece.control.CTurmaAluno;
import br.unb.sece.view.panelcadastropadrao.VCadSerie;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class VTurmaAluno extends JFrame implements ActionListener{

	private JPanel contentPane;
	private DefaultListModel listTurma;
	private DefaultListModel listModel;
	private JList list;
	private JList list_1;
	private JButton btnSalvar;
	private ArrayList a_turma;
	private Turma turma;
	

	/**
	 * Create the frame.
	 */
	public VTurmaAluno(final CTurma turma)  {
		
		this.turma = turma.getTurma();
		this.setTitle("Cadastro de Aluno na Turma");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 684, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton(">>");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listTurma.addElement(list.getSelectedValue());
				listModel.removeElement(list.getSelectedValue());
			}
		});
		button.setBounds(290, 184, 58, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("<<");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.addElement(list_1.getSelectedValue());
				listTurma.removeElement(list_1.getSelectedValue());
			}
		});
		button_1.setBounds(290, 256, 58, 23);
		contentPane.add(button_1);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(this);
		btnSalvar.setBounds(274, 394, 96, 33);
		contentPane.add(btnSalvar);
		
		JLabel lblTurma = new JLabel("Turma:");
		lblTurma.setBounds(10, 11, 58, 14);
		contentPane.add(lblTurma);
		
		JLabel lblNomeTurma = new JLabel(turma.getTurma().getSerie().getNome()+" / "+turma.getTurma().getNomeTurma());
		lblNomeTurma.setBounds(74, 11, 146, 14);
		contentPane.add(lblNomeTurma);
		
		
		
		listTurma = CTurmaAluno.getListAlunosDaTurma(turma.getTurma());
		
		
		
		listModel = CTurmaAluno.getListAlunos(turma.getTurma()) ;
		list = new JList(listModel);
		list.setBounds(10, 36, 265, 313);
		contentPane.add(list); 
		
		list_1 = new JList(listTurma);
		list_1.setBounds(363, 36, 265, 313);
		contentPane.add(list_1);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(this.btnSalvar)){
		   ArrayList listaAlunosMatricula = new ArrayList();  
	       for (int i=0; i < list_1.getModel().getSize(); i++){  
	    	   String textoAluno = (String)list_1.getModel().getElementAt(i);
	    	   System.out.println("O texto: "+textoAluno);
	    	   String[] textos = textoAluno.split(" ");
	           listaAlunosMatricula.add(textos[0]);  
	       }
	       ArrayList listaAlunosDesMatricula = new ArrayList();  
	       for (int i=0; i < list.getModel().getSize(); i++){  
	    	   String textoAluno = (String)list.getModel().getElementAt(i);
	    	   String[] textos = textoAluno.split(" ");
	    	   listaAlunosDesMatricula.add(textos[0]);  
	       }
	       try{
	    	   int escolhaUsuario = JOptionPane.showConfirmDialog(null, "Deseja realmente incluir os registro?","Confirma��o",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				
				if(escolhaUsuario == JOptionPane.OK_OPTION){
			       CTurmaAluno obTurmaAluno = new CTurmaAluno();
			       obTurmaAluno.cadastrarAlunoTurma(this.turma, listaAlunosMatricula);
			       obTurmaAluno.excluirAlunoTurma(this.turma, listaAlunosDesMatricula);
			       this.dispose();
				}
	       }catch(Exception ex){
	    	   ex.printStackTrace();
	    	   JOptionPane.showMessageDialog(null, "Ocorrecu o seguinte erro: " + ex.getMessage(), "Aten��o", JOptionPane.ERROR_MESSAGE);
	       }
		}    
	}

	
}
