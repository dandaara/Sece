package br.unb.sece.model.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.unb.sece.model.Disciplina;

public class DisciplinaDAO extends Persistencia {

	public DisciplinaDAO(){
		super();
	}
	
	public DisciplinaDAO(Session session){
		//this.objSession = session;
	}

	public List getAll(){
		Query query = this.objSession.createQuery("from Disciplina");
		List list = query.list();
		return list;
	}
	

	@Override
	public Disciplina findById(Class classe, Long pk) {
		// TODO Auto-generated method stub
		return (Disciplina) this.objSession.load(classe, pk);
	}

	@Override
	public List<Object> listAll(Class classe) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	

	

	

	

	
	

}