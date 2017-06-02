package com.guzoff.jj.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.guzoff.jj.model.Convict;
import com.guzoff.jj.dao.ConvictDAO;

@Service("convictService")
@Transactional
public class ConvictServiceImpl implements ConvictService {

    @Autowired
    private ConvictDAO dao;

    @Override
    public void deleteConvictByNick(String nickname) {
        dao.deleteConvictByNick(nickname);
    }

    @Override
    public Convict findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public void saveConvict(Convict convict) {
        dao.saveConvict(convict);
    }

    @Override
    public void updateConvict(Convict convict) {
        Convict entity = dao.findById(convict.getId());
        if (entity != null) {
            entity.setNickname(convict.getNickname());
            entity.setSex(convict.getSex());
            entity.setJailingDate(convict.getJailingDate());
            entity.setCrimeCode(convict.getCrimeCode());
            entity.setSentencePeriod(convict.getSentencePeriod());
        }

    }

    @Override
    public List<Convict> findAllConvicts() {
        return dao.findAllConvicts();
    }

    @Override
    public Convict findConvictByNick(String nickname) {
        return dao.findConvictByNick(nickname);
    }

    @Override
    public boolean isConvictNickUnique(Integer id, String nickname) {
        Convict convict = findConvictByNick(nickname);
        return convict == null 
                || convict.getId().equals(id);
    }

}
