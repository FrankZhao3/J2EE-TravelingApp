package com.wwfly.db.service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wwfly.cache.RedisManager;
import com.wwfly.db.tables.City;
import com.wwfly.db.tables.Login;
import com.wwfly.db.tables.PhotoSharing;
import com.wwfly.db.tables.School;
import com.wwfly.db.tables.TravelCost;
import com.wwfly.db.tables.TravelRoutine;
import com.wwfly.db.tables.User;
import com.wwfly.db.tables.UserRoutine;
import com.wwfly.db.tables.UserRoutineId;
import com.wwfly.db.tables.ViewRoutine;
import com.wwfly.db.tables.ViewRoutineId;
import com.wwfly.db.tables.ViewSpot;
import com.wwfly.service.result.Log;
import com.wwfly.service.result.Pair;
import com.wwfly.sessionfactory.HibernateSessionFactory;

public class ClientDataImplement implements IClientData{
	/*
	 *  ======================Login========================
	*/
	public Pair<Log, Boolean> Userlogin(String username, String password) {
		Log msg = new Log();
		try {
			Session session = HibernateSessionFactory.getSession();
			try{
				Login log =  (Login) session.get(Login.class, username);
				if(log != null){
					String psword = log.getPassword();
					if(password.equals(psword)){
						msg.setMessage("success");
						return new Pair<Log, Boolean>(msg, true);
					}else {
						msg.setMessage("failed(invalid password)");
						return new Pair<Log, Boolean>(msg, false);
					}
				}else{
					msg.setMessage("failed");
					return new Pair<Log, Boolean>(msg, false);
				}
			} catch(Exception e){
				System.out.println(e);
				msg.setErrorMsg(e.toString());
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			msg.setErrorMsg(e.toString());
		} 
		return new Pair<Log, Boolean>(msg, false);
	}
	
	public Log UserRegister(User user) {
		Log msg = new Log();
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tras = session.beginTransaction();
			try{
				School school = user.getSchool();
				if(emailExist(session, user.getUserEmail())) {
					msg.setErrorMsg("Duplicated email: " + user.getUserEmail());
					return msg;
				}
				if(school != null){
					School existedSchool = getSchool(school, session);
					if(existedSchool == null){
						//System.out.println("====start====");
						session.save(user.getSchool());
						//tras.commit();
						existedSchool = getSchool(school, session);
						//System.out.println("====end====");
					}
					user.setUserSchoolId(existedSchool.getSchoolId());
				} 
//				Set<UserRoutine> list_rout = user.getRoutineList(); 
//				if(list_rout!=null){
//					for(UserRoutine ur: list_rout) {
//						session.save(ur);
//					}
//				}
				user.setSchool(null);
				session.save(user);
				tras.commit();
				msg.setMessage("register success");
				return msg;
				
			} catch(Exception e){
				System.out.println(e);
				msg.setErrorMsg(e.toString());
				tras.rollback();
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			msg.setErrorMsg(e.toString());
		}
		msg.setErrorMsg("error occurs");
		return msg;
	}

	public School getSchoolBasedOnName(String name) {
		try {
			Session session = HibernateSessionFactory.getSession();
			try{
				return getSchool(name, session);
			} catch(Exception e){
				System.out.println(e);
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Log AcctRegister(String username, String password) {
		Log msg = new Log();
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tras = session.beginTransaction();
			try{	
				if(username.length() > 45 || password.length() > 20) {
					msg.setErrorMsg("length error");
					return msg;
				} else if(userExists(session, username)){
						msg.setErrorMsg("user exists!");
						return msg;
				} else{
					Login log = new Login(username, password);
					session.save(log);
					tras.commit();
					msg.setMessage("Your account has been added.");
				}
			} catch(Exception e){
				System.out.println(e);
				msg.setErrorMsg(e.toString());
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			msg.setErrorMsg(e.toString());
		} 
		return msg; 
	}
	
	@Override
	public User getUserBasedOnId(Integer id, Log msg) {
		try {	
			Session session = HibernateSessionFactory.getSession();
			Transaction tras = session.beginTransaction();
			try{
				User user = (User)session.get(User.class, id);
				if(user == null){
					msg.setErrorMsg("No such user.");
					return null;
				}else {
					msg.setMessage("Success");
					return user;
				}
			} catch(Exception e){
				System.out.println(e);
				tras.rollback();
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		msg.setErrorMsg("getInfoBasedOnId " +  "Error occurs");
		return null;
	}

	@Override
	public List<User> getUsersBasedOnName(String name, Log msg) {
		try {	
			Session session = HibernateSessionFactory.getSession();
			Transaction tras = session.beginTransaction();
			try{
				String hql = "from User where name = :name";
				Query query = session.createQuery(hql);
				query.setParameter("name", name);
				@SuppressWarnings("unchecked")
				List<User> list = query.list();
				if(list.size() < 1) {
					msg.setErrorMsg("No such user!");
					return list;
				}
				msg.setMessage("Success");
				return list;
			} catch(Exception e){
				System.out.println(e);
				tras.rollback();
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		msg.setErrorMsg("getInfoBaseOnName " +  "Error occurs");
		return null;
	}

	@Override
	public List<User> getUsersBasedOnEmail(String email, Log msg) {
		try {	
			Session session = HibernateSessionFactory.getSession();
			Transaction tras = session.beginTransaction();
			try{
				String hql = "from User where user_email = :name";
				Query query = session.createQuery(hql);
				query.setParameter("name", email);
				@SuppressWarnings("unchecked")
				List<User> list = query.list();
				if(list.size() < 1) {
					msg.setErrorMsg("No such user!");
					return list;
				}
				msg.setMessage("Success");
				return list;
			} catch(Exception e){
				System.out.println(e);
				tras.rollback();
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		msg.setErrorMsg("getInfoBaseOnName " +  "Error occurs");
		return null;
	}
	@Override
	public Log deleteUserBasedOnId(Integer id) {
		Log msg = new Log();
		try {	
			Session session = HibernateSessionFactory.getSession();
			Transaction tras = session.beginTransaction();
			try{
				User user = (User) session.get(User.class, id);
				if(user == null) {
					msg.setErrorMsg("No such user in database.");
					return msg;
				}
				System.out.println(user.toString());
				if(user.getRoutineList() != null && user.getRoutineList().size() > 0) {
//					System.out.println("start1");
					for(UserRoutine rout : user.getRoutineList()){
						session.delete(rout);
					}
					session.delete(user);
				} else {
//					System.out.println("start2");
					user.setRoutineList(null);
					session.delete(user);
				}
				tras.commit();
				msg.setErrorMsg("delete succeed");
				return msg;
			} catch(Exception e){
				System.out.println(e);
				tras.rollback();
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		msg.setErrorMsg("deleteUserBasedOnId " +  "Error occurs");
		return  msg;
	}

	@Override
	public Log deleteUserBasedOnName(String name) {
		Log msg = new Log();
		try {	
			Session session = HibernateSessionFactory.getSession();
			Transaction tras = session.beginTransaction();
			try{
				String hql = "from User where name = :name";
				Query query = session.createQuery(hql);
				query.setParameter("name", name);
				@SuppressWarnings("unchecked")
				List<User> list = query.list();
				if(list.size() < 1) {
					msg.setErrorMsg("No such user");
					return msg;
				}
				for(User user : list) {
					session.delete(user);
				}
				tras.commit();
				msg.setMessage("delete succeed");
				return msg;
			} catch(Exception e){
				System.out.println(e);
				tras.rollback();
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		msg.setErrorMsg("deleteUserBasedOnName " +  "Error occurs");
		return msg;
	}

	@Override
	public Log updateUserInfo(User user) {
		Log msg = new Log();
		try {	
			Session session = HibernateSessionFactory.getSession();
			Transaction tras = session.beginTransaction();
			try{
				session.update(user);
				tras.commit();
				msg.setMessage("Update succeed");
				return msg;
			} catch(Exception e){
				System.out.println(e);
				tras.rollback();
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		msg.setErrorMsg("editUser " +  "Error occurs");
		return msg;
	}
	
	@Override
	public Set<User> getUserListBasedOnSchoolId(Integer school_id, Log msg) {
		try {	
			Session session = HibernateSessionFactory.getSession();
			Transaction tras = session.beginTransaction();
			try{
				School school = (School) session.get(School.class, school_id);
				if(school == null) {
					msg.setErrorMsg("No such school!");
					return null;
				}
				msg.setMessage("get user list success.");
				return (Set<User>) school.getListOfStudents();
			} catch(Exception e){
				System.out.println(e);
				msg.setErrorMsg(e.toString());
				tras.rollback();
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		msg.setErrorMsg("getStudentInfoBasedOnSchoolId " +  "Error occurs");
		return null;
	}
	
	public User getUserBasedOnEmail(String userEmail) {
		try {
			Session session = HibernateSessionFactory.getSession();
			try{	
				String hql = "from User where user_email = :email";
				Query query = session.createQuery(hql);
				query.setParameter("email", userEmail);
				@SuppressWarnings("unchecked")
				List<User> list = query.list();
				if(list.size() < 1) {
					return null;
				} else {
					return list.get(0);
				}
			} catch(Exception e){
				System.out.println(e);
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return null;
	}
	/*
	 *  ======================Travel========================
	 */
	public Log saveATravelRoutine(TravelRoutine travelRoutine) {
		Log msg = new Log();
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tras = session.beginTransaction();
			try{	
				if(TravelRoutineExists(session, travelRoutine.getName())) {
					msg.setErrorMsg("This travelRoutine is already existed");
					return msg;
				}
				session.save(travelRoutine);
				msg.setMessage("Save travelRoutine success");
				tras.commit();
				return msg;
			} catch(Exception e){
				System.out.println(e);
				msg.setErrorMsg(e.toString());
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			msg.setErrorMsg(e.toString());
		} 
		msg.setErrorMsg("error occurs");
		return msg; 
	}
	
	public TravelRoutine getRoutineBasedOnRoutineName(String routineName, Log msg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			try{	
				String hql = "from TravelRoutine where name = :name";
				Query query = session.createQuery(hql);
				query.setParameter("name", routineName);
				@SuppressWarnings("unchecked")
				List<TravelRoutine> list = query.list();
				if(list.size() < 1) {
					msg.setErrorMsg("No such routine");
					return null;
				}
				msg.setMessage("success");
				return list.get(0);
			} catch(Exception e){
				System.out.println(e);
				msg.appendErrorMsg(e.toString());
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			msg.appendErrorMsg(e.toString());
		} 
		msg.appendErrorMsg("error occurs");
		return null; 
	}
	
	public void addTravelRoutineToAUser(User user,  TravelRoutine rout, Log msg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tras = session.beginTransaction();
			try{	
				UserRoutineId id = new UserRoutineId(user.getUserId(), rout.getTravelId());
				if(session.get(UserRoutine.class, id) != null) {
					msg.appendMsg("User has registered this routine.");
					return;
				}
				if(rout.getAvailability() == "N") {
					msg.appendErrorMsg("This trip is not available");
					return;
				}
				rout.setNumberOfRegistrations(rout.getNumberOfRegistrations() + 1);
				if(rout.getNumberOfRegistrations().equals(rout.getMaxRegistration())) {
					rout.setAvailability("N");
				}
				UserRoutine userRout = new UserRoutine(id, new Date());
				userRout.setRoutine(rout);
				userRout.setUser(user);
				session.update(rout);
				session.save(userRout);
				tras.commit();
				msg.appendMsg("addTravelRoutineToAUser success");
				return;
			} catch(Exception e){
				System.out.println(e);
				msg.appendErrorMsg(e.toString());
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			msg.appendErrorMsg(e.toString());
		} 
		msg.appendErrorMsg("addTravelRoutineToAUser error occurs");
	}
	
	public List<TravelRoutine> getRecommandRoutine(String destination, String startingPlace, Double max_cost,
			Date startDate, Date endDate, Log msg){
		try {
			Session session = HibernateSessionFactory.getSession();
			try{	
				String hql;
				Query query;
				if(destination == null && startingPlace == null && max_cost == null && startDate == null && endDate == null ) {
					hql = "from TravelRoutine order by cost";
					query = session.createQuery(hql);
				}
				else { 	
						hql = "from TravelRoutine where";
						if(destination != null) {
							hql += " destination = :destination";
						}
						if(startingPlace != null) {
							hql += " and startingPlace = :startingPlace";
						}
						if(max_cost != null) {
							hql += " and cost <= :max_cost";
						}
						if(startDate != null) {
							hql += " and startDate = :startDate";
						}
						if(endDate != null) {
							hql += " and endDate = :endDate";
						}
						hql += " order by cost";
						query = session.createQuery(hql);
						if(destination != null) { 
							query.setParameter("destination", destination);
						}
						if(startingPlace != null) {
							query.setParameter("startingPlace", startingPlace);
						}
						if(max_cost != null) {
							query.setParameter("max_cost", max_cost);
						}
						if(startDate != null) {
							query.setParameter("startDate", startDate);
						}
						if(endDate != null) {
							query.setParameter("endDate", endDate);
						}
				}
				@SuppressWarnings("unchecked")
				List<TravelRoutine> list = query.list();
				if(list.size() < 1) {
					msg.appendMsg("No such routine");
					return list;
				}
				msg.appendMsg("getRecommandRoutine success");
				return list;
			} catch(Exception e){
				System.out.println(e);
				msg.appendErrorMsg(e.toString());
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			msg.appendErrorMsg(e.toString());
		} 
		msg.appendErrorMsg("getRecommandRoutine error occurs");
		return null;
	}

	public Log addViewSpotToARoutine(String routineName, ViewSpot viewSpot) {
		Log msg = new Log();
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tras = session.beginTransaction();
			try{	
				TravelRoutine rout = getTravelRoutineByName(session, routineName);
				ViewRoutine vRout;
				if(rout == null) {
					msg.setErrorMsg("No such routine");
					return msg;
				}
				ViewSpot data = getViewSpotByName(session, viewSpot.getSpotName());
				if(data != null) {
					msg.setErrorMsg("This view spot is already existed");
					return msg;
				}
				session.save(viewSpot);
				ViewRoutineId id = new ViewRoutineId();
				id.setRoutineId(rout.getTravelId());
				ViewSpot data2 = getViewSpotByName(session, viewSpot.getSpotName());
				id.setViewId(data2.getSpotId());
				vRout = new ViewRoutine(id);
				vRout.setTravelRoutine(rout);
				vRout.setViewSpot(viewSpot);
				session.save(vRout);
				tras.commit();
				msg.setMessage("addViewSpotToARoutine success");
				return msg;
			} catch(Exception e){
				System.out.println(e);
				msg.setErrorMsg(e.toString());
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			msg.setErrorMsg(e.toString());
		} 
		msg.setErrorMsg("error occurs");
		return msg; 	
	}
	
	public City getCityByName(String cityName) {
		try {
			Session session = HibernateSessionFactory.getSession();
			try{	
				String hql = "from City where name = :name";
				Query query = session.createQuery(hql);
				query.setParameter("name", cityName);
				@SuppressWarnings("unchecked")
				List<City> list = query.list();
				if(list.size() < 1) {
					return null;
				}
				return list.get(0);
			} catch(Exception e){
				System.out.println(e);
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return null; 
	}
	//check
	public ViewSpot getViewSpotByName(String name) {
		try {
			Session session = HibernateSessionFactory.getSession();
			try{	
				String hql = "from ViewSpot where spotName = :name";
				Query query = session.createQuery(hql);
				query.setParameter("name", name);
				@SuppressWarnings("unchecked")
				List<ViewSpot> list = query.list();
				if(list.size() < 1) {
					return null;
				}
				return list.get(0);
			} catch(Exception e){
				System.out.println(e);
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return null; 
	}
	@Override
	public Set<ViewRoutine> getTravelRoutineBasedOnViewSpot(String viewSpotName, Log msg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			try{	
				String hql = "from ViewSpot where spotName = :name";
				Query query = session.createQuery(hql);
				query.setParameter("name", viewSpotName);
				@SuppressWarnings("unchecked")
				List<ViewSpot> list = query.list();
				if(list.size() < 1) {
					msg.appendErrorMsg("No such viewSpot");
					return null;
				}
				ViewSpot tRout = list.get(0);
				msg.appendMsg("getRoutine Success");
				return tRout.getRoutine_list();
			} catch(Exception e){
				System.out.println(e);
				msg.appendErrorMsg(e.toString());
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			msg.appendErrorMsg(e.toString());
		} 
		msg.appendErrorMsg("error occurs");
		return null;

	}
	@Override
	public Set<UserRoutine> getUserFromRoutine(String routineName, Log msg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			try{	
				String hql = "from TravelRoutine where name = :name";
				Query query = session.createQuery(hql);
				query.setParameter("name", routineName);
				@SuppressWarnings("unchecked")
				List<TravelRoutine> list = query.list();
				if(list.size() < 1) {
					msg.appendErrorMsg("No such routine");
					return null;
				}
				TravelRoutine tRout = list.get(0);
				return tRout.getUserList();
			} catch(Exception e){
				System.out.println(e);
				msg.appendErrorMsg(e.toString());
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			msg.appendErrorMsg(e.toString());
		} 
		msg.appendErrorMsg("error occurs");
		return null;
	}
	
	@Override
	public List<TravelCost> initRedis() {
		try {
			Session session = HibernateSessionFactory.getSession();
			try{	
				String hql = "from TravelCost travel";
				Query query = session.createQuery(hql);
				@SuppressWarnings("unchecked")
				List<TravelCost> list = query.list();
				if(list.size() < 1) {
					return null;
				}
				return list;
			} catch(Exception e){
				System.out.println(e);
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	@Override
	public Double getTravelCost(String startPlace,  String endPlace, Log msg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			try{	
				TravelCost tc = RedisManager.getInstance().getTravelCost(startPlace, endPlace);
				if(tc == null) {
					String hql = "from TravelCost where startPlace = :start and endPlace = :end";
					Query query = session.createQuery(hql);
					query.setParameter("start", startPlace);
					query.setParameter("end", endPlace);
					@SuppressWarnings("unchecked")
					List<TravelCost> list = query.list();
					if(list.size() < 1) {
						msg.appendErrorMsg("No such TravelCost");
						return null;
					}
					TravelCost tcost = list.get(0);
					msg.appendMsg("MySql access Data Success");
					return tcost.getCost();
				} else {
					msg.appendMsg("Cache access Data Success");
					return tc.getCost();
				}
			} catch(Exception e){
				System.out.println(e);
				msg.appendErrorMsg(e.toString());
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			msg.appendErrorMsg(e.toString());
		} 
		msg.appendErrorMsg("error occurs");
		return null;
	}
	
	@Override
	public List<PhotoSharing> getPhotosFromUser(String userEmail, Log msg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			try{	
				User user = getUser(session, userEmail);
				if(user == null) {
					msg.appendErrorMsg("No such user!");
					return null;
				} else {
					Integer id = user.getUserId();
					List<PhotoSharing> list = getPhotoListByUserId(session, id);
					msg.appendMsg("getPhotosFromUser success!");
					return list;
				}
			} catch(Exception e){
				System.out.println(e);
				msg.appendErrorMsg(e.toString());
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			msg.appendErrorMsg(e.toString());
		} 
		msg.appendErrorMsg("error occurs");
		return null;
	}
	
	public List<PhotoSharing> getPhotosFromTravelRoutine(String name, Log msg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			try{	
				TravelRoutine tr = getTravelRoutineByName(session, name);
				if(tr == null) {
					msg.appendErrorMsg("No such travelRoutine!");
					return null;
				} else {
					Integer id = tr.getTravelId();
					List<PhotoSharing> list = getPhotoListByTravelId(session, id);
					msg.appendMsg("getPhotosFromUser success!");
					return list;
				}
			} catch(Exception e){
				System.out.println(e);
				msg.appendErrorMsg(e.toString());
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			msg.appendErrorMsg(e.toString());
		} 
		msg.appendErrorMsg("error occurs");
		return null;
	}
	
	public List<TravelRoutine> getRecentTravelRoutine(Log msg) throws Exception{
		try {
			Session session = HibernateSessionFactory.getSession();
			try{	
				String hql = "from TravelRoutine as travel order by endDate";
				Query query = session.createQuery(hql);
				List<?> list = query.list();
				List<TravelRoutine> tr = new ArrayList<TravelRoutine>();
				if(list == null) {
					return tr; 
				}
				
				for(Object obj : list) {
					TravelRoutine t = (TravelRoutine) obj;
					tr.add(t);
				}
				
				return tr;
			} catch(Exception e){
				System.out.println(e);
				msg.appendErrorMsg(e.toString());
				throw e;
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			msg.appendErrorMsg(e.toString());
		} 
		msg.appendErrorMsg("getRecentTravelRoutine error occurs");
		return null;
	}
	@Override
	public List<TravelRoutine> getTRListBasedOnEmailName(String emailName) throws Exception {
		try {
			Session session = HibernateSessionFactory.getSession();
			try{	
				String hql = "from User user where userEmail = :emailName";
				Query query1 = session.createQuery(hql);
				query1.setParameter("emailName", emailName);
				List<?> list = query1.list();
				User user = (User)list.get(0);
				Integer id = user.getUserId();
				System.out.println(id);
				String hql2 = "select distinct travel.name, travel.startDate, travel.endDate, "
						+ "travel.cost, travel.numberOfRegistrations, travel.availability, "
						+ "travel.startingPlace, travel.destination"
						+ " from TravelRoutine as travel, UserRoutine as ur, User as user where"
						+ " ur.id.userId = :userId and travel.travelId = ur.id.routineId";
				Query query2 = session.createQuery(hql2);
				query2.setParameter("userId", id);
				List<?> list2 = query2.list();
				List<TravelRoutine> trList = new ArrayList<TravelRoutine>();
				if(list2 == null) {
					return trList;
				}
				@SuppressWarnings("unchecked")
				Iterator<Object[][]> iter = (Iterator<Object[][]>) list2.iterator();
				while(iter.hasNext()) {
					TravelRoutine tr = new TravelRoutine();
					Object[] row = (Object []) iter.next();
					tr.setName((String)row[0]);
					tr.setStartDate((Date)row[1]);
					tr.setEndDate((Date)row[2]);
					tr.setCost((Double)row[3]);
					tr.setNumberOfRegistrations((Integer)row[4]);
					tr.setAvailability((String)row[5]);
					tr.setStartingPlace((String)row[6]);
					tr.setDestination((String)row[7]);
					trList.add(tr);
				}
				return trList;
			} catch(Exception e){
				e.printStackTrace();
				throw e;
			}finally{
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public Log deleteATravelRoutineFromAUser(String trName, String userEmail) throws Exception{
		Log msg = new Log();
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tras = session.beginTransaction();
			try{	
				User user = getUser(session, userEmail);
				Integer userId = user.getUserId();
				Integer routineId = getTravelRoutineByName(session, trName).getTravelId();
				Query query = session.createQuery("delete UserRoutine ur where ur.id.userId= :userId and ur.id.routineId= :routineId");
				query.setParameter("routineId", routineId);
				query.setParameter("userId", userId);
				query.executeUpdate();
				tras.commit();
				msg.setMessage("success!");
				return msg;
			} catch(Exception e){
				tras.rollback();
				System.out.println(e);
				msg.setErrorMsg(e.toString());
				throw e;
			}finally{
				if(session.isOpen()) {
					session.close();
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			msg.setErrorMsg(e.toString());
			throw e;
		} 
	}
	//=========================HELPING METHOD==========================
	private School getSchool(School school, Session session) {
		String hql = "from School where name = :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", school.getName());
		@SuppressWarnings("unchecked")
		List<School> list = query.list();
		System.out.println(school.getName() + " " +list.size());
		if(list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	private School getSchool(String name, Session session) {
		String hql = "from School where name = :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		@SuppressWarnings("unchecked")
		List<School> list = query.list();
		if(list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
		
	}
	
	private boolean userExists(Session session, String username){
		Login log =  (Login) session.get(Login.class, username);
		if(log != null){
			return true;
		} else {
			return false;
		}
	}
	
	private boolean emailExist(Session session, String userEmail) {
		String hql = "from User where user_email = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", userEmail);
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
		if(list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private User getUser(Session session, String userEmail) {
		String hql = "from User where user_email = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", userEmail);
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
		if(list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	private boolean TravelRoutineExists(Session session, String name){
		String hql = "from TravelRoutine where name = :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
		if(list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private TravelRoutine getTravelRoutineByName(Session session, String name) {
		String hql = "from TravelRoutine where name = :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		@SuppressWarnings("unchecked")
		List<TravelRoutine> list = query.list();
		return list.get(0);
	}
	private ViewSpot getViewSpotByName(Session session, String name) {
		String hql = "from ViewSpot where spotName = :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		@SuppressWarnings("unchecked")
		List<ViewSpot> list = query.list();
		if(list.size() < 1) {
			return null;
		}
		return list.get(0);
	}
	
	private List<PhotoSharing> getPhotoListByUserId(Session session, Integer userId) {
		String hql = "from PhotoSharing where userId = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", userId);
		@SuppressWarnings("unchecked")
		List<PhotoSharing> list = query.list();
		if(list.size() < 1) {
			return null;
		}
		return list;
	}
	
	private List<PhotoSharing> getPhotoListByTravelId(Session session, Integer TravelId) {
		String hql = "from PhotoSharing where travelRoutineId = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", TravelId);
		@SuppressWarnings("unchecked")
		List<PhotoSharing> list = query.list();
		if(list.size() < 1) {
			return null;
		}
		return list;
	}
	
	
	/*
	 *  This is a TEST!!!
	 */
	public static void main(String[] args) throws MalformedURLException {
//		try {
//			Session session = HibernateSessionFactory.getSession();
//			try{	
//				String hql = "from User user where userEmail = :emailName";
//				Query query1 = session.createQuery(hql);
//				query1.setParameter("emailName", "chloe@gmail.com");
//				List<?> list = query1.list();
//				User user = (User)list.get(0);
//				Integer id = user.getUserId();
//				System.out.println(id);
//				String hql2 = "select distinct travel.name, travel.startDate, travel.endDate, "
//						+ "travel.cost, travel.numberOfRegistrations, travel.availability, "
//						+ "travel.startingPlace, travel.destination"
//						+ " from TravelRoutine as travel, UserRoutine as ur, User as user where"
//						+ " ur.id.userId = :userId and travel.travelId = ur.id.routineId";
//				Query query2 = session.createQuery(hql2);
//				query2.setParameter("userId", id);
//				List<?> list2 = query2.list();
//				List<TravelRoutine> trList = new ArrayList<TravelRoutine>();
//				if(list2 == null) {
//					return;
//				}
//				@SuppressWarnings("unchecked")
//				Iterator<Object[][]> iter = (Iterator<Object[][]>) list2.iterator();
//				while(iter.hasNext()) {
//					TravelRoutine tr = new TravelRoutine();
//					Object[] row = (Object []) iter.next();
//					tr.setName((String)row[0]);
//					tr.setStartDate((Date)row[1]);
//					tr.setEndDate((Date)row[2]);
//					tr.setCost((Double)row[3]);
//					tr.setNumberOfRegistrations((Integer)row[4]);
//					tr.setAvailability((String)row[5]);
//					tr.setStartingPlace((String)row[6]);
//					tr.setDestination((String)row[7]);
//					trList.add(tr);
//				}
//				
//				//return tr;
//			} catch(Exception e){
//				e.printStackTrace();
//				//throw e;
//			}finally{
//				session.close();
//			}
//		} catch (HibernateException e) {
//			e.printStackTrace();
//		} 
//		//return null;
//	}
	}
}
