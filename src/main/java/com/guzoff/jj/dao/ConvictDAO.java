package com.guzoff.jj.dao;

import java.util.List;
import com.guzoff.jj.model.Convict;

public interface ConvictDAO {

    Convict findById(Integer id);

    void saveConvict(Convict convict);

    void deleteConvictByNick(String nickname);

    List<Convict> findAllConvicts();

    Convict findConvictByNick(String nickname);
}
