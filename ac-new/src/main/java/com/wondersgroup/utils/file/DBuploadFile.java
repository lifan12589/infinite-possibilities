package com.wondersgroup.utils.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import wfc.service.database.BlobHelper;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.SQL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
public class DBuploadFile {

    @RequestMapping("Multipart/uploadFile.do")
    public void upFile (HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/piain;charset=UTF-8");
        MultipartHttpServletRequest params = (MultipartHttpServletRequest) request;
        String id = params.getParameter("id");
        System.out.println("id"+id);
        MultipartFile file = params.getFile("file");
        if (file == null) {
            System.out.println("附件为空!");
            response.getWriter().print("附件为空!");
            return;
        }
        byte[] filebyte;
        String fileName = file.getOriginalFilename();
        filebyte = file.getBytes();
        if (filebyte == null || filebyte.length == 0) {
            System.out.println("附件为空!");
            response.getWriter().print("附件为空!");
            return;
        }
        String uuid = UUID.randomUUID().toString();
        String fjId = "202008"+UUID.randomUUID().toString();
        String sql = "insert into DANGAN_FJ(ST_FJ_ID,ST_APPLY_ID,FJ_NAME) values (?,?,?)";
        Object[] objects = new Object[] { uuid, fjId, fileName };
        SQL.execute(sql,objects);
        Conditions conds = Conditions.newOrConditions();
        conds.add(new Condition("ST_FJ_ID", Condition.OT_EQUAL, uuid));
        BlobHelper.setBlob("DANGAN_FJ", "APPLY_FJ", conds.toString(),filebyte, conds.getObjectArray());
        response.getWriter().print("上传成功！");
    }



}
