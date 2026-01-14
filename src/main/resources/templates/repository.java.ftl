package com.dcits.ensemble.ddmp.business.repository;

import com.dcits.ensemble.nbl.business.entity.${entity?replace("Repository", "")};
import com.dcits.comet.dao.DaoSupport;
import com.dcits.libra.client.aspect.logicdatasource.LibraDynamicLogicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dcits.ensemble.repository.BusinessRepository;
import java.util.List;

import static com.dcits.ensemble.ddmp.business.constant.SchemaConstant.CMS_SCHEMA;
import static com.dcits.ensemble.ddmp.business.constant.SchemaConstant.DDMP_SCHEMA;

@Repository
public class ${entity} extends BusinessRepository {

    @Autowired
    private DaoSupport daoSupport;

    @LibraDynamicLogicDataSource(name = CMS_SCHEMA)
    public void insertTargetSchema(List<${entity?replace("Repository", "")}> list) {
        for (${entity?replace("Repository", "")} entity : list) {
            daoSupport.insert(entity);
        }
    }

    @LibraDynamicLogicDataSource(name = DDMP_SCHEMA)
    public java.util.List<${entity?replace("Repository", "")}> selectTargetAll() {
        return daoSupport.selectList(new ${entity?replace("Repository", "")}());
    }

    @LibraDynamicLogicDataSource(name = DDMP_SCHEMA)
    public void insertBatch(List<${entity?replace("Repository", "")}> list) {
        daoSupport.insertAddBatch(list);
    }
}