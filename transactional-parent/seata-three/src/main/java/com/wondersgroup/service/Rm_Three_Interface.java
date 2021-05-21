package com.wondersgroup.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface Rm_Three_Interface {

    @TwoPhaseBusinessAction(name = "rm3TccAction",commitMethod = "rm3Commit",rollbackMethod = "rm3Rollback")
    public String rm3(BusinessActionContext businessActionContext);

    public boolean rm3Commit(BusinessActionContext businessActionContext);

    public boolean rm3Rollback(BusinessActionContext businessActionContext);


}
