package com.guzoff.jj.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.guzoff.jj.model.Convict;

@Repository("convictDao")
public class ConvictDAOImpl extends AbstractDAO<Integer, Convict> implements ConvictDAO {

    @Override
    public void deleteConvictByNick(String nickname) {
        Query query = getSession().createSQLQuery(
                "delete from Convict where nickname = :nick");
        query.setString("nick", nickname);
        query.executeUpdate();
    }

    @Override
    public Convict findById(Integer id) {
        return getByKey(id);
    }

    @Override
    public void saveConvict(Convict convict) {
        persist(convict);
    }

    @Override
    public List<Convict> findAllConvicts() {
        Criteria criteria = createEntityCriteria();
        return (List<Convict>) criteria.list();
    }

    @Override
    public Convict findConvictByNick(String nickname) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("nickname", nickname));
        return (Convict) criteria.uniqueResult();
    }

}
