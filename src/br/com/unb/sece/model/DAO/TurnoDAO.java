package br.com.unb.sece.model.DAO;

import java.util.List;

import org.hibernate.Session;

public class TurnoDAO extends Persistencia {

	
	public TurnoDAO(){
		super();
	}
	
	public TurnoDAO(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object findById(Class classe, Long pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> listAll(Class classe) {
		// TODO Auto-generated method stub
		List<Object> lista = (List<Object>) this.objSession.createQuery("select o from " + classe.getSimpleName() + " o").list();
		//this.session.close();
		return lista;
	}

}
