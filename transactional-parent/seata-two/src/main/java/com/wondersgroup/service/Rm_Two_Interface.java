package com.wondersgroup.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface Rm_Two_Interface {

    @TwoPhaseBusinessAction(name = "rm2TccAction",commitMethod = "rm2Commit",rollbackMethod = "rm2Rollback")
    public String rm2(BusinessActionContext businessActionContext);

    public boolean rm2Commit(BusinessActionContext businessActionContext);

    public boolean rm2Rollback(BusinessActionContext businessActionContext);





}
