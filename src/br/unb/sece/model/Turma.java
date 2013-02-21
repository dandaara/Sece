package br.unb.sece.model;

import br.unb.sece.exceptions.AtributoNuloException;
import br.unb.sece.model.DAO.TurmaDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.annotations.*; //Especificar o que est� sendo importado

@Entity
public class Turma {
	@Id
	@GeneratedValue
	private Long idTurma;
	
	@Column(name="nomeTurma")
	private String nomeTurma;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="idSerie", insertable=true, updatable = true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Serie serie;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="idTurno", insertable=true, updatable = true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.LOCK)
	private Turno turno;
	
	@Transient
	private Collection<Aluno> aluno = new ArrayList<Aluno>();
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="turmadisciplina", schema="sece", joinColumns={@JoinColumn(name="idTurma")},	inverseJoinColumns={@JoinColumn(name="idDisciplina")})
	@Cascade(CascadeType.LOCK)
	private Collection<Disciplina> disciplinasTurma = new ArrayList<Disciplina>();
	
	
	
	
	public Long getIdTurma() {
		return this.idTurma;
	}

	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}

	public Collection<Aluno> getAluno() {
		if((this.aluno.size() == 0 || this.aluno == null) && this.idTurma != null){
			this.aluno = AlunoDisciplina.getAlunosDaTurma(this);
		}
		return this.aluno;
	}
	
	public ArrayList<Aluno> getAlunos(){
		return (ArrayList) this.aluno;
	}

	public void setAluno(Collection<Aluno> aluno) {
		this.aluno = aluno;
	}

	public String getNomeTurma() {
		return this.nomeTurma;
	}

	public void setNomeTurma(String nomeTurma) throws AtributoNuloException {
		if(nomeTurma.isEmpty()){
			throw new AtributoNuloException("Nome Turma");
		}else{
			this.nomeTurma = nomeTurma;
		}
	}

	public Serie getSerie() {
		return this.serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}
	
	public Turno getTurno() {
		return this.turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	
	public static List getAll(){
		final TurmaDAO dao = new TurmaDAO();
		final List<Object> lista = dao.listAll(Turma.class);
		
		return lista;
	}
	
	public void salvar() {
		final TurmaDAO dao = new TurmaDAO();
		
		dao.save(this);
	}
	
	public void salvar(Session session) {
		final TurmaDAO dao = new TurmaDAO(session);
		
		dao.save((Turma) this, session);
		
	}
	
	public void alterar() {
		final TurmaDAO dao = new TurmaDAO();
		
		dao.update(this);
	}
	
	static public Turma recuperarTuma(Long id){
		final TurmaDAO dao = new TurmaDAO();
		Turma turma = dao.findById(Turma.class, id);
		return turma;
	}
	
		

}
