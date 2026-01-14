package com.dcits.ensemble.ddmp.batch.step.nbl;

import com.dcits.comet.batch.model.StepContext;
import com.dcits.comet.commons.utils.BusiUtil;
import com.dcits.ensemble.ddmp.batch.common.AbstractBaseDataSegementStep;
import com.dcits.ensemble.ddmp.business.repository.${entity?replace("Step", "")}Repository;
import com.dcits.ensemble.nbl.business.entity.${entity?replace("Step", "")};
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class ${entity} extends AbstractBaseDataSegementStep<${entity?replace("Step", "")}, ${entity?replace("Step", "")}> {

    @Resource
    private ${entity?replace("Step", "")}Repository ${entity?replace("Step", "")?uncap_first}Repository;

    @Override
    public void writeChunk(StepContext stepContext, List<${entity?replace("Step", "")}> list) {
        log.info("${entity} is start");
        if (BusiUtil.isNotNull(list)) {
            ${entity?replace("Step", "")?uncap_first}Repository.insertTargetSchema(list);
        }
        log.info("${entity} is end");
    }
}