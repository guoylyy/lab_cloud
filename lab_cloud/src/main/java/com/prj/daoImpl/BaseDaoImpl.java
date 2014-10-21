package com.prj.daoImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.prj.dao.BaseDao;

public class BaseDaoImpl implements BaseDao{
	
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	@Autowired
	CommonDao commonDao;
	
	
	static Logger logger = Logger.getLogger(BaseDaoImpl.class);
	
	final static int expire=3600;
	

	public List searchByColumn(String classname,Map<String,String> eq,Map<String,String> like,Map<String,String> less,Map<String,String> greater){
		Session session = sessionFactory.getCurrentSession();
        Criteria crit=session.createCriteria(classname);
        if(eq!=null){
        	Set<String> key = eq.keySet();
            for (Iterator it = key.iterator(); it.hasNext();) {
                String s = (String) it.next();
                crit.add( Restrictions.eq(s, eq.get(s)) );
            }
        }
        if(like!=null){
        	Set<String> key = like.keySet();
            for (Iterator it = key.iterator(); it.hasNext();) {
                String s = (String) it.next();
                crit.add( Restrictions.like(s, "%"+like.get(s)+"%") );
            }
        }
        if(less!=null){
        	Set<String> key = less.keySet();
            for (Iterator it = key.iterator(); it.hasNext();) {
                String s = (String) it.next();
                crit.add( Restrictions.lt(s, less.get(s)));
            }
        }
        if(greater!=null){
        	Set<String> key = greater.keySet();
            for (Iterator it = key.iterator(); it.hasNext();) {
                String s = (String) it.next();
                crit.add( Restrictions.gt(s, greater.get(s)) );
            }
        }
       
		return crit.list();
	}
	

	
}
