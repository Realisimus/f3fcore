package f3f.data_connector.service;

import f3f.data_connector.entity.CupDetail;

import java.util.List;

public interface CupDetailService {

    CupDetail getById(Integer id);
    List<CupDetail> getAll();
    void removeById(Integer id);
    void save(CupDetail cupDetail);

}
