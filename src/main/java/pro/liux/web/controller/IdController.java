package pro.liux.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.liux.web.utils.IdWorker;
import pro.liux.web.vo.Result;

@RestController
@CrossOrigin
public class IdController {
    @Autowired
    IdWorker idWorker;
    @GetMapping("getId")
    public Result getId(){
        return Result.success(idWorker.nextId());
    }

}
