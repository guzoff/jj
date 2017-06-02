package com.guzoff.jj.service;

import java.util.List;
import com.guzoff.jj.model.Convict;

public interface ConvictService {

    Convict findById(Integer id);

    void saveConvict(Convict convict);

    void updateConvict(Convict convict);

    void deleteConvictByNick(String nickname);

    List<Convict> findAllConvicts();

    Convict findConvictByNick(String nickname);

    boolean isConvictNickUnique(Integer id, String nickname);

}
