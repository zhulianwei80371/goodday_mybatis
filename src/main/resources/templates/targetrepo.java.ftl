package com.dcits.ensemble.ddmp.business.targetrepo;

import com.dcits.ensemble.nbl.business.entity.${entity?replace("TargetRepo", "")};
import com.dcits.comet.dao.DaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ${entity} {

    @Autowired
    private DaoSupport daoSupport;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void insert(${entity?replace("TargetRepo", "")} ${entity?replace("TargetRepo", "")?uncap_first}) {
        daoSupport.insert(${entity?replace("TargetRepo", "")?uncap_first});
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public java.util.List<${entity?replace("TargetRepo", "")}> selectAll() {
        return daoSupport.selectList(new ${entity?replace("TargetRepo", "")}());
    }
}